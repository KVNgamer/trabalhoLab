/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.time.Instant;
import static java.time.Instant.now;
import java.time.LocalDate;
import model.Cliente;
import model.Compra;
import model.Produto;
import repositorio.repositorioCliente;
import repositorio.repositorioCompra;
import repositorio.repositorioProduto;
import util.Console;
import util.DataTime;

public class menuCompra {
    static Cliente cliente=null;
    static int codCompras=1;
    public static void menuComprar() {           
        if(cliente==null)
        cliente=loginCompra();  
        
        System.out.println("Menu: ");
        System.out.println("    1- Adicionar Produto ao carrinho");
        System.out.println("    2- Listar Produto dentro do carrinho");
        System.out.println("    3- Finalizar compras");
        System.out.println("    0- Voltar");
        int opcao = Console.scanInt("Digite a opcao: ");
        System.out.println("\n==ANALIZANDO==\n");
        switch(opcao){
            case 1:
                System.out.println("Adicionando Produto ao Carinho...");
                listarEComprar();
                break;
            case 2:
                System.out.println("Listando Produtos dentro do carrinho...");
                listaDoCarrinho();
                break;
            case 3:
                System.out.println("Registrando e finalizando compras...");
                finalizar();
                break;
            case 0:
                System.out.println("Voltando menu principal...");
                break;
            default:
                System.err.println("Erro: Escolha a opcao correta!!");                
        }
    
    }
    private static Cliente loginCompra() {
        Cliente cliente = null;
        boolean x=true;
        while(x==true){
        String cpf=Console.scanString("Qual seu CPF: ");
        if (!repositorioCliente.getInstance().clienteExiste(cpf)) {
                System.err.println("CPF não existente no cadastro.");
            }
            if (repositorioCliente.getInstance().clienteExiste(cpf)){
                cliente=repositorioCliente.getInstance().buscarCliente(cpf);
                x=false;                
                System.out.println("CPF encontrado com sucesso."
                        + "\nPreparando menu de compras para o Sr(a) "+cliente.getNome());
                System.out.println("\n---------------------------");
                
            }
        }
        return cliente;
    }

    private static void listarEComprar() {
        boolean x=true;
        while(x==true){
            menuProd.listarProd();        
            int compra=Console.scanInt("Qual dos produtos vc quer levar?\nDigite o codigo do Poduto: ");
            if(!repositorioProduto.getInstance().produtoExiste(compra)){
                System.err.println("Codigo do produto inexistente\nTente novamente");
            }
            if(repositorioProduto.getInstance().produtoExiste(compra)){
                Produto produto=repositorioProduto.getInstance().buscarProduto(compra);
                int quantidade=Console.scanInt("Quantas unidades desse produto vc deseja:");
                repositorioCompra.getInstance().add(new Compra(produto,quantidade));
                x=false;
            }
        }
       menuComprar();
    }

    private static void listaDoCarrinho() {
        double totalFinal=0.0;
        
        if(repositorioCompra.getInstance().estaVazio()){
            System.out.println("----------------------------------------------");        
            System.err.println("Nao ha Produtos dentro do carrinho cadastrados");
            System.out.println("--------------------------------------------\n");
        }
        else{
            System.out.println(String.format("%-20s", "|Nome do Produto") + "\t"
                    + String.format("%-10s", "|Quantidade") + "\t"
                    + String.format("%-20s", "|Total a pagar por esses produtos"));
            for (Compra compra : repositorioCompra.getInstance().getCompra()) {
                System.out.println(String.format("%-20s","|" +  compra.getNome()) + "\t"
                        + String.format("%-10s", "|" + compra.getNumeroProd()) + "\t"
                        + String.format("%-20s", "|" + compra.getTotal()));
                totalFinal +=compra.getTotal();
            }
            System.out.println("\nPreco final a ser debitado pelo consumidor é " +totalFinal+ "$ .");
            System.out.println("--------------------------------------\n");
        }
        menuComprar();        
    }

    private static void finalizar() {
        LocalDate dataHora = LocalDate.now();
        System.out.println(String.format("%-30s","|N°"+codCompras)+String.format("%-40s","|DATA: "+dataHora));
        System.out.println(String.format("%-30s","|NOME DO CLIENTE: "+cliente.getNome())+String.format("%-30s","|CPF :"+cliente.getCpf()));
        double total=listaParaFinalizar();
        System.err.println("===VERIFICANDO SE CLIENTE TEM SALDO SUFICIENTE===");
        if(cliente.getSaldo()<total){
            System.err.println("CLIENTE NAO TEM SALDO SUFICIENTE");            
            reset();
        }
        if(cliente.getSaldo()>=total){
            System.out.println("SALDO SUFICIENTE.\nREALIZANDO DEBITO");
            cliente.setDescSaldo(total);
            System.out.println("DEBITO REALIZADO COM SUCESSO!!!");
            
            
            
            reset();
            codCompras++;                
        }
        
    }
    private static double listaParaFinalizar() {
        double totalFinal=0.0;
        
        if(repositorioCompra.getInstance().estaVazio()){
            System.out.println("----------------------------------------------");        
            System.err.println("Nao ha Produtos dentro do carrinho cadastrados");
            System.out.println("--------------------------------------------\n");
        }
        else{
            System.out.println(String.format("%-20s", "|Nome do Produto") + "\t"
                    + String.format("%-10s", "|Quantidade") + "\t"
                    + String.format("%-20s", "|Total a pagar por esses produtos"));
            for (Compra compra : repositorioCompra.getInstance().getCompra()) {
                System.out.println(String.format("%-20s","|" +  compra.getNome()) + "\t"
                        + String.format("%-10s", "|" + compra.getNumeroProd()) + "\t"
                        + String.format("%-20s", "|" + compra.getTotal()));
                totalFinal +=compra.getTotal();
            }
            System.out.println("\nPreco final a ser debitado pelo consumidor é " +totalFinal+ "$ .");
            System.out.println("--------------------------------------\n");
            return totalFinal;
        }       
        return totalFinal;
    }

    private static void reset() {
        cliente=null;
        repositorioCompra.zeraRepositorio();
    }
    
}
