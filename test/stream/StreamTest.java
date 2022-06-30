package stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void streamTest() {
        Stream<String> stringStream = Stream.of("Bob", "Karl", "Alice");
        List<String> stringList = stringStream.collect(Collectors.toList());
        Assert.assertEquals("Karl", stringList.get(1));

        String[] stringArr = {"Bob", "Karl", "Alice"};
        stringStream = Arrays.stream(stringArr);
        stringList = stringStream.collect(Collectors.toList());
        Assert.assertEquals("Bob", stringList.get(0));

        Set<Integer> integerSet = new HashSet<>(Arrays.asList(1,5,6));
        Stream<Integer> integerStream = integerSet.stream();
        List<Integer> integerList = integerStream.collect(Collectors.toList());
        Assert.assertEquals(Integer.valueOf(1), integerList.get(0));


    }
}
