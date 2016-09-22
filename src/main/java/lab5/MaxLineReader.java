package lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkytsmen on 9/22/16.
 */
public class MaxLineReader {

    public MaxLineReader() {
    }

    public String getMaxWordCountLine(List<String> ribbonsList){

    }

    private static

    private List<String> readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<String> resultList = new ArrayList<>();
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                resultList.add(currentLine);
            }
            return resultList;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
