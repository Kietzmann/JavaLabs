import edu.kytsmen.java.io.IdiomaticCounter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dkytsmen on 10/25/16.
 */
public class IdiomaticCounterTest extends Assert {
    IdiomaticCounter counter = new IdiomaticCounter();
    String workingDir = System.getProperty("user.dir");
    String delimiter = "/src/resources/";


    @Test
    public void testEmptyFile() {
        String filename = "empty";
        String filePath = workingDir + delimiter + filename;
        final String expectedResult = "File is empty";
        List<String> result = counter.readMaxLineCount(filePath);
        assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void testRegularFile() {
        String filename = "testfile";
        String filePath = workingDir + delimiter + filename;
        final String expectedResult = "aliquet. Fusce lacinia eu sem ac luctus. Phasellus in auctor est";
        List<String> result = counter.readMaxLineCount(filePath);
        assertEquals(expectedResult, result.get(0));
    }
}
