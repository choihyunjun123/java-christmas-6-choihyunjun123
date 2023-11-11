package christmas.model;

import java.util.HashMap;
import java.util.List;

public class Validate {
    private static final String INPUT_CONTENT_REGEX = "^[0-9]+$";
    private static final int MONTH_START_DATE = 1;
    private static final int MONTH_END_DATE = 31;

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
//        HashMap<Object, Object> menuAndNumber = order.putOrder(order.splitOrder(orderMenu));
//        System.out.println(menuAndNumber);
    }

    public void form(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (l.size() != 2) {
                throw new IllegalArgumentException("[ERROR] 입력 형식 오류 입니다.");
            }
        }
    }

    public void orderNumber(List<List> splitOrder) {
        for (List l : splitOrder) {
            if (!l.get(1).toString().matches("^[0-9]+$")) {
                throw new IllegalArgumentException("[ERROR] 주문 숫자 오류 입니다.");
            }
        }
    }
}
