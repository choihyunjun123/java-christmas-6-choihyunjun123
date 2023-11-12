package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class Payment {

    private static final int INITIAL_VALUE_OF_AMOUNT = 0;

    public int originalAmount(HashMap<String, Integer> menuAndNumber) {
        int totalAmount = INITIAL_VALUE_OF_AMOUNT;
        for (Map.Entry<String, Integer> entry : menuAndNumber.entrySet()) {
            totalAmount += appetizerAmount(entry) + mainAmount(entry) + dessertAmount(entry) + drinkAmount(entry);
        }
        return totalAmount;
    }

    //에피타이저 구입 금액
    public int appetizerAmount(Map.Entry<String, Integer> entry) {
        int totalAppetizerAmount = INITIAL_VALUE_OF_AMOUNT;
        for (Menu.Appetizer appe : Menu.Appetizer.values()) {
            if (String.valueOf(appe).equals(entry.getKey())) {
                totalAppetizerAmount += appe.totalPrice(entry.getValue());
            }
        }
        return totalAppetizerAmount;
    }

    //메인 구입 금액
    public int mainAmount(Map.Entry<String, Integer> entry) {
        int totalMainAmount = INITIAL_VALUE_OF_AMOUNT;
        for (Menu.Main main : Menu.Main.values()) {
            if (String.valueOf(main).equals(entry.getKey())) {
                totalMainAmount += main.totalPrice(entry.getValue());
            }
        }
        return totalMainAmount;
    }

    //디져트 구입 금액
    public int dessertAmount(Map.Entry<String, Integer> entry) {
        int totalDessertAmount = INITIAL_VALUE_OF_AMOUNT;
        for (Menu.Dessert dess : Menu.Dessert.values()) {
            if (String.valueOf(dess).equals(entry.getKey())) {
                totalDessertAmount += dess.totalPrice(entry.getValue());
            }
        }
        return totalDessertAmount;
    }

    //음료수 구입 금액
    public int drinkAmount(Map.Entry<String, Integer> entry) {
        int totalDrinkAmount = INITIAL_VALUE_OF_AMOUNT;
        for (Menu.Drink drink : Menu.Drink.values()) {
            if (String.valueOf(drink).equals(entry.getKey())) {
                totalDrinkAmount += drink.totalPrice(entry.getValue());
            }
        }
        return totalDrinkAmount;
    }
}
