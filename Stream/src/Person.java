import java.util.List;

public class Person implements Comparable<Person> {

    private String name;
    private int age;
    private Gender gender;
    private List<String> programmingLanguages;

    public Person(String name, int age, Gender gender, List<String> programmingLanguages) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.programmingLanguages = programmingLanguages;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        return Integer.compare(this.getAge(), person.getAge());
    }
}
