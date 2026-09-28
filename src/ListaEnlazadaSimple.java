public class ListaEnlazadaSimple {

    // Atributos
    private NodoLista primero;

    // Métodos
    // Constructor
    public ListaEnlazadaSimple() {
        primero = null;
    }

    // Getters
    private NodoLista getPrimero() {
        return primero;
    }

    // Setters
    private void setPrimero(NodoLista primero) {
        this.primero = primero;
    }

    // Operaciones
    private boolean estaVacia() {
        return primero == null;
    }

    // Inserta un ticket resuelto al final de la lista
    public void insertarFin(Ticket ticket) {
        NodoLista nodo = new NodoLista(ticket);
        // Si está vacía, el nuevo nodo se convierte en el primero
        if (estaVacia()) {
            setPrimero(nodo);
        } else {
            // Si no, recorremos la lista hasta encontrar el último nodo
            // (el que tiene siguiente == null) y le enganchamos el nuevo nodo
            NodoLista temp = primero;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nodo);
        }
    }

    // Busca un ticket por su id. Si no lo encuentra, retorna null
    // (el Main se encarga de avisar que el ticket está pendiente)
    public Ticket buscar(int id) {
        NodoLista temp = primero;
        while (temp != null) {
            if (temp.getTicket().getId() == id) return temp.getTicket();
            temp = temp.getSiguiente();
        }
        return null;
    }

    private class NodoLista {

        // Atributos
        private Ticket ticket;
        private NodoLista siguiente;

        // Métodos
        // Constructor
        public NodoLista(Ticket ticket) {
            this.ticket = ticket;
            siguiente = null;
        }

        // Getters
        public Ticket getTicket() {
            return ticket;
        }

        public NodoLista getSiguiente() {
            return siguiente;
        }

        // Setters
        public void setTicket(Ticket ticket) {
            this.ticket = ticket;
        }

        public void setSiguiente(NodoLista siguiente) {
            this.siguiente = siguiente;
        }
    }
}