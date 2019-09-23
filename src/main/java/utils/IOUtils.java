package utils;

import org.slf4j.Logger;
import utils.exception.UnexpectedIOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class IOUtils implements Iterable<String> {
    private static final Logger LOG = getLogger(IOUtils.class);

    private final BufferedReader bufferedReader;

    public IOUtils(final InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    public String readBody(int contentLength) throws IOException {
        char[] body = new char[contentLength];
        this.bufferedReader.read(body, 0, contentLength);
        return String.copyValueOf(body);
    }

    @Override
    public Iterator<String> iterator() {
        try {
            return new MyIterator(bufferedReader);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return iterator();
        }
    }

    private static class MyIterator implements Iterator<String> {
        private BufferedReader bufferedReader;
        private String nextLine;

        MyIterator(final BufferedReader bufferedReader) throws IOException {
            this.bufferedReader = bufferedReader;
            this.nextLine = bufferedReader.readLine();
        }

        @Override
        public boolean hasNext() {
            return Objects.nonNull(nextLine) && !"".equals(nextLine);
        }

        @Override
        public String next() {
            try {
                final String result = this.nextLine;
                this.nextLine = bufferedReader.readLine();
                return result;
            } catch (IOException e) {
                LOG.error(e.getMessage());
                throw new UnexpectedIOException(e.getMessage());
            }
        }
    }
}
