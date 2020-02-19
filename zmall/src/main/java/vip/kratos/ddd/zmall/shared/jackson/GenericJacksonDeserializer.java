package vip.kratos.ddd.zmall.shared.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class GenericJacksonDeserializer<T> extends StdDeserializer<T> {

    public GenericJacksonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode obj = mapper.readTree(jsonParser);
        Iterator<Map.Entry<String, JsonNode>> elementsIterator = obj.fields();
        while (elementsIterator.hasNext()) {
            Map.Entry<String, JsonNode> element = elementsIterator.next();
            String name = element.getKey();
            if (name.equals(GenericJacksonModule.TYPE)) {
                try {
                    JsonNode value = element.getValue();
                    String className = value.asText();
                    Class<?> aClass = Class.forName(className);
                    return (T) mapper.treeToValue(obj, aClass);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        throw new RuntimeException("No meta filed (_type) found in JSON string.");
    }
}
