package org.example;

public abstract class Cliente {

    private String nome;
    private String telefone;
    private Tipo tipo;
    public enum Tipo {
        PF,
        PJ;
    }

    public Cliente(String nome, String telefone, Tipo tipo) {
        this.nome = nome;
        setTelefone(telefone);
        this.tipo = tipo;
    }

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

    public void setTelefone(String telefone) {
        if (telefone.length()<10){
            throw new IllegalArgumentException("Número de telefone inválido. Não foi possível concluir a operação.");
        }
        this.telefone = telefone;
    }
}

