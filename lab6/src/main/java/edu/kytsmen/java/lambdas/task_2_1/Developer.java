package edu.kytsmen.java.lambdas.task_2_1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class Developer {

    private String name;
    private Set<String> languages;

    public Developer(String name) {
        this.languages = new HashSet<>();
        this.name = name;
    }

    public void add(String language) {
        this.languages.add(language);
    }

    public Set<String> getLanguages() {
        return languages;
    }
}