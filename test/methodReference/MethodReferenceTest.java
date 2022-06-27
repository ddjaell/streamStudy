package methodReference;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReferenceTest {
    @Test
    public void classStaticMethodTest(){
        Function<String,Integer> strToInteger = Integer::parseInt;

        Assert.assertEquals(Integer.valueOf(15), strToInteger.apply("15"));

    }

    @Test
    public void objectInstanceMethodTest() {
        String str1 = "hello";
        Predicate<String> checkStringEquals = str1::equals;
        Assert.assertTrue(checkStringEquals.test("hello"));
    }
}
