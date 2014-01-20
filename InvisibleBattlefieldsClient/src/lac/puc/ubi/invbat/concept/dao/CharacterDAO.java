package lac.puc.ubi.invbat.concept.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import lac.puc.ubi.invbat.concept.model.CharacterData;

public class CharacterDAO extends BaseDAO {

	public void apagar(Integer id) {
		conectar();
		try {
			comando.executeUpdate("DELETE FROM `Character` WHERE id = " + id
					+ ";");
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Characters", e.getMessage());
		} finally {
			fechar();
		}
	}

	private CharacterData setChar(ResultSet rs) throws SQLException {
		CharacterData temp = new CharacterData();
		temp.setId(rs.getInt("id"));
		temp.setClanId(rs.getInt("Clan_id"));
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
		return temp;
	}

	public Vector<CharacterData> buscarTodos() {
		conectar();
		Vector<CharacterData> resultados = new Vector<CharacterData>();
		ResultSet rs;
		try {
			rs = comando.executeQuery("SELECT * FROM `Character`");
			while (rs.next()) {
				CharacterData temp = setChar(rs);
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar CharacterDatas", e.getMessage());
			return null;
		}
	}

	public void atualizar(CharacterData user) {
		conectar();
		String com = "UPDATE `Character` SET Clan_ID = '"
				+ user.getClan().getId() + "', email ='" + user.getEmail()
				+ "', password = '" + user.getPassword() + "', name = '"
				+ user.getName() + "', password = '" + user.getPassword()
				+ ", num_victories = " + user.getNum_victories()
				+ ", exp_points = " + user.getExp_points() + ", level = "
				+ user.getLevel() + ", base_stren = " + user.getBase_stren()
				+ ", base_intel = " + user.getBase_intel() + ", base_agili = "
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

	public CharacterData buscar(int id) {
		conectar();
		Vector<CharacterData> resultados = new Vector<CharacterData>();
		ResultSet rs;
		CharacterData temp = null;
		try {
			rs = comando.executeQuery("SELECT * FROM `Character` WHERE id =" + id
					+ ";");
			while (rs.next()) {
				temp = setChar(rs);
				resultados.add(temp);
			}
			return temp;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Character", e.getMessage());
			return null;
		}

	}

	public CharacterData buscar(String _email, String _pass) {
		conectar();
		Vector<CharacterData> resultados = new Vector<CharacterData>();
		ResultSet rs;
		CharacterData temp = null;
		String sql = "";
		try {
			sql = "SELECT * FROM `Character` WHERE email = '" + _email
					+ "' and password = '" + _pass + "';";
			rs = comando.executeQuery(sql);
			while (rs.next()) {
				temp = setChar(rs);
				resultados.add(temp);
			}
			return temp;
		} catch (SQLException e) {
			System.out.println("Sql: "+sql);
			imprimeErro("Erro ao buscar Character", e.getMessage());
			return null;
		}

	}

	public void insere(CharacterData user) {
		conectar();
		String sql = "";
		try {
			sql = "INSERT INTO `Character` (`id` ,`Clan_id` ,`email` ,`password` "
					+ ",`name` ,`num_victories` ,`exp_points` ,`level` "
					+ ",`base_stren` ,`base_intel` ,`base_agili` ,`mod_stren` "
					+ ",`mod_intel` ,`mod_agili`)" + "VALUES (NULL ,"
					+ ((user.getClan() == null) ? "NULL" : user.getClan()
							.getId())
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
					+ user.getMod_agili()
					+ ")";
			comando.executeUpdate(sql);
			System.out.println("Inserida!");
		} catch (SQLException e) {
			System.out.println("sql: " + sql);
			imprimeErro("Erro ao inserir Character", e.getMessage());
		} finally {
			fechar();
		}
	}
}
