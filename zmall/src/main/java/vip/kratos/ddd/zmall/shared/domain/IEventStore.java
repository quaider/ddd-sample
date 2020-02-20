package vip.kratos.ddd.zmall.shared.domain;

import java.util.List;

public interface IEventStore {

    void append(IDomainEvent... domainEvents);

    void markAsPublished(long eventId);

    void markAsPublishFailed(long eventId);

    List<IDomainEvent> latestBatchEvents(int batchSize);

    DomainEvent get(long eventId);
}
