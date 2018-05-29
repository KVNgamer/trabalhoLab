/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menusAntigos;

import java.time.format.DateTimeParseException;
import model.Cliente;
import java.util.ArrayList;
import java.util.InputMismatchException;
import repositorio.repositorioCliente;
import util.Console;

/**
 *
 * @author KEVIN
 */
public class menuCli {
    private static int k=1;
    public static void menuCliente() {
        try{
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
                    System.out.println("Voltando menu principal...");
                    break;
                default:
                    System.err.println("Erro: Escolha a opcao correta!!");                
            }
        }catch(InputMismatchException ime){
                System.err.println("Não é possivel digitar letras ou caracteres especiais!");
        }
    }
    
    private static void criaCli() {
        boolean verificado =true;
        String cpf = Console.scanString("CPF: ");
        String nome="",email =""; 
        
        if(cpf.replaceAll(" ","").length()<= 0){
            verificado =false;
            System.err.println("CPF invalido,tente novamente");
        }
        if (repositorioCliente.getInstance().clienteExiste(cpf)) {
            System.err.println("CPF já existente no cadastro");
            verificado =false;
        } 
        if(verificado == true){
            nome = Console.scanString("Nome: ");

            if(nome.replaceAll(" ","").length()==0){
                verificado =false;
                System.err.println("Nome invalido,tente novamente");
            }
        }
        
        if(verificado == true){
            email = Console.scanString("Email: ");        
            if(email.replaceAll(" ","").length()==0){
                verificado = false;
                System.err.println("Email invalido,tente novamente");
             }
        }           
      
        if(verificado==true){
            int nuconta=k;           
            double saldo=0;
            repositorioCliente.getInstance().add(new Cliente(cpf,nome, email, nuconta, saldo));
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
             k++;            
        }
         
    } 
    
    
    private static void listarCli() {
        

        if(repositorioCliente.getInstance().estaVazio()){
            System.out.println("-----------------------------");        
            System.err.println("Nao ha Cliente cadastrados");
            System.out.println("-----------------------------\n");
        }
        else{
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "|CPF") + "\t"
                    + String.format("%-20s", "|Nome") + "\t"
                    + String.format("%-20s", "|Email"));
            for (Cliente cliente : repositorioCliente.getInstance().getCliente()) {
                System.out.println(String.format("%-10s", "|" + cliente.getCpf()) + "\t"
                        + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                        + String.format("%-20s", "|" + cliente.getEmail()));
            }
            System.out.println("-----------------------------\n");
        }

    }
    
    
    
    
    
}
