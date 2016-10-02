package edu.kytsmen.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dkytsmen on 9/22/16.
 */
public class MaxLineReader {

    public static final String REGEX = "([^a-zA-Z']+)'*\\1*";

    public MaxLineReader() {
    }


    public List<String> getMaxWordCountLine(List<String> ribbonsList) {

        if (ribbonsList == null)
            throw new IllegalArgumentException("List of strings can't be null!");

        List<String> result = null;

        if (ribbonsList.size() == 0) {
            return result;
        }
        result = new ArrayList<>(ribbonsList);
        int position = 0;
        int maxSize = getWordsCount(ribbonsList.get(position));
        for (int i = 1; i < ribbonsList.size(); i++) {
            if (maxSize < getWordsCount(ribbonsList.get(i))) {
                result.remove(position);
                position = i;
                maxSize = getWordsCount(ribbonsList.get(position));
            } else {
                result.remove(i);
            }
        }
        return result;
    }

    public int getWordsCount(String sentence) {
        return sentence.split(REGEX).length;
    }


    private List<String> readFile(String filePath) {
        List<String> resultList = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            resultList = new ArrayList<>();
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                resultList.add(currentLine);
            }
            return resultList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

//    public void lambdaFilter(String path) {
//        try {
//            Files.lines(Paths.get(path)).map(line -> line.split())
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
