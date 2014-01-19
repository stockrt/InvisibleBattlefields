package br.pucrio.inf.lac.invisiblebattler.dao.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {

	public static final int MYSQL = 0;
	private static final String MySQLDriver = "com.mysql.jdbc.Driver";

	public static Connection conexao(String url, String nome, String senha,
			int banco) throws ClassNotFoundException, SQLException {
		switch (banco) {
		case MYSQL:
			Class.forName(MySQLDriver);
			break;
		}
		return DriverManager.getConnection(url, nome, senha);
	}

}
