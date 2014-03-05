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
import swairlines.gui.TelaCadConta;
import swairlines.gui.TelaCadFuncionario;
import swairlines.gui.TelaCadVoo;
import swairlines.gui.TelaEditVoo;
import swairlines.gui.TelaTabelaVoos;

public class Gerente extends Operador implements AcessoBancoGerente {
	
	private static final String TIPO_CONTA = "Administrador";
	
	public Gerente(String nome, String sexo, String cpf, String rg,
			String cargo, String dataDeNascimento, String telefoneCelular,
			String telefoneResidencial, String nacionalidade, String estadoCivil, Endereco endereco) {
		super(nome, sexo, cpf, rg, cargo, dataDeNascimento, telefoneCelular, telefoneResidencial, nacionalidade, estadoCivil, endereco);
		this.getConta().setTipoConta(Gerente.TIPO_CONTA);
	}
	
	public Gerente() {
		
	}



	@Override
	public boolean insereFuncionario(Funcionario f1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			if(cbd.executar("INSERT INTO sw_airlines.funcionario (cpf, nome, sexo, rg, cargo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, rua, cidade, bairro, numero, estado) " +
					"VALUES('" + f1.getCpf() +"','" + f1.getNome() +"','" + f1.getSexo() +"','" + f1.getRg() +"','" + f1.getCargo() +"','" + f1.getDataDeNascimento() +"','" + f1.getEstadoCivil() +"','" + f1.getNacionalidade() +"','" + f1.getTelefoneCelular() +"','" + f1.getTelefoneResidencial() + "','" 
					+ f1.getEndereco().getRua() + "','" + f1.getEndereco().getCidade() + "','" + f1.getEndereco().getBairro() + "','" + f1.getEndereco().getNumero() + "','" + f1.getEndereco().getEstado() + "');")) {
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public boolean excluiFuncionario(Funcionario f1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			if(cbd.executar("DELETE FROM sw_airlines.funcionario WHERE cpf= '" + f1.getCpf() +"';")) {
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}

	@Override
	public boolean alteraFuncionario(Funcionario f1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			if(cbd.executar("UPDATE sw_airlines.funcionario set cpf='" + f1.getCpf() +"' , nome='" + f1.getNome() +"', sexo='" + f1.getSexo() +"', rg='" + f1.getRg() +"', cargo='" + f1.getCargo() +"' ,data_de_nascimento='" + f1.getDataDeNascimento() +"', estado_civil='" + f1.getEstadoCivil() +"', nacionalidade='" + f1.getNacionalidade() +"', telefone_celular='" + f1.getTelefoneCelular() +"', telefone_residencial='" + f1.getTelefoneResidencial() 
					+", 'rua='" + f1.getEndereco().getRua() +", 'cidade='" + f1.getEndereco().getCidade() +", 'bairro='" + f1.getEndereco().getBairro() + ", 'numero='" + f1.getEndereco().getNumero() +", 'estado='"+ f1.getEndereco().getEstado() + "' WHERE cpf='" + f1.getCpf() +"';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}

	@Override
	public ObservableList<Funcionario> buscaFuncionarios() {
		ObservableList<Funcionario> funcionarios;
		funcionarios = FXCollections.observableArrayList();
		try {
			ConexaoBD cbd = new ConexaoBD();
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.funcionario;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				Funcionario func = new Operador();
				func.setCpf(rs.getString("cpf"));
				func.setNome(rs.getString("nome"));
				func.setSexo(rs.getString("sexo"));
				func.setRg(rs.getString("rg"));
				func.setCargo(rs.getString("cargo"));
				func.setDataDeNascimento(rs.getString("data_de_nascimento"));
				func.setEstadoCivil(rs.getString("estado_civil"));
				func.setNacionalidade(rs.getString("nacionalidade"));
				func.setTelefoneCelular(rs.getString("telefone_celular"));
				func.setTelefoneResidencial(rs.getString("telefone_residencial"));
				func.getEndereco().setRua(rs.getString("rua"));
				func.getEndereco().setCidade(rs.getString("cidade"));
				func.getEndereco().setBairro(rs.getString("bairro"));
				func.getEndereco().setNumero(rs.getString("numero"));
				func.getEndereco().setEstado(rs.getString("estado"));
				funcionarios.add(func);
				
			}
			
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, e);	
			return null;
		}
		return funcionarios;
		
	}

	@Override
	public boolean insereVoo(Voo v1) {
		try {
			ConexaoBD cbd = new ConexaoBD();			
			if(cbd.executar("INSERT INTO sw_airlines.voo (origem, destino, quantidadeDePassageiros, rota, horaPartida, horaChegada, dataPartida, dataChegada, tipo_voo) " +
					"VALUES('" + v1.getOrigem() +"','" + v1.getDestino() +"','" + v1.getQuantidadeDePassageiros() +"','" + v1.getRota() +"','" + v1.getHoraPartida() +"','" + v1.getHoraChegada() +"','" + v1.getDataPartida() + "','" + v1.getDataChegada() + "','" + v1.getTipoVoo() + "');")) {
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadVoo.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
		
	}

	@Override
	public boolean excluiVoo(Voo v1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			if(cbd.executar("DELETE FROM sw_airlines.voo WHERE id= '" + v1.getId() +"';")) {
				return true;
			}
			

		} catch (SQLException ex) {
			Logger.getLogger(TelaCadVoo.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}

	@Override
	public boolean alteraVoo(Voo v1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			if(cbd.executar("UPDATE sw_airlines.voo SET origem='" + v1.getOrigem() +"', destino='" + v1.getDestino() + "', rota='" + v1.getRota() +"', horaPartida='" + v1.getHoraPartida() +"', horaChegada='" + v1.getHoraChegada() +"', dataPartida='" + v1.getDataPartida() + "', dataChegada='" + v1.getDataChegada() + "', tipo_voo='" + v1.getTipoVoo() + "' WHERE id='" + v1.getId() +"';")) {
				return true;
			}
			

		} catch (SQLException ex) {
			Logger.getLogger(TelaEditVoo.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
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
			if(cbd.executar("INSERT INTO sw_airlines.usuario (login, senha, tipo_conta, cpf_func) VALUES('" + c1.getLogin() +"','" + c1.getSenha() +"','" + c1.getTipoConta() +"','" + c1.getCpfFuncionario() +"');")){
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);			
		}
		return false;
	}

	@Override
	public boolean excluiContaDeUsuario(ContaDeUsuario c1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			if(cbd.executar("DELETE FROM sw_airlines.usuario WHERE login= '" + c1.getLogin() +"';")) {
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}

	@Override
	public boolean alteraContaDeUsuario(ContaDeUsuario c1) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			if(cbd.executar("UPADATE sw_airlines.usuario SET senha='" + c1.getSenha() + "' WHERE login='" + c1.getSenha() +"';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}	
	

}
