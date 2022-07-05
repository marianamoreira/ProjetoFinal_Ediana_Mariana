package org.example;

import java.util.Scanner;

public class Aplicacao {

    public static void cadastrarCliente(){}
    public static void abrirConta(){}
    public static void sacar(){}
    public static void depositar(){}
    public static void transferir(){}
    public static void investir(){}
    public static void consultarSaldo(){}
    public static void main(String[] args) {
        int opcao;
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        while (!sair){
            System.out.println("Painel de opções");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Abrir conta");
            System.out.println("3. Sacar");
            System.out.println("4. Depositar");
            System.out.println("5. Transferir");
            System.out.println("6. Investir");
            System.out.println("7. Consultar saldo");
            System.out.println("8. Sair");
            System.out.print("Digite a opção solicitada: ");
            opcao=sc.nextInt();
            switch (opcao){
                case 1: cadastrarCliente();
                    break;
                case 2: abrirConta();
                    break;
                case 3: sacar();
                    break;
                case 4: depositar();
                    break;
                case 5: transferir();
                    break;
                case 6: investir();
                    break;
                case 7: consultarSaldo();
                    break;
                case 8:
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