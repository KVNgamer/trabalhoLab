
package trabalholab;

import model.Cliente;
import util.Console;
import java.util.ArrayList;
import menus.*;

/**
 *
 * @author KEVIN
 */
public class Main {
   
    
    public static void main(String[] args) {
       
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
                    System.out.println("Indo para menu operaçoes Cliente ...");
                    menuOpCli.menuOpCliente();
                    break;
                case 4:
                    System.out.println("Indo para menu Comprar produtos ...");
                    menuCompra.menuComprar();
                    break;
                case 0:
                    System.out.println("Saindo do Sistema...");
                    break;
                default:
                    System.err.println("Erro: Escolha a opcao correta!!");                
            }

        }
    }

}