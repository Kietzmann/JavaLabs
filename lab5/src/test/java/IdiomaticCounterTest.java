import edu.kytsmen.java.io.IdiomaticCounter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dkytsmen on 10/25/16.
 */
public class IdiomaticCounterTest extends Assert {
    private final static IdiomaticCounter COUNTER = new IdiomaticCounter();
    private final static String WORKING_DIR = System.getProperty("user.dir");
    private final static String DELIMITER = "/src/resources/";


    @Test
    public void testEmptyFile() {
        String filename = "empty";
        String filePath = WORKING_DIR + DELIMITER + filename;
        final String expectedResult = "File is empty";
        List<String> result = COUNTER.readMaxLineCount(filePath);
        assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void testRegularFile() {
        String filename = "testfile";
        String filePath = WORKING_DIR + DELIMITER + filename;
        final String expectedResult = "aliquet. Fusce lacinia eu sem ac luctus. Phasellus in auctor est";
        List<String> result = COUNTER.readMaxLineCount(filePath);
        assertEquals(expectedResult, result.get(0));
    }
}
