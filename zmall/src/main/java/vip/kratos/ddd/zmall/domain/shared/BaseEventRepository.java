package vip.kratos.ddd.zmall.domain.shared;

import org.springframework.util.Assert;

import java.util.List;

public abstract class BaseEventRepository<T extends AggregateRoot<T>> {

    public void store(T aggregateRoot) {
        Assert.notNull(aggregateRoot, "Entity must not be null!");
        storeEvents(aggregateRoot.get_events());
        aggregateRoot.clearEvents();
    }

    protected abstract void storeEvents(List<IDomainEvent> events);

}
