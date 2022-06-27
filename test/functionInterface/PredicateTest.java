package functionInterface;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {

    @Test
    public void predicateTest(){
        Predicate<Integer> checkInteger = (Integer x) -> {
            return x > 0;
        };
        Assert.assertTrue(checkInteger.test(5));

        Predicate<Integer> checkInteger2 = x -> {
            return x > 0;
        };
        Assert.assertTrue(checkInteger2.test(5));

        Predicate<Integer> checkInteger3 = x -> x > 0;
        Assert.assertTrue(checkInteger3.test(5));
    }

    @Test
    public void predicateTestWithList(){
        Predicate<Integer> checkPositive = x -> x > 0;

        List<Integer> inputs = Arrays.asList(1, 5, -1, -3, 99);
        Assert.assertNotNull(filter(inputs, checkPositive));
        Assert.assertNotNull(filter(inputs, checkPositive.negate()));

    }

    public <T> List<T> filter(List<T> inputs,Predicate<T> condition){
        List<T> result = new ArrayList<>();
        for(T input : inputs){
            if(condition.test(input)) {
                result.add(input);
            }
        }
        return result;
    }
}
