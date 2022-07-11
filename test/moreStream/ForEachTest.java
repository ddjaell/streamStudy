package moreStream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ForEachTest {
    @Test
    public void forEachTest() {
        List<Integer> integerList = Arrays.asList(1,2,3,4,5);
        integerList.stream().forEach(num -> System.out.println( num + " + 1 = " + (num+1)));
        //List,Set also has forEach method
        integerList.forEach(num -> System.out.println( num + " + 1 = " + (num+1)));
    }
}
