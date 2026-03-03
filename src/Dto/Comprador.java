package Dto;

import java.util.ArrayList;
import java.util.List;

public class Comprador {
    private String nombre;
    private String cedula;
    private List<Compra> compras;

    public Comprador(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.compras = new ArrayList<>();
    }

    public void agregarCompra(Compra compra) { compras.add(compra); }
    public List<Compra> getCompras() { return new ArrayList<>(compras); }
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }

    @Override
    public String toString() { return "Comprador{" + nombre + " - " + cedula + "}"; }
}