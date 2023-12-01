package io;
import java.io.*;

public class Gravador {
    private FileWriter arquivo;

    public Gravador(String nomeArquivo, boolean sobrescrever) {
        try {          
            this.arquivo = new FileWriter(new File(nomeArquivo), !sobrescrever);
        } catch (IOException e) {
            System.out.println("[!] Erro na abertura do arquivo.");
        }
    }

    public void gravarArquivo(String conteudo) {
        try {
            this.arquivo.append(conteudo + "\n");
        } catch (IOException e) {
            System.out.println("[!] Erro na gravação do arquivo.");
        }
    }

    public void fecharArquivo() {
        try {
            this.arquivo.close();
        } catch (IOException e) {
            System.out.println("[!] Erro no fechamento do arquivo.");
        }
    }
}