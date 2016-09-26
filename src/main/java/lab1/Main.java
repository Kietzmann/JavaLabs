package lab1;

/**
 * Created by Dmytro on 25.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        CustomClassLoader classLoader = new CustomClassLoader();
        classLoader.invokeClassMethod("lab1.TestModule", "toString");
    }

}
