package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacao {

    public static void cadastrarClientePF(ArrayList<ClientePF> clientesPF) {
        Scanner sc = new Scanner(System.in);
        String nome, cpf, profissao, telefone;

        System.out.print("Digite o CPF do cliente:");
        cpf = sc.nextLine();
        if (clientesPF.size() != 0) {
            for (ClientePF cliente : clientesPF) {
                if (cliente.getCpf().equals(cpf)) {
                    throw new IllegalArgumentException("Já existe cliente com esse CPF. Não foi possível finalizar a operação.");
                }
            }
        }
        System.out.print("Digite o nome do cliente:");
        nome = sc.nextLine();
        System.out.print("Digite o telefone do cliente, com DDD:");
        telefone = sc.nextLine();
        System.out.print("Digite a profissão do cliente:");
        profissao = sc.nextLine();

        try {
            clientesPF.add(new ClientePF(nome, telefone, Cliente.Tipo.PF, cpf, profissao));

        } catch (IllegalArgumentException e) {
            System.out.println("Erro na solicitação: " + e.getMessage());
        }
    }

    public static void cadastrarClientePJ(ArrayList<ClientePJ> clientesPJ) {
        Scanner sc = new Scanner(System.in);
        String nome, cnpj, profissao, telefone, razaoSocial, ramoAtuacao;

        System.out.print("Digite o CNPJ do cliente:");
        cnpj = sc.nextLine();
        if (clientesPJ.size() != 0) {
            for (ClientePJ cliente : clientesPJ) {
                if (cliente.getCnpj().equals(cnpj)) {
                    throw new IllegalArgumentException("Já existe cliente com esse CNPJ. Não foi possível concluir a operação.");
                }
            }
        }
        System.out.print("Digite o nome do cliente:");
        nome = sc.nextLine();
        System.out.print("Digite o telefone do cliente, com DDD:");
        telefone = sc.nextLine();
        System.out.print("Digite a razão social do cliente:");
        razaoSocial = sc.nextLine();
        System.out.print("Digite o ramo de atuação do cliente:");
        ramoAtuacao = sc.nextLine();
        try {
            clientesPJ.add(new ClientePJ(nome, telefone, Cliente.Tipo.PJ, cnpj, razaoSocial, ramoAtuacao));

        } catch (IllegalArgumentException e) {
            System.out.println("Erro na solicitação: " + e.getMessage());
        }

    }

    public static void abrirContaCorrente(ArrayList<ClientePF> clientesPF, ArrayList<ClientePJ> clientesPJ, ArrayList<ContaCorrente> contasCorrente) {
        Scanner sc = new Scanner(System.in);
        String nome, cpfCnpj, profissao, telefone;
        int opcao, agencia, numeroConta;
        opcao = agencia = numeroConta = 0;
        boolean existeCliente = false;

        System.out.println("\nPara qual tipo de cliente você deseja abrir uma conta?");
        System.out.println("1. Cliente PF");
        System.out.println("2. Cliente PJ");
        System.out.print("Digite a opção solicitada: ");
        opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                if (clientesPF.size() == 0)
                    throw new IllegalArgumentException("Não existe cliente PF cadastrado no banco.");
                System.out.print("Digite o CPF do cliente: ");
                cpfCnpj = sc.nextLine();
                for (ClientePF cli : clientesPF) {
                    if (cli.getCpf().equals(cpfCnpj)) {
                        existeCliente = true;
                        System.out.print("Digite o número da agência para cadastro: ");
                        agencia = sc.nextInt();
                        System.out.print("Digite o número da conta para cadastro: ");
                        numeroConta = sc.nextInt();
                        try {
                            contasCorrente.add(new ContaCorrente(agencia, numeroConta, cli));

                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro na solicitação: " + e.getMessage());
                        }
                        break;
                    }
                }
                if (!existeCliente)
                    throw new IllegalArgumentException("Não existe cliente com este CPF.");
                break;
            case 2:
                if (clientesPJ.size() == 0)
                    throw new IllegalArgumentException("Não existe cliente PJ cadastrado no banco.");

                System.out.print("Digite o CNPJ do cliente: ");
                cpfCnpj = sc.nextLine();
                for (ClientePJ cli : clientesPJ) {
                    if (cli.getCnpj().equals(cpfCnpj)) {
                        existeCliente = true;
                        System.out.print("Digite o número da agência para cadastro: ");
                        agencia = sc.nextInt();
                        System.out.print("Digite o número da conta para cadastro: ");
                        numeroConta = sc.nextInt();
                        try {
                            contasCorrente.add(new ContaCorrente(agencia, numeroConta, cli));

                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro na solicitação: " + e.getMessage());
                        }
                        break;
                    }
                }
                if (!existeCliente)
                    throw new IllegalArgumentException("Não existe cliente com este CNPJ.");
                break;
            default:
                System.out.println("Opção inexistente. Digite uma opção válida da próxima vez.\n");
                break;

        }


    }

    public static void abrirContaPoupanca(ArrayList<ClientePF> clientesPF, ArrayList<ContaPoupanca> contasPoupanca) {
        Scanner sc = new Scanner(System.in);
        String nome, cpf, profissao, telefone;
        int opcao, agencia, numeroConta;
        opcao = agencia = numeroConta = 0;
        boolean existeCliente = false;

        if (clientesPF.size() == 0)
            throw new IllegalArgumentException("Não existe cliente PF cadastrado no banco.");
        System.out.print("Digite o CPF do cliente: ");
        cpf = sc.nextLine();
        for (ClientePF cli : clientesPF) {
            if (cli.getCpf().equals(cpf)) {
                existeCliente = true;
                System.out.print("Digite o número da agência para cadastro: ");
                agencia = sc.nextInt();
                System.out.print("Digite o número da conta para cadastro: ");
                numeroConta = sc.nextInt();
                try {
                    contasPoupanca.add(new ContaPoupanca(agencia, numeroConta, cli));
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro na solicitação: " + e.getMessage());
                }
                break;
            }
        }
        if (!existeCliente)
            throw new IllegalArgumentException("Não existe cliente com este CPF.");
    }

    public static void abrirContaInvestimento(ArrayList<ClientePF> clientesPF, ArrayList<ClientePJ> clientesPJ, ArrayList<ContaInvestimento> contasInvestimento) {
        Scanner sc = new Scanner(System.in);
        String nome, cpfCnpj, profissao, telefone;
        int opcao, agencia, numeroConta;
        opcao = agencia = numeroConta = 0;
        boolean existeCliente = false;

        System.out.println("\nPara qual tipo de cliente você deseja abrir uma conta?");
        System.out.println("1. Cliente PF");
        System.out.println("2. Cliente PJ");
        System.out.print("Digite a opção solicitada: ");
        opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                if (clientesPF.size() == 0)
                    throw new IllegalArgumentException("Não existe cliente PF cadastrado no banco.");
                System.out.print("Digite o CPF do cliente: ");
                cpfCnpj = sc.nextLine();
                for (ClientePF cli : clientesPF) {
                    if (cli.getCpf().equals(cpfCnpj)) {
                        existeCliente = true;
                        System.out.print("Digite o número da agência para cadastro: ");
                        agencia = sc.nextInt();
                        System.out.print("Digite o número da conta para cadastro: ");
                        numeroConta = sc.nextInt();
                        try {
                            contasInvestimento.add(new ContaInvestimento(agencia, numeroConta, cli));

                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro na solicitação: " + e.getMessage());
                        }
                        break;
                    }
                }
                if (!existeCliente)
                    throw new IllegalArgumentException("Não existe cliente com este CPF.");
                break;
            case 2:
                if (clientesPJ.size() == 0)
                    throw new IllegalArgumentException("Não existe cliente PJ cadastrado no banco.");

                System.out.print("Digite o CNPJ do cliente: ");
                cpfCnpj = sc.nextLine();
                for (ClientePJ cli : clientesPJ) {
                    if (cli.getCnpj().equals(cpfCnpj)) {
                        existeCliente = true;
                        System.out.print("Digite o número da agência para cadastro: ");
                        agencia = sc.nextInt();
                        System.out.print("Digite o número da conta para cadastro: ");
                        numeroConta = sc.nextInt();
                        try {
                            contasInvestimento.add(new ContaInvestimento(agencia, numeroConta, cli));

                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro na solicitação: " + e.getMessage());
                        }
                        break;
                    }
                }
                if (!existeCliente)
                    throw new IllegalArgumentException("Não existe cliente com este CNPJ.");
                break;
            default:
                System.out.println("Opção inexistente. Digite uma opção válida da próxima vez.\n");
                break;

        }
    }

    public static void sacar() {
    }

    public static void depositar() {
    }

    public static void transferir() {
    }

    public static void investir() {
    }

    public static void consultarSaldo() {
    }

    public static void main(String[] args) {
        ArrayList<ClientePF> clientesPF = new ArrayList<>();
        ArrayList<ClientePJ> clientesPJ = new ArrayList<>();
        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>();
        ArrayList<ContaPoupanca> contasPoupanca = new ArrayList<>();
        ArrayList<ContaInvestimento> contasInvestimento = new ArrayList<>();
        int opcao;
        int tamanho;
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        while (!sair) {
            System.out.println("\nPainel de opções");
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
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    try {
                        cadastrarClientePF(clientesPF);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Solicitação finalizada.");
                    break;
                case 2:
                    try {
                        cadastrarClientePJ(clientesPJ);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Solicitação finalizada.");
                    break;
                case 3:
                    try {
                        abrirContaCorrente(clientesPF, clientesPJ, contasCorrente);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Solicitação finalizada.");
                    break;
                case 4:
                    try {
                        abrirContaPoupanca(clientesPF, contasPoupanca);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Solicitação finalizada.");
                    break;
                case 5:
                    try {
                        abrirContaInvestimento(clientesPF, clientesPJ, contasInvestimento);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Solicitação finalizada.");
                    break;
                case 6:
                    sacar();
                    break;
                case 7:
                    depositar();
                    break;
                case 8:
                    transferir();
                    break;
                case 9:
                    investir();
                    break;
                case 10:
                    consultarSaldo();
                    break;
                case 11:
                    sair = true;
                    System.out.println("\nObrigada por utilizar o Banco EM. Até a próxima! ");
                    break;
                default:
                    System.out.println("Opção inexistente. Digite uma opção válida.\n");
                    break;
            }
        }
    }
}