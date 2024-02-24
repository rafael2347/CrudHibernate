package metodos.mostrar;

import org.example.EntidadCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AddCliente {



        public void InsertarCliente(String nombre, String apellido, String nif) {
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
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                System.out.println("Error al insertar el cliente.");
            }
        }
    }


