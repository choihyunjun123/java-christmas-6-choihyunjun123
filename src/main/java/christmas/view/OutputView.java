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

    public void gift(String message) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(message);
    }
}
