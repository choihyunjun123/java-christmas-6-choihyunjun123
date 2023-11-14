package christmas;

import christmas.model.EventBadge;
import christmas.model.Discount;
import christmas.model.Payment;
import christmas.model.Validate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Application {

    private static final int PRICE_OF_EVENT_START = 10000;

    private static int visitDay;
    private static int originalPrice;

    private static final DecimalFormat won = new DecimalFormat("###,###");
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final Validate validate = new Validate();
    private static final Discount discount = new Discount();
    private static HashMap<String, Integer> menuAndNumber = new HashMap<>();
    private static HashMap<String, Integer> discountAndAmount = new HashMap<>();

    //입력 날짜 검증
    public static void validateDate() {
        try {
            visitDay = validate.validateDate(inputView.readDate());
            validateMenu();
            outputView.preview(visitDay);
        } catch (IllegalArgumentException e) {
            outputView.error(e.getMessage());
            validateDate();
        }
    }

    //입력 메뉴 검증
    public static void validateMenu() {
        try {
            menuAndNumber = validate.validateOrder(inputView.readMenu());
        } catch (IllegalArgumentException e) {
            outputView.error(e.getMessage());
            validateMenu();
        }
    }

    //주문 메뉴 출력
    public static void showOrder(HashMap<String, Integer> orders) {
        for (Map.Entry<String, Integer> entry : orders.entrySet()) {
            outputView.orderList(entry.getKey(), entry.getValue());
        }
    }

    //할인 전 총주문 금액 출력
    public static void showOriginalPrice() {
        originalPrice = new Payment().originalAmount(menuAndNumber);
        outputView.originalPrice(won.format(originalPrice));
    }

    //증정 매뉴 출력
    public static void showGift(int originalPrice) {
        outputView.giftStart();
        if (discount.giftPut(originalPrice)) {
            outputView.gift();
        }
        if (!discount.giftPut(originalPrice)) {
            outputView.none();
        }
    }

    //할인 목록 출력
    public static void showDiscount(HashMap<String, Integer> discountTotal) {
        if (originalPrice >= PRICE_OF_EVENT_START) {
            discountAndAmount = discountTotal;
        }
        discountList(discountAndAmount);
    }

    //할인 목록 표시
    public static void discountList(HashMap<String, Integer> discount) {
        outputView.discountStart();
        if (!discount.isEmpty()) {
            for (Map.Entry<String, Integer> entry : discount.entrySet()) {
                outputView.discountList(entry.getKey(), won.format(entry.getValue()));
            }
        }
        if (discount.isEmpty()) {
            outputView.none();
        }
    }

    //총 혜택 금액 출력
    public static void showTotalDiscount(HashMap<String, Integer> discountTotal) {
        outputView.discountTotalStart();
        int totalDiscount = discount.totalDiscountAmount(discountTotal);
        if (discountTotal.isEmpty()) {
            outputView.none();
        }
        if (!discountTotal.isEmpty()) {
            outputView.discountTotal(won.format(totalDiscount));
        }
    }

    //최종 결제 금액 출력
    public static void showPayAmount(HashMap<String, Integer> discountTotal) {
        outputView.payAmountStart();
        int totalDiscount = discount.totalDiscountAmount(discountTotal);
        int finalPrice = discount.fianlPayAmount(originalPrice, totalDiscount);
        if (discountTotal.isEmpty()) {
            outputView.payAmount(won.format(originalPrice));
        }
        if (!discountTotal.isEmpty()) {
            outputView.payAmount(won.format(finalPrice));
        }
    }

    //배지 출력
    public static void showBadge(HashMap<String, Integer> discountTotal) {
        EventBadge badge = new EventBadge();
        int totalDiscount = discount.totalDiscountAmount(discountTotal);
        outputView.badgeStart();
        outputView.badge(badge.presentBadge(totalDiscount));
    }

    public static void main(String[] args) {
        outputView.start(); // 주문 시작
        validateDate(); // 주문 날짜 검증
        showOrder(menuAndNumber); // 주문 목록 출력
        showOriginalPrice();  // 할인 전 총주문 금액 출력
        showGift(originalPrice); // 증정 메뉴 출력
        showDiscount(discount.totalDiscount(visitDay, originalPrice, menuAndNumber)); // 혜택 내역 출력
        showTotalDiscount(discountAndAmount); // 총 혜택 금액 출력
        showPayAmount(discountAndAmount); // 할인 후 예상 결제 금액 출력
        showBadge(discountAndAmount); // 배지 출력
    }
}
