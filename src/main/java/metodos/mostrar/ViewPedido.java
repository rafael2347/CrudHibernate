package metodos.mostrar;

import org.example.EntidadPedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.logging.Level;

/**
 * Clase para mostrar la información de los pedidos en la base de datos.
 */
public class ViewPedido {

    /**
     * Método para mostrar los pedidos.
     */
    public void MostrarPedido() {
        try {
            // Para eliminar los mensajes de Hibernate/ hacer cuando esté funcionando bien
            @SuppressWarnings("unused")
            org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

            // Configuración y apertura de la sesión
            try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
                try (Session session = sessionFactory.openSession()) {
                    // Consulta para obtener todos los pedidos
                    Query<EntidadPedido> miQuery = session.createQuery("from org.example.EntidadPedido", EntidadPedido.class);
                    List<EntidadPedido> listaPedidos = miQuery.list();
                    // Iterar sobre la lista de pedidos y mostrar la información
                    for (EntidadPedido pedido : listaPedidos) {
                        System.out.printf("ID Pedido: %d, NIF: %s, Producto: %s, Cantidad: %d\n",
                                pedido.getIdPedido(), pedido.getNif(), pedido.getProducto(), pedido.getCantidad());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
