package org.example;

import java.math.BigDecimal;

import org.example.Cliente.Tipo;

//classe que define os atributos comuns a todos os Clientes, independente do tipo
public abstract class Conta {

    protected BigDecimal saldo;
    private int agencia;
    private int numero;
    private Cliente titular;

    public Conta(int agencia, int numero, Cliente titular) {
        setAgencia(agencia);
        setNumero(numero);
        this.saldo = new BigDecimal(0);
        setTitular(titular);
    }

    public abstract void deposita(BigDecimal valor);

    // Método sacar segue a regra de negócio, onde há cobrança de uma taxa de 0.5%
    // para cada saque ou transferência realizada por cliente PJ.
    // O método verifica se o tipo do titular da conta é PJ, se positivo, acrescenta
    // o valor da taxa no desconto de saldo.
    public boolean saca(BigDecimal valor) {
        if (this.titular.getTipo().equals(Tipo.PJ)) {
            if (this.saldo.compareTo(valor.multiply(new BigDecimal(1.005))) >= 0) {
                this.saldo = this.saldo.subtract(valor.multiply(new BigDecimal(1.005)));
                return true;
            } else {
                return false;
            }
        }
        if (this.saldo.compareTo(valor) >= 0) {
            this.saldo = this.saldo.subtract(valor);
            return true;
        } else {
            return false;
        }
    }

    public boolean transfere(BigDecimal valor, Conta destino) {
        if (this.saca(valor)) {
            destino.deposita(valor);
            return true;
        } else {
            return false;
        }
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public int getNumero() {
        return this.numero;
    }

    // metodo que retorna uma mensagem de exceção para garantir que o parametro seja
    // válido
    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Número de conta inválido. Não foi possível concluir a operação.");
        }
        this.numero = numero;
    }

    public int getAgencia() {
        return this.agencia;
    }

    // metodo que retorna uma mensagem de exceção para garantir que o parametro CPF
    // seja válido
    public void setAgencia(int agencia) {
        if (agencia <= 0) {
            throw new IllegalArgumentException("Número de agência inválido. Não foi possível concluir a operação.");
        }
        this.agencia = agencia;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public Cliente getTitular() {
        return this.titular;
    }

}
