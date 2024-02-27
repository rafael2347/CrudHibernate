package metodos.borrar;

import org.pojos.EntidadCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Clase para eliminar un cliente de la base de datos.
 */
public class DellCliente {

    /**
     * Método para eliminar un cliente por su NIF.
     * @param sessionFactory Factoría de sesiones Hibernate.
     * @param nifCliente NIF del cliente a eliminar.
     */
    public static void EliminarCliente(SessionFactory sessionFactory, String nifCliente) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Obtener el cliente por su NIF
            EntidadCliente cliente = session.get(EntidadCliente.class, nifCliente);

            // Verificar si se encontró el cliente
            if (cliente != null) {
                // Si se encontró, eliminarlo
                session.delete(cliente);
                System.out.println("Cliente eliminado correctamente.");
            } else {
                // Si no se encontró, mostrar un mensaje de error
                System.out.println("No se encontró ningún cliente con el NIF proporcionado.");
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
