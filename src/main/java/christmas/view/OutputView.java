package christmas.view;

public class OutputView {

    public void start() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void error(String e) {
        System.out.println(e + "\n");
    }

    public void preview(int day) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
}
