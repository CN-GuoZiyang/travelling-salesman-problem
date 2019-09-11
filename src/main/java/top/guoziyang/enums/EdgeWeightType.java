package top.guoziyang.enums;

public enum EdgeWeightType {

    EXPLICIT("EXPLICIT"), EUC_2D("EUC_2D"), EUC_3D("EUC_3D"),
    MAX_2D("MAX_2D"), MAX_3D("MAX_3D"), MAN_2D("MAN_2D"), MAN_3D("MAN_3D"),
    CEIL_2D("CEIL_2D"), GEO("GEO"), ATT("ATT"), XRAY1("XRAY1"), XRAY2("XRAY2"), SPECIAL("SPECIAL");

    private final String type;

    private EdgeWeightType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
