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
public class Conta {
    private int id;
    private double saldo;

    public Conta(int nuconta, double saldo) {
       this.id=nuconta;
       this.saldo=saldo;
    }
    
    
    
    public double getSaldo() {
        return this.saldo;
    }
    public void setSaldo(double saldo){
        this.saldo += saldo;
    
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    
    }
    public void setDescSaldo(double saldo) {
        this.saldo -= saldo;
    }
    
    
    
    
    
}



