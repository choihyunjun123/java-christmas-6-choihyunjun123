package christmas.model;

import java.util.*;

public class Validate {

    private static final String INPUT_CONTENT_REGEX = "^[0-9]+$";
    private static final int MONTH_START_DATE = 1;
    private static final int MONTH_END_DATE = 31;
    private static final int MENU_NAME_POSITION = 0;
    private static final int MENU_NUMBER_POSITION = 1;
    private static final int MENU_NUMBER_LIST_SIZE = 2;
    private static final int INITIAL_VALUE_OF_ORDER_NUM = 0;
    private static final int MAXIMUM_VALUE_OF_ORDER_NUM = 20;

    private static final Order order = new Order();
    private static final Payment payment = new Payment();

    public int validateDate(String date) {
        validateNumber(date);
        validateRange(date);
        return Integer.parseInt(date);
    }

    public void validateNumber(String numbers) {
        if (!numbers.matches(INPUT_CONTENT_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateRange(String numbers) {
        int date = Integer.parseInt(numbers);
        if (date < MONTH_START_DATE || date > MONTH_END_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public HashMap<String, Integer> validateOrder(String orderMenu) {
        List<List> splitOrder = order.splitOrder(orderMenu);
        orderForm(splitOrder);
        orderNumberWord(splitOrder);
        orderNumberRange(splitOrder);
        orderDuplication(splitOrder);
        HashMap<String, Integer> menuAndNumber = order.putOrder(splitOrder);
        menuExist(menuAndNumber);
        drinkOnly(menuAndNumber);
        totalOrderNum(menuAndNumber);
        return menuAndNumber;
    }

    public void orderForm(List<List> splitOrder) {
        for (List<String> l : splitOrder) {
            if (l.size() != MENU_NUMBER_LIST_SIZE) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public void orderNumberWord(List<List> splitOrder) {
        for (List<String> l : splitOrder) {
            if (!l.get(MENU_NUMBER_POSITION).matches(INPUT_CONTENT_REGEX)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public void orderNumberRange(List<List> splitOrder) {
        for (List<String> l : splitOrder) {
            int orderNumber = Integer.parseInt(l.get(MENU_NUMBER_POSITION));
            if (orderNumber < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public void orderDuplication(List<List> splitOrder) {
        List<String> allMenu = new ArrayList<>();
        Set<String> organizedMenu = new HashSet<>();
        for (List<String> l : splitOrder) {
            allMenu.add(l.get(MENU_NAME_POSITION));
            organizedMenu.add(l.get(MENU_NAME_POSITION));
        }
        if (allMenu.size() != organizedMenu.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void menuExist(HashMap<String, Integer> menuAndNumber) {
        int orderNameNum = payment.menuOrderNum(menuAndNumber);
        int orderNum = INITIAL_VALUE_OF_ORDER_NUM;
        for (Integer order : menuAndNumber.values()) {
            orderNum += order;
        }
        if (orderNum != orderNameNum) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void drinkOnly(HashMap<String, Integer> menuAndNumber) {
        int drink = payment.menuCheck(menuAndNumber, Menu.Drink.values());
        int orderNameNum = payment.menuOrderNum(menuAndNumber);
        if (orderNameNum == drink) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void totalOrderNum(HashMap<String, Integer> menuAndNumber) {
        int totalNum = INITIAL_VALUE_OF_ORDER_NUM;
        for (int total : menuAndNumber.values()) {
            totalNum += total;
        }
        if (totalNum > MAXIMUM_VALUE_OF_ORDER_NUM) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}