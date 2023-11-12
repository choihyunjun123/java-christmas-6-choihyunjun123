package christmas.view;

public class OutputView {

    public void start() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void error(String e) {
        System.out.println(e + "\n");
    }

    public void preview(int day) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");
    }

    public void orderList(String key, Integer value) {
        System.out.println(key + " " + value + "개");
    }

    public void originalPrice(String originalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(originalPrice + "원");
    }

    public void none() {
        System.out.println("없음");
    }

    public void giftStart() {
        System.out.println("\n<증정 메뉴>");
    }

    public void gift() {
        System.out.println("샴페인 1개");
    }

    public void discountStart() {
        System.out.println("\n<혜택 내역>");
    }

    public void discountList(String key, String value) {
        System.out.println(key + ": -" + value + "원");
    }

    public void discountTotalStart() {
        System.out.println("\n<총혜택 금액>");
    }

    public void discountTotal(String value) {
        System.out.println("-" + value + "원");
    }

    public void payAmountStart() {
        System.out.println("\n<할인 후 예상 결제 금액>");
    }

    public void payAmount(String value) {
        System.out.println(value + "원");
    }

    public void badgeStart() {
        System.out.println("\n<12월 이벤트 배지>");
    }
}
