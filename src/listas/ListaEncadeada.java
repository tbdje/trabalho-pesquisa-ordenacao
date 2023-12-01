package listas;

import contas.Conta;

public class ListaEncadeada {
    private NoListaEncadeada primeiroNo, ultimoNo;
    private int quantidade;

    public ListaEncadeada() {
        this.primeiroNo = null;
        this.quantidade = 0;
    }

    public NoListaEncadeada getPrimeiroNo() {
        return this.primeiroNo;
    }

    public void setPrimeiroNo(NoListaEncadeada novoNo) {
        this.primeiroNo = novoNo;
    }

    public NoListaEncadeada getUltimoNo() {
        return this.ultimoNo;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public boolean listaEstaVazia() {
        return this.quantidade == 0;
    }

    public void inserirInicio(Conta conta) {
        NoListaEncadeada novoNo = new NoListaEncadeada(conta);

        if (listaEstaVazia()) {
            this.ultimoNo = novoNo;
        } else {
            novoNo.setProximoNo(this.primeiroNo);
        }

        this.primeiroNo = novoNo;
        this.quantidade++;
    }

    public void inserirFim(Conta conta) {
        NoListaEncadeada novoNo = new NoListaEncadeada(conta);

        if (this.listaEstaVazia()) {
            this.primeiroNo = novoNo;
        } else {
            this.ultimoNo.setProximoNo(novoNo);
        }

        this.ultimoNo = novoNo;
        this.quantidade++;
    }

    public void mostrarNos() {
        NoListaEncadeada tmp = this.primeiroNo;

        while (tmp != null) {
            System.out.println(tmp.getConta().toString());
            tmp = tmp.getProximoNo();
        }
    }
}
