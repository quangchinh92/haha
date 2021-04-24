package haha.wrapper;

import java.util.List;

import haha.wrapper.enums.RESPONSE_STATUS;
import lombok.Getter;

public class ResponseBodyWrapper<T> {

    @Getter
    private Integer responseStatus;

    @Getter
    private String message;

    @Getter
    private List<T> data;

    private ResponseBodyWrapper(Integer responseStatus, String message, List<T> data) {
        this.responseStatus = responseStatus;
        this.message = message;
        this.data = data;
    }

    /**
     *
     * @param data
     * @return ResponseEntityWrapper
     */
    public static <T> ResponseBodyWrapper<T> createSuccess(List<T> data) {
        return new ResponseBodyWrapper<T>(RESPONSE_STATUS.SUCCESS.getCode(), null,
                data);
    }

    /**
     *
     * @param message
     * @return ResponseBodyWrapper
     */
    public static <T> ResponseBodyWrapper<T> createError(String message) {
        return new ResponseBodyWrapper<T>(RESPONSE_STATUS.ERROR.getCode(), message, null);
    }
}
