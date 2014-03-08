package swairlines.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Voo {
	
	private String origem;
	private String destino;
	private String status;
	private int quantidadeDePassageiros;
	private String rota;
	private String horaPartida;
	private String horaChegada;
	private String dataPartida;
	private String dataChegada;
	private int id;
	private String tipoVoo;
	
	public Voo() {
		
	}
	

	public Voo(String origem, String destino, String rota, String horaPartida,
			String horaChegada, String dataPartida, String dataChegada, String tipoVoo) {	
			
		this.origem = origem;
		this.destino = destino;
		this.rota = rota;
		this.horaPartida = horaPartida;
		this.horaChegada = horaChegada;
		this.tipoVoo = tipoVoo;
		this.dataPartida = dataPartida;
		this.dataChegada = dataChegada;
		
	}
	

	public String getDataChegada() {
		return dataChegada;
	}


	public void setDataChegada(String dataChegada) {
		this.dataChegada = dataChegada;
	}


	public String getDataPartida() {
		return dataPartida;
	}


	public void setDataPartida(String dataPartida) {
		this.dataPartida = dataPartida;
	}


	public String getTipoVoo() {
		return tipoVoo;
	}


	public void setTipoVoo(String tipoVoo) {
		this.tipoVoo = tipoVoo;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getQuantidadeDePassageiros() {
		return quantidadeDePassageiros;
	}
	
	public void setQuantidadeDePassageiros(int quantidadeDePassageiros) {
		this.quantidadeDePassageiros = quantidadeDePassageiros;
	}
	
	public String getRota() {
		return rota;
	}
	
	public void setRota(String rota) {
		this.rota = rota;
	}
	
	public String getHoraPartida() {
		return horaPartida;
	}
	
	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}
	
	public String getHoraChegada() {
		return horaChegada;
	}
	
	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
	}
	
	public Date retornaHoraDataPartida() throws ParseException {
		String dataHoraPartida = dataPartida + " " + horaPartida;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Date dataHora = df.parse(dataHoraPartida);
		return dataHora;
	}
	
	public Date retornaHoraDataChegada() throws ParseException {
		String dataHoraChegada = dataChegada + " " + horaChegada;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Date dataHora = df.parse(dataHoraChegada);
		return dataHora;
	}


	@Override
	public String toString() {
		return "Voo [origem=" + origem + ", destino=" + destino + ", status="
				+ status + ", quantidadeDePassageiros="
				+ quantidadeDePassageiros + ", rota=" + rota + ", horaPartida="
				+ horaPartida + ", horaChegada=" + horaChegada + ", id=" + id
				+ ", tipoVoo=" + tipoVoo + "]";
	}
	
	
	

}