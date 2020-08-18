public class Personality {

    @PersonalityProperty("personality name")
    private String name;

    @PersonalityProperty("personality age")
    private int age;

    @PersonalityProperty("personality sex")
    private String sex;

    @PersonalityProperty("personality occupation")
    private String occupation;

    public Personality(String name, int age, String sex, String occupation) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
