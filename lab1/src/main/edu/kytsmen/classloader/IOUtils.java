package main.edu.kytsmen.classloader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by dmytro on 02.10.16.
 */
public class IOUtils {
    public static final String TYPE_SEPARATOR = ".";
    //For UNIX
    public static final String PATH_SEPARATOR = "/";
    public static final String PACKAGE_FOLDER = "target/classes";
    public static final String CLASS_PATTERN = System.getProperty("classes.name.pattern", "");
    public static final String JAVAC = "javac";
    public static final int COMPILATION_EXIT_VALUE = 0;
    public static final String PACKAGE = System.getProperty("classes.package", "");

    public static boolean compileClass(String fileName) throws IOException {
        Process process = Runtime.getRuntime().exec(JAVAC + fileName);
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int exitValue = process.exitValue();
        return exitValue == COMPILATION_EXIT_VALUE;
    }

    public static byte[] getBytes(String fileName) throws IOException {
        File file = new File(fileName);
        long fileLength;
        byte readedBytes[] = null;
        int timesRepeated = 2;
        boolean fileLoaded = false;
        while (timesRepeated > 0) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                fileLength = file.length();
                readedBytes = new byte[(int) fileLength];
                int nextBytePosition = fileInputStream.read();
                if (nextBytePosition != fileLength) {
                    throw new IOException("Can't read all file.");
                }

            } catch (IOException e) {
                System.out.println("Waiting for class compilation...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                timesRepeated--;
            }

        }
        return readedBytes;
    }

    public static String getFullClassName() {
        //return PACKAGE + TYPE_SEPARATOR + CLASS_PATTERN;
        return "/home/dmytro/IdeaProjects/JavaLabs/target/classes/lab1/TestModule.class";
    }
}
