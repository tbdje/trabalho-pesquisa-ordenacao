package contas;

public class Conta {
    private int agencia;
    private String numero;
    private double saldo;
    private long cpf;

    public Conta() {
        this.agencia = 0;
        this.numero = "";
        this.saldo = 0.0;
        this.cpf = 0;
    }

    public Conta(int agencia, String numero, double saldo, long cpf) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.cpf = cpf;
    }

    public String formatarSaida() {
        return "agencia " + this.agencia + "\t" + "Conta " + this.numero + "\t" + "Saldo: " + this.saldo;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public String getNumero() {
        return this.numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public long getCpf() {
        return this.cpf;
    }

    public void setAgencia(int novaAgencia) {
        this.agencia = novaAgencia;
    }

    public void setNumero(String novoNumero) {
        this.numero = novoNumero;
    }

    public void setSaldo(double novoSaldo) {
        this.saldo = novoSaldo;
    }

    public void setCpf(long novoCpf) {
        this.cpf = novoCpf;
    }

    @Override
    public String toString() {
        return this.agencia + ";" + this.numero + ";" + this.saldo + ";" + this.cpf;
    }
}
