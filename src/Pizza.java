public class Pizza {

    private final String name;
    private final String ingredients;

    public Pizza(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }
}

//    @Override
//    public String toString() {
//        return String.format();
//    }



//VESUVIO(1, "Vesuvio", "tomatsauce, ost, skinke og oregano", 57),
//AMERIKANER(2,"Amerikaner", "tomatsauce, ost, oksefars og oregano",53),
//CACCIATORE(3,"Cacciatore","tomatsauce, ost, pepperoni og oregano", 57),
//CARBONA(4,"Carbona","tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano", 63),
//DENNIS(5,"Dennis","tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano",65),
//BERTIL(6,"Bertil","tomatsauce, ost, bacon og oregano",57),
//SILVIA(7,"Silvia","tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano",61),
//VICTORIA(8,"Victoria","tomatsauce, ost, skinke, ananas, champignon, løg og oregano",61),
//TORONFO(9,"Toronfo","tomatsauce, ost, skinke, bacon, kebab, chili og oregano",61),
//CAPPRICIOSA(10,"Capriciosa","tomatsauce, ost, skinke, champignon og oregano",61),
//HAWAI(11,"Hawai","tomatsauce, ost, skinke, ananas og oregano",61),
//LEBLISSOLA(12,"Le Blissola","tomatsauce, ost, skinke, rejer og oregano",61),
//VENEZIA(13,"Venezia","tomatsauce, ost, skinke, bacon og oregano",61),
//MAFIA(14,"Mafia","tomatsauce, ost, pepperoni, bacon, løg og oregano",61);