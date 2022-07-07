package org.example;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(int agencia, int numero, Cliente titular) {
        super(agencia, numero, titular);
    }


    @Override
    public void deposita(double valor) {
        super.saldo += valor;
    }

}
