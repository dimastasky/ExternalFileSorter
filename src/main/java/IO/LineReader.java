package IO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;


public class LineReader {
    private LineIterator iterator;
    private String cache;

    public LineReader(File file) throws IOException {
        iterator = FileUtils.lineIterator(file);
    }

    public boolean hasNext() {
        if (cache != null) return true;
        cache = readNext();
        return cache != null;
    }

    public String peek() { return cache; }

    public String next() {
        String value = cache;
        cache = null;
        return value;
    }

    private String readNext() {
        return iterator.hasNext() ? iterator.nextLine() : null;
    }

    public void close() throws IOException {
        iterator.close();
    }
}
