package contas;

import io.Gravador;
import listas.ListaContigua;

public class Registros {
    private int tamanho;
    private int quantidade;
    private ListaContigua lista;

    public Registros(int tamanho) {
        this.tamanho = tamanho;
        this.quantidade = 0;
        lista = new ListaContigua(tamanho);
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public ListaContigua getLista() {
        return this.lista;
    }

    public int insere(Conta conta) {
        this.quantidade++;
        return this.lista.inserirFinal(conta);
    }

    public void mostrar() {
        System.out.println("A");
        for (int i = 0; i < this.quantidade; i++) {
            System.out.println(this.lista.getConta(i).toString());
        }
    }

    public void gravar(String novoArquivo) {
        Gravador g = new Gravador(novoArquivo, true);
        for (int i = 0; i < this.quantidade; i++) {
            g.gravarArquivo(this.lista.getConta(i).toString());
        }
        g.fecharArquivo();
    }

    public void quicksort() {
        ListaContigua ordenada = this.ordena(this.lista);
        this.lista = ordenada;
    }

    public ListaContigua ordena(ListaContigua lista) {
        if (lista.getQuantidade() <= 1) {
            return lista;
        } else {
            int meio = lista.getQuantidade() / 2;
            Conta pivo = lista.getConta(meio);

            ListaContigua menores = new ListaContigua(10);
            ListaContigua maiores = new ListaContigua(10);

            for (int i = 0; i < lista.getQuantidade(); i++) {
                Conta conta = lista.getConta(i);

                if (i != meio) {
                    if (conta.getCpf() < pivo.getCpf()) {
                        menores.inserirFinal(conta);
                    } else if (conta.getCpf() > pivo.getCpf()) {
                        maiores.inserirFinal(conta);
                    } else {
                        if (i < meio) {
                            menores.inserirFinal(conta);
                        } else {
                            maiores.inserirFinal(conta);
                        }
                    }
                }
            }

            ListaContigua resultante = new ListaContigua(10);
            ListaContigua listaMenores = ordena(menores);
            ListaContigua listaMaiores = ordena(maiores);

            for (int i = 0; i < listaMenores.getQuantidade(); i++) {
                resultante.inserirFinal(listaMenores.getConta(i));
            }

            resultante.inserirFinal(pivo);

            for (int i = 0; i < listaMaiores.getQuantidade(); i++) {
                resultante.inserirFinal(listaMaiores.getConta(i));
            }

            return resultante;
        }
    }

    public void shellsort() {
        this.lista.shellsort();
    }
}
