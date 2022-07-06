package org.example;

public class ClientePJ extends Cliente{
    private String cnpj;
    private String razaoSocial;
    private String ramoAtuacao;

    public ClientePJ(String nome, String telefone, Tipo tipo, String cnpj, String razaoSocial, String ramoAtuacao) {
        super(nome, telefone, Tipo.PJ);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.ramoAtuacao = ramoAtuacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj.length()<14){
            System.out.println("CNPJ inválido.");
            return;
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
