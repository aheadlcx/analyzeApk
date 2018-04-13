package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.msgpack.core.MessagePack.PackerConfig;
import org.msgpack.core.MessagePacker;
import org.msgpack.core.buffer.MessageBufferOutput;
import org.msgpack.core.buffer.OutputStreamBufferOutput;

public class MessagePackGenerator extends GeneratorBase {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static ThreadLocal<OutputStreamBufferOutput> messageBufferOutputHolder = new ThreadLocal();
    private final MessagePacker messagePacker;
    private final OutputStream output;
    private final PackerConfig packerConfig;
    private StackItem rootStackItem;
    private LinkedList<StackItem> stack;

    private static abstract class StackItem {
        protected List<Object> objectKeys;
        protected List<Object> objectValues;

        abstract void addKey(Object obj);

        abstract List<Object> getKeys();

        private StackItem() {
            this.objectKeys = new ArrayList();
            this.objectValues = new ArrayList();
        }

        void addValue(Object obj) {
            this.objectValues.add(obj);
        }

        List<Object> getValues() {
            return this.objectValues;
        }
    }

    private static class StackItemForArray extends StackItem {
        private StackItemForArray() {
            super();
        }

        void addKey(Object obj) {
            throw new IllegalStateException("This method shouldn't be called");
        }

        List<Object> getKeys() {
            throw new IllegalStateException("This method shouldn't be called");
        }
    }

    private static class StackItemForObject extends StackItem {
        private StackItemForObject() {
            super();
        }

        void addKey(Object obj) {
            this.objectKeys.add(obj);
        }

        List<Object> getKeys() {
            return this.objectKeys;
        }
    }

    public MessagePackGenerator(int i, ObjectCodec objectCodec, OutputStream outputStream, PackerConfig packerConfig, boolean z) throws IOException {
        MessageBufferOutput messageBufferOutput;
        super(i, objectCodec);
        this.output = outputStream;
        if (z) {
            messageBufferOutput = (OutputStreamBufferOutput) messageBufferOutputHolder.get();
            if (messageBufferOutput == null) {
                messageBufferOutput = new OutputStreamBufferOutput(outputStream);
                messageBufferOutputHolder.set(messageBufferOutput);
            } else {
                messageBufferOutput.reset(outputStream);
            }
        } else {
            messageBufferOutput = new OutputStreamBufferOutput(outputStream);
        }
        this.messagePacker = packerConfig.newPacker(messageBufferOutput);
        this.packerConfig = packerConfig;
        this.stack = new LinkedList();
    }

    public void writeStartArray() throws IOException, JsonGenerationException {
        this._writeContext = this._writeContext.createChildArrayContext();
        this.stack.push(new StackItemForArray());
    }

