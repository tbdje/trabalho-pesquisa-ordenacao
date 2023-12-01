package io;

import java.io.*;

import contas.Conta;
import contas.Registros;

public class LeitorContas {
    private FileReader entrada;

    public LeitorContas(String nome) {
        try {
            this.entrada = new FileReader(nome);
        } catch (FileNotFoundException e) {
            System.out.println("[!] O arquivo não foi encontrado.");
        }
    }

    public Registros lerArquivo(int tamanho) {
        Registros contas = new Registros(tamanho);
        BufferedReader buffer = new BufferedReader(entrada);
        String conteudoLinha;

        try {
            while ((conteudoLinha = buffer.readLine()) != null) {
                contas.insere(separarDados(conteudoLinha));
            }
            return contas;
        } catch (Exception e) {
            System.out.println("[!] Erro na leitura do arquivo.");
            return null;
        }
    }

    private Conta separarDados(String linha) {
        String[] registro;
        int agencia;
        String numero;
        long cpf;
        double saldo;

        try {
            registro = linha.split(";");
            agencia = Integer.parseInt(registro[0]);
            numero = registro[1];
            saldo = Double.parseDouble(registro[2]);
            cpf = Long.parseLong(registro[3]);
            
            return (new Conta(agencia, numero, saldo, cpf));
        } catch (Exception e) {
            System.out.println("[!] Erro na etapa de separação dos dados.");
            return null;
        }
    }

    public void fecharArquivo() {
        try {
            this.entrada.close();
        } catch (Exception e) {
            System.out.println("[!] Erro no fechamento do arquivo.");
        }
    }
}

