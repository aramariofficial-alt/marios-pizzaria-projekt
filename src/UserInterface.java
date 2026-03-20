import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // public static void main(String[] args) {
    private Scanner scan = new Scanner(System.in);
    private Menu menu;
    private OrderManager orderManager;

    public UserInterface(Menu menu, OrderManager orderManager) {
        this.menu = menu;
        this.orderManager = orderManager;

    }

    public void start() {
        boolean quit = false;
        while (!quit) {

            System.out.println("""
                    1. Opret Bestilling
                    2. Betal
                    3. Aktive Ordrer
                    4. Admin
                    5. Afslut
                    """);
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> newOrder();
                case 2 -> payOrder();
                //case 3 printactiveOrders();
                //case 4 -> admin();
                case 5 -> quit = true;
                default -> System.out.println("Ugyldigt nummer, prøv igen: ");
            }
        }
    }

    private void payOrder(){
        ArrayList<Order> activeOrders = this.orderManager.activeOrders();
        System.out.println("Hvilken ordre bliver betalt");
        System.out.println("0 for at annullere");
        System.out.println();
        activeOrders.forEach(order -> {
            System.out.println(order.getOrderNumberString());
        });
        int choice = scan.nextInt();
        if (choice == 0){
            return; //annullere betaling
        }
        double totalPrice = orderManager.getOrderTotal(choice);
        System.out.println("Total: " + totalPrice);
        orderManager.payOrder(choice);

    }



    private void newOrder() {
        Order order = new Order();

        System.out.println(menu);
        boolean orderDone = false;

        while (!orderDone) {
            System.out.println("Indtast nummer på pizza? ");
            int productNumber = scan.nextInt() - 1;
            Product chosenProduct = menu.getProductByNumber(productNumber);

            System.out.println("Indtast antal? ");
            int quantity = scan.nextInt();

            OrderLine orderLine = new OrderLine(quantity, chosenProduct);
            order.addOrderline(orderLine);


            System.out.println("Tryk 0 for at afslutte bestillingen");
            System.out.println("Tryk 1 for at tilføje flere pizzaer");


            while(true){
                int choice = scan.nextInt();
                if(choice == 0) {
                    orderDone = true;
                    break;
                }else if(choice == 1) {
                    break;
                }else{System.out.println("""
                            Ugyldig indtastning!
                            Tryk 0 for at afslutte bestillingen
                            Tryk 1 for at tilføje flere pizzaer""");
                }
            }
        }


        orderManager.addOrder(order);













//        System.out.println("""
//                Afhentningstidpunkt:
//                Tast 0 for hurtigst muligt
//                Tast 1 for at vælge afhentningstispunkt
//                """);
//
//        //Herfra virker det ikke endnu...
//
//        order.addPickUpTime(scan.nextInt(), scan.nextInt());
//
//        System.out.println("Du har bestilt: " + order);
    }
}