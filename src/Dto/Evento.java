package Dto;

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

  @Override
    public String toString() { return "Evento{" + nombre + ", " + fecha + "}"; }
}
