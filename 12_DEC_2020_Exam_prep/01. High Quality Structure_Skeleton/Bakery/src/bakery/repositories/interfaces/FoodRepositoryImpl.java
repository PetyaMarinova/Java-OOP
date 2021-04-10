package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BaseFood;

import java.util.ArrayList;
import java.util.Collection;

public class FoodRepositoryImpl<T extends BaseFood> implements FoodRepository<T> {
    Collection<T> models;

    public FoodRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return this.models.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<T> getAll() {
        return this.models;
    }

    @Override
    public void add(T t) {
        this.models.add(t);
    }
}
