package metodos.borrar;

import org.pojos.EntidadPago;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Clase para eliminar un pago de la base de datos.
 */
public class DellPago {

    /**
     * Método para eliminar un pago por su ID.
     * @param sessionFactory Factoría de sesiones Hibernate.
     * @param idPago ID del pago a eliminar.
     */
    public static void EliminarPago(SessionFactory sessionFactory, int idPago) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Obtener el pago por su ID
            EntidadPago pago = session.get(EntidadPago.class, idPago);

            // Verificar si se encontró el pago
            if (pago != null) {
                // Si se encontró, eliminarlo
                session.delete(pago);
                System.out.println("Pago eliminado correctamente.");
            } else {
                // Si no se encontró, mostrar un mensaje de error
                System.out.println("No se encontró ningún pago con el ID proporcionado.");
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
