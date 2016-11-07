package edu.kytsmen.java.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IdiomaticCounter {
    private static final String MAX_NUMBER_OF_WORDS_IN_A_LINE_IS = "Max number of words in a line is: ";
    private static final String LINE_S_WITH_MAX_WORD_COUNT = "Line(s) with max word count: ";
    private int currentMaxCount = 0;
    private List<String> lines = new ArrayList<>();

    public List<String> readMaxLineCount(String fileName) {
        List<String> extractedLines = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            extractedLines = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                int count = (line.split("\\s+")).length;
                if (count > currentMaxCount) {
                    extractedLines.clear();
                    extractedLines.add(line);
                    currentMaxCount = count;
                } else if (count == currentMaxCount) {
                    extractedLines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (extractedLines != null && extractedLines.isEmpty()) {
            extractedLines.add("File is empty");
            return extractedLines;
        }
        return extractedLines;
    }

    public int getCurrentMaxCount() {
        return currentMaxCount;
    }

    public void processFilter(String filePath) {
        List<String> readedLines = readMaxLineCount(filePath);
        if (readedLines == null) {
            System.out.println("List with readed lines wasn't initialized. Something bad had happen.");
        }
        System.out.println(MAX_NUMBER_OF_WORDS_IN_A_LINE_IS + getCurrentMaxCount());
        System.out.println(LINE_S_WITH_MAX_WORD_COUNT);
        for (String line : readedLines) {
            System.out.println(line);
        }

    }
}
