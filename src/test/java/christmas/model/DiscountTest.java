package christmas.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    public static HashMap<String, Integer> order = new HashMap<>();
    public static HashMap<String, Integer> discountExpect = new HashMap<>();

    @BeforeAll
    static void beforeAll() {
        order.put("양송이스프", 2);
        order.put("초코케이크", 2);
        order.put("티본스테이크", 2);
        discountExpect.put("증정 이벤트", 25000);
        discountExpect.put("크리스마스 디데이 할인", 2600);
        discountExpect.put("평일 할인", 4046);
        discountExpect.put("특별 할인", 1000);
    }

    @DisplayName("전체 할인 내역 확인")
    @Test
    void totalDiscount() {
        Discount discount = new Discount();
        HashMap<String, Integer> test = discount.totalDiscount(17, 152000, order);
        assertThat(test).isEqualTo(discountExpect);
    }

    @DisplayName("크리스마스 할인 내역 확인")
    @ParameterizedTest
    @CsvSource(value = {"10:1900", "20:2900", "26:0"}, delimiter = ':')
    void christmas(int visitDay, int expect) {
        Discount discount = new Discount();
        int result = discount.christmas(visitDay);
        assertThat(result).isEqualTo(expect);
    }

    @DisplayName("평일 할인 내역 확인")
    @Test
    void weekday() {
        Discount discount = new Discount();
        int result = discount.weekday(20, order);
        assertThat(result).isEqualTo(4046);
    }

    @DisplayName("주말 할인 내역 확인")
    @Test
    void weekend() {
        Discount discount = new Discount();
        int result = discount.weekend(22, order);
        assertThat(result).isEqualTo(4046);
    }

    @DisplayName("특별 할인 내역 확인")
    @ParameterizedTest
    @CsvSource(value = {"17:1000", "18:0"}, delimiter = ':')
    void special(int visitDay, int expect) {
        Discount discount = new Discount();
        int result = discount.special(visitDay);
        assertThat(result).isEqualTo(expect);
    }

    @DisplayName("증정 유무 확인")
    @ParameterizedTest
    @CsvSource(value = {"130000:true", "100000:false"}, delimiter = ':')
    void giftPut(int originalPrice, boolean expect) {
        Discount discount = new Discount();
        boolean result = discount.giftPut(originalPrice);
        assertThat(result).isEqualTo(expect);
    }

    @DisplayName("총 할인 금액 내역 확인")
    @Test
    void totalDiscountAmount() {
        Discount discount = new Discount();
        int result = discount.totalDiscountAmount(discountExpect);
        assertThat(result).isEqualTo(32646);
    }

    @DisplayName("증정품 유무에 따른 최종 금액 계산")
    @ParameterizedTest
    @CsvSource(value = {"130000:30000:125000", "100000:30000:70000"}, delimiter = ':')
    void fianlPayAmountGift(int originalPrice, int totalDiscount, int expectPrice) {
        Discount discount = new Discount();
        int result = discount.fianlPayAmount(originalPrice, totalDiscount);
        assertThat(result).isEqualTo(expectPrice);
    }
}