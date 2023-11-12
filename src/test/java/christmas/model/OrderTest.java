package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    List<List> splitOrders = List.of(List.of("양송이스프", "1"));

    @DisplayName("메뉴 쉼표 기준으로 나누기")
    @Test
    void splitOrder() {
        Order order = new Order();
        List result = order.splitOrder("양송이스프-1");
        assertThat(result).isEqualTo(splitOrders);
    }

    @DisplayName("메뉴 - 기준으로 나누기")
    @Test
    void putOrder() {
        HashMap<String, Integer> test = new HashMap<>();
        test.put("양송이스프",1);
        Order order = new Order();
        HashMap<String, Integer> result = order.putOrder(splitOrders);
        assertThat(result).isEqualTo(test);
    }
}