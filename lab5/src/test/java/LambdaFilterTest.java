import edu.kytsmen.java.io.IdiomaticCounter;
import edu.kytsmen.java.io.LambdaFilter;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dmytro on 07.11.16.
 */
public class LambdaFilterTest extends Assert{
    LambdaFilter filter = new LambdaFilter();
    String workingDir = System.getProperty("user.dir");
    String delimiter = "/src/resources/";


    @Test
    public void testEmptyFile() {
        String filename = "empty";
        String filePath = workingDir + delimiter + filename;
        final String expectedResult = "File is empty";
        Pair<Integer, String> result  = filter.filterList(filter.readFile(filePath));
        assertEquals(expectedResult, result.getValue());
    }

    @Test
    public void testRegularFile() {
        String filename = "testfile";
        String filePath = workingDir + delimiter + filename;
        final String expectedResult = "aliquet. Fusce lacinia eu sem ac luctus. Phasellus in auctor est";
        Pair<Integer, String> result  = filter.filterList(filter.readFile(filePath));
        assertEquals(expectedResult, result.getValue());
    }
}
