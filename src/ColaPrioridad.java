import java.util.ArrayList;

public class ColaPrioridad {

    // Atributos
    private ArrayList<Ticket> cola;

    // Métodos
    // Constructor
    public ColaPrioridad() {
        cola = new ArrayList<>();
    }

    // Operaciones
    private boolean estaVacia() {
        return cola.isEmpty();
    }

    // Criterio de prioridad: el ticket creado primero (fecha más antigua) tiene mayor prioridad
    private boolean tieneMayorPrioridad(Ticket a, Ticket b) {
        return a.getFechaCreacion().isBefore(b.getFechaCreacion());
    }

    // Inserta el ticket en la posición que le corresponde según su prioridad
    public void insertar(Ticket ticket) {
        int posicion = 0;
        while (posicion < cola.size() && !tieneMayorPrioridad(ticket, cola.get(posicion))) {
            posicion++;
        }
        cola.add(posicion, ticket);
    }

    // Saca el ticket del frente de la cola
    public Ticket eliminar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.\n");
            return null;
        }
        return cola.remove(0);
    }

    // Muestra el ticket del frente sin sacarlo
    public Ticket verFrente() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.\n");
            return null;
        }
        return cola.get(0);
    }
}