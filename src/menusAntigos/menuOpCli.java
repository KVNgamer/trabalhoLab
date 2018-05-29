/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menusAntigos;

import java.util.InputMismatchException;
import model.Cliente;
import model.Produto;
import repositorio.repositorioCliente;
import repositorio.repositorioProduto;
import util.Console;

/**
 *
 * @author KEVIN
 */

public class menuOpCli {
    
    public static void menuOpCliente()  {
        boolean x =false;
        String cpf="";
        while(x==false){
            cpf=Console.scanString("Qual o CPF do cliente: ");  
            if (!repositorioCliente.getInstance().clienteExiste(cpf)) {
                System.out.println("CPF não existente no cadastro.");
            }
            if (repositorioCliente.getInstance().clienteExiste(cpf)){
                x=true;
                
                System.out.println("CPF encontrado com sucesso.");
            }
        }        
        
        Cliente cliente=repositorioCliente.getInstance().buscarCliente(cpf);
        
        try{
            System.out.println("-----------------------------\n");       
            System.out.println("Menu: ");
            System.out.println("    1- Depositar");
            System.out.println("    2- Transferir");
            System.out.println("    3- Vizualizar Saldo");
            System.out.println("    0- Voltar");        
            int opcao = Console.scanInt("Digite a opcao: ");
            System.out.println("\n==ANALIZANDO==\n");
            switch(opcao){
                case 1:
                    System.out.println("Abrindo processo de deposito ...");
                    depositar(cliente);
                    break;
                case 2:
                    System.out.println("Abrindo processo de transferencia ...");
                    transferir(cliente);
                    break;
                case 3:
                    System.out.println("Fazendo analize do saldo do Sr." + cliente.getNome() +" ...");
                    mostraSaldo(cliente);
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
    
    private static void depositar(Cliente cliente) {
        int saldoAdd=Console.scanInt("O saldo atual do "+cliente.getSaldo()+", quanto pretende addcidionar a conta?");
        
        cliente.setSaldo(saldoAdd);
        System.out.println("saldo atual é de: "+cliente.getSaldo());   
    }
    
    private static void mostraSaldo(Cliente cliente) {
        System.out.println("O saldo atual do Sr(a)  "+cliente.getNome()+" é de: "+cliente.getSaldo());
    }     

    private static void transferir(Cliente cliente) {
        boolean x=false;
        double saldoAdd=0.0;
        while(x==false){
            saldoAdd=Console.scanDouble("O saldo atual do "+cliente.getSaldo()+", quanto pretende transferir para outra conta?\n");
            if(saldoAdd>cliente.getSaldo()){
                System.out.println("O saldo atual é insuficiente!");
                x=false;
            }
            if(saldoAdd<0)
                System.out.println("Numeros negativos nao sao transferiveis!");
                x=false;
            if(saldoAdd<cliente.getSaldo() && saldoAdd>0){
                x=true;
            }
        }  
        
        while(x==true){
            int erro=0;
            String cpf2=Console.scanString("qual o cpf que recebera "+saldoAdd+"$ ?\n");            
            if (!repositorioCliente.getInstance().clienteExiste(cpf2)) {
                erro++;              
                System.err.println("CPF não existente no cadastro.");    
                x=false;
            }            
            if (repositorioCliente.getInstance().clienteExiste(cpf2)){
                int test=Console.scanInt("vc tem certeza que quer transferir da sua conta para a conta do Sr(a) "
                    +repositorioCliente.getInstance().buscarCliente(cpf2).getNome() + " um total de "
                    + saldoAdd+ "$ ?\n"
                            + "\n1-Confirmar."
                            + "\n2-Cancelar.\n");
                switch(test){
                    case 1:
                        System.out.println("Transferencia Sendo Realizada...");
                        cliente.setDescSaldo(saldoAdd);
                        repositorioCliente.getInstance().buscarCliente(cpf2).setSaldo(saldoAdd);
                        x=false;
                        break;
                    case 2:
                        x=false;
                        System.err.println("Cancelando transferencia...");
                        break;
                    default:
                        System.err.println("Erro: Escolha a opcao correta!!");            
                }
            }              
        }            
    }
}
