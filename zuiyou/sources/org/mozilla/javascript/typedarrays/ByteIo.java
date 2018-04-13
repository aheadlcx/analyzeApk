package org.mozilla.javascript.typedarrays;

public class ByteIo {
    public static Object readInt8(byte[] bArr, int i) {
        return Byte.valueOf(bArr[i]);
    }

    public static void writeInt8(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) i2;
    }

    public static Object readUint8(byte[] bArr, int i) {
        return Integer.valueOf(bArr[i] & 255);
    }

    public static void writeUint8(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 & 255);
    }

    private static short doReadInt16(byte[] bArr, int i, boolean z) {
        if (z) {
            return (short) ((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8));
        }
        return (short) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255));
    }

    private static void doWriteInt16(byte[] bArr, int i, int i2, boolean z) {
        if (z) {
            bArr[i] = (byte) (i2 & 255);
            bArr[i + 1] = (byte) ((i2 >>> 8) & 255);
            return;
        }
        bArr[i] = (byte) ((i2 >>> 8) & 255);
        bArr[i + 1] = (byte) (i2 & 255);
    }

    public static Object readInt16(byte[] bArr, int i, boolean z) {
        return Short.valueOf(doReadInt16(bArr, i, z));
    }

    public static void writeInt16(byte[] bArr, int i, int i2, boolean z) {
        doWriteInt16(bArr, i, i2, z);
    }

    public static Object readUint16(byte[] bArr, int i, boolean z) {
        return Integer.valueOf(doReadInt16(bArr, i, z) & 65535);
    }

    public static void writeUint16(byte[] bArr, int i, int i2, boolean z) {
        doWriteInt16(bArr, i, 65535 & i2, z);
    }

    public static Object readInt32(byte[] bArr, int i, boolean z) {
        if (z) {
            return Integer.valueOf((((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24));
        }
        return Integer.valueOf(((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8)) | (bArr[i + 3] & 255));
    }

    public static void writeInt32(byte[] bArr, int i, int i2, boolean z) {
        if (z) {
            bArr[i] = (byte) (i2 & 255);
            bArr[i + 1] = (byte) ((i2 >>> 8) & 255);
            bArr[i + 2] = (byte) ((i2 >>> 16) & 255);
            bArr[i + 3] = (byte) ((i2 >>> 24) & 255);
            return;
        }
        bArr[i] = (byte) ((i2 >>> 24) & 255);
        bArr[i + 1] = (byte) ((i2 >>> 16) & 255);
        bArr[i + 2] = (byte) ((i2 >>> 8) & 255);
        bArr[i + 3] = (byte) (i2 & 255);
    }

    public static long readUint32Primitive(byte[] bArr, int i, boolean z) {
        if (z) {
            return ((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) & 4294967295L;
        }
        return (((((((long) bArr[i]) & 255) << 24) | ((((long) bArr[i + 1]) & 255) << 16)) | ((((long) bArr[i + 2]) & 255) << 8)) | (((long) bArr[i + 3]) & 255)) & 4294967295L;
    }

    public static void writeUint32(byte[] bArr, int i, long j, boolean z) {
        if (z) {
            bArr[i] = (byte) ((int) (j & 255));
            bArr[i + 1] = (byte) ((int) ((j >>> 8) & 255));
            bArr[i + 2] = (byte) ((int) ((j >>> 16) & 255));
            bArr[i + 3] = (byte) ((int) ((j >>> 24) & 255));
            return;
        }
        bArr[i] = (byte) ((int) ((j >>> 24) & 255));
        bArr[i + 1] = (byte) ((int) ((j >>> 16) & 255));
        bArr[i + 2] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i + 3] = (byte) ((int) (j & 255));
    }

    public static Object readUint32(byte[] bArr, int i, boolean z) {
        return Long.valueOf(readUint32Primitive(bArr, i, z));
    }

    public static long readUint64Primitive(byte[] bArr, int i, boolean z) {
        if (z) {
            return (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48)) | ((((long) bArr[i + 7]) & 255) << 56);
        }
        return ((((((((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i + 1]) & 255) << 48)) | ((((long) bArr[i + 2]) & 255) << 40)) | ((((long) bArr[i + 3]) & 255) << 32)) | ((((long) bArr[i + 4]) & 255) << 24)) | ((((long) bArr[i + 5]) & 255) << 16)) | ((((long) bArr[i + 6]) & 255) << 8)) | ((((long) bArr[i + 7]) & 255) << null);
    }

    public static void writeUint64(byte[] bArr, int i, long j, boolean z) {
        if (z) {
            bArr[i] = (byte) ((int) (j & 255));
            bArr[i + 1] = (byte) ((int) ((j >>> 8) & 255));
            bArr[i + 2] = (byte) ((int) ((j >>> 16) & 255));
            bArr[i + 3] = (byte) ((int) ((j >>> 24) & 255));
            bArr[i + 4] = (byte) ((int) ((j >>> 32) & 255));
            bArr[i + 5] = (byte) ((int) ((j >>> 40) & 255));
            bArr[i + 6] = (byte) ((int) ((j >>> 48) & 255));
            bArr[i + 7] = (byte) ((int) ((j >>> 56) & 255));
            return;
        }
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        bArr[i + 1] = (byte) ((int) ((j >>> 48) & 255));
        bArr[i + 2] = (byte) ((int) ((j >>> 40) & 255));
        bArr[i + 3] = (byte) ((int) ((j >>> 32) & 255));
        bArr[i + 4] = (byte) ((int) ((j >>> 24) & 255));
        bArr[i + 5] = (byte) ((int) ((j >>> 16) & 255));
        bArr[i + 6] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i + 7] = (byte) ((int) (j & 255));
    }

    public static Object readFloat32(byte[] bArr, int i, boolean z) {
        return Float.valueOf(Float.intBitsToFloat((int) readUint32Primitive(bArr, i, z)));
    }

    public static void writeFloat32(byte[] bArr, int i, double d, boolean z) {
        writeUint32(bArr, i, (long) Float.floatToIntBits((float) d), z);
    }

    public static Object readFloat64(byte[] bArr, int i, boolean z) {
        return Double.valueOf(Double.longBitsToDouble(readUint64Primitive(bArr, i, z)));
    }

    public static void writeFloat64(byte[] bArr, int i, double d, boolean z) {
        writeUint64(bArr, i, Double.doubleToLongBits(d), z);
    }
}
