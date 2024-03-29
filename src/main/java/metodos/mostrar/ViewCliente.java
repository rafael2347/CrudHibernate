package metodos.mostrar;

import org.pojos.EntidadCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;

/**
 * Clase para mostrar la información de los clientes en la base de datos.
 */
public class ViewCliente {

    /**
     * Método para mostrar los clientes.
     */
    public void MostrarCliente() {
        try {
            // Para eliminar los mensajes de Hibernate/ hacer cuando esté funcionando bien
            @SuppressWarnings("unused")
            org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

            // Configuración y apertura de la sesión
            try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
                try (Session session = sessionFactory.openSession()) {
                    // Consulta para obtener todos los clientes
                    Query<EntidadCliente> miQuery = session.createQuery("from org.pojos.EntidadCliente", EntidadCliente.class);
                    List<EntidadCliente> listaClientes = miQuery.list();
                    // Iterar sobre la lista de clientes y mostrar la información
                    for (EntidadCliente cliente : listaClientes) {
                        System.out.printf("Nombre: %s, Apellido: %s, NIF: %s\n",
                                cliente.getNombre(), cliente.getApellido(), cliente.getNif());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
