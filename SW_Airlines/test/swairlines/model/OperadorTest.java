package swairlines.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OperadorTest {
	
	private Operador operador;
	private Endereco endereco;
	
	@Before
	public void setUp() throws Exception {
		endereco = new Endereco("Rua Adolfo Bispo", "Arapiraca", "Eldorado", "318", "Alagoas");
		operador = new Operador("Alice", "Feminino", "333.333.333-33", "111111111", "10/07/1985", "5656565656", "Operador", "65656565", "Brasileira", "Casado(a)", endereco);

	}

	@Test
	public void testOperador() {
		assertEquals("Alice", operador.getNome());
		assertEquals("Feminino", operador.getSexo());
		assertEquals("333.333.333-33", operador.getCpf());
		assertEquals("111111111", operador.getRg());
		assertEquals("Operador", operador.getCargo());
		assertEquals("10/07/1985", operador.getDataDeNascimento());
		assertEquals("Brasileira", operador.getNacionalidade());
		assertEquals("Casado(a)", operador.getEstadoCivil());
		assertEquals("Arapiraca", operador.getEndereco().getCidade());
		assertEquals("Operador", operador.getConta().getTipoConta());
		
	}
	
	@Test
	public void testEditarOperador() {
		assertEquals("Alice", operador.getNome());
		assertEquals("Feminino", operador.getSexo());
		assertEquals("333.333.333-33", operador.getCpf());
		assertEquals("111111111", operador.getRg());
		assertEquals("Operador", operador.getCargo());
		assertEquals("10/07/1985", operador.getDataDeNascimento());
		assertEquals("Brasileira", operador.getNacionalidade());
		assertEquals("Casado(a)", operador.getEstadoCivil());
		assertEquals("Arapiraca", operador.getEndereco().getCidade());
		assertEquals("Operador", operador.getConta().getTipoConta());
		
		operador.setNome("Vanessa");
		operador.setEstadoCivil("Solteiro(a)");
		
		assertEquals("Vanessa", operador.getNome());
		assertEquals("Solteiro(a)", operador.getEstadoCivil());
		
		
	}
	
	@Test
	public void testOperadorStr() {
		assertEquals("Funcionario [nome=Alice, sexo=Feminino, cpf=333.333.333-33, rg=111111111, dataDeNascimento=10/07/1985, telefoneCelular=5656565656, telefoneResidencial=65656565, nacionalidade=Brasileira, estadoCivil=Casado(a), cargo=Operador]", operador.toString());
	}
	
	

}
