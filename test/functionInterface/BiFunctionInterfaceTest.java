package functionInterface;

import org.junit.Test;

import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

public class BiFunctionInterfaceTest {

    @Test
    public void bifunctionWithLambdaExpression(){
        BiFunction<Integer, Integer, Integer> myAdder = (Integer x, Integer y) -> {
            return x+y;
        };
        assertEquals(Integer.valueOf(15), myAdder.apply(5,10));

        BiFunction<Integer, Integer, Integer> myAdder2 = (x, y) -> {
            return x+y;
        };
        assertEquals(Integer.valueOf(15), myAdder2.apply(5,10));

        BiFunction<Integer, Integer, Integer> myAdder3 = (x, y) -> x+y;
        assertEquals(Integer.valueOf(15), myAdder3.apply(5,10));
    }

}
