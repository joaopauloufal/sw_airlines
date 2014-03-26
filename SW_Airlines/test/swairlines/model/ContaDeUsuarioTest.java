package swairlines.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContaDeUsuarioTest {
	
	private ContaDeUsuario contaOperador;
	private ContaDeUsuario contaAdmin;
	private Funcionario funcionario1;
	private Funcionario funcionario2;
	
	@Before
	public void setUp() throws Exception {
		funcionario1 = new Operador("Alice", "Feminino", "333.333.333-33", "111111111", "10/07/1985", "5656565656", "Operador", "65656565", "Brasileira", "Casado(a)", null);
		contaOperador = new ContaDeUsuario("alice", "1234", ContaDeUsuario.TIPO_CONTA_OPERADOR, funcionario1.getCpf());
		funcionario1.setConta(contaOperador);
		
		funcionario2 = new Gerente("Pedro", "Masculino", "111.111.111-11", "1233565", "12/04/1985", "56565556", "Gerente", "4545454545", "Brasileira", "Casado(a)", null);
		contaAdmin = new ContaDeUsuario("pedro", "12345", ContaDeUsuario.TIPO_CONTA_ADMIN, funcionario2.getCpf());
		funcionario2.setConta(contaAdmin);

	}

	@Test
	public void testContaDeusuario() {
		assertEquals("alice", contaOperador.getLogin());
		assertEquals("1234", contaOperador.getSenha());
		assertEquals("333.333.333-33", contaOperador.getCpfFuncionario());
		assertEquals(ContaDeUsuario.TIPO_CONTA_OPERADOR, contaOperador.getTipoConta());
		
		assertEquals("pedro", contaAdmin.getLogin());
		assertEquals("12345", contaAdmin.getSenha());
		assertEquals("111.111.111-11", contaAdmin.getCpfFuncionario());
		assertEquals(ContaDeUsuario.TIPO_CONTA_ADMIN, contaAdmin.getTipoConta());
		
		
	}

}
