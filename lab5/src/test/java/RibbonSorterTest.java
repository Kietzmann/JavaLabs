import edu.kytsmen.java.io.RibbonSorter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dkytsmen on 11/8/16.
 */
public class RibbonSorterTest extends Assert {
    final static String WORKING_DIR = System.getProperty("user.dir");
    final static String DELIMITER = "/src/resources/";
    final static String SOURCE_FILE = "testfile";
    final static String DESTINATION_FILE = "target";
    final static String EMPTY_FILE = "empty";
    final static String INPUT_FILE_PATH = WORKING_DIR + DELIMITER + SOURCE_FILE;
    final static String OUTPUT_FILE_PATH = WORKING_DIR + DELIMITER + DESTINATION_FILE;
    final static String EMPTY_FILE_PATH = WORKING_DIR + DELIMITER + EMPTY_FILE;


    @Test
    public void testRegularFile() throws IOException {
        RibbonSorter.invokeSorting(INPUT_FILE_PATH, OUTPUT_FILE_PATH);
        List<String> text = Files.lines(Paths.get(OUTPUT_FILE_PATH)).collect(Collectors.toList());
        assertTrue(checkOrder(text));
    }

    @Test
    public void testEmptyFile() throws IOException {
        RibbonSorter.invokeSorting(EMPTY_FILE_PATH, OUTPUT_FILE_PATH);
        List<String> text = Files.lines(Paths.get(OUTPUT_FILE_PATH)).collect(Collectors.toList());
        assertTrue(text.isEmpty());
    }

    private boolean checkOrder(List<String> text) {
        boolean result = true;
        for (int i = 0; i < text.size() - 1; i++) {
            result &= text.get(i + 1).length() >= text.get(i).length();
        }
        return result;
    }
}
