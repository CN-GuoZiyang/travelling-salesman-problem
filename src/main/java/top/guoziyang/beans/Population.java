package top.guoziyang.beans;

import top.guoziyang.problems.TSPProblem;

/**
 * Population contains a group of individuals
 *
 * @author Guo Ziyang
 */
public class Population {

    private Individual[] individuals;

    public Population(int numberOfIndividuals, int numberOfCities) {
        individuals = new Individual[numberOfIndividuals];
        for(int i = 0; i < numberOfIndividuals; i ++) {
            individuals[i] = new Individual(numberOfCities);
        }
    }

    public Population(Individual[] individuals) {
        this.individuals = individuals;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public void print(TSPProblem problem) {
        for(Individual i : individuals) {
            System.out.println(Individual.getDistance(i, problem));
        }
    }

    public int getLeastDistance(TSPProblem problem) {
        int leastDistance = Integer.MAX_VALUE;
        for(Individual i : individuals) {
            int distance = Individual.getDistance(i, problem);
            if(distance < leastDistance) {
                leastDistance = distance;
            }
        }
        return leastDistance;
    }

}
