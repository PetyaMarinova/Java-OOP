package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        int sumAllComfortByDecorations = this.decorations.stream().mapToInt(Decoration::getComfort).sum();
        return sumAllComfortByDecorations;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.capacity > this.fish.size() ){
            this.fish.add(fish);
        } else {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.stream().forEach(f -> f.eat());
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):",this.getName(),this.getClass().getSimpleName()))
                .append(System.lineSeparator());
        if (this.fish.isEmpty()){
            sb.append("Fish: none").append(System.lineSeparator());
        } else {
            sb.append("Fish: ");
            List<String> namesFish = this.fish.stream().map(Fish::getName).collect(Collectors.toList());
            sb.append(String.join(" ",  namesFish));
            sb.append(System.lineSeparator());
        }
        sb.append(String.format("Decorations: %d",this.getDecorations().size()))
                .append(System.lineSeparator())
                .append(String.format("Comfort: %d",this.calculateComfort()))
                .append(System.lineSeparator());

        return sb.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }

}
