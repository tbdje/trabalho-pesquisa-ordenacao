package abb;

public class VetorItem {
    private Item[] vetor;
    private int quantidade;

    public VetorItem() {
        this.vetor = null;
        this.quantidade = 0;
    }

    public VetorItem(int tamanho) {
        this.vetor = new Item[tamanho];
        this.quantidade = 0;
    }

    public Item[] aumentarTamanho() {
        int tamanhoAtual = this.vetor.length;
        int novoTamanho = (int) Math.ceil(tamanhoAtual * 1.50);
        Item[] listaMaior = new Item[novoTamanho];

        for (int i = 0; i < this.getQuantidade(); i++) {
            listaMaior[i] = this.vetor[i];
        }
        return listaMaior;
    }

    public boolean vetorCheio() {
        return this.quantidade == this.vetor.length;
    }

    public void inserir(Item item) {
        if (this.vetorCheio()) {
            this.vetor = this.aumentarTamanho();
        }
        this.vetor[this.quantidade] = item;
        this.quantidade++;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public Item get(int posicao) {
        if (posicao >= 0 && posicao <= this.quantidade - 1) {
            return vetor[posicao];
        }
        return null;
    }
}
