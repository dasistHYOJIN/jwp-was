package webserver.message.request;

import org.slf4j.Logger;
import webserver.message.HttpMethod;
import webserver.message.HttpVersion;

import java.net.URI;
import java.net.URISyntaxException;

import static org.slf4j.LoggerFactory.getLogger;

public class RequestStartLine {
    private static final Logger LOG = getLogger(Request.class);

    private static final int URL_INDEX = 1;
    private static final int METHOD_INDEX = 0;
    private static final int PROTOCOL_INDEX = 2;

    private final HttpMethod httpMethod;
    private final URI url;
    private final HttpVersion httpVersion;

    public RequestStartLine(final String[] httpMethodAndPath) throws URISyntaxException {
        this.httpMethod = HttpMethod.valueOf(httpMethodAndPath[METHOD_INDEX]);
        this.url = new URI(httpMethodAndPath[URL_INDEX]);
        this.httpVersion = HttpVersion.of(httpMethodAndPath[PROTOCOL_INDEX]);

        LOG.debug("RequestStartLine - httpVersion: {}, method: {}, path: {}",
                this.getHttpVersion(), this.getHttpMethod(), this.getPath());
    }

    protected String getHttpMethod() {
        return httpMethod.name();
    }

    protected String getPath() {
        return url.getPath();
    }

    protected String getQuery() {
        return url.getQuery();
    }

    protected String getUrl() {
        return url.toString();
    }

    protected String getHttpVersion() {
        return httpVersion.getVersion();
    }

    protected boolean matchesMethod(HttpMethod method) {
        return this.httpMethod.matchesMethod(method);
    }
}
