package p05Telephony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] callNumbers = scanner.nextLine().split("\\s+");

        List<String> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(callNumbers));

        String[] allUrls = scanner.nextLine().split("\\s+");
        List<String> urls = new ArrayList<>();
        urls.addAll(Arrays.asList(allUrls));

        Smartphone smartphone = new Smartphone(numbers,urls);
        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());
    }
}
