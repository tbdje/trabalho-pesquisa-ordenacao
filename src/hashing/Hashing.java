package hashing;

import java.util.LinkedList;
import contas.Conta;
import contas.Registros;

import io.Gravador;

public class Hashing {
    private LinkedList<Conta>[] vetor;
    private int tamanho;
    private int tamanhoArquivo;
    
    @SuppressWarnings("unchecked")
    public Hashing(int tamanho) {
        this.tamanhoArquivo = tamanho;
        this.tamanho = calcularTamanho(tamanho);
        vetor = new LinkedList[this.tamanho];
        for (int i = 0; i < this.tamanho; i++) {
            vetor[i] = new LinkedList<>();
        }
    }

    public void carregarVetor(Registros contas) {
        for (int i = 0; i < contas.getQuantidade(); i++) {
            Conta novaConta = contas.getLista().getConta(i);
            int indiceHashing = funcaoHashing(novaConta.getCpf());
            this.vetor[indiceHashing].add(novaConta);
        }
    }

    public void pesquisarCpf(long cpf) {
        Gravador g = new Gravador("arquivos/pesquisados/hashing/conta" + this.tamanhoArquivo + "Hashing.txt", false);
        int indice = funcaoHashing(cpf);
        g.gravarArquivo("CPF " + cpf);
        LinkedList<Conta> contas = this.vetor[indice];
        double totalSaldo = 0;

        if (contas.size() != 0) {
            LinkedList<Conta> semColisao = new LinkedList<>();
            for (Conta conta : contas) {
                if (conta.getCpf() == cpf) {
                    semColisao.add(conta);
                    totalSaldo += conta.getSaldo();
                }
            }
            if (semColisao.size() == 0) {
                g.gravarArquivo("INEXISTENTE");
            } else {
                for (Conta conta : semColisao) {
                    g.gravarArquivo(conta.formatarSaida());
                }
                g.gravarArquivo("Saldo total: " + totalSaldo);
            }
        } else {
            g.gravarArquivo("INEXISTENTE");
        }
        g.fecharArquivo();
    }

    public int funcaoHashing(long cpf) {
        return (int)(cpf % this.tamanho);
    }

    public int calcularTamanho(int tamanho) {
        return pegarPrimoMaiorAlvo(tamanho);
    }

    private static boolean verificarNumeroPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int pegarPrimoMaiorAlvo(int alvo) {
        int numero = alvo + 1;

        while (true) {
            if (verificarNumeroPrimo(numero)) {
                return numero;
            }
            numero++;
        }
    }
}
