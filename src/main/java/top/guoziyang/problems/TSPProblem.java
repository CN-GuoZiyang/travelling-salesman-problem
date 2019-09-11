package top.guoziyang.problems;

import top.guoziyang.enums.EdgeWeightType;

public class TSPProblem {

    private final String name;
    private final int dimension;
    private final EdgeWeightType edgeWeightType;

    public TSPProblem(BaseBuilder baseBuilder) {
        this.name = baseBuilder.name;
        this.dimension = baseBuilder.dimension;
        this.edgeWeightType = baseBuilder.edgeWeightType;
    }

    public String getName() {
        return name;
    }

    public int getDimension() {
        return dimension;
    }

    public EdgeWeightType getEdgeWeightType() {
        return edgeWeightType;
    }

    public static class BaseBuilder<T extends BaseBuilder> {
        private String name;
        private int dimension;
        private EdgeWeightType edgeWeightType;

        public T name(String name) {
            this.name = name;
            return (T) this;
        }

        public T dimension(int dimension) {
            this.dimension = dimension;
            return (T) this;
        }

        public T edgeWeightType(EdgeWeightType edgeWeightType) {
            this.edgeWeightType = edgeWeightType;
            return (T) this;
        }

        public TSPProblem build() {
            return new TSPProblem(this);
        }

    }
}
