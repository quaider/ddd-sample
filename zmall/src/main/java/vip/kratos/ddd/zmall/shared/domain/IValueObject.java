package vip.kratos.ddd.zmall.shared.domain;

import java.io.Serializable;

public interface IValueObject<TElement> extends Serializable {
    boolean sameValueAs(TElement other);
}
