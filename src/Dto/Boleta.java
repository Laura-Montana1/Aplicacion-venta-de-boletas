package Dto;

public class Boleta {

    private String idBoleta;
    private Zona zona;
    private EstadoBoleta estado;
    private Compra compra;

    public Boleta(String idBoleta, Zona zona) {
        this.idBoleta = idBoleta;
        this.zona = zona;
        this.estado = EstadoBoleta.DISPONIBLE;
        this.compra = null;
    }

    public String getIdBoleta() { return idBoleta; }
    public Zona getZona() { return zona; }
    public EstadoBoleta getEstado() { return estado; }
    public void setEstado(EstadoBoleta estado) { this.estado = estado; }
    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }

    @Override
    public String toString() {
        return "Boleta{" + idBoleta + ", " + (zona != null ? zona.getNombre() : "") + ", " + estado + "}";
    }
}
