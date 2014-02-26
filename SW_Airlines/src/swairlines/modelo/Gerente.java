package swairlines.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.bd.AcessoBancoGerente;
import swairlines.bd.ConexaoBD;
import swairlines.gui.TelaCadCliente;
import swairlines.gui.TelaCadConta;
import swairlines.gui.TelaCadFuncionario;
import swairlines.gui.TelaCadVoo;
import swairlines.gui.TelaEditVoo;
import swairlines.gui.TelaTabelaVoos;

public class Gerente extends Funcionario implements AcessoBancoGerente {
	
	public Gerente(String nome, String sexo, String cpf, String rg,
			String cargo, String dataDeNascimento, String telefoneCelular,
			String telefoneResidencial, String nacionalidade, String estadoCivil) {
		super(nome, sexo, cpf, rg, cargo, dataDeNascimento, telefoneCelular, telefoneResidencial, nacionalidade, estadoCivil);
	}

	@Override
	public boolean insereCliente(Cliente cliente, Endereco e1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("insert into sw_airlines.cliente (rg, cpf_cnpj, nome, sexo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, cartao_de_credito, rua, cidade, bairro, numero, estado) " +
					"values('" + cliente.getRg() +"','" + cliente.getCpfCnpj() +"','" + cliente.getNome() +"','" + cliente.getSexo() +"','" + cliente.getDataDeNascimento() +"','" + cliente.getEstadoCivil() +"','" + cliente.getNacionalidade() +"','" + cliente.getTelefoneCelular() +"','" + cliente.getTelefoneResidencial() +"','" + cliente.getCartaoDeCredito() +"','" + e1.getRua() + "','" + e1.getCidade() + "','" + e1.getBairro() + "','" + e1.getNumero() + "','" + e1.getEstado() + "');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public boolean excluiCliente(Cliente cliente) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("delete from sw_airlines.cliente where rg = '" + cliente.getRg()+"';");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public void alteraCliente(Cliente cliente) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("update sw_airlines.cliente set cpf_cnpj='" + cliente.getCpfCnpj() +"', nome='" + cliente.getNome() +"', sexo='" + cliente.getSexo() +"', data_de_nascimento='" + cliente.getDataDeNascimento() +"', estado_civil='" + cliente.getEstadoCivil() +"', nacionalidade='" + cliente.getNacionalidade() +"', telefone_celular='" + cliente.getTelefoneCelular() +"', telefone_residencial='" + cliente.getTelefoneResidencial() +"', cartao_de_credito='" + cliente.getCartaoDeCredito() +"' where rg='" + cliente.getRg() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public ObservableList<Cliente> buscaClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereFuncionario(Funcionario f1, Endereco e1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("insert into sw_airlines.funcionario (cpf, nome, sexo, rg, cargo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, rua, cidade, bairro, numero, estado) " +
					"values('" + f1.getCpf() +"','" + f1.getNome() +"','" + f1.getSexo() +"','" + f1.getRg() +"','" + f1.getCargo() +"','" + f1.getDataDeNascimento() +"','" + f1.getEstadoCivil() +"','" + f1.getNacionalidade() +"','" + f1.getTelefoneCelular() +"','" + f1.getTelefoneResidencial() + "','" + e1.getRua() + "','" + e1.getCidade() + "','" + e1.getBairro() + "','" + e1.getNumero() + "','" + e1.getEstado() + "');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public boolean excluiFuncionario(Funcionario f1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("delete from sw_airlines.funcionario where cpf = '" + f1.getCpf() +"';");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public void alteraFuncionario(Funcionario f1, Endereco e1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("update sw_airlines.funcionario set cpf='" + f1.getCpf() +"' , nome='" + f1.getNome() +"', sexo='" + f1.getSexo() +"', rg='" + f1.getRg() +"', cargo='" + f1.getCargo() +"' ,data_de_nascimento='" + f1.getDataDeNascimento() +"', estado_civil='" + f1.getEstadoCivil() +"', nascionalidade='" + f1.getNacionalidade() +"', telefone_celular='" + f1.getTelefoneCelular() +"', telfone_residencial='" + f1.getTelefoneResidencial() +"' where cpf='" + f1.getCpf() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	@Override
	public ObservableList<Funcionario> buscaFuncionarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereVoo(Voo v1) {
		try {
			ConexaoBD cbd = new ConexaoBD();			
			cbd.executar("insert into sw_airlines.voo (origem, destino, quantidadeDePassageiros, rota, horaPartida, horaChegada, dataPartida, dataChegada, tipo_voo) " +
					"values('" + v1.getOrigem() +"','" + v1.getDestino() +"','" + v1.getQuantidadeDePassageiros() +"','" + v1.getRota() +"','" + v1.getHoraPartida() +"','" + v1.getHoraChegada() +"','" + v1.getDataPartida() + "','" + v1.getDataChegada() + "','" + v1.getTipoVoo() + "');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadVoo.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public boolean excluiVoo(Voo v1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("delete from sw_airlines.voo where id = '" + v1.getId() +"';");
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(TelaCadVoo.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public boolean alteraVoo(Voo v1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("update sw_airlines.voo set origem='" + v1.getOrigem() +"', destino='" + v1.getDestino() + "', rota='" + v1.getRota() +"', horaPartida='" + v1.getHoraPartida() +"', horaChegada='" + v1.getHoraChegada() +"', dataPartida='" + v1.getDataPartida() + "', dataChegada='" + v1.getDataChegada() + "', tipo_voo='" + v1.getTipoVoo() + "' where id='" + v1.getId() +"';");
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(TelaEditVoo.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public ObservableList<Voo> buscaVoos() {
		ObservableList<Voo> voos;
		voos = FXCollections.observableArrayList();
		Date horaAtual = new Date();		
		
		try {			
			ConexaoBD cbd = new ConexaoBD();
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.voo;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {				
				Voo v1 = new Voo();				
				v1.setId(rs.getInt("id"));
				v1.setOrigem(rs.getString("origem"));
				v1.setDestino(rs.getString("destino"));
				v1.setQuantidadeDePassageiros(rs.getInt("quantidadeDePassageiros"));
				v1.setRota(rs.getString("rota"));
				v1.setHoraPartida(rs.getString(("horaPartida")));
				v1.setHoraChegada(rs.getString("horaChegada"));
				v1.setDataPartida(rs.getString("dataPartida"));
				v1.setDataChegada(rs.getString("dataChegada"));
				v1.setTipoVoo(rs.getString("tipo_voo"));		
				voos.add(v1);
				
				try {
					if (horaAtual.before(v1.retornaHoraDataPartida())) {
						v1.setStatus("Não iniciado");
					}
					if (horaAtual.after(v1.retornaHoraDataPartida()) && horaAtual.before(v1.retornaHoraDataChegada())) {
						v1.setStatus("Em andamento");
					}
					
					if (horaAtual.after(v1.retornaHoraDataChegada())) {
						v1.setStatus("Concluido");
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}			
			rs.close();
			stm.close();
			con.close();						
			return voos;
			
		} catch(SQLException e) {
			Logger.getLogger(TelaTabelaVoos.class.getName()).log(Level.SEVERE, null, e);
			return null;
			
		}
	}

	@Override
	public boolean insereContaDeUsuario(ContaDeUsuario c1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("insert into sw_airlines.usuario (login, senha, tipo_conta) values('" + c1.getLogin() +"','" + c1.getSenha() +"','" + c1.getTipoConta() +"');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public boolean excluiContaDeUsuario(ContaDeUsuario c1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("delete from sw_airlines.usuario where login = '" + c1.getLogin() +"';");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public void alteraContaDeUsuaio(ContaDeUsuario c1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("update sw_airlines.usuario set senha='" + c1.getSenha() + "' where login='" + c1.getSenha() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	@Override
	public ObservableList<ContaDeUsuario> buscaContasDeUsuario() {
		ObservableList<ContaDeUsuario> usuarios;
		usuarios = FXCollections.observableArrayList();
		try {
			ConexaoBD cbd = new ConexaoBD();
			//Abre a conexão com o banco de dados
			Connection con = cbd.abreConexao();
			//Cria um statement para realizar comandos no BD
			PreparedStatement stm = con.prepareStatement("select * from sw_airlines.usuario;");
			//Armazena o valor da pesquisa e no rs
			ResultSet rs = stm.executeQuery();
			//Com o while ele vai rodar linha por linha sendo o parâmetro o rs.next(), que retorna V ou F se a tabela tiver ou não linhas.
			while (rs.next()){
				ContaDeUsuario u = new ContaDeUsuario();
				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setTipoConta("tipo_conta");
				//Adiciona o objeto a (usuario) a lista usuarios, usando o método add
				usuarios.add(u);
			}
			rs.close();
			stm.close();
			con.close();
			//O retorno pode ser tanto dentro do try-catch sendo return usuario e return null ou fora e assim só sendo preciso um return
			return usuarios;

		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	@Override
	public boolean autenticar(ContaDeUsuario c1) {
		for (ContaDeUsuario temp : buscaContasDeUsuario()) {
			if (temp.getLogin().equals(c1.getLogin()) && temp.getSenha().equals(c1.getSenha())){
				return true;
				
			}
		}		
		return false;
	}		
	

}
