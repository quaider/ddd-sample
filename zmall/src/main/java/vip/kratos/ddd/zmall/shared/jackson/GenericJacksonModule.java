package vip.kratos.ddd.zmall.shared.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import vip.kratos.ddd.zmall.shared.domain.IDomainEvent;

public class GenericJacksonModule<T> extends SimpleModule {
    public static final String TYPE = "_type";

    public GenericJacksonModule(Class<?> clazz) {
        super();
        super.addDeserializer(clazz, new GenericJacksonDeserializer<>(clazz));
    }

    public void setupModule(SetupContext context) {
        super.setupModule(context);

        context.addBeanSerializerModifier(new BeanSerializerModifier() {

            public JsonSerializer<?> modifySerializer(
                    SerializationConfig config,
                    BeanDescription beanDesc,
                    JsonSerializer<?> serializer) {
                if (serializer instanceof BeanSerializerBase) {
                    return new GenericJacksonSerializer<>(IDomainEvent.class, (BeanSerializerBase) serializer);
                }
                return serializer;

            }
        });
    }
}
