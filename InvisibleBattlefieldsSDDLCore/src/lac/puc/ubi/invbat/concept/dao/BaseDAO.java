package lac.puc.ubi.invbat.concept.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import lac.puc.ubi.invbat.concept.dao.banco.ConFactory;


public class BaseDAO {
	protected final String URL = "jdbc:mysql://mysql.ivan.inf.br/ivan07",
			NOME = "ivan07", SENHA = "lacpuc2012";
	protected Connection con;
	protected Statement comando;

	public void conectar() {
		try {
			con = ConFactory.conexao(URL, NOME, SENHA, ConFactory.MYSQL);
			comando = con.createStatement();
			System.out.println("Conectado!");
		} catch (ClassNotFoundException e) {
			System.out.println(URL);
			imprimeErro("Erro ao carregar o driver", e.getMessage());
		} catch (SQLException e) {
			imprimeErro("Erro ao conectar", e.getMessage());
		}
	}

	public void fechar() {
		try {
			comando.close();
			con.close();
			System.out.println("Conexão Fechada");
		} catch (SQLException e) {
			imprimeErro("Erro ao fechar conexão", e.getMessage());
		}
	}

	protected void imprimeErro(String msg, String msgErro) {
		JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);
		System.err.println(msg);
		System.out.println(msgErro);
		System.exit(0);
	}

	public static void main(String[] args) {
	}
}
