package br.pucrio.inf.lac.helloworld;

import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.serialization.Serialization;
import lac.cnet.sddl.objects.ApplicationObject;
import lac.cnet.sddl.objects.Message;
import lac.cnet.sddl.objects.PrivateMessage;
import lac.cnet.sddl.udi.core.SddlLayer;
import lac.cnet.sddl.udi.core.UniversalDDSLayerFactory;
import lac.cnet.sddl.udi.core.listener.UDIDataReaderListener;
import lac.puc.ubi.invbat.concept.model.CharacterData;
import lac.puc.ubi.invbat.concept.model.FightRequest;
import lac.puc.ubi.invbat.concept.model.LocationRequest;
import lac.puc.ubi.invbat.concept.model.RegistrationRequest;
import lac.puc.ubi.invbat.concept.model.UserDataRequest;
import lac.puc.ubi.invbat.concept.model.UserDataResponse;
import br.pucrio.inf.lac.invisiblebattler.dao.UserDAO;
import br.pucrio.inf.lac.invisiblebattler.model.User;

public class HelloCoreServer implements
		UDIDataReaderListener<ApplicationObject> {
	SddlLayer core;
	int counter;

	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.OFF);

		new HelloCoreServer();
	}

	public HelloCoreServer() {
		core = UniversalDDSLayerFactory.getInstance();
		core.createParticipant(UniversalDDSLayerFactory.CNET_DOMAIN);

		core.createPublisher();
		core.createSubscriber();

		Object receiveMessageTopic = core.createTopic(Message.class,
				Message.class.getSimpleName());
		core.createDataReader(this, receiveMessageTopic);

		Object toMobileNodeTopic = core.createTopic(PrivateMessage.class,
				PrivateMessage.class.getSimpleName());
		core.createDataWriter(toMobileNodeTopic);

		counter = 0;
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onNewData(ApplicationObject topicSample) {
		Message message = (Message) topicSample;
		Serializable serializable = Serialization.fromJavaByteStream(message
				.getContent());
		String className = serializable.getClass().getCanonicalName();
		String uuid = message.getSenderId().toString();
		ApplicationMessage appMsg = null; // = new ApplicationMessage();

		System.out.println("[] Mensagem recebida do cliente: "
				+ Serialization.fromJavaByteStream(message.getContent())
				+ "| \nclassName:" + className + " | uuid:" + uuid);

		if (className != null) {
			if (className.equals(UserDataRequest.class.getCanonicalName())) {
				// envia login (autenticação) (cliente-server)
				// retornando o UserDataResponse
				appMsg = processUserData(message);
			} else if (className.equals(RegistrationRequest.class
					.getCanonicalName())) {
				// criar usuário (cliente-server) retornar UserDataResponse
				// retornando o UserDataResponse
				appMsg = processRegistration(message);
			} else if (className.equals(LocationRequest.class
					.getCanonicalName())) {
				// uuid e latlng (cliente-server) retorna FightRequest
				appMsg = processLocation(message);
			} else if (className.equals(FightRequest.class
					.getCanonicalName())) {
				// uuid e latlng (cliente-server) retorna FightRequest
				appMsg = processFight(message);
			}
		}

		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setGatewayId(message.getGatewayId());
		privateMessage.setNodeId(message.getSenderId());

		synchronized (core) {
			counter++;
		}

		// ApplicationMessage appMsg = new ApplicationMessage();
		// appMsg.setContentObject(counter);
		privateMessage.setMessage(Serialization.toProtocolMessage(appMsg));

		core.writeTopic(PrivateMessage.class.getSimpleName(), privateMessage);
	}

	private ApplicationMessage processUserData(Message message) {
		UserDataRequest requestMessage = (UserDataRequest) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[UserDataRequest]: " + requestMessage.toString());
		UserDataRequest request = (UserDataRequest) Serialization
				.fromJavaByteStream(message.getContent());
		ApplicationMessage appMsg = new ApplicationMessage();
		UserDataResponse response = null;
		UUID uuid = message.getSenderId();
		try {
			UserDAO dao = new UserDAO();
			User user = dao.buscar(request.getEmail(), request.getPassword());
			if (user != null) {
				CharacterData data = new CharacterData(user);
				response = new UserDataResponse(uuid, true, data);
				appMsg.setContentObject(response);
			} else {
				response = new UserDataResponse(uuid, false, null);
				appMsg.setContentObject(response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return appMsg;
	}

	private ApplicationMessage processRegistration(Message message) {
		RegistrationRequest requestMessage = (RegistrationRequest) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[RegistrationRequest]: "
				+ requestMessage.toString());
		RegistrationRequest request = (RegistrationRequest) Serialization
				.fromJavaByteStream(message.getContent());
		ApplicationMessage appMsg = new ApplicationMessage();
		UserDataResponse response = null;
		UUID uuid = message.getSenderId();
		try {
			UserDAO dao = new UserDAO();
			User user = new User();
			user.setClan(request.getCharData().clanId);
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setName(request.getCharData().name);
			user.setNum_victories(request.getCharData().num_victories);
			user.setExp_points(request.getCharData().exp_points);
			user.setLevel(request.getCharData().level);
			user.setBase_stren(request.getCharData().getAttributeStrength());
			user.setBase_intel(request.getCharData().getAttributeIntelligence());
			user.setBase_agili(request.getCharData().getAttributeAgility());
			user.setMod_stren(request.getCharData().getMod_stren());
			user.setMod_intel(request.getCharData().getMod_intel());
			user.setMod_agili(request.getCharData().getMod_agili());
			dao.insere(user);

			CharacterData data = new CharacterData(request.getCharData());
			response = new UserDataResponse(uuid, true, data);
			appMsg.setContentObject(response);
		} catch (Exception e) {
			appMsg.setContentObject("erro");
		}
		return appMsg;
	}

	private ApplicationMessage processLocation(Message message) {
		LocationRequest requestMessage = (LocationRequest) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[LocationRequest]: " + requestMessage.toString());
		return null;

	}
	private ApplicationMessage processFight(Message message) {
		FightRequest requestMessage = (FightRequest) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[FightRequest]: " + requestMessage.toString());
		
		return null;
	}

}
