package IO;

import Service.FileGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class LineWriter {
    static Logger log = Logger.getLogger(FileGenerator.class.getName());

    private BufferedWriter writer;
    private File file;

    public LineWriter() throws IOException {
        file = File.createTempFile("sorted", ".tmp.txt");
        file.deleteOnExit();
        writer = new BufferedWriter(new FileWriter(file));
    }

    public void write(String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    public File getFile() {
        return file;
    }

    public void close() throws IOException {
        writer.close();
    }
}
