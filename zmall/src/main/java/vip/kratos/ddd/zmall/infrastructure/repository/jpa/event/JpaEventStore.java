package vip.kratos.ddd.zmall.infrastructure.repository.jpa.event;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import vip.kratos.ddd.zmall.shared.domain.DomainEvent;
import vip.kratos.ddd.zmall.shared.domain.IDomainEvent;
import vip.kratos.ddd.zmall.shared.domain.IEventStore;
import vip.kratos.ddd.zmall.shared.domain.StoredEvent;
import vip.kratos.ddd.zmall.shared.jackson.DefaultObjectMapper;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaEventStore extends SimpleJpaRepository<StoredEvent, Long> implements IEventStore {

    private final DefaultObjectMapper objectMapper;

    public JpaEventStore(EntityManager em, DefaultObjectMapper objectMapper) {
        super(StoredEvent.class, em);
        this.objectMapper = objectMapper;
    }

    @Override
    public void append(IDomainEvent... events) {
        List<StoredEvent> storedEvents = Arrays.stream(events)
                .map(f -> new StoredEvent(f.eventId(), f.getClass().getName(), f.occurredOn(), objectMapper.writeValueAsString(f)))
                .collect(Collectors.toList());

        super.saveAll(storedEvents);
    }

    @Override
    public void markAsPublished(long eventId) {
        this.findById(eventId).ifPresent(f -> {
            f.markAsPublished();
            super.save(f);
        });
    }

    @Override
    public void markAsPublishFailed(long eventId) {
        this.findById(eventId).ifPresent(f -> {
            f.markAsPublishFailed();
            super.save(f);
        });
    }

    @Override
    public List<IDomainEvent> latestBatchEvents(int batchSize) {
        Pageable pageable = PageRequest.of(1, batchSize, Sort.by("occurredOn"));

        return findAll(pageable)
                .map(f -> objectMapper.readValue(f.getEventBody(), IDomainEvent.class))
                .getContent();
    }

    @Override
    public DomainEvent get(long eventId) {
        return null;
    }
}
