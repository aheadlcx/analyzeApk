package org.msgpack.core.buffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import org.msgpack.core.Preconditions;

public class ChannelBufferInput implements MessageBufferInput {
    private final MessageBuffer buffer;
    private ReadableByteChannel channel;

    public ChannelBufferInput(ReadableByteChannel readableByteChannel) {
        this(readableByteChannel, 8192);
    }

    public ChannelBufferInput(ReadableByteChannel readableByteChannel, int i) {
        this.channel = (ReadableByteChannel) Preconditions.checkNotNull(readableByteChannel, "input channel is null");
        Preconditions.checkArgument(i > 0, "buffer size must be > 0: " + i);
        this.buffer = MessageBuffer.allocate(i);
    }

    public ReadableByteChannel reset(ReadableByteChannel readableByteChannel) throws IOException {
        ReadableByteChannel readableByteChannel2 = this.channel;
        this.channel = readableByteChannel;
        return readableByteChannel2;
    }

    public MessageBuffer next() throws IOException {
        ByteBuffer sliceAsByteBuffer = this.buffer.sliceAsByteBuffer();
        while (sliceAsByteBuffer.remaining() > 0) {
            if (this.channel.read(sliceAsByteBuffer) == -1) {
                break;
            }
        }
        sliceAsByteBuffer.flip();
        return sliceAsByteBuffer.remaining() == 0 ? null : this.buffer.slice(0, sliceAsByteBuffer.limit());
    }

    public void close() throws IOException {
        this.channel.close();
    }
}
