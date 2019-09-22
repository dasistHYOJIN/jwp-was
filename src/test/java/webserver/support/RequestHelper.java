package webserver.support;

import utils.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class RequestHelper {
    protected final String urlWithQuery = "/user/create?userId=javajigi&name=pob";
    protected final String requestGetStartLine = "GET /index.html HTTP/1.1";
    protected final String requestGetStartLineWithQuery = "GET /index.html?userId=javajigi&name=pob HTTP/1.1";
    protected final String requestGetHeader =
            "GET /index.html HTTP/1.1\n" +
                    "Host: localhost:8080\n" +
                    "Connection: keep-alive\n" +
                    "Accept: */*";
    protected final String requestGetHeaderWithQuery =
            "GET /user/create?userId=javajigi&name=pobi HTTP/1.1\n" +
                    "Host: localhost:8080\n" +
                    "Connection: keep-alive\n" +
                    "Accept: */*";
    protected final String requestNotFoundHeader =
            "GET /weirdPath HTTP/1.1\n" +
                    "Host: localhost:8080\n" +
                    "Connection: keep-alive\n" +
                    "Accept: */*";

    protected InputStream inputStream(final String requestInput) {
        return new ByteArrayInputStream(requestInput.getBytes());
    }

    protected IOUtils ioUtils(final String requestInput) {
        final InputStream inputStream = new ByteArrayInputStream(requestInput.getBytes());
        return new IOUtils(inputStream);
    }
}
