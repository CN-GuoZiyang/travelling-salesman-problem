package top.guoziyang.problems;

import top.guoziyang.beans.Coord2d;
import top.guoziyang.enums.EdgeWeightFormat;
import top.guoziyang.enums.EdgeWeightType;
import top.guoziyang.problems.impl.ExplicitTsp;
import top.guoziyang.problems.impl.Simple2dTsp;

import java.util.List;

/**
 * The base class for the tsp problem
 *
 * @author Guo Ziyang
 */
public class TSPProblem {

    private final String name;
    private final int dimension;
    private final EdgeWeightType edgeWeightType;

    protected TSPProblem(BaseBuilder baseBuilder) {
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

    public static int getDistance(TSPProblem problem, int city1, int city2) {
        if(problem instanceof Simple2dTsp) {
            return getSimple2dDistance((Simple2dTsp) problem, city1, city2);
        } else {
            return getExplicitDistance((ExplicitTsp) problem, city1, city2);
        }
    }

    private static int getSimple2dDistance(Simple2dTsp problem, int city1, int city2) {
        EdgeWeightType edgeWeightType = problem.getEdgeWeightType();
        List<Coord2d> nodeCoordSection = problem.getNodeCoordSection();
        Coord2d c1 = nodeCoordSection.get(city1 - 1);
        Coord2d c2 = nodeCoordSection.get(city2 - 1);
        if(EdgeWeightType.valueOf("EUC_2D") == edgeWeightType) {
            double xd = c1.getX() - c2.getX();
            double yd = c1.getY() - c2.getY();
            double distance = Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2));
            return (int)Math.round(distance);
        } else if(EdgeWeightType.valueOf("CEIL_2D") == edgeWeightType) {
            double xd = c1.getX() - c2.getX();
            double yd = c1.getY() - c2.getY();
            double distance = Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2));
            return (int)Math.ceil(distance);
        } else if(EdgeWeightType.valueOf("GEO") == edgeWeightType) {
            double PI = 3.141592;
            int deg = (int) Math.round(c1.getX());
            double min = c1.getX() - deg;
            double latitude1 = PI * (deg + 5.0 * min / 3.0 ) / 180.0;
            deg = (int) Math.round(c1.getY());
            min = c1.getY() - deg;
            double longitude1 = PI * (deg + 5.0 * min / 3.0 ) / 180.0;
            deg = (int) Math.round(c2.getX());
            min = c2.getX() - deg;
            double latitude2 = PI * (deg + 5.0 * min / 3.0 ) / 180.0;
            deg = (int) Math.round(c2.getY());
            min = c2.getY() - deg;
            double longitude2 = PI * (deg + 5.0 * min / 3.0 ) / 180.0;
            double RRR = 6378.388;
            double q1 = Math.cos(longitude1 - longitude2);
            double q2 = Math.cos(latitude1 - latitude2);
            double q3 = Math.cos(latitude1 + latitude2);
            return (int) ( RRR * Math.acos(0.5 * ((1.0 + q1) * q2 - (1.0 - q1) * q3) ) + 1.0);
        } else if(EdgeWeightType.valueOf("ATT") == edgeWeightType) {
            double xd = c1.getX() - c2.getX();
            double yd = c1.getY() - c2.getY();
            double rij = Math.sqrt((xd * xd + yd * yd) / 10.0);
            int tij = (int) Math.round(rij);
            if (tij < rij) {
                return tij + 1;
            } else {
                return tij;
            }
        }
        return -1;
    }

    private static int getExplicitDistance(ExplicitTsp problem, int city1, int city2) {

        EdgeWeightFormat edgeWeightFormat = problem.getEdgeWeightFormat();
        if(EdgeWeightFormat.valueOf("UPPER_ROW") == edgeWeightFormat) {
            if(city1 > city2) {
                int index = city1 * (2 * problem.getDimension() - 1 - city1) / 2 + city2 - 1;
                return problem.getEdgeWeightSection()[index];
            } else {
                int index = city1 * (2 * problem.getDimension() - 1 - city1) / 2 + city2 - 2;
                return problem.getEdgeWeightSection()[index];
            }
        } else if(EdgeWeightFormat.valueOf("FULL_MATRIX") == edgeWeightFormat) {
            int index = city1 * problem.getDimension() + city2 - 1;
            return problem.getEdgeWeightSection()[index];
        } else if(EdgeWeightFormat.valueOf("LOWER_DIAG_ROW") == edgeWeightFormat) {
            int index = -1;
            if(city1 > city2) {
                index = city1 * (1 + city1) / 2 + city2 - 1;
            } else {
                index = city2 * (1 + city2) / 2 + city1 - 1;
            }
            return problem.getEdgeWeightSection()[index];
        } else if(EdgeWeightFormat.valueOf("UPPER_DIAG_ROW") == edgeWeightFormat) {
            int index = -1;
            if(city1 > city2) {
                index = city1 * problem.getDimension() + city2 - 1;
            } else {
                index = city2 * problem.getDimension() + city1 - 1;
            }
            return problem.getEdgeWeightSection()[index];
        }
        return -1;
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
