package animals;

public class Kitten extends Cat {
    final static String genderInfo = "Female";

    public Kitten(String name, int age) {
        super(name, age, genderInfo);
    }

    @Override
    public String produceSound() {
        return String.format("Meow");
    }
}
