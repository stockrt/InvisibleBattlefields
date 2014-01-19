package br.pucrio.inf.lac.invisiblebattler.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import br.pucrio.inf.lac.invisiblebattler.model.Clan;
import br.pucrio.inf.lac.invisiblebattler.model.User;

public class UserDAO extends BaseDAO {

	public void apagar(Integer id) {
		conectar();
		try {
			comando.executeUpdate("DELETE FROM User WHERE id = " + id + ";");
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Users", e.getMessage());
		} finally {
			fechar();
		}
	}

	public Vector<User> buscarTodos() {
		conectar();
		Vector<User> resultados = new Vector<User>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM User");
			while (rs.next()) {
				User temp = new User();
				// pega todos os atributos da User
				temp.setId(rs.getInt("id"));
				temp.setEmail(rs.getString("email"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setNum_victories(rs.getInt("num_victories"));
				temp.setExp_points(rs.getInt("exp_points"));
				temp.setLevel(rs.getInt("level"));
				temp.setBase_agili(rs.getInt("base_agili"));
				temp.setBase_intel(rs.getInt("base_intel"));
				temp.setBase_stren(rs.getInt("base_stren"));
				temp.setMod_agili(rs.getInt("mod_agili"));
				temp.setMod_intel(rs.getInt("mod_intel"));
				temp.setMod_stren(rs.getInt("mod_stren"));
				ClanDAO dao = new ClanDAO();
				Clan clan = dao.buscar(rs.getInt("Clan_id"));
				temp.setClan(clan);
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Users", e.getMessage());
			return null;
		}
	}

	public void atualizar(User user) {
		conectar();
		String com = "UPDATE User SET Clan_ID = '" + user.getClan().getId()
				+ "', email ='" + user.getEmail() + "', password = '"
				+ user.getPassword() + "', name = '" + user.getName()
				+ "', password = '" + user.getPassword() + ", num_victories = "
				+ user.getNum_victories() + ", exp_points = "
				+ user.getExp_points() + ", level = " + user.getLevel()
				+ ", base_stren = " + user.getBase_stren() + ", base_intel = "
				+ user.getBase_intel() + ", base_agili = "
				+ user.getBase_agili() + ", mod_stren = " + user.getMod_stren()
				+ ", mod_intel = " + user.getMod_intel() + ", mod_agili = "
				+ user.getMod_agili() + " WHERE  id = '" + user.getId() + "';";
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

	public User buscar(Integer id) {
		conectar();
		Vector<User> resultados = new Vector<User>();
		ResultSet rs;
		User temp = null;
		try {
			rs = comando.executeQuery("SELECT * FROM User WHERE id LIKE '" + id
					+ "%';");
			while (rs.next()) {
				temp = new User();
				// pega todos os atributos da User
				temp.setId(rs.getInt("id"));
				ClanDAO dao = new ClanDAO();
				Clan clan = dao.buscar(rs.getInt("Clan_id"));
				temp.setClan(clan);
				temp.setEmail(rs.getString("email"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setNum_victories(rs.getInt("num_victories"));
				temp.setExp_points(rs.getInt("exp_points"));
				temp.setLevel(rs.getInt("level"));
				temp.setBase_agili(rs.getInt("base_agili"));
				temp.setBase_intel(rs.getInt("base_intel"));
				temp.setBase_stren(rs.getInt("base_stren"));
				temp.setMod_agili(rs.getInt("mod_agili"));
				temp.setMod_intel(rs.getInt("mod_intel"));
				temp.setMod_stren(rs.getInt("mod_stren"));
				resultados.add(temp);
			}
			return temp;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar User", e.getMessage());
			return null;
		}

	}

	public void insere(User user) {
		conectar();
		String sql = "";
		try {
			sql = "INSERT INTO  User (`id` ,`Clan_id` ,`email` ,`password` "
					+ ",`name` ,`num_victories` ,`exp_points` ,`level` "
					+ ",`base_stren` ,`base_intel` ,`base_agili` ,`mod_stren` "
					+ ",`mod_intel` ,`mod_agili`)" + "VALUES (NULL ,"
					+ user.getClan().getId()
					+ ",  '"
					+ user.getEmail()
					+ "',  '"
					+ user.getPassword()
					+ "',  '"
					+ user.getName()
					+ "',  "
					+ user.getNum_victories()
					+ ",  "
					+ user.getExp_points()
					+ ",  "
					+ user.getLevel()
					+ ",  "
					+ user.getBase_stren()
					+ ",  "
					+ user.getBase_intel()
					+ ",  "
					+ user.getBase_agili()
					+ ",  "
					+ user.getMod_stren()
					+ ",  "
					+ user.getMod_intel()
					+ ",  "
					+ user.getMod_agili() + ")";
			comando.executeUpdate(sql);
			System.out.println("Inserida!");
		} catch (SQLException e) {
			System.out.println("sql: " + sql);
			imprimeErro("Erro ao inserir User", e.getMessage());
		} finally {
			fechar();
		}
	}
}
