package top.guoziyang.enums;

/**
 * The type of the data displayed
 *
 * @author Guo Ziyang
 */
public enum DisplayDataType {

    COORD_DISPLAY("COORD_DISPLAY"), TWOD_DISPLAY("TWOD_DISPLAY"), NO_DISPLAY("NO_DISPLAY");

    private String type;

    private DisplayDataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
