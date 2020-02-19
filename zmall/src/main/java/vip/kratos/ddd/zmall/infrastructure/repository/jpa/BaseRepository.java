package vip.kratos.ddd.zmall.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import vip.kratos.ddd.zmall.shared.domain.AggregateRoot;
import vip.kratos.ddd.zmall.shared.domain.IDomainEventRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

public abstract class BaseRepository<T extends AggregateRoot<T>> extends SimpleJpaRepository<T, Long> {

    @Resource
    private IDomainEventRepository eventRepository;

    public BaseRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public <S extends T> S save(S entity) {
        eventRepository.save(entity.get_events());
        entity.clearEvents();
        return super.save(entity);
    }
}
