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
		//35216493 senha bd do Cid
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
		criarTabelaVendas();
		criarTabelaBagagens();
	}

	
	private void criarTabelaVoo() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.voo("
				+ " id INT NOT NULL AUTO_INCREMENT,"
				+ " aeronave_numero VARCHAR(80) NOT NULL,"
				+ " origem VARCHAR(60) NOT NULL,"
				+ " destino VARCHAR(60) NOT NULL,"
				+ " quantidadeDePassageiros INT NOT NULL,"
				+ " rota varchar(60) NOT NULL,"
				+ " horaPartida VARCHAR(20) NOT NULL,"
				+ " horaChegada VARCHAR(20) NOT NULL,"
				+ " dataPartida VARCHAR(20) NOT NULL,"
				+ " dataChegada VARCHAR(20) NOT NULL,"
				+ " tipo_voo VARCHAR(80) NOT NULL,"
				+ " valor DOUBLE(15,2) NOT NULL,"
				+ " status VARCHAR(40),"
				+ " PRIMARY KEY (id)) "
	            + " ENGINE = InnoDB "
	            + " DEFAULT CHARACTER SET = utf8;";
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
				+ " ON DELETE CASCADE ON UPDATE CASCADE)"
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
				+ " UNIQUE (cpf))"
	            + " ENGINE = InnoDB"
	            + " DEFAULT CHARACTER SET = utf8;" ;
		executar(sql);
		sql = "INSERT IGNORE sw_airlines.funcionario(cpf, nome, sexo, rg, cargo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, rua, cidade, bairro, numero, estado) "
				+ "VALUES('000.000.000-00', 'admin', 'anônimo', '0000000-0', 'Gerente', '00/00/0000', 'anônimo', 'anônimo', '0000', '0000', 'anônimo', 'anônimo', 'anônimo', 'anônimo', 'anônimo');";
		executar(sql);	
	}

	private void criarTabelaCliente() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.cliente("
				+ " rg VARCHAR(80) NOT NULL,"
				+ " cpfcnpj VARCHAR(40) NOT NULL,"
				+ " nome VARCHAR(80) NOT NULL,"
				+ " sexo VARCHAR(40) NOT NULL,"
				+ " passaporte_numero VARCHAR(40),"
				+ " data_de_nascimento VARCHAR(25) NOT NULL,"
				+ " estado_civil VARCHAR(40),"
				+ " nacionalidade VARCHAR(60),"
				+ " telefone_celular VARCHAR(30) NOT NULL,"
				+ " telefone_residencial VARCHAR(30),"
				+ " cartao_de_credito VARCHAR(30),"
				+ " rua varchar(80) NOT NULL,"
				+ " cidade VARCHAR(80) NOT NULL,"
				+ " bairro VARCHAR(80) NOT NULL,"
				+ " numero VARCHAR(20) NOT NULL,"
				+ " estado VARCHAR(80) NOT NULL,"
				+ " PRIMARY KEY (cpfcnpj),"
				+ " UNIQUE (rg, cpfcnpj))"
	            + " ENGINE = InnoDB"
	            + " DEFAULT CHARACTER SET = utf8;";
		executar(sql);
		
	}
	
	private void criarTabelaVendas() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.vendas("
				+ " cpf_cliente VARCHAR(40) NOT NULL,"
				+ " nome_cliente VARCHAR(80) NOT NULL,"
				+ " cartao_cliente VARCHAR(80) NOT NULL,"
				+ " id_voo_venda INT NOT NULL,"
				+ " tipo_venda VARCHAR(40) NOT NULL,"
				+ " data_venda VARCHAR(40) NOT NULL,"
				+ " parcelas INT(20),"
				+ " valor_parcela DOUBLE(15,2),"
				+ " valor_voo DOUBLE(15,2) NOT NULL,"
				+ " origem_voo VARCHAR(80) NOT NULL,"
				+ " destino_voo VARCHAR(80) NOT NULL,"				
				+ " PRIMARY KEY (cpf_cliente, id_voo_venda), "
				+ " CONSTRAINT cpf_cliente_cpfcnpj"
				+ " FOREIGN KEY (cpf_cliente)"
				+ " REFERENCES sw_airlines.cliente (cpfcnpj)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE,"
				+ " CONSTRAINT id_voo_id"
				+ " FOREIGN KEY (id_voo_venda)"
				+ " REFERENCES sw_airlines.voo (id)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE)"
				+ " ENGINE = InnoDB"
				+ " DEFAULT CHARACTER SET = utf8;";
		executar(sql);
	}
	
	private void criarTabelaBagagens() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sw_airlines.bagagens("
				+ " cpf_cliente_bagagem VARCHAR(40) NOT NULL,"
				+ " nome_cliente VARCHAR(120) NOT NULL,"
				+ " voo_id INT NOT NULL,"
				+ " origem_voo VARCHAR(80) NOT NULL,"
				+ " destino_voo VARCHAR(80) NOT NULL,"
				+ " peso_bagagem DOUBLE(15,2) NOT NULL,"
				+ " PRIMARY KEY (cpf_cliente_bagagem, voo_id),"
				+ " CONSTRAINT cpf_bagagem_cpf_cliente"
				+ " FOREIGN KEY (cpf_cliente_bagagem)"
				+ " REFERENCES sw_airlines.vendas (cpf_cliente)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE,"
				+ " CONSTRAINT voo_id_id_voo_venda"
				+ " FOREIGN KEY (voo_id)"
				+ " REFERENCES sw_airlines.vendas (id_voo_venda)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE)"
				+ " ENGINE = InnoDB"
				+ " DEFAULT CHARACTER SET = utf8;";
		executar(sql);
	}
	

}
