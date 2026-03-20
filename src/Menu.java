import java.util.ArrayList;

public class Menu {

    private static final ArrayList<Product> menu = new ArrayList<>();

    public static void addPizza(Product product) {
        menu.add(product);
    }

    public static Product getPizzaByNumber(int number) {
        if (number < 1 || number > menu.size()) {
            return null;
        }
        return menu.get(number - 1);
    }

    public static int size() {
        return menu.size();
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < menu.size(); i++) {
            Product p = menu.get(i);
            result += (i + 1) + ". " + p.getPizza() + " - " + p.getPrice() + " kr\n";
        }

        return result;
    }
}