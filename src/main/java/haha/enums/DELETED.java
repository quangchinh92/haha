package haha.enums;

import lombok.Getter;

public enum DELETED {
    YES(1), NO(0);

    @Getter
    private Integer value;

    DELETED(Integer value) {
        this.value = value;
    }
}
