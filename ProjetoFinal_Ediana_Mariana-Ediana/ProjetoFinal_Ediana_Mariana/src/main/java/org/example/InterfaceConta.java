package org.example;

interface InterfaceConta {

    void deposita(double valor);
    boolean saca(double valor);
    boolean transfere(double valor, Conta destino);
    
}
