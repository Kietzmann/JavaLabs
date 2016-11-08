package edu.kytsmen.java.io;

/**
 * Created by dkytsmen on 10/25/16.
 */
public class Main {
    public static void main(String[] args) {
        IdiomaticCounter counter = new IdiomaticCounter();
        String workingDir = System.getProperty("user.dir");
        String filename = "textfile";
        String delimiter = "/lab5/src/resources/";
        String filePath = workingDir + delimiter + filename;
        String dest = workingDir + delimiter + "empty";
        counter.processFilter(filePath);
        System.out.println();
        LambdaFilter filter = new LambdaFilter();
        filter.filter(filePath);
        RibbonSorter sorter = new RibbonSorter();
        sorter.invokeSorting(filePath, dest);


    }
}
