package dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nombre;
    private LocalDate fecha;
    private LocalTime hora;
    private String lugar;
    private String patrocinador;
    private List<Zona> zonas;

    public Evento(String nombre, LocalDate fecha, LocalTime hora, String lugar, String patrocinador) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.patrocinador = patrocinador;
        this.zonas = new ArrayList<>();
    }

    public void agregarZona(Zona zona) { zonas.add(zona); }

    public Zona getZona(String nombreZona) {
        for (Zona z : zonas) {
            if (z.getNombre().equalsIgnoreCase(nombreZona)) return z;
        }
        return null;
    }

    public String getInfoDisponibilidad() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" (").append(fecha).append(" ").append(hora).append(") - ")
                .append(lugar).append(" | Disponibles: ");
        for (Zona z : zonas) {
            sb.append(z.getNombre()).append(":").append(z.getBoletasDisponibles()).append(" ");
        }
        return sb.toString();
    }

    public String getNombre() { return nombre; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getLugar() { return lugar; }
    public String getPatrocinador() { return patrocinador; }
    public List<Zona> getZonas() { return new ArrayList<>(zonas); }

    @Override
    public String toString() { return "Evento{" + nombre + ", " + fecha + "}"; }
}
