package abb;

import java.util.LinkedList;
import contas.*;

public class Item {
    private LinkedList<Conta> conta;

    public Item(LinkedList<Conta> c) {
        conta = c;
    }

    public LinkedList<Conta> getConta() {
        return this.conta;
    }

    public void setConta(LinkedList<Conta> novaC) {
        this.conta = novaC;
    }

    public void inserirContaRepetida(Conta repetida) {
        this.conta.add(repetida);
    }
}
