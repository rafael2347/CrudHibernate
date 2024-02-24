package org.example;

import java.util.Collection;

public class EntidadPedido {
    private int idPedido;
    private String nif;
    private String producto;
    private Integer cantidad;
    private Collection<EntidadPago> pagosByIdPedido;
    private EntidadCliente clienteByNif;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadPedido that = (EntidadPedido) o;

        if (idPedido != that.idPedido) return false;
        if (nif != null ? !nif.equals(that.nif) : that.nif != null) return false;
        if (producto != null ? !producto.equals(that.producto) : that.producto != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPedido;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (producto != null ? producto.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }

    public Collection<EntidadPago> getPagosByIdPedido() {
        return pagosByIdPedido;
    }

    public void setPagosByIdPedido(Collection<EntidadPago> pagosByIdPedido) {
        this.pagosByIdPedido = pagosByIdPedido;
    }

    public EntidadCliente getClienteByNif() {
        return clienteByNif;
    }

    public void setClienteByNif(EntidadCliente clienteByNif) {
        this.clienteByNif = clienteByNif;
    }
}
