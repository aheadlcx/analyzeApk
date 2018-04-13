package org.msgpack.core.buffer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

class DirectBufferAccess {
    static Constructor byteBufferConstructor;
    static DirectBufferConstructorType directBufferConstructorType;
    static Class<?> directByteBufferClass;
    static Method mClean;
    static Method mCleaner;
    static Method mGetAddress;
    static Method memoryBlockWrapFromJni;

    enum DirectBufferConstructorType {
        ARGS_LONG_INT_REF,
        ARGS_LONG_INT,
        ARGS_INT_INT,
        ARGS_MB_INT_INT
    }

    private DirectBufferAccess() {
    }

    static {
        try {
            Constructor declaredConstructor;
            DirectBufferConstructorType directBufferConstructorType;
            directByteBufferClass = ClassLoader.getSystemClassLoader().loadClass("java.nio.DirectByteBuffer");
            Method method = null;
            try {
                declaredConstructor = directByteBufferClass.getDeclaredConstructor(new Class[]{Long.TYPE, Integer.TYPE, Object.class});
                directBufferConstructorType = DirectBufferConstructorType.ARGS_LONG_INT_REF;
            } catch (NoSuchMethodException e) {
                try {
                    declaredConstructor = directByteBufferClass.getDeclaredConstructor(new Class[]{Long.TYPE, Integer.TYPE});
                    directBufferConstructorType = DirectBufferConstructorType.ARGS_LONG_INT;
                } catch (NoSuchMethodException e2) {
                    try {
                        declaredConstructor = directByteBufferClass.getDeclaredConstructor(new Class[]{Integer.TYPE, Integer.TYPE});
                        directBufferConstructorType = DirectBufferConstructorType.ARGS_INT_INT;
                    } catch (NoSuchMethodException e3) {
                        method = Class.forName("java.nio.MemoryBlock").getDeclaredMethod("wrapFromJni", new Class[]{Integer.TYPE, Long.TYPE});
                        method.setAccessible(true);
                        declaredConstructor = directByteBufferClass.getDeclaredConstructor(new Class[]{r1, Integer.TYPE, Integer.TYPE});
                        directBufferConstructorType = DirectBufferConstructorType.ARGS_MB_INT_INT;
                    }
                }
            }
            byteBufferConstructor = declaredConstructor;
            directBufferConstructorType = directBufferConstructorType;
            memoryBlockWrapFromJni = method;
            if (byteBufferConstructor == null) {
                throw new RuntimeException("Constructor of DirectByteBuffer is not found");
            }
            byteBufferConstructor.setAccessible(true);
            mGetAddress = directByteBufferClass.getDeclaredMethod("address", new Class[0]);
            mGetAddress.setAccessible(true);
            mCleaner = directByteBufferClass.getDeclaredMethod("cleaner", new Class[0]);
            mCleaner.setAccessible(true);
            mClean = mCleaner.getReturnType().getDeclaredMethod("clean", new Class[0]);
            mClean.setAccessible(true);
        } catch (Throwable e4) {
            throw new RuntimeException(e4);
        }
    }

    static long getAddress(Object obj) {
        try {
            return ((Long) mGetAddress.invoke(obj, new Object[0])).longValue();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    static void clean(Object obj) {
        try {
            mClean.invoke(mCleaner.invoke(obj, new Object[0]), new Object[0]);
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException(th);
        }
    }

    static boolean isDirectByteBufferInstance(Object obj) {
        return directByteBufferClass.isInstance(obj);
    }

    static ByteBuffer newByteBuffer(long j, int i, int i2, ByteBuffer byteBuffer) {
        RuntimeException runtimeException;
        try {
            switch (directBufferConstructorType) {
                case ARGS_LONG_INT_REF:
                    return (ByteBuffer) byteBufferConstructor.newInstance(new Object[]{Long.valueOf(((long) i) + j), Integer.valueOf(i2), byteBuffer});
                case ARGS_LONG_INT:
                    return (ByteBuffer) byteBufferConstructor.newInstance(new Object[]{Long.valueOf(((long) i) + j), Integer.valueOf(i2)});
                case ARGS_INT_INT:
                    return (ByteBuffer) byteBufferConstructor.newInstance(new Object[]{Integer.valueOf(((int) j) + i), Integer.valueOf(i2)});
                case ARGS_MB_INT_INT:
                    Constructor constructor = byteBufferConstructor;
                    r1 = new Object[3];
                    r1[0] = memoryBlockWrapFromJni.invoke(null, new Object[]{Long.valueOf(((long) i) + j), Integer.valueOf(i2)});
                    r1[1] = Integer.valueOf(i2);
                    r1[2] = Integer.valueOf(0);
                    return (ByteBuffer) constructor.newInstance(r1);
                default:
                    throw new IllegalStateException("Unexpected value");
            }
        } catch (Throwable th) {
            runtimeException = new RuntimeException(th);
        }
        runtimeException = new RuntimeException(th);
    }
}
