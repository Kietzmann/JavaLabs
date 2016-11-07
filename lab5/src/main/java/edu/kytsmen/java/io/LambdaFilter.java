package edu.kytsmen.java.io;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaFilter {
    public List<String> readFile(String filename) {
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Pair<Integer, String> filterList(List<String> unfilteredList) {
        return unfilteredList.parallelStream()
                .map(x -> new Pair<>(x.split("\\s").length, x))
                .max((o1, o2) -> o1.getKey().compareTo(o2.getKey()))
                .orElse(new Pair<>(0, "File is empty"));

    }

    public void filter(String filepath) {
        System.out.println(filterList(readFile(filepath)));
    }
}
