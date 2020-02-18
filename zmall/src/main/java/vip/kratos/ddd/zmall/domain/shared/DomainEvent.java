package vip.kratos.ddd.zmall.domain.shared;

import java.time.Instant;

public abstract class DomainEvent<T> implements IDomainEvent<T> {

    private Instant createdAt;

    protected DomainEvent() {
        this.createdAt = Instant.now();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
