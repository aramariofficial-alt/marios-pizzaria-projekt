import java.util.ArrayList;

public class Menu {

    private final ArrayList<Product> menu = new ArrayList<>();

    public void addProduct(Product product) {
        menu.add(product);
    }

    public Product getProductByNumber(int number) {
        if (number < 0 || number >= menu.size()) {
            return null;
        }
        return menu.get(number);
    }

    public int getProductCount() {
        return menu.size();
    }



    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < menu.size(); i++) {
            Product p = menu.get(i);
            result += (i + 1) + ". " + p.getPizza() + ": " + p.getIngredients() + " - " + p.getPrice() + " kr\n";
        }

        return result;
    }
}