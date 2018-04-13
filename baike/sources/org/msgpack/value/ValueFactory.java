package org.msgpack.value;

import java.math.BigInteger;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.msgpack.value.impl.ImmutableArrayValueImpl;
import org.msgpack.value.impl.ImmutableBigIntegerValueImpl;
import org.msgpack.value.impl.ImmutableBinaryValueImpl;
import org.msgpack.value.impl.ImmutableBooleanValueImpl;
import org.msgpack.value.impl.ImmutableDoubleValueImpl;
import org.msgpack.value.impl.ImmutableExtensionValueImpl;
import org.msgpack.value.impl.ImmutableLongValueImpl;
import org.msgpack.value.impl.ImmutableMapValueImpl;
import org.msgpack.value.impl.ImmutableNilValueImpl;
import org.msgpack.value.impl.ImmutableStringValueImpl;

public final class ValueFactory {

    public static class MapBuilder {
        private final Map<Value, Value> map = new LinkedHashMap();

        public MapValue build() {
            return ValueFactory.newMap(this.map);
        }

        public MapBuilder put(Entry<? extends Value, ? extends Value> entry) {
            put((Value) entry.getKey(), (Value) entry.getValue());
            return this;
        }

        public MapBuilder put(Value value, Value value2) {
            this.map.put(value, value2);
            return this;
        }

        public MapBuilder putAll(Iterable<? extends Entry<? extends Value, ? extends Value>> iterable) {
            for (Entry entry : iterable) {
                put((Value) entry.getKey(), (Value) entry.getValue());
            }
            return this;
        }

        public MapBuilder putAll(Map<? extends Value, ? extends Value> map) {
            for (Entry put : map.entrySet()) {
                put(put);
            }
            return this;
        }
    }

    private ValueFactory() {
    }

    public static ImmutableNilValue newNil() {
        return ImmutableNilValueImpl.get();
    }

    public static ImmutableBooleanValue newBoolean(boolean z) {
        return z ? ImmutableBooleanValueImpl.TRUE : ImmutableBooleanValueImpl.FALSE;
    }

    public static ImmutableIntegerValue newInteger(byte b) {
        return new ImmutableLongValueImpl((long) b);
    }

    public static ImmutableIntegerValue newInteger(short s) {
        return new ImmutableLongValueImpl((long) s);
    }

    public static ImmutableIntegerValue newInteger(int i) {
        return new ImmutableLongValueImpl((long) i);
    }

    public static ImmutableIntegerValue newInteger(long j) {
        return new ImmutableLongValueImpl(j);
    }

    public static ImmutableIntegerValue newInteger(BigInteger bigInteger) {
        return new ImmutableBigIntegerValueImpl(bigInteger);
    }

    public static ImmutableFloatValue newFloat(float f) {
        return new ImmutableDoubleValueImpl((double) f);
    }

    public static ImmutableFloatValue newFloat(double d) {
        return new ImmutableDoubleValueImpl(d);
    }

    public static ImmutableBinaryValue newBinary(byte[] bArr) {
        return newBinary(bArr, false);
    }

    public static ImmutableBinaryValue newBinary(byte[] bArr, boolean z) {
        if (z) {
            return new ImmutableBinaryValueImpl(bArr);
        }
        return new ImmutableBinaryValueImpl(Arrays.copyOf(bArr, bArr.length));
    }

    public static ImmutableBinaryValue newBinary(byte[] bArr, int i, int i2) {
        return newBinary(bArr, i, i2, false);
    }

    public static ImmutableBinaryValue newBinary(byte[] bArr, int i, int i2, boolean z) {
        if (z && i == 0 && i2 == bArr.length) {
            return new ImmutableBinaryValueImpl(bArr);
        }
        return new ImmutableBinaryValueImpl(Arrays.copyOfRange(bArr, i, i2));
    }

    public static ImmutableStringValue newString(String str) {
        return new ImmutableStringValueImpl(str);
    }

