package christmas;

import christmas.model.ValidateDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void validateDate() {
        try {
            new ValidateDate(inputView.readDate());
        } catch (IllegalArgumentException e) {
            outputView.errorMessage(e.getMessage());
            validateDate();
        }
    }

    public static void validateMenu() {
        inputView.readMenu();
    }

    public static void main(String[] args) {
        outputView.startMessage();
        validateDate();
        validateMenu();
    }
}
