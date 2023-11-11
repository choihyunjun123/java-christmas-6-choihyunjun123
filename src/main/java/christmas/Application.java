package christmas;

import christmas.model.Validate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final Validate validate = new Validate();

    public static void validateDate() {
        try {
            validate.validateDate(inputView.readDate());
        } catch (IllegalArgumentException e) {
            outputView.errorMessage(e.getMessage());
            validateDate();
        }
    }

    public static void validateMenu() {
        try {
            validate.validateOrder(inputView.readMenu());
        } catch (IllegalArgumentException e) {
            outputView.errorMessage(e.getMessage());
            validateMenu();
        }
    }


    public static void main(String[] args) {
        outputView.startMessage();
        validateDate();
        validateMenu();
    }
}
