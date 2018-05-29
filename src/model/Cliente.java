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
public class Cliente {
    private String cpf,nome,email;    
    private Conta conta;

    public Cliente(String cpf,String nome,String email,int nuconta,double saldo) {
        this.cpf = cpf;
        this.nome= nome;
        this.email= email;
        conta = new Conta(nuconta,saldo);
        
    }

    public Cliente(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome= nome;
        this.email= email;       
        
    }

    public String getNome() {
       return this.nome;
    }
    public void setNome(String nome){
        this.nome=nome;
    }

     public double getSaldo() {
       return this.conta.getSaldo();
    }
     
    public String getCpf() {
        return this.cpf;
    }
     public String getEmail() {
        return this.email;
    }
      public void setEmail(String email){
        this.email=email;
    }
     
    public void setSaldo(double saldo){
    this.conta.setSaldo(saldo);
    
    }

    public void setDescSaldo(double saldo) {
        this.conta.setDescSaldo(saldo);
    }
}
