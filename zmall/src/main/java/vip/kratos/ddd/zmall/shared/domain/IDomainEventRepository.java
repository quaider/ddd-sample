package vip.kratos.ddd.zmall.shared.domain;

import java.util.List;

public interface IDomainEventRepository {
    void save(List<IDomainEvent> events);

    void markAsPublished(String eventId);

    void markAsPublishFailed(String eventId);

    DomainEvent get(String eventId);
}
