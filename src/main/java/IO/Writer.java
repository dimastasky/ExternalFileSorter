package IO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Writer {
    public File writeLines(List<String> lines) throws IOException {
        File tempFile = File.createTempFile("sorted", ".tmp.txt");
        tempFile.deleteOnExit();
        FileUtils.writeLines(tempFile, lines);
        return tempFile;
    }
}
