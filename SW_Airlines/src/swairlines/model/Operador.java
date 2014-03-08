package swairlines.model;

public class Operador extends Funcionario {
	
	public static final String TIPO_CONTA = "Operador";
	
	public Operador(String nome, String sexo, String cpf, String rg,
			String cargo, String dataDeNascimento, String telefoneCelular,
			String telefoneResidencial, String nacionalidade, String estadoCivil, Endereco endereco) {
		super(nome, sexo, cpf, rg, cargo, dataDeNascimento, telefoneCelular, telefoneResidencial, nacionalidade, estadoCivil, endereco);
		this.getConta().setTipoConta(Operador.TIPO_CONTA);

		
	}
	
	public Operador() {
		
	}
	

}