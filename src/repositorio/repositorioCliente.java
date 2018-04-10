/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author KEVIN
 */
public class repositorioCliente {
   
    private List<Cliente> listaClientes;
    private static repositorioCliente instance = null;
    
    private repositorioCliente() {
        listaClientes = new ArrayList<Cliente>();
    }

    public static repositorioCliente getInstance() {
        if(instance == null) instance = new repositorioCliente();
        return instance;
    }
    
    

    public boolean add(Cliente cliente) {
        return (listaClientes.add(cliente));
    }
    
    public boolean estaVazio(){
        return listaClientes.isEmpty();
    }

    public List<Cliente> getCliente() {
        return listaClientes;
    }

    public boolean clienteExiste(String Cpf) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(Cpf)) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarCliente(String Cpf) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(Cpf)) {
                return cliente;
           }
        }
        return null;
    }
} 


