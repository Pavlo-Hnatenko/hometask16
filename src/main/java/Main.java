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

        csvTable = getCsvTable();
        personalities = getPersonalitiesFromCsv();

        System.out.println(PersonalitiesPropertiesToString(personalities));

    }

    private static CsvTable getCsvTable() {

        try {
            csvTable = CsvTable.fromFile(Paths.get("origin.csv")).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvTable;
    }

    private static List<Personality> getPersonalitiesFromCsv() {

        personalities = new ArrayList<>();

        for (int i = 0; i < csvTable.height(); i++) {

            row = csvTable.getRow(i);

            personalities.add(new Personality(
                    getPersonalityProperty("name"),
                    Integer.parseInt(getPersonalityProperty("age")),
                    getPersonalityProperty("sex"),
                    getPersonalityProperty("occupation")
            ));
        }

        return personalities;
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

    private static String PersonalitiesPropertiesToString(List<Personality> personalities) {

        StringBuilder result = new StringBuilder();

        for (Personality personality : personalities) {

            result.append("personality.getName() = ").append(personality.getName()).append(System.lineSeparator())
                    .append("personality.getAge() = ").append(personality.getAge()).append(System.lineSeparator())
                    .append("personality.getSex() = ").append(personality.getSex()).append(System.lineSeparator())
                    .append("personality.getOccupation() = ").append(personality.getOccupation());
        }

        return result.toString();
    }

    //    a potentially simple way to create personalities from CSV file (without reflection)
    private static void getPersonalitiesWithoutReflection() {

        for (int i = 0; i < csvTable.height(); i++) {

            row = csvTable.getRow(i);

            personalities.add(new Personality(row.get(0), Integer.parseInt(row.get(1)), row.get(2), row.get(3)));
        }
    }
}
