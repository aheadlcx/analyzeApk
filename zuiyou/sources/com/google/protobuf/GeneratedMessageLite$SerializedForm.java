package com.google.protobuf;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;

protected final class GeneratedMessageLite$SerializedForm implements Serializable {
    private static final long serialVersionUID = 0;
    private final byte[] asBytes;
    private final String messageClassName;

    public static GeneratedMessageLite$SerializedForm of(MessageLite messageLite) {
        return new GeneratedMessageLite$SerializedForm(messageLite);
    }

    GeneratedMessageLite$SerializedForm(MessageLite messageLite) {
        this.messageClassName = messageLite.getClass().getName();
        this.asBytes = messageLite.toByteArray();
    }

    protected Object readResolve() throws ObjectStreamException {
        try {
            Field declaredField = Class.forName(this.messageClassName).getDeclaredField("DEFAULT_INSTANCE");
            declaredField.setAccessible(true);
            return ((MessageLite) declaredField.get(null)).newBuilderForType().mergeFrom(this.asBytes).buildPartial();
        } catch (Throwable e) {
            throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
        } catch (NoSuchFieldException e2) {
            return readResolveFallback();
        } catch (Throwable e3) {
            throw new RuntimeException("Unable to call DEFAULT_INSTANCE in " + this.messageClassName, e3);
        } catch (Throwable e32) {
            throw new RuntimeException("Unable to call parsePartialFrom", e32);
        } catch (Throwable e322) {
            throw new RuntimeException("Unable to understand proto buffer", e322);
        }
    }

    @Deprecated
    private Object readResolveFallback() throws ObjectStreamException {
        try {
            Field declaredField = Class.forName(this.messageClassName).getDeclaredField("defaultInstance");
            declaredField.setAccessible(true);
            return ((MessageLite) declaredField.get(null)).newBuilderForType().mergeFrom(this.asBytes).buildPartial();
        } catch (Throwable e) {
            throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
        } catch (Throwable e2) {
            throw new RuntimeException("Unable to find defaultInstance in " + this.messageClassName, e2);
        } catch (Throwable e22) {
            throw new RuntimeException("Unable to call defaultInstance in " + this.messageClassName, e22);
        } catch (Throwable e222) {
            throw new RuntimeException("Unable to call parsePartialFrom", e222);
        } catch (Throwable e2222) {
            throw new RuntimeException("Unable to understand proto buffer", e2222);
        }
    }
}
