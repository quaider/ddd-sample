package vip.kratos.ddd.zmall.domain.common;

public abstract class Entity {
    protected Long id;

    public Long getId() {
        return id;
    }

    protected Entity(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Entity other = (Entity) obj;
        if (this.id == null || other.getId() == null) return false;
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
