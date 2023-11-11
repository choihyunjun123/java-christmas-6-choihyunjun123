package christmas.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Order {

    public HashMap<Object,Object> splitOrder(String order) {
        List<String> orders = Arrays.asList(order.split(","));
        List<List> splitOrders = new ArrayList<>();
        HashMap<Object,Object> menuAndNumber = new HashMap<>();
        for (String s : orders) {
            splitOrders.add(Arrays.asList(s.split("-")));
        }
        for (List l : splitOrders) {
            menuAndNumber.put(l.get(0),l.get(1));
        }
        return menuAndNumber;
    }
}
