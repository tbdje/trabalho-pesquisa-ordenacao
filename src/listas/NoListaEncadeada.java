package listas;

import contas.Conta;

public class NoListaEncadeada {
    private Conta conta;
    private NoListaEncadeada proximoNo;

    public NoListaEncadeada(Conta conta) {
        this.conta = conta;
        this.proximoNo = null;
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta novaConta) {
        this.conta = novaConta;
    }

    public NoListaEncadeada getProximoNo() {
        return this.proximoNo;
    }

    public void setProximoNo(NoListaEncadeada proximoNo) {
        this.proximoNo = proximoNo;
    }

    @Override
    public String toString() {
        return "-[" + conta.toString() + "]";
    }
}
