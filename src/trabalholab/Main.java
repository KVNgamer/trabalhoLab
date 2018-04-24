
package trabalholab;

import model.Cliente;
import util.Console;
import java.util.ArrayList;
import menus.*;
import model.Compra;
import model.Produto;
import repositorio.repositorioCliente;
import repositorio.repositorioCompra;
import repositorio.repositorioProduto;

/**
 *
 * @author KEVIN
 */
public class Main {
   
    
    public static void main(String[] args) {
        
        povoarRepositorios();
        MenuPrincipal();
        
    }

    private static void MenuPrincipal() {
        while(true){         

            System.out.println("Menu: ");
            System.out.println("    1- Menu para administrar Cliente");
            System.out.println("    2- Menu para administrar Produto");
            System.out.println("    3- Menu para operaçoes Cliente");
            System.out.println("    4- Menu para Comprar produtos");
            System.out.println("    0- Sair");
            int opcao = Console.scanInt("Digite a opcao: ");
            System.out.println("\n==ANALIZANDO==\n");
            switch(opcao){
                case 1:
                    System.out.println("Indo menu Cliente ...");
                    menuCli.menuCliente();
                    break;
                case 2:
                    System.out.println("Indo menu Produto ...");
                    menuProd.menuProduto();
                    break;
                case 3:
                    if(repositorioCliente.getInstance().estaVazio()){
                        System.out.println("\n----------------------------------------------------");        
                        System.out.println("Nao ha Clientes cadastrados para realizar as operaçoes");
                        System.out.println("----------------------------------------------------\n");
                        break;
                    }
                    System.out.println("Indo para menu operaçoes Cliente ...");
                    menuOpCli.menuOpCliente();
                    break;
                case 4:
                    if(repositorioCliente.getInstance().estaVazio()){
                        System.out.println("\n-------------------------------------------------------");        
                        System.err.println("Nao ha Clientes cadastrados para poderem realizar compras");
                        System.out.println("-------------------------------------------------------\n");
                        break;
                    }
                    if(repositorioProduto.getInstance().estaVazio()){
                        System.out.println("\n-------------------------------------------------------");        
                        System.err.println("Nao ha Produtos cadastrados para poderem realizar compras");
                        System.out.println("-------------------------------------------------------\n");
                        break;
                    }
                    System.out.println("Indo para menu Comprar produtos ...");
                    menuCompra.menuComprar();
                    break;
                case 5:
                    System.out.println("Relatorios ...");
                    menuCli.menuCliente();
                    break;
                case 0:
                    System.out.println("Saindo do Sistema...");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Erro: Escolha a opcao correta!!");                
            }

        }
    }

    private static void povoarRepositorios() {
        
        
        repositorioProduto.getInstance().add(new Produto(15,"repolho",2.10));
        repositorioCliente.getInstance().add(new Cliente("862","kevin", "kevin@gmail.com", 214, 5000));
        repositorioProduto.getInstance().add(new Produto(18,"brocules",5.81));
        repositorioCliente.getInstance().add(new Cliente("123","joao", "joao@gmail.com", 104, 3000));
        repositorioProduto.getInstance().add(new Produto(19,"alface",1.37));
        repositorioCliente.getInstance().add(new Cliente("456","pedro", "pedro@gmail.com", 52, 1000));
        
    }

}