package functionInterface;

import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void consumerBasicTest(){
        Consumer<Integer> integerConsumer = (Integer i) -> {
          System.out.println("consuming integer " + i);
        };
        integerConsumer.accept(5);

        Consumer<Integer> integerConsumer2 = i -> {
            System.out.println("consuming integer " + i);
        };
        integerConsumer2.accept(6);

        Consumer<Integer> integerConsumer3 = i -> System.out.println("consuming integer " + i);
        integerConsumer3.accept(7);
    }

    @Test
    public void consumerTestWithObject(){
        Student john = new Student();
        john.setName("John");
        john.setAge(17);
        john.setEngScore(99.5);
        john.setMathScore(11.5);
        Consumer<Student> studentConsumer = (s) -> {
          System.out.println("My name is " + s.getName());
          System.out.println("I am " + s.getAge() + " years old");
        };
        studentConsumer.accept(john);
    }

    @Test
    public void biconsumerTestWithObject(){
        Student john = new Student();
        john.setName("Johnathan");
        john.setAge(17);
        john.setEngScore(99.5);
        john.setMathScore(11.5);

        Student tom = new Student();
        tom.setName("Thomas");
        tom.setAge(17);
        tom.setEngScore(15.0);
        tom.setMathScore(99.8);

        BiConsumer<Student, Student> compareAgeConsumer = (Student a, Student b) -> {
            if(a.getAge() > b.getAge()) {
                System.out.println(a.getName() + " is older than " + b.getName());
            }else if(a.getAge() < b.getAge()) {
                System.out.println(b.getName() + " is older than " + a.getName());
            }else {
                System.out.println(a.getName() + " and " + b.getName() + "are the same age");
            }
        };

        compareAgeConsumer.accept(john, tom);
    }

}
class Student{
    private String name;
    private Integer age;
    private Double engScore;
    private Double mathScore;

    public void setName(String name){
        this.name = name;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public void setEngScore(Double engScore){
        this.engScore = engScore;
    }
    public void setMathScore(Double mathScore){
        this.mathScore = mathScore;
    }

    public String getName(){
        return this.name;
    }
    public Integer getAge(){
        return this.age;
    }
    public Double getMathScore(){
        return this.mathScore;
    }
    public Double getEngScore(){
        return this.engScore;
    }
}
