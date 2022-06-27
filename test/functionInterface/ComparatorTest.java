package functionInterface;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ComparatorTest {

    @Test
    public void testComparator(){
        KoreanStudent student1 = new KoreanStudent("chulsoo", 30, 50.0);
        KoreanStudent student2 = new KoreanStudent("minsoo", 22, 55.0);
        KoreanStudent student3 = new KoreanStudent("gwangsoo", 24, 60.0);
        List<KoreanStudent> inputs = Arrays.asList(student1, student2, student3);


        Comparator<KoreanStudent> ageComparator = (o1, o2) -> o1.getAge() - o2.getAge();
        Collections.sort(inputs, ageComparator);
        Assert.assertEquals("minsoo", inputs.get(0).getName());
        Collections.sort(inputs, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Assert.assertEquals("chulsoo", inputs.get(0).getName());
    }

}

class KoreanStudent{
    private String name;
    private Integer age;
    private Double krScore;

    public KoreanStudent(String name, Integer age, Double krScore) {
        this.name = name;
        this.age = age;
        this.krScore = krScore;
    }
    public String getName(){
        return this.name;
    }
    public Integer getAge(){ return this.age; }
    public Double getKrScore(){ return this.krScore; }

}

