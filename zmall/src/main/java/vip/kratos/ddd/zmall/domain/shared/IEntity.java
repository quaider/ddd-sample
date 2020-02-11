package vip.kratos.ddd.zmall.domain.shared;

public interface IEntity<TKey, TElement extends IEntity<TKey, TElement>> {

    TKey identity();

    default boolean sameIdentityAs(TElement other) {
        return other != null && identity().equals(other.identity());
    }
}

