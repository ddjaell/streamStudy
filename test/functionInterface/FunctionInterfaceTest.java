package functionInterface;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class FunctionInterfaceTest {
    @Test
    public void adderTest(){
        Function<Integer, Integer> myAdder = new Adder();
        assertEquals(Integer.valueOf(15), myAdder.apply(5));
    }

    @Test
    public void adderWithLambdaExpression(){
        Function<Integer, Integer> myAdder = (Integer x) -> {
            return x+10;
        };
        assertEquals(Integer.valueOf(15), myAdder.apply(5));

        Function<Integer, Integer> myAdder2 = (x) -> {
            return x+10;
        };
        assertEquals(Integer.valueOf(15), myAdder2.apply(5));

        Function<Integer, Integer> myAdder3 = x -> {
            return x+10;
        };
        assertEquals(Integer.valueOf(15), myAdder3.apply(5));

        Function<Integer, Integer> myAdder4 = x -> x+10;
        assertEquals(Integer.valueOf(15), myAdder4.apply(5));
    }
}
