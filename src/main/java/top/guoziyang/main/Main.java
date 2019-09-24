package top.guoziyang.main;

import top.guoziyang.problems.TSPProblem;
import top.guoziyang.utils.ConfigReader;

public class Main {

    public static void main(String[] args) {
        TSPProblem problem = ConfigReader.readConfig(Main.class.getClassLoader().getResource("ALL_tsp/eil51.tsp").getPath());
        Sga sga = new Sga();
        sga.Sga1(problem, 100);
    }

}
