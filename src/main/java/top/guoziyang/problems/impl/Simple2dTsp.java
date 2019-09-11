package top.guoziyang.problems.impl;

import top.guoziyang.beans.Coord2d;
import top.guoziyang.problems.TSPProblem;

import java.util.List;

public class Simple2dTsp extends TSPProblem {

    private final List<Coord2d> nodeCoordSection;

    private Simple2dTsp(Simple2dBuilder builder) {
        super(builder);
        this.nodeCoordSection = builder.nodeCoordSection;
    }

    public List<Coord2d> getNodeCoordSection() {
        return nodeCoordSection;
    }

    public static class Simple2dBuilder extends BaseBuilder<Simple2dBuilder> {

        private List<Coord2d> nodeCoordSection;

        public Simple2dBuilder() {
            super();
        }

        public Simple2dBuilder nodeCoordSection(List<Coord2d> nodeCoordSection) {
            this.nodeCoordSection = nodeCoordSection;
            return this;
        }

        public Simple2dTsp build() {
            return new Simple2dTsp(this);
        }
    }

}
