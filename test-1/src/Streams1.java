import java.util.Arrays;
import java.util.List;

public class Streams1 {

    record Student(String name, List<String> languages) {

    }

    // Data example
    static final List<Student> students = Arrays.asList(
            new Student("Doug Lea", Arrays.asList("Java", "C#", "JavaScript")),
            new Student("Bjarne Stroustrup", Arrays.asList("C", "C++", "Java")),
            new Student("Martin Odersky", Arrays.asList("Java", "Scala", "JavaScript"))
    );
}
