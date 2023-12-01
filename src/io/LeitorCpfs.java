package io;

import java.io.*;

import cpfs.RegistrosCpfs;

public class LeitorCpfs {
    private FileReader entrada;

    public LeitorCpfs(String nome) {
        try {
            this.entrada = new FileReader(nome);
        } catch (FileNotFoundException e) {
            System.out.println("[!] O arquivo não foi encontrado.");
        }
    }

    public RegistrosCpfs lerArquivo(int tamanho) {
        RegistrosCpfs cpfs = new RegistrosCpfs(tamanho);
        BufferedReader buffer = new BufferedReader(entrada);
        String conteudoLinha;

        try {
            while ((conteudoLinha = buffer.readLine()) != null) {
                cpfs.insere(Long.parseLong(conteudoLinha));
            }
            return cpfs;
        } catch (Exception e) {
            System.out.println("[!] Erro na leitura do arquivo.");
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

