package vip.kratos.ddd.zmall.domain.shared;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ValueObject<TElement extends IValueObject<TElement>> implements IValueObject<TElement> {

    private transient int cachedHashCode = 0;

    @Override
    public boolean sameValueAs(TElement other) {
        return other != null && reflectionEquals((TElement) this, other);
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return sameValueAs((TElement) o);
    }

    @Override
    public final int hashCode() {
        int h = cachedHashCode;
        if (h == 0) {
            h = reflectionHash((TElement) this);
            cachedHashCode = h;
        }

        return h;
    }

    private int reflectionHash(TElement a) {
        Class<?> clazz = a.getClass();
        List<Object> list = new ArrayList<>();

        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Method readMethod = pd.getReadMethod();
                Object aValue = readMethod.invoke(a);
                list.add(aValue);
            }
        } catch (Exception ignored) {

        }

        return Objects.hash(list);
    }

    private boolean reflectionEquals(TElement a, TElement b) {
        if (a.getClass() != b.getClass()) return false;
        boolean diff = false;
        Class<?> clazz = a.getClass();

        try {
            //获取object的所有属性
            PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                Method readMethod = pd.getReadMethod();

                // a.getXxx()
                Object aValue = readMethod.invoke(a);
                // b.getXxx()
                Object bValue = readMethod.invoke(b);

                if (aValue == null && bValue == null) continue;

                if (aValue == null || bValue == null) {
                    diff = true;
                    break;
                }

                if (!aValue.equals(bValue)) {
                    diff = true;
                    break;
                }
            }
        } catch (Exception ignored) {

        }

        return diff;
    }
}
