package christmas;

import christmas.model.Menu;
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

    public static void validateMenu(String c) {
        inputView.readMenu();
//        for (Menu.Appetizer a : Menu.Appetizer.values()) {
//            validateMenu(String.valueOf(a));
//        }
//        if (c.equals("양송이스프")){
//            System.out.println(1);
//        }
    }


    public static void main(String[] args) {
        outputView.startMessage();
        validateDate();

    }
}
