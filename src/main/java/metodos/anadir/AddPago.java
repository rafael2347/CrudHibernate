package metodos.anadir;

import org.example.EntidadPago;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.sql.Date;

public class AddPago {

    public void InsertarPago(int idPedido, BigDecimal monto, Date fecha) {
        SessionFactory sessionFactory = null;
        Transaction transaction = null;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            EntidadPago pago = new EntidadPago();
            pago.setIdPedido(idPedido);
            pago.setMonto(monto);
            pago.setFecha(fecha);

            session.save(pago);

            transaction.commit();
            System.out.println("Pago insertado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al insertar el pago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
