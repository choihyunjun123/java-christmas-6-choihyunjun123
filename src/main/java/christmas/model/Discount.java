package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private static final int GIFT_PRESENT_AMOUNT = 120000;
    private static final int PRICE_OF_CHAMPAGNE = 25000;
    private static final int INITIAL_VALUE_OF_DISCOUNT = 0;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    private static final int CHRISTMAS_DISCOUNT_DUE_DATE = 25;
    private static final int CHRISTMAS_DISCOUNT_START_PRICE = 1000;
    private static final int CHRISTMAS_DISCOUNT_INCREMENT = 100;
    private static final int WEEK_DISCOUNT_INCREMENT = 2023;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int CHRISTMAS_DAY = 25;
    private static final String CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인";
    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final String WEEKEND_DISCOUNT = "주말 할인";
    private static final String SPECIAL_DISCOUNT = "특별 할인";
    private static final String GIFT_PRESENT = "증정 이벤트";


    private static final HashMap<String, Integer> discountAndNumbers = new HashMap<>();

    //전체 할인 목록
    public HashMap<String, Integer> totalDiscount(int visitDay, int originalPrice, HashMap<String, Integer> menuAndNumber) {
        christmas(visitDay);
        weekday(visitDay, menuAndNumber);
        weekend(visitDay, menuAndNumber);
        special(visitDay);
        giftPut(originalPrice);
        return discountAndNumbers;
    }

    //크리스마스 할인 계산
    public int christmas(int visitDay) {
        int christmasDiscount = INITIAL_VALUE_OF_DISCOUNT;
        if (visitDay <= CHRISTMAS_DISCOUNT_DUE_DATE) {
            christmasDiscount = CHRISTMAS_DISCOUNT_START_PRICE + CHRISTMAS_DISCOUNT_INCREMENT * (visitDay - 1);
        }
        if (christmasDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discountAndNumbers.put(CHRISTMAS_DISCOUNT, christmasDiscount);
        }
        return christmasDiscount;
    }

    //평일 할인 계산
    public int weekday(int visitDay, HashMap<String, Integer> menuAndNumber) {
        int weekdayDiscount = INITIAL_VALUE_OF_DISCOUNT;
        int dessertNum = new Payment().menuCheck(menuAndNumber, Menu.Dessert.values());
        LocalDate day = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
            weekdayDiscount = WEEK_DISCOUNT_INCREMENT * dessertNum;
        }
        if (weekdayDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discountAndNumbers.put(WEEKDAY_DISCOUNT, weekdayDiscount);
        }
        return weekdayDiscount;
    }

    //주말 할인 계산
    public int weekend(int visitDay, HashMap<String, Integer> menuAndNumber) {
        int weekendDiscount = INITIAL_VALUE_OF_DISCOUNT;
        int mainNum = new Payment().menuCheck(menuAndNumber, Menu.Main.values());
        LocalDate day = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            weekendDiscount = WEEK_DISCOUNT_INCREMENT * mainNum;
        }
        if (weekendDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discountAndNumbers.put(WEEKEND_DISCOUNT, weekendDiscount);
        }
        return weekendDiscount;
    }

    //특별 할인 계산
    public int special(int visitDay) {
        int specialDiscount = INITIAL_VALUE_OF_DISCOUNT;
        LocalDate day = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY || visitDay == CHRISTMAS_DAY) {
            specialDiscount = SPECIAL_DISCOUNT_AMOUNT;
        }
        if (specialDiscount != INITIAL_VALUE_OF_DISCOUNT) {
            discountAndNumbers.put(SPECIAL_DISCOUNT, SPECIAL_DISCOUNT_AMOUNT);
        }
        return specialDiscount;
    }

    //증정 유무 계산
    public boolean giftPut(int originalPrice) {
        if (originalPrice >= GIFT_PRESENT_AMOUNT) {
            discountAndNumbers.put(GIFT_PRESENT, PRICE_OF_CHAMPAGNE);
            return true;
        }
        return false;
    }

    //총 혜택 금액 계산
    public int totalDiscountAmount(HashMap<String, Integer> discount) {
        int totalDiscount = 0;
        for (Map.Entry<String, Integer> entry : discount.entrySet()) {
            totalDiscount += entry.getValue();
        }
        return totalDiscount;
    }

    public int fianlPayAmount(int originalPrice, int totalDiscount) {
        int finalPay = 0;
        if (giftPut(originalPrice)) {
            finalPay = originalPrice - totalDiscount + PRICE_OF_CHAMPAGNE;
        }
        if (!giftPut(originalPrice)) {
            finalPay = originalPrice - totalDiscount;
        }
        return finalPay;
    }
}