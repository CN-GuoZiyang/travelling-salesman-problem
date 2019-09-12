package top.guoziyang.problems.impl;

import top.guoziyang.problems.TSPProblem;

/**
 * The implement of a simple explicit tsp problem with only EDGE_WEIGHT_SECTION
 *
 * @author Guo Ziyang
 */
public class ExplicitTsp extends TSPProblem {

    private final int[][] edgeWeightSection;

    ExplicitTsp(Builder builder) {
        super(builder);
        this.edgeWeightSection = builder.edgeWeightSection;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int[][] getEdgeWeightSection() {
        return edgeWeightSection;
    }

    public static class Builder extends BaseBuilder<Builder> {

        private int[][] edgeWeightSection;

        Builder() {
            super();
        }

        public Builder edgeWeightSection(int[][] edgeWeightSection) {
            this.edgeWeightSection = edgeWeightSection;
            return this;
        }

        @Override
        public ExplicitTsp build() {
            return new ExplicitTsp(this);
        }

    }

}
