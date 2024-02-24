package metodos.anadir;

import org.example.EntidadPedido; // Importa la clase que representa los pedidos
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.sql.Date;

public class AddPedido {

    public void InsertarPedido( String nif, String producto, int cantidad) {
        SessionFactory sessionFactory = null;
        Transaction transaction = null;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            // Crea una instancia de EntidadPedido con los valores proporcionados
            EntidadPedido pedido = new EntidadPedido();
            pedido.setNif(nif);
            pedido.setProducto(producto);
            pedido.setCantidad(cantidad);

            // Guarda el pedido en la base de datos
            session.save(pedido);

            transaction.commit();
            System.out.println("Pedido insertado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al insertar el pedido: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
