package christmas.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Order {

    private static final int MENU_NAME_POSITION = 0;
    private static final int MENU_NUMBER_POSITION = 1;
    private static final String MENU_SPLIT_PUNCTUATION = ",";
    private static final String NUMBER_SPLIT_PUNCTUATION = "-";

    public List<List> splitOrder(String order) {
        List<String> orders = Arrays.asList(order.split(MENU_SPLIT_PUNCTUATION));
        List<List> splitOrders = new ArrayList<>();
        for (String s : orders) {
            splitOrders.add(Arrays.asList(s.split(NUMBER_SPLIT_PUNCTUATION)));
        }
        return splitOrders;
    }

    public HashMap<String, Integer> putOrder(List<List> order) {
        HashMap<String, Integer> menuAndNumber = new HashMap<>();
        for (List l : order) {
            menuAndNumber.put(l.get(MENU_NAME_POSITION).toString(),
                    Integer.parseInt(l.get(MENU_NUMBER_POSITION).toString()));
        }
        return menuAndNumber;
    }
}
