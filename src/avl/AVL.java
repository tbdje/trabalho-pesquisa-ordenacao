package avl;

import java.util.LinkedList;
import abb.Item;
import contas.*;

public class AVL {
    private NoAVL raiz;
    private boolean h;
    private int quantidade;

    public AVL() {
        this.raiz = null;
        this.quantidade = 0;
    }

    public void carregarAVL(Registros r) {
        for (int i = 0; i < r.getQuantidade(); i++) {
            LinkedList<Conta> nova = new LinkedList<>();
            nova.add(r.getLista().getConta(i));
            Item novo = new Item(nova);
            this.inserir(novo);
        }
    }

    public NoAVL pesquisar(long cpf) {
        return pesquisar(cpf, this.raiz);
    }

    private NoAVL pesquisar(long cpf, NoAVL no) {
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

    public NoAVL getRaiz() {
        return this.raiz;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public boolean getH() {
        return this.h;
    }

    public void inserir(Item item) {
        NoAVL aux = pesquisar(item.getConta().getFirst().getCpf());
        if (aux != null) {
            LinkedList<Conta> contasEmItem = item.getConta();
            for (Conta conta : contasEmItem) {
                aux.getItem().inserirContaRepetida(conta);
            }
        } else {
            this.raiz = inserir(item, this.raiz);
        }
    }

    private NoAVL inserir(Item item, NoAVL no) {
        if (no == null) {
            NoAVL novo = new NoAVL(item);
            this.h = true;
            return novo;
        } else {
            if (item.getConta().getFirst().getCpf() < no.getItem().getConta().getFirst().getCpf()) {
                no.setNoEsquerdo(this.inserir(item, no.getNoEsquerdo()));
                no = this.balancearNoDireito(no);
                return no;
            } else {
                no.setNoDireito(this.inserir(item, no.getNoDireito()));
                no = this.balancearNoEsquerdo(no);
                return no;
            } 
        }
    }

    private NoAVL balancearNoDireito(NoAVL no) {
        if (this.h) {
            switch (no.getFatorBalanceamento()) {
                case 1:
                    no.setFatorBalanceamento(0);
                    this.h = false;
                    break;
            
                case 0:
                    no.setFatorBalanceamento(-1);
                    break;
                case -1:
                    no = this.rotacaoDireita(no);
            }
        }
        return no;
    }

    private NoAVL balancearNoEsquerdo(NoAVL no) {
        if (this.h) {
            switch (no.getFatorBalanceamento()) {
                case -1:
                    no.setFatorBalanceamento(0);
                    this.h = false;
                    break;
                case 0:
                    no.setFatorBalanceamento(1);
                    break;
                case 1:
                    no = this.rotacaoEsquerda(no);
            }
        }
        return no;
    }

    private NoAVL rotacaoDireita(NoAVL no) {
        NoAVL tmp1, tmp2;
        tmp1 = no.getNoEsquerdo();

        if (tmp1.getFatorBalanceamento() == -1) {
            no.setNoEsquerdo(tmp1.getNoDireito());
            tmp1.setNoDireito(no);
            no.setFatorBalanceamento(0);
            no = tmp1;
        } else {
            tmp2 = tmp1.getNoDireito();
            tmp1.setNoDireito(tmp2.getNoEsquerdo());
            tmp2.setNoEsquerdo(tmp1);
            no.setNoEsquerdo(tmp2.getNoDireito());
            tmp2.setNoDireito(no);

            if (tmp2.getFatorBalanceamento() == -1) {
                no.setFatorBalanceamento(1);
            } else {
                no.setFatorBalanceamento(0);
            }

            if (tmp2.getFatorBalanceamento() == 1) {
                tmp1.setFatorBalanceamento(-1);
            } else {
                tmp1.setFatorBalanceamento(0);
            }
            no = tmp2;
        }
        no.setFatorBalanceamento(0);
        this.h = false;
        return no;
    }

    private NoAVL rotacaoEsquerda(NoAVL no) {
        NoAVL tmp1, tmp2;
        tmp1 = no.getNoDireito();

        if (tmp1.getFatorBalanceamento() == 1) {
            no.setNoDireito(tmp1.getNoEsquerdo());
            tmp1.setNoEsquerdo(no);
            no.setFatorBalanceamento(0);
            no = tmp1;
        } else {
            tmp2 = tmp1.getNoEsquerdo();
            tmp1.setNoEsquerdo(tmp2.getNoDireito());
            tmp2.setNoDireito(tmp1);
            no.setNoDireito(tmp2.getNoEsquerdo());
            tmp2.setNoEsquerdo(no);

            if (tmp2.getFatorBalanceamento() == 1) {
                no.setFatorBalanceamento(-1);
            } else {
                no.setFatorBalanceamento(0);
            }

            if (tmp2.getFatorBalanceamento() == -1) {
                tmp1.setFatorBalanceamento(1);
            } else {
                tmp1.setFatorBalanceamento(0);
            }
            no = tmp2;
        }
        no.setFatorBalanceamento(0);
        this.h = false;
        return no;
    }
}
