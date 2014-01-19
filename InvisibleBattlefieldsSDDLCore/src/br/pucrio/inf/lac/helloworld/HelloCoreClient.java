package br.pucrio.inf.lac.helloworld;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pucrio.inf.lac.invisiblebattler.dao.BattleDAO;
import br.pucrio.inf.lac.invisiblebattler.model.Battle;

import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;
import lac.cnclib.sddl.serialization.Serialization;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.FightRequest;
import lac.puc.ubi.invbat.concept.model.UserDataRequest;
import lac.puc.ubi.invbat.concept.model.UserDataResponse;

public class HelloCoreClient implements NodeConnectionListener {

	private static String gatewayIP = "127.0.0.1";
	private static int gatewayPort = 5500;
	private MrUdpNodeConnection connection;

	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.OFF);

		new HelloCoreClient();
	}

	public HelloCoreClient() {
		InetSocketAddress address = new InetSocketAddress(gatewayIP,
				gatewayPort);
		try {
			connection = new MrUdpNodeConnection();
			connection.connect(address);
			connection.addNodeConnectionListener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connected(NodeConnection remoteCon) {
		ApplicationMessage message = new ApplicationMessage();
		// String serializableContent = "Hello World";

		java.util.UUID _id = remoteCon.getUuid();
		String _email = "ilima@inf.puc-rio.br";
		String _pass = "1234";
		// String _charName = "xvan";
		// int _clanId = 2;

		// Criando Usuário
		// RegistrationRequest registrationRequest = new
		// RegistrationRequest(_id,
		// _email, _charName, _pass, _clanId);
		// message.setContentObject(registrationRequest);

		// // autenticando
//		UserDataRequest userDataRequest = new UserDataRequest(_id, _email,
//				_pass);
//		message.setContentObject(userDataRequest);
		
		// Lutando
		BattleDAO dao = new BattleDAO();
		Vector<Battle> vet = dao.buscarTodos();
		Battle _battle = vet.elementAt(0); 
		
		FightRequest fightRequest = new FightRequest(_id, new BattleData(_battle));
		message.setContentObject(fightRequest);
		try {
			remoteCon.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void newMessageReceived(NodeConnection remoteCon, Message message) {
		System.out.println(message.getContentObject());
		// Message message = (Message) topicSample;
		Serializable serializable = Serialization.fromJavaByteStream(message
				.getContent());
		String className = serializable.getClass().getCanonicalName();
		String uuid = message.getSenderID().toString();

		System.out.println("[] Mensagem recebida do servidor: "
				+ Serialization.fromJavaByteStream(message.getContent())
				+ "| className:" + className + " | uuid:" + uuid);

		if (className != null) {
			if (className.equals(UserDataResponse.class.getCanonicalName())) {
				UserDataResponse response = (UserDataResponse) Serialization
						.fromJavaByteStream(message.getContent());
				System.out.println("Answer: " + response.getAuthAnswer());
				if (response.getAuthAnswer()) {
					System.out.println("Char: "
							+ response.getChardata().toString());
				}
			}
			// if
			// (className.equals(UserRegistrationResponse.class.getCanonicalName()))
			// {

			// }
		}
	}

	// other methods

	@Override
	public void reconnected(NodeConnection remoteCon, SocketAddress endPoint,
			boolean wasHandover, boolean wasMandatory) {
	}

	@Override
	public void disconnected(NodeConnection remoteCon) {
	}

	@Override
	public void unsentMessages(NodeConnection remoteCon,
			List<Message> unsentMessages) {
	}

	@Override
	public void internalException(NodeConnection remoteCon, Exception e) {
	}
}
