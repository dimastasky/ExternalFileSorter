import Service.FileGenerator;
import Service.LineSorter;
import util.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Программа генерирует исходный файл,
 * затем сортирует его с помощью алгоритма Внешней Сортировки (External Sorting).
 */

public class Main {

    /**
     * @param RUNTIME_FREE_MEMORY Свободная память в JVM
     * @param MAX_LINE_LENGTH Максимальная длина строки
     * @param COUNT_OF_LINES Количество строк
     * @param SOURCE_FILE_PATH Путь и название исходного файла
     * @param RESULT_FILE_PATH Путь и название сортированного файла
     */
    private static final int RUNTIME_FREE_MEMORY = (int) Runtime.getRuntime().freeMemory();
    private static final int MAX_LINE_LENGTH = 10;
    private static final int COUNT_OF_LINES = RUNTIME_FREE_MEMORY/(MAX_LINE_LENGTH*2); //Для создания файла, который не поместится в Память
    private static final String SOURCE_FILE_PATH = "source.txt";
    private static final String RESULT_FILE_PATH = "/sorted_file.txt";


    public static void main(String[] args) throws IOException {

        FileGenerator fg = new FileGenerator();
        LineSorter lineSorter = new LineSorter();
        ResourceLoader resourceLoader = new ResourceLoader();

        LocalDateTime from = LocalDateTime.now(); // Для вычисления времени выполнения программы

        try {
            fg.createFile(SOURCE_FILE_PATH, COUNT_OF_LINES, MAX_LINE_LENGTH);
            File inputFile = new File(SOURCE_FILE_PATH);
            File sortedFile = lineSorter.sortFile(inputFile, RUNTIME_FREE_MEMORY, MAX_LINE_LENGTH);
            String path = System.getProperty("user.dir") + RESULT_FILE_PATH;
            resourceLoader.writeResource(sortedFile, path);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        LocalDateTime to = LocalDateTime.now(); // Для вычисления времени выполнения программы
        long minutes = Duration.between(from, to).toMinutes();
        long seconds = Duration.between(from, to).toSeconds() - (Duration.between(from, to).toMinutes() * 60);
        System.out.println("Время выполнения программы: " + minutes + " мин. " + seconds + " сек.");
    }
}
