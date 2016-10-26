package edu.kytsmen.java.io;

/**
 * Created by dkytsmen on 10/25/16.
 */
public class Main {
    public static void main(String[] args) {
        IdiomaticCounter counter = new IdiomaticCounter();
        String workingDir = System.getProperty("user.dir");
        String filename = "testfile";
        String delimiter = "/lab5/src/main/java/edu/kytsmen/java/io/";
        String filePath = workingDir + delimiter + filename;
        counter.processFilter(filePath);
        System.out.println();
        LambdaFilter filter = new LambdaFilter();
        filter.filter(filePath);


    }
}
