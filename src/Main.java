import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ColaPrioridad pendientes = new ColaPrioridad();            // tickets sin resolver
        ListaEnlazadaSimple resueltos = new ListaEnlazadaSimple(); // tickets resueltos
        int opcion;

        do {
            System.out.println("===== SISTEMA DE TICKETS =====");
            System.out.println("1. Menú de usuario");
            System.out.println("2. Menú de administrador");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(br);

            switch (opcion) {
                case 1:
                    menuUsuario(br, pendientes, resueltos);
                    break;
                case 2:
                    menuAdministrador(br, pendientes, resueltos);
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        } while (opcion != 3);

        br.close();
    }

    // Menú con las funciones del usuario: crear y buscar tickets
    private static void menuUsuario(BufferedReader br, ColaPrioridad pendientes,
                                    ListaEnlazadaSimple resueltos) throws IOException {
        int opcion;
        do {
            System.out.println("\n--- MENÚ DE USUARIO ---");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket por id");
            System.out.println("3. Volver");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(br);

            switch (opcion) {
                case 1:
                    crearTicket(br, pendientes);
                    break;
                case 2:
                    System.out.print("Id del ticket a buscar: ");
                    int id = leerEntero(br);
                    Ticket encontrado = resueltos.buscar(id);
                    if (encontrado != null) {
                        System.out.println("¡Ticket resuelto!");
                        System.out.println(encontrado);
                    } else {
                        System.out.println("El ticket #" + id + " está pendiente (o no existe).\n");
                    }
                    break;
                case 3:
                    System.out.println();
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        } while (opcion != 3);
    }

    // Menú con las funciones del administrador: ver y resolver el ticket del frente
    private static void menuAdministrador(BufferedReader br, ColaPrioridad pendientes,
                                          ListaEnlazadaSimple resueltos) throws IOException {
        int opcion;
        do {
            System.out.println("\n--- MENÚ DE ADMINISTRADOR ---");
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("3. Volver");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(br);

            switch (opcion) {
                case 1:
                    Ticket frente = pendientes.verFrente();
                    if (frente != null) {
                        System.out.println(frente);
                    }
                    break;
                case 2:
                    Ticket aResolver = pendientes.eliminar();
                    if (aResolver != null) {
                        // Se establece la fecha de resolución y pasa a la lista de resueltos
                        aResolver.setFechaResolucion(LocalDateTime.now());
                        resueltos.insertarFin(aResolver);
                        System.out.println("Ticket #" + aResolver.getId() + " resuelto con éxito.\n");
                    }
                    break;
                case 3:
                    System.out.println();
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        } while (opcion != 3);
    }

    // Pide los datos de un ticket y lo inserta en la cola de pendientes
    private static void crearTicket(BufferedReader br, ColaPrioridad pendientes) throws IOException {
        System.out.print("Nombre completo: ");
        String nombre = br.readLine();

        System.out.print("Descripción del problema: ");
        String descripcion = br.readLine();

        Ticket ticket = new Ticket(descripcion, nombre);
        pendientes.insertar(ticket);

        System.out.println("Ticket creado con éxito. Tu id es: " + ticket.getId() + "\n");
    }

    // Lee un entero de forma segura: si el usuario escribe algo que no es número, vuelve a pedirlo
    private static int leerEntero(BufferedReader br) throws IOException {
        while (true) {
            try {
                return Integer.parseInt(br.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un número válido.");
            }
        }
    }
}
