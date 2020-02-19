package vip.kratos.ddd.zmall.shared.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

import java.io.IOException;
import java.util.Set;

public class GenericJacksonSerializer<T> extends BeanSerializerBase {

    private Class<T> clazz;

    public GenericJacksonSerializer(Class<T> clazz, BeanSerializerBase source) {
        super(source);
        this.clazz = clazz;
    }

    private GenericJacksonSerializer(Class<T> clazz, GenericJacksonSerializer source, ObjectIdWriter objectIdWriter) {
        super(source, objectIdWriter);
        this.clazz = clazz;
    }

    private GenericJacksonSerializer(Class<T> clazz, GenericJacksonSerializer source, Set<String> toIgnore) {
        super(source, toIgnore);
        this.clazz = clazz;
    }

    private GenericJacksonSerializer(Class<T> clazz, GenericJacksonSerializer domainEventSerializer, ObjectIdWriter objectIdWriter, Object filterId) {
        super(domainEventSerializer, objectIdWriter, filterId);
        this.clazz = clazz;
    }

    @Override
    public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        return new GenericJacksonSerializer<>(clazz, this, objectIdWriter);
    }

    @Override
    protected BeanSerializerBase withIgnorals(Set<String> toIgnore) {
        return new GenericJacksonSerializer<>(clazz, this, toIgnore);
    }

    @Override
    protected BeanSerializerBase asArraySerializer() {
        return this;
    }

    @Override
    public BeanSerializerBase withFilterId(Object filterId) {
        return new GenericJacksonSerializer<>(clazz, this, _objectIdWriter, filterId);
    }

    @Override
    public void serialize(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        serializeFields(bean, gen, provider);
        if (clazz.isInstance(bean)) {
            gen.writeStringField(GenericJacksonModule.TYPE, bean.getClass().getName());
        }
        gen.writeEndObject();
    }
}
