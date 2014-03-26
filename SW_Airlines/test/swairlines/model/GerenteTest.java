package swairlines.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GerenteTest {
	
	private Gerente gerente;
	private Endereco endereco;
	
	@Before
	public void setUp() throws Exception {
		endereco = new Endereco("Rua Adolfo Bispo", "Arapiraca", "Eldorado", "318", "Alagoas");
		gerente = new Gerente("Pedro", "Masculino", "111.111.111-11", "1233565", "12/04/1985", "56565556", "Gerente", "4545454545", "Brasileira", "Casado(a)", endereco);
	}

	@Test
	public void testGerente() {
		assertEquals("Pedro", gerente.getNome());
		assertEquals("Masculino", gerente.getSexo());
		assertEquals("111.111.111-11", gerente.getCpf());
		assertEquals("1233565", gerente.getRg());
		assertEquals("Gerente", gerente.getCargo());
		assertEquals("12/04/1985", gerente.getDataDeNascimento());
		assertEquals("Brasileira", gerente.getNacionalidade());
		assertEquals("Casado(a)", gerente.getEstadoCivil());
		assertEquals("Arapiraca", gerente.getEndereco().getCidade());
		assertEquals("Administrador", gerente.getConta().getTipoConta());
		
	}
	
	@Test
	public void testEditarGerente() {
		assertEquals("Pedro", gerente.getNome());
		assertEquals("Masculino", gerente.getSexo());
		assertEquals("111.111.111-11", gerente.getCpf());
		assertEquals("1233565", gerente.getRg());
		assertEquals("Gerente", gerente.getCargo());
		assertEquals("12/04/1985", gerente.getDataDeNascimento());
		assertEquals("Brasileira", gerente.getNacionalidade());
		assertEquals("Casado(a)", gerente.getEstadoCivil());
		assertEquals("Arapiraca", gerente.getEndereco().getCidade());
		assertEquals("Administrador", gerente.getConta().getTipoConta());
		
		gerente.setCpf("222.222.222-22");
		gerente.setEstadoCivil("Solteiro(a)");
		gerente.setRg("55556666");
		
		assertEquals("Masculino", gerente.getSexo());
		assertEquals("222.222.222-22", gerente.getCpf());
		assertEquals("55556666", gerente.getRg());
		assertEquals("Solteiro(a)", gerente.getEstadoCivil());		
		
	}
	
	@Test
	public void testGerenteStr() {
		assertEquals("Funcionario [nome=Pedro, sexo=Masculino, cpf=111.111.111-11, rg=1233565, dataDeNascimento=12/04/1985, telefoneCelular=56565556, telefoneResidencial=4545454545, nacionalidade=Brasileira, estadoCivil=Casado(a), cargo=Gerente]", gerente.toString());
	}
	
	

}
