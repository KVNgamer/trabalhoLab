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
    String cpf,nome,email;
    int nuconta;
    double saldo;

    public Cliente(String cpf,String nome,String email,int nuconta,double saldo) {
        this.cpf = cpf;
        this.nome= nome;
        this.email= email;
        this.nuconta = nuconta;
        this.saldo = saldo;
        
    }

    public String getNome() {
       return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }
     public String getEmail() {
        return this.email;
    }
}
