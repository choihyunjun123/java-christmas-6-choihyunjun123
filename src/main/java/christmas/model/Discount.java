package christmas.model;

import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class Discount {

    private static final int INITIAL_VALUE_OF_DISCOUNT = 0;
    private static final int CHRISTMAS_DISCOUNT_DUE_DATE = 25;
    private static final int CHRISTMAS_DISCOUNT_START_PRICE = 1000;
    private static final int CHRISTMAS_DISCOUNT_INCREMENT = 100;

    private static final OutputView outputView = new OutputView();
    private static HashMap<String, Integer> discoutAndNumbers = new HashMap<>();

    public HashMap<String, Integer> totalDiscount(int visitDay, HashMap<String, Integer> menuAndNumber) {
        christmas(visitDay);
        weekday(visitDay, menuAndNumber);
        return discoutAndNumbers;
    }

    public void christmas(int visitDay) {
        int christmasDiscount = INITIAL_VALUE_OF_DISCOUNT;
        if (visitDay <= CHRISTMAS_DISCOUNT_DUE_DATE) {
            christmasDiscount = CHRISTMAS_DISCOUNT_START_PRICE + CHRISTMAS_DISCOUNT_INCREMENT * (visitDay - 1);
        }
        if (christmasDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discoutAndNumbers.put("크리스마스 디데이 할인", christmasDiscount);
        }
    }

    public void weekday(int visitDay, HashMap<String, Integer> menuAndNumber) {
        int weekdayDiscount = INITIAL_VALUE_OF_DISCOUNT;
        int dessertNum = new Validate().menuCheck(menuAndNumber, Menu.Dessert.values());
        LocalDate day = LocalDate.of(2023, 12, visitDay);
        if (!day.getDayOfWeek().toString().equals("FRIDAY") || !day.getDayOfWeek().toString().equals("SATURDAY")) {
            weekdayDiscount = 2023 * dessertNum;
        }
        if (weekdayDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discoutAndNumbers.put("평일 할인", weekdayDiscount);
        }
    }
}
