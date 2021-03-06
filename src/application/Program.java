// Removendo dados do banco de dados
// PreparedStatement = Interface responsável por montar a consulta sql e passar o parametros depois

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.exceptions.DbIntegrityException;
import model.services.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			
			conn = DB.getConnection();
			
			// Instanciando o PreparedStatement com a operação
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?"
					);
			
			// Substituindo os valores "?" por dados
			// O comando set(String, Int, Double...) serve para alterarmos os valores "?" pelo dado pertinente a cada tipo
			// o Campo "1" é o indice de cada valor "?", ou seja, 1 é o primeiro ?, 2 o segundo, 3 o terceiro.... e assim em diante;
			st.setInt(1, 2);
			
			// Realizando de fato a execução, retorna o numero de linhas afetadas
			int rowsAffect = st.executeUpdate();
			
			System.out.println("Done! - Rows Affected: " + rowsAffect);
			
		} 
		catch (SQLException e) {
			// Lançando a Exceção Personalizada
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
