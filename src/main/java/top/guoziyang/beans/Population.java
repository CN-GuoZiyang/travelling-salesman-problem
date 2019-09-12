package top.guoziyang.beans;

/**
 * Population contains a group of individuals
 *
 * @author Guo Ziyang
 */
public class Population {

    private Individual[] individuals;

    public Population(int numberOfIndividuals, int numberOfCities) {
        individuals = new Individual[numberOfIndividuals];
        for(Individual individual : individuals) {
            individual = new Individual(numberOfCities);
        }
    }

    public Population(Individual[] individuals) {
        this.individuals = individuals;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

}
