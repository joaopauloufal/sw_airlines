package swairlines.model;

public class Gerente extends Funcionario {
	
	public static final String TIPO_CONTA = "Administrador";
	
	public Gerente(String nome, String sexo, String cpf, String rg,
			String dataDeNascimento, String telefoneCelular, String cargo,
			String telefoneResidencial, String nacionalidade, String estadoCivil, Endereco endereco) {
		super(nome, sexo, cpf, rg, dataDeNascimento, telefoneCelular, cargo, telefoneResidencial, nacionalidade, estadoCivil, endereco);
		this.getConta().setTipoConta(Gerente.TIPO_CONTA);
	}
	
	public Gerente() {
		
	}
	

}
