/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl_BD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import dao.ClienteDao;

/**
 *
 * @author KEVIN
 */
public class ClienteDaoBd implements ClienteDao{
    private Connection conexao;
    private PreparedStatement comando;

    public Connection conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }
    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new BDException(ex);

        }

    } 
    
    @Override
    public void salvar(Cliente cliente) {
        int id = 0;
        try {
            String sql = "INSERT INTO cliente (cpf, nome, email) "
                    + "VALUES (?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, cliente.getCpf());
            comando.setString(2, cliente.getNome());            
            comando.setString(3,cliente.getEmail());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();            

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }    

    @Override
    public void deletar(Cliente cliente) {
         try {
            String sql = "DELETE FROM cliente WHERE cpf = ?";

            conectar(sql);
            comando.setString(1, cliente.getCpf());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    } 

    @Override
    public void atualizar(Cliente cliente) {
    try {
            String sql = "UPDATE cliente SET cpf=?, nome=?, email=? "
                    + "WHERE cpf=?";

            conectar(sql);
            comando.setString(1, cliente.getCpf());
            comando.setString(2, cliente.getNome());           
            comando.setString(3, cliente.getEmail());   
            comando.setString(4, cliente.getCpf());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public List<Cliente> listar() {
         List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {                
                String cpf = resultado.getString("cpf");
                String nome = resultado.getString("nome");
                String email= resultado.getString("email");
                Cliente cli = new Cliente(cpf, nome, email);
                listaClientes.add(cli);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaClientes);
    }

    @Override
    public Cliente procurarPorCpf(String cpf) {
     String sql = "SELECT * FROM cliente WHERE cpf = ?";

        try {
            conectar(sql);
            comando.setString(1, cpf);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                String rg = resultado.getString("cpf");
                String nome = resultado.getString("nome");
                String email= resultado.getString("email");
                Cliente cli = new Cliente(cpf, nome, email);
                return cli;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o paciente pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Cliente procurarPorNome(String nome) {
    String sql = "SELECT * FROM cliente WHERE nome = ?";

        try {
            conectar(sql);
            comando.setString(1, nome);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {               
                String cpf = resultado.getString("cpf");
                String email= resultado.getString("email");               
                Cliente cli = new Cliente( cpf, nome, email);
                return cli;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o paciente pelo rg do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null); 
    }

    @Override
    public List<Cliente> listarPorNome(String nome) {
    List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";
        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                String cpf = resultado.getString("cpf");
                String nomeX = resultado.getString("nome");                
                String email = resultado.getString("email");
                Cliente cli = new Cliente( cpf, nomeX, email);
                listaClientes.add(cli);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaClientes); 
    }
    
}
