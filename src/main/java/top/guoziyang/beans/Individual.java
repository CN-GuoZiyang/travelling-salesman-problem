package top.guoziyang.beans;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A possible solution for the tsp problem
 * It contains a array representing the order of cities which will shuffle when constructing
 *
 * @author Guo Ziyang
 */
public class Individual {

    private int[] order;

    private Random random = new Random();

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

    public void setOrder(int[] order) {
        this.order = order;
    }

    /**
     * Insert mutation
     */
    public void insert() {
        List<Integer> orderList = IntStream.of(order).boxed().collect(Collectors.toList());
        Integer x1 = orderList.get(random.nextInt(order.length));
        Integer x2;
        do {
            x2 = orderList.get(random.nextInt(order.length));
        } while (x1.equals(x2));
        orderList.remove(x2);
        orderList.add(orderList.indexOf(x1) + 1, x2);
        order = orderList.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * swap mutation
     */
    public void swap() {
        int index1 = random.nextInt(order.length);
        int index2;
        do {
            index2 = random.nextInt(order.length);
        } while (index1 == index2);
        int x1 = order[index1];
        order[index1] = order[index2];
        order[index2] = x1;
    }

    /**
     * inversion mutation
     */
    public void inversion() {
        List<Integer> orderList = IntStream.of(order).boxed().collect(Collectors.toList());
        int index1 = random.nextInt(order.length);
        int index2;
        do {
            index2 = random.nextInt(order.length);
        } while (index1 == index2);
        List<Integer> sub = orderList.subList(index1, index2);
        orderList.removeAll(sub);
        Collections.reverse(sub);
        orderList.addAll(Math.min(index1, index2), sub);
        order = orderList.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * scramble mutation
     */
    public void scramble() {
        List<Integer> orderList = IntStream.of(order).boxed().collect(Collectors.toList());
        int num = random.nextInt(order.length);
        List<Integer> positions = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < num; i ++) {
            int position;
            do {
                position = random.nextInt(order.length);
            } while (positions.contains(position));
            positions.add(position);
            numbers.add(orderList.get(position));
        }
        Collections.shuffle(numbers);
        for(int i = 0; i < num; i ++) {
            orderList.set(positions.get(i), numbers.get(i));
        }
        order = orderList.stream().mapToInt(Integer::valueOf).toArray();
    }

}
