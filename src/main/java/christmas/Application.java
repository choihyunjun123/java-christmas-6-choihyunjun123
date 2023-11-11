package christmas;

import christmas.model.Validate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final Validate validate = new Validate();

    private static int visitDay;

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
            validate.validateOrder(inputView.readMenu());
        } catch (IllegalArgumentException e) {
            outputView.error(e.getMessage());
            validateMenu();
        }
    }

    public static void main(String[] args) {
        outputView.start();
        validateDate();
        validateMenu();
        outputView.preview(visitDay);
    }
}
