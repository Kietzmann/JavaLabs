import edu.kytsmen.java.io.encrypt.CaesarsEncryptor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Created by dkytsmen on 11/8/16.
 */
public class CaesarsEncryptorTest extends Assert {
    public static final String ORIGINAL_FILE_NAME = "originalFile";
    public static final String ENCODED_FILE_NAME = "encodedFile";
    public static final String RESOURCES_DIRECTORY_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    public static final String ORIGINAL = "KIETZMANN";
    public static final String ENCODED = "uso~\u0084wkxx";
    public static final String ORIGINAL_FILE_PATH = RESOURCES_DIRECTORY_PATH + ORIGINAL_FILE_NAME;
    public static final String ENCODED_FILE_PATH = RESOURCES_DIRECTORY_PATH + ENCODED_FILE_NAME;
    public final Character KEY = '*';

    File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = File.createTempFile("test", "");
    }

    @After
    public void cleanResources() throws IOException {
        testFile.deleteOnExit();
    }

    @Test
    public void testEncodingForSingleLineFile() throws IOException {
        CaesarsEncryptor encryptor = new CaesarsEncryptor();
        InputStream source = new FileInputStream(ORIGINAL_FILE_PATH);
        encryptor.encode(source, new FileOutputStream(testFile), KEY);
        String firstLine = new BufferedReader(new FileReader(testFile)).readLine();
        assertEquals(ENCODED, firstLine);
    }

    @Test
    public void testDecodingForSingleLineFile() throws IOException {
        CaesarsEncryptor encryptor = new CaesarsEncryptor();
        InputStream source = new FileInputStream(ENCODED_FILE_PATH);
        encryptor.decode(source, new FileOutputStream(testFile), KEY);
        String firstLine = new BufferedReader(new FileReader(testFile)).readLine();
        assertEquals(ORIGINAL, firstLine);
    }
}
