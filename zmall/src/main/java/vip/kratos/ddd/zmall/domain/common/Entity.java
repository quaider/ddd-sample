package vip.kratos.ddd.zmall.domain.common;

import com.google.common.base.Objects;

public abstract class Entity {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Entity other = (Entity) obj;
        return (this.id != null && other.getId() != null) && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
