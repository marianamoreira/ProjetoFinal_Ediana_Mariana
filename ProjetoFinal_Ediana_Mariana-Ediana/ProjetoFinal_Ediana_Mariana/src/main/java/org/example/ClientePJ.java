package org.example;

//classe que estende a classe Cliente para especificar os atributos do Cliente Pessoa Física
public class ClientePJ extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private String ramoAtuacao;

    // construtor específico para os Clientes do Tipo Pessoa Jurídica
    public ClientePJ(String nome, String telefone, Tipo tipo, String cnpj, String razaoSocial, String ramoAtuacao) {
        super(nome, telefone, Tipo.PJ);
        setCnpj(cnpj);
        this.razaoSocial = razaoSocial;
        this.ramoAtuacao = ramoAtuacao;
    }

    // métodos de encapsulamento...
    public String getCnpj() {
        return cnpj;
    }

    // metodo que retorna uma mensagem de exceção para garantir que o parametro CNPJ
    // seja válido
    public void setCnpj(String cnpj) {
        if (cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido. Não foi possível concluir a operação.");
        }
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getRamoAtuacao() {
        return ramoAtuacao;
    }

    public void setRamoAtuacao(String ramoAtuacao) {
        this.ramoAtuacao = ramoAtuacao;
    }
}
