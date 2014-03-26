package swairlines.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import swairlines.model.ContaDeUsuario;

public class ContaDeUsuarioDAOTest {
	
	private ContaDeUsuario conta1;
	private ContaDeUsuario conta2;
	private ContaDeUsuario conta3;
	private ContaDeUsuarioDAO conDao;
	
	@Before
	public void setUp() throws Exception {
		conta1 = new ContaDeUsuario("alice", "1234", ContaDeUsuario.TIPO_CONTA_OPERADOR, "333.333.333-33");
		conta2 = new ContaDeUsuario("pedro", "12345", ContaDeUsuario.TIPO_CONTA_ADMIN, "111.111.111-11");
		conta3 = new ContaDeUsuario("paulo", "1234567", ContaDeUsuario.TIPO_CONTA_OPERADOR, "444.444.444-44");
		conDao = new ContaDeUsuarioDAO();
		conDao.excluiContaDeUsuario(conta1);
		conDao.excluiContaDeUsuario(conta2);
	}

	@Test
	public void autenticarTest() throws SQLException {
		assertFalse(conta1.autenticar(conta1));
		assertFalse(conta2.autenticar(conta2));
		assertEquals(true, conDao.insereContaDeUsuario(conta1));
		assertTrue(conta1.autenticar(conta1));
		conta1.setSenha("55555");
		assertFalse(conta1.autenticar(conta1));
		assertEquals(true, conDao.insereContaDeUsuario(conta2));
		conta2.autenticar(conta2);
		assertEquals(false, conDao.insereContaDeUsuario(conta3));
		
	}

}
