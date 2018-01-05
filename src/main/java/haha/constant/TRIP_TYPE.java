package haha.constant;

public enum TRIP_TYPE {
    BUSINESS(1), COUPLES(2), FAMILY(3), FRIENDS(4), SOLO(5);

    private final int code;

    TRIP_TYPE(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
