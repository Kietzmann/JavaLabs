package edu.kytsmen.java.classloader.gamma;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by dmytro on 09.10.16.
 */
public class CustomClassLoader extends ClassLoader {

    public static final String FILE_READ_EXCEPTION_MESSAGE = "Cannot read all file, current length is greater than elligible";
    public static final String COMPILING_CLASS_MESSAGE = "Compiling modified class: ";
    public static final String JAVAC_STRING = "javac ";
    public static final String JAVA_EXTENSION = ".java";
    public static final String CLASS_EXTENSION = ".class";
    public static final String CLASS_NOT_FOUND_EXCEPTION_MESSAGE = "Compile failed: ";

    private byte[] getBytes(String fileName) throws IOException {
        File file = new File(fileName);
        long fileLength = file.length();
        byte[] rawFile = new byte[(int) fileLength];
        FileInputStream fin = new FileInputStream(file);
        int pos = fin.read(rawFile);
        if (pos != fileLength)
            throw new IOException(FILE_READ_EXCEPTION_MESSAGE);
        fin.close();
        return rawFile;
    }

    private boolean compile(String javaFile) throws IOException {
        System.out.println(COMPILING_CLASS_MESSAGE + javaFile + "...");
        Process p = Runtime.getRuntime().exec(JAVAC_STRING + javaFile);
        try {
            p.waitFor();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        int ret = p.exitValue();
        return ret == 0;
    }

    @Override
    public Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class loadingClass = null;
        loadingClass = findLoadedClass(name);
        // Create a pathname from the class name
        // E.g. java.lang.Object => java/lang/Object
        String fileStub = name.replace('.', '/');
        // Build objects pointing to the source code (.java) and object
        // code (.class)
        String javaFilename = fileStub + JAVA_EXTENSION;
        String classFilename = fileStub + CLASS_EXTENSION;

        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);

        // First, see if we want to try compiling.  We do if (a) there
        // is source code, and either (b0) there is no object code,
        // or (b1) there is object code, but it's older than the source
        if (javaFile.exists() &&
                (!classFile.exists() ||
                        javaFile.lastModified() > classFile.lastModified())) {

            try {
                if (!compile(javaFilename) || !classFile.exists()) {
                    throw new ClassNotFoundException(CLASS_NOT_FOUND_EXCEPTION_MESSAGE + javaFilename);
                }
            } catch (IOException ie) {
                throw new ClassNotFoundException(ie.toString());
            }
        }

        // Let's try to load up the raw bytes, assuming they were
        // properly compiled, or didn't need to be compiled
        try {
            // read the bytes
            byte raw[] = getBytes(classFilename);

            // try to turn them into a class
            loadingClass = defineClass(name, raw, 0, raw.length);
        } catch (IOException ie) {
            // This is not a failure!  If we reach here, it might
            // mean that we are dealing with a class in a library,
            // such as java.lang.Object
        }

        // Maybe the class is in a library -- try loading
        // the normal way
        if (loadingClass == null) {
            loadingClass = findSystemClass(name);
        }

        // Resolve the class, if any, but only if the "resolve"
        // flag is set to true
        if (resolve && loadingClass != null)
            resolveClass(loadingClass);

        // If we still don't have a class, it's an error
        if (loadingClass == null)
            throw new ClassNotFoundException(name);

        // Otherwise, return the class
        return loadingClass;
    }
}
