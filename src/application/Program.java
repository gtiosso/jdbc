// Retornando dados do banco de dados
// Statement = Interface responsável por realizar todas as operações no banco de dados
// ResultSet = Interface responsável por armazenar todos os valores retornados do banco em forma de tabela (Matriz)

package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.services.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			// Abrindo conexão com o banco de dados
			conn = DB.getConnection();
			// /Criando/Instanciando o Objeto Statement
			st = conn.createStatement();
			
			// Realizando uma query no banco de dados e armazenando no ResultSet
			rs = st.executeQuery("select * from department");
			
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
