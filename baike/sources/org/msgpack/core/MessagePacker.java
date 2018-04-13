package org.msgpack.core;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.msgpack.core.MessagePack.Code;
import org.msgpack.core.MessagePack.PackerConfig;
import org.msgpack.core.buffer.MessageBuffer;
import org.msgpack.core.buffer.MessageBufferOutput;
import org.msgpack.value.Value;

public class MessagePacker implements Closeable, Flushable {
    private static final boolean CORRUPTED_CHARSET_ENCODER;
    private static final int UTF_8_MAX_CHAR_SIZE = 6;
    private MessageBuffer buffer;
    private final int bufferFlushThreshold;
    private CharsetEncoder encoder;
    protected MessageBufferOutput out;
    private int position = 0;
    private final int smallStringOptimizationThreshold;
    private final boolean str8FormatSupport;
    private long totalFlushBytes = 0;

    static {
        boolean z = false;
        try {
            Class cls = Class.forName("android.os.Build$VERSION");
            int i = cls.getField("SDK_INT").getInt(cls.getConstructor(new Class[0]).newInstance(new Object[0]));
            if (i >= 14 && i < 21) {
                z = true;
            }
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (InstantiationException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        } catch (NoSuchFieldException e6) {
            e6.printStackTrace();
        }
        CORRUPTED_CHARSET_ENCODER = z;
    }

    protected MessagePacker(MessageBufferOutput messageBufferOutput, PackerConfig packerConfig) {
        this.out = (MessageBufferOutput) Preconditions.checkNotNull(messageBufferOutput, "MessageBufferOutput is null");
        this.smallStringOptimizationThreshold = packerConfig.getSmallStringOptimizationThreshold();
        this.bufferFlushThreshold = packerConfig.getBufferFlushThreshold();
        this.str8FormatSupport = packerConfig.isStr8FormatSupport();
    }

    public MessageBufferOutput reset(MessageBufferOutput messageBufferOutput) throws IOException {
        MessageBufferOutput messageBufferOutput2 = (MessageBufferOutput) Preconditions.checkNotNull(messageBufferOutput, "MessageBufferOutput is null");
        flush();
        MessageBufferOutput messageBufferOutput3 = this.out;
        this.out = messageBufferOutput2;
        this.totalFlushBytes = 0;
        return messageBufferOutput3;
    }

    public long getTotalWrittenBytes() {
        return this.totalFlushBytes + ((long) this.position);
    }

    public void flush() throws IOException {
        if (this.position > 0) {
            flushBuffer();
        }
        this.out.flush();
    }

    public void close() throws IOException {
        try {
            flush();
        } finally {
            this.out.close();
        }
    }

    private void flushBuffer() throws IOException {
        this.out.writeBuffer(this.position);
        this.buffer = null;
        this.totalFlushBytes += (long) this.position;
        this.position = 0;
    }

    private void ensureCapacity(int i) throws IOException {
        if (this.buffer == null) {
            this.buffer = this.out.next(i);
        } else if (this.position + i >= this.buffer.size()) {
            flushBuffer();
            this.buffer = this.out.next(i);
        }
    }

    private void writeByte(byte b) throws IOException {
        ensureCapacity(1);
        MessageBuffer messageBuffer = this.buffer;
        int i = this.position;
        this.position = i + 1;
        messageBuffer.putByte(i, b);
    }

    private void writeByteAndByte(byte b, byte b2) throws IOException {
        ensureCapacity(2);
        MessageBuffer messageBuffer = this.buffer;
        int i = this.position;
        this.position = i + 1;
        messageBuffer.putByte(i, b);
        messageBuffer = this.buffer;
        i = this.position;
        this.position = i + 1;
        messageBuffer.putByte(i, b2);
    }

    private void writeByteAndShort(byte b, short s) throws IOException {
        ensureCapacity(3);
        MessageBuffer messageBuffer = this.buffer;
        int i = this.position;
        this.position = i + 1;
        messageBuffer.putByte(i, b);
        this.buffer.putShort(this.position, s);
        this.position += 2;
    }

    private void writeByteAndInt(byte b, int i) throws IOException {
        ensureCapacity(5);
        MessageBuffer messageBuffer = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        messageBuffer.putByte(i2, b);
        this.buffer.putInt(this.position, i);
        this.position += 4;
    }

    private void writeByteAndFloat(byte b, float f) throws IOException {
        ensureCapacity(5);
        MessageBuffer messageBuffer = this.buffer;
        int i = this.position;
        this.position = i + 1;
        messageBuffer.putByte(i, b);
        this.buffer.putFloat(this.position, f);
        this.position += 4;
    }

    private void writeByteAndDouble(byte b, double d) throws IOException {
        ensureCapacity(9);
        MessageBuffer messageBuffer = this.buffer;
        int i = this.position;
        this.position = i + 1;
        messageBuffer.putByte(i, b);
        this.buffer.putDouble(this.position, d);
        this.position += 8;
    }

    private void writeByteAndLong(byte b, long j) throws IOException {
        ensureCapacity(9);
        MessageBuffer messageBuffer = this.buffer;
        int i = this.position;
        this.position = i + 1;
        messageBuffer.putByte(i, b);
        this.buffer.putLong(this.position, j);
        this.position += 8;
    }

    private void writeShort(short s) throws IOException {
        ensureCapacity(2);
        this.buffer.putShort(this.position, s);
        this.position += 2;
    }

    private void writeInt(int i) throws IOException {
        ensureCapacity(4);
        this.buffer.putInt(this.position, i);
        this.position += 4;
    }

    private void writeLong(long j) throws IOException {
        ensureCapacity(8);
        this.buffer.putLong(this.position, j);
        this.position += 8;
    }

    public MessagePacker packNil() throws IOException {
        writeByte(Code.NIL);
        return this;
    }

    public MessagePacker packBoolean(boolean z) throws IOException {
        writeByte(z ? Code.TRUE : Code.FALSE);
        return this;
    }

    public MessagePacker packByte(byte b) throws IOException {
        if (b < Code.NEGFIXINT_PREFIX) {
            writeByteAndByte(Code.INT8, b);
        } else {
            writeByte(b);
        }
        return this;
    }

    public MessagePacker packShort(short s) throws IOException {
        if (s < (short) -32) {
            if (s < (short) -128) {
                writeByteAndShort(Code.INT16, s);
            } else {
                writeByteAndByte(Code.INT8, (byte) s);
            }
        } else if (s < MqttException.REASON_CODE_SUBSCRIBE_FAILED) {
            writeByte((byte) s);
        } else if (s < (short) 256) {
            writeByteAndByte(Code.UINT8, (byte) s);
        } else {
            writeByteAndShort(Code.UINT16, s);
        }
        return this;
    }

    public MessagePacker packInt(int i) throws IOException {
        if (i < -32) {
            if (i < -32768) {
                writeByteAndInt(Code.INT32, i);
            } else if (i < -128) {
                writeByteAndShort(Code.INT16, (short) i);
            } else {
                writeByteAndByte(Code.INT8, (byte) i);
            }
        } else if (i < 128) {
            writeByte((byte) i);
        } else if (i < 256) {
            writeByteAndByte(Code.UINT8, (byte) i);
        } else if (i < 65536) {
            writeByteAndShort(Code.UINT16, (short) i);
        } else {
            writeByteAndInt(Code.UINT32, i);
        }
        return this;
    }

    public MessagePacker packLong(long j) throws IOException {
        if (j < -32) {
            if (j < -32768) {
                if (j < -2147483648L) {
                    writeByteAndLong(Code.INT64, j);
                } else {
                    writeByteAndInt(Code.INT32, (int) j);
                }
            } else if (j < -128) {
                writeByteAndShort(Code.INT16, (short) ((int) j));
            } else {
                writeByteAndByte(Code.INT8, (byte) ((int) j));
            }
        } else if (j < 128) {
            writeByte((byte) ((int) j));
        } else if (j < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            if (j < 256) {
                writeByteAndByte(Code.UINT8, (byte) ((int) j));
            } else {
                writeByteAndShort(Code.UINT16, (short) ((int) j));
            }
        } else if (j < 4294967296L) {
            writeByteAndInt(Code.UINT32, (int) j);
        } else {
            writeByteAndLong(Code.UINT64, j);
        }
        return this;
    }

    public MessagePacker packBigInteger(BigInteger bigInteger) throws IOException {
        if (bigInteger.bitLength() <= 63) {
            packLong(bigInteger.longValue());
        } else if (bigInteger.bitLength() == 64 && bigInteger.signum() == 1) {
            writeByteAndLong(Code.UINT64, bigInteger.longValue());
        } else {
            throw new IllegalArgumentException("MessagePack cannot serialize BigInteger larger than 2^64-1");
        }
        return this;
    }

    public MessagePacker packFloat(float f) throws IOException {
        writeByteAndFloat(Code.FLOAT32, f);
        return this;
    }

    public MessagePacker packDouble(double d) throws IOException {
        writeByteAndDouble(Code.FLOAT64, d);
        return this;
    }

    private void packStringWithGetBytes(String str) throws IOException {
        byte[] bytes = str.getBytes(MessagePack.UTF8);
        packRawStringHeader(bytes.length);
        addPayload(bytes);
    }

    private void prepareEncoder() {
        if (this.encoder == null) {
            this.encoder = MessagePack.UTF8.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        }
        this.encoder.reset();
    }

    private int encodeStringToBufferAt(int i, String str) {
        prepareEncoder();
        ByteBuffer sliceAsByteBuffer = this.buffer.sliceAsByteBuffer(i, this.buffer.size() - i);
        int position = sliceAsByteBuffer.position();
        CoderResult encode = this.encoder.encode(CharBuffer.wrap(str), sliceAsByteBuffer, true);
        if (encode.isError()) {
            try {
                encode.throwException();
            } catch (CharacterCodingException e) {
                throw new MessageStringCodingException(e);
            }
        }
        if (encode.isUnderflow() && !encode.isOverflow() && this.encoder.flush(sliceAsByteBuffer).isUnderflow()) {
            return sliceAsByteBuffer.position() - position;
        }
        return -1;
    }

    public MessagePacker packString(String str) throws IOException {
        if (str.length() <= 0) {
            packRawStringHeader(0);
        } else if (CORRUPTED_CHARSET_ENCODER || str.length() < this.smallStringOptimizationThreshold) {
            packStringWithGetBytes(str);
        } else {
            int encodeStringToBufferAt;
            MessageBuffer messageBuffer;
            int i;
            if (str.length() < 256) {
                ensureCapacity(((str.length() * 6) + 2) + 1);
                encodeStringToBufferAt = encodeStringToBufferAt(this.position + 2, str);
                if (encodeStringToBufferAt >= 0) {
                    if (this.str8FormatSupport && encodeStringToBufferAt < 256) {
                        messageBuffer = this.buffer;
                        i = this.position;
                        this.position = i + 1;
                        messageBuffer.putByte(i, Code.STR8);
                        messageBuffer = this.buffer;
                        i = this.position;
                        this.position = i + 1;
                        messageBuffer.putByte(i, (byte) encodeStringToBufferAt);
                        this.position = encodeStringToBufferAt + this.position;
                    } else if (encodeStringToBufferAt >= 65536) {
                        throw new IllegalArgumentException("Unexpected UTF-8 encoder state");
                    } else {
                        this.buffer.putMessageBuffer(this.position + 3, this.buffer, this.position + 2, encodeStringToBufferAt);
                        messageBuffer = this.buffer;
                        i = this.position;
                        this.position = i + 1;
                        messageBuffer.putByte(i, Code.STR16);
                        this.buffer.putShort(this.position, (short) encodeStringToBufferAt);
                        this.position += 2;
                        this.position = encodeStringToBufferAt + this.position;
                    }
                }
            } else if (str.length() < 65536) {
                ensureCapacity(((str.length() * 6) + 3) + 2);
                encodeStringToBufferAt = encodeStringToBufferAt(this.position + 3, str);
                if (encodeStringToBufferAt >= 0) {
                    if (encodeStringToBufferAt < 65536) {
                        messageBuffer = this.buffer;
                        i = this.position;
                        this.position = i + 1;
                        messageBuffer.putByte(i, Code.STR16);
                        this.buffer.putShort(this.position, (short) encodeStringToBufferAt);
                        this.position += 2;
                        this.position = encodeStringToBufferAt + this.position;
                    } else if (((long) encodeStringToBufferAt) >= 4294967296L) {
                        throw new IllegalArgumentException("Unexpected UTF-8 encoder state");
                    } else {
                        this.buffer.putMessageBuffer(this.position + 5, this.buffer, this.position + 3, encodeStringToBufferAt);
                        messageBuffer = this.buffer;
                        i = this.position;
                        this.position = i + 1;
                        messageBuffer.putByte(i, Code.STR32);
                        this.buffer.putInt(this.position, encodeStringToBufferAt);
                        this.position += 4;
                        this.position = encodeStringToBufferAt + this.position;
                    }
                }
            }
            packStringWithGetBytes(str);
        }
        return this;
    }

    public MessagePacker packArrayHeader(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("array size must be >= 0");
        }
        if (i < 16) {
            writeByte((byte) (i | -112));
        } else if (i < 65536) {
            writeByteAndShort(Code.ARRAY16, (short) i);
        } else {
            writeByteAndInt(Code.ARRAY32, i);
        }
        return this;
    }

