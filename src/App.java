import contas.*;
import cpfs.RegistrosCpfs;
import io.*;
import hashing.Hashing;
import abb.*;
import avl.AVL;
import avl.NoAVL;

public class App {
    public static void main(String[] args) throws Exception {
        Registros arquivo500contas = lerArquivo("arquivos/contas/conta500.txt", 500);
        Registros arquivo1000contas = lerArquivo("arquivos/contas/conta1000.txt", 1000);
        Registros arquivo5000contas = lerArquivo("arquivos/contas/conta5000.txt", 5000);
        Registros arquivo10000contas = lerArquivo("arquivos/contas/conta10000.txt", 10000);
        Registros arquivo50000contas = lerArquivo("arquivos/contas/conta50000.txt", 50000);

        // executar 1 por vez
        // ordenarArquivosQuicksort(arquivo500contas, arquivo1000contas, arquivo5000contas, arquivo10000contas, arquivo50000contas);
        // ordenarArquivosShellsort(arquivo500contas, arquivo1000contas, arquivo5000contas, arquivo10000contas, arquivo50000contas);
        // pesquisarArquivosHashing(arquivo500contas, arquivo1000contas, arquivo5000contas, arquivo10000contas, arquivo50000contas);
        // pesquisarArquivosAbb(arquivo500contas, arquivo1000contas, arquivo5000contas, arquivo10000contas, arquivo50000contas);
        // pesquisarArquivosAvl(arquivo500contas, arquivo1000contas, arquivo5000contas, arquivo10000contas, arquivo50000contas);
    }

    public static void ordenarArquivosQuicksort(Registros r1, Registros r2, Registros r3, Registros r4, Registros r5) {
        ordenarPorQuicksort(r1);
        ordenarPorQuicksort(r2);
        ordenarPorQuicksort(r3);
        ordenarPorQuicksort(r4);
        ordenarPorQuicksort(r5);
    }

    public static void ordenarArquivosShellsort(Registros r1, Registros r2, Registros r3, Registros r4, Registros r5) {
        ordenarPorShellsort(r1);
        ordenarPorShellsort(r2);
        ordenarPorShellsort(r3);
        ordenarPorShellsort(r4);
        ordenarPorShellsort(r5);
    }

    public static void pesquisarArquivosHashing(Registros r1, Registros r2, Registros r3, Registros r4, Registros r5) {
        pesquisarHashing(r1);
        pesquisarHashing(r2);
        pesquisarHashing(r3);
        pesquisarHashing(r4);
        pesquisarHashing(r5);
    }

    public static void pesquisarArquivosAbb(Registros r1, Registros r2, Registros r3, Registros r4, Registros r5) {
        pesquisarAbb(r1);
        pesquisarAbb(r2);
        pesquisarAbb(r3);
        pesquisarAbb(r4);
        pesquisarAbb(r5);
    }

    public static void pesquisarArquivosAvl(Registros r1, Registros r2, Registros r3, Registros r4, Registros r5) {
        pesquisarAVL(r1);
        pesquisarAVL(r2);
        pesquisarAVL(r3);
        pesquisarAVL(r4);
        pesquisarAVL(r5);
    }

    public static void pesquisarAVL(Registros r) {
        Gravador g = new Gravador("arquivos/pesquisados/avl/contas" + r.getQuantidade() + "Avl.txt", false);
        RegistrosCpfs cpfs = lerArquivoCpf();
        AVL arvore = new AVL();
        arvore.carregarAVL(r);
        long[] listaCpfs = cpfs.getCpfs();

        for (int i = 0; i < listaCpfs.length; i++) {
            g.gravarArquivo("CPF " + listaCpfs[i] + ":");
            NoAVL noPesquisado = arvore.pesquisar(listaCpfs[i]);
            if (noPesquisado == null) {
                g.gravarArquivo("INEXISTENTE");
            } else {
                double totalSaldo = 0;
                for (Conta conta : noPesquisado.getItem().getConta()) {
                    g.gravarArquivo(conta.formatarSaida());
                    totalSaldo += conta.getSaldo();
                }
                g.gravarArquivo("Saldo total: " + totalSaldo);
            }
        }
        g.fecharArquivo();
    }

    public static void pesquisarHashing(Registros r) {
        RegistrosCpfs cpfs = lerArquivoCpf();
        Hashing h = new Hashing(r.getTamanho());
        h.carregarVetor(r);
        long[] listaCpfs = cpfs.getCpfs();

        for (int i = 0; i < listaCpfs.length; i++) {
            h.pesquisarCpf(listaCpfs[i]);
        }
    }

    public static void pesquisarAbb(Registros r) {
        Gravador g = new Gravador("arquivos/pesquisados/abb/contas" + r.getQuantidade() + "Abb.txt", false);
        RegistrosCpfs cpfs = lerArquivoCpf();
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        arvore.carregarArvore(r);
        ArvoreBinariaBusca balanceada = arvore.balancear();
        long[] listaCpfs = cpfs.getCpfs();

        for (int i = 0; i < listaCpfs.length; i++) {
            g.gravarArquivo("CPF " + listaCpfs[i] + ":");
            NoABB noPesquisado = balanceada.pesquisar(listaCpfs[i]);
            if (noPesquisado == null) {
                g.gravarArquivo("INEXISTENTE");
            } else {
                double totalSaldo = 0;
                for (Conta conta : noPesquisado.getItem().getConta()) {
                    g.gravarArquivo(conta.formatarSaida());
                    totalSaldo += conta.getSaldo();
                }
                g.gravarArquivo("Saldo total: " + totalSaldo);
            }
        }
        g.fecharArquivo();
    }

    public static RegistrosCpfs lerArquivoCpf() {
        LeitorCpfs lcpfs = new LeitorCpfs("arquivos/CPF.txt");
        RegistrosCpfs rcpfs = lcpfs.lerArquivo(400);
        return rcpfs;
    }

    public static Registros lerArquivo(String caminho, int quantidade) {
        LeitorContas novoLeitor = new LeitorContas(caminho);
        Registros r = novoLeitor.lerArquivo(quantidade);
        return r;
    }

    public static void ordenarPorQuicksort(Registros r) {
        r.quicksort();
        r.gravar("arquivos/ordenados/quicksort/conta" + r.getQuantidade() + "Quicksort.txt");
    }

    public static void ordenarPorShellsort(Registros r) {
        r.shellsort();
        r.gravar("arquivos/ordenados/shellsort/conta" + r.getQuantidade() + "Shellsort.txt");
    }
}
