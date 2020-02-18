package vip.kratos.ddd.zmall.infrastructure.repository.jpa.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainEventRepository extends JpaRepository<DomainEventJpa, Long> {
}
