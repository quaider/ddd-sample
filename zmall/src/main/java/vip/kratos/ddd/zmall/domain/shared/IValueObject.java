package vip.kratos.ddd.zmall.domain.shared;

import java.io.Serializable;

public interface IValueObject<TElement> extends Serializable {
    boolean sameValueAs(TElement other);
}
