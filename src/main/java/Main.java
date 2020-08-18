import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Personality> personalities;
    static CsvTable csvTable;
    static List<String> row;

    public static void main(String[] args) {

        personalities = new ArrayList<>();

        try {
            csvTable = CsvTable.fromFile(Paths.get("origin.csv")).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < csvTable.height(); i++) {

            row = csvTable.getRow(i);

            personalities.add(new Personality(
                    getPersonalityProperty("name"),
                    Integer.parseInt(getPersonalityProperty("age")),
                    getPersonalityProperty("sex"),
                    getPersonalityProperty("occupation")
            ));
        }

        for (Personality personality : personalities) {
            System.out.println("personality.getName() = " + personality.getName());
            System.out.println("personality.getAge() = " + personality.getAge());
            System.out.println("personality.getSex() = " + personality.getSex());
            System.out.println("personality.getOccupation() = " + personality.getOccupation());
        }
    }

    private static String getPersonalityProperty(String personalityField) {

        try {

            Field personalityProperty = Personality.class.getDeclaredField(personalityField);

            personalityProperty.setAccessible(true);

            PersonalityProperty annotation = personalityProperty.getAnnotation(PersonalityProperty.class);

            switch (annotation.value()) {
                case "personality name":
                    return row.get(0);
                case "personality age":
                    return row.get(1);
                case "personality sex":
                    return row.get(2);
                case "personality occupation":
                    return row.get(3);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }
}
