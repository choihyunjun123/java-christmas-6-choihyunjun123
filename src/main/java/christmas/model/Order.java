package christmas.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Order {

    public List<List> splitOrder(String order) {
        List<String> orders = Arrays.asList(order.split(","));
        List<List> splitOrders = new ArrayList<>();
        for (String s : orders) {
            splitOrders.add(Arrays.asList(s.split("-")));
        }
        return splitOrders;
    }

    public HashMap<Object,Object> putOrder(List<List> order) {
        HashMap<Object,Object> menuAndNumber = new HashMap<>();
        for (List l : order) {
            menuAndNumber.put(l.get(0),l.get(1));
        }
        return menuAndNumber;
    }
}