    public static ImmutableStringValue newString(byte[] bArr) {
        return new ImmutableStringValueImpl(bArr);
    }

    public static ImmutableStringValue newString(byte[] bArr, boolean z) {
        if (z) {
            return new ImmutableStringValueImpl(bArr);
        }
        return new ImmutableStringValueImpl(Arrays.copyOf(bArr, bArr.length));
    }

    public static ImmutableStringValue newString(byte[] bArr, int i, int i2) {
        return newString(bArr, i, i2, false);
    }

    public static ImmutableStringValue newString(byte[] bArr, int i, int i2, boolean z) {
        if (z && i == 0 && i2 == bArr.length) {
            return new ImmutableStringValueImpl(bArr);
        }
        return new ImmutableStringValueImpl(Arrays.copyOfRange(bArr, i, i2));
    }

    public static ImmutableArrayValue newArray(List<? extends Value> list) {
        if (list.isEmpty()) {
            return ImmutableArrayValueImpl.empty();
        }
        return new ImmutableArrayValueImpl((Value[]) list.toArray(new Value[list.size()]));
    }

    public static ImmutableArrayValue newArray(Value... valueArr) {
        if (valueArr.length == 0) {
            return ImmutableArrayValueImpl.empty();
        }
        return new ImmutableArrayValueImpl((Value[]) Arrays.copyOf(valueArr, valueArr.length));
    }

    public static ImmutableArrayValue newArray(Value[] valueArr, boolean z) {
        if (valueArr.length == 0) {
            return ImmutableArrayValueImpl.empty();
        }
        if (z) {
            return new ImmutableArrayValueImpl(valueArr);
        }
        return new ImmutableArrayValueImpl((Value[]) Arrays.copyOf(valueArr, valueArr.length));
    }

    public static ImmutableArrayValue emptyArray() {
        return ImmutableArrayValueImpl.empty();
    }

    public static <K extends Value, V extends Value> ImmutableMapValue newMap(Map<K, V> map) {
        Value[] valueArr = new Value[(map.size() * 2)];
        int i = 0;
        for (Entry entry : map.entrySet()) {
            valueArr[i] = (Value) entry.getKey();
            int i2 = i + 1;
            valueArr[i2] = (Value) entry.getValue();
            i = i2 + 1;
        }
        return new ImmutableMapValueImpl(valueArr);
    }

    public static ImmutableMapValue newMap(Value... valueArr) {
        if (valueArr.length == 0) {
            return ImmutableMapValueImpl.empty();
        }
        return new ImmutableMapValueImpl((Value[]) Arrays.copyOf(valueArr, valueArr.length));
    }

    public static ImmutableMapValue newMap(Value[] valueArr, boolean z) {
        if (valueArr.length == 0) {
            return ImmutableMapValueImpl.empty();
        }
        if (z) {
            return new ImmutableMapValueImpl(valueArr);
        }
        return new ImmutableMapValueImpl((Value[]) Arrays.copyOf(valueArr, valueArr.length));
    }

    public static ImmutableMapValue emptyMap() {
        return ImmutableMapValueImpl.empty();
    }

    public static MapValue newMap(Entry<? extends Value, ? extends Value>... entryArr) {
        Value[] valueArr = new Value[(entryArr.length * 2)];
        for (int i = 0; i < entryArr.length; i += 2) {
            valueArr[i * 2] = (Value) entryArr[i].getKey();
            valueArr[(i * 2) + 1] = (Value) entryArr[i].getValue();
        }
        return newMap(valueArr, true);
    }

    public static MapBuilder newMapBuilder() {
        return new MapBuilder();
    }

    public static Entry<Value, Value> newMapEntry(Value value, Value value2) {
        return new SimpleEntry(value, value2);
    }

    public static ImmutableExtensionValue newExtension(byte b, byte[] bArr) {
        return new ImmutableExtensionValueImpl(b, bArr);
    }
}
