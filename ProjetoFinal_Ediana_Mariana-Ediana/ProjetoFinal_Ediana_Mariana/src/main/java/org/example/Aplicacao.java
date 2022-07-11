package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacao {

    // Como estamos utilizando apenas o terminal para realizar as entradas e saídas
    // de dados, implementamos os método de limpar console para que as informações
    // fiquem menos confusas e a exibição mais limpa. Ele é chamado no método main.
    public final static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // O método de cadastrar cliente solicita os dados do cliente, faz as
    // devidas validações para garantir que as regras de negócio são seguidas. Após,
    // finaliza a operação.
    public static void cadastrarCliente(ArrayList<ClientePF> clientesPF, ArrayList<ClientePJ> clientesPJ) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        String nome, cpfCNPJ;
        System.out.println("\nCADASTRO DE CLIENTE\n");
        System.out.println("Qual tipo de cliente você deseja cadastrar no banco?");
        System.out.println("1. Cliente PF");
        System.out.println("2. Cliente PJ");
        System.out.print("Digite a opção solicitada: ");
        opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                String profissao, telefone;

                System.out.print("Digite o CPF do cliente: ");
                cpfCNPJ = sc.nextLine();
                if (clientesPF.size() != 0) {
                    for (ClientePF cliente : clientesPF) {
                        if (cliente.getCpf().equals(cpfCNPJ)) {
                            throw new IllegalArgumentException(
                                    "Já existe cliente com esse CPF. Não foi possível finalizar a operação.");
                        }
                    }
                }
                System.out.print("Digite o nome do cliente: ");
                nome = sc.nextLine();
                System.out.print("Digite o telefone do cliente, com DDD: ");
                telefone = sc.nextLine();
                System.out.print("Digite a profissão do cliente: ");
                profissao = sc.nextLine();
                try {
                    clientesPF.add(new ClientePF(nome, telefone, Cliente.Tipo.PF, cpfCNPJ, profissao));
                    System.out.println("Cliente cadastrado com sucesso.");

                } catch (IllegalArgumentException e) {
                    System.out.println("Erro na solicitação: " + e.getMessage());
                }
                break;
            case 2:
                String razaoSocial, ramoAtuacao;

                System.out.print("Digite o CNPJ do cliente: ");
                cpfCNPJ = sc.nextLine();
                if (clientesPJ.size() != 0) {
                    for (ClientePJ cliente : clientesPJ) {
                        if (cliente.getCnpj().equals(cpfCNPJ)) {
                            throw new IllegalArgumentException(
                                    "Já existe cliente com esse CNPJ. Não foi possível concluir a operação.");
                        }
                    }
                }
                System.out.print("Digite o nome do cliente: ");
                nome = sc.nextLine();
                System.out.print("Digite o telefone do cliente, com DDD: ");
                telefone = sc.nextLine();
                System.out.print("Digite a razão social do cliente: ");
                razaoSocial = sc.nextLine();
                System.out.print("Digite o ramo de atuação do cliente: ");
                ramoAtuacao = sc.nextLine();
                try {
                    clientesPJ.add(new ClientePJ(nome, telefone, Cliente.Tipo.PJ, cpfCNPJ, razaoSocial, ramoAtuacao));
                    System.out.println("Cliente cadastrado com sucesso.");

                } catch (IllegalArgumentException e) {
                    System.out.println("Erro na solicitação: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Opção inexistente. Digite uma opção válida da próxima vez.\n");
                break;
        }


    }

    // Os métodos de abertura de conta solicitam os dados do cliente, fazem as
    // devidas validações para garantir que as regras de negócio são seguidas. Após,
    // finalizam a operação.
    public static void abrirContaCorrente(ArrayList<ClientePF> clientesPF, ArrayList<ClientePJ> clientesPJ,
            ArrayList<ContaCorrente> contasCorrente) {
        Scanner sc = new Scanner(System.in);
        String cpfCnpj;
        int opcao, agencia, numeroConta;
        opcao = agencia = numeroConta = 0;
        boolean existeCliente = false;
        System.out.println("\nABERTURA DE CONTA CORRENTE\n");
        System.out.println("Para qual tipo de cliente você deseja abrir uma conta corrente?");
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
                        for (ContaCorrente conta : contasCorrente) {
                            if (conta.getNumero() == numeroConta)
                                throw new IllegalArgumentException("Número de conta já existe.");
                        }
                        try {
                            contasCorrente.add(new ContaCorrente(agencia, numeroConta, cli));
                            System.out.println("Conta cadastrada com sucesso.");

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
                            System.out.println("Conta cadastrada com sucesso.");

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

    public static void abrirContaInvestimento(ArrayList<ClientePF> clientesPF, ArrayList<ClientePJ> clientesPJ,
            ArrayList<ContaInvestimento> contasInvestimento) {
        Scanner sc = new Scanner(System.in);
        String cpfCnpj;
        int opcao, agencia, numeroConta;
        opcao = agencia = numeroConta = 0;
        boolean existeCliente = false;
        System.out.println("\nABERTURA DE CONTA INVESTIMENTO\n");

        System.out.println("Para qual tipo de cliente você deseja abrir uma conta Investimento?");
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
                        for (ContaInvestimento conta : contasInvestimento) {
                            if (conta.getNumero() == numeroConta)
                                throw new IllegalArgumentException("Número de conta já existe.");
                        }
                        try {
                            contasInvestimento.add(new ContaInvestimento(agencia, numeroConta, cli));
                            System.out.println("Conta cadastrada com sucesso.");

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

                            System.out.println("Conta cadastrada com sucesso.");

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

    // Seguindo as regras de negócio, cliente PJ não pode abrir conta poupança,
    // portanto o método solicita os dados apenas dos clientes PF.
    // O restante das operações seguem o padrão das outras aberturas de conta.
    public static void abrirContaPoupanca(ArrayList<ClientePF> clientesPF, ArrayList<ContaPoupanca> contasPoupanca) {
        Scanner sc = new Scanner(System.in);
        String cpf;
        int agencia, numeroConta;
        agencia = numeroConta = 0;
        boolean existeCliente = false;
        System.out.println("\nABERTURA DE CONTA POUPANÇA\n");
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
                for (ContaPoupanca conta : contasPoupanca) {
                    if (conta.getNumero() == numeroConta)
                        throw new IllegalArgumentException("Número de conta já existe.");
                }
                try {
                    contasPoupanca.add(new ContaPoupanca(agencia, numeroConta, cli));
                    System.out.println("Conta cadastrada com sucesso.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro na solicitação: " + e.getMessage());
                }
                break;
            }
        }
        if (!existeCliente)
            throw new IllegalArgumentException("Não existe cliente com este CPF.");
    }

    // Os método de sacar, depositar e transferir solicitam os dados das contas ,
    // fazem as
    // devidas validações para garantir que as regras de negócio são seguidas. Após,
    // finalizam a operação.
    public static void sacar(ArrayList<ContaCorrente> contasCorrente, ArrayList<ContaPoupanca> contasPoupanca) {
        Scanner sc = new Scanner(System.in);
        int agencia, numeroConta, opcao;
        BigDecimal valor;
        boolean existe = false;
        boolean resposta = false;
        System.out.println("\nSAQUE\n");
        System.out.println("De qual tipo de conta você deseja sacar? ");
        System.out.println("1. Conta corrente");
        System.out.println("2. Conta poupança");
        System.out.print("Digite a opção: ");
        opcao = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite o número da agência: ");
        agencia = sc.nextInt();
        System.out.print("Digite o número da conta: ");
        numeroConta = sc.nextInt();
        System.out.print("Digite o valor a ser sacado: ");
        valor = sc.nextBigDecimal();
        if (valor.compareTo(new BigDecimal(1)) == -1)
            throw new IllegalArgumentException("Valor a ser sacado deve ser maior que 0.");
        switch (opcao) {
            case 1:
                if (contasCorrente.size() == 0)
                    throw new IllegalArgumentException("Não existe conta corrente cadastrada no banco.");
                for (ContaCorrente conta : contasCorrente) {
                    if (conta.getAgencia() == agencia && conta.getNumero() == numeroConta) {
                        existe = true;
                        resposta = conta.saca(valor);
                    }
                }
                break;
            case 2:
                if (contasPoupanca.size() == 0)
                    throw new IllegalArgumentException("Não existe conta poupança cadastrada no banco.");
                for (ContaPoupanca conta : contasPoupanca) {
                    if (conta.getAgencia() == agencia && conta.getNumero() == numeroConta) {
                        existe = true;
                        resposta = conta.saca(valor);
                    }
                }
                break;
        }
        if (!existe)
            throw new IllegalArgumentException(
                    "Número de agência ou número de conta inválidos. \nVerifique as informações e tente novamente.");
        if (resposta)
            System.out.println("Saque realizado com sucesso.");
        else
            System.out.println("Saque não realizado por falta de saldo na conta.");
    }

    public static void depositar(ArrayList<ContaCorrente> contasCorrente, ArrayList<ContaPoupanca> contasPoupanca) {
        Scanner sc = new Scanner(System.in);
        int agencia, numeroConta, opcao;
        BigDecimal valor;
        boolean existe = false;

        System.out.println("\nDEPÓSITO\n");
        System.out.println("Em qual tipo de conta você deseja depositar? ");
        System.out.println("1. Conta corrente");
        System.out.println("2. Conta poupança");
        System.out.print("Digite a opção: ");
        opcao = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite o número da agência: ");
        agencia = sc.nextInt();
        System.out.print("Digite o número da conta: ");
        numeroConta = sc.nextInt();
        System.out.print("Digite o valor a ser depositado: ");
        valor = sc.nextBigDecimal();
        if (valor.compareTo(new BigDecimal(1)) == -1)
            throw new IllegalArgumentException("Valor a depositar deve ser maior que 0.");

        switch (opcao) {
            case 1:
                if (contasCorrente.size() == 0)
                    throw new IllegalArgumentException("Não existe conta corrente cadastrada no banco.");
                for (ContaCorrente conta : contasCorrente) {
                    if (conta.getAgencia() == agencia && conta.getNumero() == numeroConta) {
                        existe = true;
                        conta.deposita(valor);
                        System.out.println("Depósito realizado com sucesso.");
                    }
                }
                break;
            case 2:
                if (contasPoupanca.size() == 0)
                    throw new IllegalArgumentException("Não existe conta poupança cadastrada no banco.");
                for (ContaPoupanca conta : contasPoupanca) {
                    if (conta.getAgencia() == agencia && conta.getNumero() == numeroConta) {
                        existe = true;
                        conta.deposita(valor);
                        System.out.println("Depósito realizado com sucesso.");
                    }
                }
                break;
        }
        if (!existe)
            throw new IllegalArgumentException(
                    "Número de agência ou número de conta inválidos. \nVerifique as informações e tente novamente.");



    }

    public static void transferir(ArrayList<ContaCorrente> contasCorrente, ArrayList<ContaPoupanca> contasPoupanca) {
        Scanner sc = new Scanner(System.in);
        int agenciaOrigem, agenciaDestino, numeroContaOrigem, numeroContaDestino, origem, destino;
        BigDecimal valor;
        boolean existe = false;
        boolean resposta = false;
        System.out.println("\nTRANSFERÊNCIA\n");

        System.out.println("Qual o tipo da conta de origem?");
        System.out.println("1. Conta corrente");
        System.out.println("2. Conta poupança");
        System.out.print("Digite a opção: ");
        origem = sc.nextInt();
        System.out.print("Digite o número da agência origem: ");
        agenciaOrigem = sc.nextInt();
        System.out.print("Digite o número da conta origem: ");
        numeroContaOrigem = sc.nextInt();
        System.out.println("Qual o tipo da conta de destino? ");
        System.out.println("1. Conta corrente");
        System.out.println("2. Conta poupança");
        System.out.print("Digite a opção: ");
        destino = sc.nextInt();
        System.out.print("Digite o número da agência destino: ");
        agenciaDestino = sc.nextInt();
        System.out.print("Digite o número da conta destino: ");
        numeroContaDestino = sc.nextInt();

        System.out.print("Digite o valor a ser transferido: ");
        valor = sc.nextBigDecimal();
        if (valor.compareTo(new BigDecimal(1)) == -1)
            throw new IllegalArgumentException("Valor a depositar deve ser maior que 0.");

        switch (origem) {
            case 1:
                switch (destino) {
                    case 1:
                        if (contasCorrente.size() == 0)
                            throw new IllegalArgumentException("Não existe conta corrente cadastrada no banco.");
                        for (ContaCorrente contaO : contasCorrente) {
                            if (contaO.getAgencia() == agenciaOrigem && contaO.getNumero() == numeroContaOrigem) {
                                for (ContaCorrente contaD : contasCorrente) {
                                    if (contaD.getAgencia() == agenciaDestino
                                            && contaD.getNumero() == numeroContaDestino) {
                                        existe = true;
                                        resposta = contaO.transfere(valor, contaD);
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        if (contasCorrente.size() == 0 || contasPoupanca.size() == 0)
                            throw new IllegalArgumentException(
                                    "Não existe conta corrente e/ou conta poupança cadastrada no banco.");
                        for (ContaCorrente contaO : contasCorrente) {
                            if (contaO.getAgencia() == agenciaOrigem && contaO.getNumero() == numeroContaOrigem) {
                                for (ContaPoupanca contaD : contasPoupanca) {
                                    if (contaD.getAgencia() == agenciaDestino
                                            && contaD.getNumero() == numeroContaDestino) {
                                        existe = true;
                                        resposta = contaO.transfere(valor, contaD);
                                    }
                                }
                            }
                        }
                        break;
                }
            case 2:
                switch (destino) {
                    case 1:
                        if (contasCorrente.size() == 0 || contasPoupanca.size() == 0)
                            throw new IllegalArgumentException(
                                    "Não existe conta corrente e/ou conta poupança cadastrada no banco.");
                        for (ContaPoupanca contaO : contasPoupanca) {
                            if (contaO.getAgencia() == agenciaOrigem && contaO.getNumero() == numeroContaOrigem) {
                                for (ContaCorrente contaD : contasCorrente) {
                                    if (contaD.getAgencia() == agenciaDestino
                                            && contaD.getNumero() == numeroContaDestino) {
                                        existe = true;
                                        resposta = contaO.transfere(valor, contaD);
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        if (contasPoupanca.size() == 0)
                            throw new IllegalArgumentException("Não existe conta poupança cadastrada no banco.");
                        for (ContaPoupanca contaO : contasPoupanca) {
                            if (contaO.getAgencia() == agenciaOrigem && contaO.getNumero() == numeroContaOrigem) {
                                for (ContaPoupanca contaD : contasPoupanca) {
                                    if (contaD.getAgencia() == agenciaDestino
                                            && contaD.getNumero() == numeroContaDestino) {
                                        existe = true;
                                        resposta = contaO.transfere(valor, contaD);
                                    }
                                }
                            }
                        }
                        break;
                }
        }
        if (!existe)
            throw new IllegalArgumentException(
                    "Números das agências ou números das contas inválidos. \nVerifique as informações e tente novamente.");

        if (resposta)
            System.out.println("Transferência realizada com sucesso.");
        else
            System.out.println("Transferência não realizada por falta de saldo na conta.");
    }

    // O investimento em conta Investimento pode ser realizado por meio de
    // transferência entre contas pré existentes no banco ou por meio de depósito
    // comum. O programa realiza essa verificação e invoca o método de depositar da
    // classe conta, de acordo com os parâmetros informados.
    public static void investir(ArrayList<ContaCorrente> contasCorrente, ArrayList<ContaPoupanca> contasPoupanca,
            ArrayList<ContaInvestimento> contasInvestimento) {
        Scanner sc = new Scanner(System.in);
        int agenciaOrigem, agenciaDestino, numeroContaOrigem, numeroContaDestino, origem, opcao;
        BigDecimal valor;
        boolean existe = false;
        boolean resposta = false;
        System.out.println("\nINVESTIMENTO\n");

        System.out.println("Qual será a origem do dinheiro a ser investido?");
        System.out.println("1. Conta pré existente");
        System.out.println("2. Depósito em caixa");
        System.out.print("Digite a opção: ");
        opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Qual o tipo da conta de origem?");
                System.out.println("1. Conta corrente");
                System.out.println("2. Conta poupança");
                System.out.print("Digite a opção: ");
                origem = sc.nextInt();
                System.out.print("Digite o número da agência origem: ");
                agenciaOrigem = sc.nextInt();
                System.out.print("Digite o número da conta origem: ");
                numeroContaOrigem = sc.nextInt();
                System.out.println("DADOS DA CONTA DE INVESTIMENTO");
                System.out.print("Digite o número da agência: ");
                agenciaDestino = sc.nextInt();
                System.out.print("Digite o número da conta: ");
                numeroContaDestino = sc.nextInt();

                System.out.print("Digite o valor a ser investido: ");
                valor = sc.nextBigDecimal();
                if (valor.compareTo(new BigDecimal(1)) == -1)
                    throw new IllegalArgumentException("Valor a investir deve ser maior que 0.");

                switch (origem) {
                    case 1:
                        for (ContaCorrente contaO : contasCorrente) {
                            if (contaO.getAgencia() == agenciaOrigem && contaO.getNumero() == numeroContaOrigem) {
                                for (ContaInvestimento contaD : contasInvestimento) {
                                    if (contaD.getAgencia() == agenciaDestino
                                            && contaD.getNumero() == numeroContaDestino) {
                                        existe = true;
                                        resposta = contaO.transfere(valor, contaD);
                                    }
                                }
                            }
                        }
                        break;

                    case 2:
                        for (ContaPoupanca contaO : contasPoupanca) {
                            if (contaO.getAgencia() == agenciaOrigem && contaO.getNumero() == numeroContaOrigem) {
                                for (ContaInvestimento contaD : contasInvestimento) {
                                    if (contaD.getAgencia() == agenciaDestino
                                            && contaD.getNumero() == numeroContaDestino) {
                                        existe = true;
                                        resposta = contaO.transfere(valor, contaD);
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        System.out.println("Opção inexistente. Digite uma opção válida da próxima vez.\n");
                        break;

                }
                if (!existe)
                    throw new IllegalArgumentException(
                            "Números das agências ou números das contas inválidos. \nVerifique as informações e tente novamente.");

                if (resposta)
                    System.out.println("Investimento realizado com sucesso.");
                else
                    System.out.println("Investimento não realizado por falta de saldo na conta.");
                break;
            case 2:
                System.out.println("DADOS DA CONTA DE INVESTIMENTO");
                System.out.print("Digite o número da agência: ");
                agenciaDestino = sc.nextInt();
                System.out.print("Digite o número da conta: ");
                numeroContaDestino = sc.nextInt();

                System.out.print("Digite o valor a ser investido: ");
                valor = sc.nextBigDecimal();
                if (valor.compareTo(new BigDecimal(1)) == -1)
                    throw new IllegalArgumentException("Valor a investir deve ser maior que 0.");
                for (ContaInvestimento conta : contasInvestimento) {
                    if (conta.getAgencia() == agenciaDestino && conta.getNumero() == numeroContaDestino) {
                        existe = true;
                        conta.deposita(valor);
                        System.out.println("Investimento realizado com sucesso.");
                    }
                }
                if (!existe)
                    throw new IllegalArgumentException(
                            "Número da agência ou número da conta inválido. \nVerifique as informações e tente novamente.");

                break;
            default:
                System.out.println("Opção inexistente. Digite uma opção válida da próxima vez.\n");
                break;
        }

    }

    // Método de consultar saldo: Solicita o tipo da conta para consulta e o seu
    // número de identificação. Ao final, retorna o saldo.
    public static void consultarSaldo(ArrayList<ContaCorrente> contasCorrente, ArrayList<ContaPoupanca> contasPoupanca,
            ArrayList<ContaInvestimento> contasInvestimento) {
        Scanner sc = new Scanner(System.in);
        int opcao, agencia, numeroConta;
        boolean existe = false;
        System.out.println("\nCONSULTA DE SALDO\n");

        System.out.println("De qual conta você deseja consultar o saldo?");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.println("3. Conta Investimento");
        System.out.print("Digite a opção solicitada: ");
        opcao = sc.nextInt();
        System.out.print("Digite o número da agência: ");
        agencia = sc.nextInt();
        System.out.print("Digite o número da conta: ");
        numeroConta = sc.nextInt();
        switch (opcao) {
            case 1:
                if (contasCorrente.size() == 0)
                    throw new IllegalArgumentException("Não existe conta cadastrada no Banco.");

                for (ContaCorrente cc : contasCorrente) {
                    if (cc.getAgencia() == agencia && cc.getNumero() == numeroConta) {
                        System.out.printf("Saldo da conta número %d, agência número %d: R$ %.2f\n", numeroConta,
                                agencia, cc.getSaldo());
                        existe = true;
                    }

                }
                break;
            case 2:
                if (contasPoupanca.size() == 0)
                    throw new IllegalArgumentException("Não existe conta cadastrada no Banco.");

                for (ContaPoupanca cc : contasPoupanca) {
                    if (cc.getAgencia() == agencia && cc.getNumero() == numeroConta) {
                        System.out.printf("Saldo da conta número %d, agência número %d: R$ %.2f\n", numeroConta,
                                agencia, cc.getSaldo());
                        existe = true;
                    }

                }
                break;
            case 3:
                if (contasInvestimento.size() == 0)
                    throw new IllegalArgumentException("Não existe conta cadastrada no Banco.");
                for (ContaInvestimento cc : contasInvestimento) {
                    if (cc.getAgencia() == agencia && cc.getNumero() == numeroConta) {
                        System.out.printf("Saldo da conta número %d, agência número %d: R$ %.2f\n", numeroConta,
                                agencia, cc.getSaldo());
                        existe = true;
                    }
                }
                break;
            default:
                System.out.println("Opção inexistente. Digite uma opção válida da próxima vez.\n");
                break;
        }
        if (!existe) {
            throw new IllegalArgumentException(
                    "Número de agência ou número de conta inválidos. \nVerifique as informações e tente novamente.");
        } else
            System.out.println("Consulta realizada com sucesso.");

    }

    // Método main realiza a construção do menu principal e invoca os outros métodos
    // responsáveis por realizar as operações bancárias
    public static void main(String[] args) {
        ArrayList<ClientePF> clientesPF = new ArrayList<>();
        ArrayList<ClientePJ> clientesPJ = new ArrayList<>();
        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>();
        ArrayList<ContaPoupanca> contasPoupanca = new ArrayList<>();
        ArrayList<ContaInvestimento> contasInvestimento = new ArrayList<>();
        int opcao;
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        while (!sair) {
            System.out.println("\nBem vindo ao Banco EM!");
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("\nPAINEL DE OPÇÕES");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Abrir conta corrente");
            System.out.println("3. Abrir conta poupança");
            System.out.println("4. Abrir conta investimento");
            System.out.println("5. Sacar");
            System.out.println("6. Depositar");
            System.out.println("7. Transferir");
            System.out.println("8. Investir");
            System.out.println("9. Consultar saldo");
            System.out.println("10. Sair");
            System.out.print("Digite a opção solicitada: ");
            opcao = sc.nextInt();
            sc.nextLine();
            clearConsole();
            switch (opcao) {
                case 1:
                    try {
                        cadastrarCliente(clientesPF, clientesPJ);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 2:
                    try {
                        abrirContaCorrente(clientesPF, clientesPJ, contasCorrente);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 3:
                    try {
                        abrirContaPoupanca(clientesPF, contasPoupanca);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 4:
                    try {
                        abrirContaInvestimento(clientesPF, clientesPJ, contasInvestimento);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 5:
                    try {
                        sacar(contasCorrente, contasPoupanca);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 6:
                    try {
                        depositar(contasCorrente, contasPoupanca);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 7:
                    try {
                        transferir(contasCorrente, contasPoupanca);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 8:
                    try {
                        investir(contasCorrente, contasPoupanca, contasInvestimento);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 9:
                    try {
                        consultarSaldo(contasCorrente, contasPoupanca, contasInvestimento);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro na solicitação: " + e.getMessage());
                    }
                    System.out.println("Você será redirecionado ao menu principal.");
                    break;
                case 10:
                    sair = true;
                    System.out.println("\nObrigada por utilizar o Banco EM. Até a próxima!\n");
                    break;
                default:
                    System.out.println("Opção inexistente. Digite uma opção válida.\n");
                    break;
            }
        }
    }
}