    public void writeEndArray() throws IOException, JsonGenerationException {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not an array but " + this._writeContext.getTypeDesc());
        }
        getStackTopForArray();
        this._writeContext = this._writeContext.getParent();
        popStackAndStoreTheItemAsValue();
    }

    public void writeStartObject() throws IOException, JsonGenerationException {
        this._writeContext = this._writeContext.createChildObjectContext();
        this.stack.push(new StackItemForObject());
    }

    public void writeEndObject() throws IOException, JsonGenerationException {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not an object but " + this._writeContext.getTypeDesc());
        }
        StackItemForObject stackTopForObject = getStackTopForObject();
        if (stackTopForObject.getKeys().size() != stackTopForObject.getValues().size()) {
            throw new IllegalStateException(String.format("objectKeys.size() and objectValues.size() is not same: depth=%d, key=%d, value=%d", new Object[]{Integer.valueOf(this.stack.size()), Integer.valueOf(stackTopForObject.getKeys().size()), Integer.valueOf(stackTopForObject.getValues().size())}));
        }
        this._writeContext = this._writeContext.getParent();
        popStackAndStoreTheItemAsValue();
    }

    private void pack(Object obj) throws IOException {
        MessagePacker messagePacker = getMessagePacker();
        if (obj == null) {
            messagePacker.packNil();
        } else if (obj instanceof Integer) {
            messagePacker.packInt(((Integer) obj).intValue());
        } else if (obj instanceof ByteBuffer) {
            ByteBuffer byteBuffer = (ByteBuffer) obj;
            int remaining = byteBuffer.remaining();
            if (byteBuffer.hasArray()) {
                messagePacker.packBinaryHeader(remaining);
                messagePacker.writePayload(byteBuffer.array(), byteBuffer.arrayOffset(), remaining);
                return;
            }
            byte[] bArr = new byte[remaining];
            byteBuffer.get(bArr);
            messagePacker.packBinaryHeader(remaining);
            messagePacker.addPayload(bArr);
        } else if (obj instanceof String) {
            messagePacker.packString((String) obj);
        } else if (obj instanceof Float) {
            messagePacker.packFloat(((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            messagePacker.packLong(((Long) obj).longValue());
        } else if (obj instanceof StackItemForObject) {
            packObject((StackItemForObject) obj);
        } else if (obj instanceof StackItemForArray) {
            packArray((StackItemForArray) obj);
        } else if (obj instanceof Double) {
            messagePacker.packDouble(((Double) obj).doubleValue());
        } else if (obj instanceof BigInteger) {
            messagePacker.packBigInteger((BigInteger) obj);
        } else if (obj instanceof BigDecimal) {
            packBigDecimal((BigDecimal) obj);
        } else if (obj instanceof Boolean) {
            messagePacker.packBoolean(((Boolean) obj).booleanValue());
        } else if (obj instanceof MessagePackExtensionType) {
            MessagePackExtensionType messagePackExtensionType = (MessagePackExtensionType) obj;
            byte[] data = messagePackExtensionType.getData();
            messagePacker.packExtensionTypeHeader(messagePackExtensionType.getType(), data.length);
            messagePacker.writePayload(data);
        } else {
            messagePacker.flush();
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            getCodec().writeValue(new MessagePackGenerator(getFeatureMask(), getCodec(), byteArrayOutputStream, this.packerConfig, false), obj);
            this.output.write(byteArrayOutputStream.toByteArray());
        }
    }

    private void packBigDecimal(BigDecimal bigDecimal) throws IOException {
        int i;
        MessagePacker messagePacker = getMessagePacker();
        Object obj = null;
        try {
            messagePacker.packBigInteger(bigDecimal.toBigIntegerExact());
        } catch (ArithmeticException e) {
            i = 1;
        } catch (IllegalArgumentException e2) {
            i = 1;
        }
        if (obj != null) {
            double doubleValue = bigDecimal.doubleValue();
            if (bigDecimal.stripTrailingZeros().toEngineeringString().equals(BigDecimal.valueOf(doubleValue).toEngineeringString())) {
                messagePacker.packDouble(doubleValue);
                return;
            }
            throw new IllegalArgumentException("MessagePack cannot serialize a BigDecimal that can't be represented as double. " + bigDecimal);
        }
    }

    private void packObject(StackItemForObject stackItemForObject) throws IOException {
        List keys = stackItemForObject.getKeys();
        List values = stackItemForObject.getValues();
        getMessagePacker().packMapHeader(keys.size());
        for (int i = 0; i < keys.size(); i++) {
            pack(keys.get(i));
            pack(values.get(i));
        }
    }

    private void packArray(StackItemForArray stackItemForArray) throws IOException {
        List values = stackItemForArray.getValues();
        getMessagePacker().packArrayHeader(values.size());
        for (int i = 0; i < values.size(); i++) {
            pack(values.get(i));
        }
    }

    public void writeFieldName(String str) throws IOException, JsonGenerationException {
        addKeyToStackTop(str);
    }

    public void writeFieldName(SerializableString serializableString) throws IOException {
        if (serializableString instanceof MessagePackSerializedString) {
            addKeyToStackTop(((MessagePackSerializedString) serializableString).getRawValue());
        } else if (serializableString instanceof SerializedString) {
            addKeyToStackTop(serializableString.getValue());
        } else {
            System.out.println(serializableString.getClass());
            throw new IllegalArgumentException("Unsupported key: " + serializableString);
        }
    }

    public void writeString(String str) throws IOException, JsonGenerationException {
        addValueToStackTop(str);
    }

    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        addValueToStackTop(new String(cArr, i, i2));
    }

    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        addValueToStackTop(new String(bArr, i, i2, DEFAULT_CHARSET));
    }

    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        addValueToStackTop(new String(bArr, i, i2, DEFAULT_CHARSET));
    }

    public void writeRaw(String str) throws IOException, JsonGenerationException {
        addValueToStackTop(str);
    }

    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        addValueToStackTop(str.substring(0, i2));
    }

    public void writeRaw(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        addValueToStackTop(new String(cArr, i, i2));
    }

    public void writeRaw(char c) throws IOException, JsonGenerationException {
        addValueToStackTop(String.valueOf(c));
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        addValueToStackTop(ByteBuffer.wrap(bArr, i, i2));
    }

    public void writeNumber(int i) throws IOException, JsonGenerationException {
        addValueToStackTop(Integer.valueOf(i));
    }

    public void writeNumber(long j) throws IOException, JsonGenerationException {
        addValueToStackTop(Long.valueOf(j));
    }

    public void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException {
        addValueToStackTop(bigInteger);
    }

    public void writeNumber(double d) throws IOException, JsonGenerationException {
        addValueToStackTop(Double.valueOf(d));
    }

    public void writeNumber(float f) throws IOException, JsonGenerationException {
        addValueToStackTop(Float.valueOf(f));
    }

    public void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        addValueToStackTop(bigDecimal);
    }

    public void writeNumber(String str) throws IOException, JsonGenerationException, UnsupportedOperationException {
        throw new UnsupportedOperationException("writeNumber(String encodedValue) isn't supported yet");
    }

    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        addValueToStackTop(Boolean.valueOf(z));
    }

    public void writeNull() throws IOException, JsonGenerationException {
        addValueToStackTop(null);
    }

    public void writeExtensionType(MessagePackExtensionType messagePackExtensionType) throws IOException {
        addValueToStackTop(messagePackExtensionType);
    }

    public void close() throws IOException {
        try {
            flush();
        } finally {
            if (isEnabled(Feature.AUTO_CLOSE_TARGET)) {
                getMessagePacker().close();
            }
        }
    }

    public void flush() throws IOException {
        if (this.rootStackItem != null) {
            if (this.rootStackItem instanceof StackItemForObject) {
                packObject((StackItemForObject) this.rootStackItem);
            } else if (this.rootStackItem instanceof StackItemForArray) {
                packArray((StackItemForArray) this.rootStackItem);
            } else {
                throw new IllegalStateException("Unexpected rootStackItem: " + this.rootStackItem);
            }
            this.rootStackItem = null;
            flushMessagePacker();
        }
    }

    private void flushMessagePacker() throws IOException {
        getMessagePacker().flush();
    }

    protected void _releaseBuffers() {
    }

    protected void _verifyValueWrite(String str) throws IOException, JsonGenerationException {
        if (this._writeContext.writeValue() == 5) {
            _reportError("Can not " + str + ", expecting field name");
        }
    }

    private StackItem getStackTop() {
        if (!this.stack.isEmpty()) {
            return (StackItem) this.stack.getFirst();
        }
        throw new IllegalStateException("The stack is empty");
    }

    private StackItemForObject getStackTopForObject() {
        StackItem stackTop = getStackTop();
        if (stackTop instanceof StackItemForObject) {
            return (StackItemForObject) stackTop;
        }
        throw new IllegalStateException("The stack top should be Object: " + stackTop);
    }

    private StackItemForArray getStackTopForArray() {
        StackItem stackTop = getStackTop();
        if (stackTop instanceof StackItemForArray) {
            return (StackItemForArray) stackTop;
        }
        throw new IllegalStateException("The stack top should be Array: " + stackTop);
    }

    private void addKeyToStackTop(Object obj) {
        getStackTop().addKey(obj);
    }

    private void addValueToStackTop(Object obj) throws IOException {
        if (this.stack.isEmpty()) {
            pack(obj);
            flushMessagePacker();
            return;
        }
        getStackTop().addValue(obj);
    }

    private void popStackAndStoreTheItemAsValue() throws IOException {
        StackItem stackItem = (StackItem) this.stack.pop();
        if (this.stack.size() > 0) {
            addValueToStackTop(stackItem);
        } else if (this.rootStackItem != null) {
            throw new IllegalStateException("rootStackItem is not null");
        } else {
            this.rootStackItem = stackItem;
        }
    }

    private MessagePacker getMessagePacker() {
        return this.messagePacker;
    }
}
