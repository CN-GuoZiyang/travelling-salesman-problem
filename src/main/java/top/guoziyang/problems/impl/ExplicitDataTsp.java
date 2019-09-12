package top.guoziyang.problems.impl;

import top.guoziyang.beans.Coord2d;
import top.guoziyang.enums.DisplayDataType;

import java.util.List;

/**
 * The implement of the explicit tsp problem with not only EDGE_WEIGHT_SECTION but also DISPLAY_DATA_SECTION
 *
 * @author Guo Ziyang
 */
public class ExplicitDataTsp extends ExplicitTsp {

    private final DisplayDataType displayDataType;
    private final List<Coord2d> displayDataSection;

    private ExplicitDataTsp(Builder builder) {
        super(builder);
        this.displayDataType = builder.displayDataType;
        this.displayDataSection = builder.displayDataSection;
    }

    public static Builder builder() {
        return new Builder();
    }

    public DisplayDataType getDisplayDataType() {
        return displayDataType;
    }

    public List<Coord2d> getDisplayDataSection() {
        return displayDataSection;
    }

    public static class Builder extends ExplicitTsp.Builder {

        private DisplayDataType displayDataType;
        private List<Coord2d> displayDataSection;

        public Builder displayDataType(DisplayDataType displayDataType) {
            this.displayDataType = displayDataType;
            return this;
        }

        public Builder displayDataSection(List<Coord2d> displayDataSection) {
            this.displayDataSection = displayDataSection;
            return this;
        }

        public ExplicitDataTsp build() {
            return new ExplicitDataTsp(this);
        }

    }

}
