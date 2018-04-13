package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class MessagePackExtensionType$Serializer extends JsonSerializer<MessagePackExtensionType> {
    public void serialize(MessagePackExtensionType messagePackExtensionType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (jsonGenerator instanceof MessagePackGenerator) {
            ((MessagePackGenerator) jsonGenerator).writeExtensionType(messagePackExtensionType);
            return;
        }
        throw new IllegalStateException("'gen' is expected to be MessagePackGenerator but it's " + jsonGenerator.getClass());
    }
}
