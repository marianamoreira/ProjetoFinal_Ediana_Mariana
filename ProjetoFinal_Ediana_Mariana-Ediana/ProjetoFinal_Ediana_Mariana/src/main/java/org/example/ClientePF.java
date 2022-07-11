package org.example;

//classe que estende a classe Cliente para especificar os atributos do Cliente Pessoa Física
public class ClientePF extends Cliente {
    private String cpf;
    private String profissao;

    // construtor específico para os Clientes do Tipo Pessoa Física
    public ClientePF(String nome, String telefone, Tipo tipo, String cpf, String profissao) {
        super(nome, telefone, Tipo.PF);
        setCpf(cpf);
        this.profissao = profissao;
    }

    // métodos de encapsulamento...
    public String getCpf() {
        return cpf;
    }

    // metodo que retorna uma mensagem de exceção para garantir que o parametro CPF
    // seja válido
    public void setCpf(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido. Não foi possível concluir a operação.");
        }
        this.cpf = cpf;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

}
