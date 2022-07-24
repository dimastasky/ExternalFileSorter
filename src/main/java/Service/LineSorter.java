package Service;

import IO.LineReader;
import IO.LineWriter;
import IO.Writer;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;


public class LineSorter {
    static Logger log = Logger.getLogger(LineSorter.class.getName());
    private int bufferSize;
    private final Writer writer;

    public LineSorter() { writer = new Writer(); }

    //------Метод для сортировки строк в файле------
    public File sortFile(File sourceFilePath, int runtimeFreeMem, int maxLineLength) throws IOException {
        bufferSize = runtimeFreeMem / (maxLineLength*100);
        List<File> tempFiles = splitAndSort(sourceFilePath);
        return merge(tempFiles);
    }

    //-----Метод для разделения файла на небольшие части и сортировки строк внутри этих частей
    private List<File> splitAndSort (File inputFile) throws IOException {
        List<File> tempFiles = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = "";
            log.info("Splitting the file into temporary files of a size acceptable for sorting.");
            while (line != null) {
                int counter = 0;
                lines.clear();
                while (counter <= bufferSize &&
                        (line = reader.readLine()) != null) {
                    lines.add(line);
                    counter++;
                }
                if (!lines.isEmpty()) {
                    tempFiles.add(sortAndSave(lines));
                }
            }
            log.info("Adding sorted lines to temporary files.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFiles;
    }

    //------Для сортировки внутри временных файлов
    private File sortAndSave(List<String> lines) throws IOException {
        Collections.sort(lines);
        return writer.writeLines(lines);
    }

    //------Слияние временных файлов в единый файл
    private File merge(List<File> files) {
        log.info("Starting the process of sorting and merging files into the resulting file.");
        while (files.size() > 1) {
            File file1 = files.remove(0);
            File file2 = files.remove(0);
            Optional<File> newFile = mergeFiles(file1, file2);
            newFile.ifPresent(files::add);

            file1.delete();
            file2.delete();
            System.gc();
        }
        log.info("Completing the sorting process and merging the files into the resulting file.");
        return files.get(0);
    }

    //------Сортировка и слияние двух файлов
    private Optional<File> mergeFiles(File file1, File file2) {
        try {
            LineReader reader1 = new LineReader(file1);
            LineReader reader2 = new LineReader(file2);
            LineWriter writer = new LineWriter();

            while (reader1.hasNext() && reader2.hasNext()) {
                if (reader1.peek().compareTo(reader2.peek()) <= 0) {
                    String line = reader1.next();
                    writer.write(line);
                } else {
                    String line = reader2.next();
                    writer.write(line);
                }
            }

            while (reader1.hasNext()) {
                writer.write(reader1.next());
            }
            while (reader2.hasNext()) {
                writer.write(reader2.next());
            }
            writer.close();
            reader1.close();
            reader2.close();
            return Optional.ofNullable(writer.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
