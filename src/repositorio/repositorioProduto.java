/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class repositorioProduto {
    private List<Produto> listaProdutos;
    private static repositorioProduto instance = null;
    
    private repositorioProduto() {
        listaProdutos = new ArrayList<Produto>();
    }

    public static repositorioProduto getInstance() {
        if(instance == null) instance = new repositorioProduto();
        return instance;
    }
    
    

    public boolean add(Produto produto) {
        return (listaProdutos.add(produto));
    }
    
    public boolean estaVazio(){
        return listaProdutos.isEmpty();
    }

    public List<Produto> getProduto() {
        return listaProdutos;
    }

    public boolean produtoExiste(int cod) {
        for (Produto produto : listaProdutos) {
            if (produto.getCod()==cod) {
                return true;
            }
        }
        return false;
    }

    public Produto buscarProduto(int cod) {
        for (Produto produto : listaProdutos) {
            if (produto.getCod()==cod) {
                return produto;
           }
        }
        return null;
    }
} 