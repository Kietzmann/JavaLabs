package edu.kytsmen.java.classloader.gamma;

/**
 * Created by dmytro on 09.10.16.
 */
public class App {

    public static final String CLASSPATH = "edu.kytsmen.java.classloader.gamma.TestModule";
    public static final int MILLIS_TO_WAIT = 1000;

    static public void main(String args[]) throws Exception {

        while (true) {
            CustomClassLoader ccl = new CustomClassLoader();
            Class classs = ccl.loadClass(CLASSPATH);
            Object testModule = classs.newInstance();
            System.out.println(testModule);
            Thread.sleep(MILLIS_TO_WAIT);
        }
    }
}
