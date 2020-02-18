package vip.kratos.ddd.zmall.infrastructure.repository.jpa.event;

import org.springframework.stereotype.Repository;
import vip.kratos.ddd.zmall.domain.shared.AggregateRoot;
import vip.kratos.ddd.zmall.domain.shared.BaseEventRepository;
import vip.kratos.ddd.zmall.domain.shared.IDomainEvent;
import vip.kratos.ddd.zmall.infrastructure.repository.jpa.event.jackson.DefaultObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DefaultEventRepository<T extends AggregateRoot<T>> extends BaseEventRepository<T> {

    private final DomainEventRepository domainEventRepository;
    private final DefaultObjectMapper objectMapper;

    public DefaultEventRepository(DomainEventRepository domainEventRepository, DefaultObjectMapper objectMapper) {
        this.domainEventRepository = domainEventRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void storeEvents(List<IDomainEvent> list) {
        List<DomainEventJpa> djs = list.stream().map(f -> {
            DomainEventJpa dj = new DomainEventJpa();
            dj.setPayload(objectMapper.writeValueAsString(f));
            return dj;
        }).collect(Collectors.toList());

        domainEventRepository.saveAll(djs);
    }
}