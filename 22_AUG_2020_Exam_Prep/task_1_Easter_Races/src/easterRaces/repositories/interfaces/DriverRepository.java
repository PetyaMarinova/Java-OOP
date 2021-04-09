package easterRaces.repositories.interfaces;

import easterRaces.entities.drivers.DriverImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DriverRepository<T extends DriverImpl> implements Repository<T> {
    private Collection<T> models;

    public DriverRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return this.models.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(T model) {
        this.models.add(model);
    }

    @Override
    public boolean remove(T model) {
        return this.models.remove(model);
    }
}
