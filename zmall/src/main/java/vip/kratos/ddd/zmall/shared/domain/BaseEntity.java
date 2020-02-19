package vip.kratos.ddd.zmall.shared.domain;

import com.google.common.base.Objects;

public abstract class BaseEntity<T extends BaseEntity<T>> implements IEntity<Long, T> {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        T other = (T) obj;
        return identity() != null && sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identity());
    }
}
