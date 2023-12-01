package avl;

import abb.Item;
import contas.Conta;

public class NoAVL {
    private Item item;
    private NoAVL noEsquerdo, noDireito;
    private int fatorBalanceamento;

    public NoAVL(Item item) {
        this.item = item;
        this.fatorBalanceamento = 0;
    }

    public Item getItem() {
        return this.item;
    }

    protected void setItem(Item novoItem) {
        this.item = novoItem;
    }

    public NoAVL getNoEsquerdo() {
        return this.noEsquerdo;
    }

    public void setNoEsquerdo(NoAVL novoNoEsquerdo) {
        this.noEsquerdo = novoNoEsquerdo;
    }

    public NoAVL getNoDireito() {
        return this.noDireito;
    }

    public void setNoDireito(NoAVL novoNoDireito) {
        this.noDireito = novoNoDireito;
    }

    public int getFatorBalanceamento() {
        return this.fatorBalanceamento;
    }

    public void setFatorBalanceamento(int novoFator) {
        this.fatorBalanceamento = novoFator;
    }

    public void inserirContaRepetida(Conta c) {
        this.item.inserirContaRepetida(c);
    }
}
