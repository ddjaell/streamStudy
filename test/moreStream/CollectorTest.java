package moreStream;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorTest {
    @Test
    public void collectorTest() {
        Stream<Integer> integerStream = Stream.of(1,4,6,-4,9,6,1);
        List<Integer> integerList = integerStream
                .collect(Collectors.toList());
        System.out.println(integerList);

        Stream<Integer> integerStream2 = Stream.of(1,4,6,-4,9,6,1);
        Set<Integer> integerSet = integerStream2
                .collect(Collectors.toSet());
        System.out.println(integerSet);

        Stream<Integer> integerStream3 = Stream.of(1,4,6,-4,9,6,1);
        List<Integer> plusOneIntegerList = integerStream3
                .collect(Collectors.mapping(x -> x+1, Collectors.toList()));
        System.out.println(plusOneIntegerList);

        Stream<Integer> integerStream4 = Stream.of(1,4,6,-4,9,6,1);
        int sum = integerStream4
                .collect(Collectors.reducing(0, (x, y) -> x+y));
        Assert.assertEquals(23, sum);



    }

}
