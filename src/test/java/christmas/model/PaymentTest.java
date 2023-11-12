package christmas.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    public static HashMap<String, Integer> menuAndNumber = new HashMap<>();

    @BeforeAll
    static void beforeAll() {
        menuAndNumber.put("양송이스프", 1);
        menuAndNumber.put("티본스테이크", 2);
        menuAndNumber.put("초코케이크", 3);
        menuAndNumber.put("제로콜라", 4);
    }

    @DisplayName("할인 전 주문 금액")
    @Test
    void originalAmount() {
        int result = 0;
        Payment payment = new Payment();
        result = payment.originalAmount(menuAndNumber);
        assertThat(result).isEqualTo(173000);
    }

    @DisplayName("에피타이저 주문 금액")
    @Test
    void appetizerAmount() {
        int result = 0;
        Payment payment = new Payment();
        for (Map.Entry<String, Integer> entry : menuAndNumber.entrySet()) {
            result += payment.appetizerAmount(entry);
        }
        assertThat(result).isEqualTo(6000);
    }

    @DisplayName("메인 주문 금액")
    @Test
    void mainAmount() {
        int result = 0;
        Payment payment = new Payment();
        for (Map.Entry<String, Integer> entry : menuAndNumber.entrySet()) {
            result += payment.mainAmount(entry);
        }
        assertThat(result).isEqualTo(110000);
    }

    @DisplayName("디저트 주문 금액")
    @Test
    void dessertAmount() {
        int result = 0;
        Payment payment = new Payment();
        for (Map.Entry<String, Integer> entry : menuAndNumber.entrySet()) {
            result += payment.dessertAmount(entry);
        }
        assertThat(result).isEqualTo(45000);
    }

    @DisplayName("음료 주문 금액")
    @Test
    void drinkAmount() {
        int result = 0;
        Payment payment = new Payment();
        for (Map.Entry<String, Integer> entry : menuAndNumber.entrySet()) {
            result += payment.drinkAmount(entry);
        }
        assertThat(result).isEqualTo(12000);
    }
}