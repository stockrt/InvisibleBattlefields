package br.pucrio.inf.lac.helloworld;

import java.io.Serializable;
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
import lac.puc.ubi.invbat.concept.model.LocationRequest;
import lac.puc.ubi.invbat.concept.model.RegistrationRequest;
import lac.puc.ubi.invbat.concept.model.UserDataRequest;

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

		System.out.println("[LoginCoreServer] Mensagem recebida do cliente: "
				+ Serialization.fromJavaByteStream(message.getContent())
				+ "| className:" + className + " | uuid:" + uuid);

		if (className != null) {
			if (className.equals(UserDataRequest.class.getCanonicalName())) {
				// envia login (autenticação) (cliente-server)
				processUserData(message);
			} else if (className.equals(RegistrationRequest.class
					.getCanonicalName())) {
				// criar usuário (cliente-server) retornar UserDataResponse
				processRegistration(message);
			} else if (className.equals(LocationRequest.class
					.getCanonicalName())) {
				// uuid e latlng (cliente-server) retorna FightRequest
				processLocation(message);
			}
		}

		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setGatewayId(message.getGatewayId());
		privateMessage.setNodeId(message.getSenderId());

		synchronized (core) {
			counter++;
		}

		ApplicationMessage appMsg = new ApplicationMessage();
		appMsg.setContentObject(counter);
		privateMessage.setMessage(Serialization.toProtocolMessage(appMsg));

		core.writeTopic(PrivateMessage.class.getSimpleName(), privateMessage);
	}

	private void processUserData(Message message) {
		UserDataRequest requestMessage = (UserDataRequest) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[UserDataRequest]: " + requestMessage.toString());
	}

	private void processRegistration(Message message) {
		RegistrationRequest requestMessage = (RegistrationRequest) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[RegistrationRequest]: "
				+ requestMessage.toString());
	}

	private void processLocation(Message message) {
		LocationRequest requestMessage = (LocationRequest) Serialization
				.fromJavaByteStream(message.getContent());
		System.out.println("[LocationRequest]: " + requestMessage.toString());

	}
}
