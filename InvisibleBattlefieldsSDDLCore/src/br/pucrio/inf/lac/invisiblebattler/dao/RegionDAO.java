package br.pucrio.inf.lac.invisiblebattler.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import br.pucrio.inf.lac.invisiblebattler.model.Region;

public class RegionDAO extends BaseDAO {

	public void apagar(Integer id) {
		conectar();
		try {
			comando.executeUpdate("DELETE FROM Region WHERE id = " + id + ";");
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Regions", e.getMessage());
		} finally {
			fechar();
		}
	}

	public Vector<Region> buscarTodos() {
		conectar();
		Vector<Region> resultados = new Vector<Region>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM Region");
			while (rs.next()) {
				Region temp = new Region();
				// pega todos os atributos da Region
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				temp.setPoints(rs.getString("points"));
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Regions", e.getMessage());
			return null;
		}
	}

	public void atualizar(Region region) {
		conectar();
		String com = "UPDATE Region SET name = '" + region.getName()
				+ "', points ='" + region.getStrPoints()
				+ "' WHERE  id = '" + region.getId() + "';";
		System.out.println("Atualizada!");
		try {
			comando.executeUpdate(com);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

	public Region buscar(Integer id) {
		conectar();
		Vector<Region> resultados = new Vector<Region>();
		ResultSet rs;
		String sql = "";
		Region temp = null;
		try {
			sql = "SELECT * FROM Region WHERE id = " + id + ";";
			rs = comando.executeQuery(sql);
			while (rs.next()) {
				temp = new Region();
				// pega todos os atributos da Region
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				temp.setPoints(rs.getString("points"));
				resultados.add(temp);
			}
			return temp;
		} catch (SQLException e) {
			System.out.println(sql);
			imprimeErro("Erro ao buscar Region", e.getMessage());
			return null;
		}

	}

	public void insere(Region region) {
		conectar();
		String sql = "";
		try {
			sql = "INSERT INTO  Region (`id` ,`name` ,`points`)"
					+ "VALUES (NULL,'" + region.getName() + "','"
					+ region.getStrPoints() + "')";
			comando.executeUpdate(sql);
			System.out.println("Region Inserida!" + sql);
		} catch (SQLException e) {
			System.out.println(sql);
			imprimeErro("Erro ao inserir Region", e.getMessage());
		} finally {
			fechar();
		}
	}

}
