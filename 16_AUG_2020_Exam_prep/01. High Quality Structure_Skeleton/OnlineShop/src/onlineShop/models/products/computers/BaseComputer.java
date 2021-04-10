package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        double average = this.components.stream()
                .mapToDouble(Component::getOverallPerformance)
                .average().orElse(0);

        return average + super.getOverallPerformance();
    }

    @Override
    public double getPrice() {
        double sumComponentsPrice = this.components.stream().mapToDouble(Component::getPrice).sum();
        double sumPeripheralPrice = this.peripherals.stream().mapToDouble(Peripheral::getPrice).sum();

        return super.getPrice() + sumComponentsPrice + sumPeripheralPrice;
    }

    @Override
    public void addComponent(Component component) {
        if (this.components.stream().anyMatch(c -> c.getClass().getSimpleName().equals(component.getClass().getSimpleName()))) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, component.getClass().getSimpleName(),
                    this.getClass().getSimpleName(), this.getId()));
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        boolean isNotInTheListOfComponents = true;
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                isNotInTheListOfComponents = false;
            }
        }
        if (this.components.isEmpty() || isNotInTheListOfComponents) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        int index = 0;
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (component.getClass().getSimpleName().equals(componentType)) {
                index = i;
                break;
            }
        }
        return components.remove(index);
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral per : peripherals) {
            if (per.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, per.getClass().getSimpleName(),
                        this.getClass().getSimpleName(), this.getId()));
            }
        }
        this.peripherals.add(peripheral);

    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        boolean isNotInTheListOfPeripherals = true;
        for (Peripheral per : peripherals) {
            if (per.getClass().getSimpleName().equals(peripheralType)) {
                isNotInTheListOfPeripherals = false;
            }
        }
        if (this.components.isEmpty() || isNotInTheListOfPeripherals) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        int index = 0;
        for (int i = 0; i < this.peripherals.size(); i++) {
            Peripheral peripheral = this.peripherals.get(i);
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                index = i;
                break;
            }
        }
        return this.peripherals.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(System.lineSeparator());
        builder.append(String.format(COMPUTER_COMPONENTS_TO_STRING, components.size()));

        builder.append(System.lineSeparator());

        for (Component component : components) {
            builder.append("  ")
                    .append(component.toString())
                    .append(System.lineSeparator());
        }

        builder.append(String.format(COMPUTER_PERIPHERALS_TO_STRING,
                peripherals.size(),
                peripherals
                        .stream()
                        .mapToDouble(Peripheral::getOverallPerformance)
                        .average().orElse(0)));

        builder.append(System.lineSeparator());

        for (Peripheral peripheral : peripherals) {
            builder.append(" ")
                    .append(peripheral.toString())
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
