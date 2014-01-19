package br.pucrio.inf.lac.invisiblebattler.test;

import java.util.Date;
import java.util.Vector;

import br.pucrio.inf.lac.invisiblebattler.dao.BaseDAO;
import br.pucrio.inf.lac.invisiblebattler.dao.BattleDAO;
import br.pucrio.inf.lac.invisiblebattler.dao.ClanDAO;
import br.pucrio.inf.lac.invisiblebattler.dao.RegionDAO;
import br.pucrio.inf.lac.invisiblebattler.dao.UserDAO;
import br.pucrio.inf.lac.invisiblebattler.model.Battle;
import br.pucrio.inf.lac.invisiblebattler.model.Clan;
import br.pucrio.inf.lac.invisiblebattler.model.Region;
import br.pucrio.inf.lac.invisiblebattler.model.User;

public class TestBD {

	public static void main(String[] args) {
		TestBD test = new TestBD();
		test.user();
//		test.clan();
//		test.battle();
//		test.openClose();
	}
	
	public void user() {
//		userInsere();
		userBusca();
//		userAtualiza();
//		userRemove();
	}
	public void clan() {
		clanInsere();
//		clanBusca();
//		clanAtualiza();
//		clanRemove();
	}
	public void battle() {
//		battleInsere();
//		battleBusca();
//		battleAtualiza();
//		battleRemove();
	}
	public void region() {
		regionInsere();
		regionBusca();
		regionRemove();
		regionAtualiza();
	}

	public void regionInsere() {
		RegionDAO dao = new RegionDAO();
		Region region = new Region();
		region.setName("A");
		region.setPoints("1;2I3;4I4;1");
		dao.insere(region);
	}

	public void regionBusca() {
		RegionDAO dao = new RegionDAO();

		Vector<Region> vet = dao.buscarTodos();
		for (Region region : vet) {
			System.out.println(region.toString());
			;
		}
	}

	public void regionRemove() {
		RegionDAO dao = new RegionDAO();
		dao.apagar(5);
	}

	public void regionAtualiza() {
		RegionDAO dao = new RegionDAO();
		Region region = new Region();
		region.setId(2);
		region.setName("A");
		region.setPoints("2;2I3;4I4;1");
		dao.atualizar(region);
	}

	public void battleInsere() {
		BattleDAO dao = new BattleDAO();

		Battle battle = new Battle();
		battle.setDate(new Date());

		RegionDAO daoReg = new RegionDAO();
		Vector<Region> vet = daoReg.buscarTodos();
		Region region = vet.elementAt(0);

		battle.setRegion(region);
		battle.setTimeFrameID(0);
		dao.insere(battle);
	}
	public void battleBusca() {
		BattleDAO dao = new BattleDAO();

		Vector<Battle> vet = dao.buscarTodos();
		for (Battle battle : vet) {
			System.out.println(battle.toString());
			;
		}
	}

	public void battleAtualiza() {
		BattleDAO dao = new BattleDAO();
		Battle battle= new Battle();
		battle.setId(1);
		
		RegionDAO daoReg = new RegionDAO();
		Vector<Region> vet = daoReg.buscarTodos();
		Region region = vet.elementAt(0);

		battle.setRegion(region);
		battle.setTimeFrameID(1);
		battle.setDate(new Date());
		dao.atualizar(battle);
	}

	public void battleRemove() {
		BattleDAO dao = new BattleDAO();
		dao.apagar(1);
	}

	public void clanInsere() {
		ClanDAO dao = new ClanDAO();
		Clan clan = new Clan();
		clan.setName("A");
		dao.insere(clan);
	}
	public void clanBusca() {
		ClanDAO dao = new ClanDAO();

		Vector<Clan> vet = dao.buscarTodos();
		for (Clan clan : vet) {
			System.out.println(clan.toString());
			;
		}
	}

	public void clanAtualiza() {
		ClanDAO dao = new ClanDAO();
		Clan clan = new Clan();
		clan.setId(1);
		clan.setName("B");
		dao.atualizar(clan);
	}

	public void clanRemove() {
		ClanDAO dao = new ClanDAO();
		dao.apagar(1);
	}

	public void openClose() {	
		BaseDAO dao = new BaseDAO();
		dao.conectar();
		dao.fechar();
	}
	public void userInsere() {
		UserDAO dao = new UserDAO();

		User user = new User();

		ClanDAO daoReg = new ClanDAO();
		Vector<Clan> vet = daoReg.buscarTodos();
		Clan clan = vet.elementAt(0);

		user.setClan(clan);
		user.setEmail("email");
		user.setPassword("password");
		user.setName("name");
		user.setNum_victories(1);
		user.setExp_points(2);
		user.setLevel(3);
		user.setBase_stren(4);
		user.setBase_intel(5);
		user.setBase_agili(6);
		user.setMod_stren(7);
		user.setMod_intel(8);
		user.setMod_agili(9);

		dao.insere(user);
	}
	public void userBusca() {
		UserDAO dao = new UserDAO();

		Vector<User> vet = dao.buscarTodos();
		for (User user : vet) {
			System.out.println(user.toString());
			;
		}
	}

	public void userAtualiza() {
		BattleDAO dao = new BattleDAO();
		Battle battle= new Battle();
		battle.setId(1);
		
		RegionDAO daoReg = new RegionDAO();
		Vector<Region> vet = daoReg.buscarTodos();
		Region region = vet.elementAt(0);

		battle.setRegion(region);
		battle.setTimeFrameID(1);
		battle.setDate(new Date());
		dao.atualizar(battle);
	}

	public void userRemove() {
		UserDAO dao = new UserDAO();
		dao.apagar(1);
	}

}
