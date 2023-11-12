package christmas.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    public static final int VISIT_DAY = 20;

    public static HashMap<String, Integer> order = new HashMap<>();
    public static HashMap<String, Integer> discountExpect = new HashMap<>();

    @BeforeAll
    static void beforeAll() {
        order.put("양송이스프", 2);
        order.put("초코케이크", 2);
        discountExpect.put("크리스마스 디데이 할인", 2900);
        discountExpect.put("평일 할인", 4046);
    }

    @DisplayName("전체 할인 내역 확인")
    @Test
    void totalDiscount() {
        Discount discount = new Discount();
        HashMap<String, Integer> test = discount.totalDiscount(VISIT_DAY, order);
        assertThat(test).isEqualTo(discountExpect);
    }

    @DisplayName("크리스마스 할인 내역 확인")
    @Test
    void christmas() {
        Discount discount = new Discount();
        int result = discount.christmas(VISIT_DAY);
        assertThat(result).isEqualTo(2900);
    }

    @DisplayName("평일 할인 내역 확인")
    @Test
    void weekday() {
        Discount discount = new Discount();
        int result = discount.weekday(VISIT_DAY, order);
        assertThat(result).isEqualTo(4046);
    }
}