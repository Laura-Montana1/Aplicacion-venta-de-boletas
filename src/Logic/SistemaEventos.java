package logic;

import Dto.*;
// import configuracion.Configuracion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class SistemaEventos {
    private List<Evento> eventos;
    
    private int contadorIdCompra;

    public SistemaEventos() {
        this.eventos = new ArrayList<>();
       
        this.contadorIdCompra = 1;
    }

    // Historia de Usuario 1: Crear Evento
    public void crearEvento(String nombre, LocalDate fecha, LocalTime hora, String lugar,
                            String patrocinador, int capacidadA, int capacidadB, int capacidadC) {
        Evento evento = new Evento(nombre, fecha, hora, lugar, patrocinador);
        evento.agregarZona(new Zona("A", 200000.0, capacidadA));
        evento.agregarZona(new Zona("B", 100000.0, capacidadB));
        evento.agregarZona(new Zona("C", 50000.0, capacidadC));
        eventos.add(evento);
    }


