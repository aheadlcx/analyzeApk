package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Arrays;

@JsonSerialize(using = MessagePackExtensionType$Serializer.class)
public class MessagePackExtensionType {
    private final byte[] data;
    private final byte type;

    public MessagePackExtensionType(byte b, byte[] bArr) {
        this.type = b;
        this.data = bArr;
    }

    public byte getType() {
        return this.type;
    }

    public byte[] getData() {
        return this.data;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessagePackExtensionType)) {
            return false;
        }
        MessagePackExtensionType messagePackExtensionType = (MessagePackExtensionType) obj;
        if (this.type == messagePackExtensionType.type) {
            return Arrays.equals(this.data, messagePackExtensionType.data);
        }
        return false;
    }

    public int hashCode() {
        return (this.type * 31) + Arrays.hashCode(this.data);
    }
}
