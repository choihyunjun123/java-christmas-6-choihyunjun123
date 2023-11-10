package christmas;

import christmas.model.ValidateDate;
import christmas.view.InputView;

public class Application {

    private static final InputView inputView = new InputView();

    public static void validateDate() {
        new ValidateDate(inputView.readDate());
    }


    public static void main(String[] args) {
        validateDate();
    }
}
