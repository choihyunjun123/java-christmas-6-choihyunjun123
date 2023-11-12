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

    public int christmas(int visitDay) {
        int christmasDiscount = 0;
        if (visitDay <= 25) {
            christmasDiscount = 1000 + 100 * (visitDay-1);
        }
        return christmasDiscount;
    }
}
