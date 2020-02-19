package vip.kratos.ddd.zmall.shared.domain;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class AggregateRoot<T extends BaseEntity<T>> extends BaseEntity<T> {

    private List<IDomainEvent> _events;

    protected final void raiseEvent(IDomainEvent event) {
        get_events().add(event);
    }

    public final void clearEvents() {
        get_events().clear();
    }

    public final List<IDomainEvent> get_events() {
        if (_events == null) {
            _events = newArrayList();
        }

        return _events;
    }
}
