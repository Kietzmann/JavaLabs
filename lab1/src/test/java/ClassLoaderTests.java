import edu.kytsmen.java.classloader.gamma.App;
import edu.kytsmen.java.classloader.gamma.CustomClassLoader;
import edu.kytsmen.java.classloader.gamma.TestModule;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dmytro on 09.10.16.
 */
public class ClassLoaderTests {

    public static final String TEST_MODULE_VERSION_1_TOSTRING = "TestModule, version 1!";

    @Test
    public void TestLoadClassReturnPropriateClass() {
        CustomClassLoader ccl = new CustomClassLoader();
        try {
            Class clas = ccl.loadClass(App.CLASSPATH);
            TestModule tm = new TestModule();
            Assert.assertEquals(clas, tm.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestReturnsString() {
        TestModule tm = new TestModule();
        Assert.assertEquals(TEST_MODULE_VERSION_1_TOSTRING, tm.toString());
    }

}
