package swairlines.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {
	
	private Connection c;
	private String sql;
	private Statement stm;
	
	public ConexaoBD() {
		
		try {
			criarBanco();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
	}	
	
	public Connection abreConexao(){
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost/","root","35216493");
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
	
	public void desconectar() throws SQLException {
		c.close();
	}
	
	public boolean executar(String sql) throws SQLException {
		try{
			abreConexao();
			stm = null;
			stm = c.createStatement();
			stm.executeUpdate(sql);
			desconectar();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public void criarBanco() throws SQLException {
		sql = "CREATE DATABASE IF NOT EXISTS sw_airlines DEFAULT CHARACTER SET utf8 ";
		executar(sql);
		tabelaCliente();
		tabelaFuncionario();
		tabelaUsuario();
		tabelaVoo();		
		
	}

	private void tabelaVoo() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.voo("
				+ " id int not null auto_increment,"
				+ " origem varchar(60) not null,"
				+ " destino varchar(60) not null,"
				+ " quantidadeDePassageiros int not null,"
				+ " rota varchar(60) not null,"
				+ " horaPartida varchar(20) not null,"
				+ " horaChegada varchar(20) not null,"
				+ " dataPartida varchar(20) not null,"
				+ " dataChegada varchar(20) not null,"
				+ " tipo_voo varchar(80) not null,"
				+ " primary key (id)) "
	            + " ENGINE = InnoDB "
	            + " DEFAULT CHARACTER SET = utf8;";
		abreConexao();
		executar(sql);
		desconectar();	
	}

	private void tabelaUsuario() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.usuario("
				+ " login varchar(30) not null,"
				+ " senha varchar(30) not null,"
				+ " tipo_conta varchar(30) not null,"
				+ " primary key (login)) "
	            + " ENGINE = InnoDB "
	            + " DEFAULT CHARACTER SET = utf8;" ;
		abreConexao();
		executar(sql);
		sql = "INSERT IGNORE sw_airlines.usuario(login, senha, tipo_conta) "
				+ "VALUES('admin', '0000','Administrador');";
		executar(sql);
		desconectar();	
	}

	private void tabelaFuncionario() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.funcionario("
				+ " cpf varchar(120) not null ,"
				+ " nome varchar(80) not null,"
				+ " sexo varchar(50) not null ,"
				+ " rg varchar(80) not null ,"
				+ " cargo varchar(50) not null ,"
				+ " data_de_nascimento varchar(30) not null ,"
				+ " estado_civil varchar(50) not null,"
				+ " nacionalidade varchar(50) not null ,"
				+ " telefone_celular varchar(40) not null ,"
				+ " telefone_residencial varchar(40) not null,"
				+ " rua varchar(80) not null ,"
				+ " cidade varchar(80) not null ,"
				+ " bairro varchar(80) not null ,"
				+ " numero varchar(20) not null ,"
				+ " estado varchar(80) not null ,"
				+ " primary key (cpf)) "
	            + " ENGINE = InnoDB "
	            + " DEFAULT CHARACTER SET = utf8;" ;
		abreConexao();
		executar(sql);
		desconectar();		
	}

	private void tabelaCliente() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.cliente("
				+ "rg varchar(25) not null ,"
				+ "cpf_cnpj varchar(25) not null ,"
				+ "nome varchar(80) not null ,"
				+ "sexo varchar(40) not null ,"
				+ "data_de_nascimento varchar(25) not null ,"
				+ "estado_civil varchar(20) ,"
				+ "nacionalidade varchar(25) ,"
				+ "telefone_celular varchar(25) ,"
				+ "telefone_residencial varchar(25) ,"
				+ "cartao_de_credito varchar(30) ,"
				+ "rua varchar(80) not null ,"
				+ "cidade varchar(80) not null ,"
				+ "bairro varchar(80) not null ,"
				+ "numero varchar(20) not null ,"
				+ "estado varchar(80) not null ,"
				+ "primary key (rg))"
	            + "ENGINE = InnoDB "
	            + "DEFAULT CHARACTER SET = utf8;" ;
		abreConexao();
		executar(sql);
		desconectar();		
		
	}
	
	
	

}