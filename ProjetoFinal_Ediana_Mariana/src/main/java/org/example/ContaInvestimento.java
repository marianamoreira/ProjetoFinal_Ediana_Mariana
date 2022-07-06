package org.example;

public class ContaInvestimento extends Conta {

    //taxa de rendimento: 0,5% a cada depósito, 2,5% para PJ
    public ContaInvestimento(int agencia, int numero) {
        super(agencia, numero);
    }
    @Override
    public void deposita(double valor) {
        if (this.getTitular().getTipo().equals("PJ")){

                super.saldo += valor*1.025;
        }
        else{
            super.saldo += valor*1.005;
        }
    }

}
