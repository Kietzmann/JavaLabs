package main.edu.kytsmen.classloader;
import javafx.util.Pair;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmytro on 02.10.16.
 */
public class ClassReloader {
    public static final String CLASS_EXTENSION = ".class";
    private static Map<String, Pair<String, CustomClassLoader>> hashedCorrespondingClassLoaders = new HashMap<>();

    public Class reloadClass(String className) throws ClassNotFoundException {
        byte readedBytes[] = null;

        try {
            //readedBytes = IOUtils.getBytes(IOUtils.PACKAGE_FOLDER + IOUtils.PATH_SEPARATOR + className.replace(IOUtils.TYPE_SEPARATOR, IOUtils.TYPE_SEPARATOR) + CLASS_EXTENSION);
            readedBytes = IOUtils.getBytes("/home/dmytro/IdeaProjects/JavaLabs/target/classes/lab1/TestModule.class");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (readedBytes != null) {
            String classHash = new String(readedBytes);
            Pair hashedClassLoader = hashedCorrespondingClassLoaders.get(className);

            if (hashedClassLoader != null && classHash.equals(hashedClassLoader.getKey())) {
                return ((CustomClassLoader) hashedClassLoader.getValue()).getLoadedClass(className);
            }

            ClassLoader customClassLoader = new CustomClassLoader(readedBytes);
            System.out.println("Reloading class by " + customClassLoader);

            Class loadedClass = customClassLoader.loadClass(className);

            hashedCorrespondingClassLoaders.remove(className);
            hashedCorrespondingClassLoaders.put(className, new Pair(classHash, customClassLoader));

            return loadedClass;
        } else {
            return Class.forName(className);
        }
    }
}
