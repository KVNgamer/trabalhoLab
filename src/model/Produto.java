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
public class Produto {
    private String nome;
    private double preco;
    private int cod;
    
  

    public Produto(int cod, String nome, double preco) {
        this.cod=cod;
        this.nome=nome;
        this.preco=preco;
    
    }

 
    public int getCod(){
    return this.cod;
    }
     public String getNome(){
    return this.nome;
    }
      public double getPreco(){
    return this.preco;
    }
    
    
}
