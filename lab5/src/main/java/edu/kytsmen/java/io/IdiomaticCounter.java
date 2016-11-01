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
    private int currentmaxCount = 0;
    private List<String> lines = new ArrayList<>();

    public List<String> readMaxLineCount(String fileName) {
        List<String> extractedLines = null;
//        FileInputStream fileInputStream;
//        DataInputStream dataInputStream;
//        BufferedReader bufferedReader = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
//            fileInputStream = new FileInputStream(fileName);
//            dataInputStream = new DataInputStream(fileInputStream);
//            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            String line;
            extractedLines = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {

                int count = (line.split("\\s+")).length;
                if (count > currentmaxCount) {
                    extractedLines.clear();
                    extractedLines.add(line);
                    currentmaxCount = count;
                } else if (count == currentmaxCount) {
                    extractedLines.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//        } finally {
//            try {
//                if (bufferedReader != null) bufferedReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return extractedLines;
    }

    public int getCurrentmaxCount() {
        return currentmaxCount;
    }

    public void setCurrentmaxCount(int currentmaxCount) {
        this.currentmaxCount = currentmaxCount;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void processFilter(String filePath) {
        List<String> readedLines = readMaxLineCount(filePath);
        System.out.println(MAX_NUMBER_OF_WORDS_IN_A_LINE_IS + getCurrentmaxCount());
        System.out.println(LINE_S_WITH_MAX_WORD_COUNT);
        for (String line : readedLines) {
            System.out.println(line);
        }

    }
}
