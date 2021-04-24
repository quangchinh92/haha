package haha.wrapper.enums;

import java.util.Arrays;

import lombok.Getter;

public enum RESPONSE_STATUS {

    SUCCESS(200), BAD_REQUEST(400), NOT_FOUND(404), ERROR(999);

    @Getter
    private Integer code;

    RESPONSE_STATUS(Integer code) {
        this.code = code;
    }

    /**
     *
     * @param code
     * @return RESPONSE_STATUS
     */
    public static RESPONSE_STATUS codeToEnum(Integer code) {
        return Arrays.asList(RESPONSE_STATUS.values()).stream().filter(enumElement -> {
            return enumElement.getCode().equals(code);
        }).findFirst().orElse(RESPONSE_STATUS.ERROR);
    }

}
