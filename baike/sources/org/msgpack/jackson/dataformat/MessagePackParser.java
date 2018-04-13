package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonReadContext;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import org.msgpack.core.ExtensionTypeHeader;
import org.msgpack.core.MessageFormat;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;
import org.msgpack.core.Preconditions;
import org.msgpack.core.buffer.ArrayBufferInput;
import org.msgpack.core.buffer.InputStreamBufferInput;
import org.msgpack.core.buffer.MessageBufferInput;
import org.msgpack.jackson.dataformat.ExtensionTypeCustomDeserializers.Deser;

public class MessagePackParser extends ParserMinimalBase {
    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);
    private static final ThreadLocal<Tuple<Object, MessageUnpacker>> messageUnpackerHolder = new ThreadLocal();
    private BigInteger biValue;
    private byte[] bytesValue;
    private ObjectCodec codec;
    private long currentPosition;
    private double doubleValue;
    private ExtensionTypeCustomDeserializers extTypeCustomDesers;
    private MessagePackExtensionType extensionTypeValue;
    private int intValue;
    private final IOContext ioContext;
    private boolean isClosed;
    private long longValue;
    private final MessageUnpacker messageUnpacker;
    private JsonReadContext parsingContext;
    private boolean reuseResourceInParser;
    private final LinkedList<StackItem> stack;
    private String stringValue;
    private long tokenPosition;
    private Type type;

    private static abstract class StackItem {
        private long numOfElements;

        protected StackItem(long j) {
            this.numOfElements = j;
        }

        public void consume() {
            this.numOfElements--;
        }

        public boolean isEmpty() {
            return this.numOfElements == 0;
        }
    }

    private static class StackItemForArray extends StackItem {
        StackItemForArray(long j) {
            super(j);
        }
    }

    private static class StackItemForObject extends StackItem {
        StackItemForObject(long j) {
            super(j);
        }
    }

    private enum Type {
        INT,
        LONG,
        DOUBLE,
        STRING,
        BYTES,
        BIG_INT,
        EXT
    }

    public MessagePackParser(IOContext iOContext, int i, ObjectCodec objectCodec, InputStream inputStream) throws IOException {
        this(iOContext, i, objectCodec, inputStream, true);
    }

    public MessagePackParser(IOContext iOContext, int i, ObjectCodec objectCodec, InputStream inputStream, boolean z) throws IOException {
        this(iOContext, i, new InputStreamBufferInput(inputStream), objectCodec, inputStream, z);
    }

    public MessagePackParser(IOContext iOContext, int i, ObjectCodec objectCodec, byte[] bArr) throws IOException {
        this(iOContext, i, objectCodec, bArr, true);
    }

    public MessagePackParser(IOContext iOContext, int i, ObjectCodec objectCodec, byte[] bArr, boolean z) throws IOException {
        this(iOContext, i, new ArrayBufferInput(bArr), objectCodec, bArr, z);
    }

    private MessagePackParser(IOContext iOContext, int i, MessageBufferInput messageBufferInput, ObjectCodec objectCodec, Object obj, boolean z) throws IOException {
        super(i);
        this.stack = new LinkedList();
        this.codec = objectCodec;
        this.ioContext = iOContext;
        this.parsingContext = JsonReadContext.createRootContext(Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i) ? DupDetector.rootDetector(this) : null);
        this.reuseResourceInParser = z;
        if (z) {
            Object newDefaultUnpacker;
            this.messageUnpacker = null;
            Tuple tuple = (Tuple) messageUnpackerHolder.get();
            if (tuple == null) {
                newDefaultUnpacker = MessagePack.newDefaultUnpacker(messageBufferInput);
            } else {
                if (isEnabled(Feature.AUTO_CLOSE_SOURCE) || tuple.first() != obj) {
                    ((MessageUnpacker) tuple.second()).reset(messageBufferInput);
                }
                MessageUnpacker messageUnpacker = (MessageUnpacker) tuple.second();
            }
            messageUnpackerHolder.set(new Tuple(obj, newDefaultUnpacker));
            return;
        }
        this.messageUnpacker = MessagePack.newDefaultUnpacker(messageBufferInput);
    }

    public void setExtensionTypeCustomDeserializers(ExtensionTypeCustomDeserializers extensionTypeCustomDeserializers) {
        this.extTypeCustomDesers = extensionTypeCustomDeserializers;
    }

    public ObjectCodec getCodec() {
        return this.codec;
    }

    public void setCodec(ObjectCodec objectCodec) {
        this.codec = objectCodec;
    }

    public Version version() {
        return null;
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        Object obj = null;
        MessageUnpacker messageUnpacker = getMessageUnpacker();
        this.tokenPosition = messageUnpacker.getTotalReadBytes();
        if ((this.parsingContext.inObject() || this.parsingContext.inArray()) && ((StackItem) this.stack.getFirst()).isEmpty()) {
            this.stack.pop();
            this._currToken = this.parsingContext.inObject() ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            this.parsingContext = this.parsingContext.getParent();
            return this._currToken;
        } else if (!messageUnpacker.hasNext()) {
            return null;
        } else {
            JsonToken jsonToken;
            MessageFormat nextFormat = messageUnpacker.getNextFormat();
            switch (messageUnpacker.getNextFormat().getValueType()) {
                case NIL:
                    messageUnpacker.unpackNil();
                    jsonToken = JsonToken.VALUE_NULL;
                    break;
                case BOOLEAN:
                    boolean unpackBoolean = messageUnpacker.unpackBoolean();
                    if (this.parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
                        this.parsingContext.setCurrentName(Boolean.toString(unpackBoolean));
                        jsonToken = JsonToken.FIELD_NAME;
                        break;
                    }
                    jsonToken = unpackBoolean ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
                    break;
                case INTEGER:
                    Object valueOf;
                    switch (nextFormat) {
                        case UINT64:
                            BigInteger unpackBigInteger = messageUnpacker.unpackBigInteger();
                            if (unpackBigInteger.compareTo(LONG_MIN) >= 0 && unpackBigInteger.compareTo(LONG_MAX) <= 0) {
                                this.type = Type.LONG;
                                this.longValue = unpackBigInteger.longValue();
                                valueOf = Long.valueOf(this.longValue);
                                break;
                            }
                            this.type = Type.BIG_INT;
                            this.biValue = unpackBigInteger;
                            valueOf = this.biValue;
                            break;
                        default:
                            long unpackLong = messageUnpacker.unpackLong();
                            if (-2147483648L <= unpackLong && unpackLong <= 2147483647L) {
                                this.type = Type.INT;
                                this.intValue = (int) unpackLong;
                                valueOf = Integer.valueOf(this.intValue);
                                break;
                            }
                            this.type = Type.LONG;
                            this.longValue = unpackLong;
                            valueOf = Long.valueOf(this.longValue);
                            break;
                            break;
                    }
                    if (this.parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
                        this.parsingContext.setCurrentName(String.valueOf(valueOf));
                        jsonToken = JsonToken.FIELD_NAME;
                        break;
                    }
                    jsonToken = JsonToken.VALUE_NUMBER_INT;
                    break;
                    break;
                case FLOAT:
                    this.type = Type.DOUBLE;
                    this.doubleValue = messageUnpacker.unpackDouble();
                    if (this.parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
                        this.parsingContext.setCurrentName(String.valueOf(this.doubleValue));
                        jsonToken = JsonToken.FIELD_NAME;
                        break;
                    }
                    jsonToken = JsonToken.VALUE_NUMBER_FLOAT;
                    break;
                    break;
                case STRING:
                    this.type = Type.STRING;
                    this.stringValue = messageUnpacker.unpackString();
                    if (this.parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
                        this.parsingContext.setCurrentName(this.stringValue);
                        jsonToken = JsonToken.FIELD_NAME;
                        break;
                    }
                    jsonToken = JsonToken.VALUE_STRING;
                    break;
                case BINARY:
                    this.type = Type.BYTES;
                    this.bytesValue = messageUnpacker.readPayload(messageUnpacker.unpackBinaryHeader());
                    if (this.parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
                        this.parsingContext.setCurrentName(new String(this.bytesValue, MessagePack.UTF8));
                        jsonToken = JsonToken.FIELD_NAME;
                        break;
                    }
                    jsonToken = JsonToken.VALUE_EMBEDDED_OBJECT;
                    break;
                case ARRAY:
                    jsonToken = null;
                    StackItemForArray stackItemForArray = new StackItemForArray((long) messageUnpacker.unpackArrayHeader());
                    break;
                case MAP:
                    jsonToken = null;
                    StackItemForObject stackItemForObject = new StackItemForObject((long) messageUnpacker.unpackMapHeader());
                    break;
                case EXTENSION:
                    this.type = Type.EXT;
                    ExtensionTypeHeader unpackExtensionTypeHeader = messageUnpacker.unpackExtensionTypeHeader();
                    this.extensionTypeValue = new MessagePackExtensionType(unpackExtensionTypeHeader.getType(), messageUnpacker.readPayload(unpackExtensionTypeHeader.getLength()));
                    jsonToken = JsonToken.VALUE_EMBEDDED_OBJECT;
                    break;
                default:
                    throw new IllegalStateException("Shouldn't reach here");
            }
            this.currentPosition = messageUnpacker.getTotalReadBytes();
            if ((this.parsingContext.inObject() && r2 != JsonToken.FIELD_NAME) || this.parsingContext.inArray()) {
                ((StackItem) this.stack.getFirst()).consume();
            }
            if (obj != null) {
                this.stack.push(obj);
                if (obj instanceof StackItemForArray) {
                    jsonToken = JsonToken.START_ARRAY;
                    this.parsingContext = this.parsingContext.createChildArrayContext(-1, -1);
                } else if (obj instanceof StackItemForObject) {
                    jsonToken = JsonToken.START_OBJECT;
                    this.parsingContext = this.parsingContext.createChildObjectContext(-1, -1);
                }
            }
            this._currToken = jsonToken;
            return jsonToken;
        }
    }

    protected void _handleEOF() throws JsonParseException {
    }

    public String getText() throws IOException, JsonParseException {
        switch (this.type) {
            case STRING:
                return this.stringValue;
            case BYTES:
                return new String(this.bytesValue, MessagePack.UTF8);
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public char[] getTextCharacters() throws IOException, JsonParseException {
        return getText().toCharArray();
    }

    public boolean hasTextCharacters() {
        return false;
    }

    public int getTextLength() throws IOException, JsonParseException {
        return getText().length();
    }

    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        Preconditions.checkArgument(this.type == Type.BYTES);
        return this.bytesValue;
    }

    public Number getNumberValue() throws IOException, JsonParseException {
        switch (this.type) {
            case INT:
                return Integer.valueOf(this.intValue);
            case LONG:
                return Long.valueOf(this.longValue);
            case DOUBLE:
                return Double.valueOf(this.doubleValue);
            case BIG_INT:
                return this.biValue;
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public int getIntValue() throws IOException, JsonParseException {
        switch (this.type) {
            case INT:
                return this.intValue;
            case LONG:
                return (int) this.longValue;
            case DOUBLE:
                return (int) this.doubleValue;
            case BIG_INT:
                return this.biValue.intValue();
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public long getLongValue() throws IOException, JsonParseException {
        switch (this.type) {
            case INT:
                return (long) this.intValue;
            case LONG:
                return this.longValue;
            case DOUBLE:
                return (long) this.doubleValue;
            case BIG_INT:
                return this.biValue.longValue();
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        switch (this.type) {
            case INT:
                return BigInteger.valueOf((long) this.intValue);
            case LONG:
                return BigInteger.valueOf(this.longValue);
            case DOUBLE:
                return BigInteger.valueOf((long) this.doubleValue);
            case BIG_INT:
                return this.biValue;
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public float getFloatValue() throws IOException, JsonParseException {
        switch (this.type) {
            case INT:
                return (float) this.intValue;
            case LONG:
                return (float) this.longValue;
            case DOUBLE:
                return (float) this.doubleValue;
            case BIG_INT:
                return this.biValue.floatValue();
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public double getDoubleValue() throws IOException, JsonParseException {
        switch (this.type) {
            case INT:
                return (double) this.intValue;
            case LONG:
                return (double) this.longValue;
            case DOUBLE:
                return this.doubleValue;
            case BIG_INT:
                return this.biValue.doubleValue();
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public BigDecimal getDecimalValue() throws IOException {
        switch (this.type) {
            case INT:
                return BigDecimal.valueOf((long) this.intValue);
            case LONG:
                return BigDecimal.valueOf(this.longValue);
            case DOUBLE:
                return BigDecimal.valueOf(this.doubleValue);
            case BIG_INT:
                return new BigDecimal(this.biValue);
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public Object getEmbeddedObject() throws IOException, JsonParseException {
        switch (this.type) {
            case BYTES:
                return this.bytesValue;
            case EXT:
                if (this.extTypeCustomDesers != null) {
                    Deser deser = this.extTypeCustomDesers.getDeser(this.extensionTypeValue.getType());
                    if (deser != null) {
                        return deser.deserialize(this.extensionTypeValue.getData());
                    }
                }
                return this.extensionTypeValue;
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public NumberType getNumberType() throws IOException, JsonParseException {
        switch (this.type) {
            case INT:
                return NumberType.INT;
            case LONG:
                return NumberType.LONG;
            case DOUBLE:
                return NumberType.DOUBLE;
            case BIG_INT:
                return NumberType.BIG_INTEGER;
            default:
                throw new IllegalStateException("Invalid type=" + this.type);
        }
    }

    public void close() throws IOException {
        try {
            if (isEnabled(Feature.AUTO_CLOSE_SOURCE)) {
                getMessageUnpacker().close();
            }
            this.isClosed = true;
        } catch (Throwable th) {
            this.isClosed = true;
        }
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public JsonStreamContext getParsingContext() {
        return this.parsingContext;
    }

    public JsonLocation getTokenLocation() {
        return new JsonLocation(this.ioContext.getSourceReference(), this.tokenPosition, -1, -1, (int) this.tokenPosition);
    }

    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this.ioContext.getSourceReference(), this.currentPosition, -1, -1, (int) this.currentPosition);
    }

    public void overrideCurrentName(String str) {
        try {
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this.parsingContext.getParent().setCurrentName(str);
            } else {
                this.parsingContext.setCurrentName(str);
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public String getCurrentName() throws IOException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            return this.parsingContext.getParent().getCurrentName();
        }
        return this.parsingContext.getCurrentName();
    }

    private MessageUnpacker getMessageUnpacker() {
        if (!this.reuseResourceInParser) {
            return this.messageUnpacker;
        }
        Tuple tuple = (Tuple) messageUnpackerHolder.get();
        if (tuple != null) {
            return (MessageUnpacker) tuple.second();
        }
        throw new IllegalStateException("messageUnpacker is null");
    }
}
