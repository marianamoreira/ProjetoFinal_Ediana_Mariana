package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacao {

    public static void cadastrarCliente(){

    }
    public static void abrirConta(){}
    public static void sacar(){}
    public static void depositar(){}
    public static void transferir(){}
    public static void investir(){}
    public static void consultarSaldo(){}
    public static void main(String[] args) {
        ArrayList<ClientePF> clientesPF = new ArrayList<>();
        ArrayList<ClientePJ> clientesPJ = new ArrayList<>();
        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>();
        ArrayList<ContaPoupanca> contasPoupanca = new ArrayList<>();
        ArrayList<ContaInvestimento> contasInvestimento = new ArrayList<>();
        int opcao;
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        while (!sair){
            System.out.println("Painel de opções");
            System.out.println("1. Cadastrar cliente PF");
            System.out.println("2. Cadastrar cliente PJ");
            System.out.println("3. Abrir conta corrente");
            System.out.println("4. Abrir conta poupança");
            System.out.println("5. Abrir conta investimento");
            System.out.println("6. Sacar");
            System.out.println("7. Depositar");
            System.out.println("8. Transferir");
            System.out.println("9. Investir");
            System.out.println("10. Consultar saldo");
            System.out.println("11. Sair");
            System.out.print("Digite a opção solicitada: ");
            opcao=sc.nextInt();
            switch (opcao){
                case 1: cadastrarCliente();
                    break;
                case 3: abrirConta();
                    break;
                case 6: sacar();
                    break;
                case 7: depositar();
                    break;
                case 8: transferir();
                    break;
                case 9: investir();
                    break;
                case 10: consultarSaldo();
                    break;
                case 11:
                    sair=true;
                    System.out.println("\nObrigada por utilizar o Banco EM. Até a próxima! ");
                    break;
                default:
                    System.out.println("Opção inexistente. Digite uma opção válida.\n");
                    break;
            }
        }
    }
}