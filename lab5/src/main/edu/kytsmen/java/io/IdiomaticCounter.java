package main.edu.kytsmen.java.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkytsmen on 9/23/16.
 */
public class IdiomaticCounter {
    private int currentmaxCount = 0;
    private List<String> lines = new ArrayList<String>();

    public void readMaxLineCount(String fileName) {
        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            dataInputStream = new DataInputStream(fileInputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            String line = null;
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
        readMaxLineCount(filePath);
        System.out.println("Max number of words in a line is: " + getCurrentmaxCount());
        System.out.println("Line with max word count: ");
        List<String> lines = getLines();
        for (String line : lines) {
            System.out.println(line);
        }

    }
}
