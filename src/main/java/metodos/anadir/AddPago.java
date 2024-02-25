package metodos.anadir;

import org.example.EntidadPago;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase para agregar un nuevo pago a la base de datos.
 */
public class AddPago {

    /**
     * Método para insertar un nuevo pago en la base de datos.
     * @param idPedido ID del pedido asociado al pago.
     * @param monto Monto del pago.
     * @param fecha Fecha del pago.
     */
    public void InsertarPago(int idPedido, BigDecimal monto, Date fecha) {
        SessionFactory sessionFactory = null;
        Transaction transaction = null;

        try {
            // Configurar la sesión de Hibernate
            sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            // Iniciar una nueva transacción
            transaction = session.beginTransaction();

            // Crear un nuevo objeto de pago
            EntidadPago pago = new EntidadPago();
            pago.setIdPedido(idPedido);
            pago.setMonto(monto);
            pago.setFecha(fecha);

            // Guardar el pago en la base de datos
            session.save(pago);

            // Confirmar la transacción
            transaction.commit();
            System.out.println("Pago insertado correctamente.");
        } catch (Exception e) {
            // Manejar errores durante la transacción
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al insertar el pago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar la sesión de Hibernate
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
