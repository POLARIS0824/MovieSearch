import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Cocktail cocktail0 = new Cocktail("Cocktail0", 10f, 50, 120);
        Cocktail cocktail1 = new Cocktail("Cocktail1", 30f, 100, 300);
        Cocktail cocktail2 = new Cocktail("Cocktail2", 12.8f, 50, 150);
        Cocktail cocktail3 = new Cocktail("Soda", 0f, 0, 330);
        Cocktail cocktail4 = new Cocktail("Cocktail4", 43.2f, 80, 120);

        List<Cocktail> cocktailList = new ArrayList<>();
        cocktailList.add(cocktail0);
        cocktailList.add(cocktail1);
        cocktailList.add(cocktail2);
        cocktailList.add(cocktail3);
        cocktailList.add(cocktail4);

        cocktailList.sort();

        cocktailList.forEach(System.out::println);
    }
}