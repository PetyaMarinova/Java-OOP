package animals;

public class Tomcat extends Cat {

    final static String genderInfo = "Male";

    public Tomcat(String name, int age) {
        super(name, age, genderInfo);
    }

    @Override
    public String produceSound() {
        return String.format("MEOW");
    }
}
