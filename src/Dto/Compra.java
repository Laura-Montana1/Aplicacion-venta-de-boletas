package Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private String idCompra;
    private LocalDateTime fechaCompra;
    private EstadoCompra estado;
    private double valorTotal;
    private LocalDateTime fechaLimitePago;
    private List<Boleta> boletas;
    private Comprador comprador;
    private MetodoPago metodoPago;
    private Pago pago;

    public Compra(String idCompra, LocalDateTime fechaCompra, EstadoCompra estado,
                  double valorTotal, LocalDateTime fechaLimitePago, MetodoPago metodoPago) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
        this.valorTotal = valorTotal;
        this.fechaLimitePago = fechaLimitePago;
        this.boletas = new ArrayList<>();
        this.metodoPago = metodoPago;
        this.pago = null;
    }

    public void setBoletas(List<Boleta> boletas) { this.boletas = new ArrayList<>(boletas); }
    public void setComprador(Comprador comprador) { this.comprador = comprador; }

    public void liberarBoletasSiExpirada() {
        if (estado == EstadoCompra.RESERVADA && LocalDateTime.now().isAfter(fechaLimitePago)) {
            estado = EstadoCompra.EXPIRADA;
            for (Boleta b : boletas) {
                b.setEstado(EstadoBoleta.DISPONIBLE);
                b.setCompra(null);
            }
        }
    }

    public void registrarPago(Pago pago) {
        this.pago = pago;
        this.estado = EstadoCompra.PAGADA;
        for (Boleta b : boletas) b.setEstado(EstadoBoleta.VENDIDA);
    }

    public String getIdCompra() { return idCompra; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public EstadoCompra getEstado() { return estado; }
    public void setEstado(EstadoCompra estado) { this.estado = estado; }
    public double getValorTotal() { return valorTotal; }
    public LocalDateTime getFechaLimitePago() { return fechaLimitePago; }
    public List<Boleta> getBoletas() { return new ArrayList<>(boletas); }
    public Comprador getComprador() { return comprador; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public Pago getPago() { return pago; }

    @Override
    public String toString() {
        return "Compra{" + idCompra + ", " + estado + ", $" + valorTotal + ", límite " + fechaLimitePago + "}";
    }
}
