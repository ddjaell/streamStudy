package practicalFunctionInterface;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ScopeTest {
    @Test
    public void closureTest(){
        Supplier<String> helloWorldSupplier = getStringSupplier();
        Assert.assertEquals("HelloWorld", getStringSupplier().get());
    }

    @Test
    public void curryTest(){
        //Bifunction to Function using curry
        BiFunction<Integer, Integer, Integer> addNum = (x,y) -> x+y;
        Assert.assertEquals(Integer.valueOf(8), addNum.apply(5,3));

        Function<Integer, Function<Integer,Integer>> curriedAddNum = x -> y -> x+y;
        Function<Integer, Integer> addThree = curriedAddNum.apply(3);
        Assert.assertEquals(Integer.valueOf(8), addThree.apply(5));

    }
    public Supplier<String> getStringSupplier(){
        String hello = "Hello";
        Supplier<String> supplier = () -> {
            String world = "World";
            return hello+world;
        };
        return supplier;
    }
}
