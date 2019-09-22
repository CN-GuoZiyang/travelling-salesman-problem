package top.guoziyang.utils;

import top.guoziyang.beans.Individual;

import java.util.*;
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
        int[] randomArray = getTwoRandom(i1.getOrder().length);
        individuals[0] = singleOrderCrossover(i1, i2, randomArray);
        individuals[1] = singleOrderCrossover(i2, i1, randomArray);
        return individuals;
    }

    public static Individual[] partiallyMappedCrossover(Individual i1, Individual i2) {
        Individual[] individuals = new Individual[2];
        int[] randomArray = getTwoRandom(i1.getOrder().length);
        individuals[0] = singlePmx(i1, i2, randomArray);
        individuals[1] = singlePmx(i1, i2, randomArray);
        return individuals;
    }

    private static Individual singlePmx(Individual i1, Individual i2, int[] randomArray) {
        int[] i1Order = i1.getOrder();
        List<Integer> i1List = IntStream.of(i1Order).boxed().collect(Collectors.toList());
        int[] i2Order = i2.getOrder();
        List<Integer> i2List = IntStream.of(i2Order).boxed().collect(Collectors.toList());
        int length = i1Order.length;
        Individual child = new Individual(length);
        int[] childOrder = new int[length];
        Arrays.fill(childOrder, -1);
        List<Integer> childList = IntStream.of(childOrder).boxed().collect(Collectors.toList());
        int index1 = randomArray[0];
        int index2 = randomArray[1];

        Set<Integer> already = new HashSet<>();

        childList.addAll(index1, i1List.subList(index1, index2));
        for(int p = index1; p < index2; p ++) {
            int i = i2List.get(p);
            int j = i1List.get(p);
            if(already.contains(i)) {
                continue;
            } else {
                already.add(i);
                already.add(j);
            }
            if(i == j) {
                continue;
            }
            if(index1 > i2List.indexOf(j) || index2 <= i2List.indexOf(j)) {
                childList.set(i2List.indexOf(j), i);
            } else {
                int k = i1List.get(i2List.indexOf(j));
                childList.set(i2List.indexOf(k), i);
            }
        }

        for(int i = 0; i < length; i ++) {
            if(childList.get(i) == -1) {
                childList.set(i, i2List.get(i));
            }
        }

        childOrder = childList.subList(0, 9).stream().mapToInt(Integer::valueOf).toArray();
        child.setOrder(childOrder);
        return child;
    }

    private static Individual singleOrderCrossover(Individual i1, Individual i2, int[] randomArray) {
        int[] i1Order = i1.getOrder();
        int[] i2Order = i2.getOrder();
        int length = i1Order.length;
        Individual child = new Individual(length);

        int[] childOrder = new int[length];
        Arrays.fill(childOrder, -1);
        int index1 = randomArray[0];
        int index2 = randomArray[1];
        System.arraycopy(i1Order, index1, childOrder, index1, index2 - index1);
        int i = index2;
        for(int k = 0; k < length; k ++) {
            if(childOrder[i] == -1) {
                List<Integer> tempList = IntStream.of(childOrder).boxed().collect(Collectors.toList());
                int j = index2;
                for(int l = 0; l < length; l ++) {
                    if(!tempList.contains(i2Order[j])) {
                        childOrder[i] = i2Order[j];
                        break;
                    }
                    j ++;
                    if(j == length) {
                        j = 0;
                    }
                }
            }
            i ++;
            if(i == length) {
                i = 0;
            }
        }
        child.setOrder(childOrder);
        return child;
    }

    private static int[] getTwoRandom(int length) {
        int index1 = random.nextInt(length);
        int index2;
        do {
            index2 = random.nextInt(length);
        } while (index1 == index2);
        if (index2 - index1 < 0) {
            int temp = index2;
            index2 = index1;
            index1 = temp;
        }
        return new int[]{index1, index2};
    }

}
