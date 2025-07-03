package uth.hn.tareagrupal_segundoparcial.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import uth.hn.tareagrupal_segundoparcial.abstracciones.Cliente;

/*import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;*/

@Named("CajeroBean")
//@ManagedBean(name = "cajeroBean")
@SessionScoped
public class CajeroBean implements Serializable {
    private String cuentaIngresada;
    private String pinIngresado;
    private double monto;
    private String mensaje;
    private List<Cliente> clientes;

    public CajeroBean() {
        clientes = new ArrayList<>();
        clientes.add(new Cliente("1001", 500.0, "1234"));
        clientes.add(new Cliente("1002", 1000.0, "5678"));
        clientes.add(new Cliente("1003", 150.0, "1111"));
        clientes.add(new Cliente("1004", 200.0, "2222"));
        clientes.add(new Cliente("1005", 3000.0, "3333"));
    }

    private Cliente buscarCliente() {
        return clientes.stream()
                .filter(c -> c.getNumeroCuenta().equals(cuentaIngresada) && c.getPin().equals(pinIngresado))
                .findFirst().orElse(null);
    }

    public void depositar() {
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            if (monto > 0) {
                cliente.depositar(monto);
                mensaje = "Depósito exitoso. Nuevo saldo: $" + cliente.getSaldo();
            } else {
                mensaje = "Monto inválido para depósito.";
            }
        } else {
            mensaje = "PIN o número de cuenta incorrecto.";
        }
        mostrarMensaje("Reserva creada exitosamente", FacesMessage.SEVERITY_INFO);
    }

    public void retirar() {
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            if (monto > 0 && cliente.getSaldo() >= monto) {
                cliente.retirar(monto);
                mensaje = "Retiro exitoso. Nuevo saldo: $" + cliente.getSaldo();
            } else {
                mensaje = "Saldo insuficiente o monto inválido.";
            }
        } else {
            mensaje = "PIN o número de cuenta incorrecto.";
        }
        mostrarMensaje("Reserva creada exitosamente", FacesMessage.SEVERITY_INFO);
    }

    /*private void mostrarMensaje() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
    }*/

    private void mostrarMensaje(String mensaje, FacesMessage.Severity tipo){;
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, mensaje, null));
        FacesMessage message = new FacesMessage(tipo, mensaje, null);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    // Getters y Setters
    public String getCuentaIngresada() { return cuentaIngresada; }
    public void setCuentaIngresada(String cuentaIngresada) { this.cuentaIngresada = cuentaIngresada; }
    public String getPinIngresado() { return pinIngresado; }
    public void setPinIngresado(String pinIngresado) { this.pinIngresado = pinIngresado; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public List<Cliente> getClientes() { return clientes; }
}
