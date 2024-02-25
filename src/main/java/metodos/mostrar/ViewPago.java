package metodos.mostrar;

import org.example.EntidadPago;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;

/**
 * Clase para mostrar la información de los pagos en la base de datos.
 */
public class ViewPago {
    /**
     * Método para mostrar los pagos.
     */
    public void MostrarPago() {
        try {
            // Para eliminar los mensajes de Hibernate/ hacer cuando esté funcionando bien
            @SuppressWarnings("unused")
            org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

            // Configuración y apertura de la sesión
            try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
                try (Session session = sessionFactory.openSession()) {
                    // Consulta para obtener todos los pagos
                    Query<EntidadPago> miQuery = session.createQuery("from org.example.EntidadPago", EntidadPago.class);
                    List<EntidadPago> listaPagos = miQuery.list();
                    // Iterar sobre la lista de pagos y mostrar la información
                    for (EntidadPago pago : listaPagos) {
                        System.out.printf("ID Pago: %d, ID Pedido: %d, Monto: %s, Fecha: %s\n",
                                pago.getIdPago(), pago.getIdPedido(), pago.getMonto(), pago.getFecha());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
