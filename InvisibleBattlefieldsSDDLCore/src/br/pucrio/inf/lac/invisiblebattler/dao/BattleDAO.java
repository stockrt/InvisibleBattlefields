package br.pucrio.inf.lac.invisiblebattler.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import br.pucrio.inf.lac.invisiblebattler.model.Battle;
import br.pucrio.inf.lac.invisiblebattler.model.Region;

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

	public Vector<Battle> buscarTodos() {
		conectar();
		Vector<Battle> resultados = new Vector<Battle>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM Battle");
			while (rs.next()) {
				Battle temp = new Battle();
				// pega todos os atributos da Battle
				temp.setId(rs.getInt("id"));
				temp.setDate(rs.getDate("date"));
				temp.setTimeFrameID(rs.getInt("timeFrameID"));
				RegionDAO dao = new RegionDAO();
				Region region = dao.buscar(rs.getInt("Region_id"));
				temp.setRegion(region);
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Battles", e.getMessage());
			return null;
		}
	}

	public void atualizar(Battle Battle) {
		conectar();
		long mili = Battle.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String com = "UPDATE Battle SET timeFrameID = '"
				+ Battle.getTimeFrameID() + "', date ='" + dataSQL.toString()
				+ "', Region_id = '" + Battle.getRegion().getId()
				+ "' WHERE  id = '" + Battle.getId() + "';";
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

	public Vector<Battle> buscar(Integer id) {
		conectar();
		Vector<Battle> resultados = new Vector<Battle>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM Battle WHERE id LIKE '"
					+ id + "%';");
			while (rs.next()) {
				Battle temp = new Battle();
				// pega todos os atributos da Battle
				temp.setId(rs.getInt("id"));
				temp.setDate(rs.getDate("nome"));

				// temp.setRegion(new Region((rs.getInt("Region_id")));
				temp.setTimeFrameID(rs.getInt("timeFrameID"));
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Battle", e.getMessage());
			return null;
		}

	}

	public void insere(Battle Battle) {
		conectar();
		long mili = Battle.getDate().getTime();
		java.sql.Date dataSQL = new java.sql.Date(mili);
		String sql = "";
		try {
			sql = "INSERT INTO Battle VALUES(null" + " , "
					+ Battle.getRegion().getId() + " ,'"
					+ Battle.getTimeFrameID() + "','" + dataSQL.toString()
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
