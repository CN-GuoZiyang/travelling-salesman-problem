package top.guoziyang.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A possible solution for the tsp problem
 * It contains a array representing the order of cities which will shuffle when constructing
 *
 * @author Guo Ziyang
 */
public class Individual {

    private int[] order;

    public Individual(int numberOfCities) {
        List<Integer> orderList = new ArrayList<>();
        for(int i = 1; i <= numberOfCities; i ++) {
            orderList.add(i);
        }
        Collections.shuffle(orderList);
        order = orderList.stream().mapToInt(Integer::valueOf).toArray();
    }

    public int[] getOrder() {
        return order;
    }

}
