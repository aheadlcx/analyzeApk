package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.util.ByteInput;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.UTFDataFormatException;

public final class Mutf8 {
    private Mutf8() {
    }

    public static String decode(ByteInput byteInput, char[] cArr) throws UTFDataFormatException {
        int i = 0;
        while (true) {
            char readByte = (char) (byteInput.readByte() & 255);
            if (readByte == '\u0000') {
                return new String(cArr, 0, i);
            }
            cArr[i] = readByte;
            if (readByte < '') {
                i++;
            } else if ((readByte & Opcodes.SHL_INT_LIT8) == 192) {
                r4 = byteInput.readByte() & 255;
                if ((r4 & 192) != 128) {
                    throw new UTFDataFormatException("bad second byte");
                }
                r2 = i + 1;
                cArr[i] = (char) (((readByte & 31) << 6) | (r4 & 63));
                i = r2;
            } else if ((readByte & 240) == Opcodes.SHL_INT_LIT8) {
                r4 = byteInput.readByte() & 255;
                int readByte2 = byteInput.readByte() & 255;
                if ((r4 & 192) == 128 && (readByte2 & 192) == 128) {
                    r2 = i + 1;
                    cArr[i] = (char) ((((readByte & 15) << 12) | ((r4 & 63) << 6)) | (readByte2 & 63));
                    i = r2;
                }
            } else {
                throw new UTFDataFormatException("bad byte");
            }
        }
        throw new UTFDataFormatException("bad second or third byte");
    }

    public static long countBytes(String str, boolean z) throws UTFDataFormatException {
        int length = str.length();
        long j = 0;
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '\u0000' && charAt <= '') {
                j++;
            } else if (charAt <= '߿') {
                j += 2;
            } else {
                j += 3;
            }
            if (!z || r0 <= 65535) {
                i++;
            } else {
                throw new UTFDataFormatException("String more than 65535 UTF bytes long");
            }
        }
        return j;
    }

    public static void encode(byte[] bArr, int i, String str) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int i3;
            char charAt = str.charAt(i2);
            if (charAt != '\u0000' && charAt <= '') {
                i3 = i + 1;
                bArr[i] = (byte) charAt;
            } else if (charAt <= '߿') {
                r4 = i + 1;
                bArr[i] = (byte) (((charAt >> 6) & 31) | 192);
                i3 = r4 + 1;
                bArr[r4] = (byte) ((charAt & 63) | 128);
            } else {
                i3 = i + 1;
                bArr[i] = (byte) (((charAt >> 12) & 15) | Opcodes.SHL_INT_LIT8);
                r4 = i3 + 1;
                bArr[i3] = (byte) (((charAt >> 6) & 63) | 128);
                i3 = r4 + 1;
                bArr[r4] = (byte) ((charAt & 63) | 128);
            }
            i2++;
            i = i3;
        }
    }

    public static byte[] encode(String str) throws UTFDataFormatException {
        byte[] bArr = new byte[((int) countBytes(str, true))];
        encode(bArr, 0, str);
        return bArr;
    }
}
