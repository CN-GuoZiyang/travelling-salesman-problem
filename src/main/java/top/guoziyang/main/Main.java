package top.guoziyang.main;

import top.guoziyang.problems.TSPProblem;
import top.guoziyang.utils.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> results = new ArrayList<>();
        Sga sga = null;
        TSPProblem problem = ConfigReader.readConfig(Main.class.getClassLoader().getResource("ALL_tsp/lin105.tsp").getPath());
        for(int i = 0; i < 10; i ++) {
            sga = new Sga();
            int result = sga.Sga1(problem, 50);
            System.out.println("Run" + (i+1) + ": " + result);
            results.add(result);
        }
        int average = 0;
        for(Integer i : results) {
            average += i;
        }
        average /= 10;
        System.out.println("Average: " + average);
        double deviation = 0;
        for(Integer i : results) {
            deviation += (i - average) * (i - average);
        }
        deviation /= 10;
        deviation = Math.sqrt(deviation);
        System.out.println("standard deviation: " + deviation);
    }

}
