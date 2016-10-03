package edu.kytsmen.java.classloader.beta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dkytsmen on 10/3/16.
 */
public class DynamicClassReloader extends ClassLoader {

    private Map<String, Class> classesHash = new HashMap<>();
    public final String[] classPath;

    public DynamicClassReloader(String[] classPath) {
        this.classPath = classPath;
    }


    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        Class result = classesHash.get(name);
        if (result != null) {
            return result;
        }
        File file = findFile(name.replace('.', '/'), ".class");
        if (file == null) {
            return findSystemClass(name);
        }
        try {
            byte[] classBytes = loadFileAsBytesArray(file);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Cannot load specified class " + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + name + ": " + e);
        }
        classesHash.put(name, result);
        return result;
    }

    protected URL findResource(String name) {
        File file = findFile(name, "");
        if (file == null)
            return null;
        try {
            return file.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private File findFile(String name, String extension) {
        for (int k = 0; k < classPath.length; k++) {
            File file = new File((new File(classPath[k])).getPath() + File.separatorChar + name.replace('/', File.separatorChar) + extension);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    public static byte[] loadFileAsBytesArray(File file) throws IOException {
        byte[] result = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(result, 0, result.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
