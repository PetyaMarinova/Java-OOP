package viceCity.repositories.interfaces;

import viceCity.models.guns.BaseGun;
import viceCity.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GunRepository<T extends BaseGun> implements Repository<T> {
    private Collection<T> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<T> getModels() {
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

    @Override
    public T find(String name) {
        return this.models.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
    }
}
