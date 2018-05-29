/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;

/**
 *
 * @author KEVIN
 */
public interface ClienteDao {
    public void salvar(Cliente cliente);
    public void deletar(Cliente cliente);
    public void atualizar(Cliente cliente);
    public List<Cliente> listar();
    public Cliente procurarPorCpf(String cpf);
    //Adicionado
    public Cliente procurarPorNome(String nome);
    public List<Cliente> listarPorNome(String nome);
}