    public MessagePacker packMapHeader(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("map size must be >= 0");
        }
        if (i < 16) {
            writeByte((byte) (i | -128));
        } else if (i < 65536) {
            writeByteAndShort(Code.MAP16, (short) i);
        } else {
            writeByteAndInt(Code.MAP32, i);
        }
        return this;
    }

    public MessagePacker packValue(Value value) throws IOException {
        value.writeTo(this);
        return this;
    }

    public MessagePacker packExtensionTypeHeader(byte b, int i) throws IOException {
        if (i < 256) {
            if (i <= 0 || ((i - 1) & i) != 0) {
                writeByteAndByte(Code.EXT8, (byte) i);
                writeByte(b);
            } else if (i == 1) {
                writeByteAndByte(Code.FIXEXT1, b);
            } else if (i == 2) {
                writeByteAndByte(Code.FIXEXT2, b);
            } else if (i == 4) {
                writeByteAndByte(Code.FIXEXT4, b);
            } else if (i == 8) {
                writeByteAndByte(Code.FIXEXT8, b);
            } else if (i == 16) {
                writeByteAndByte(Code.FIXEXT16, b);
            } else {
                writeByteAndByte(Code.EXT8, (byte) i);
                writeByte(b);
            }
        } else if (i < 65536) {
            writeByteAndShort(Code.EXT16, (short) i);
            writeByte(b);
        } else {
            writeByteAndInt(Code.EXT32, i);
            writeByte(b);
        }
        return this;
    }

    public MessagePacker packBinaryHeader(int i) throws IOException {
        if (i < 256) {
            writeByteAndByte(Code.BIN8, (byte) i);
        } else if (i < 65536) {
            writeByteAndShort(Code.BIN16, (short) i);
        } else {
            writeByteAndInt(Code.BIN32, i);
        }
        return this;
    }

    public MessagePacker packRawStringHeader(int i) throws IOException {
        if (i < 32) {
            writeByte((byte) (i | -96));
        } else if (this.str8FormatSupport && i < 256) {
            writeByteAndByte(Code.STR8, (byte) i);
        } else if (i < 65536) {
            writeByteAndShort(Code.STR16, (short) i);
        } else {
            writeByteAndInt(Code.STR32, i);
        }
        return this;
    }

    public MessagePacker writePayload(byte[] bArr) throws IOException {
        return writePayload(bArr, 0, bArr.length);
    }

    public MessagePacker writePayload(byte[] bArr, int i, int i2) throws IOException {
        if (this.buffer == null || this.buffer.size() - this.position < i2 || i2 > this.bufferFlushThreshold) {
            flush();
            this.out.write(bArr, i, i2);
            this.totalFlushBytes += (long) i2;
        } else {
            this.buffer.putBytes(this.position, bArr, i, i2);
            this.position += i2;
        }
        return this;
    }

    public MessagePacker addPayload(byte[] bArr) throws IOException {
        return addPayload(bArr, 0, bArr.length);
    }

    public MessagePacker addPayload(byte[] bArr, int i, int i2) throws IOException {
        if (this.buffer == null || this.buffer.size() - this.position < i2 || i2 > this.bufferFlushThreshold) {
            flush();
            this.out.add(bArr, i, i2);
            this.totalFlushBytes += (long) i2;
        } else {
            this.buffer.putBytes(this.position, bArr, i, i2);
            this.position += i2;
        }
        return this;
    }
}
