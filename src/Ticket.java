import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {

    // Atributos
    // Contador estático: se usa para asignar un id consecutivo e irrepetible a cada ticket
    private static int cantidad = 0;

    private int id;
    private String descripcion;
    private String nombreCompleto;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    // Métodos
    // Constructor
    public Ticket(String descripcion, String nombreCompleto) {
        cantidad++;
        id = cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        fechaCreacion = LocalDateTime.now();
        // Mientras el ticket no se resuelva, la fecha de resolución se mantiene en null
        fechaResolucion = null;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    // Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    // toString()
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String resolucion = (fechaResolucion == null) ? "Pendiente" : fechaResolucion.format(formato);
        return "\nTicket #" + id + "\nDescripción: " + descripcion + "\nUsuario: " + nombreCompleto +
                "\nFecha de creación: " + fechaCreacion.format(formato) + "\nFecha de resolución: " + resolucion + "\n";
    }
}
