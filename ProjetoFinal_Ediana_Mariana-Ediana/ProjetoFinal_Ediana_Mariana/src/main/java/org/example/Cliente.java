package org.example;

//classe que define os atributos comuns a todos os Clientes, independente do tipo
public abstract class Cliente {

    private String nome;
    private String telefone;
    private Tipo tipo;

    public enum Tipo {
        PF,
        PJ;
    }

    // construtor de Cliente
    public Cliente(String nome, String telefone, Tipo tipo) {
        this.nome = nome;
        setTelefone(telefone);
        this.tipo = tipo;
    }

    // metodos para encapsulamento dos atributos da classe
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    // metodo que retorna uma mensagem de exceção para garantir que o parametro seja válido
    public void setTelefone(String telefone) {
        if (telefone.length() < 10 || telefone.length() > 11) {
            throw new IllegalArgumentException("Número de telefone inválido. Não foi possível concluir a operação.");
        }
        this.telefone = telefone;
    }
}
