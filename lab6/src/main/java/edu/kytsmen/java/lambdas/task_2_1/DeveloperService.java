package edu.kytsmen.java.lambdas.task_2_1;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class DeveloperService {

    public static List<String> getLanguages(List<Developer> team) {
        List<String> result;
        result = team.stream()
                .map(Developer::getLanguages)
                .flatMap(Set::stream)
                .collect(Collectors.toList());
        return result;
    }


}
