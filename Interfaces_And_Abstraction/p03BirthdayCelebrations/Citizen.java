package p03BirthdayCelebrations;

public class Citizen implements Person, Birthable, Identifiable {
    private String name;
    private int age;
    private String id;
    private String birthDate;

    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birthDate;
    }

    public Citizen(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Citizen(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s %d %s %s",getName(), getAge(), getId(),getBirthDate());
    }
}
