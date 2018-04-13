package com.tencent.tinker.ziputils.ziputil;

import java.nio.ByteOrder;

public final class Memory {
    private Memory() {
    }

    public static int peekInt(byte[] bArr, int i, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i2 = i + 1;
            int i3 = i2 + 1;
            return ((((bArr[i2] & 255) << 16) | ((bArr[i] & 255) << 24)) | ((bArr[i3] & 255) << 8)) | ((bArr[i3 + 1] & 255) << 0);
        }
        i2 = i + 1;
        i3 = i2 + 1;
        return ((((bArr[i2] & 255) << 8) | ((bArr[i] & 255) << 0)) | ((bArr[i3] & 255) << 16)) | ((bArr[i3 + 1] & 255) << 24);
    }

    public static long peekLong(byte[] bArr, int i, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i2 = i + 1;
            int i3 = i2 + 1;
            i2 = ((bArr[i2] & 255) << 16) | ((bArr[i] & 255) << 24);
            int i4 = i3 + 1;
            i2 |= (bArr[i3] & 255) << 8;
            i3 = i4 + 1;
            i2 |= (bArr[i4] & 255) << 0;
            i4 = i3 + 1;
            int i5 = i4 + 1;
            return (((long) (((((bArr[i4] & 255) << 16) | ((bArr[i3] & 255) << 24)) | ((bArr[i5] & 255) << 8)) | ((bArr[i5 + 1] & 255) << 0))) & 4294967295L) | (((long) i2) << 32);
        }
        i2 = i + 1;
        i3 = i2 + 1;
        i2 = ((bArr[i2] & 255) << 8) | ((bArr[i] & 255) << 0);
        i4 = i3 + 1;
        i2 |= (bArr[i3] & 255) << 16;
        i3 = i4 + 1;
        i2 |= (bArr[i4] & 255) << 24;
        i4 = i3 + 1;
        i5 = i4 + 1;
        return (((long) i2) & 4294967295L) | (((long) (((((bArr[i4] & 255) << 8) | ((bArr[i3] & 255) << 0)) | ((bArr[i5] & 255) << 16)) | ((bArr[i5 + 1] & 255) << 24))) << 32);
    }

    public static short peekShort(byte[] bArr, int i, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return (short) ((bArr[i] << 8) | (bArr[i + 1] & 255));
        }
        return (short) ((bArr[i + 1] << 8) | (bArr[i] & 255));
    }

    public static void pokeInt(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i3 = i + 1;
            bArr[i] = (byte) ((i2 >> 24) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((i2 >> 16) & 255);
            i3 = i4 + 1;
            bArr[i4] = (byte) ((i2 >> 8) & 255);
            bArr[i3] = (byte) ((i2 >> 0) & 255);
            return;
        }
        i3 = i + 1;
        bArr[i] = (byte) ((i2 >> 0) & 255);
        i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >> 8) & 255);
        i3 = i4 + 1;
        bArr[i4] = (byte) ((i2 >> 16) & 255);
        bArr[i3] = (byte) ((i2 >> 24) & 255);
    }

    public static void pokeLong(byte[] bArr, int i, long j, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i2 = (int) (j >> 32);
            int i3 = i + 1;
            bArr[i] = (byte) ((i2 >> 24) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((i2 >> 16) & 255);
            i3 = i4 + 1;
            bArr[i4] = (byte) ((i2 >> 8) & 255);
            i4 = i3 + 1;
            bArr[i3] = (byte) ((i2 >> 0) & 255);
            i2 = (int) j;
            i3 = i4 + 1;
            bArr[i4] = (byte) ((i2 >> 24) & 255);
            i4 = i3 + 1;
            bArr[i3] = (byte) ((i2 >> 16) & 255);
            i3 = i4 + 1;
            bArr[i4] = (byte) ((i2 >> 8) & 255);
            bArr[i3] = (byte) ((i2 >> 0) & 255);
            return;
        }
        i2 = (int) j;
        i3 = i + 1;
        bArr[i] = (byte) ((i2 >> 0) & 255);
        i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >> 8) & 255);
        i3 = i4 + 1;
        bArr[i4] = (byte) ((i2 >> 16) & 255);
        i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >> 24) & 255);
        i2 = (int) (j >> 32);
        i3 = i4 + 1;
        bArr[i4] = (byte) ((i2 >> 0) & 255);
        i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >> 8) & 255);
        i3 = i4 + 1;
        bArr[i4] = (byte) ((i2 >> 16) & 255);
        bArr[i3] = (byte) ((i2 >> 24) & 255);
    }

    public static void pokeShort(byte[] bArr, int i, short s, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i2 = i + 1;
            bArr[i] = (byte) ((s >> 8) & 255);
            bArr[i2] = (byte) ((s >> 0) & 255);
            return;
        }
        i2 = i + 1;
        bArr[i] = (byte) ((s >> 0) & 255);
        bArr[i2] = (byte) ((s >> 8) & 255);
    }
}
