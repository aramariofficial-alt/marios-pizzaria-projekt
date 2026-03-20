import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
   // public static void main(String[] args) {
        private Scanner scan = new Scanner(System.in);
        private Menu menu = new Menu();

        public UserInterface(){

        }
        public void newOrder(){
            System.out.println(menu);

            System.out.println("Indtast nummer på pizza? ");
            int productNumber = scan.nextInt() - 1;

            Product chosenProduct = menu.chooseMenuNumber(productNumber);

            System.out.println("Indtast antal? ");
            int quantity = scan.nextInt();

            Order order = new Order();
            OrderLine orderLine = new OrderLine(quantity, chosenProduct);

            System.out.println("""
                Indtast afhentningstidpunkt:
                Tast 1 for 30 min
                Tast 2 for 1 time
                Tast 3 for 1,5 time
                Tast 4 for 2 timer
                """);

            order.setPickupTime(scan.nextInt(), scan.nextInt());

            System.out.println("Du har bestilt: " + quantity + " X " +chosenProduct);
        }






}
