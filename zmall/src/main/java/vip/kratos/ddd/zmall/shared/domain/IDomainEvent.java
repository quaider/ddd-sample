package vip.kratos.ddd.zmall.shared.domain;

import java.util.Date;

public interface IDomainEvent {
    Date occurredOn();
    long eventId();
}
