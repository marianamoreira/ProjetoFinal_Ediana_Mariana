package org.example;

public class ClientePF extends Cliente {
    private String cpf;
    private String profissao;

    public ClientePF(String nome, String telefone, Tipo tipo, String cpf, String profissao) {
        super(nome, telefone, Tipo.PF);
        setCpf(cpf);
        this.profissao = profissao;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        if (cpf.length()<11){
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
