package edu.kytsmen.java.io.encrypt;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkytsmen on 11/8/16.
 */
public class Utils {
    private Utils() {

    }

    public static List<String> readLines(InputStream inputStream, Charset charset) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        }
        return result;
    }

    public static void writeLines(OutputStream outputStream, List<String> text, Charset charset) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, charset))) {
            for (String line : text) {
                bufferedWriter.write(line);
            }
        }
    }
}
