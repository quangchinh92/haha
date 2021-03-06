package haha.enums;

import java.util.Arrays;

import javax.transaction.NotSupportedException;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

public enum ROOM_TYPE {

    SGL("SGL", "Single bed room"),
    TWN("TWN", "Twin bed room"),
    DBL("DBL", "Double bed room"),
    TRPL("TRPL", "Triple bed room");

    @Getter
    private String id;

    @Getter
    private String description;

    ROOM_TYPE(String id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     *
     * @param id
     * @return ROOM_TYPE
     * @throws NotSupportedException
     */
    public static ROOM_TYPE codeToEnum(String id) throws NotSupportedException {
        if (StringUtils.isBlank(id)) {
            throw new NotSupportedException("");
        }
        return Arrays.asList(ROOM_TYPE.values()).parallelStream().filter(roomType -> {
            return roomType.getId().equals(id);
        }).findFirst().orElseThrow(() -> new NotSupportedException("Not support room type: " + id));
    }
}
