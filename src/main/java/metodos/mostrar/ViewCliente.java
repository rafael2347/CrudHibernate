package metodos.mostrar;

import org.example.EntidadCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;

public class ViewCliente {

    // Método para mostrar los clientes
    public void MostrarCliente() {
        try {
            // Para eliminar los mensajes de Hibernate/ hacer cuando esté funcionando bien
            @SuppressWarnings("unused")
            org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

            try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
                try (Session session = sessionFactory.openSession()) {
                    Query<EntidadCliente> miQuery = session.createQuery("from org.example.EntidadCliente", EntidadCliente.class);
                    List<EntidadCliente> listaClientes = miQuery.list();
                    for (EntidadCliente cliente : listaClientes) {
                        System.out.printf("Nombre: %s, Apellido: %s, NIF: %s\n",
                                cliente.getNombre(), cliente.getApellido());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
