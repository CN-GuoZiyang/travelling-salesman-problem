package top.guoziyang.enums;

/**
 * The format of the edge weight data
 *
 * @author Guo Ziyang
 */
public enum EdgeWeightFormat {

    FUNCTION("FUNCTION"), FULL_MATRIX("FULL_MATRIX"), UPPER_ROW("UPPER_ROW"), LOWER_ROW("LOWER_ROW"),
    UPPER_DIAG_ROW("UPPER_DIAG_ROW"), LOWER_DIAG_ROW("LOWER_DIAG_ROW"), UPPER_COL("UPPER_COL"),
    LOWER_COL("LOWER_COL"), UPPER_DIAG_COL("UPPER_DIAG_COL"), LOWER_DIAG_COL("LOWER_DIAG_COL");

    private final String format;

    private EdgeWeightFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

}
