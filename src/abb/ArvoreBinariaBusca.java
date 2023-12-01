package abb;

import java.util.LinkedList;
import contas.*;

public class ArvoreBinariaBusca {
    private NoABB raiz;
    private int quantidade;

    public ArvoreBinariaBusca() {
        this.quantidade = 0;
        this.raiz = null;
    }

    public void carregarArvore(Registros r) {
        for (int i = 0; i < r.getQuantidade(); i++) {
            LinkedList<Conta> nova = new LinkedList<>();
            nova.add(r.getLista().getConta(i));
            Item novo = new Item(nova);
            this.inserir(novo);
        }
    }

    public NoABB getRaiz() {
        return this.raiz;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public boolean arvoreVazia() {
        return this.quantidade == 0;
    }

    public NoABB pesquisar(long cpf) {
        return pesquisar(cpf, this.raiz);
    }

    private NoABB pesquisar(long cpf, NoABB no) {
        if (no == null) {
            return null;
        } else if (cpf == no.getItem().getConta().getFirst().getCpf()) {
            return no;
        } else if (cpf > no.getItem().getConta().getFirst().getCpf()) {
            return pesquisar(cpf, no.getNoDireito());
        } else {
            return pesquisar(cpf, no.getNoEsquerdo());
        }
    }

    public void inserir(Item item) {
        NoABB aux = pesquisar(item.getConta().getFirst().getCpf());
        if (aux != null) {
            LinkedList<Conta> contasEmItem = item.getConta();
            for (Conta conta : contasEmItem) {
                aux.getItem().inserirContaRepetida(conta);
            }
        } else {
            this.raiz = inserir(item, this.raiz);
        }
    }

    private NoABB inserir(Item item, NoABB no) {
        if (no == null) {
            no = new NoABB(item);
            this.quantidade++;
        } else if (item.getConta().getFirst().getCpf() > no.getItem().getConta().getFirst().getCpf()) {
            no.setNoDireito(inserir(item, no.getNoDireito()));
        } else {
            no.setNoEsquerdo(inserir(item, no.getNoEsquerdo()));
        }
        return no;
    }

    public VetorItem caminhamentoCentral() {
        VetorItem vetor = new VetorItem(10);
        return fazCaminhamentoCentral(this.raiz, vetor);
    }

    private VetorItem fazCaminhamentoCentral(NoABB no, VetorItem vetor) {
        if (no != null){
            vetor = this.fazCaminhamentoCentral(no.getNoEsquerdo(), vetor);
            vetor.inserir(no.getItem());
            vetor = this.fazCaminhamentoCentral(no.getNoDireito(), vetor);
        }
        return vetor;
    } 

    public ArvoreBinariaBusca balancear() {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        VetorItem vetor = caminhamentoCentral();
        balancear(vetor, arvore, 0, vetor.getQuantidade() - 1);
        return arvore;
    }

    private void balancear(VetorItem vetor, ArvoreBinariaBusca arvore, int inicio, int fim) {
        int meio;
        if (inicio <= fim) {
            meio = (inicio + fim) / 2;
            arvore.inserir(vetor.get(meio));
            balancear(vetor, arvore, inicio, meio - 1);
            balancear(vetor, arvore, meio + 1, fim);
        }
    }
}