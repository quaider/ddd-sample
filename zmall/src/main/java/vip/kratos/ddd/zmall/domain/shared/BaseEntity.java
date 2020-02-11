package vip.kratos.ddd.zmall.domain.shared;

import com.google.common.base.Objects;

public abstract class BaseEntity<T extends IEntity<Long, T>> implements IEntity<Long, T> {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        BaseEntity<?> other = (BaseEntity<?>) obj;
        return (getIdentity() != null && other.getIdentity() != null) && getIdentity().equals(other.getIdentity());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIdentity());
    }
}
