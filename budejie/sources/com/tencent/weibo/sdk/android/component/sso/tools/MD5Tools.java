package com.tencent.weibo.sdk.android.component.sso.tools;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Tools {
    static final byte[] PADDING;
    static final int S11 = 7;
    static final int S12 = 12;
    static final int S13 = 17;
    static final int S14 = 22;
    static final int S21 = 5;
    static final int S22 = 9;
    static final int S23 = 14;
    static final int S24 = 20;
    static final int S31 = 4;
    static final int S32 = 11;
    static final int S33 = 16;
    static final int S34 = 23;
    static final int S41 = 6;
    static final int S42 = 10;
    static final int S43 = 15;
    static final int S44 = 21;
    private byte[] buffer = new byte[64];
    private long[] count = new long[2];
    private byte[] digest = new byte[16];
    public String digestHexStr;
    private long[] state = new long[4];

    static {
        byte[] bArr = new byte[64];
        bArr[0] = Byte.MIN_VALUE;
        PADDING = bArr;
    }

    public byte[] getMD5(byte[] bArr) {
        md5Init();
        md5Update(new ByteArrayInputStream(bArr), (long) bArr.length);
        md5Final();
        return this.digest;
    }

    public byte[] getMD5(InputStream inputStream, long j) {
        md5Init();
        if (!md5Update(inputStream, j)) {
            return new byte[16];
        }
        md5Final();
        return this.digest;
    }

    public MD5Tools() {
        md5Init();
    }

    private void md5Init() {
        this.count[0] = 0;
        this.count[1] = 0;
        this.state[0] = 1732584193;
        this.state[1] = 4023233417L;
        this.state[2] = 2562383102L;
        this.state[3] = 271733878;
    }

    private long F(long j, long j2, long j3) {
        return (j & j2) | ((-1 ^ j) & j3);
    }

    private long G(long j, long j2, long j3) {
        return (j & j3) | ((-1 ^ j3) & j2);
    }

    private long H(long j, long j2, long j3) {
        return (j ^ j2) ^ j3;
    }

    private long I(long j, long j2, long j3) {
        return ((-1 ^ j3) | j) ^ j2;
    }

    private long FF(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long F = ((F(j2, j3, j4) + j5) + j7) + j;
        return ((long) ((((int) F) >>> ((int) (32 - j6))) | (((int) F) << ((int) j6)))) + j2;
    }

    private long GG(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long G = ((G(j2, j3, j4) + j5) + j7) + j;
        return ((long) ((((int) G) >>> ((int) (32 - j6))) | (((int) G) << ((int) j6)))) + j2;
    }

    private long HH(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long H = ((H(j2, j3, j4) + j5) + j7) + j;
        return ((long) ((((int) H) >>> ((int) (32 - j6))) | (((int) H) << ((int) j6)))) + j2;
    }

    private long II(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long I = ((I(j2, j3, j4) + j5) + j7) + j;
        return ((long) ((((int) I) >>> ((int) (32 - j6))) | (((int) I) << ((int) j6)))) + j2;
    }

    private boolean md5Update(InputStream inputStream, long j) {
        byte[] bArr;
        int i;
        byte[] bArr2 = new byte[64];
        int i2 = ((int) (this.count[0] >>> 3)) & 63;
        long[] jArr = this.count;
        long j2 = jArr[0] + (j << 3);
        jArr[0] = j2;
        if (j2 < (j << 3)) {
            jArr = this.count;
            jArr[1] = jArr[1] + 1;
        }
        jArr = this.count;
        jArr[1] = jArr[1] + (j >>> 29);
        int i3 = 64 - i2;
        if (j >= ((long) i3)) {
            bArr = new byte[i3];
            try {
                inputStream.read(bArr, 0, i3);
                md5Memcpy(this.buffer, bArr, i2, 0, i3);
                md5Transform(this.buffer);
                i = i3;
                while (((long) (i + 63)) < j) {
                    try {
                        inputStream.read(bArr2);
                        md5Transform(bArr2);
                        i += 64;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                i2 = 0;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        i = 0;
        bArr = new byte[((int) (j - ((long) i)))];
        try {
            inputStream.read(bArr);
            md5Memcpy(this.buffer, bArr, i2, 0, bArr.length);
            return true;
        } catch (Exception e22) {
            e22.printStackTrace();
            return false;
        }
    }

    private void md5Update(byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[64];
        int i3 = ((int) (this.count[0] >>> 3)) & 63;
        long[] jArr = this.count;
        long j = jArr[0] + ((long) (i << 3));
        jArr[0] = j;
        if (j < ((long) (i << 3))) {
            jArr = this.count;
            jArr[1] = jArr[1] + 1;
        }
        jArr = this.count;
        jArr[1] = jArr[1] + ((long) (i >>> 29));
        int i4 = 64 - i3;
        if (i >= i4) {
            md5Memcpy(this.buffer, bArr, i3, 0, i4);
            md5Transform(this.buffer);
            while (i4 + 63 < i) {
                md5Memcpy(bArr2, bArr, 0, i4, 64);
                md5Transform(bArr2);
                i4 += 64;
            }
            i3 = 0;
            i2 = i4;
        }
        md5Memcpy(this.buffer, bArr, i3, i2, i - i2);
    }

    private void md5Final() {
        byte[] bArr = new byte[8];
        Encode(bArr, this.count, 8);
        int i = ((int) (this.count[0] >>> 3)) & 63;
        md5Update(PADDING, i < 56 ? 56 - i : 120 - i);
        md5Update(bArr, 8);
        Encode(this.digest, this.state, 16);
    }

    private void md5Memcpy(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[i + i4] = bArr2[i2 + i4];
        }
    }

    private void md5Transform(byte[] bArr) {
        long j = this.state[0];
        long j2 = this.state[1];
        long j3 = this.state[2];
        long j4 = this.state[3];
        long[] jArr = new long[16];
        Decode(jArr, bArr, 64);
        long FF = FF(j, j2, j3, j4, jArr[0], 7, 3614090360L);
        long FF2 = FF(j4, FF, j2, j3, jArr[1], 12, 3905402710L);
        long FF3 = FF(j3, FF2, FF, j2, jArr[2], 17, 606105819);
        long FF4 = FF(j2, FF3, FF2, FF, jArr[3], 22, 3250441966L);
        long FF5 = FF(FF, FF4, FF3, FF2, jArr[4], 7, 4118548399L);
        long FF6 = FF(FF2, FF5, FF4, FF3, jArr[5], 12, 1200080426);
        j2 = FF(FF3, FF6, FF5, FF4, jArr[6], 17, 2821735955L);
        long FF7 = FF(FF4, j2, FF6, FF5, jArr[7], 22, 4249261313L);
        long FF8 = FF(FF5, FF7, j2, FF6, jArr[8], 7, 1770035416);
        FF4 = FF(FF6, FF8, FF7, j2, jArr[9], 12, 2336552879L);
        j2 = FF(j2, FF4, FF8, FF7, jArr[10], 17, 4294925233L);
        long FF9 = FF(FF7, j2, FF4, FF8, jArr[11], 22, 2304563134L);
        long FF10 = FF(FF8, FF9, j2, FF4, jArr[12], 7, 1804603682);
        FF7 = FF(FF4, FF10, FF9, j2, jArr[13], 12, 4254626195L);
        j2 = FF(j2, FF7, FF10, FF9, jArr[14], 17, 2792965006L);
        FF3 = FF(FF9, j2, FF7, FF10, jArr[15], 22, 1236535329);
        FF10 = GG(FF10, FF3, j2, FF7, jArr[1], 5, 4129170786L);
        j3 = GG(FF7, FF10, FF3, j2, jArr[6], 9, 3225465664L);
        FF2 = GG(j2, j3, FF10, FF3, jArr[11], 14, 643717713);
        FF9 = GG(FF3, FF2, j3, FF10, jArr[0], 20, 3921069994L);
        j4 = GG(FF10, FF9, FF2, j3, jArr[5], 5, 3593408605L);
        FF = GG(j3, j4, FF9, FF2, jArr[10], 9, 38016083);
        long GG = GG(FF2, FF, j4, FF9, jArr[15], 14, 3634488961L);
        FF3 = GG(FF9, GG, FF, j4, jArr[4], 20, 3889429448L);
        FF6 = GG(j4, FF3, GG, FF, jArr[9], 5, 568446438);
        long GG2 = GG(FF, FF6, FF3, GG, jArr[14], 9, 3275163606L);
        FF2 = GG(GG, GG2, FF6, FF3, jArr[3], 14, 4107603335L);
        FF5 = GG(FF3, FF2, GG2, FF6, jArr[8], 20, 1163531501);
        long GG3 = GG(FF6, FF5, FF2, GG2, jArr[13], 5, 2850285829L);
        FF = GG(GG2, GG3, FF5, FF2, jArr[2], 9, 4243563512L);
        FF4 = GG(FF2, FF, GG3, FF5, jArr[7], 14, 1735328473);
        long GG4 = GG(FF5, FF4, FF, GG3, jArr[12], 20, 2368359562L);
        FF6 = HH(GG3, GG4, FF4, FF, jArr[5], 4, 4294588738L);
        FF8 = HH(FF, FF6, GG4, FF4, jArr[8], 11, 2272392833L);
        long HH = HH(FF4, FF8, FF6, GG4, jArr[11], 16, 1839030562);
        FF5 = HH(GG4, HH, FF8, FF6, jArr[14], 23, 4259657740L);
        FF7 = HH(FF6, FF5, HH, FF8, jArr[1], 4, 2763975236L);
        long HH2 = HH(FF8, FF7, FF5, HH, jArr[4], 11, 1272893353);
        FF4 = HH(HH, HH2, FF7, FF5, jArr[7], 16, 4139469664L);
        FF10 = HH(FF5, FF4, HH2, FF7, jArr[10], 23, 3200236656L);
        long HH3 = HH(FF7, FF10, FF4, HH2, jArr[13], 4, 681279174);
        FF8 = HH(HH2, HH3, FF10, FF4, jArr[0], 11, 3936430074L);
        FF9 = HH(FF4, FF8, HH3, FF10, jArr[3], 16, 3572445317L);
        long HH4 = HH(FF10, FF9, FF8, HH3, jArr[6], 23, 76029189);
        FF7 = HH(HH3, HH4, FF9, FF8, jArr[9], 4, 3654602809L);
        GG = HH(FF8, FF7, HH4, FF9, jArr[12], 11, 3873151461L);
        long HH5 = HH(FF9, GG, FF7, HH4, jArr[15], 16, 530742520);
        FF10 = HH(HH4, HH5, GG, FF7, jArr[2], 23, 3299628645L);
        GG2 = II(FF7, FF10, HH5, GG, jArr[0], 6, 4096336452L);
        long II = II(GG, GG2, FF10, HH5, jArr[7], 10, 1126891415);
        FF9 = II(HH5, II, GG2, FF10, jArr[14], 15, 2878612391L);
        GG3 = II(FF10, FF9, II, GG2, jArr[5], 21, 4237533241L);
        long II2 = II(GG2, GG3, FF9, II, jArr[12], 6, 1700485571);
        GG = II(II, II2, GG3, FF9, jArr[3], 10, 2399980690L);
        GG4 = II(FF9, GG, II2, GG3, jArr[10], 15, 4293915773L);
        long II3 = II(GG3, GG4, GG, II2, jArr[1], 21, 2240044497L);
        GG2 = II(II2, II3, GG4, GG, jArr[8], 6, 1873313359);
        HH = II(GG, GG2, II3, GG4, jArr[15], 10, 4264355552L);
        long II4 = II(GG4, HH, GG2, II3, jArr[6], 15, 2734768916L);
        GG3 = II(II3, II4, HH, GG2, jArr[13], 21, 1309151649);
        HH2 = II(GG2, GG3, II4, HH, jArr[4], 6, 4149444226L);
        long II5 = II(HH, HH2, GG3, II4, jArr[11], 10, 3174756917L);
        GG4 = II(II4, II5, HH2, GG3, jArr[2], 15, 718787259);
        long II6 = II(GG3, GG4, II5, HH2, jArr[9], 21, 3951481745L);
        long[] jArr2 = this.state;
        jArr2[0] = jArr2[0] + HH2;
        jArr2 = this.state;
        jArr2[1] = II6 + jArr2[1];
        jArr = this.state;
        jArr[2] = jArr[2] + GG4;
        jArr = this.state;
        jArr[3] = jArr[3] + II5;
    }

    private void Encode(byte[] bArr, long[] jArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            bArr[i2] = (byte) ((int) (jArr[i3] & 255));
            bArr[i2 + 1] = (byte) ((int) ((jArr[i3] >>> 8) & 255));
            bArr[i2 + 2] = (byte) ((int) ((jArr[i3] >>> 16) & 255));
            bArr[i2 + 3] = (byte) ((int) ((jArr[i3] >>> 24) & 255));
            i3++;
            i2 += 4;
        }
    }

    private void Decode(long[] jArr, byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            jArr[i3] = ((b2iu(bArr[i2]) | (b2iu(bArr[i2 + 1]) << 8)) | (b2iu(bArr[i2 + 2]) << 16)) | (b2iu(bArr[i2 + 3]) << 24);
            i3++;
            i2 += 4;
        }
    }

    public static long b2iu(byte b) {
        if (b < (byte) 0) {
            b &= 255;
        }
        return (long) b;
    }

    public static String byteHEX(byte b) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & 15]});
    }

    public static byte[] toMD5Byte(byte[] bArr) {
        return new MD5Tools().getMD5(bArr);
    }

    public static byte[] toMD5Byte(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        return new MD5Tools().getMD5(bytes);
    }

    public static byte[] toMD5Byte(InputStream inputStream, long j) {
        return new MD5Tools().getMD5(inputStream, j);
    }

    public static String toMD5(InputStream inputStream, long j) {
        byte[] md5 = new MD5Tools().getMD5(inputStream, j);
        String str = "";
        for (int i = 0; i < 16; i++) {
            str = new StringBuilder(String.valueOf(str)).append(byteHEX(md5[i])).toString();
        }
        return str;
    }

    public static String toMD5(byte[] bArr) {
        byte[] md5 = new MD5Tools().getMD5(bArr);
        String str = "";
        for (int i = 0; i < 16; i++) {
            str = new StringBuilder(String.valueOf(str)).append(byteHEX(md5[i])).toString();
        }
        return str;
    }

    public static String toMD5(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        byte[] md5 = new MD5Tools().getMD5(bytes);
        String str2 = "";
        for (int i = 0; i < 16; i++) {
            str2 = new StringBuilder(String.valueOf(str2)).append(byteHEX(md5[i])).toString();
        }
        return str2;
    }

    public static String getMD5String(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            char[] cArr2 = new char[32];
            int i2 = 0;
            while (i < 16) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFileMD5(File file) throws FileNotFoundException {
        int i = 0;
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[1024];
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest instance = MessageDigest.getInstance("MD5");
        while (true) {
            int read = fileInputStream.read(bArr, 0, 1024);
            if (read == -1) {
                break;
            }
            try {
                instance.update(bArr, 0, read);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    fileInputStream.close();
                    return null;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return null;
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        byte[] digest = instance.digest();
        char[] cArr2 = new char[32];
        int i2 = 0;
        while (i < 16) {
            byte b = digest[i];
            int i3 = i2 + 1;
            cArr2[i2] = cArr[(b >>> 4) & 15];
            i2 = i3 + 1;
            cArr2[i3] = cArr[b & 15];
            i++;
        }
        String str = new String(cArr2);
        try {
            fileInputStream.close();
            return str;
        } catch (IOException e32) {
            e32.printStackTrace();
            return str;
        }
    }

    public static String HEXByte(byte[] bArr) {
        try {
            byte[] bArr2 = new byte[(bArr.length / 2)];
            for (int i = 0; i < bArr2.length; i++) {
                bArr2[i] = (byte) ((getByte(bArr[i * 2]) << 4) + getByte(bArr[(i * 2) + 1]));
            }
            return new String(bArr2, "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static byte getByte(byte b) {
        if (b >= (byte) 48 && b <= (byte) 57) {
            return (byte) (b - 48);
        }
        if (b >= (byte) 97 && b <= (byte) 102) {
            return (byte) ((b - 97) + 10);
        }
        if (b < (byte) 65 || b > (byte) 70) {
            return (byte) 48;
        }
        return (byte) ((b - 65) + 10);
    }
}
