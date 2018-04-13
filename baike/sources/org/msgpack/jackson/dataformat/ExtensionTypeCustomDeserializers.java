package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtensionTypeCustomDeserializers {
    private Map<Byte, Deser> deserTable = new ConcurrentHashMap();
    private final ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory().setReuseResourceInParser(false));

    public interface Deser {
        Object deserialize(byte[] bArr) throws IOException;
    }

    public <T> void addTargetClass(byte b, final Class<T> cls) {
        this.deserTable.put(Byte.valueOf(b), new Deser() {
            public Object deserialize(byte[] bArr) throws IOException {
                return ExtensionTypeCustomDeserializers.this.objectMapper.readValue(bArr, cls);
            }
        });
    }

    public void addTargetTypeReference(byte b, final TypeReference typeReference) {
        this.deserTable.put(Byte.valueOf(b), new Deser() {
            public Object deserialize(byte[] bArr) throws IOException {
                return ExtensionTypeCustomDeserializers.this.objectMapper.readValue(bArr, typeReference);
            }
        });
    }

    public void addCustomDeser(byte b, final Deser deser) {
        this.deserTable.put(Byte.valueOf(b), new Deser() {
            public Object deserialize(byte[] bArr) throws IOException {
                return deser.deserialize(bArr);
            }
        });
    }

    public Deser getDeser(byte b) {
        return (Deser) this.deserTable.get(Byte.valueOf(b));
    }

    public void clearEntries() {
        this.deserTable.clear();
    }
}
