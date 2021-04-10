package TrafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] states = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(scanner.nextLine());

        List<TrafficLightsState> allTrafficLights = new ArrayList();
        for (String state : states) {
            TrafficLightsState current = new TrafficLightsState(TrafficLights.valueOf(state));
            allTrafficLights.add(current);
        }

        for (int i = 0; i < n; i++) {
            for (TrafficLightsState allTrafficLight : allTrafficLights) {
                allTrafficLight.update();
                System.out.print(allTrafficLight.toString() + " ");
            }
            System.out.println();
        }
    }
}
