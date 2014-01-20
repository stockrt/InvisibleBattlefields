package lac.puc.ubi.invbat.concept.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import lac.puc.ubi.invbat.concept.model.BattleData;

public class BattleDAO extends BaseDAO {

	public void apagar(Integer id) {
		conectar();
		try {
			comando.executeUpdate("DELETE FROM Battle WHERE id = " + id + ";");
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Battles", e.getMessage());
		} finally {
			fechar();
		}
	}

	private BattleData setBattle(ResultSet rs) throws SQLException {
		BattleData temp = new BattleData();
		temp.setId(rs.getInt("id"));
		temp.setDate(rs.getDate("date"));
		temp.setTimeFrameId(rs.getInt("timeFrameId"));
		temp.setRegionId(rs.getInt("Region_id"));
		return temp;
	}

	public Vector<BattleData> buscarTodos() {
		conectar();
		Vector<BattleData> resultados = new Vector<BattleData>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM Battle");
			while (rs.next()) {
				BattleData temp = setBattle(rs);
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Battles", e.getMessage());
			return null;
		}
	}

	public void atualizar(BattleData battle) {
		conectar();
		long mili = battle.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String com = "UPDATE Battle SET timeFrameId = '"
				+ battle.getTimeFrameId() + "', date ='" + dataSQL.toString()
				+ "', Region_id = '" + battle.getRegionId()
				+ "' WHERE  id = '" + battle.getId() + "';";
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

	public BattleData buscar(Integer id) {
		conectar();
		Vector<BattleData> resultados = new Vector<BattleData>();
		ResultSet rs;
		BattleData battle = null;
		try {
			rs = comando.executeQuery("SELECT * FROM Battle WHERE id = " + id
					+ ";");
			while (rs.next()) {
				BattleData temp = setBattle(rs);
				resultados.add(temp);
				return temp;
			}
			return battle;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Battle", e.getMessage());
			return null;
		}

	}

	public void insere(BattleData battle) {
		conectar();
		long mili = battle.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String sql = "";
		try {
			sql = "INSERT INTO Battle VALUES(null" + " , "
					+ battle.getRegionId() + " ,'"
					+ battle.getTimeFrameId() + "','" + dataSQL.toString()
					+ "')";
			comando.executeUpdate(sql);
			System.out.println("Inserida!");
		} catch (SQLException e) {
			System.out.println("sql: " + sql);
			imprimeErro("Erro ao inserir Battle", e.getMessage());
		} finally {
			fechar();
		}
	}
}
