package uth.hn.tareagrupal_segundoparcial.abstracciones;

public class Cliente {
    private String numeroCuenta;
    private double saldo;
    private String pin;

    public Cliente(String numeroCuenta, double saldo, String pin) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.pin = pin;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getPin() {
        return pin;
    }

    public void depositar(double monto) {
        this.saldo += monto;
    }

    public void retirar(double monto) {
        this.saldo -= monto;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
