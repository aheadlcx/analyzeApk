package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class MessagePackKeySerializer extends StdSerializer<Object> {
    public MessagePackKeySerializer() {
        super(Object.class);
    }

    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws JsonGenerationException, IOException {
        jsonGenerator.writeFieldName(new MessagePackSerializedString(obj));
    }
}
