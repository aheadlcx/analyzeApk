package com.google.protobuf;

import com.google.protobuf.CodedInputStream.StreamDecoder;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

class CodedInputStream$StreamDecoder$SkippedDataSink implements CodedInputStream$StreamDecoder$RefillCallback {
    private ByteArrayOutputStream byteArrayStream;
    private int lastPos = StreamDecoder.access$400(this.this$0);
    final /* synthetic */ StreamDecoder this$0;

    private CodedInputStream$StreamDecoder$SkippedDataSink(StreamDecoder streamDecoder) {
        this.this$0 = streamDecoder;
    }

    public void onRefill() {
        if (this.byteArrayStream == null) {
            this.byteArrayStream = new ByteArrayOutputStream();
        }
        this.byteArrayStream.write(StreamDecoder.access$500(this.this$0), this.lastPos, StreamDecoder.access$400(this.this$0) - this.lastPos);
        this.lastPos = 0;
    }

    ByteBuffer getSkippedData() {
        if (this.byteArrayStream == null) {
            return ByteBuffer.wrap(StreamDecoder.access$500(this.this$0), this.lastPos, StreamDecoder.access$400(this.this$0) - this.lastPos);
        }
        this.byteArrayStream.write(StreamDecoder.access$500(this.this$0), this.lastPos, StreamDecoder.access$400(this.this$0));
        return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
    }
}
