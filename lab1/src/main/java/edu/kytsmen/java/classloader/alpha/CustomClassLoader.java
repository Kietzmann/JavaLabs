package edu.kytsmen.java.classloader.alpha;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Dmytro on 25.09.2016.
 */
public class CustomClassLoader extends ClassLoader {
    public void invokeClassMethod(String classBinaryName, String methodName) {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Class loadedClass = classLoader.loadClass(classBinaryName);
            System.out.println("Loaded class name: " + loadedClass.getName());

            Constructor constructor = loadedClass.getConstructor();
            Object myClassObject = constructor.newInstance();

            Method method = loadedClass.getMethod(methodName);
            System.out.println("Invoked method name: " + method.getName());
            method.invoke(myClassObject);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] classDefinition;

    public CustomClassLoader(byte[] classDefinition) {
        super(getSystemClassLoader());
        this.classDefinition = classDefinition;
    }


    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        if (!className.startsWith(IOUtils.PACKAGE)) {
            return super.loadClass(className);
        }

        Class definedClass = defineClass(className, classDefinition, 0, classDefinition.length);

        if (definedClass == null) {
            throw new ClassNotFoundException("Defined class was not found.");
        }
        return definedClass;
    }

    public Class<?> getLoadedClass(String className) {
        return findLoadedClass(className);
    }
}
