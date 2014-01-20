package br.pucrio.inf.lac.helloworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;
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
import lac.puc.ubi.invbat.concept.comm.FightRequest;
import lac.puc.ubi.invbat.concept.comm.FightResponse;
import lac.puc.ubi.invbat.concept.comm.LocationRequest;
import lac.puc.ubi.invbat.concept.comm.LoginRequest;
import lac.puc.ubi.invbat.concept.comm.LoginResponse;
import lac.puc.ubi.invbat.concept.comm.RegistrationRequest;
import lac.puc.ubi.invbat.concept.dao.BattleDAO;
import lac.puc.ubi.invbat.concept.dao.BattleResultDAO;
import lac.puc.ubi.invbat.concept.dao.CharacterDAO;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.BattleResultData;
import lac.puc.ubi.invbat.concept.model.CharacterData;
import ContextNetGeo.CtxCoordinate;
import ContextNetGeo.Polygon;

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
			if (className.equals(LoginRequest.class.getCanonicalName())) {
				// envia login (autenticação) (cliente-server)
				// retornando o UserDataResponse
				appMsg = processLogin(message);
			} else if (className.equals(RegistrationRequest.class
					.getCanonicalName())) {
				// criar usuário (cliente-server) retornar UserDataResponse
				// retornando o UserDataResponse
				appMsg = processRegistration(message);
			} else if (className.equals(LocationRequest.class
					.getCanonicalName())) {
				// uuid e latlng (cliente-server) retorna FightRequest
				appMsg = processLocation(message);
			} else if (className.equals(FightResponse.class.getCanonicalName())) {
				// uuid e latlng (cliente-server) retorna FightRequest
				appMsg = processFight(message);
			} else {
				appMsg = new ApplicationMessage();
				appMsg.setContentObject("Nada");
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

	private ApplicationMessage processLogin(Message message) {
		System.out.println("[LoginRequest]: " + message.toString());
		LoginRequest request = (LoginRequest) Serialization
				.fromJavaByteStream(message.getContent());
		ApplicationMessage appMsg = new ApplicationMessage();
		LoginResponse response = null;
		// UUID uuid = message.getSenderId();
		try {
			CharacterDAO dao = new CharacterDAO();
			CharacterData charData = dao.buscar(request.getEmail(),
					request.getPassword());
			if (charData != null) {
				CharacterData data = new CharacterData(charData);
				response = new LoginResponse(true, data);
				appMsg.setContentObject(response);
			} else {
				response = new LoginResponse(false, null);
				appMsg.setContentObject(response);
			}
		} catch (Exception e) {

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
		LoginResponse response = null;
		// UUID uuid = message.getSenderId();
		try {
			CharacterDAO dao = new CharacterDAO();
			CharacterData charData = new CharacterData(request.getCharData());
			dao.insere(charData);
			response = new LoginResponse(true, charData);
			appMsg.setContentObject(response);
		} catch (Exception e) {
			response = new LoginResponse(false, null);
			appMsg.setContentObject("erro");
		}
		return appMsg;
	}

	private ApplicationMessage processLocation(Message message) {
		LocationRequest requestMessage = (LocationRequest) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[LocationRequest]: " + requestMessage.toString());
		ApplicationMessage appMsg = new ApplicationMessage();
		FightRequest request = null;

		double lat = requestMessage.getLatitude();
		double lng = requestMessage.getLongitude();
		ArrayList<CtxCoordinate> arrayCoordinate = new ArrayList<CtxCoordinate>();
		arrayCoordinate.add(new CtxCoordinate(lat, lng));
		arrayCoordinate.add(new CtxCoordinate(lat + 1, lng));
		arrayCoordinate.add(new CtxCoordinate(lat, lng + 1));
		arrayCoordinate.add(new CtxCoordinate(lat, lng));
		Polygon polygonUser = new Polygon(arrayCoordinate);

		try {
			// procurando batalha
			BattleDAO dao = new BattleDAO();
			Vector<BattleData> vet = dao.buscarTodos();
			BattleData battle = null;
			for (BattleData battleData : vet) {
				Polygon polygonRegion = battleData.getRegionData().getPolygon();
				if (polygonUser.GetData().intersects(polygonRegion.GetData())) {
					battle = battleData;
					break;
				}
			}

			if (battle != null) {
				CharacterDAO daoChar = new CharacterDAO();
				Vector<CharacterData> vet1 = daoChar.buscarTodos();
				int index = (int) (Math.random() % vet1.size());
				CharacterData charData = vet1.elementAt(index);

				request = new FightRequest(battle, charData);
				appMsg.setContentObject(request);
			}
		} catch (Exception e) {
			appMsg.setContentObject("Não há batalha");
		}
		return appMsg;

	}

	private ApplicationMessage processFight(Message message) {
		FightResponse requestMessage = (FightResponse) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[FightResponse]: " + requestMessage.toString());

		ApplicationMessage appMsg = new ApplicationMessage();
		try {
			BattleResultDAO dao = new BattleResultDAO();
			BattleResultData batteResultData = new BattleResultData();
			batteResultData.setClanId(requestMessage.getAttackingClanId());
			batteResultData.setBattleId(requestMessage.getBattleId());
			batteResultData.setCharFromId(requestMessage.getCharId());
			BattleDAO daoBattle = new BattleDAO();
			BattleData battle = daoBattle.buscar(requestMessage.getBattleId());
			batteResultData.setDate(battle.getDate());
			batteResultData.setState(0);
			dao.insere(batteResultData);
			appMsg.setContentObject("ok");
		} catch (Exception e) {
			appMsg.setContentObject("erro");
		}
		return appMsg;
	}

}
