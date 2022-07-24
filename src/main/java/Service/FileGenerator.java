package Service;

import org.apache.commons.lang3.RandomStringUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;


public class FileGenerator {

    static Logger log = Logger.getLogger(FileGenerator.class.getName());

    // Создать файл
    public void createFile(String filePath, int countOfLines, int maxLineLength) throws IOException {
            FileOutputStream generatedFile = new FileOutputStream(filePath);
            log.info("Source file created.");
            for (int i = 0; i < countOfLines; i++) {
                generatedFile.write(generateLine(maxLineLength).getBytes(StandardCharsets.UTF_8));
                generatedFile.write("\n".getBytes(StandardCharsets.UTF_8));
            }
            log.info("Random Strings added to file.");
            generatedFile.close();
    }

    // Сгенерировать строку
    private String generateLine(int maxLineLength) {
        return RandomStringUtils.randomAlphanumeric(1, maxLineLength);
    }


}
