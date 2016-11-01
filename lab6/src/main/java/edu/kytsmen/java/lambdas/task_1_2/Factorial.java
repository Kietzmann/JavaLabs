package edu.kytsmen.java.lambdas.task_1_2;

import java.util.function.LongUnaryOperator;
import java.util.stream.LongStream;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class Factorial {

    static final LongUnaryOperator factorial = x -> x == 0
            ? 1
            : x * Factorial.factorial.applyAsLong(x - 1);

    public static long factorial(long i) {
        // implement function body in order all test to complete successfully

        //return factorial.apply(i);


        return LongStream.iterate(1, e -> e + 1).limit(i).reduce(1, (x, y) -> x * y);
    }
}
