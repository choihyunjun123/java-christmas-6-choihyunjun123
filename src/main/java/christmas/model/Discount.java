package christmas.model;

import christmas.view.OutputView;

public class Discount {

    private static final OutputView outputView = new OutputView();

    public void gift(int originalPrice) {
        if (originalPrice >= 120000) {
            outputView.gift("샴페인 1개");
        }
        if (originalPrice < 120000) {
            outputView.gift("없음");
        }
    }
}
