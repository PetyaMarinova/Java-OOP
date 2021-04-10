package p03ShoppingSpree;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new HashMap<>();

        try {
            Arrays.stream(scanner.nextLine().split(";"))
                    .forEach(p -> {
                        String[] tokens = p.split("=");
                        Person person = new Person(tokens[0], Double.parseDouble(tokens[1]));
                        people.putIfAbsent(tokens[0], person);
                    });


            Arrays.stream(scanner.nextLine().split(";"))
                    .forEach(p -> {
                        String[] tokens = p.split("=");
                        Product product = new Product(tokens[0], Double.parseDouble(tokens[1]));
                        products.putIfAbsent(tokens[0], product);
                    });
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            return;
        }

        String purchases = scanner.nextLine();

            while (!purchases.equals("END")) {
                String[] currentPurchase = purchases.split("\\s+");

                String namePerson = currentPurchase[0];
                String nameProduct = currentPurchase[1];

                Person person = people.get(namePerson);
                Product product = products.get(nameProduct);

                if (person == null || product == null) {
                    continue;
                }

                try {
                    person.buyProduct(product);
                    System.out.println(String.format("%s bought %s", person.getName(),
                            product.getName()));

                } catch (IllegalArgumentException iae){
                    System.out.println(iae.getMessage());
                }

                purchases = scanner.nextLine();
            }

            people.values().forEach(p -> {
                StringBuilder result = new StringBuilder();
                if (p.getProducts().size() == 0){
                    result.append(String.format("%s - Nothing bought", p.getName()));
                } else {
                    result.append(String.format("%s - %s", p.getName(),
                            p.getProducts().stream().map(Product::getName)
                    .collect(Collectors.joining(", "))));
                }
                System.out.println(result.toString());
            });

    }
}
