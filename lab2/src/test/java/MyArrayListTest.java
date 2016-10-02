import edu.kytsmen.java.collections.lists.MyArrayList;
import edu.kytsmen.java.collections.lists.MyList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by dmytro on 03.10.16.
 */
public class MyArrayListTest extends Assert{
    private MyList<String> myList;

    @Before
    public void startUp(){
        myList = new MyArrayList<>();
    }

    @Test
    public void testCopyConstructor(){
        myList = new MyArrayList<>(Arrays.asList("a","b", "c"));
        assertEquals(myList.size(),3);
    }
}
