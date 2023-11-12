package christmas;

import christmas.model.Discount;
import christmas.model.Payment;
import christmas.model.Validate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Application {

    private static final int GIFT_PRESENT_AMOUNT = 120000;
    private static final int PRICE_OF_CHAMPAGNE = 25000;

    private static int visitDay;
    private static int originalPrice;
    private static int totalDiscount;

    private static final DecimalFormat won = new DecimalFormat("###,###");
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final Validate validate = new Validate();
    private static final Payment payment = new Payment();
    private static final Discount discount = new Discount();
    private static HashMap<String, Integer> menuAndNumber = new HashMap<>();
    private static HashMap<String, Integer> discountAndAmount = new HashMap<>();

    public static void validateDate() {
        try {
            visitDay = validate.validateDate(inputView.readDate());
            validateMenu();
        } catch (IllegalArgumentException e) {
            outputView.error(e.getMessage());
            validateDate();
        }
    }

    public static void validateMenu() {
        try {
            menuAndNumber = validate.validateOrder(inputView.readMenu());
        } catch (IllegalArgumentException e) {
            outputView.error(e.getMessage());
            validateMenu();
        }
    }

    public static void showOrder(HashMap<String, Integer> orders) {
        for (Map.Entry<String, Integer> entry : orders.entrySet()) {
            outputView.orderList(entry.getKey(), entry.getValue());
        }
    }

    public static void showGift(int originalPrice) {
        outputView.giftStart();
        if (originalPrice >= GIFT_PRESENT_AMOUNT) {
            outputView.gift();
        }
        if (originalPrice < GIFT_PRESENT_AMOUNT) {
            outputView.none();
        }
    }

    public static void giftPut(int originalPrice, HashMap<String, Integer> discount) {
        if (originalPrice >= 10000) {
            discountAndAmount = discount;
        }
        if (originalPrice >= GIFT_PRESENT_AMOUNT) {
            discountAndAmount.put("증정 이벤트", PRICE_OF_CHAMPAGNE);
        }
        showDiscountList(discountAndAmount);
    }

    //할인 목록 표시
    public static void showDiscountList(HashMap<String, Integer> discount) {
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

    public static void main(String[] args) {
        outputView.start();
        validateDate();
        outputView.preview(visitDay);
        showOrder(menuAndNumber);
        originalPrice = payment.originalAmount(menuAndNumber);
        outputView.originalPrice(won.format(originalPrice));
        showGift(originalPrice);
        giftPut(originalPrice, discount.totalDiscount(visitDay, menuAndNumber));
        totalDiscount = Discount.showTotalDiscountAmount(discountAndAmount);
    }
}
