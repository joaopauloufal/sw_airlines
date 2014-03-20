package swairlines.model;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name Operador
 */

public class Operador extends Funcionario {
	
	public static final String TIPO_CONTA = "Operador";
	
	public Operador(String nome, String sexo, String cpf, String rg,
			String dataDeNascimento, String telefoneCelular, String cargo,
			String telefoneResidencial, String nacionalidade, String estadoCivil, Endereco endereco) {
		super(nome, sexo, cpf, rg, dataDeNascimento, telefoneCelular, cargo, telefoneResidencial, nacionalidade, estadoCivil, endereco);
		this.getConta().setTipoConta(Operador.TIPO_CONTA);

		
	}
	
	public Operador() {
		
	}
	

}
