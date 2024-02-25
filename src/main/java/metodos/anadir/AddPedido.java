package metodos.anadir;

import org.example.EntidadPedido; // Importa la clase que representa los pedidos
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase para agregar un nuevo pedido a la base de datos.
 */
public class AddPedido {

    /**
     * Método para insertar un nuevo pedido en la base de datos.
     * @param nif NIF del cliente asociado al pedido.
     * @param producto Producto del pedido.
     * @param cantidad Cantidad del producto en el pedido.
     */
    public void InsertarPedido(String nif, String producto, int cantidad) {
        SessionFactory sessionFactory = null;
        Transaction transaction = null;

        try {
            // Configurar la sesión de Hibernate
            sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            // Iniciar una nueva transacción
            transaction = session.beginTransaction();

            // Crear un nuevo objeto de pedido con los valores proporcionados
            EntidadPedido pedido = new EntidadPedido();
            pedido.setNif(nif);
            pedido.setProducto(producto);
            pedido.setCantidad(cantidad);

            // Guardar el pedido en la base de datos
            session.save(pedido);

            // Confirmar la transacción
            transaction.commit();
            System.out.println("Pedido insertado correctamente.");
        } catch (Exception e) {
            // Manejar errores durante la transacción
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al insertar el pedido: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar la sesión de Hibernate
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
