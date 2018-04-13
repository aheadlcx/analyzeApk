package com.google.tagmanager.protobuf.nano;

import java.util.ArrayList;
import java.util.List;

public abstract class ExtendableMessageNano extends MessageNano {
    protected List<UnknownFieldData> unknownFieldData;

    public int getSerializedSize() {
        int computeWireSize = WireFormatNano.computeWireSize(this.unknownFieldData);
        this.cachedSize = computeWireSize;
        return computeWireSize;
    }

    public <T> T getExtension(Extension<T> extension) {
        return WireFormatNano.getExtension(extension, this.unknownFieldData);
    }

    public <T> void setExtension(Extension<T> extension, T t) {
        if (this.unknownFieldData == null) {
            this.unknownFieldData = new ArrayList();
        }
        WireFormatNano.setExtension(extension, t, this.unknownFieldData);
    }
}
