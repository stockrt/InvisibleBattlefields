package br.pucrio.inf.lac.registry;

import java.io.Serializable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

import crublib.CrudLib;

import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.serialization.Serialization;
import lac.cnet.sddl.objects.ApplicationObject;
import lac.cnet.sddl.objects.Message;
import lac.cnet.sddl.objects.PrivateMessage;
import lac.cnet.sddl.udi.core.SddlLayer;
import lac.cnet.sddl.udi.core.UniversalDDSLayerFactory;
import lac.cnet.sddl.udi.core.listener.UDIDataReaderListener;
import modellibrary.RequestInfo;
import modellibrary.ResponseInfo;

public class RegistryCoreServer implements
		UDIDataReaderListener<ApplicationObject> {
	SddlLayer core;

	public RegistryCoreServer() {
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

		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static JsonNode handleRequest(RequestInfo requestMessage) {
		System.out.println("[RegistryCoreServer] Request type: "
				+ requestMessage.getType() + " | payload: "
				+ requestMessage.getPayload());

		JsonNode result_json = Json.newObject();

		switch (requestMessage.getType()) {
		case "addNode":
			result_json = CrudLib.addNode(Json.parse(requestMessage
					.getPayload()));
			break;
		case "getNode":
			result_json = CrudLib.getNode(requestMessage.getPayload());
			break;
		case "lstNodes":
			result_json = CrudLib.lstNodes();
			break;
		case "srchNodes":
			result_json = CrudLib.srchNodes(requestMessage.getPayload());
			break;
		case "updNode":
			result_json = CrudLib.updNode(requestMessage.getPayload(),
					Json.parse(requestMessage.getPayload()));
			break;
		case "delNode":
			result_json = CrudLib.delNode(requestMessage.getPayload());
			break;
		default:
			System.out
					.println("[RegistryCoreServer] Request type not recognized by server");
			ObjectNode result = Json.newObject();
			result.put("status", "ERR");
			result.put("message", "Request type not recognized by server");
			result.put("type", requestMessage.getType());
			result.put("payload", "");
			result_json = Json.fromJson(result, JsonNode.class);
			break;
		}

		return result_json;
	}

	@Override
	public void onNewData(ApplicationObject topicSample) {
		Message message = (Message) topicSample;
		Serializable serializable = Serialization.fromJavaByteStream(message
				.getContent());
		String className = serializable.getClass().getCanonicalName();

		System.out
				.println("[RegistryCoreServer] Mensagem recebida do cliente: "
						+ Serialization.fromJavaByteStream(message.getContent())
						+ " | " + className);

		if (className != null) {
			if (className.equals(RequestInfo.class.getCanonicalName())) {
				// REQUEST recv
				RequestInfo requestMessage = (RequestInfo) Serialization
						.fromJavaByteStream(message.getContent());
				JsonNode result_json = Json.newObject();
				result_json = handleRequest(requestMessage);

				// RESPONSE send
				ResponseInfo responseMessage = new ResponseInfo(
						message.getSenderId(), requestMessage.getType(),
						result_json.toString());

				PrivateMessage privateMessage = new PrivateMessage();
				privateMessage.setGatewayId(message.getGatewayId());
				privateMessage.setNodeId(message.getSenderId());

				ApplicationMessage appMessage = new ApplicationMessage();
				appMessage.setContentObject(responseMessage);
				privateMessage.setMessage(Serialization
						.toProtocolMessage(appMessage));

				core.writeTopic(PrivateMessage.class.getSimpleName(),
						privateMessage);
			} else {
				System.out
						.println("[RegistryCoreServer] Objeto desconhecido recebido do cliente: "
								+ className);
			}
		} else {
			System.out
					.println("[RegistryCoreServer] Objeto inválido recebido do cliente");
		}
	}
}
