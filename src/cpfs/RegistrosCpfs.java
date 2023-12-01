package cpfs;

public class RegistrosCpfs {
    private int tamanho;
    private int quantidade;
    private long[] cpfs;

    public RegistrosCpfs(int tamanho) {
        this.tamanho = tamanho;
        this.quantidade = 0;
        this.cpfs = new long[tamanho];
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public int insere(long cpf) {
        this.cpfs[this.quantidade] = cpf;
        this.quantidade++;
        return 0;
    }

    public long[] getCpfs() {
        return this.cpfs;
    }
}