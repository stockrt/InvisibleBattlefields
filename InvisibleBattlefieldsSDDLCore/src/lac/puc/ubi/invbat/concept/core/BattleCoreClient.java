package lac.puc.ubi.invbat.concept.core;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;
import lac.cnclib.sddl.serialization.Serialization;
import lac.puc.ubi.invbat.concept.comm.BattleResultRequest;
import lac.puc.ubi.invbat.concept.comm.BattleResultResponse;
import lac.puc.ubi.invbat.concept.comm.FightRequest;
import lac.puc.ubi.invbat.concept.comm.FightResponse;
import lac.puc.ubi.invbat.concept.comm.LocationRequest;
import lac.puc.ubi.invbat.concept.comm.LoginRequest;
import lac.puc.ubi.invbat.concept.comm.LoginResponse;
import lac.puc.ubi.invbat.concept.comm.RegistrationRequest;
import lac.puc.ubi.invbat.concept.dao.BattleDAO;
import lac.puc.ubi.invbat.concept.dao.CharacterDAO;
import lac.puc.ubi.invbat.concept.dao.ClanDAO;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.CharacterData;
import lac.puc.ubi.invbat.concept.model.ClanData;

public class BattleCoreClient implements NodeConnectionListener {

	private static String gatewayIP = "127.0.0.1";
	private static int gatewayPort = 5500;
	private MrUdpNodeConnection connection;

	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.OFF);

		new BattleCoreClient();
	}

	public BattleCoreClient() {
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
		// ApplicationMessage message = new ApplicationMessage();

		// Autenticação
		autenticandoOk(remoteCon);
		autenticandoErro(remoteCon);

		// Criando Usuário
		registrando(remoteCon);

		// Passando por uma regiao
		movendo(remoteCon);

		// solicitando resultado da batalha
		batteResult(remoteCon);
	}

	private void batteResult(NodeConnection remoteCon) {
		ApplicationMessage message = new ApplicationMessage();

		BattleDAO daoB = new BattleDAO();
		Vector<BattleData> vetB = daoB.buscarTodos();
		int indexB = (int) (vetB.size() * Math.random());
		BattleData battle = vetB.elementAt(indexB);
		int battleId = battle.getId();

		CharacterDAO daoC = new CharacterDAO();
		Vector<CharacterData> vetC = daoC.buscarTodos();
		int indexC = (int) (vetC.size() * Math.random());
		CharacterData charData = vetC.elementAt(indexC);
		int charId = charData.getId();

		ClanDAO daoClan = new ClanDAO();
		Vector<ClanData> vetClan = daoClan.buscarTodos();
		int indexClan = (int) (vetClan.size() * Math.random());
		ClanData clanData = vetClan.elementAt(indexClan);
		int clanId = clanData.getId();
		BattleResultRequest request = new BattleResultRequest(battleId, charId,
				clanId);

		message.setContentObject(request);

		try {
			remoteCon.sendMessage(message);
			Thread.sleep(5000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	private void movendo(NodeConnection remoteCon) {
		ApplicationMessage message = new ApplicationMessage();
		// REQUEST login: passando login e senha (autenticação)

		// Criando Usuário
		double lat = Math.random() * 1000;
		double lng = Math.random() * 1000;
		LocationRequest request = new LocationRequest(lat, lng);

		message.setContentObject(request);

		try {
			remoteCon.sendMessage(message);
			Thread.sleep(5000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	private void registrando(NodeConnection remoteCon) {
		ApplicationMessage message = new ApplicationMessage();
		// REQUEST login: passando login e senha (autenticação)
		Date date = new Date();
		String _email = "ilima" + date.getTime() + "@inf.puc-rio.br";
		String _pass = "1234";
		String _charName = "xvan";
		ClanDAO dao = new ClanDAO();
		Vector<ClanData> vet = dao.buscarTodos();
		int index = (int) (Math.random() * vet.size());
		int _clanId = vet.elementAt(index).getId();

		// Criando Usuário
		RegistrationRequest request = new RegistrationRequest(_email,
				_charName, _pass, _clanId);

		message.setContentObject(request);

		try {
			remoteCon.sendMessage(message);
			Thread.sleep(5000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

	private void autenticandoOk(NodeConnection remoteCon) {
		ApplicationMessage message = new ApplicationMessage();
		// REQUEST login: passando login e senha (autenticação)
		String _email = "ilima@inf.puc-rio.br";
		String _pass = "1234";

		// Logando
		LoginRequest request = new LoginRequest(_email, _pass);
		message.setContentObject(request);
		try {
			remoteCon.sendMessage(message);
			Thread.sleep(5000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

	private void autenticandoErro(NodeConnection remoteCon) {
		ApplicationMessage message = new ApplicationMessage();
		// REQUEST login: passando login e senha (autenticação)
		String _email = "ilima@inf.puc-rio.br";
		String _pass = "123";

		// Logando
		LoginRequest request = new LoginRequest(_email, _pass);
		message.setContentObject(request);

		try {
			remoteCon.sendMessage(message);
			Thread.sleep(5000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
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
				+ "|\n className:" + className + " | uuid:" + uuid);

		if (className != null) {
			// resposta do LoginRequest enviado
			if (className.equals(LoginResponse.class.getCanonicalName())) {
				LoginResponse response = (LoginResponse) Serialization
						.fromJavaByteStream(message.getContent());
				System.out.println("Answer: " + response.getAuthAnswer());
				if (response.getAuthAnswer()) {
					System.out.println("Char: "
							+ response.getChardata().toString());
					// faça alguma coisa
				}
			}
			// resposta do LocationRequest, se passar por uma área de batalha
			else if (className.equals(FightRequest.class.getCanonicalName())) {
				aceitaLuta(remoteCon, message);
			} else if (className.equals(BattleResultResponse.class
					.getCanonicalName())) {
				atualizaLuta(remoteCon, message);
			} else {
				System.out.println("nada");
			}

		}
	}

	// other methods

	private void atualizaLuta(NodeConnection remoteCon, Message message) {
		BattleResultResponse request = (BattleResultResponse) Serialization
				.fromJavaByteStream(message.getContent());

		System.out.println(request.getBattleResultData().toString());

	}

	private void aceitaLuta(NodeConnection remoteCon, Message message) {
		FightRequest request = (FightRequest) Serialization
				.fromJavaByteStream(message.getContent());
		BattleData battle = request.getBattle();
		CharacterData charData = request.getCharData();

		ClanDAO dao = new ClanDAO();
		Vector<ClanData> vet = dao.buscarTodos();
		int index = (int) (Math.random() * vet.size());
		ClanData clan = vet.elementAt(index);
		// Logando
		FightResponse response = new FightResponse(charData.getId(),
				battle.getId(), true, clan.getId());
		message.setContentObject(response);

		try {
			remoteCon.sendMessage(message);
			Thread.sleep(5000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

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
