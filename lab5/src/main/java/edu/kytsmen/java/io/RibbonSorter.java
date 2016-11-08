package edu.kytsmen.java.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dkytsmen on 11/8/16.
 */
public class RibbonSorter {

    public List<String> readFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Filepath in cannot be null or empty");
        }
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> sortFile(List<String> inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream cannot be null");
        }
        return inputStream.stream().sorted((x, y) -> Integer.compare(x.length(), y.length())).collect(Collectors.toList());
    }

    public void writeFile(List<String> streamToWrite, String filepath) {
        if (filepath == null || filepath.isEmpty()) {
            throw new IllegalArgumentException("Filepath out cannot be null or empty");
        }
        if (streamToWrite == null) {
            throw new IllegalArgumentException("Input stream cannot be null");
        }
        try {
            Files.write(Paths.get(filepath), ((Iterable<String>) streamToWrite.stream()::iterator));
            System.out.println("Operation successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readSortWrite(String filenameIn, String filenameOut) {
        try (Stream<String> stream = Files.lines(Paths.get(filenameIn))) {
            Files.write(Paths.get(filenameOut),
                    (Iterable<String>) stream.sorted((x, y) -> Integer.compare(x.length(), y.length()))::iterator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void invokeSorting(String inputFilepath, String outputFilepath) {
        writeFile(sortFile(readFile(inputFilepath)), outputFilepath);
    }
}
