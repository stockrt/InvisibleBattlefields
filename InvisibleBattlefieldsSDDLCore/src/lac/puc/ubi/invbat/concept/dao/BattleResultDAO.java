package lac.puc.ubi.invbat.concept.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import lac.puc.ubi.invbat.concept.model.BattleResultData;

public class BattleResultDAO extends BaseDAO {

	public void apagar(Integer id) {
		conectar();
		try {
			comando.executeUpdate("DELETE FROM BattleResult WHERE id = "
					+ id + ";");
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar BattleResults", e.getMessage());
		} finally {
			fechar();
		}
	}

	private BattleResultData setUserBattle(ResultSet rs) throws SQLException {
		BattleResultData temp = new BattleResultData();
		// pega todos os atributos da BattleResult
		temp.setId(rs.getInt("id"));
		temp.setBattleId(rs.getInt("Battle_id"));
		temp.setCharFromId(rs.getInt("Char_From"));
		temp.setCharToId(rs.getInt("Char_To"));
		temp.setDate(rs.getDate("date"));
		temp.setExp_points(rs.getInt("exp_points"));
		temp.setSten(rs.getDouble("sten"));
		temp.setIntel(rs.getDouble("intel"));
		temp.setAgili(rs.getDouble("agili"));
		temp.setState(rs.getInt("state"));
		return temp;
	}

	public Vector<BattleResultData> buscarTodos() {
		conectar();
		Vector<BattleResultData> resultados = new Vector<BattleResultData>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM BattleResult");
			while (rs.next()) {
				BattleResultData temp = setUserBattle(rs);
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar BattleResults", e.getMessage());
			return null;
		}
	}

	public void atualizar(BattleResultData userBattle) {
		conectar();
		long mili = userBattle.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String com = "UPDATE BattleResult SET Battle_id = "
				+ userBattle.getBattleId() + ", User_From = "
				+ userBattle.getCharFromId() + ", User_To = "
				+ userBattle.getCharToId() + ", date ='"
				+ dataSQL.toString() + "', exp_points = "
				+ userBattle.getExp_points() + ", sten = "
				+ userBattle.getSten() + ", intel = " + userBattle.getIntel()
				+ ", agili = " + userBattle.getAgili() + ", state = "
				+ userBattle.getState() + " WHERE  id = " + userBattle.getId()
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

	public BattleResultData buscar(Integer id) {
		conectar();
		Vector<BattleResultData> resultados = new Vector<BattleResultData>();
		ResultSet rs;
		BattleResultData userBattle = null;
		try {
			rs = comando
					.executeQuery("SELECT * FROM BattleResult WHERE id = "
							+ id + ";");
			while (rs.next()) {
				BattleResultData temp = setUserBattle(rs);
				resultados.add(temp);
				return temp;
			}
			return userBattle;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar BattleResult", e.getMessage());
			return null;
		}

	}

	public void insere(BattleResultData userBattle) {
		conectar();
		long mili = userBattle.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String sql = "";
		try {
			sql = "INSERT INTO BattleResult VALUES(null" + " , "
					+ userBattle.getBattleId() + " ,"
					+ userBattle.getCharFromId() + " , "
					+ userBattle.getCharToId() + " , '"
					+ dataSQL.toString() + "' , " + userBattle.getExp_points()
					+ " , " + userBattle.getSten() + " , "
					+ userBattle.getIntel() + " , " + userBattle.getAgili()
					+ " , " + userBattle.getState() + ")";
			comando.executeUpdate(sql);
			System.out.println("Inserida!");
		} catch (SQLException e) {
			System.out.println("sql: " + sql);
			imprimeErro("Erro ao inserir BattleResult", e.getMessage());
		} finally {
			fechar();
		}
	}
}
