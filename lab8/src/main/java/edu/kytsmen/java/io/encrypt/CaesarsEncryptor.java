package edu.kytsmen.java.io.encrypt;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkytsmen on 11/8/16.
 */
public class CaesarsEncryptor {

    public List<String> decode(InputStream source, OutputStream destination, Character symbol) throws IOException {
        List<String> decodedText = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new ShiftDecoder(new InputStreamReader(source), symbol))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                decodedText.add(line);
            }
        }
        Utils.writeLines(destination, decodedText, Charset.defaultCharset());
        return decodedText;
    }

    public void encode(InputStream source, OutputStream destination, Character symbol) throws IOException {
        List<String> text = Utils.readLines(source, Charset.defaultCharset());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new ShiftEncoder(new OutputStreamWriter(destination), symbol))) {
            for (String line : text) {
                bufferedWriter.write(line);
            }
        }
    }

    private static class ShiftEncoder extends FilterWriter {
        private Character symbol;

        public ShiftEncoder(Writer out, Character symbol) {
            super(out);
            this.symbol = symbol;
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for (int i = 0; i < cbuf.length; i++) {
                cbuf[i] = (char) (cbuf[i] + symbol);
            }
            super.write(cbuf, off, len);
        }
    }

    private static class ShiftDecoder extends FilterReader {
        private Character symbol;

        public ShiftDecoder(Reader in, Character symbol) {
            super(in);
            this.symbol = symbol;
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            int n = super.read(cbuf, off, len);
            for (int i = 0; i < cbuf.length; i++) {
                cbuf[i] = (char) (cbuf[i] - symbol);
            }
            return n;
        }
    }
}
