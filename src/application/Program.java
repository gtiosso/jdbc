// Inserindo dados no banco de dados
// PreparedStatement = Interface responsável por montar a consulta sql e passar o parametros depois
// Statement.RETURN_GENERATED_KEYS = Permite recuperar o ID dos dados inseridos no banco de dados

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.services.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			// Instanciando o PreparedStatement com a operação
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?,?,?,?,?)", // Criando um placeholder, local onde depois eu irei inserir as informações
					Statement.RETURN_GENERATED_KEYS // Metodo para retornar o id do dado criado
					);
			
			// Substituindo os valores "?" por dados
			// O comando set(String, Int, Double...) serve para alterarmos os valores "?" pelo dado pertinente a cada tipo
			// o Campo "1" é o indice de cada valor "?", ou seja, 1 é o primeiro ?, 2 o segundo, 3 o terceiro.... e assim em diante;
			st.setString(1, "Guilherme Tiosso");
			st.setString(2, "guilherme@gmail.com");
			// Para inserir dados do tipo DATE, precisamos instanciar a Classe "java.sql.date" e atribuir o valor em milisegundos
			st.setDate(3, new java.sql.Date(sdf.parse("22/06/1992").getTime()));
			st.setDouble(4, 7000.0);
			st.setInt(5, 1);
			
			// Realizando de fato a inserção dos dados, retorna o numero de linhas afetadas
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys(); // getGeneratedKeys retorna um valor do tipo ResultSet
				while (rs.next()) {
					int id = rs.getInt(1); // Atribuou os dados da coluna 1 para o atributo ID
					System.out.println("Done! - ID = " + id);
					
				}
			}
			else {
				System.out.println("No rows affected");
			}
		}
		catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
