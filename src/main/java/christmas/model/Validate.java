package christmas.model;

import java.util.*;

public class Validate {
    private static final String INPUT_CONTENT_REGEX = "^[0-9]+$";
    private static final int MONTH_START_DATE = 1;
    private static final int MONTH_END_DATE = 31;
    private static final int MENU_NAME_POSITION = 0;
    private static final int MENU_NUMBER_POSITION = 1;
    private static final int MENU_NUMBER_LIST_SIZE = 2;

    private static final Order order = new Order();

    public void validateDate(String date) {
        number(date);
        range(date);
    }

    public void number(String numbers) {
        if (!numbers.matches(INPUT_CONTENT_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void range(String numbers) {
        if (Integer.parseInt(numbers) < MONTH_START_DATE || Integer.parseInt(numbers) > MONTH_END_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateOrder(String orderMenu) {
        List<List> splitOrder = order.splitOrder(orderMenu);
        form(splitOrder);
        orderNumber(splitOrder);
        duplication(splitOrder);
        HashMap<String, Integer> menuAndNumber = order.putOrder(splitOrder);
        menuExist(menuAndNumber);
    }

    public void form(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (l.size() != MENU_NUMBER_LIST_SIZE) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public void orderNumber(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (!l.get(MENU_NUMBER_POSITION).toString().matches(INPUT_CONTENT_REGEX)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public void duplication(List<List> splitOrder) {
        List<Object> allMenu = new ArrayList<>();
        Set<Object> organizedMenu = new HashSet<>();
        for (List l : splitOrder) {
            allMenu.add(l.get(MENU_NAME_POSITION));
            organizedMenu.add(l.get(MENU_NAME_POSITION));
        }
        if (allMenu.size() != organizedMenu.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void menuExist(HashMap<String, Integer> menuAndNumber) {
        int appetizer = appetizerCheck(menuAndNumber);
        int main = mainCheck(menuAndNumber);
        int dessert = dessertCheck(menuAndNumber);
        int drink = drinkCheck(menuAndNumber);
        int orderNum = appetizer + main + dessert + drink;
        if (menuAndNumber.size() != orderNum) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        System.out.println(orderNum);
//        if (c.equals("양송이스프")) {
//            System.out.println(1);
//        }
    }

    public int appetizerCheck(HashMap<String, Integer> menuAndNumber) {
        int orderNum = 0;
        for (Menu.Appetizer menu : Menu.Appetizer.values()) {
            if (menuAndNumber.containsKey(String.valueOf(menu))) {
                orderNum++;
            }
        }
        return orderNum;
    }

    public int mainCheck(HashMap<String, Integer> menuAndNumber) {
        int orderNum = 0;
        for (Menu.Main menu : Menu.Main.values()) {
            if (menuAndNumber.containsKey(String.valueOf(menu))) {
                orderNum++;
            }
        }
        return orderNum;
    }

    public int dessertCheck(HashMap<String, Integer> menuAndNumber) {
        int orderNum = 0;
        for (Menu.Dessert menu : Menu.Dessert.values()) {
            if (menuAndNumber.containsKey(String.valueOf(menu))) {
                orderNum++;
            }
        }
        return orderNum;
    }

    public int drinkCheck(HashMap<String, Integer> menuAndNumber) {
        int orderNum = 0;
        for (Menu.Drink menu : Menu.Drink.values()) {
            if (menuAndNumber.containsKey(String.valueOf(menu))) {
                orderNum++;
            }
        }
        return orderNum;
    }
}
