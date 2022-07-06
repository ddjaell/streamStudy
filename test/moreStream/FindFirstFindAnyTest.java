package moreStream;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindFirstFindAnyTest {
    @Test
    public void findAnyTest(){
        Stream<Integer> intStream = Stream.of(1,2,-7,5,3,-4,-3,-5);
        List<Integer> intList = intStream.collect(Collectors.toList());
        Optional<Integer> foundNum = intList.stream()
                .filter(x -> x<0)
                .findAny();

        Optional<Integer> foundNum2 = intList.parallelStream()
                .filter(x -> x<0)
                .findAny();

        foundNum.ifPresent(x -> System.out.println(x));
        foundNum2.ifPresent(x -> System.out.println(x));
    }

    @Test
    public void findFirstTest(){
        List<Integer> intList = Arrays.asList(1,-4,-8,2,5,6,7,-2);
        Optional<Integer> foundNum = intList.stream()
                .filter(x -> x < 0)
                .findFirst();

        Assert.assertEquals(Integer.valueOf(-4), foundNum.get());

    }
}
