package metodos.borrar;

import org.example.EntidadCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class DellCliente {

    public static void eliminarCliente(SessionFactory sessionFactory, String nifCliente) {
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
