package christmas.model;

public class EventBadge {

    public enum Badges {
        별(5000), 트리(10000), 산타(20000);

        private final int price;

        Badges(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    public String presentBadge(int totalDiscount) {
        if (totalDiscount >= Badges.산타.getPrice()) {
            return "산타";
        }
        if (totalDiscount >= Badges.트리.getPrice()) {
            return "트리";
        }
        if (totalDiscount >= Badges.별.getPrice()) {
            return "별";
        }
        return "없음";
    }
}
