package org.example;

public abstract class Conta {

    protected double saldo;
    private int agencia;
    private int numero;
    private Cliente titular;

    public Conta(int agencia, int numero, Cliente titular) {
        setAgencia(agencia);
        setNumero(numero);
        this.saldo=0;
        setTitular(titular);
    }

    public abstract void deposita(double valor);

    public boolean saca(double valor) {
        if (this.titular.getTipo().equals("PJ")){
            if(this.saldo >= valor*1.05) {
                this.saldo -= valor*1.05;
                return true;
            } else {
                return false;
            }
        }
        if(this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean transfere(double valor, Conta destino) {
        if(this.saca(valor)) {
            destino.deposita(valor);
            return true;
        } else {
            return false;
        }
    }

    public double getSaldo(){
        return this.saldo;
    }

    public int getNumero(){
        return this.numero;
    }

    public void setNumero(int numero){
        if(numero <= 0) {
            System.out.println("Número de agência inválido");
            return;
        }
        this.numero = numero;
    }

    public int getAgencia(){
        return this.agencia;
    }

    public void setAgencia(int agencia){
        if(agencia <= 0) {
            System.out.println("Número de agência inválido");
            return;
        }
        this.agencia = agencia;
    }

    public void setTitular(Cliente titular){
        this.titular = titular;
    }

    public Cliente getTitular(){
        return this.titular;
    }

}
