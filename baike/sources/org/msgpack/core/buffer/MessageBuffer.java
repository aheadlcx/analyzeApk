package org.msgpack.core.buffer;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.bugly.Bugly;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.msgpack.core.Preconditions;
import sun.misc.Unsafe;

public class MessageBuffer {
    static final /* synthetic */ boolean $assertionsDisabled;
    static final int ARRAY_BYTE_BASE_OFFSET;
    private static final String BIGENDIAN_MESSAGE_BUFFER = "org.msgpack.core.buffer.MessageBufferBE";
    private static final String DEFAULT_MESSAGE_BUFFER = "org.msgpack.core.buffer.MessageBuffer";
    private static final String UNIVERSAL_MESSAGE_BUFFER = "org.msgpack.core.buffer.MessageBufferU";
    static final boolean isUniversalBuffer;
    private static final Constructor<?> mbArrConstructor;
    private static final Constructor<?> mbBBConstructor;
    static final Unsafe unsafe;
    protected final long address;
    protected final Object base;
    protected final ByteBuffer reference;
    protected final int size;

    static {
        boolean z;
        String property;
        boolean z2;
        boolean z3;
        Unsafe unsafe;
        Exception exception;
        Class cls;
        Constructor declaredConstructor;
        Constructor declaredConstructor2;
        Throwable th;
        int i;
        Unsafe unsafe2 = null;
        boolean z4 = true;
        if (MessageBuffer.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
        int i2 = 16;
        try {
            boolean contains;
            Field declaredField;
            Unsafe unsafe3;
            int i3;
            property = System.getProperty("java.specification.version", "");
            int indexOf = property.indexOf(46);
            if (indexOf != -1) {
                try {
                    int parseInt = Integer.parseInt(property.substring(0, indexOf));
                    z = parseInt > 1 || (parseInt == 1 && Integer.parseInt(property.substring(indexOf + 1)) >= 7);
                } catch (NumberFormatException e) {
                    e.printStackTrace(System.err);
                }
                if (Class.forName("sun.misc.Unsafe") == null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2;
                contains = System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
                if (System.getProperty("com.google.appengine.runtime.version") == null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (Boolean.parseBoolean(System.getProperty("msgpack.universal-buffer", Bugly.SDK_IS_DEV)) && !contains && !r3 && r0 && r6) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (z3) {
                    try {
                        declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                        declaredField.setAccessible(true);
                        unsafe = (Unsafe) declaredField.get(null);
                        if (unsafe != null) {
                            try {
                                throw new RuntimeException("Unsafe is unavailable");
                            } catch (Exception e2) {
                                unsafe2 = unsafe;
                                exception = e2;
                                z2 = z3;
                                try {
                                    exception.printStackTrace(System.err);
                                    unsafe = unsafe2;
                                    ARRAY_BYTE_BASE_OFFSET = i2;
                                    isUniversalBuffer = true;
                                    if (isUniversalBuffer) {
                                        if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                            z4 = false;
                                        }
                                        property = z4 ? DEFAULT_MESSAGE_BUFFER : BIGENDIAN_MESSAGE_BUFFER;
                                    } else {
                                        property = UNIVERSAL_MESSAGE_BUFFER;
                                    }
                                    try {
                                        cls = Class.forName(property);
                                        declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                                        declaredConstructor.setAccessible(true);
                                        mbArrConstructor = declaredConstructor;
                                        declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                                        declaredConstructor2.setAccessible(true);
                                        mbBBConstructor = declaredConstructor2;
                                    } catch (Throwable e3) {
                                        e3.printStackTrace(System.err);
                                        throw new RuntimeException(e3);
                                    }
                                } catch (Throwable e32) {
                                    z3 = z2;
                                    th = e32;
                                    unsafe = unsafe2;
                                    ARRAY_BYTE_BASE_OFFSET = i2;
                                    isUniversalBuffer = z3;
                                    if (isUniversalBuffer) {
                                        if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                            z4 = false;
                                        }
                                        property = z4 ? DEFAULT_MESSAGE_BUFFER : BIGENDIAN_MESSAGE_BUFFER;
                                    } else {
                                        property = UNIVERSAL_MESSAGE_BUFFER;
                                    }
                                    try {
                                        cls = Class.forName(property);
                                        declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                                        declaredConstructor.setAccessible(true);
                                        mbArrConstructor = declaredConstructor;
                                        declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                                        declaredConstructor2.setAccessible(true);
                                        mbBBConstructor = declaredConstructor2;
                                        throw th;
                                    } catch (Throwable e322) {
                                        e322.printStackTrace(System.err);
                                        throw new RuntimeException(e322);
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                unsafe2 = unsafe;
                                unsafe = unsafe2;
                                ARRAY_BYTE_BASE_OFFSET = i2;
                                isUniversalBuffer = z3;
                                if (isUniversalBuffer) {
                                    property = UNIVERSAL_MESSAGE_BUFFER;
                                } else {
                                    if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                        z4 = false;
                                    }
                                    if (z4) {
                                    }
                                }
                                cls = Class.forName(property);
                                declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                                declaredConstructor.setAccessible(true);
                                mbArrConstructor = declaredConstructor;
                                declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                                declaredConstructor2.setAccessible(true);
                                mbBBConstructor = declaredConstructor2;
                                throw th;
                            }
                        }
                        indexOf = unsafe.arrayBaseOffset(byte[].class);
                        try {
                            i2 = unsafe.arrayIndexScale(byte[].class);
                            if (i2 == 1) {
                                throw new IllegalStateException("Byte array index scale must be 1, but is " + i2);
                            }
                            i = indexOf;
                            unsafe3 = unsafe;
                            i3 = i;
                        } catch (Exception e4) {
                            unsafe2 = unsafe;
                            exception = e4;
                            i2 = indexOf;
                            z2 = z3;
                            exception.printStackTrace(System.err);
                            unsafe = unsafe2;
                            ARRAY_BYTE_BASE_OFFSET = i2;
                            isUniversalBuffer = true;
                            if (isUniversalBuffer) {
                                property = UNIVERSAL_MESSAGE_BUFFER;
                            } else {
                                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                    z4 = false;
                                }
                                if (z4) {
                                }
                            }
                            cls = Class.forName(property);
                            declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                            declaredConstructor.setAccessible(true);
                            mbArrConstructor = declaredConstructor;
                            declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                            declaredConstructor2.setAccessible(true);
                            mbBBConstructor = declaredConstructor2;
                        } catch (Throwable th3) {
                            unsafe2 = unsafe;
                            i = indexOf;
                            th = th3;
                            i2 = i;
                            unsafe = unsafe2;
                            ARRAY_BYTE_BASE_OFFSET = i2;
                            isUniversalBuffer = z3;
                            if (isUniversalBuffer) {
                                property = UNIVERSAL_MESSAGE_BUFFER;
                            } else {
                                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                    z4 = false;
                                }
                                if (z4) {
                                }
                            }
                            cls = Class.forName(property);
                            declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                            declaredConstructor.setAccessible(true);
                            mbArrConstructor = declaredConstructor;
                            declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                            declaredConstructor2.setAccessible(true);
                            mbBBConstructor = declaredConstructor2;
                            throw th;
                        }
                    } catch (Exception e5) {
                        exception = e5;
                        z2 = z3;
                        exception.printStackTrace(System.err);
                        unsafe = unsafe2;
                        ARRAY_BYTE_BASE_OFFSET = i2;
                        isUniversalBuffer = true;
                        if (isUniversalBuffer) {
                            if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                z4 = false;
                            }
                            if (z4) {
                            }
                        } else {
                            property = UNIVERSAL_MESSAGE_BUFFER;
                        }
                        cls = Class.forName(property);
                        declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                        declaredConstructor.setAccessible(true);
                        mbArrConstructor = declaredConstructor;
                        declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                        declaredConstructor2.setAccessible(true);
                        mbBBConstructor = declaredConstructor2;
                    } catch (Throwable e3222) {
                        th = e3222;
                        unsafe = unsafe2;
                        ARRAY_BYTE_BASE_OFFSET = i2;
                        isUniversalBuffer = z3;
                        if (isUniversalBuffer) {
                            if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                z4 = false;
                            }
                            if (z4) {
                            }
                        } else {
                            property = UNIVERSAL_MESSAGE_BUFFER;
                        }
                        cls = Class.forName(property);
                        declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                        declaredConstructor.setAccessible(true);
                        mbArrConstructor = declaredConstructor;
                        declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                        declaredConstructor2.setAccessible(true);
                        mbBBConstructor = declaredConstructor2;
                        throw th;
                    }
                }
                i3 = 16;
                unsafe3 = null;
                unsafe = unsafe3;
                ARRAY_BYTE_BASE_OFFSET = i3;
                isUniversalBuffer = z3;
                if (isUniversalBuffer) {
                    if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                        z4 = false;
                    }
                    property = z4 ? DEFAULT_MESSAGE_BUFFER : BIGENDIAN_MESSAGE_BUFFER;
                } else {
                    property = UNIVERSAL_MESSAGE_BUFFER;
                }
                cls = Class.forName(property);
                declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                declaredConstructor.setAccessible(true);
                mbArrConstructor = declaredConstructor;
                declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                declaredConstructor2.setAccessible(true);
                mbBBConstructor = declaredConstructor2;
            }
            z = false;
            try {
                if (Class.forName("sun.misc.Unsafe") == null) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                z3 = z2;
            } catch (Exception e6) {
                z3 = false;
            } catch (Throwable e32222) {
                th = e32222;
                z3 = false;
                unsafe = unsafe2;
                ARRAY_BYTE_BASE_OFFSET = i2;
                isUniversalBuffer = z3;
                if (isUniversalBuffer) {
                    if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                        z4 = false;
                    }
                    if (z4) {
                    }
                } else {
                    property = UNIVERSAL_MESSAGE_BUFFER;
                }
                cls = Class.forName(property);
                declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                declaredConstructor.setAccessible(true);
                mbArrConstructor = declaredConstructor;
                declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                declaredConstructor2.setAccessible(true);
                mbBBConstructor = declaredConstructor2;
                throw th;
            }
            contains = System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
            if (System.getProperty("com.google.appengine.runtime.version") == null) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (Boolean.parseBoolean(System.getProperty("msgpack.universal-buffer", Bugly.SDK_IS_DEV))) {
            }
            z3 = true;
            if (z3) {
                i3 = 16;
                unsafe3 = null;
            } else {
                declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                unsafe = (Unsafe) declaredField.get(null);
                if (unsafe != null) {
                    indexOf = unsafe.arrayBaseOffset(byte[].class);
                    i2 = unsafe.arrayIndexScale(byte[].class);
                    if (i2 == 1) {
                        i = indexOf;
                        unsafe3 = unsafe;
                        i3 = i;
                    } else {
                        throw new IllegalStateException("Byte array index scale must be 1, but is " + i2);
                    }
                }
                throw new RuntimeException("Unsafe is unavailable");
            }
            unsafe = unsafe3;
            ARRAY_BYTE_BASE_OFFSET = i3;
            isUniversalBuffer = z3;
            if (isUniversalBuffer) {
                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                    z4 = false;
                }
                if (z4) {
                }
            } else {
                property = UNIVERSAL_MESSAGE_BUFFER;
            }
            try {
                cls = Class.forName(property);
                declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                declaredConstructor.setAccessible(true);
                mbArrConstructor = declaredConstructor;
                declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
                declaredConstructor2.setAccessible(true);
                mbBBConstructor = declaredConstructor2;
            } catch (Throwable e322222) {
                e322222.printStackTrace(System.err);
                throw new RuntimeException(e322222);
            }
        } catch (Exception e7) {
            exception = e7;
            z2 = false;
            exception.printStackTrace(System.err);
            unsafe = unsafe2;
            ARRAY_BYTE_BASE_OFFSET = i2;
            isUniversalBuffer = true;
            if (isUniversalBuffer) {
                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                    z4 = false;
                }
                if (z4) {
                }
            } else {
                property = UNIVERSAL_MESSAGE_BUFFER;
            }
            cls = Class.forName(property);
            declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            mbArrConstructor = declaredConstructor;
            declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
            declaredConstructor2.setAccessible(true);
            mbBBConstructor = declaredConstructor2;
        } catch (Throwable e3222222) {
            th = e3222222;
            z3 = false;
            unsafe = unsafe2;
            ARRAY_BYTE_BASE_OFFSET = i2;
            isUniversalBuffer = z3;
            if (isUniversalBuffer) {
                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                    z4 = false;
                }
                if (z4) {
                }
            } else {
                property = UNIVERSAL_MESSAGE_BUFFER;
            }
            cls = Class.forName(property);
            declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            mbArrConstructor = declaredConstructor;
            declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{ByteBuffer.class});
            declaredConstructor2.setAccessible(true);
            mbBBConstructor = declaredConstructor2;
            throw th;
        }
    }

    public static MessageBuffer allocate(int i) {
        if (i >= 0) {
            return wrap(new byte[i]);
        }
        throw new IllegalArgumentException("size must not be negative");
    }

    public static MessageBuffer wrap(byte[] bArr) {
        return newMessageBuffer(bArr, 0, bArr.length);
    }

    public static MessageBuffer wrap(byte[] bArr, int i, int i2) {
        return newMessageBuffer(bArr, i, i2);
    }

    public static MessageBuffer wrap(ByteBuffer byteBuffer) {
        return newMessageBuffer(byteBuffer);
    }

    private static MessageBuffer newMessageBuffer(byte[] bArr, int i, int i2) {
        Preconditions.checkNotNull(bArr);
        return newInstance(mbArrConstructor, bArr, Integer.valueOf(i), Integer.valueOf(i2));
    }

    private static MessageBuffer newMessageBuffer(ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer);
        return newInstance(mbBBConstructor, byteBuffer);
    }

    private static MessageBuffer newInstance(Constructor<?> constructor, Object... objArr) {
        try {
            return (MessageBuffer) constructor.newInstance(objArr);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        } catch (Throwable e2) {
            throw new IllegalStateException(e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e3.getCause());
            } else if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            } else {
                throw new IllegalStateException(e3.getCause());
            }
        }
    }

    public static void releaseBuffer(MessageBuffer messageBuffer) {
        if (!isUniversalBuffer && !messageBuffer.hasArray()) {
            if (DirectBufferAccess.isDirectByteBufferInstance(messageBuffer.reference)) {
                DirectBufferAccess.clean(messageBuffer.reference);
            } else {
                unsafe.freeMemory(messageBuffer.address);
            }
        }
    }

    MessageBuffer(byte[] bArr, int i, int i2) {
        this.base = bArr;
        this.address = (long) (ARRAY_BYTE_BASE_OFFSET + i);
        this.size = i2;
        this.reference = null;
    }

    MessageBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
            if (isUniversalBuffer) {
                throw new UnsupportedOperationException("Cannot create MessageBuffer from a DirectBuffer on this platform");
            }
            this.base = null;
            this.address = DirectBufferAccess.getAddress(byteBuffer) + ((long) byteBuffer.position());
            this.size = byteBuffer.remaining();
            this.reference = byteBuffer;
        } else if (byteBuffer.hasArray()) {
            this.base = byteBuffer.array();
            this.address = (long) ((ARRAY_BYTE_BASE_OFFSET + byteBuffer.arrayOffset()) + byteBuffer.position());
            this.size = byteBuffer.remaining();
            this.reference = null;
        } else {
            throw new IllegalArgumentException("Only the array-backed ByteBuffer or DirectBuffer is supported");
        }
    }

    protected MessageBuffer(Object obj, long j, int i) {
        this.base = obj;
        this.address = j;
        this.size = i;
        this.reference = null;
    }

    public int size() {
        return this.size;
    }

    public MessageBuffer slice(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        Preconditions.checkArgument(i + i2 <= size());
        return new MessageBuffer(this.base, this.address + ((long) i), i2);
    }

    public byte getByte(int i) {
        return unsafe.getByte(this.base, this.address + ((long) i));
    }

    public boolean getBoolean(int i) {
        return unsafe.getBoolean(this.base, this.address + ((long) i));
    }

    public short getShort(int i) {
        return Short.reverseBytes(unsafe.getShort(this.base, this.address + ((long) i)));
    }

    public int getInt(int i) {
        return Integer.reverseBytes(unsafe.getInt(this.base, this.address + ((long) i)));
    }

    public float getFloat(int i) {
        return Float.intBitsToFloat(getInt(i));
    }

    public long getLong(int i) {
        return Long.reverseBytes(unsafe.getLong(this.base, this.address + ((long) i)));
    }

    public double getDouble(int i) {
        return Double.longBitsToDouble(getLong(i));
    }

    public void getBytes(int i, byte[] bArr, int i2, int i3) {
        unsafe.copyMemory(this.base, this.address + ((long) i), bArr, (long) (ARRAY_BYTE_BASE_OFFSET + i2), (long) i3);
    }

    public void getBytes(int i, int i2, ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < i2) {
            throw new BufferOverflowException();
        }
        byteBuffer.put(sliceAsByteBuffer(i, i2));
    }

    public void putByte(int i, byte b) {
        unsafe.putByte(this.base, this.address + ((long) i), b);
    }

    public void putBoolean(int i, boolean z) {
        unsafe.putBoolean(this.base, this.address + ((long) i), z);
    }

    public void putShort(int i, short s) {
        unsafe.putShort(this.base, this.address + ((long) i), Short.reverseBytes(s));
    }

    public void putInt(int i, int i2) {
        unsafe.putInt(this.base, this.address + ((long) i), Integer.reverseBytes(i2));
    }

    public void putFloat(int i, float f) {
        putInt(i, Float.floatToRawIntBits(f));
    }

    public void putLong(int i, long j) {
        unsafe.putLong(this.base, this.address + ((long) i), Long.reverseBytes(j));
    }

    public void putDouble(int i, double d) {
        putLong(i, Double.doubleToRawLongBits(d));
    }

    public void putBytes(int i, byte[] bArr, int i2, int i3) {
        unsafe.copyMemory(bArr, (long) (ARRAY_BYTE_BASE_OFFSET + i2), this.base, this.address + ((long) i), (long) i3);
    }

    public void putByteBuffer(int i, ByteBuffer byteBuffer, int i2) {
        if (!$assertionsDisabled && i2 > byteBuffer.remaining()) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && isUniversalBuffer) {
            throw new AssertionError();
        } else if (byteBuffer.isDirect()) {
            unsafe.copyMemory(null, DirectBufferAccess.getAddress(byteBuffer) + ((long) byteBuffer.position()), this.base, this.address + ((long) i), (long) i2);
            byteBuffer.position(byteBuffer.position() + i2);
        } else if (byteBuffer.hasArray()) {
            unsafe.copyMemory(byteBuffer.array(), (long) (ARRAY_BYTE_BASE_OFFSET + byteBuffer.position()), this.base, this.address + ((long) i), (long) i2);
            byteBuffer.position(byteBuffer.position() + i2);
        } else if (hasArray()) {
            byteBuffer.get((byte[]) this.base, i, i2);
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                unsafe.putByte(this.base, this.address + ((long) i), byteBuffer.get());
            }
        }
    }

    public void putMessageBuffer(int i, MessageBuffer messageBuffer, int i2, int i3) {
        unsafe.copyMemory(messageBuffer.base, messageBuffer.address + ((long) i2), this.base, this.address + ((long) i), (long) i3);
    }

    public ByteBuffer sliceAsByteBuffer(int i, int i2) {
        if (hasArray()) {
            return ByteBuffer.wrap((byte[]) this.base, (int) ((this.address - ((long) ARRAY_BYTE_BASE_OFFSET)) + ((long) i)), i2);
        }
        if ($assertionsDisabled || !isUniversalBuffer) {
            return DirectBufferAccess.newByteBuffer(this.address, i, i2, this.reference);
        }
        throw new AssertionError();
    }

    public ByteBuffer sliceAsByteBuffer() {
        return sliceAsByteBuffer(0, size());
    }

    public boolean hasArray() {
        return this.base != null;
    }

    public byte[] toByteArray() {
        Object obj = new byte[size()];
        unsafe.copyMemory(this.base, this.address, obj, (long) ARRAY_BYTE_BASE_OFFSET, (long) size());
        return obj;
    }

    public byte[] array() {
        return (byte[]) this.base;
    }

    public int arrayOffset() {
        return ((int) this.address) - ARRAY_BYTE_BASE_OFFSET;
    }

    public void copyTo(int i, MessageBuffer messageBuffer, int i2, int i3) {
        unsafe.copyMemory(this.base, this.address + ((long) i), messageBuffer.base, messageBuffer.address + ((long) i2), (long) i3);
    }

    public String toHexString(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i3 = i; i3 < i2; i3++) {
            if (i3 != i) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(getByte(i3))}));
        }
        return stringBuilder.toString();
    }
}
