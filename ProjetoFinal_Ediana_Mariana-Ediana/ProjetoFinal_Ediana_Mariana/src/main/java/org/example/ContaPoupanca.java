package org.example;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(int agencia, int numero, Cliente titular) {
        super(agencia, numero, titular);
    }


    @Override
    public void deposita(BigDecimal valor) {
        super.saldo=super.saldo.add(valor);
    }

}