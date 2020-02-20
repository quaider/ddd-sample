package vip.kratos.ddd.zmall.shared.domain;

import vip.kratos.ddd.zmall.shared.util.IdGenerator;

import java.util.Date;

public abstract class DomainEvent implements IDomainEvent {

    private Date occurredOn;
    private Long eventId;

    protected DomainEvent() {
        this.occurredOn = new Date();
        eventId = IdGenerator.nextId();
    }

    public Date occurredOn() {
        return occurredOn;
    }

    @Override
    public long eventId() {
        return eventId;
    }
}
