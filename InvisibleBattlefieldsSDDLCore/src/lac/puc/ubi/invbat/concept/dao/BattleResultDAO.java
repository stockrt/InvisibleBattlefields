package lac.puc.ubi.invbat.concept.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import lac.puc.ubi.invbat.concept.model.BattleResultData;

public class BattleResultDAO extends BaseDAO {

	public void apagar(Integer id) {
		conectar();
		try {
			comando.executeUpdate("DELETE FROM BattleResult WHERE id = " + id
					+ ";");
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar BattleResults", e.getMessage());
		} finally {
			fechar();
		}
	}

	private BattleResultData setBattleResult(ResultSet rs) throws SQLException {
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
				BattleResultData temp = setBattleResult(rs);
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar BattleResults", e.getMessage());
			return null;
		}
	}

	public void atualizar(BattleResultData battleResult) {
		conectar();
		long mili = battleResult.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String com = "UPDATE BattleResult SET Battle_id = "
				+ battleResult.getBattleId() + ", User_From = "
				+ battleResult.getCharFromId() + ", User_To = "
				+ battleResult.getCharToId() + ", date ='" + dataSQL.toString()
				+ "', exp_points = " + battleResult.getExp_points()
				+ ", sten = " + battleResult.getSten() + ", intel = "
				+ battleResult.getIntel() + ", agili = "
				+ battleResult.getAgili() + ", state = "
				+ battleResult.getState() + " WHERE  id = "
				+ battleResult.getId() + ";";
		System.out.println("Atualizada!");
		try {
			System.out.println("Sql:" + com);
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
			rs = comando.executeQuery("SELECT * FROM BattleResult WHERE id = "
					+ id + ";");
			while (rs.next()) {
				BattleResultData temp = setBattleResult(rs);
				resultados.add(temp);
				return temp;
			}
			return userBattle;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar BattleResult", e.getMessage());
			return null;
		}
	}

	public BattleResultData buscar(int battleId, int charId) {
		conectar();
		Vector<BattleResultData> resultados = new Vector<BattleResultData>();
		ResultSet rs;
		BattleResultData userBattle = null;
		try {
			rs = comando
					.executeQuery("SELECT * FROM BattleResult WHERE Battle_id = "
							+ battleId + " and Char_From = " + charId + ";");
			while (rs.next()) {
				BattleResultData temp = setBattleResult(rs);
				resultados.add(temp);
				return temp;
			}
			return userBattle;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar BattleResult", e.getMessage());
			return null;
		}
	}

	public void insere(BattleResultData battleResult) {
		conectar();
		long mili = battleResult.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String sql = "";
		String charTo = (String) ((battleResult.getCharToId() == 0) ? "null"
				: battleResult.getCharToId());
		try {
			sql = "INSERT INTO BattleResult VALUES (null" + " , "
					+ battleResult.getBattleId() + " ,"
					+ battleResult.getCharFromId() + " , "
					+ battleResult.getClanId() + " , '" + dataSQL.toString()
					+ "' , " + charTo + " , " + battleResult.getExp_points()
					+ " , " + battleResult.getSten() + " , "
					+ battleResult.getIntel() + " , " + battleResult.getAgili()
					+ " , " + battleResult.getState() + ")";

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
