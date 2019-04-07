// Implementando Exception Customizada para tratar exceções de Integridade Relacional no Banco de Dados

package model.exceptions;

public class DbIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DbIntegrityException(String msg) {
		super(msg);
	}

}
