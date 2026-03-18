import java.util.ArrayList;

public class Menu {

    static ArrayList<Pizza> menu = new ArrayList<>();

    public static void addPizza(Pizza pizza){
        menu.add(pizza);

    }

    public String toString(){
       return String.format("""
                %s
                """, menu);
    }
}
