package swairlines.model;

public class Gerente extends Funcionario {
	
	public static final String TIPO_CONTA = "Administrador";
	
	public Gerente(String nome, String sexo, String cpf, String rg,
			String cargo, String dataDeNascimento, String telefoneCelular,
			String telefoneResidencial, String nacionalidade, String estadoCivil, Endereco endereco) {
		super(nome, sexo, cpf, rg, cargo, dataDeNascimento, telefoneCelular, telefoneResidencial, nacionalidade, estadoCivil, endereco);
		this.getConta().setTipoConta(Gerente.TIPO_CONTA);
	}
	
	public Gerente() {
		
	}
	

}
