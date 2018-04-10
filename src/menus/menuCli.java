/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.time.format.DateTimeParseException;
import model.Cliente;
import java.util.ArrayList;
import repositorio.repositorioCliente;
import util.Console;

/**
 *
 * @author KEVIN
 */
public class menuCli {
    private static int k=1;
    public static void menuCliente() {
        System.out.println("Menu: ");
        System.out.println("    1- Adicionar cliente");
        System.out.println("    2- Listar cliente");
        System.out.println("    0- Sair");
        int opcao = Console.scanInt("Digite a opcao: ");
        System.out.println("\n==ANALIZANDO==\n");
        switch(opcao){
            case 1:
                System.out.println("Adicionando ...");
                criaCli();
                break;
            case 2:
                System.out.println("Listando ...");
                listarCli();
                break;
            case 0:
                System.out.println("Saindo do Sistema...");
                break;
            default:
                System.err.println("Erro: Escolha a opcao correta!!");                
        }
    }
    
    private static void criaCli() {
        String cpf = Console.scanString("CPF: ");
        if (repositorioCliente.getInstance().clienteExiste(cpf)) {
            System.out.println("CPF já existente no cadastro");
        } else {
            String nome = Console.scanString("Nome: ");
            String email = Console.scanString("Email: ");
            int nuconta=k;
            k++;
            double saldo=0;
            repositorioCliente.getInstance().add(new Cliente(cpf,nome, email, nuconta, saldo));
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");            
            
            }
        } 
    
    
    private static void listarCli() {
        

        if(repositorioCliente.getInstance().estaVazio()){
            System.out.println("-----------------------------");        
            System.out.println("Nao ha Cliente cadastrados");
            System.out.println("-----------------------------\n");
        }
        else{
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CPF") + "\t"
                    + String.format("%-20s", "|Nome") + "\t"
                    + String.format("%-20s", "|Email"));
            for (Cliente cliente : repositorioCliente.getInstance().getCliente()) {
                System.out.println(String.format("%-10s", cliente.getCpf()) + "\t"
                        + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                        + String.format("%-20s", "|" + cliente.getEmail()));
            }
            System.out.println("-----------------------------\n");
        }

    }
    
    
    
    
    
}
