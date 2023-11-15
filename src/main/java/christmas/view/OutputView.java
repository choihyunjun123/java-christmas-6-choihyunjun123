package christmas.view;

public class OutputView {

    public void start() {
        printLine("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void error(String errorMessage) {
        printLine(errorMessage + "\n");
    }

    public void preview(int day) {
        printLine(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", day));
        printLine("<주문 메뉴>");
    }

    public void orderList(String menu, Integer quantity) {
        printLine(String.format("%s %d개", menu, quantity));
    }

    public void originalPrice(String originalPrice) {
        printLine("\n<할인 전 총주문 금액>");
        printLine(originalPrice + "원");
    }

    public void none() {
        printLine("없음");
    }

    public void giftStart() {
        printLine("\n<증정 메뉴>");
    }

    public void gift() {
        printLine("샴페인 1개");
    }

    public void discountStart() {
        printLine("\n<혜택 내역>");
    }

    public void discountList(String discountName, String discountAmount) {
        printLine(String.format("%s: -%s원", discountName, discountAmount));
    }

    public void discountTotalStart() {
        printLine("\n<총혜택 금액>");
    }

    public void discountTotal(String totalDiscount) {
        printLine(String.format("-%s원", totalDiscount));
    }

    public void payAmountStart() {
        printLine("\n<할인 후 예상 결제 금액>");
    }

    public void payAmount(String finalPrice) {
        printLine(finalPrice + "원");
    }

    public void badgeStart() {
        printLine("\n<12월 이벤트 배지>");
    }

    public void badge(String badge) {
        printLine(badge);
    }

    private void printLine(String message) {
        System.out.println(message);
    }
}