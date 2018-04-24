/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Compra;

public class repositorioCompra {
 private List<Compra> listaCompra;
    private static repositorioCompra instance = null;
    
    private repositorioCompra() {
        listaCompra = new ArrayList<Compra>();
    }

    public static repositorioCompra getInstance() {
        if(instance == null) instance = new repositorioCompra();
        return instance;
    }
    public static void zeraRepositorio(){
        instance =null;    
    }
    

    public boolean add(Compra compra) {
        return (listaCompra.add(compra));
    }
    
    public boolean estaVazio(){
        return listaCompra.isEmpty();
    }

    public List<Compra> getCompra() {
        return listaCompra;
    }

    public boolean compraExiste() {
        for (Compra compra : listaCompra) {
            if (compra.getNumeroProd()>0) {
                return true;
            }
        }
        return false;
    }

    public Compra buscarCompra(int cod) {
        for (Compra compra : listaCompra) {
            if (compra.getProduto().getCod()==cod) {
                return compra;
           }
        }
        return null;
    }
} 