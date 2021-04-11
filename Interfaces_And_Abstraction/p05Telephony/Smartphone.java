package p05Telephony;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {

    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder output = new StringBuilder();
        for (String url : urls) {
            String regex = "\\d+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()){
                output.append(String.format("Invalid URL!")).append(System.lineSeparator());
            } else {
                output.append(String.format("Browsing: %s!",url)).append(System.lineSeparator());
            }
        }
        return output.toString().trim();
    }

    @Override
    public String call() {
        StringBuilder output = new StringBuilder();
        for (String number : numbers) {
            String regex = "^\\d+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                output.append(String.format("Calling... %s%n",number));
            } else {
                output.append(String.format("Invalid number!%n"));
            }
        }
        return output.toString().trim();
    }
}
