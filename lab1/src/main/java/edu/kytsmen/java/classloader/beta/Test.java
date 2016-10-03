package edu.kytsmen.java.classloader.beta;

/**
 * Created by dkytsmen on 10/3/16.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        for (; ; ) {
            int counter = 0;
            ClassLoader customLoader = new DynamicClassReloader(new String[]{"."});
            Class testModule = Class.forName("TestModule", true, customLoader);
            Object testModuleObject = testModule.newInstance();
            System.out.println("Load counter: " + ++counter);
            System.out.println(testModuleObject);
            Thread.sleep(1000);
        }
    }
}
