import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Order order = new Order();
        Pizza pizza = new Pizza("Hawai", "Ananas, skinke");
        Pizza pizza1 = new Pizza("Amerikaner", "Ost, Bacon");

        Product product = new Product(pizza, 54);
        Product product1 = new Product(pizza1, 60);


        OrderLine orderline = new OrderLine(3, product);
        OrderLine orderline1 = new OrderLine(3, product1);

//        System.out.println(orderline);
//        System.out.println(orderline1);

        order.addOrderline(orderline);
        order.addOrderline(orderline1);

        System.out.println(order);

    }
}

//pizza objekter i main
//        Menu menu = buildMenu();

//    private static printCompletedOrders(){
//
//    }

//    private static Menu buildMenu() {
//        Menu menu = new Menu();
//
//        return menu;
//    }
