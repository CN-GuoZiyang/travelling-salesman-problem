package top.guoziyang.problems.impl;

import top.guoziyang.beans.Coord3d;
import top.guoziyang.problems.TSPProblem;

import java.util.List;

public class Simple3dTsp extends TSPProblem {

    private final List<Coord3d> nodeCoordSection;

    private Simple3dTsp(Simple3dBuilder builder) {
        super(builder);
        this.nodeCoordSection = builder.nodeCoordSection;
    }

    public List<Coord3d> getNodeCoordSection() {
        return nodeCoordSection;
    }

    public static class Simple3dBuilder extends BaseBuilder<Simple3dBuilder> {

        private List<Coord3d> nodeCoordSection;

        public Simple3dBuilder() {
            super();
        }

        public Simple3dBuilder nodeCoordSection(List<Coord3d> nodeCoordSection) {
            this.nodeCoordSection = nodeCoordSection;
            return this;
        }

        public Simple3dTsp build() {
            return new Simple3dTsp(this);
        }
    }

}
