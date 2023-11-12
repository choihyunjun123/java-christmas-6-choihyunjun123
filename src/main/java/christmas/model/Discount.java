package christmas.model;

import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private static final int INITIAL_VALUE_OF_DISCOUNT = 0;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    private static final int CHRISTMAS_DISCOUNT_DUE_DATE = 25;
    private static final int CHRISTMAS_DISCOUNT_START_PRICE = 1000;
    private static final int CHRISTMAS_DISCOUNT_INCREMENT = 100;
    private static final int WEEK_DISCOUNT_INCREMENT = 2023;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int CHRISTMAS_DAY = 25;

    private static final HashMap<String, Integer> discoutAndNumbers = new HashMap<>();

    //할일 목록 구하기
    public HashMap<String, Integer> totalDiscount(int visitDay, HashMap<String, Integer> menuAndNumber) {
        christmas(visitDay);
        weekday(visitDay, menuAndNumber);
        weekend(visitDay, menuAndNumber);
        special(visitDay);
        return discoutAndNumbers;
    }

    //크리스마스 할인 계산
    public int christmas(int visitDay) {
        int christmasDiscount = INITIAL_VALUE_OF_DISCOUNT;
        if (visitDay <= CHRISTMAS_DISCOUNT_DUE_DATE) {
            christmasDiscount = CHRISTMAS_DISCOUNT_START_PRICE + CHRISTMAS_DISCOUNT_INCREMENT * (visitDay - 1);
        }
        if (christmasDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discoutAndNumbers.put("크리스마스 디데이 할인", christmasDiscount);
        }
        return christmasDiscount;
    }

    //평일 할인 계산
    public int weekday(int visitDay, HashMap<String, Integer> menuAndNumber) {
        int weekdayDiscount = INITIAL_VALUE_OF_DISCOUNT;
        int dessertNum = new Validate().menuCheck(menuAndNumber, Menu.Dessert.values());
        LocalDate day = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);
        if (!day.getDayOfWeek().toString().equals("FRIDAY") || !day.getDayOfWeek().toString().equals("SATURDAY")) {
            weekdayDiscount = WEEK_DISCOUNT_INCREMENT * dessertNum;
        }
        if (weekdayDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discoutAndNumbers.put("평일 할인", weekdayDiscount);
        }
        return weekdayDiscount;
    }

    //주말 할인 계산
    public int weekend(int visitDay, HashMap<String, Integer> menuAndNumber) {
        int weekendDiscount = INITIAL_VALUE_OF_DISCOUNT;
        int mainNum = new Validate().menuCheck(menuAndNumber, Menu.Main.values());
        LocalDate day = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);
        if (day.getDayOfWeek().toString().equals("FRIDAY") || day.getDayOfWeek().toString().equals("SATURDAY")) {
            weekendDiscount = WEEK_DISCOUNT_INCREMENT * mainNum;
        }
        if (weekendDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discoutAndNumbers.put("주말 할인", weekendDiscount);
        }
        return weekendDiscount;
    }

    //특별 할인 계산
    public int special(int visitDay) {
        int specialDiscount = INITIAL_VALUE_OF_DISCOUNT;
        LocalDate day = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);
        if (day.getDayOfWeek().toString().equals("SUNDAY") || visitDay == CHRISTMAS_DAY) {
            specialDiscount = SPECIAL_DISCOUNT_AMOUNT;
        }
        if (specialDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discoutAndNumbers.put("특별 할인", SPECIAL_DISCOUNT_AMOUNT);
        }
        return specialDiscount;
    }

    //총 할인 금액 표시
    public int totalDiscountAmount(HashMap<String, Integer> discount) {
        int totalDiscount = 0;
        for (Map.Entry<String, Integer> entry : discount.entrySet()) {
            totalDiscount += entry.getValue();
        }
        return totalDiscount;
    }
}
