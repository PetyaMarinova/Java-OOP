package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Field[] declaredFields = RichSoilLand.class.getDeclaredFields();

        String input = scanner.nextLine();
        while (!input.equals("HARVEST")) {
            String modifier = input;
            Field[] fields = Arrays.stream(declaredFields).filter(f -> Modifier.toString(f.getModifiers()).equals(modifier))
                    .toArray(Field[]::new);
            if (fields.length == 0){
                Arrays.stream(declaredFields)
                        .forEach(f -> System.out.printf("%s %s %s%n",
                                Modifier.toString(f.getModifiers()),
                                f.getType().getSimpleName(),
                                f.getName()));
            } else {
                Arrays.stream(fields)
                        .forEach(f -> System.out.printf("%s %s %s%n",
                                Modifier.toString(f.getModifiers()),
                                f.getType().getSimpleName(),
                                f.getName()));
            }
            input = scanner.nextLine();
        }
    }
}
