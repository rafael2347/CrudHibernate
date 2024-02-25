package metodos.editar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.EntidadPedido;

import java.util.List;
import java.util.Scanner;

public class UpDatePedido {

    public static void ModificarPedido(SessionFactory sessionFactory, String producto) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Buscar el pedido por su producto
            Query<EntidadPedido> query = session.createQuery("FROM EntidadPedido WHERE producto = :producto");
            query.setParameter("producto", producto);
            List<EntidadPedido> pedidos = query.list();

            // Verificar si se encontró el pedido
            if (!pedidos.isEmpty()) {
                // Obtener el primer pedido encontrado (en caso de que haya más de uno con el mismo producto)
                EntidadPedido pedido = pedidos.get(0);

                // Solicitar nuevos datos al usuario
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ingrese el nuevo NIF del cliente:");
                String nuevoNIF = scanner.nextLine();
                System.out.println("Ingrese la nueva cantidad:");
                int nuevaCantidad = Integer.parseInt(scanner.nextLine());
                System.out.println("Ingrese el nuevo producto:");
                String nuevoProducto = scanner.nextLine();

                // Actualizar los datos del pedido
                pedido.setNif(nuevoNIF);
                pedido.setCantidad(nuevaCantidad);
                pedido.setProducto(nuevoProducto);

                // Guardar los cambios en la base de datos
                session.update(pedido);
                transaction.commit();
                System.out.println("Pedido actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún pedido con el producto proporcionado.");
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
