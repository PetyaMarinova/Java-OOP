package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.BaseTable;

import java.util.ArrayList;
import java.util.Collection;

public class TableRepositoryImpl<T extends BaseTable> implements TableRepository<T> {
    Collection<T> models;

    public TableRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public T getByNumber(int number) {
        return this.models.stream().filter(m -> m.getTableNumber() == number).findFirst().orElse(null);
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
