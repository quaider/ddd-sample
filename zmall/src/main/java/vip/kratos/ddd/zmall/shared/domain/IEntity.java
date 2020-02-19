package vip.kratos.ddd.zmall.shared.domain;

public interface IEntity<TKey, TElement extends IEntity<TKey, TElement>> {

    TKey identity();

    default boolean sameIdentityAs(TElement other) {
        return other != null && identity().equals(other.identity());
    }
}

