import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        System.out.println("\n1. ");
        System.out.println(getAllLanguages(Streams1.students));

        System.out.println("\n2. ");
        getStudentsByLanguage(Streams1.students).forEach( (key, value) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(key).append(": ");
            for (Streams1.Student st: value) {
                stringBuilder.append(st.name()).append(", ");
            }
            int lastI = stringBuilder.lastIndexOf(",");
            stringBuilder.setCharAt(lastI, ';');
            System.out.println(stringBuilder);
        });

        System.out.println("\n3. ");

    }

    public static List<String> getAllLanguages(List<Streams1.Student> students) {
        // Given list of students with languages, return list of unique languages.
        Set<String> langs = new HashSet<>();
        students.forEach(l -> langs.addAll(l.languages()));
        return langs.stream().toList();
    }

    // Given list of students group them by language.
    public static Map<String, List<Streams1.Student>> getStudentsByLanguage(List<Streams1.Student> students) {
        return students.stream()
                .flatMap(student ->
                    student.languages().stream().map(line -> Map.entry(line, student)))
                .collect(Collectors.groupingBy(
                        Entry::getKey,
                        Collectors.mapping(Entry::getValue, Collectors.toList())
                ));
    }


}