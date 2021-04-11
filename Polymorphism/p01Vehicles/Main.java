package p01Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] carInfo = scanner.nextLine().split("\\s+");
        String[] truckInfo = scanner.nextLine().split("\\s+");

        Car car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n ; i++) {
            String[] command = scanner.nextLine().split("\\s+");
            switch (command[0]){
                case "Drive":
                    if (command[1].equals("Car")){
                        System.out.println(car.driving(Double.parseDouble(command[2])));
                    } else {
                        System.out.println(truck.driving(Double.parseDouble(command[2])));
                    }
                    break;
                case "Refuel":
                    if (command[1].equals("Car")){
                        car.refueling(Double.parseDouble(command[2]));
                    } else {
                        truck.refueling(Double.parseDouble(command[2]));
                    }
                    break;
            }
        }

        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n", truck.getFuelQuantity());
    }
}
