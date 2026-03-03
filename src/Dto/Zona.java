package dto;

import java.util.ArrayList;
import java.util.List;

public class Zona {
    private String nombre;
    private double precio;
    private int capacidad;
    private List<Boleta> boletas;

    public Zona(String nombre, double precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
        this.boletas = new ArrayList<>();
        for (int i = 1; i <= capacidad; i++) {
            String id = nombre + "-" + String.format("%04d", i);
            boletas.add(new Boleta(id, this));
        }
    }

    public boolean hayDisponibles(int cantidad) {
        return getBoletasDisponibles() >= cantidad;
    }

    public int getBoletasDisponibles() {
        int count = 0;
        for (Boleta b : boletas) {
            if (b.getEstado() == EstadoBoleta.DISPONIBLE) count++;
        }
        return count;
    }

    public List<Boleta> reservarBoletas(int cantidad, Compra compra) {
        List<Boleta> reservadas = new ArrayList<>();
        for (Boleta b : boletas) {
            if (b.getEstado() == EstadoBoleta.DISPONIBLE && reservadas.size() < cantidad) {
                b.setEstado(EstadoBoleta.RESERVADA);
                b.setCompra(compra);
                reservadas.add(b);
            }
        }
        return reservadas;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCapacidad() { return capacidad; }
    public List<Boleta> getBoletas() { return new ArrayList<>(boletas); }

    @Override
    public String toString() {
        return "Zona " + nombre + " ($" + precio + ")";
    }
}
