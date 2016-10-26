package edu.kytsmen.java.lambdas.task_1_1;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class AbbrivationBuilder {


    static final Predicate<String> valueNotNullOrEmpty = e -> e != null && !e.isEmpty();

    public static String build(List<String> list) {
        // implement function body in order all tests to complete successfully
        StringBuilder result;
        // result = list.filter(valueNotNullOrEmpty)stream().map(s -> s.substring(0, 1)).collect(Collectors.joining());
        result = list.parallelStream()
                .filter(valueNotNullOrEmpty)
                .map(l -> l.charAt(0))
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        return result.toString();
    }
}
