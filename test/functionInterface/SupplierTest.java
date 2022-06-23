package functionInterface;

import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class SupplierTest {

    @Test
    public void supplierTest(){
        Supplier<String> stringSupplier = () -> {
            return "String";
        };
        assertEquals("String", stringSupplier.get());

        Supplier<String> stringSupplier2 = () -> "String";
        assertEquals("String", stringSupplier2.get());
    }

    @Test
    public void doubleSupplierTest(){
        Supplier<Double> randomSupplier = () -> Math.random();
        printRandomDouble(randomSupplier, 5);
    }

    protected void printRandomDouble(Supplier<Double> doubleSupplier, int count){
        for (int i = 0; i<count; i++){
            System.out.println(doubleSupplier.get());
        }
    }
}
