package metodos.editar;

import org.example.EntidadCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class UpDateCliente {
    public static void ModificarCliente(SessionFactory sessionFactory, String nifCliente) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            // Consultar el cliente por su NIF
            Query<EntidadCliente> query = session.createQuery("FROM EntidadCliente WHERE nif = :nifCliente");
            query.setParameter("nifCliente", nifCliente);
            List<EntidadCliente> clientes = query.list();

            // Verificar si se encontró el cliente
            if (!clientes.isEmpty()) {
                transaction = session.beginTransaction();

                // Obtener el cliente encontrado
                EntidadCliente cliente = clientes.get(0);

                // Solicitar nuevos datos al usuario
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ingrese el nuevo nombre del cliente:");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Ingrese el nuevo apellido del cliente:");
                String nuevoApellido = scanner.nextLine();

                // Actualizar los datos del cliente
                cliente.setNombre(nuevoNombre);
                cliente.setApellido(nuevoApellido);

                // Guardar los cambios en la base de datos
                session.update(cliente);
                transaction.commit();
                System.out.println("Cliente actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún cliente con el NIF proporcionado.");
            }
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
