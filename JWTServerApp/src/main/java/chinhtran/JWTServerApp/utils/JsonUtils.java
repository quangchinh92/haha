package chinhtran.JWTServerApp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import chinhtran.JWTServerApp.consts.Message;
import chinhtran.JWTServerApp.exceptions.SystemException;

public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Convert object to String.
     * 
     * @param input
     * @return String
     */
    public static String writeValueAsString(Object input) {
        try {
            return mapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            throw new SystemException(Message.SYS_ERR_001);
        }
    }
}
