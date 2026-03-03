package logic;

import Dto.*;
// import configuracion.Configuracion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class SistemaEventos {
    private List<Evento> eventos;
    // private Map<String, Comprador> compradoresPorCedula;
    // private Configuracion configuracion;
    private int contadorIdCompra;

    public SistemaEventos() {
        this.eventos = new ArrayList<>();
        // this.compradoresPorCedula = new HashMap<>();
        // this.configuracion = new Configuracion();
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

    // Historia de Usuario 2: Reservar Boletas
    public String reservarBoletas(String nombreEvento, String nombreZona, int cantidad,
                                  MetodoPago metodoPago, String nombreComprador, String cedula) {
        if (cantidad > 10 || cantidad <= 0) return "Error: Máximo 10 boletas por transacción.";

        Evento evento = buscarEvento(nombreEvento);
        if (evento == null) return "Error: Evento no encontrado.";

        Zona zona = evento.getZona(nombreZona);
        if (zona == null || !zona.hayDisponibles(cantidad))
            return "Error: No hay suficientes boletas en la zona " + nombreZona + ".";

        Comprador comprador = compradoresPorCedula.computeIfAbsent(cedula,
                k -> new Comprador(nombreComprador, cedula));

        String idCompra = "COMP-" + String.format("%06d", contadorIdCompra++);
        LocalDateTime ahora = LocalDateTime.now();
        // LocalDateTime limite = ahora.plusHours(configuracion.getHorasExpiracionReserva());
        double total = zona.getPrecio() * cantidad;

        Compra compra = new Compra(idCompra, ahora, EstadoCompra.RESERVADA, total, limite, metodoPago);
        compra.setComprador(comprador);

        List<Boleta> reservadas = zona.reservarBoletas(cantidad, compra);
        compra.setBoletas(reservadas);
        comprador.agregarCompra(compra);

        return "✅ Reserva exitosa. ID: " + idCompra + " | Límite pago: " + limite;
    }

    // Historia de Usuario 3: Registrar Pago
    public boolean registrarPago(String cedula, double montoPagado) {
        Comprador comprador = compradoresPorCedula.get(cedula);
        if (comprador == null) return false;

        for (Compra compra : comprador.getCompras()) {
            if (compra.getEstado() == EstadoCompra.RESERVADA) {
                compra.liberarBoletasSiExpirada();
                if (compra.getEstado() == EstadoCompra.EXPIRADA) continue;
                if (Math.abs(compra.getValorTotal() - montoPagado) > 0.01) return false;
                Pago pago = new Pago(compra.getMetodoPago(), montoPagado);
                compra.registrarPago(pago);
                return true;
            }
        }
        return false;
    }




