package methodReference;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
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

    @Test
    public void instanceMethodTest(){
        Function<String, Integer> returnLength = String::length;
        Assert.assertEquals(Integer.valueOf(15), returnLength.apply("My name is John"));

        BiPredicate<String, String> isSameStr = String::equals;
        Assert.assertTrue(isSameStr.test("John", "John"));

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("John", 29, 80.5, 90.0));
        studentList.add(new Student("Bob", 30, 85.5, 92.4));
        studentList.add(new Student("Tim", 31, 84.5, 58.0));
        printStudentScores(studentList, Student::getEngScore);
        printStudentScores(studentList, Student::getMathScore);
    }

    @Test
    public void constructorReferenceTest() {
        BiFunction<String, Integer, Student> studentCreator = Student::new;

        Student student = studentCreator.apply("John", 31);

        Assert.assertEquals("John", student.getName());
        Assert.assertEquals(Integer.valueOf(31), student.getAge());

    }

    public void printStudentScores(List<Student> studentList,Function<Student, Object> getter){
        for(Student student : studentList) {
            System.out.println(getter.apply(student));
        }
    }
}
class Student{
    private String name;
    private Integer age;
    private Double engScore;
    private Double mathScore;

    public Student(String name, Integer age, Double engScore, Double mathScore) {
        this.name = name;
        this.age = age;
        this.engScore = engScore;
        this.mathScore = mathScore;
    }
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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
