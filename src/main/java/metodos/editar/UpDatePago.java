package metodos.editar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.EntidadPago;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class UpDatePago {

    public static void ModificarPago(SessionFactory sessionFactory, int idPago, int idPedido, BigDecimal monto, Date fecha) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Buscar el pago por su ID
            Query<EntidadPago> query = session.createQuery("FROM EntidadPago WHERE idPago = :idPago");
            query.setParameter("idPago", idPago);
            List<EntidadPago> pagos = query.list();

            // Verificar si se encontró el pago
            if (!pagos.isEmpty()) {
                // Obtener el pago encontrado
                EntidadPago pago = pagos.get(0);

                // Actualizar los datos del pago
                pago.setIdPedido(idPedido);
                pago.setMonto(monto);
                pago.setFecha(fecha);

                // Guardar los cambios en la base de datos
                session.update(pago);
                transaction.commit();
                System.out.println("Pago actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún pago con el ID proporcionado.");
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
