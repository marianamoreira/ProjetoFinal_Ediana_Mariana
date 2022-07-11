package org.example;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {

    public ContaCorrente(int agencia, int numero, Cliente titular) {
        super(agencia, numero, titular);
    }

    @Override
    public void deposita(BigDecimal valor) {
        super.saldo = super.saldo.add(valor);
    }

}
