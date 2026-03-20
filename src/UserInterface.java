import java.util.Scanner;

public class UserInterface {
    // public static void main(String[] args) {
    private Scanner scan = new Scanner(System.in);
    private Menu menu;

    public UserInterface(Menu menu) {
        this.menu = menu;

    }

    public void start() {
        boolean quit = false;
        while (!quit) {

            System.out.println("""
                    1. Opret Bestilling
                    2. Aktive Ordrer
                    3. Admin
                    4. Afslut
                    """);
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> newOrder();
                //case 2 -> printactiveOrders();
                //case 3 -> admin();
                case 4 -> quit = true;
                default -> System.out.println("Ugyldigt nummer, prøv igen: ");
            }
        }

    }

    private void newOrder() {

        OrderManager orderManager = new OrderManager();
        Order order = new Order();

        System.out.println(menu);
        boolean orderDone = false;

        while (!orderDone) {
            System.out.println("Indtast nummer på pizza? ");
            int productNumber = scan.nextInt() - 1;
            Product chosenProduct = menu.getPizzaByNumber(productNumber);

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
        System.out.println("""
                Afhentningstidpunkt:
                Tast 0 for hurtigst muligt
                Tast 1 for at vælge afhentningstispunkt
                """);

        //Herfra virker det ikke endnu...

        order.addPickUpTime(scan.nextInt(), scan.nextInt());

        System.out.println("Du har bestilt: " + order);
    }
}