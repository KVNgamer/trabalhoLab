/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import model.Produto;
import repositorio.repositorioCliente;
import repositorio.repositorioProduto;
import util.Console;

/**
 *
 * @author KEVIN
 */
public class menuProd {
 private static int k=1;
    public static void menuProduto() {
        System.out.println("Menu: ");
        System.out.println("    1- Adicionar Produto");
        System.out.println("    2- Listar Produto");
        System.out.println("    0- Sair");
        int opcao = Console.scanInt("Digite a opcao: ");
        System.out.println("\n==ANALIZANDO==\n");
        switch(opcao){
            case 1:
                System.out.println("Adicionando ...");
                criaProd();
                break;
            case 2:
                System.out.println("Listando ...");
                listarProd();
                break;
            case 0:
                System.out.println("Saindo do Sistema...");
                break;
            default:
                System.err.println("Erro: Escolha a opcao correta!!");                
        }
    }
    
    private static void criaProd() {
        int cod =k;
        k++;
        if (repositorioProduto.getInstance().produtoExiste(cod)) {
            System.out.println("Codigo já existente no cadastro");
        } else {
            String nome = Console.scanString("Nome: ");
            double preco = Console.scanDouble("Preço: ");
            repositorioProduto.getInstance().add(new Produto(cod,nome,preco));
            System.out.println("Produto " + nome + " cadastrado com sucesso!");            
            
            }
        } 
    
    
    private static void listarProd() {
        

        if(repositorioProduto.getInstance().estaVazio()){
            System.out.println("-----------------------------");        
            System.out.println("Nao ha produtos cadastrados");
            System.out.println("-----------------------------\n");
        }
        else{
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-8s", "codigo") + "\t"
                             + String.format("%-20s", "|Nome") + "\t"
                             + String.format("%-10s", "|Preço"));
            for (Produto produto : repositorioProduto.getInstance().getProduto()) {
                System.out.println(String.format("%-8s", produto.getCod()) + "\t"
                                 + String.format("%-20s", "|" + produto.getNome()) + "\t"
                                 + String.format("%-10s", "|" + produto.getPreco())+'\n');
            }
        }
    }     
}
