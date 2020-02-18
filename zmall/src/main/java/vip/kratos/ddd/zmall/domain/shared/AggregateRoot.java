package vip.kratos.ddd.zmall.domain.shared;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class AggregateRoot<T extends BaseEntity<T>> extends BaseEntity<T> {

    private List<IDomainEvent> _events;

    protected final void raiseEvent(IDomainEvent event) {
        get_events().add(event);
    }

    final void clearEvents() {
        get_events().clear();
    }

    final List<IDomainEvent> get_events() {
        if (_events == null) {
            _events = newArrayList();
        }

        return _events;
    }
}
