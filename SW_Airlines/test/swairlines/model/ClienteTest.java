package swairlines.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
	
	private Cliente cliente;
	private Endereco endereco;
	
	@Before
	public void setUp() throws Exception {
		endereco = new Endereco("Rua Adolfo Bispo", "Arapiraca", "Eldorado", "318", "Alagoas");
		cliente = new Cliente("João", "000.000.000-00", "Masculino", "0000000-0", "12/07/1991", "Solteiro(a)", "Brasileira", "96127508", "35301882", "56565655", "56565655", endereco);

	}

	@Test
	public void testCliente() {
		assertEquals("João", cliente.getNome());
		assertEquals("000.000.000-00", cliente.getCpfCnpj());
		assertEquals("Masculino", cliente.getSexo());
		assertEquals("Brasileira", cliente.getNacionalidade());
		assertNotNull(endereco.toString(), cliente.getEndereco());
		
		
	}
	
	@Test
	public void testEditarCliente() {
		assertEquals("João", cliente.getNome());
		assertEquals("000.000.000-00", cliente.getCpfCnpj());
		assertEquals("Masculino", cliente.getSexo());
		assertEquals("Brasileira", cliente.getNacionalidade());
		assertNotNull(endereco.toString(), cliente.getEndereco());
		
		cliente.setNome("Danilo");
		cliente.setCpfCnpj("111.111.111-11");
		Endereco endereco = new Endereco("tal", "Arapiraca", "Ouro Preto", "123", "Alagoas");
		cliente.setEndereco(endereco);
		
		assertEquals("Danilo", cliente.getNome());
		assertEquals("111.111.111-11", cliente.getCpfCnpj());	
		
	}
	
	@Test
	public void testClienteStr() {
		assertEquals("Cliente [nome=João, cpfCnpj=000.000.000-00, sexo=Masculino, rg=0000000-0, dataDeNascimento=12/07/1991, estadoCivil=Solteiro, nacionalidade=Brasileira, telefoneCelular=96127508, telefoneResidencial=35301882, cartaoDeCredito=56565655, passaporteNumero=56565655, endereco=Rua: Rua Adolfo Bispo, Cidade: Arapiraca, Bairro: Eldorado, Nº: 318, Estado: Alagoas.]", cliente.toString());
	}

}
