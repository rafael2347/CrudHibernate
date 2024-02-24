package org.example;

import metodos.mostrar.ViewPago;
import metodos.mostrar.ViewPedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import metodos.mostrar.ViewCliente;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al Crud en Hibernate, ¿qué desea hacer?");
        System.out.println("0.Salir");
        System.out.println("1.Mostrar");
        System.out.println("2.Insertar");
        System.out.println("3.Editar");
        System.out.println("4.Eliminar");
        String accion = sc.nextLine();
        switch (accion){
            case "1":
                System.out.println("Qué base de datos quieres ver: cliente, pago, pedido");
                String dato = sc.nextLine();
                if ("cliente".equals(dato)) {
                    ViewCliente cliente = new ViewCliente();
                    cliente.MostrarCliente();
                } else if ("pago".equals(dato)) {
                    ViewPago pago = new ViewPago();
                    pago.MostrarPago();
                    // Lógica para manejar pagos
                } else if ("pedido".equals(dato)) {
                    ViewPedido pedido = new ViewPedido();
                    pedido.MostrarPedido();
                    // Lógica para manejar pedidos
                }
                break;
            default:
                System.out.println("No puedes introducir un número que no esté en el menú y tampoco puedes poner una palabra");
        }
    }
}
