package top.guoziyang.utils;

import top.guoziyang.beans.Individual;
import top.guoziyang.beans.Population;
import top.guoziyang.problems.TSPProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectionUtils {

    private static Random random = new Random();

    public static Population fitnessProportionate(Population population, TSPProblem problem) {
        int populationLength = population.getIndividuals().length;
        Individual[] individuals = new Individual[populationLength];

        Individual[] parents = population.getIndividuals();
        double[] fitness = new double[populationLength];
        double[] size = new double[populationLength];
        double sum = 0;
        for(int i = 0; i < populationLength; i ++) {
            fitness[i] = 1.0 / Individual.getDistance(parents[i], problem);
            sum += fitness[i];
        }
        for(int i = 0; i < populationLength; i ++) {
            size[i] = fitness[i] / sum;
        }
        double[] end = new double[populationLength];
        sum = 0;
        for(int i = 0; i < populationLength; i ++) {
            sum += size[i];
            end[i] = sum;
        }
        for(int i = 0; i < populationLength; i ++) {
            double randomNum = Math.random();
            double tempSum = 0;
            for(int j = 0; j < populationLength; j ++) {
                tempSum += end[j];
                if(tempSum >= randomNum) {
                    individuals[i] = new Individual(parents[j]);
                    break;
                }
            }
        }

        return new Population(individuals);
    }

    public static Population tournamentSelection(Population population, TSPProblem problem, int sampleNum) {
        int populationLength = population.getIndividuals().length;
        Individual[] individuals = new Individual[populationLength];

        Individual[] parents = population.getIndividuals();
        List<Individual> candidates = new ArrayList<>();
        for(int i = 0; i < populationLength; i ++) {
            candidates.clear();
            do {
                Individual candidate = parents[random.nextInt(populationLength)];
                if(!candidates.contains(candidate)) {
                    candidates.add(candidate);
                }
            } while (candidates.size() != sampleNum);
            Individual bestCandidate = candidates.get(0);
            int leastDistance = Integer.MAX_VALUE;
            for(Individual candidate : candidates) {
                int distance = Individual.getDistance(candidate, problem);
                if(distance < leastDistance) {
                    bestCandidate = candidate;
                    leastDistance = distance;
                }
            }
            individuals[i] = new Individual(bestCandidate);
        }

        return new Population(individuals);
    }

}
