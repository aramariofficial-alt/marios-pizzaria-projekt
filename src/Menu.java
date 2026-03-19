import java.util.ArrayList;

public class Menu {

    static ArrayList<Product> menu = new ArrayList<>();

    public static void addPizza(Product product) {
        menu.add(product);

    }

    @Override
    public String toString() {
        String result = "";

        for (Product p : menu) {
            result += p.getPizza() + " - " + p.getPrice() + " kr\n";
        }

        return result;
    }
}