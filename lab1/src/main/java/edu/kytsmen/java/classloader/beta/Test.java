package edu.kytsmen.java.classloader.beta;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by dkytsmen on 10/3/16.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        for (; ; ) {
            ClassLoader loader = new DynamicClassReloader(new String[]{"."});
            // текущий каталог "." будет единственным каталогом поиска
            Class clazz = Class.forName("TestModule", true, loader);
            Object object = clazz.newInstance();
            System.out.println(object);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
    }
}
