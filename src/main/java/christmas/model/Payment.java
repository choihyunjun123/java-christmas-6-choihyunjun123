package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class Payment {

    private static final Order order = new Order();

    public int originalAmount(HashMap<String, Integer> menuAndNumber) {
        int totalAmount = 0;
        for (Map.Entry<String, Integer> entry : menuAndNumber.entrySet()) {
            totalAmount += appetizerAmount(entry) + mainAmount(entry) + dessertAmount(entry) + drinkAmount(entry);
        }
        return totalAmount;
    }

    public int appetizerAmount(Map.Entry<String, Integer> entry) {
        int totalAppetizerAmount = 0;
        for (Menu.Appetizer a : Menu.Appetizer.values()) {
            if (String.valueOf(a).equals(entry.getKey())) {
                totalAppetizerAmount += a.totalPrice(entry.getValue());
            }
        }
        return totalAppetizerAmount;
    }

    public int mainAmount(Map.Entry<String, Integer> entry) {
        int totalMainAmount = 0;
        for (Menu.Main a : Menu.Main.values()) {
            if (String.valueOf(a).equals(entry.getKey())) {
                totalMainAmount += a.totalPrice(entry.getValue());
            }
        }
        return totalMainAmount;
    }

    public int dessertAmount(Map.Entry<String, Integer> entry) {
        int totalDessertAmount = 0;
        for (Menu.Dessert a : Menu.Dessert.values()) {
            if (String.valueOf(a).equals(entry.getKey())) {
                totalDessertAmount += a.totalPrice(entry.getValue());
            }
        }
        return totalDessertAmount;
    }

    public int drinkAmount(Map.Entry<String, Integer> entry) {
        int totalDrinkAmount = 0;
        for (Menu.Drink a : Menu.Drink.values()) {
            if (String.valueOf(a).equals(entry.getKey())) {
                totalDrinkAmount += a.totalPrice(entry.getValue());
            }
        }
        return totalDrinkAmount;
    }
}
