package edu.kytsmen.java.classloader.alpha;
import java.io.IOException;

/**
 * Created by Dmytro on 25.09.2016.
 */
public class Main {

    private static final ClassReloader reloader = new ClassReloader();

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException, IllegalAccessException, InstantiationException {
        int counter = 0;

        while (true) {
            String classExt = IOUtils.getFullClassName();
            Class loadClass = reloader.reloadClass(classExt);
            System.out.println(++counter + " " + loadClass.newInstance());
            Thread.sleep(1000);
        }
    }

}
