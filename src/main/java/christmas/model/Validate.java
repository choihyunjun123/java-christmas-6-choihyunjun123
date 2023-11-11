package christmas.model;

import java.util.*;

public class Validate {
    private static final String INPUT_CONTENT_REGEX = "^[0-9]+$";
    private static final int MONTH_START_DATE = 1;
    private static final int MONTH_END_DATE = 31;
    private static final int MENU_NUMBER_POSITION = 1;
    private static final int MENU_NUMBER_LIST_SIZE = 2;

    private static final Order order = new Order();

    public void validateDate(String date) {
        number(date);
        range(date);
    }

    public void number(String numbers) {
        if (!numbers.matches(INPUT_CONTENT_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
    }

    public void range(String numbers) {
        if (Integer.parseInt(numbers) < MONTH_START_DATE || Integer.parseInt(numbers) > MONTH_END_DATE) {
            throw new IllegalArgumentException("[ERROR] 날짜 입력 오류 입니다.");
        }
    }

    public void validateOrder(String orderMenu) {
        List<List> splitOrder = order.splitOrder(orderMenu);
        form(splitOrder);
        orderNumber(splitOrder);
//        HashMap<String, Integer> menuAndNumber = order.putOrder(splitOrder);
        duplication(splitOrder);
    }

    public void form(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (l.size() != MENU_NUMBER_LIST_SIZE) {
                throw new IllegalArgumentException("[ERROR] 입력 형식 오류 입니다.");
            }
        }
    }

    public void orderNumber(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (!l.get(MENU_NUMBER_POSITION).toString().matches(INPUT_CONTENT_REGEX)) {
                throw new IllegalArgumentException("[ERROR] 주문 숫자 오류 입니다.");
            }
        }
    }

    public void duplication(List<List> splitOrder) {
        List<Object> allMenu = new ArrayList<>();
        Set<Object> organizedMenu = new HashSet<>();
        for (List l : splitOrder) {
            allMenu.add(l.get(0));
            organizedMenu.add(l.get(0));
        }
        if (allMenu.size() != organizedMenu.size()) {
            throw new IllegalArgumentException("[ERROR] 메뉴가 중복입니다.");
        }
    }
}
