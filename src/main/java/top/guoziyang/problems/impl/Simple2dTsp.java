package top.guoziyang.problems.impl;

import top.guoziyang.beans.Coord2d;
import top.guoziyang.problems.TSPProblem;

import java.util.List;

/**
 * The implement of the tsp problem with only 2d coordinate data
 *
 * Applicable types include: EUC_2D, ATT and GEO
 *
 * @author Guo Ziyang
 */
public class Simple2dTsp extends TSPProblem {

    private final List<Coord2d> nodeCoordSection;

    private Simple2dTsp(Builder builder) {
        super(builder);
        this.nodeCoordSection = builder.nodeCoordSection;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Coord2d> getNodeCoordSection() {
        return nodeCoordSection;
    }

    public static class Builder extends BaseBuilder<Builder> {

        private List<Coord2d> nodeCoordSection;

        Builder() {
            super();
        }

        public Builder nodeCoordSection(List<Coord2d> nodeCoordSection) {
            this.nodeCoordSection = nodeCoordSection;
            return this;
        }

        @Override
        public Simple2dTsp build() {
            return new Simple2dTsp(this);
        }
    }

}
