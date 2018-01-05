package haha.constant;

public enum RATING {
    TERRIBLE(1), POOR(2), AVERAGE(3), GOOD(4), EXCELLENT(5);

    private final int code;

    RATING(int code) {
        this.code = code;
    }
    
    public Integer getCode() {
        return code;
    }
}
