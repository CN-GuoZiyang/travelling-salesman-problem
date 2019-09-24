package top.guoziyang.main;

import top.guoziyang.beans.Individual;
import top.guoziyang.beans.Population;
import top.guoziyang.problems.TSPProblem;
import top.guoziyang.utils.CrossoverUtils;
import top.guoziyang.utils.SelectionUtils;

import java.util.Random;

public class Sga {

    private Random random = new Random();

    public void Sga1(TSPProblem problem, int numberOfIndividuals) {
        /*
            crossover probability
         */
        double pc = 0.8;
        /*
            mutation probability
         */
        double pm = 0.09;

        /*
            generate the init population
         */
        Population population = new Population(numberOfIndividuals, problem.getDimension());
        int generationNum = 1;
        while(true) {
            Individual[] childIndividuals = new Individual[numberOfIndividuals];
            // Population matingPool = SelectionUtils.fitnessProportionate(population, problem);
            Population matingPool = SelectionUtils.tournamentSelection(population, problem, 10);
            Individual[] matingIndividuals = matingPool.getIndividuals();
            for(int i = 0; i < numberOfIndividuals; i += 2) {
                double randomNum = Math.random();
                if(randomNum < pc) {
                    Individual[] children = CrossoverUtils.orderCrossover(matingIndividuals[i], matingIndividuals[i + 1]);
                    childIndividuals[i] = children[0];
                    childIndividuals[i + 1] = children[1];
                } else {
                    childIndividuals[i] = matingIndividuals[i];
                    childIndividuals[i + 1] = matingIndividuals[i + 1];
                }
            }
            for(int i = 0; i < numberOfIndividuals; i ++) {
                double randomNum = Math.random();
                if(randomNum < pm) {
                    int n = random.nextInt(problem.getDimension()) + 1;
                    for(int j = 0; j < n; j ++) {
                        childIndividuals[i].swap();
                    }
                }
            }
            population = new Population(childIndividuals);
            if(generationNum % 10 == 0) {
                System.out.println(population.getLeastDistance(problem));
            }
            generationNum ++;
        }
    }

}
