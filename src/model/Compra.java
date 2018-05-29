/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author KEVIN
 */
public class Compra {
    private Produto produto;
    private int numeroProd;
    
    
    
    public Compra(Produto produto,int numeroProd){
    this.numeroProd=numeroProd;
    this.produto=produto;
    }
    
    
    
    public int getNumeroProd(){
        return this.numeroProd;
    }
    public Produto getProduto(){
        return this.produto;
    }

    public String getNome() {
        return this.produto.getNome();
    }

    public double getTotal() {
        return (this.numeroProd*this.produto.getPreco());
    }
}
