package top.guoziyang.utils;

import top.guoziyang.beans.Individual;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The crossover utils
 *
 * @author Guo Ziyang
 */
public class CrossoverUtils {

    private static Random random = new Random();

    public static Individual[] orderCrossover(Individual i1, Individual i2) {
        Individual[] individuals = new Individual[2];
        individuals[0] = singleOrderCrossover(i1, i2);
        individuals[1] = singleOrderCrossover(i2, i1);
        return individuals;
    }

    private static Individual singleOrderCrossover(Individual i1, Individual i2) {
        Individual child = new Individual(i1.getOrder().length);
        int[] childOrder = new int[i1.getOrder().length];
        Arrays.fill(childOrder, -1);

        int[] i1Order = i1.getOrder();
        int[] i2Order = i2.getOrder();
        int index1 = random.nextInt(i1Order.length);
        int index2;
        do {
            index2 = random.nextInt(i1Order.length);
        } while (index1 == index2);
        if (index2 - index1 < 0) {
            int temp = index2;
            index2 = index1;
            index1 = temp;
        }
        System.arraycopy(i1Order, index1, childOrder, index1, index2 - index1);
        int i = index2;
        for(int k = 0; k < i1Order.length; k ++) {
            if(childOrder[i] == -1) {
                List<Integer> tempList = IntStream.of(childOrder).boxed().collect(Collectors.toList());
                int j = index2;
                for(int l = 0; l < i1Order.length; l ++) {
                    if(!tempList.contains(i2Order[j])) {
                        childOrder[i] = i2Order[j];
                        break;
                    }
                    j ++;
                    if(j == i1Order.length) {
                        j = 0;
                    }
                }
            }
            i ++;
            if(i == i1Order.length) {
                i = 0;
            }
        }
        child.setOrder(childOrder);
        return child;
    }

}
