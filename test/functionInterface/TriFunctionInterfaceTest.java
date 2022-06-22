package functionInterface;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriFunctionInterfaceTest {
    @Test
    public void triFunctionInterfaceWithLambdaExpression(){
        TriFunction<Integer,Integer,Integer,Integer> myAdder = (Integer x, Integer y, Integer z) -> {
            return x+y+z;
        };
        assertEquals(Integer.valueOf(10), myAdder.apply(2,3,5));

        TriFunction<Integer,Integer,Integer,Integer> myAdder2 = (x, y, z) -> {
            return x+y+z;
        };
        assertEquals(Integer.valueOf(10), myAdder2.apply(2,3,5));

        TriFunction<Integer,Integer,Integer,Integer> myAdder3 = (x, y, z) -> x+y+z;
        assertEquals(Integer.valueOf(10), myAdder3.apply(2,3,5));
    }
}
