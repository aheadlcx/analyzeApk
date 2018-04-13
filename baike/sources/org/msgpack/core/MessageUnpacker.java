package org.msgpack.core;

import android.support.v4.internal.view.SupportMenu;
import java.io.Closeable;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.msgpack.core.MessagePack.Code;
import org.msgpack.core.MessagePack.UnpackerConfig;
import org.msgpack.core.buffer.MessageBuffer;
import org.msgpack.core.buffer.MessageBufferInput;
import org.msgpack.value.ImmutableValue;
import org.msgpack.value.Value;
import org.msgpack.value.ValueFactory;
import org.msgpack.value.Variable;

public class MessageUnpacker implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final MessageBuffer EMPTY_BUFFER = MessageBuffer.wrap(new byte[0]);
    private static final String EMPTY_STRING = "";
    private final CodingErrorAction actionOnMalformedString;
    private final CodingErrorAction actionOnUnmappableString;
    private final boolean allowReadingBinaryAsString;
    private final boolean allowReadingStringAsBinary;
    private MessageBuffer buffer = EMPTY_BUFFER;
    private CharBuffer decodeBuffer;
    private StringBuilder decodeStringBuffer;
    private CharsetDecoder decoder;
    private MessageBufferInput in;
    private int nextReadPosition;
    private final MessageBuffer numberBuffer = MessageBuffer.allocate(8);
    private int position;
    private final int stringDecoderBufferSize;
    private final int stringSizeLimit;
    private long totalReadBytes;

    static {
        boolean z;
        if (MessageUnpacker.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
    }

    protected MessageUnpacker(MessageBufferInput messageBufferInput, UnpackerConfig unpackerConfig) {
        this.in = (MessageBufferInput) Preconditions.checkNotNull(messageBufferInput, "MessageBufferInput is null");
        this.allowReadingStringAsBinary = unpackerConfig.getAllowReadingStringAsBinary();
        this.allowReadingBinaryAsString = unpackerConfig.getAllowReadingBinaryAsString();
        this.actionOnMalformedString = unpackerConfig.getActionOnMalformedString();
        this.actionOnUnmappableString = unpackerConfig.getActionOnUnmappableString();
        this.stringSizeLimit = unpackerConfig.getStringSizeLimit();
        this.stringDecoderBufferSize = unpackerConfig.getStringDecoderBufferSize();
    }

    public MessageBufferInput reset(MessageBufferInput messageBufferInput) throws IOException {
        MessageBufferInput messageBufferInput2 = (MessageBufferInput) Preconditions.checkNotNull(messageBufferInput, "MessageBufferInput is null");
        MessageBufferInput messageBufferInput3 = this.in;
        this.in = messageBufferInput2;
        this.buffer = EMPTY_BUFFER;
        this.position = 0;
        this.totalReadBytes = 0;
        return messageBufferInput3;
    }

    public long getTotalReadBytes() {
        return this.totalReadBytes + ((long) this.position);
    }

    private MessageBuffer getNextBuffer() throws IOException {
        MessageBuffer next = this.in.next();
        if (next == null) {
            throw new MessageInsufficientBufferException();
        } else if ($assertionsDisabled || this.buffer != null) {
            this.totalReadBytes += (long) this.buffer.size();
            return next;
        } else {
            throw new AssertionError();
        }
    }

    private void nextBuffer() throws IOException {
        this.buffer = getNextBuffer();
        this.position = 0;
    }

    private MessageBuffer prepareNumberBuffer(int i) throws IOException {
        int size = this.buffer.size() - this.position;
        if (size >= i) {
            this.nextReadPosition = this.position;
            this.position += i;
            return this.buffer;
        }
        if (size > 0) {
            this.numberBuffer.putMessageBuffer(0, this.buffer, this.position, size);
            i -= size;
            size += 0;
        } else {
            size = 0;
        }
        while (true) {
            nextBuffer();
            int size2 = this.buffer.size();
            if (size2 >= i) {
                this.numberBuffer.putMessageBuffer(size, this.buffer, 0, i);
                this.position = i;
                this.nextReadPosition = 0;
                return this.numberBuffer;
            }
            this.numberBuffer.putMessageBuffer(size, this.buffer, 0, size2);
            i -= size2;
            size += size2;
        }
    }

    private static int utf8MultibyteCharacterSize(byte b) {
        return Integer.numberOfLeadingZeros(((b & 255) ^ -1) << 24);
    }

    public boolean hasNext() throws IOException {
        return ensureBuffer();
    }

    private boolean ensureBuffer() throws IOException {
        while (this.buffer.size() <= this.position) {
            MessageBuffer next = this.in.next();
            if (next == null) {
                return false;
            }
            this.totalReadBytes += (long) this.buffer.size();
            this.buffer = next;
            this.position = 0;
        }
        return true;
    }

    public MessageFormat getNextFormat() throws IOException {
        if (ensureBuffer()) {
            return MessageFormat.valueOf(this.buffer.getByte(this.position));
        }
        throw new MessageInsufficientBufferException();
    }

    private byte readByte() throws IOException {
        if (this.buffer.size() > this.position) {
            byte b = this.buffer.getByte(this.position);
            this.position++;
            return b;
        }
        nextBuffer();
        if (this.buffer.size() <= 0) {
            return readByte();
        }
        b = this.buffer.getByte(0);
        this.position = 1;
        return b;
    }

    private short readShort() throws IOException {
        return prepareNumberBuffer(2).getShort(this.nextReadPosition);
    }

    private int readInt() throws IOException {
        return prepareNumberBuffer(4).getInt(this.nextReadPosition);
    }

    private long readLong() throws IOException {
        return prepareNumberBuffer(8).getLong(this.nextReadPosition);
    }

    private float readFloat() throws IOException {
        return prepareNumberBuffer(4).getFloat(this.nextReadPosition);
    }

    private double readDouble() throws IOException {
        return prepareNumberBuffer(8).getDouble(this.nextReadPosition);
    }

    public void skipValue() throws IOException {
        skipValue(1);
    }

    public void skipValue(int i) throws IOException {
        int i2 = i;
        while (i2 > 0) {
            byte readByte = readByte();
            switch (MessageFormat.valueOf(readByte)) {
                case FIXMAP:
                    i2 += (readByte & 15) * 2;
                    break;
                case FIXARRAY:
                    i2 += readByte & 15;
                    break;
                case FIXSTR:
                    skipPayload(readByte & 31);
                    break;
                case INT8:
                case UINT8:
                    skipPayload(1);
                    break;
                case INT16:
                case UINT16:
                    skipPayload(2);
                    break;
                case INT32:
                case UINT32:
                case FLOAT32:
                    skipPayload(4);
                    break;
                case INT64:
                case UINT64:
                case FLOAT64:
                    skipPayload(8);
                    break;
                case BIN8:
                case STR8:
                    skipPayload(readNextLength8());
                    break;
                case BIN16:
                case STR16:
                    skipPayload(readNextLength16());
                    break;
                case BIN32:
                case STR32:
                    skipPayload(readNextLength32());
                    break;
                case FIXEXT1:
                    skipPayload(2);
                    break;
                case FIXEXT2:
                    skipPayload(3);
                    break;
                case FIXEXT4:
                    skipPayload(5);
                    break;
                case FIXEXT8:
                    skipPayload(9);
                    break;
                case FIXEXT16:
                    skipPayload(17);
                    break;
                case EXT8:
                    skipPayload(readNextLength8() + 1);
                    break;
                case EXT16:
                    skipPayload(readNextLength16() + 1);
                    break;
                case EXT32:
                    skipPayload(readNextLength32() + 1);
                    break;
                case ARRAY16:
                    i2 += readNextLength16();
                    break;
                case ARRAY32:
                    i2 += readNextLength32();
                    break;
                case MAP16:
                    i2 += readNextLength16() * 2;
                    break;
                case MAP32:
                    i2 += readNextLength32() * 2;
                    break;
                case NEVER_USED:
                    throw new MessageNeverUsedFormatException("Encountered 0xC1 \"NEVER_USED\" byte");
                default:
                    break;
            }
            i2--;
        }
    }

    private static MessagePackException unexpected(String str, byte b) {
        MessageFormat valueOf = MessageFormat.valueOf(b);
        if (valueOf == MessageFormat.NEVER_USED) {
            return new MessageNeverUsedFormatException(String.format("Expected %s, but encountered 0xC1 \"NEVER_USED\" byte", new Object[]{str}));
        }
        String name = valueOf.getValueType().name();
        String str2 = name.substring(0, 1) + name.substring(1).toLowerCase();
        return new MessageTypeException(String.format("Expected %s, but got %s (%02x)", new Object[]{str, str2, Byte.valueOf(b)}));
    }

    public ImmutableValue unpackValue() throws IOException {
        int i = 0;
        MessageFormat nextFormat = getNextFormat();
        int unpackArrayHeader;
        Value[] valueArr;
        switch (nextFormat.getValueType()) {
            case NIL:
                readByte();
                return ValueFactory.newNil();
            case BOOLEAN:
                return ValueFactory.newBoolean(unpackBoolean());
            case INTEGER:
                switch (nextFormat) {
                    case UINT64:
                        return ValueFactory.newInteger(unpackBigInteger());
                    default:
                        return ValueFactory.newInteger(unpackLong());
                }
            case FLOAT:
                return ValueFactory.newFloat(unpackDouble());
            case STRING:
                return ValueFactory.newString(readPayload(unpackRawStringHeader()), true);
            case BINARY:
                return ValueFactory.newBinary(readPayload(unpackBinaryHeader()), true);
            case ARRAY:
                unpackArrayHeader = unpackArrayHeader();
                valueArr = new Value[unpackArrayHeader];
                while (i < unpackArrayHeader) {
                    valueArr[i] = unpackValue();
                    i++;
                }
                return ValueFactory.newArray(valueArr, true);
            case MAP:
                unpackArrayHeader = unpackMapHeader();
                valueArr = new Value[(unpackArrayHeader * 2)];
                while (i < unpackArrayHeader * 2) {
                    valueArr[i] = unpackValue();
                    i++;
                    valueArr[i] = unpackValue();
                    i++;
                }
                return ValueFactory.newMap(valueArr, true);
            case EXTENSION:
                ExtensionTypeHeader unpackExtensionTypeHeader = unpackExtensionTypeHeader();
                return ValueFactory.newExtension(unpackExtensionTypeHeader.getType(), readPayload(unpackExtensionTypeHeader.getLength()));
            default:
                throw new MessageNeverUsedFormatException("Unknown value type");
        }
    }

    public Variable unpackValue(Variable variable) throws IOException {
        int i = 0;
        MessageFormat nextFormat = getNextFormat();
        int unpackArrayHeader;
        switch (nextFormat.getValueType()) {
            case NIL:
                readByte();
                variable.setNilValue();
                break;
            case BOOLEAN:
                variable.setBooleanValue(unpackBoolean());
                break;
            case INTEGER:
                switch (nextFormat) {
                    case UINT64:
                        variable.setIntegerValue(unpackBigInteger());
                        break;
                    default:
                        variable.setIntegerValue(unpackLong());
                        break;
                }
            case FLOAT:
                variable.setFloatValue(unpackDouble());
                break;
            case STRING:
                variable.setStringValue(readPayload(unpackRawStringHeader()));
                break;
            case BINARY:
                variable.setBinaryValue(readPayload(unpackBinaryHeader()));
                break;
            case ARRAY:
                unpackArrayHeader = unpackArrayHeader();
                List arrayList = new ArrayList(unpackArrayHeader);
                while (i < unpackArrayHeader) {
                    arrayList.add(unpackValue());
                    i++;
                }
                variable.setArrayValue(arrayList);
                break;
            case MAP:
                unpackArrayHeader = unpackMapHeader();
                Map hashMap = new HashMap();
                while (i < unpackArrayHeader) {
                    hashMap.put(unpackValue(), unpackValue());
                    i++;
                }
                variable.setMapValue(hashMap);
                break;
            case EXTENSION:
                ExtensionTypeHeader unpackExtensionTypeHeader = unpackExtensionTypeHeader();
                variable.setExtensionValue(unpackExtensionTypeHeader.getType(), readPayload(unpackExtensionTypeHeader.getLength()));
                break;
            default:
                throw new MessageFormatException("Unknown value type");
        }
        return variable;
    }

    public void unpackNil() throws IOException {
        byte readByte = readByte();
        if (readByte != Code.NIL) {
            throw unexpected("Nil", readByte);
        }
    }

    public boolean unpackBoolean() throws IOException {
        byte readByte = readByte();
        if (readByte == Code.FALSE) {
            return false;
        }
        if (readByte == Code.TRUE) {
            return true;
        }
        throw unexpected("boolean", readByte);
    }

    public byte unpackByte() throws IOException {
        byte readByte = readByte();
        if (Code.isFixInt(readByte)) {
            return readByte;
        }
        short readShort;
        int readInt;
        long readLong;
        switch (readByte) {
            case (byte) -52:
                readByte = readByte();
                if (readByte >= (byte) 0) {
                    return readByte;
                }
                throw overflowU8(readByte);
            case (byte) -51:
                readShort = readShort();
                if (readShort >= (short) 0 && readShort <= (short) 127) {
                    return (byte) readShort;
                }
                throw overflowU16(readShort);
            case (byte) -50:
                readInt = readInt();
                if (readInt >= 0 && readInt <= 127) {
                    return (byte) readInt;
                }
                throw overflowU32(readInt);
            case (byte) -49:
                readLong = readLong();
                if (readLong >= 0 && readLong <= 127) {
                    return (byte) ((int) readLong);
                }
                throw overflowU64(readLong);
            case (byte) -48:
                return readByte();
            case (byte) -47:
                readShort = readShort();
                if (readShort >= (short) -128 && readShort <= (short) 127) {
                    return (byte) readShort;
                }
                throw overflowI16(readShort);
            case (byte) -46:
                readInt = readInt();
                if (readInt >= -128 && readInt <= 127) {
                    return (byte) readInt;
                }
                throw overflowI32(readInt);
            case (byte) -45:
                readLong = readLong();
                if (readLong >= -128 && readLong <= 127) {
                    return (byte) ((int) readLong);
                }
                throw overflowI64(readLong);
            default:
                throw unexpected("Integer", readByte);
        }
    }

    public short unpackShort() throws IOException {
        byte readByte = readByte();
        if (Code.isFixInt(readByte)) {
            return (short) readByte;
        }
        int readInt;
        long readLong;
        switch (readByte) {
            case (byte) -52:
                return (short) (readByte() & 255);
            case (byte) -51:
                short readShort = readShort();
                if (readShort >= (short) 0) {
                    return readShort;
                }
                throw overflowU16(readShort);
            case (byte) -50:
                readInt = readInt();
                if (readInt >= 0 && readInt <= 32767) {
                    return (short) readInt;
                }
                throw overflowU32(readInt);
            case (byte) -49:
                readLong = readLong();
                if (readLong >= 0 && readLong <= 32767) {
                    return (short) ((int) readLong);
                }
                throw overflowU64(readLong);
            case (byte) -48:
                return (short) readByte();
            case (byte) -47:
                return readShort();
            case (byte) -46:
                readInt = readInt();
                if (readInt >= -32768 && readInt <= 32767) {
                    return (short) readInt;
                }
                throw overflowI32(readInt);
            case (byte) -45:
                readLong = readLong();
                if (readLong >= -32768 && readLong <= 32767) {
                    return (short) ((int) readLong);
                }
                throw overflowI64(readLong);
            default:
                throw unexpected("Integer", readByte);
        }
    }

    public int unpackInt() throws IOException {
        byte readByte = readByte();
        if (Code.isFixInt(readByte)) {
            return readByte;
        }
        long readLong;
        switch (readByte) {
            case (byte) -52:
                return readByte() & 255;
            case (byte) -51:
                return readShort() & SupportMenu.USER_MASK;
            case (byte) -50:
                int readInt = readInt();
                if (readInt >= 0) {
                    return readInt;
                }
                throw overflowU32(readInt);
            case (byte) -49:
                readLong = readLong();
                if (readLong >= 0 && readLong <= 2147483647L) {
                    return (int) readLong;
                }
                throw overflowU64(readLong);
            case (byte) -48:
                return readByte();
            case (byte) -47:
                return readShort();
            case (byte) -46:
                return readInt();
            case (byte) -45:
                readLong = readLong();
                if (readLong >= -2147483648L && readLong <= 2147483647L) {
                    return (int) readLong;
                }
                throw overflowI64(readLong);
            default:
                throw unexpected("Integer", readByte);
        }
    }

    public long unpackLong() throws IOException {
        byte readByte = readByte();
        if (Code.isFixInt(readByte)) {
            return (long) readByte;
        }
        switch (readByte) {
            case (byte) -52:
                return (long) (readByte() & 255);
            case (byte) -51:
                return (long) (readShort() & SupportMenu.USER_MASK);
            case (byte) -50:
                int readInt = readInt();
                if (readInt < 0) {
                    return ((long) (readInt & Integer.MAX_VALUE)) + k.MAX_AGE;
                }
                return (long) readInt;
            case (byte) -49:
                long readLong = readLong();
                if (readLong >= 0) {
                    return readLong;
                }
                throw overflowU64(readLong);
            case (byte) -48:
                return (long) readByte();
            case (byte) -47:
                return (long) readShort();
            case (byte) -46:
                return (long) readInt();
            case (byte) -45:
                return readLong();
            default:
                throw unexpected("Integer", readByte);
        }
    }

    public BigInteger unpackBigInteger() throws IOException {
        byte readByte = readByte();
        if (Code.isFixInt(readByte)) {
            return BigInteger.valueOf((long) readByte);
        }
        switch (readByte) {
            case (byte) -52:
                return BigInteger.valueOf((long) (readByte() & 255));
            case (byte) -51:
                return BigInteger.valueOf((long) (readShort() & SupportMenu.USER_MASK));
            case (byte) -50:
                int readInt = readInt();
                if (readInt < 0) {
                    return BigInteger.valueOf(((long) (readInt & Integer.MAX_VALUE)) + k.MAX_AGE);
                }
                return BigInteger.valueOf((long) readInt);
            case (byte) -49:
                long readLong = readLong();
                if (readLong < 0) {
                    return BigInteger.valueOf((readLong + Long.MAX_VALUE) + 1).setBit(63);
                }
                return BigInteger.valueOf(readLong);
            case (byte) -48:
                return BigInteger.valueOf((long) readByte());
            case (byte) -47:
                return BigInteger.valueOf((long) readShort());
            case (byte) -46:
                return BigInteger.valueOf((long) readInt());
            case (byte) -45:
                return BigInteger.valueOf(readLong());
            default:
                throw unexpected("Integer", readByte);
        }
    }

    public float unpackFloat() throws IOException {
        byte readByte = readByte();
        switch (readByte) {
            case (byte) -54:
                return readFloat();
            case (byte) -53:
                return (float) readDouble();
            default:
                throw unexpected("Float", readByte);
        }
    }

    public double unpackDouble() throws IOException {
        byte readByte = readByte();
        switch (readByte) {
            case (byte) -54:
                return (double) readFloat();
            case (byte) -53:
                return readDouble();
            default:
                throw unexpected("Float", readByte);
        }
    }

    private void resetDecoder() {
        if (this.decoder == null) {
            this.decodeBuffer = CharBuffer.allocate(this.stringDecoderBufferSize);
            this.decoder = MessagePack.UTF8.newDecoder().onMalformedInput(this.actionOnMalformedString).onUnmappableCharacter(this.actionOnUnmappableString);
        } else {
            this.decoder.reset();
        }
        if (this.decodeStringBuffer == null) {
            this.decodeStringBuffer = new StringBuilder();
        } else {
            this.decodeStringBuffer.setLength(0);
        }
    }

    public String unpackString() throws IOException {
        int unpackRawStringHeader = unpackRawStringHeader();
        if (unpackRawStringHeader == 0) {
            return "";
        }
        if (unpackRawStringHeader > this.stringSizeLimit) {
            throw new MessageSizeException(String.format("cannot unpack a String of size larger than %,d: %,d", new Object[]{Integer.valueOf(this.stringSizeLimit), Integer.valueOf(unpackRawStringHeader)}), (long) unpackRawStringHeader);
        }
        resetDecoder();
        if (this.buffer.size() - this.position >= unpackRawStringHeader) {
            return decodeStringFastPath(unpackRawStringHeader);
        }
        while (unpackRawStringHeader > 0) {
            int size = this.buffer.size() - this.position;
            if (size >= unpackRawStringHeader) {
                this.decodeStringBuffer.append(decodeStringFastPath(unpackRawStringHeader));
                break;
            } else if (size == 0) {
                try {
                    nextBuffer();
                } catch (Throwable e) {
                    throw new MessageFormatException("Unexpected UTF-8 multibyte sequence", e);
                } catch (CharacterCodingException e2) {
                    throw new MessageStringCodingException(e2);
                }
            } else {
                ByteBuffer sliceAsByteBuffer = this.buffer.sliceAsByteBuffer(this.position, size);
                int position = sliceAsByteBuffer.position();
                this.decodeBuffer.clear();
                CoderResult decode = this.decoder.decode(sliceAsByteBuffer, this.decodeBuffer, false);
                int position2 = sliceAsByteBuffer.position() - position;
                this.position += position2;
                unpackRawStringHeader -= position2;
                this.decodeStringBuffer.append(this.decodeBuffer.flip());
                if (decode.isError()) {
                    handleCoderError(decode);
                }
                if (decode.isUnderflow() && position2 < size) {
                    ByteBuffer allocate = ByteBuffer.allocate(utf8MultibyteCharacterSize(this.buffer.getByte(this.position)));
                    this.buffer.getBytes(this.position, this.buffer.size() - this.position, allocate);
                    while (true) {
                        nextBuffer();
                        position2 = allocate.remaining();
                        if (this.buffer.size() >= position2) {
                            break;
                        }
                        this.buffer.getBytes(0, this.buffer.size(), allocate);
                        this.position = this.buffer.size();
                    }
                    this.buffer.getBytes(0, position2, allocate);
                    this.position = position2;
                    allocate.position(0);
                    this.decodeBuffer.clear();
                    CoderResult decode2 = this.decoder.decode(allocate, this.decodeBuffer, false);
                    if (decode2.isError()) {
                        handleCoderError(decode2);
                    }
                    if (decode2.isOverflow() || (decode2.isUnderflow() && allocate.position() < allocate.limit())) {
                        decode2.throwException();
                        throw new MessageFormatException("Unexpected UTF-8 multibyte sequence");
                    }
                    unpackRawStringHeader -= allocate.limit();
                    this.decodeStringBuffer.append(this.decodeBuffer.flip());
                }
            }
        }
        return this.decodeStringBuffer.toString();
    }

    private void handleCoderError(CoderResult coderResult) throws CharacterCodingException {
        if ((coderResult.isMalformed() && this.actionOnMalformedString == CodingErrorAction.REPORT) || (coderResult.isUnmappable() && this.actionOnUnmappableString == CodingErrorAction.REPORT)) {
            coderResult.throwException();
        }
    }

    private String decodeStringFastPath(int i) {
        if (this.actionOnMalformedString == CodingErrorAction.REPLACE && this.actionOnUnmappableString == CodingErrorAction.REPLACE && this.buffer.hasArray()) {
            String str = new String(this.buffer.array(), this.buffer.arrayOffset() + this.position, i, MessagePack.UTF8);
            this.position += i;
            return str;
        }
        try {
            CharBuffer decode = this.decoder.decode(this.buffer.sliceAsByteBuffer(this.position, i));
            this.position += i;
            return decode.toString();
        } catch (CharacterCodingException e) {
            throw new MessageStringCodingException(e);
        }
    }

    public int unpackArrayHeader() throws IOException {
        byte readByte = readByte();
        if (Code.isFixedArray(readByte)) {
            return readByte & 15;
        }
        switch (readByte) {
            case (byte) -36:
                return readNextLength16();
            case (byte) -35:
                return readNextLength32();
            default:
                throw unexpected("Array", readByte);
        }
    }

    public int unpackMapHeader() throws IOException {
        byte readByte = readByte();
        if (Code.isFixedMap(readByte)) {
            return readByte & 15;
        }
        switch (readByte) {
            case (byte) -34:
                return readNextLength16();
            case (byte) -33:
                return readNextLength32();
            default:
                throw unexpected("Map", readByte);
        }
    }

    public ExtensionTypeHeader unpackExtensionTypeHeader() throws IOException {
        byte readByte = readByte();
        MessageBuffer prepareNumberBuffer;
        switch (readByte) {
            case (byte) -57:
                prepareNumberBuffer = prepareNumberBuffer(2);
                return new ExtensionTypeHeader(prepareNumberBuffer.getByte(this.nextReadPosition + 1), prepareNumberBuffer.getByte(this.nextReadPosition) & 255);
            case (byte) -56:
                prepareNumberBuffer = prepareNumberBuffer(3);
                return new ExtensionTypeHeader(prepareNumberBuffer.getByte(this.nextReadPosition + 2), prepareNumberBuffer.getShort(this.nextReadPosition) & SupportMenu.USER_MASK);
            case (byte) -55:
                prepareNumberBuffer = prepareNumberBuffer(5);
                int i = prepareNumberBuffer.getInt(this.nextReadPosition);
                if (i >= 0) {
                    return new ExtensionTypeHeader(prepareNumberBuffer.getByte(this.nextReadPosition + 4), i);
                }
                throw overflowU32Size(i);
            case (byte) -44:
                return new ExtensionTypeHeader(readByte(), 1);
            case (byte) -43:
                return new ExtensionTypeHeader(readByte(), 2);
            case (byte) -42:
                return new ExtensionTypeHeader(readByte(), 4);
            case (byte) -41:
                return new ExtensionTypeHeader(readByte(), 8);
            case (byte) -40:
                return new ExtensionTypeHeader(readByte(), 16);
            default:
                throw unexpected("Ext", readByte);
        }
    }

    private int tryReadStringHeader(byte b) throws IOException {
        switch (b) {
            case (byte) -39:
                return readNextLength8();
            case (byte) -38:
                return readNextLength16();
            case (byte) -37:
                return readNextLength32();
            default:
                return -1;
        }
    }

    private int tryReadBinaryHeader(byte b) throws IOException {
        switch (b) {
            case (byte) -60:
                return readNextLength8();
            case (byte) -59:
                return readNextLength16();
            case (byte) -58:
                return readNextLength32();
            default:
                return -1;
        }
    }

    public int unpackRawStringHeader() throws IOException {
        byte readByte = readByte();
        if (Code.isFixedRaw(readByte)) {
            return readByte & 31;
        }
        int tryReadStringHeader = tryReadStringHeader(readByte);
        if (tryReadStringHeader >= 0) {
            return tryReadStringHeader;
        }
        if (this.allowReadingBinaryAsString) {
            tryReadStringHeader = tryReadBinaryHeader(readByte);
            if (tryReadStringHeader >= 0) {
                return tryReadStringHeader;
            }
        }
        throw unexpected("String", readByte);
    }

    public int unpackBinaryHeader() throws IOException {
        byte readByte = readByte();
        if (Code.isFixedRaw(readByte)) {
            return readByte & 31;
        }
        int tryReadBinaryHeader = tryReadBinaryHeader(readByte);
        if (tryReadBinaryHeader >= 0) {
            return tryReadBinaryHeader;
        }
        if (this.allowReadingStringAsBinary) {
            tryReadBinaryHeader = tryReadStringHeader(readByte);
            if (tryReadBinaryHeader >= 0) {
                return tryReadBinaryHeader;
            }
        }
        throw unexpected("Binary", readByte);
    }

    private void skipPayload(int i) throws IOException {
        while (true) {
            int size = this.buffer.size() - this.position;
            if (size >= i) {
                this.position += i;
                return;
            }
            this.position += size;
            i -= size;
            nextBuffer();
        }
    }

    public void readPayload(ByteBuffer byteBuffer) throws IOException {
        while (true) {
            int remaining = byteBuffer.remaining();
            int size = this.buffer.size() - this.position;
            if (size >= remaining) {
                this.buffer.getBytes(this.position, remaining, byteBuffer);
                this.position = remaining + this.position;
                return;
            }
            this.buffer.getBytes(this.position, size, byteBuffer);
            this.position += size;
            nextBuffer();
        }
    }

    public void readPayload(byte[] bArr) throws IOException {
        readPayload(bArr, 0, bArr.length);
    }

    public byte[] readPayload(int i) throws IOException {
        byte[] bArr = new byte[i];
        readPayload(bArr);
        return bArr;
    }

    public void readPayload(byte[] bArr, int i, int i2) throws IOException {
        readPayload(ByteBuffer.wrap(bArr, i, i2));
    }

    public MessageBuffer readPayloadAsReference(int i) throws IOException {
        if (this.buffer.size() - this.position >= i) {
            MessageBuffer slice = this.buffer.slice(this.position, i);
            this.position += i;
            return slice;
        }
        slice = MessageBuffer.allocate(i);
        readPayload(slice.sliceAsByteBuffer());
        return slice;
    }

    private int readNextLength8() throws IOException {
        return readByte() & 255;
    }

    private int readNextLength16() throws IOException {
        return readShort() & SupportMenu.USER_MASK;
    }

    private int readNextLength32() throws IOException {
        int readInt = readInt();
        if (readInt >= 0) {
            return readInt;
        }
        throw overflowU32Size(readInt);
    }

    public void close() throws IOException {
        this.buffer = EMPTY_BUFFER;
        this.position = 0;
        this.in.close();
    }

    private static MessageIntegerOverflowException overflowU8(byte b) {
        return new MessageIntegerOverflowException(BigInteger.valueOf((long) (b & 255)));
    }

    private static MessageIntegerOverflowException overflowU16(short s) {
        return new MessageIntegerOverflowException(BigInteger.valueOf((long) (SupportMenu.USER_MASK & s)));
    }

    private static MessageIntegerOverflowException overflowU32(int i) {
        return new MessageIntegerOverflowException(BigInteger.valueOf(((long) (Integer.MAX_VALUE & i)) + k.MAX_AGE));
    }

    private static MessageIntegerOverflowException overflowU64(long j) {
        return new MessageIntegerOverflowException(BigInteger.valueOf((Long.MAX_VALUE + j) + 1).setBit(63));
    }

    private static MessageIntegerOverflowException overflowI16(short s) {
        return new MessageIntegerOverflowException(BigInteger.valueOf((long) s));
    }

    private static MessageIntegerOverflowException overflowI32(int i) {
        return new MessageIntegerOverflowException(BigInteger.valueOf((long) i));
    }

    private static MessageIntegerOverflowException overflowI64(long j) {
        return new MessageIntegerOverflowException(BigInteger.valueOf(j));
    }

    private static MessageSizeException overflowU32Size(int i) {
        return new MessageSizeException(((long) (Integer.MAX_VALUE & i)) + k.MAX_AGE);
    }
}
