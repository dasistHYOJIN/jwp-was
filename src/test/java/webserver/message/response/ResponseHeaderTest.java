package webserver.message.response;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.message.HttpCookie;
import webserver.message.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseHeaderTest {
    @Test
    @DisplayName("쿠키 정보가 존재하지 않는 경우의 헤더 테스트")
    void toBytesWithoutCookie() {
        // given
        Map<String, String> responseFields = new HashMap<>();
        responseFields.put("Content-Type", MediaType.TEXT_HTML.getMediaType());

        // when
        ResponseHeader responseHeader = new ResponseHeader(responseFields, Maps.newHashMap());
        String result = new String(responseHeader.toBytes(5));

        // then
        assertThat(result)
                .isEqualTo("Content-Length: 5\r\n"
                        + "Content-Type: text/html; charset=utf-8");
    }

    @Test
    @DisplayName("쿠키 정보가 존재하는 경우의 헤더 테스트")
    void toBytesWithCookie() {
        // given
        Map<String, String> responseFields = new HashMap<>();
        responseFields.put("Content-Type", MediaType.TEXT_HTML.getMediaType());

        Map<String, HttpCookie> cookies = Maps.newHashMap();
        cookies.put("logined", new HttpCookie.Builder("logined", "true").path("/").build());

        // when
        ResponseHeader responseHeader = new ResponseHeader(responseFields, cookies);
        String result = new String(responseHeader.toBytes(5));

        // then
        assertThat(result)
                .isEqualTo("Content-Length: 5\r\n"
                        + "Content-Type: text/html; charset=utf-8\r\n"
                        + "Set-Cookie: logined=true; Path=/; ");
    }
}