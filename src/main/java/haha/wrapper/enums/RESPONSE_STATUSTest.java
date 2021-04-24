package haha.wrapper.enums;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class RESPONSE_STATUSTest {

    @Test
    public void testCodeToEnum() {
        assertThat(RESPONSE_STATUS.codeToEnum(200), is(RESPONSE_STATUS.SUCCESS));
        assertThat(RESPONSE_STATUS.codeToEnum(400), is(RESPONSE_STATUS.BAD_REQUEST));
        assertThat(RESPONSE_STATUS.codeToEnum(404), is(RESPONSE_STATUS.NOT_FOUND));
        assertThat(RESPONSE_STATUS.codeToEnum(999), is(RESPONSE_STATUS.ERROR));
        assertThat(RESPONSE_STATUS.codeToEnum(100), is(RESPONSE_STATUS.ERROR));
    }

}
