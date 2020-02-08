package vip.kratos.ddd.zmall.domain.common;

public abstract class AggregateRoot extends Entity {
    protected AggregateRoot(Long id) {
        super(id);
    }
}
