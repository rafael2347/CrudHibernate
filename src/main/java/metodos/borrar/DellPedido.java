package metodos.borrar;

import org.pojos.EntidadPedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Clase para eliminar pedidos de la base de datos.
 */
public class DellPedido {

    /**
     * Método para eliminar pedidos por producto.
     * @param sessionFactory Factoría de sesiones Hibernate.
     * @param producto Producto asociado a los pedidos que se desean eliminar.
     */
    public static void EliminarPedido(SessionFactory sessionFactory, String producto) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Buscar los pedidos asociados al producto
            List<EntidadPedido> pedidos = session.createQuery("FROM EntidadPedido WHERE producto = :producto")
                    .setParameter("producto", producto)
                    .list();

            // Verificar si se encontraron pedidos
            if (!pedidos.isEmpty()) {
                // Si se encontraron, eliminarlos uno por uno
                for (EntidadPedido pedido : pedidos) {
                    session.delete(pedido);
                }
                System.out.println(producto + " Eliminado correctamente.");
            } else {
                // Si no se encontraron pedidos, mostrar un mensaje de error
                System.out.println("No se encontraron pedidos asociados al producto proporcionado.");
            }

            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (transaction != null)
                transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
