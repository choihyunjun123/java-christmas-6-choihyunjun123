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

    private static int visitDay;
    private static int originalPrice;

    private static final DecimalFormat won = new DecimalFormat("###,###");
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final Validate validate = new Validate();
    private static final Payment payment = new Payment();
    private static final Discount discount = new Discount();
    private static HashMap<String, Integer> menuAndNumber = new HashMap<>();

    public static void validateDate() {
        try {
            visitDay = validate.validateDate(inputView.readDate());
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

    public static String decimalPrice() {
        originalPrice = payment.originalAmount(menuAndNumber);
        return won.format(payment.originalAmount(menuAndNumber));
    }

    public static void main(String[] args) {
        outputView.start();
        validateDate();
        validateMenu();
        outputView.preview(visitDay);
        showOrder(menuAndNumber);
        outputView.originalPrice(decimalPrice());
        discount.gift(originalPrice);
    }
}
