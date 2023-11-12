package christmas.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    List<List> splitOrders = List.of(List.of("양송이스프", "1"));

    @Test
    void splitOrder() {
        Order order = new Order();
        List result = order.splitOrder("양송이스프-1");
        assertThat(result).isEqualTo(splitOrders);
    }

    @Test
    void putOrder() {
        HashMap<String, Integer> test = new HashMap<>();
        test.put("양송이스프",1);
        Order order = new Order();
        HashMap<String, Integer> result = order.putOrder(splitOrders);
        assertThat(result).isEqualTo(test);
    }
}