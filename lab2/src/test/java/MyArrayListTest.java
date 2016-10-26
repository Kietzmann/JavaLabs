import edu.kytsmen.java.collections.lists.MyArrayList;
import edu.kytsmen.java.collections.lists.MyList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dmytro on 03.10.16.
 */
@RunWith(Theories.class)
public class MyArrayListTest extends Assert {
    private MyList<String> myList;
    private String[] expected = new String[]{"a", "b", "c"};

    @DataPoints
    public static int[] wrongIndexes = {-1, 0};

    @Before
    public void startUp() {
        myList = new MyArrayList<>();
    }

    @Test
    public void testCopyConstructor() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        assertEquals(myList.size(), expected.length);
    }

    @Test
    public void testGet() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        assertEquals(myList.get(1), expected[1]);
    }

    @Test
    public void testAdd() {
        myList.add(expected[0]);
        assertEquals(myList.size(), 1);
        assertEquals(myList.get(0), expected[0]);
    }

    @Test
    public void testToArray() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        assertArrayEquals(myList.toArray(), expected);
    }

    @Test
    public void testRemove() {
        int index = 0;
        myList = new MyArrayList<>(Arrays.asList(expected));
        myList.remove(index);
        assertArrayEquals(myList.toArray(), Arrays.copyOfRange(expected, index + 1, expected.length));
    }

    @Test
    public void testIndexOf() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        int index = myList.indexOf(expected[2]);
        assertEquals(index, 2);
    }

    @Test
    public void testIndexOfNull() {
        myList = new MyArrayList<>(Arrays.asList("a", null, "b", null));
        int index = myList.indexOf(null);
        assertEquals(index, 1);
    }

    @Test
    public void testIndexOfNotExist() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        int index = myList.indexOf("d");
        assertEquals(index, -1);
    }

    @Test
    public void testSet() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        myList.set(0, "b");
        assertEquals(myList.get(0), "b");
    }

    @Test
    public void testAddToIndex() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        myList.add(1, "c");
        assertArrayEquals(myList.toArray(), new String[]{"a", "c", "b", "c"});
    }

    @Test
    public void testAddAll() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        myList.addAll(Arrays.asList("d", "e", "f"));
        assertArrayEquals(myList.toArray(), new String[]{"a", "b", "c", "d", "e", "f"});
    }

    @Test
    public void testAddAllAtIndex() {
        myList = new MyArrayList<>(Arrays.asList(expected));
        myList.addAll(1, Arrays.asList("c", "d", "c"));
        assertArrayEquals(myList.toArray(), new String[]{"a", "c", "d", "c", "b", "c"});
    }


    @Test
    public void testForCapacityGrowth() {
        myList = new MyArrayList<>();
        for (int i = 0; i < 30; i++) {
            myList.add(i + "element");
        }
        assertEquals(myList.size(), 30);
    }


    @Theory
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetRangeCheck(int wrongIndex) {
        myList.get(wrongIndex);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllRangeCheck() {
        myList.addAll(-1, Arrays.asList(expected));
    }

    @Theory
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeCheck(int wrongIndex) {
        myList.remove(wrongIndex);
    }

    @Theory
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetRangeCheck(int wrongIndex) {
        myList.set(wrongIndex, "element");
    }
}
