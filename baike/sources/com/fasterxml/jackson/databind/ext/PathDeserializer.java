package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDeserializer extends StdScalarDeserializer<Path> {
    private static final long serialVersionUID = 1;

    public PathDeserializer() {
        super(Path.class);
    }

    public Path deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != null && currentToken.isScalarValue()) {
            return Paths.get(jsonParser.getValueAsString(), new String[0]);
        }
        throw deserializationContext.mappingException(Path.class, currentToken);
    }
}
