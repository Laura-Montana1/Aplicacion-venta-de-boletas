package Dto;

import java.time.LocalDateTime;

public class Pago {
    private MetodoPago metodoPago;
    private double monto;
    private LocalDateTime fechaPago;

    public Pago(MetodoPago metodoPago, double monto) {
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = LocalDateTime.now();
    }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public double getMonto() { return monto; }
    public LocalDateTime getFechaPago() { return fechaPago; }

    @Override
    public String toString() { return "Pago{" + metodoPago + ", $" + monto + "}"; }
}
