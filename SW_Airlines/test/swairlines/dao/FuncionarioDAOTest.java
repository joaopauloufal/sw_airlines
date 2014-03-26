package swairlines.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import swairlines.model.Endereco;
import swairlines.model.Funcionario;
import swairlines.model.Gerente;
import swairlines.model.Operador;

public class FuncionarioDAOTest {
	
	private Funcionario funcionario1;
	private Funcionario funcionario2;
	private Endereco endereco;
	private FuncionarioDAO funDao;
	
	@Before
	public void setUp() throws Exception {
		endereco = new Endereco("Rua Adolfo Bispo", "Arapiraca", "Eldorado", "318", "Alagoas");
		funcionario1 = new Gerente("Pedro", "Masculino", "111.111.111-11", "1233565", "12/04/1985", "56565556", "Gerente", "4545454545", "Brasileira", "Casado(a)", endereco);
		funcionario2 = new Operador("Alice", "Feminino", "333.333.333-33", "111111111", "10/07/1985", "5656565656", "Operador", "65656565", "Brasileira", "Casado(a)", endereco);
		funDao = new FuncionarioDAO();
		funDao.excluiFuncionario(funcionario1);
		funDao.excluiFuncionario(funcionario2);
		

	}

	@Test
	public void testInsereFuncionario() throws SQLException {
		assertFalse(funDao.buscaFuncionarios().isEmpty());
		funDao.insereFuncionario(funcionario1);
		funDao.insereFuncionario(funcionario2);
		assertEquals(3, funDao.buscaFuncionarios().size());
		assertEquals(false, funDao.insereFuncionario(funcionario1));
		assertEquals(false, funDao.insereFuncionario(funcionario2));
	}
	
	@Test
	public void testExcluiFuncionario() throws SQLException {
		assertFalse(funDao.buscaFuncionarios().isEmpty());
		assertEquals(true, funDao.insereFuncionario(funcionario1));
		assertEquals(true, funDao.insereFuncionario(funcionario2));
		assertEquals(true, funDao.excluiFuncionario(funcionario1));
		assertEquals(2, funDao.buscaFuncionarios().size());
		assertEquals(true, funDao.excluiFuncionario(funcionario2));
		assertEquals(1, funDao.buscaFuncionarios().size());		
		
	}
	
	@Test
	public void testBuscaFuncionarios() throws SQLException {
		assertNotNull(funDao.buscaFuncionarios());
		assertEquals(true, funDao.insereFuncionario(funcionario1));
		assertEquals(true, funDao.insereFuncionario(funcionario2));
		assertEquals(3, funDao.buscaFuncionarios().size());	
		
	}

}
