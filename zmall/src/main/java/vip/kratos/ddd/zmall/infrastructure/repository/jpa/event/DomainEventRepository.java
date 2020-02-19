package vip.kratos.ddd.zmall.infrastructure.repository.jpa.event;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import vip.kratos.ddd.zmall.shared.jackson.DefaultObjectMapper;
import vip.kratos.ddd.zmall.shared.domain.DomainEvent;
import vip.kratos.ddd.zmall.shared.domain.IDomainEvent;
import vip.kratos.ddd.zmall.shared.domain.IDomainEventRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DomainEventRepository extends SimpleJpaRepository<DomainEventJpa, Long> implements IDomainEventRepository {

    private final DefaultObjectMapper objectMapper;

    public DomainEventRepository(EntityManager em, DefaultObjectMapper objectMapper) {
        super(DomainEventJpa.class, em);
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(List<IDomainEvent> events) {
        List<DomainEventJpa> djs = events.stream().map(f -> {
            DomainEventJpa dj = new DomainEventJpa();
            dj.setPayload(objectMapper.writeValueAsString(f));
            return dj;
        }).collect(Collectors.toList());

        super.saveAll(djs);
    }

    @Override
    public void markAsPublished(String eventId) {

    }

    @Override
    public void markAsPublishFailed(String eventId) {

    }

    @Override
    public DomainEvent get(String eventId) {
        return null;
    }
}
