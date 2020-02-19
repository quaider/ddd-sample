package vip.kratos.ddd.zmall.shared.domain;

import java.util.Date;

public abstract class DomainEvent implements IDomainEvent {

    private Date occurredOn;

    protected DomainEvent() {
        this.occurredOn = new Date();
    }

    public Date occurredOn() {
        return occurredOn;
    }
}
