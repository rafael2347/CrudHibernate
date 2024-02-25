package metodos.anadir;

import org.example.EntidadCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Clase para agregar un nuevo cliente a la base de datos.
 */
public class AddCliente {

    /**
     * Método para insertar un nuevo cliente en la base de datos.
     * @param nombre Nombre del cliente.
     * @param apellido Apellido del cliente.
     * @param nif NIF del cliente.
     */
    public void InsertarCliente(String nombre, String apellido, String nif) {
        // Configurar la sesión de Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Crear un nuevo cliente
            EntidadCliente cliente = new EntidadCliente();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setNif(nif);

            // Guardar el cliente en la base de datos
            session.save(cliente);

            // Confirmar la transacción
            transaction.commit();
            System.out.println("Cliente insertado correctamente.");
        } catch (Exception e) {
            // Manejar errores durante la transacción
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al insertar el cliente.");
        } finally {
            // Cerrar la sesión de Hibernate
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
