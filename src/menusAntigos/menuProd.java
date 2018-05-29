/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menusAntigos;

import java.util.InputMismatchException;
import model.Produto;
import repositorio.repositorioCliente;
import repositorio.repositorioProduto;
import util.Console;

/*
 *
 * @author KEVIN
 *
 */

public class menuProd {
    private static int k=1;
    public static void menuProduto() {
        try{
            System.out.println("Menu: ");
            System.out.println("    1- Adicionar Produto");
            System.out.println("    2- Listar Produto");
            System.out.println("    0- Voltar");
            int opcao = Console.scanInt("Digite a opcao: ");
            System.out.println("\n==ANALIZANDO==\n");
            switch(opcao){
                case 1:
                    System.out.println("Adicionando Produto...");
                    criaProd();
                    break;
                case 2:
                    System.out.println("Listando Produtos...");
                    listarProd();
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
    
    private static void criaProd() {
        boolean verificado=true;
        int cod =k;      
        double preco =0.0;
        String nome="";
        
        if (repositorioProduto.getInstance().produtoExiste(cod)) {
            System.err.println("Codigo já existente no cadastro");
            verificado=false;
        }
        
        if(verificado == true){
            nome = Console.scanString("Nome: ");
            if(nome.replaceAll(" ","").length()==0){
            verificado= false;
            System.err.println("Nome invalido,tente novamente");
            }
        }
        
        if(verificado == true){
             preco = Console.scanDouble("Preço: ");
            if(preco<0){
            verificado= false;
            System.err.println("Preco invalido,tente novamente");
            } 
        }
        
        if(verificado == true){
            repositorioProduto.getInstance().add(new Produto(cod,nome,preco));
            System.out.println("Produto " + nome + " cadastrado com sucesso!");            
            k++;
        }
    
    }     
    
    public static void listarProd() {
        

        if(repositorioProduto.getInstance().estaVazio()){
            System.out.println("-----------------------------");        
            System.out.println("Nao ha produtos cadastrados");
            System.out.println("-----------------------------\n");
        }
        else{
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-7s", "|codigo") + "\t"
                             + String.format("%-20s", "|Nome") + "\t"
                             + String.format("%-10s", "|Preço"));
            for (Produto produto : repositorioProduto.getInstance().getProduto()) {
                System.out.println(String.format("%-7s", "|" + produto.getCod()) + "\t"
                                 + String.format("%-20s", "|" + produto.getNome()) + "\t"
                                 + String.format("%-10s", "|" + produto.getPreco()));
            }
            System.out.println(" ");
        }
    }     
}
