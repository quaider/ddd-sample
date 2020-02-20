package vip.kratos.ddd.zmall.shared.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
public class StoredEvent {

    public static final int INIT = 0;
    public static final int PUBLISHED = 1;
    public static final int PUBLISH_FAILED = 2;

    private String eventBody;
    private Long eventId;
    private Date occurredOn;
    private String typeName;
    private Integer status = INIT;

    public StoredEvent() {
        super();
        this.setEventId(-1L);
    }

    public StoredEvent(long id, String aTypeName, Date anOccurredOn, String anEventBody) {
        this();
        this.setEventId(id);
        this.setEventBody(anEventBody);
        this.setOccurredOn(anOccurredOn);
        this.setTypeName(aTypeName);
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            StoredEvent typedObject = (StoredEvent) anObject;
            equalObjects = this.getEventId() == typedObject.getEventId();
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getEventId());
    }

    public void markAsPublished() {
        this.status = PUBLISHED;
    }

    public void markAsPublishFailed() {
        this.status = PUBLISH_FAILED;
    }
}
