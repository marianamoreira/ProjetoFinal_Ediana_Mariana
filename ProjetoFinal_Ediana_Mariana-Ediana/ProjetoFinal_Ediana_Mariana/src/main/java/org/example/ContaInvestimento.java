package org.example;

import java.math.BigDecimal;

import org.example.Cliente.Tipo;

public class ContaInvestimento extends Conta {

    // Como o enunciado deixou livre, definimos que a taxa de rendimento será de
    // 0,5% a cada depósito/investimento de clientes PF e de 2,5% a cada
    // depósito/investimento de clientes PJ.

    public ContaInvestimento(int agencia, int numero, Cliente titular) {
        super(agencia, numero, titular);
    }

    // Método deposita (que também pode ser chamado de investir) segue a regra de
    // negócio, onde os rendimentos da conta de investimento de clientes PJ rende 2%
    // a mais que os rendimentos de clientes PF.
    // O método verifica se o tipo do titular da conta é PJ, se positivo, utiliza a
    // taxa do cliente PJ para realizar o rendimento.
    @Override
    public void deposita(BigDecimal valor) {
        if (this.getTitular().getTipo().equals(Tipo.PJ)) {
            valor = valor.multiply(new BigDecimal(1.025));
            super.saldo = super.saldo.add(valor);
        } else {
            valor = valor.multiply(new BigDecimal(1.005));
            super.saldo = super.saldo.add(valor);
        }
    }

}
