package top.guoziyang.problems.impl;

import top.guoziyang.beans.Coord3d;
import top.guoziyang.problems.TSPProblem;

import java.util.List;

/**
 * The implement of the tsp problem with only 3d coordinate data
 *
 * Applicable types include: EUC_3D, MAX_3D, MAN_3D
 *
 * @author Guo Ziyang
 */
public class Simple3dTsp extends TSPProblem {

    private final List<Coord3d> nodeCoordSection;

    private Simple3dTsp(Builder builder) {
        super(builder);
        this.nodeCoordSection = builder.nodeCoordSection;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Coord3d> getNodeCoordSection() {
        return nodeCoordSection;
    }

    public static class Builder extends BaseBuilder<Builder> {

        private List<Coord3d> nodeCoordSection;

        Builder() {
            super();
        }

        public Builder nodeCoordSection(List<Coord3d> nodeCoordSection) {
            this.nodeCoordSection = nodeCoordSection;
            return this;
        }

        @Override
        public Simple3dTsp build() {
            return new Simple3dTsp(this);
        }
    }

}
