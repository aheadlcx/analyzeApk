package com.squareup.wire;

import com.squareup.wire.Message.Builder;
import com.squareup.wire.WireField.Label;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public abstract class ProtoAdapter<E> {
    public static final ProtoAdapter<Boolean> BOOL = new d(FieldEncoding.VARINT, Boolean.class);
    public static final ProtoAdapter<ByteString> BYTES = new h(FieldEncoding.LENGTH_DELIMITED, ByteString.class);
    public static final ProtoAdapter<Double> DOUBLE = new f(FieldEncoding.FIXED64, Double.class);
    public static final ProtoAdapter<Integer> FIXED32 = new n(FieldEncoding.FIXED32, Integer.class);
    public static final ProtoAdapter<Long> FIXED64 = new r(FieldEncoding.FIXED64, Long.class);
    public static final ProtoAdapter<Float> FLOAT = new e(FieldEncoding.FIXED32, Float.class);
    public static final ProtoAdapter<Integer> INT32 = new k(FieldEncoding.VARINT, Integer.class);
    public static final ProtoAdapter<Long> INT64 = new o(FieldEncoding.VARINT, Long.class);
    public static final ProtoAdapter<Integer> SFIXED32 = FIXED32;
    public static final ProtoAdapter<Long> SFIXED64 = FIXED64;
    public static final ProtoAdapter<Integer> SINT32 = new m(FieldEncoding.VARINT, Integer.class);
    public static final ProtoAdapter<Long> SINT64 = new q(FieldEncoding.VARINT, Long.class);
    public static final ProtoAdapter<String> STRING = new g(FieldEncoding.LENGTH_DELIMITED, String.class);
    public static final ProtoAdapter<Integer> UINT32 = new l(FieldEncoding.VARINT, Integer.class);
    public static final ProtoAdapter<Long> UINT64 = new p(FieldEncoding.VARINT, Long.class);
    final Class<?> a;
    ProtoAdapter<List<E>> b;
    ProtoAdapter<List<E>> c;
    private final FieldEncoding d;

    public static final class EnumConstantNotFoundException extends IllegalArgumentException {
        public final int value;

        EnumConstantNotFoundException(int i, Class<?> cls) {
            super("Unknown enum tag " + i + " for " + cls.getCanonicalName());
            this.value = i;
        }
    }

    private static final class a<K, V> extends ProtoAdapter<Entry<K, V>> {
        final ProtoAdapter<K> d;
        final ProtoAdapter<V> e;

        a(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
            super(FieldEncoding.LENGTH_DELIMITED, null);
            this.d = protoAdapter;
            this.e = protoAdapter2;
        }

        public int encodedSize(Entry<K, V> entry) {
            return this.d.encodedSizeWithTag(1, entry.getKey()) + this.e.encodedSizeWithTag(2, entry.getValue());
        }

        public void encode(ProtoWriter protoWriter, Entry<K, V> entry) throws IOException {
            this.d.encodeWithTag(protoWriter, 1, entry.getKey());
            this.e.encodeWithTag(protoWriter, 2, entry.getValue());
        }

        public Entry<K, V> decode(ProtoReader protoReader) {
            throw new UnsupportedOperationException();
        }
    }

    private static final class b<K, V> extends ProtoAdapter<Map<K, V>> {
        private final a<K, V> d;

        b(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
            super(FieldEncoding.LENGTH_DELIMITED, null);
            this.d = new a(protoAdapter, protoAdapter2);
        }

        public int encodedSize(Map<K, V> map) {
            throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
        }

        public int encodedSizeWithTag(int i, Map<K, V> map) {
            int i2 = 0;
            for (Entry encodedSizeWithTag : map.entrySet()) {
                i2 = this.d.encodedSizeWithTag(i, encodedSizeWithTag) + i2;
            }
            return i2;
        }

        public void encode(ProtoWriter protoWriter, Map<K, V> map) {
            throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
        }

        public void encodeWithTag(ProtoWriter protoWriter, int i, Map<K, V> map) throws IOException {
            for (Entry encodeWithTag : map.entrySet()) {
                this.d.encodeWithTag(protoWriter, i, encodeWithTag);
            }
        }

        public Map<K, V> decode(ProtoReader protoReader) throws IOException {
            Object obj = null;
            long beginMessage = protoReader.beginMessage();
            Object obj2 = null;
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            obj2 = this.d.d.decode(protoReader);
                            break;
                        case 2:
                            obj = this.d.e.decode(protoReader);
                            break;
                        default:
                            break;
                    }
                }
                protoReader.endMessage(beginMessage);
                if (obj2 == null) {
                    throw new IllegalStateException("Map entry with null key");
                } else if (obj != null) {
                    return Collections.singletonMap(obj2, obj);
                } else {
                    throw new IllegalStateException("Map entry with null value");
                }
            }
        }

        public Map<K, V> redact(Map<K, V> map) {
            return Collections.emptyMap();
        }
    }

    public abstract E decode(ProtoReader protoReader) throws IOException;

    public abstract void encode(ProtoWriter protoWriter, E e) throws IOException;

    public abstract int encodedSize(E e);

    public ProtoAdapter(FieldEncoding fieldEncoding, Class<?> cls) {
        this.d = fieldEncoding;
        this.a = cls;
    }

    public static <M extends Message<M, B>, B extends Builder<M, B>> ProtoAdapter<M> newMessageAdapter(Class<M> cls) {
        return t.a(cls);
    }

    public static <E extends WireEnum> s<E> newEnumAdapter(Class<E> cls) {
        return new s(cls);
    }

    public static <K, V> ProtoAdapter<Map<K, V>> newMapAdapter(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
        return new b(protoAdapter, protoAdapter2);
    }

    public static <M extends Message> ProtoAdapter<M> get(M m) {
        return get(m.getClass());
    }

    public static <M> ProtoAdapter<M> get(Class<M> cls) {
        Throwable e;
        try {
            return (ProtoAdapter) cls.getField("ADAPTER").get(null);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new IllegalArgumentException("failed to access " + cls.getName() + "#ADAPTER", e);
        } catch (NoSuchFieldException e3) {
            e = e3;
            throw new IllegalArgumentException("failed to access " + cls.getName() + "#ADAPTER", e);
        }
    }

    public static ProtoAdapter<?> get(String str) {
        Throwable e;
        try {
            int indexOf = str.indexOf(35);
            String substring = str.substring(0, indexOf);
            return (ProtoAdapter) Class.forName(substring).getField(str.substring(indexOf + 1)).get(null);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new IllegalArgumentException("failed to access " + str, e);
        } catch (NoSuchFieldException e3) {
            e = e3;
            throw new IllegalArgumentException("failed to access " + str, e);
        } catch (ClassNotFoundException e4) {
            e = e4;
            throw new IllegalArgumentException("failed to access " + str, e);
        }
    }

    public E redact(E e) {
        return null;
    }

    public int encodedSizeWithTag(int i, E e) {
        if (e == null) {
            return 0;
        }
        int encodedSize = encodedSize(e);
        if (this.d == FieldEncoding.LENGTH_DELIMITED) {
            encodedSize += ProtoWriter.c(encodedSize);
        }
        return encodedSize + ProtoWriter.a(i);
    }

    public void encodeWithTag(ProtoWriter protoWriter, int i, E e) throws IOException {
        if (e != null) {
            protoWriter.writeTag(i, this.d);
            if (this.d == FieldEncoding.LENGTH_DELIMITED) {
                protoWriter.writeVarint32(encodedSize(e));
            }
            encode(protoWriter, (Object) e);
        }
    }

    public final void encode(BufferedSink bufferedSink, E e) throws IOException {
        c.a(e, "value == null");
        c.a(bufferedSink, "sink == null");
        encode(new ProtoWriter(bufferedSink), (Object) e);
    }

    public final byte[] encode(E e) {
        c.a(e, "value == null");
        BufferedSink buffer = new Buffer();
        try {
            encode(buffer, (Object) e);
            return buffer.readByteArray();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void encode(OutputStream outputStream, E e) throws IOException {
        c.a(e, "value == null");
        c.a(outputStream, "stream == null");
        BufferedSink buffer = Okio.buffer(Okio.sink(outputStream));
        encode(buffer, (Object) e);
        buffer.emit();
    }

    public final E decode(byte[] bArr) throws IOException {
        c.a(bArr, "bytes == null");
        return decode(new Buffer().write(bArr));
    }

    public final E decode(ByteString byteString) throws IOException {
        c.a(byteString, "bytes == null");
        return decode(new Buffer().write(byteString));
    }

    public final E decode(InputStream inputStream) throws IOException {
        c.a(inputStream, "stream == null");
        return decode(Okio.buffer(Okio.source(inputStream)));
    }

    public final E decode(BufferedSource bufferedSource) throws IOException {
        c.a(bufferedSource, "source == null");
        return decode(new ProtoReader(bufferedSource));
    }

    public String toString(E e) {
        return e.toString();
    }

    ProtoAdapter<?> a(Label label) {
        if (!label.a()) {
            return this;
        }
        if (label.b()) {
            return asPacked();
        }
        return asRepeated();
    }

    public final ProtoAdapter<List<E>> asPacked() {
        ProtoAdapter<List<E>> protoAdapter = this.b;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        protoAdapter = a();
        this.b = protoAdapter;
        return protoAdapter;
    }

    public final ProtoAdapter<List<E>> asRepeated() {
        ProtoAdapter<List<E>> protoAdapter = this.c;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        protoAdapter = b();
        this.c = protoAdapter;
        return protoAdapter;
    }

    private ProtoAdapter<List<E>> a() {
        if (this.d != FieldEncoding.LENGTH_DELIMITED) {
            return new i(this, FieldEncoding.LENGTH_DELIMITED, List.class);
        }
        throw new IllegalArgumentException("Unable to pack a length-delimited type.");
    }

    private ProtoAdapter<List<E>> b() {
        return new j(this, this.d, List.class);
    }
}
