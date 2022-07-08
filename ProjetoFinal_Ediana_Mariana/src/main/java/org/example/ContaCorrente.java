package org.example;

public class ContaCorrente extends Conta {

    public ContaCorrente(int agencia, int numero, Cliente titular) {
        super(agencia, numero, titular);
    }

    @Override
    public void deposita(double valor) {
        super.saldo += valor;
    }

}

