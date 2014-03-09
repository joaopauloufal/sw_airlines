package swairlines.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ConexaoDAO implements ConexaoBDMySql {
	
	private Connection c;
	private String sql;
	private Statement stm;
	
	public ConexaoDAO() {
		
		try {
			criarBanco();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}	
	
	@Override
	public Connection abreConexao() throws SQLException {
		//35216493
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost/","root","35216493");
            return c;
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados. Tente novamente mais tarde.", "Erro de Conexão Com o Banco de Dados", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ConexaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
	
	@Override
	public void desconectar() throws SQLException{
		c.close();
	}
	
	@Override
	public boolean executar(String sql) throws SQLException {
		try {
			abreConexao();
			stm = null;
			stm = c.createStatement();
			stm.executeUpdate(sql);
			return true;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados. Tente novamente mais tarde.", "Erro de Conexão Com o Banco de Dados", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		} finally {
			desconectar();
		}
		return false;
		
	}
	
	
	private void criarBanco() throws SQLException {
		sql = "CREATE DATABASE IF NOT EXISTS sw_airlines DEFAULT CHARACTER SET utf8 ";
		executar(sql);
		criarTabelaCliente();
		criarTabelaFuncionario();
		criarTabelaUsuario();
		criarTabelaVoo();
	}

	
	private void criarTabelaVoo() throws SQLException {
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
	}
	
	private void criarTabelaUsuario() throws SQLException {
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
				+ " REFERENCES sw_airlines.funcionario (cpf)"
				+ " ON DELETE CASCADE)"
	            + " ENGINE = InnoDB "
	            + " DEFAULT CHARACTER SET = utf8;";
		abreConexao();
		executar(sql);
		sql = "INSERT IGNORE sw_airlines.usuario(login, senha, tipo_conta, cpf_func) "
				+ "VALUES('admin', '0000','Administrador', '000.000.000-00');";
		executar(sql);	
	}

	private void criarTabelaFuncionario() throws SQLException {
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
	}

	private void criarTabelaCliente() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.cliente("
				+ " rg varchar(25) not null,"
				+ " cpfcnpj varchar(40) not null,"
				+ " nome varchar(80) not null,"
				+ " sexo varchar(40) not null,"
				+ " data_de_nascimento varchar(25) not null ,"
				+ " estado_civil varchar(40),"
				+ " nacionalidade varchar(60),"
				+ " telefone_celular varchar(30),"
				+ " telefone_residencial varchar(30),"
				+ " cartao_de_credito varchar(30),"
				+ " rua varchar(80) not null,"
				+ " cidade varchar(80) not null,"
				+ " bairro varchar(80) not null,"
				+ " numero varchar(20) not null,"
				+ " estado varchar(80) not null,"
				+ " primary key (rg))"
	            + " ENGINE = InnoDB"
	            + " DEFAULT CHARACTER SET = utf8;";
		abreConexao();
		executar(sql);
		
	}
	
	
	

}