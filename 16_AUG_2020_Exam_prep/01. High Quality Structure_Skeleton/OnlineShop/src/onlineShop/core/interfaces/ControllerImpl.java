package onlineShop.core.interfaces;

import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Map<Integer, Computer> computers;
    private Map<Integer, Component> components;
    private Map<Integer, Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new LinkedHashMap<>();
        this.components = new LinkedHashMap<>();
        this.peripherals = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer;
        if (computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        if (this.computers.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        this.computers.put(id, computer);
        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
       Peripheral peripheral;
       switch (peripheralType){
           case "Headset":
               peripheral = new Headset(id,manufacturer,model,price,overallPerformance,connectionType);
               break;
           case "Keyboard":
               peripheral = new Keyboard(id,manufacturer,model,price,overallPerformance,connectionType);
               break;
           case "Monitor":
               peripheral = new Monitor(id,manufacturer,model,price,overallPerformance,connectionType);
               break;
           case "Mouse":
               peripheral = new Mouse(id,manufacturer,model,price,overallPerformance,connectionType);
               break;
           default:
               throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
       }
        if (!this.computers.containsKey(computerId)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        if (this.peripherals.containsKey(id)){
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }
        this.computers.get(computerId).addPeripheral(peripheral);
        this.peripherals.put(id,peripheral);
        return String.format(ADDED_PERIPHERAL,peripheralType,id,computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (!this.computers.containsKey(computerId)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Peripheral peripheral = this.computers.get(computerId).removePeripheral(peripheralType);
        int id = peripheral.getId();
        this.peripherals.remove(id);
        return String.format(REMOVED_PERIPHERAL,peripheralType, id);
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Component component;
        switch (componentType){
            case "Motherboard":
                component = new Motherboard(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }
        if (!this.computers.containsKey(computerId)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        if (this.components.containsKey(id)){
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }
        this.computers.get(computerId).addComponent(component);
        this.components.put(id,component);
        return String.format(ADDED_COMPONENT,componentType,id,computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        if (!this.computers.containsKey(computerId)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Component component = this.computers.get(computerId).removeComponent(componentType);
        int id = component.getId();
        this.components.remove(id);
        return String.format(REMOVED_COMPONENT,componentType, id);
    }

    @Override
    public String buyComputer(int id) {
        if (!this.computers.containsKey(id)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer boughtComputer = this.computers.get(id);
        this.computers.remove(id);
        return boughtComputer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> filteredComputers = computers.values()
                .stream()
                .filter(c -> c.getPrice() <= budget)
                .sorted(Comparator.comparing(Computer::getOverallPerformance).reversed())
                .collect(Collectors.toList());

        if (filteredComputers.isEmpty()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }

        Computer computer = filteredComputers.get(0);

        computers.remove(computer.getId());

        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        if (!this.computers.containsKey(id)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = this.computers.get(id);
        return computer.toString();
    }
}
