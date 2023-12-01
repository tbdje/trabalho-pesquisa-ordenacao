package listas;

import contas.Conta;

public class ListaContigua {
    private Conta[] lista;
    private int quantidade;

    public ListaContigua() {
        this.lista = new Conta[10];
        this.quantidade = 0;
    }

    public ListaContigua(int tamanho) {
        this.lista = new Conta[tamanho];
        this.quantidade = 0;
    }

    public int getTamanho() {
        return this.lista.length;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public Conta getConta(int posicao) {
        if (posicao >= 0 && posicao <= this.quantidade) {
            return this.lista[posicao];
        }
        return null;
    }

    public boolean listaEstaVazia() {
        return this.quantidade == 0;
    }

    public boolean listaEstaCheia() {
        return this.quantidade == this.getTamanho();
    }

    public Conta[] aumentarTamanho() {
        int tamanhoAtual = this.getTamanho();
        int novoTamanho = (int) Math.ceil(tamanhoAtual * 1.50);
        Conta[] listaMaior = new Conta[novoTamanho];

        for (int i = 0; i < this.getQuantidade(); i++) {
            listaMaior[i] = this.lista[i];
        }

        return listaMaior;
    }

    public int pesquisar(String numeroConta) {
        int indice = 0;
        while (indice < this.getQuantidade()) {
            if (this.lista[indice].getNumero().equals(numeroConta)) {
                return indice;
            }
            indice++;
        }
        return -1;
    }

    public int inserirFinal(Conta novaConta) {
        if (this.listaEstaCheia()) {
            this.lista = this.aumentarTamanho();
        }
        this.lista[this.getQuantidade()] = novaConta;
        this.quantidade++;
        return 0;
    }

    public int inserirIndice(Conta novaConta, int indice) {
        if (this.listaEstaCheia()) {
            this.lista = this.aumentarTamanho();
        }
        if (indice < 0 || indice > this.quantidade) {
            return -1;
        }
        for (int i = quantidade - 1; i >= indice; i--) {
            this.lista[i + 1] = this.lista[i];
        }
        this.lista[indice] = novaConta;
        this.quantidade++;
        return 0;
    }

    public boolean remover(String numeroConta) {
        int indice = this.pesquisar(numeroConta);
        if (indice == -1) {
            return false;
        }
        this.lista[indice] = this.lista[this.quantidade - 1];
        this.quantidade--;
        return true;
    }

    public void shellsort() {
        int h = this.lista.length / 2;

        while (h > 0) {
            for (int i = h; i < this.lista.length; i++) {
                Conta temp = this.lista[i];
                int j = i;
                while (j >= h && this.lista[j - h].getCpf() > temp.getCpf()) {
                    this.lista[j] = this.lista[j - h];
                    j -= h;
                }
                this.lista[j] = temp;
            }
            h /= 2;
        }
    }
}
