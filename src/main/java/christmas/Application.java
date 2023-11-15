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

    public static void main(String[] args) {
        outputView.start();
        validateDate();
        showOrder(menuAndNumber);
        showOriginalPrice();
        showGift(originalPrice);
        showDiscount(discount.totalDiscount(visitDay, originalPrice, menuAndNumber));
        showTotalDiscount(discountAndAmount);
        showPayAmount(discountAndAmount);
        showBadge(discountAndAmount);
    }

    private static void validateDate() {
        try {
            visitDay = validate.validateDate(inputView.readDate());
            validateMenu();
            outputView.preview(visitDay);
        } catch (IllegalArgumentException e) {
            outputView.error(e.getMessage());
            validateDate();
        }
    }

    private static void validateMenu() {
        try {
            menuAndNumber = validate.validateOrder(inputView.readMenu());
        } catch (IllegalArgumentException e) {
            outputView.error(e.getMessage());
            validateMenu();
        }
    }

    private static void showOrder(HashMap<String, Integer> orders) {
        orders.forEach(outputView::orderList);
    }

    private static void showOriginalPrice() {
        originalPrice = new Payment().originalAmount(menuAndNumber);
        outputView.originalPrice(won.format(originalPrice));
    }

    private static void showGift(int originalPrice) {
        outputView.giftStart();
        if (discount.giftPut(originalPrice)) {
            outputView.gift();
        } else {
            outputView.none();
        }
    }

    private static void showDiscount(HashMap<String, Integer> discountTotal) {
        if (originalPrice >= PRICE_OF_EVENT_START) {
            discountAndAmount = discountTotal;
        }
        discountList(discountAndAmount);
    }

    private static void discountList(HashMap<String, Integer> discount) {
        outputView.discountStart();
        if (!discount.isEmpty()) {
            discount.forEach((discountName, discountAmount) ->
                    outputView.discountList(discountName, won.format(discountAmount)));
        } else {
            outputView.none();
        }
    }

    private static void showTotalDiscount(HashMap<String, Integer> discountTotal) {
        outputView.discountTotalStart();
        int totalDiscount = discount.totalDiscountAmount(discountTotal);
        if (discountTotal.isEmpty()) {
            outputView.none();
        } else {
            outputView.discountTotal(won.format(totalDiscount));
        }
    }

    private static void showPayAmount(HashMap<String, Integer> discountTotal) {
        outputView.payAmountStart();
        int totalDiscount = discount.totalDiscountAmount(discountTotal);
        int finalPrice = discount.fianlPayAmount(originalPrice, totalDiscount);
        if (discountTotal.isEmpty()) {
            outputView.payAmount(won.format(originalPrice));
        } else {
            outputView.payAmount(won.format(finalPrice));
        }
    }

    private static void showBadge(HashMap<String, Integer> discountTotal) {
        EventBadge badge = new EventBadge();
        int totalDiscount = discount.totalDiscountAmount(discountTotal);
        outputView.badgeStart();
        outputView.badge(badge.presentBadge(totalDiscount));
    }
}