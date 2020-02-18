package vip.kratos.ddd.zmall.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import vip.kratos.ddd.zmall.domain.shared.AggregateRoot;
import vip.kratos.ddd.zmall.infrastructure.repository.jpa.event.DefaultEventRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

public abstract class BaseRepository<T extends AggregateRoot<T>> extends SimpleJpaRepository<T, Long> {

    @Resource
    private DefaultEventRepository<T> eventRepository;

    public BaseRepository(Class<T> domainClass, EntityManager em) {
        super(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    @Transactional
    @Override
    public <S extends T> S save(S entity) {
        eventRepository.store(entity);
        return super.save(entity);
    }
}
