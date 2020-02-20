package vip.kratos.ddd.zmall.infrastructure.repository.jpa;

import com.google.common.collect.Iterables;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import vip.kratos.ddd.zmall.shared.domain.AggregateRoot;
import vip.kratos.ddd.zmall.shared.domain.IDomainEvent;
import vip.kratos.ddd.zmall.shared.domain.IEventStore;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

public abstract class BaseRepository<T extends AggregateRoot<T>> extends SimpleJpaRepository<T, Long> {

    @Resource
    private IEventStore eventStore;

    public BaseRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public <S extends T> S save(S entity) {

        eventStore.append(Iterables.toArray(entity.get_events(), IDomainEvent.class));

        S result = super.save(entity);

        // publish in-memory
        entity.clearEvents();

        return result;
    }
}
