package lac.puc.ubi.invbat.concept.test;

import java.util.Date;
import java.util.Vector;

import lac.puc.ubi.invbat.concept.dao.BaseDAO;
import lac.puc.ubi.invbat.concept.dao.BattleDAO;
import lac.puc.ubi.invbat.concept.dao.BattleResultDAO;
import lac.puc.ubi.invbat.concept.dao.CharacterDAO;
import lac.puc.ubi.invbat.concept.dao.ClanDAO;
import lac.puc.ubi.invbat.concept.dao.RegionDAO;
import lac.puc.ubi.invbat.concept.model.BattleResultData;
import lac.puc.ubi.invbat.concept.model.RegionData;

public class TestBD {

	public static void main(String[] args) {
//		TestBD test = new TestBD();
//		test.userBattle();
//		test.user();
//		test.clan();
//		test.battle();
//		test.openClose();
	}
	
//	public void userBattle() {
////		userBattleInsere();
////		userBattleBusca();
////		userBattleAtualiza();
//		userBattleRemove();
//	}
//	
//	private void userBattleRemove() {
//		BattleResultDAO dao = new BattleResultDAO();
//		dao.apagar(1);
//	}
//
//	private void userBattleAtualiza() {
//		BattleResultDAO dao = new BattleResultDAO();
//		BattleResultData userBattle = dao.buscar(1);
//		userBattle.setExp_points(1);
//		userBattle.setSten(2);
//		userBattle.setIntel(3);
//		userBattle.setAgili(4);
//		userBattle.setCharFromId(24);
//		userBattle.setCharToId(2);
//		dao.atualizar(userBattle);
//	}
//
//	private void battleResultBusca() {
//		BattleResultDAO dao = new BattleResultDAO();
//		Vector<BattleResultData> vet = dao.buscarTodos();
//		for (BattleResultData userBattle : vet) {
//			System.out.println(userBattle.toString());
//		}
//	}
//
//	private void battleResultInsere() {
//		BattleResultDAO dao = new BattleResultDAO();
//
//		BattleResultData battleResult = new BattleResultData();
//		battleResult.setCharFromId(3);
//		battleResult.setCharToId(24);
//		battleResult.setBattleId(3);
//		battleResult.setDate(new Date());
//
//		dao.insere(battleResult);
//	}
//
//	public void user() {
////		userInsere();
//		userBusca();
////		userAtualiza();
////		userRemove();
//	}
//	public void clan() {
//		clanInsere();
////		clanBusca();
////		clanAtualiza();
////		clanRemove();
//	}
//	public void battle() {
////		battleInsere();
////		battleBusca();
////		battleAtualiza();
////		battleRemove();
//	}
//	public void region() {
//		regionInsere();
//		regionBusca();
//		regionRemove();
//		regionAtualiza();
//	}
//
//	public void regionInsere() {
//		RegionDAO dao = new RegionDAO();
//		RegionData region = new RegionData();
//		region.setName("A");
//		region.setPoints("1;2I3;4I4;1");
//		dao.insere(region);
//	}
//
//	public void regionBusca() {
//		RegionDAO dao = new RegionDAO();
//
//		Vector<RegionData> vet = dao.buscarTodos();
//		for (RegionData region : vet) {
//			System.out.println(region.toString());
//			;
//		}
//	}
//
//	public void regionRemove() {
//		RegionDAO dao = new RegionDAO();
//		dao.apagar(5);
//	}
//
//	public void regionAtualiza() {
//		RegionDAO dao = new RegionDAO();
//		RegionData region = new RegionData();
//		region.setId(2);
//		region.setName("A");
//		region.setPoints("2;2I3;4I4;1");
//		dao.atualizar(region);
//	}
//
//	public void battleInsere() {
//		BattleDAO dao = new BattleDAO();
//
//		Battle battle = new Battle();
//		battle.setDate(new Date());
//
//		RegionDAO daoReg = new RegionDAO();
//		Vector<RegionData> vet = daoReg.buscarTodos();
//		RegionData region = vet.elementAt(0);
//
//		battle.setRegion(region);
//		battle.setTimeFrameID(0);
//		dao.insere(battle);
//	}
//	public void battleBusca() {
//		BattleDAO dao = new BattleDAO();
//
//		Vector<Battle> vet = dao.buscarTodos();
//		for (Battle battle : vet) {
//			System.out.println(battle.toString());
//			;
//		}
//	}
//
//	public void battleAtualiza() {
//		BattleDAO dao = new BattleDAO();
//		Battle battle= new Battle();
//		battle.setId(1);
//		
//		RegionDAO daoReg = new RegionDAO();
//		Vector<RegionData> vet = daoReg.buscarTodos();
//		RegionData region = vet.elementAt(0);
//
//		battle.setRegion(region);
//		battle.setTimeFrameID(1);
//		battle.setDate(new Date());
//		dao.atualizar(battle);
//	}
//
//	public void battleRemove() {
//		BattleDAO dao = new BattleDAO();
//		dao.apagar(1);
//	}
//
//	public void clanInsere() {
//		ClanDAO dao = new ClanDAO();
//		Clan clan = new Clan();
//		clan.setName("A");
//		dao.insere(clan);
//	}
//	public void clanBusca() {
//		ClanDAO dao = new ClanDAO();
//
//		Vector<Clan> vet = dao.buscarTodos();
//		for (Clan clan : vet) {
//			System.out.println(clan.toString());
//			;
//		}
//	}
//
//	public void clanAtualiza() {
//		ClanDAO dao = new ClanDAO();
//		Clan clan = new Clan();
//		clan.setId(1);
//		clan.setName("B");
//		dao.atualizar(clan);
//	}
//
//	public void clanRemove() {
//		ClanDAO dao = new ClanDAO();
//		dao.apagar(1);
//	}
//
//	public void openClose() {	
//		BaseDAO dao = new BaseDAO();
//		dao.conectar();
//		dao.fechar();
//	}
//	public void userInsere() {
//		CharacterDAO dao = new CharacterDAO();
//
//		User user = new User();
//
//		ClanDAO daoReg = new ClanDAO();
//		Vector<Clan> vet = daoReg.buscarTodos();
//		Clan clan = vet.elementAt(0);
//
//		user.setClan(clan);
//		user.setEmail("email");
//		user.setPassword("password");
//		user.setName("name");
//		user.setNum_victories(1);
//		user.setExp_points(2);
//		user.setLevel(3);
//		user.setBase_stren(4);
//		user.setBase_intel(5);
//		user.setBase_agili(6);
//		user.setMod_stren(7);
//		user.setMod_intel(8);
//		user.setMod_agili(9);
//
//		dao.insere(user);
//	}
//	public void userBusca() {
//		CharacterDAO dao = new CharacterDAO();
//
//		Vector<User> vet = dao.buscarTodos();
//		for (User user : vet) {
//			System.out.println(user.toString());
//			;
//		}
//	}
//
//	public void userAtualiza() {
//		BattleDAO dao = new BattleDAO();
//		Battle battle= new Battle();
//		battle.setId(1);
//		
//		RegionDAO daoReg = new RegionDAO();
//		Vector<RegionData> vet = daoReg.buscarTodos();
//		RegionData region = vet.elementAt(0);
//
//		battle.setRegion(region);
//		battle.setTimeFrameID(1);
//		battle.setDate(new Date());
//		dao.atualizar(battle);
//	}
//
//	public void userRemove() {
//		CharacterDAO dao = new CharacterDAO();
//		dao.apagar(1);
//	}
//
}
