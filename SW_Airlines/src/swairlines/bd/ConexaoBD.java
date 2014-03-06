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
		//35216493
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost/","root","12345");
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
			
		} catch(SQLException e) {
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
				+ " id INT NOT NULL AUTO_INCREMENT,"
				+ " origem VARCHAR(60) NOT NULL,"
				+ " destino VARCHAR(60) NOT NULL,"
				+ " quantidadeDePassageiros INT NOT NULL,"
				+ " rota varchar(60) NOT NULL,"
				+ " horaPartida VARCHAR(20) NOT NULL,"
				+ " horaChegada VARCHAR(20) NOT NULL,"
				+ " dataPartida VARCHAR(20) NOT NULL,"
				+ " dataChegada VARCHAR(20) NOT NULL,"
				+ " tipo_voo VARCHAR(80) NOT NULL,"
				+ " PRIMARY KEY (id)) "
	            + " ENGINE = InnoDB "
	            + " DEFAULT CHARACTER SET = utf8;";
		abreConexao();
		executar(sql);
		desconectar();	
	}

	private void tabelaUsuario() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.usuario("
				+ " login varchar(30) NOT NULL,"
				+ " senha varchar(30) NOT NULL,"
				+ " tipo_conta VARCHAR(30) NOT NULL,"
				+ " cpf_func VARCHAR(120) NOT NULL,"
				+ " PRIMARY KEY (login, cpf_func),"
				+ " UNIQUE (login),"
				+ " UNIQUE (cpf_func),"
				+ " INDEX fk_cpf_usuario (cpf_func),"
				+ "	CONSTRAINT fk_cpf_usuario"
				+ " FOREIGN KEY (cpf_func)"
				+ " REFERENCES sw_airlines.funcionario (cpf)) "
	            + " ENGINE = InnoDB "
	            + " DEFAULT CHARACTER SET = utf8;";
		abreConexao();
		executar(sql);
		sql = "INSERT IGNORE sw_airlines.usuario(login, senha, tipo_conta, cpf_func) "
				+ "VALUES('admin', '0000','Administrador', '000.000.000-00');";
		executar(sql);
		desconectar();	
	}

	private void tabelaFuncionario() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.funcionario("
				+ " cpf VARCHAR(120) NOT NULL,"
				+ " nome VARCHAR(80) NOT NULL,"
				+ " sexo VARCHAR(50) NOT NULL,"
				+ " rg VARCHAR(80) NOT NULL ,"
				+ " cargo VARCHAR(50) NOT NULL,"
				+ " data_de_nascimento VARCHAR(30) NOT NULL,"
				+ " estado_civil VARCHAR(50) NOT NULL,"
				+ " nacionalidade VARCHAR(50) NOT NULL,"
				+ " telefone_celular VARCHAR(40) NOT NULL,"
				+ " telefone_residencial VARCHAR(40) NOT NULL,"
				+ " rua VARCHAR(80) NOT NULL,"
				+ " cidade VARCHAR(80) NOT NULL,"
				+ " bairro VARCHAR(80) NOT NULL,"
				+ " numero VARCHAR(20) NOT NULL,"
				+ " estado VARCHAR(80) NOT NULL,"
				+ " PRIMARY KEY (cpf),"
				+ " UNIQUE (cpf)) "
	            + " ENGINE = InnoDB"
	            + " DEFAULT CHARACTER SET = utf8;" ;
		abreConexao();
		executar(sql);
		sql = "INSERT IGNORE sw_airlines.funcionario(cpf, nome, sexo, rg, cargo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, rua, cidade, bairro, numero, estado) "
				+ "VALUES('000.000.000-00', 'admin', 'anônimo', '0000000-0', 'Gerente', '00/00/0000', 'anônimo', 'anônimo', '0000', '0000', 'anônimo', 'anônimo', 'anônimo', 'anônimo', 'anônimo');";
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