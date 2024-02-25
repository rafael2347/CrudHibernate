package org.example;

import metodos.borrar.DellPago;
import metodos.borrar.DellPedido;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import metodos.anadir.AddCliente;
import metodos.anadir.AddPago;
import metodos.anadir.AddPedido;
import metodos.borrar.DellCliente;
import metodos.mostrar.ViewCliente;
import metodos.mostrar.ViewPago;
import metodos.mostrar.ViewPedido;

import java.math.BigDecimal;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        // Inicializar sessionFactory
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al Crud en Hibernate, ¿qué desea hacer?");
        System.out.println("0.Salir");
        System.out.println("1.Mostrar");
        System.out.println("2.Insertar");
        System.out.println("3.Editar");
        System.out.println("4.Eliminar");
        String accion = sc.nextLine();
        switch (accion) {
            case "1":
                System.out.println("Qué base de datos quieres ver: cliente, pago, pedido");
                String dato = sc.nextLine();
                if ("cliente".equals(dato)) {
                    ViewCliente cliente = new ViewCliente();
                    cliente.MostrarCliente();
                } else if ("pago".equals(dato)) {
                    ViewPago pago = new ViewPago();
                    pago.MostrarPago();
                } else if ("pedido".equals(dato)) {
                    ViewPedido pedido = new ViewPedido();
                    pedido.MostrarPedido();
                }
                break;
            case "2":
                System.out.println("En qué base de datos quieres añadir: cliente, pago, pedido");
                String add = sc.nextLine();
                if ("cliente".equals(add)) {
                    System.out.println("Ingrese el nombre del cliente:");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el apellido del cliente:");
                    String apellido = sc.nextLine();
                    System.out.println("Ingrese el NIF del cliente:");
                    String nif = sc.nextLine();
                    AddCliente cliente = new AddCliente();
                    cliente.InsertarCliente(nombre, apellido, nif);
                } else if ("pago".equals(add)) {
                    System.out.println("Ingrese el ID del pedido:");
                    int idPedido = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingrese el monto:");
                    BigDecimal monto = new BigDecimal(sc.nextLine());
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date fecha = new java.sql.Date(utilDate.getTime());
                    AddPago pago = new AddPago();
                    pago.InsertarPago(idPedido, monto, fecha);
                } else if ("pedido".equals(add)) {
                    System.out.println("Ingrese el NIF del cliente:");
                    String nif = sc.nextLine();
                    System.out.println("Ingrese el producto:");
                    String producto = sc.nextLine();
                    System.out.println("Ingrese la cantidad:");
                    int cantidad = Integer.parseInt(sc.nextLine());
                    AddPedido pedido = new AddPedido();
                    pedido.InsertarPedido(nif, producto, cantidad);
                }
                break;
            case "4":
                System.out.println("Ingrese la base de datos de la que desea eliminar un registro: cliente, pago, pedido");
                String dell = sc.nextLine();
                if ("cliente".equals(dell)) {
                    // Utilizar Scanner para capturar la entrada del usuario
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Ingrese el NIF del cliente que desea eliminar:");
                    String nifClienteAEliminar = scanner.nextLine(); // Leer el NIF ingresado por el usuario

                    // Llamar al método para eliminar el cliente
                    DellCliente.EliminarCliente(sessionFactory, nifClienteAEliminar);

                    // Cerrar el scanner
                    scanner.close();
                } else if ("pago".equals(dell)) {
                    System.out.println("Ingrese el ID del pago a eliminar:");
                    int idPago = Integer.parseInt(sc.nextLine());

                    // Llamar al método para eliminar el cliente
                    DellPago.EliminarPago(sessionFactory, idPago);
                } else if ("pedido".equals(dell)) {
                    System.out.println("Ingrese el producto a eliminar:");
                    String producto = sc.nextLine();

                    // Llamar al método para eliminar el pedido por producto
                    DellPedido.EliminarPedido(sessionFactory, producto);
                }


                break;
            default:
                System.out.println("No puedes introducir un número que no esté en el menú y tampoco puedes poner una palabra");
        }
    }
}
