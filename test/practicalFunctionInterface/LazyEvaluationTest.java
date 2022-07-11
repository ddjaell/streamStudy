package practicalFunctionInterface;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluationTest {
    @Test
    public void lazyEvaluationTest(){
        if(returnTrue() || returnFalse()){
            System.out.println("returned true");
        } //only returnTrue method executed;
        if(or(returnTrue(), returnFalse())){
            System.out.println("returned true2");
        } //both returnTrue, returnFalse methods executed;
        if(lazyOr(() -> returnTrue(), () -> returnFalse())){
            System.out.println("returned true3");
        } // only returnTrue supplier returned result

        Stream<Integer> integerStream = Stream.of(1,2,-4,5,6,-7,9,-10)
                .filter(x -> x>0)
                .peek(x -> System.out.println("this num is " + x));

        System.out.println("before collect to list"); //thread executes this println until integerStream meets .collect(Collectors.toList())

        List<Integer> integerList = integerStream.collect(Collectors.toList());

        System.out.println(integerList);





    }
    public boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y){
        return x.get() || y.get();
    }
    public boolean or(boolean x, boolean y){
        return x || y;
    }
    public boolean returnTrue(){
        System.out.println("true");
        return true;
    }
    public boolean returnFalse(){
        System.out.println("false");
        return false;
    }
}
