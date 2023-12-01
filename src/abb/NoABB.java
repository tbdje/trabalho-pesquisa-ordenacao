package abb;

import contas.Conta;

public class NoABB {
    private Item item;
    private NoABB noEsquerdo, noDireito;

    public NoABB(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public long getCpf() {
        // pega o cpf da primeira conta
        return item.getConta().getFirst().getCpf();
    }

    protected void setItem(Item novo) {
        this.item = novo;
    }

    public NoABB getNoEsquerdo() {
        return this.noEsquerdo;
    }

    public void setNoEsquerdo(NoABB novoNoEsquerdo) {
        this.noEsquerdo = novoNoEsquerdo;
    }

    public NoABB getNoDireito() {
        return this.noDireito;
    }

    public void setNoDireito(NoABB novoNoDireito) {
        this.noDireito = novoNoDireito;
    }

    public void inserirContaRepetida(Conta c) {
        this.item.inserirContaRepetida(c);
    }
}
