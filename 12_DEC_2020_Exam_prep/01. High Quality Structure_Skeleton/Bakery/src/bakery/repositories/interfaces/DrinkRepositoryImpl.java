package bakery.repositories.interfaces;

import bakery.entities.drinks.interfaces.BaseDrink;

import java.util.ArrayList;
import java.util.Collection;

public class DrinkRepositoryImpl<T extends BaseDrink> implements DrinkRepository<T> {
    Collection<T> models;

    public DrinkRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public T getByNameAndBrand(String drinkName, String drinkBrand) {
        return this.models.stream().filter(m -> m.getName().equals(drinkName)
        & m.getBrand().equals(drinkBrand)).findFirst().orElse(null);
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
