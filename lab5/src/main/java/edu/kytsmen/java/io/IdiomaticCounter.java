package edu.kytsmen.java.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkytsmen on 9/23/16.
 */
public class IdiomaticCounter {
    public static final String MAX_NUMBER_OF_WORDS_IN_A_LINE_IS = "Max number of words in a line is: ";
    public static final String LINE_S_WITH_MAX_WORD_COUNT = "Line(s) with max word count: ";
    private int currentmaxCount = 0;
    private List<String> lines = new ArrayList<String>();

    public List<String> readMaxLineCount(String fileName) {
        List<String> lines = null;
        FileInputStream fileInputStream;
        DataInputStream dataInputStream;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            dataInputStream = new DataInputStream(fileInputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            String line = null;
            lines = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {

                int count = (line.split("\\s+")).length;
                if (count > currentmaxCount) {
                    lines.clear();
                    lines.add(line);
                    currentmaxCount = count;
                } else if (count == currentmaxCount) {
                    lines.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
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
        List<String> lines = readMaxLineCount(filePath);
        System.out.println(MAX_NUMBER_OF_WORDS_IN_A_LINE_IS + getCurrentmaxCount());
        System.out.println(LINE_S_WITH_MAX_WORD_COUNT);
        for (String line : lines) {
            System.out.println(line);
        }

    }
}
