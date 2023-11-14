package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class Payment {

    private static final int INITIAL_VALUE_OF_AMOUNT = 0;

    //할인 전 금액
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

    //메뉴 주문 개수 계산
    public int menuCheck(HashMap<String, Integer> menuAndNumber, Enum<?>[] menuValues) {
        int orderNum = INITIAL_VALUE_OF_AMOUNT;
        for (Enum<?> menu : menuValues) {
            orderNum = entryCheck(menu, menuAndNumber, orderNum);
        }
        return orderNum;
    }

    //주문 목록 반복 재생
    public int entryCheck(Enum<?> menu, HashMap<String, Integer> menuAndNumber, int orderNum) {
        for (Map.Entry<String, Integer> entry : menuAndNumber.entrySet()) {
            if (menu.toString().equals(entry.getKey())) {
                orderNum += entry.getValue();
            }
        }
        return orderNum;
    }

    //주문 메뉴 개수 구하기
    public int menuOrderNum(HashMap<String, Integer> menuAndNumber) {
        int appetizer = menuCheck(menuAndNumber, Menu.Appetizer.values());
        int main = menuCheck(menuAndNumber, Menu.Main.values());
        int dessert = menuCheck(menuAndNumber, Menu.Dessert.values());
        int drink = menuCheck(menuAndNumber, Menu.Drink.values());
        return appetizer + main + dessert + drink;
    }
}
