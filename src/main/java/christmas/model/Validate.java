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

    //날짜 검증
    public int validateDate(String date) {
        number(date);
        range(date);
        return Integer.parseInt(date);
    }

    //날짜 문자 검증
    public void number(String numbers) {
        if (!numbers.matches(INPUT_CONTENT_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    //날짜 범위 검증
    public void range(String numbers) {
        if (Integer.parseInt(numbers) < MONTH_START_DATE || Integer.parseInt(numbers) > MONTH_END_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    //주문 메뉴 검증
    public HashMap<String, Integer> validateOrder(String orderMenu) {
        List<List> splitOrder = order.splitOrder(orderMenu);
        form(splitOrder);
        orderNumberWord(splitOrder);
        orderNumberRange(splitOrder);
        duplication(splitOrder);
        HashMap<String, Integer> menuAndNumber = order.putOrder(splitOrder);
        menuExist(menuAndNumber);
        drinkOnly(menuAndNumber);
        totalOrderNum(menuAndNumber);
        return menuAndNumber;
    }

    //주문 입력 형식 검증
    public void form(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (l.size() != MENU_NUMBER_LIST_SIZE) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    //주문 메뉴 개수 문자 검증
    public void orderNumberWord(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (!l.get(MENU_NUMBER_POSITION).toString().matches(INPUT_CONTENT_REGEX)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    //주문 메뉴 개수 최소 숫자 검증
    public void orderNumberRange(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (Integer.parseInt(l.get(MENU_NUMBER_POSITION).toString()) < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    //주문 메뉴 중복 검증
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

    //존재 메뉴 검증
    public void menuExist(HashMap<String, Integer> menuAndNumber) {
        int orderNameNum = menuOrderNum(menuAndNumber);
        int orderNum = INITIAL_VALUE_OF_ORDER_NUM;
        for (Integer order : menuAndNumber.values()) {
            orderNum += order;
        }
        if (orderNum != orderNameNum) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    //음료만 주문 검증
    public void drinkOnly(HashMap<String, Integer> menuAndNumber) {
        int drink = menuCheck(menuAndNumber, Menu.Drink.values());
        int orderNameNum = menuOrderNum(menuAndNumber);
        if (orderNameNum == drink) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    //메뉴 주문 개수 계산
    public int menuCheck(HashMap<String, Integer> menuAndNumber, Enum<?>[] menuValues) {
        int orderNum = INITIAL_VALUE_OF_ORDER_NUM;
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

    //전체 주문 개수 20개 이상 검증
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
