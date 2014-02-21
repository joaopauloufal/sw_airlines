package swairlines.bd;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import swairlines.gui.TelaCadFuncionario;
import swairlines.modelo.Endereco;
import swairlines.modelo.Funcionario;

public class FuncionarioBD {

	public boolean insere(Funcionario f1, Endereco e1){
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("insert into sw_airlines.funcionario (cpf, nome, sexo, rg, cargo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, rua, cidade, bairro, numero, estado) " +
					"values('" + f1.getNome() +"','" + f1.getCpf() +"','" + f1.getSexo() +"','" + f1.getRg() +"','" + f1.getCargo() +"','" + f1.getDataDeNascimento() +"','" + f1.getEstadoCivil() +"','" + f1.getNacionalidade() +"','" + f1.getTelefoneCelular() +"','" + f1.getTelefoneResidencial() + "','" + e1.getRua() + "','" + e1.getCidade() + "','" + e1.getBairro() + "','" + e1.getNumero() + "','" + e1.getEstado() + "');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	public void altera(Funcionario f1){

		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("update sw_airlines.funcionario set cpf='" + f1.getCpf() +"' , nome='" + f1.getNome() +"', sexo='" + f1.getSexo() +"', rg='" + f1.getRg() +"', cargo='" + f1.getCargo() +"' ,data_de_nascimento='" + f1.getDataDeNascimento() +"', estado_civil='" + f1.getEstadoCivil() +"', nascionalidade='" + f1.getNacionalidade() +"', telefone_celular='" + f1.getTelefoneCelular() +"', telfone_residencial='" + f1.getTelefoneResidencial() +"' where cpf='" + f1.getCpf() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
		}


	}
	public void exclui(Funcionario f1){

		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("delete from sw_airlines.funcionario where cpf = '" + f1.getCpf() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public void busca(){

	}
//	public ArrayList<Funcionario> buscaTodos(){
//		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
//		try {
//			ConexaoBD cbd = new ConexaoBD();
//			//Abre a conexão com o banco de dados
//			Connection con = cbd.abreConexao();
//			//Cria um statement para realizar comandos no BD
//			PreparedStatement stm = con.prepareStatement("SELECT nome, cpf, sexo, rg, cargo, dataNascimento, estadoCivil, Nascionalidade, nCelular, nTelefone FROM Funcionario");
//			//Armazena o valor da pesquisa e no rs
//			ResultSet rs = stm.executeQuery();
//			//Com o while ele vai rodar linha por linha sendo o parâmetro o rs.next(), que retorna V ou F se a tabela tiver ou não linhas.
//			while (rs.next()){
//				Funcionario f = new Funcionario();
//				f.setNome(rs.getString("nome"));
//				f.setCpf(rs.getString("cpf"));
//				f.setSexo(rs.getString("sexo"));
//				f.setRg(rs.getString("rg"));
//				f.setCargo(rs.getString("cargo"));
//				f.setDataDeNascimento(rs.getString("dataNascimento"));
//				f.setEstadoCivil(rs.getString("estadoCivil"));
//				f.setNacionalidade(rs.getString("Nascionalidade"));
//				f.setTelefoneCelular(rs.getInt("nCelular"));
//				f.setTelefoneResidencial(rs.getInt("nTelefone"));
//				//Adiciona o objeto a (funcionario) a lista funcionarios, usando o método add
//				funcionarios.add(f);
//			}
//			rs.close();
//			stm.close();
//			con.close();
//			//O retorno pode ser tanto dentro do try-catch sendo return funcionario e return null ou fora e assim só sendo preciso um return
//			return funcionarios;
//
//		} catch (SQLException ex) {
//			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
//			return null;
//		}
//	}

}
