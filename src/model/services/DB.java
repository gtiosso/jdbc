package model.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.exceptions.DbException;

public class DB {

	// Criando um atributo do tipo Connection com o valor nulo
	private static Connection conn = null;

	// Criando o metodo estatico para abrir uma conexão com o Banco de Dados
	public static Connection getConnection() {
		if (conn == null) {
			try {
				// Carregando as propriedades do arquivo db.properties atraves do metodo criado
				// loadProperties()
				Properties props = loadProperties();
				// Armazenando a url de conexão com o banco de dados
				String url = props.getProperty("dburl");

				// Criando a conexão com o banco e armazenando no atributo conn
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	// Criando o metodo estatico para fechar uma conexão com o Banco de Dados
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// Criando um metodo estatico para leitura das propriedades de conexão no
	// arquivo "db.properties"
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	// Criando o metodo estatico para fechar um ResutSet
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	// Criando o metodo estatico para fechar um Statement
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
