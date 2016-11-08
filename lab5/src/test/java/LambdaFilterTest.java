import edu.kytsmen.java.io.LambdaFilter;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dmytro on 07.11.16.
 */
public class LambdaFilterTest extends Assert {
    private final static LambdaFilter filter = new LambdaFilter();
    private final static String WORKING_DIR = System.getProperty("user.dir");
    private final static String DELIMITER = "/src/resources/";


    @Test
    public void testEmptyFile() {
        String filename = "empty";
        String filePath = WORKING_DIR + DELIMITER + filename;
        final String expectedResult = "File is empty";
        Pair<Integer, String> result = filter.filterList(filter.readFile(filePath));
        assertEquals(expectedResult, result.getValue());
    }

    @Test
    public void testRegularFile() {
        String filename = "testfile";
        String filePath = WORKING_DIR + DELIMITER + filename;
        final String expectedResult = "aliquet. Fusce lacinia eu sem ac luctus. Phasellus in auctor est";
        Pair<Integer, String> result = filter.filterList(filter.readFile(filePath));
        assertEquals(expectedResult, result.getValue());
    }
}
