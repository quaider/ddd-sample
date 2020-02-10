package vip.kratos.ddd.zmall.domain.shared;

public interface IEntity<TKey, TElement extends IEntity<TKey, TElement>> {

    TKey getIdentity();

    default boolean sameIdentityAs(TElement other) {
        return other != null && getIdentity().equals(other.getIdentity());
    }
}

