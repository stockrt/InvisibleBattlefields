package br.pucrio.inf.lac.invisiblebattler.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import br.pucrio.inf.lac.invisiblebattler.model.Clan;

public class ClanDAO extends BaseDAO {

	public void apagar(Integer id) {
		conectar();
		try {
			comando.executeUpdate("DELETE FROM Clan WHERE id = " + id + ";");
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Clans", e.getMessage());
		} finally {
			fechar();
		}
	}

	public Vector<Clan> buscarTodos() {
		conectar();
		Vector<Clan> resultados = new Vector<Clan>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM Clan");
			while (rs.next()) {
				Clan temp = new Clan();
				// pega todos os atributos da Clan
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Clans", e.getMessage());
			return null;
		}
	}

	public void atualizar(Clan clan) {
		conectar();
		String com = "UPDATE Clan SET name = '" + clan.getName()
				+ "' WHERE  id = '" + clan.getId() + "';";
		System.out.println("Atualizada!");
		try {
			comando.executeUpdate(com);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

	public Clan buscar(Integer id) {
		conectar();
		Vector<Clan> resultados = new Vector<Clan>();
		ResultSet rs;
		Clan temp = null;
		String sql = "";
		try {
			sql = "SELECT * FROM Clan WHERE id = " + id + ";";
			rs = comando.executeQuery(sql);
			while (rs.next()) {
				temp = new Clan();
				// pega todos os atributos da Clan
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				resultados.add(temp);
//				System.out.println(sql);
			}
			return temp;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Clan", e.getMessage());
			return null;
		}

	}

	public void insere(Clan clan) {
		conectar();
		try {
			comando.executeUpdate("INSERT INTO Clan VALUES(null , '"
					+ clan.getName() + "')");
			System.out.println("Inserida!");
		} catch (SQLException e) {
			imprimeErro("Erro ao inserir Clan", e.getMessage());
		} finally {
			fechar();
		}
	}

	public static void main(String[] args) {
		Clan clan = new Clan("ivan");
		ClanDAO dao = new ClanDAO();
		dao.insere(clan);
	}
}
