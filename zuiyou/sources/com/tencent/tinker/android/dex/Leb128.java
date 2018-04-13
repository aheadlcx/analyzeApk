package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.util.ByteInput;
import com.tencent.tinker.android.dex.util.ByteOutput;

public final class Leb128 {
    private Leb128() {
    }

    public static int unsignedLeb128Size(int i) {
        int i2 = i >>> 7;
        int i3 = 0;
        while (i2 != 0) {
            i2 >>>= 7;
            i3++;
        }
        return i3 + 1;
    }

    public static int unsignedLeb128p1Size(int i) {
        return unsignedLeb128Size(i + 1);
    }

    public static int signedLeb128Size(int i) {
        int i2 = i >> 7;
        int i3 = (Integer.MIN_VALUE & i) == 0 ? 0 : -1;
        int i4 = 0;
        int i5 = i2;
        Object obj = 1;
        while (obj != null) {
            if (i5 == i3 && (i5 & 1) == ((r7 >> 6) & 1)) {
                obj = null;
            } else {
                obj = 1;
            }
            i4++;
            i = i5;
            i5 >>= 7;
        }
        return i4;
    }

    public static int readSignedLeb128(ByteInput byteInput) {
        int i = 0;
        int i2 = -1;
        int i3 = 0;
        do {
            int readByte = byteInput.readByte() & 255;
            i3 |= (readByte & 127) << (i * 7);
            i2 <<= 7;
            i++;
            if ((readByte & 128) != 128) {
                break;
            }
        } while (i < 5);
        if ((readByte & 128) == 128) {
            throw new DexException("invalid LEB128 sequence");
        } else if (((i2 >> 1) & i3) != 0) {
            return i3 | i2;
        } else {
            return i3;
        }
    }

    public static int readUnsignedLeb128(ByteInput byteInput) {
        int i = 0;
        int i2 = 0;
        do {
            int readByte = byteInput.readByte() & 255;
            i2 |= (readByte & 127) << (i * 7);
            i++;
            if ((readByte & 128) != 128) {
                break;
            }
        } while (i < 5);
        if ((readByte & 128) != 128) {
            return i2;
        }
        throw new DexException("invalid LEB128 sequence");
    }

    public static int readUnsignedLeb128p1(ByteInput byteInput) {
        return readUnsignedLeb128(byteInput) - 1;
    }

    public static int writeUnsignedLeb128(ByteOutput byteOutput, int i) {
        int i2 = 0;
        for (int i3 = i >>> 7; i3 != 0; i3 >>>= 7) {
            byteOutput.writeByte((byte) ((i & 127) | 128));
            i2++;
            i = i3;
        }
        byteOutput.writeByte((byte) (i & 127));
        return i2 + 1;
    }

    public static int writeUnsignedLeb128p1(ByteOutput byteOutput, int i) {
        return writeUnsignedLeb128(byteOutput, i + 1);
    }

    public static int writeSignedLeb128(ByteOutput byteOutput, int i) {
        int i2 = i >> 7;
        int i3 = (Integer.MIN_VALUE & i) == 0 ? 0 : -1;
        int i4 = 0;
        Object obj = 1;
        int i5 = i2;
        while (obj != null) {
            if (i5 == i3 && (i5 & 1) == ((i >> 6) & 1)) {
                obj = null;
            } else {
                obj = 1;
            }
            int i6 = i & 127;
            if (obj != null) {
                i2 = 128;
            } else {
                i2 = 0;
            }
            byteOutput.writeByte((byte) (i2 | i6));
            i = i5;
            i5 >>= 7;
            i4++;
        }
        return i4;
    }
}
