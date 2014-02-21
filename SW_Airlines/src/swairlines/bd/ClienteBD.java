package swairlines.bd;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import swairlines.gui.TelaCadCliente;
import swairlines.modelo.Cliente;
import swairlines.modelo.Endereco;

public class ClienteBD {

	public boolean insere(Cliente c1, Endereco e1){
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("insert into sw_airlines.cliente (rg, cpf_cnpj, nome, sexo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, cartao_de_credito, rua, cidade, bairro, numero, estado) " +
					"values('" + c1.getRg() +"','" + c1.getCpfCnpj() +"','" + c1.getNome() +"','" + c1.getSexo() +"','" + c1.getDataDeNascimento() +"','" + c1.getEstadoCivil() +"','" + c1.getNacionalidade() +"','" + c1.getTelefoneCelular() +"','" + c1.getTelefoneResidencial() +"','" + c1.getCartaoDeCredito() +"','" + e1.getRua() + "','" + e1.getCidade() + "','" + e1.getBairro() + "','" + e1.getNumero() + "','" + e1.getEstado() + "');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	public void altera(Cliente c1){

		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("update sw_airlines.cliente set cpf_cnpj='" + c1.getCpfCnpj() +"', nome='" + c1.getNome() +"', sexo='" + c1.getSexo() +"', data_de_nascimento='" + c1.getDataDeNascimento() +"', estado_civil='" + c1.getEstadoCivil() +"', nacionalidade='" + c1.getNacionalidade() +"', telefone_celular='" + c1.getTelefoneCelular() +"', telefone_residencial='" + c1.getTelefoneResidencial() +"', cartao_de_credito='" + c1.getCartaoDeCredito() +"' where rg='" + c1.getRg() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
		}


	}
	public void exclui(Cliente c1){

		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("delete from sw_airlines.cliente where rg = '" + c1.getRg()+"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public void busca(){

	}
//	public ArrayList<Cliente> buscaTodos(){
//		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
//		try {
//			ConexaoBD cbd = new ConexaoBD();
//			//Abre a conexão com o banco de dados
//			Connection con = cbd.abreConexao();
//			//Cria um statement para realizar comandos no BD
//			PreparedStatement stm = con.prepareStatement("SELECT nome, cpf_cnpj, sexo, rg, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, cartao_de_credito FROM Cliente");
//			//Armazena o valor da pesquisa e no rs
//			ResultSet rs = stm.executeQuery();
//			//Com o while ele vai rodar linha por linha sendo o parâmetro o rs.next(), que retorna V ou F se a tabela tiver ou não linhas.
//			while (rs.next()){
//				
//				Cliente c = new Cliente();
//				c.setNome(rs.getString("nome"));
//				c.setCpfCnpj(rs.getString("cpf_cnpj"));
//				c.setSexo(rs.getString("sexo"));
//				c.setRg(rs.getString("rg"));
//				c.setDataDeNascimento(rs.getDate("data_de_nascimento"));
//				c.setEstadoCivil(rs.getString("estado_civil"));
//				c.setNacionalidade(rs.getString("nacionalidade"));
//				c.setTelefoneCelular(rs.getInt("telefone_celular"));
//				c.setTelefoneResidencial(rs.getInt("telefone_residencial"));
//				c.setCartaoDeCredito(rs.getInt("cartao_de_credito"));
//				//Adiciona o objeto a (cliente) a lista clientes, usando o método add
//				clientes.add(c);
//				
//			}
//			rs.close();
//			stm.close();
//			con.close();
//			//O retorno pode ser tanto dentro do try-catch sendo return cliente e return null ou fora e assim só sendo preciso um return
//			return clientes;
//
//		} catch (SQLException ex) {
//			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
//			return null;
//		}
//	}

}
