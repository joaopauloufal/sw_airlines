package swairlines.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Before;
import org.junit.Test;

public class VooTest {
	
	private Voo voo;
	
	@Before
	public void setUp() throws Exception {
		voo = new Voo("1234", "Arapiraca", "São Paulo", "Rio de Janeiro", "08:00", "12:00", "23/12/2014", "23/12/2014", "Nacional", 120);

	}

	@Test
	public void testVoo() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy HH:mm");
		assertEquals("1234", voo.getAeronaveNumero());
		assertEquals("Arapiraca", voo.getOrigem());
		assertEquals("São Paulo", voo.getDestino());
		assertEquals("Rio de Janeiro", voo.getRota());
		assertEquals("08:00", voo.getHoraPartida());
		assertEquals("12:00", voo.getHoraChegada());
		assertEquals("23/12/2014", voo.getDataPartida());
		assertEquals("23/12/2014", voo.getDataChegada());
		assertEquals("Nacional", voo.getTipoVoo());
		assertEquals(120.0f, voo.getValor(), 0.0f);
		assertEquals(sd.parse("23/12/2014 08:00"), voo.retornaHoraDataPartida());
		assertEquals(sd.parse("23/12/2014 12:00"), voo.retornaHoraDataChegada());
		assertEquals(0, voo.getQuantidadeDePassageiros());
		assertNull(voo.getStatus());
		
	}
	
	@Test
	public void testEditarVoo() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy HH:mm");
		assertEquals("1234", voo.getAeronaveNumero());
		assertEquals("Arapiraca", voo.getOrigem());
		assertEquals("São Paulo", voo.getDestino());
		assertEquals("Rio de Janeiro", voo.getRota());
		assertEquals("08:00", voo.getHoraPartida());
		assertEquals("12:00", voo.getHoraChegada());
		assertEquals("23/12/2014", voo.getDataPartida());
		assertEquals("23/12/2014", voo.getDataChegada());
		assertEquals("Nacional", voo.getTipoVoo());
		assertEquals(120.0f, voo.getValor(), 0.0f);
		assertEquals(0, voo.getQuantidadeDePassageiros());
		assertNull(voo.getStatus());
		
		
		voo.setOrigem("Maceió");
		voo.setDestino("Amazonas");
		voo.setRota("Minas Gerais");
		voo.setHoraChegada("13:00");
		voo.setValor(140.25f);
		
		assertEquals("Maceió", voo.getOrigem());
		assertEquals("Amazonas", voo.getDestino());
		assertEquals("Minas Gerais", voo.getRota());
		assertEquals("13:00", voo.getHoraChegada());
		assertEquals(sd.parse("23/12/2014 13:00"), voo.retornaHoraDataChegada());
		assertEquals(140.25f, voo.getValor(), 0.0f);
		
	}
	
	@Test
	public void testVooStr() {
		assertEquals("Voo [origem=Arapiraca, destino=São Paulo, status=null, quantidadeDePassageiros=0, rota=Rio de Janeiro, horaPartida=08:00, horaChegada=12:00, id=0, tipoVoo=Nacional]", voo.toString());
	}

}
