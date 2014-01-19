package br.pucrio.inf.lac.invisiblebattler.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import br.pucrio.inf.lac.invisiblebattler.model.UserBattle;

public class UserBattleDAO extends BaseDAO {

	public void apagar(Integer id) {
		conectar();
		try {
			comando.executeUpdate("DELETE FROM Battle_has_User WHERE id = "
					+ id + ";");
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Battle_has_Users", e.getMessage());
		} finally {
			fechar();
		}
	}

	private UserBattle setUserBattle(ResultSet rs) throws SQLException {
		UserBattle temp = new UserBattle();
		// pega todos os atributos da Battle_has_User
		temp.setId(rs.getInt("id"));
		temp.setBattle(rs.getInt("Battle_id"));
		temp.setUserFrom(rs.getInt("User_From"));
		temp.setUserTo(rs.getInt("User_To"));
		temp.setDate(rs.getDate("date"));
		temp.setExp_points(rs.getInt("exp_points"));
		temp.setSten(rs.getDouble("sten"));
		temp.setIntel(rs.getDouble("intel"));
		temp.setAgili(rs.getDouble("agili"));
		temp.setRecived(rs.getBoolean("recived"));
		return temp;
	}

	public Vector<UserBattle> buscarTodos() {
		conectar();
		Vector<UserBattle> resultados = new Vector<UserBattle>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM Battle_has_User");
			while (rs.next()) {
				UserBattle temp = setUserBattle(rs);
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Battle_has_Users", e.getMessage());
			return null;
		}
	}

	public void atualizar(UserBattle userBattle) {
		conectar();
		long mili = userBattle.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String com = "UPDATE Battle_has_User SET Battle_id = "
				+ userBattle.getBattle().getId() + ", User_From = "
				+ userBattle.getUserFrom().getId() + ", User_To = "
				+ userBattle.getUserTo().getId() + ", date ='"
				+ dataSQL.toString() + "', exp_points = "
				+ userBattle.getExp_points() + ", sten = "
				+ userBattle.getSten() + ", intel = " + userBattle.getIntel()
				+ ", agili = " + userBattle.getAgili() + ", recived = "
				+ userBattle.isRecived() + " WHERE  id = " + userBattle.getId()
				+ ";";
		System.out.println("Atualizada!");
		try {
			comando.executeUpdate(com);
		} catch (SQLException e) {
			System.out.println("Sql:" + com);
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

	public UserBattle buscar(Integer id) {
		conectar();
		Vector<UserBattle> resultados = new Vector<UserBattle>();
		ResultSet rs;
		UserBattle userBattle = null;
		try {
			rs = comando
					.executeQuery("SELECT * FROM Battle_has_User WHERE id = "
							+ id + ";");
			while (rs.next()) {
				UserBattle temp = setUserBattle(rs);
				resultados.add(temp);
				return temp;
			}
			return userBattle;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Battle_has_User", e.getMessage());
			return null;
		}

	}

	public void insere(UserBattle userBattle) {
		conectar();
		long mili = userBattle.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String sql = "";
		try {
			sql = "INSERT INTO Battle_has_User VALUES(null" + " , "
					+ userBattle.getBattle().getId() + " ,"
					+ userBattle.getUserFrom().getId() + " , "
					+ userBattle.getUserTo().getId() + " , '"
					+ dataSQL.toString() + "' , " + userBattle.getExp_points()
					+ " , " + userBattle.getSten() + " , "
					+ userBattle.getIntel() + " , " + userBattle.getAgili()
					+ " , " + userBattle.isRecived() + ")";
			comando.executeUpdate(sql);
			System.out.println("Inserida!");
		} catch (SQLException e) {
			System.out.println("sql: " + sql);
			imprimeErro("Erro ao inserir Battle_has_User", e.getMessage());
		} finally {
			fechar();
		}
	}
}
