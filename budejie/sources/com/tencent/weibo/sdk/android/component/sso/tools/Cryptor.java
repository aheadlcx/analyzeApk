package com.tencent.weibo.sdk.android.component.sso.tools;

import android.support.v4.internal.view.SupportMenu;
import com.budejie.www.R$styleable;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class Cryptor {
    public static final int QUOTIENT = 79764919;
    public static final int SALT_LEN = 2;
    public static final int ZERO_LEN = 7;
    private int contextStart;
    private int crypt;
    private boolean header = true;
    private byte[] key;
    private byte[] out;
    private int padding;
    private byte[] plain;
    private int pos;
    private int preCrypt;
    private byte[] prePlain;
    private Random random = new Random();

    public static byte[] MD5Hash(byte[] bArr, int i) {
        return new byte[2];
    }

    public static int CRC32Hash(byte[] bArr) {
        int length = bArr.length;
        int i = -1;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i;
            i = bArr[i2];
            for (int i4 = 0; i4 < 8; i4++) {
                if (((i ^ i3) >>> 31) == 1) {
                    i3 = (i3 << 1) ^ QUOTIENT;
                } else {
                    i3 <<= 1;
                }
                i = (byte) (i << 1);
            }
            i2++;
            i = i3;
        }
        return i ^ -1;
    }

    public static byte[] _4bytesEncryptAFrame(int i, byte[] bArr) {
        short[] sArr = new short[]{(short) (SupportMenu.USER_MASK & i), (short) (i >>> 16)};
        short[] sArr2 = new short[]{(short) ((bArr[1] << 8) | bArr[0]), (short) ((bArr[3] << 8) | bArr[2]), (short) ((bArr[5] << 8) | bArr[4]), (short) ((bArr[7] << 8) | bArr[6])};
        short s = sArr[0];
        short s2 = sArr[1];
        int i2 = 32;
        int i3 = s2;
        int i4 = s;
        int i5 = 0;
        while (true) {
            s2 = (short) (i2 - 1);
            if (i2 <= 0) {
                return new byte[]{(byte) (i3 >> 8), (byte) (i3 & 255), (byte) (i4 >> 8), (byte) (i4 & 255)};
            }
            short s3 = (short) (i5 + 12895);
            i4 = (short) (((((i3 << 4) + sArr2[0]) ^ (i3 + s3)) ^ ((i3 >> 5) + sArr2[1])) + i4);
            short s4 = (short) (((((i4 << 4) + sArr2[2]) ^ (i4 + s3)) ^ ((i4 >> 5) + sArr2[3])) + i3);
            s = s3;
            s3 = s2;
        }
    }

    public static int _4bytesDecryptAFrame(long j, byte[] bArr) {
        short[] sArr = new short[]{(short) ((int) (65535 & j)), (short) ((int) (j >> 16))};
        short[] sArr2 = new short[]{(short) ((bArr[1] << 8) | bArr[0]), (short) ((bArr[3] << 8) | bArr[2]), (short) ((bArr[5] << 8) | bArr[4]), (short) ((bArr[7] << 8) | bArr[6])};
        int i = 32;
        short s = sArr[0];
        short s2 = sArr[1];
        int i2 = (short) 412640;
        while (true) {
            short s3 = (short) (i - 1);
            if (i <= 0) {
                sArr[0] = s;
                sArr[1] = s2;
                return (sArr[1] << 16) | (sArr[0] & SupportMenu.USER_MASK);
            }
            s2 = (short) (s2 - ((((s << 4) + sArr2[2]) ^ (s + i2)) ^ ((s >> 5) + sArr2[3])));
            s = (short) (s - ((((s2 << 4) + sArr2[0]) ^ (s2 + i2)) ^ ((s2 >> 5) + sArr2[1])));
            i2 = (short) (i2 - 12895);
            short s4 = s3;
        }
    }

    public static long getUnsignedInt(byte[] bArr, int i, int i2) {
        int i3;
        long j = 0;
        if (i2 > 8) {
            i3 = i + 8;
        } else {
            i3 = i + i2;
        }
        while (i < i3) {
            j = (j << 8) | ((long) (bArr[i] & 255));
            i++;
        }
        return (4294967295L & j) | (j >>> 32);
    }

    public byte[] decrypt(byte[] bArr, int i, int i2, byte[] bArr2) {
        this.preCrypt = 0;
        this.crypt = 0;
        this.key = bArr2;
        byte[] bArr3 = new byte[(i + 8)];
        if (i2 % 8 != 0 || i2 < 16) {
            return null;
        }
        this.prePlain = decipher(bArr, i);
        this.pos = this.prePlain[0] & 7;
        int i3 = (i2 - this.pos) - 10;
        if (i3 < 0) {
            return null;
        }
        int i4;
        for (i4 = i; i4 < bArr3.length; i4++) {
            bArr3[i4] = (byte) 0;
        }
        this.out = new byte[i3];
        this.preCrypt = 0;
        this.crypt = 8;
        this.contextStart = 8;
        this.pos++;
        this.padding = 1;
        byte[] bArr4 = bArr3;
        while (this.padding <= 2) {
            if (this.pos < 8) {
                this.pos++;
                this.padding++;
            }
            if (this.pos == 8) {
                if (!decrypt8Bytes(bArr, i, i2)) {
                    return null;
                }
                bArr4 = bArr;
            }
        }
        int i5 = i3;
        byte[] bArr5 = bArr4;
        i4 = 0;
        byte[] bArr6 = bArr5;
        while (i5 != 0) {
            if (this.pos < 8) {
                this.out[i4] = (byte) (bArr6[(this.preCrypt + i) + this.pos] ^ this.prePlain[this.pos]);
                i4++;
                i5--;
                this.pos++;
            }
            if (this.pos == 8) {
                this.preCrypt = this.crypt - 8;
                if (!decrypt8Bytes(bArr, i, i2)) {
                    return null;
                }
                bArr6 = bArr;
            }
        }
        this.padding = 1;
        bArr4 = bArr6;
        while (this.padding < 8) {
            if (this.pos < 8) {
                if ((bArr4[(this.preCrypt + i) + this.pos] ^ this.prePlain[this.pos]) != 0) {
                    return null;
                }
                this.pos++;
            }
            if (this.pos == 8) {
                this.preCrypt = this.crypt;
                if (!decrypt8Bytes(bArr, i, i2)) {
                    return null;
                }
                bArr4 = bArr;
            }
            this.padding++;
        }
        return this.out;
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return decrypt(bArr, 0, bArr.length, bArr2);
    }

    public byte[] encrypt(byte[] bArr, int i, int i2, byte[] bArr2) {
        int i3;
        int i4;
        this.plain = new byte[8];
        this.prePlain = new byte[8];
        this.pos = 1;
        this.padding = 0;
        this.preCrypt = 0;
        this.crypt = 0;
        this.key = bArr2;
        this.header = true;
        this.pos = (i2 + 10) % 8;
        if (this.pos != 0) {
            this.pos = 8 - this.pos;
        }
        this.out = new byte[((this.pos + i2) + 10)];
        this.plain[0] = (byte) ((rand() & R$styleable.Theme_Custom_top_suiji_btn_bg_2) | this.pos);
        for (i3 = 1; i3 <= this.pos; i3++) {
            this.plain[i3] = (byte) (rand() & 255);
        }
        this.pos++;
        for (i3 = 0; i3 < 8; i3++) {
            this.prePlain[i3] = (byte) 0;
        }
        this.padding = 1;
        while (this.padding <= 2) {
            if (this.pos < 8) {
                byte[] bArr3 = this.plain;
                i4 = this.pos;
                this.pos = i4 + 1;
                bArr3[i4] = (byte) (rand() & 255);
                this.padding++;
            }
            if (this.pos == 8) {
                encrypt8Bytes();
            }
        }
        i4 = i;
        int i5 = i2;
        while (i5 > 0) {
            if (this.pos < 8) {
                byte[] bArr4 = this.plain;
                int i6 = this.pos;
                this.pos = i6 + 1;
                i3 = i4 + 1;
                bArr4[i6] = bArr[i4];
                i4 = i5 - 1;
            } else {
                i3 = i4;
                i4 = i5;
            }
            if (this.pos == 8) {
                encrypt8Bytes();
            }
            i5 = i4;
            i4 = i3;
        }
        this.padding = 1;
        while (this.padding <= 7) {
            if (this.pos < 8) {
                bArr3 = this.plain;
                int i7 = this.pos;
                this.pos = i7 + 1;
                bArr3[i7] = (byte) 0;
                this.padding++;
            }
            if (this.pos == 8) {
                encrypt8Bytes();
            }
        }
        return this.out;
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) {
        return encrypt(bArr, 0, bArr.length, bArr2);
    }

    private byte[] encipher(byte[] bArr) {
        int i = 16;
        try {
            long unsignedInt = getUnsignedInt(bArr, 0, 4);
            long unsignedInt2 = getUnsignedInt(bArr, 4, 4);
            long unsignedInt3 = getUnsignedInt(this.key, 0, 4);
            long unsignedInt4 = getUnsignedInt(this.key, 4, 4);
            long unsignedInt5 = getUnsignedInt(this.key, 8, 4);
            long unsignedInt6 = getUnsignedInt(this.key, 12, 4);
            long j = 0;
            long j2 = -1640531527 & 4294967295L;
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
                    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    dataOutputStream.writeInt((int) unsignedInt);
                    dataOutputStream.writeInt((int) unsignedInt2);
                    dataOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                j = (j + j2) & 4294967295L;
                unsignedInt = (unsignedInt + ((((unsignedInt2 << 4) + unsignedInt3) ^ (unsignedInt2 + j)) ^ ((unsignedInt2 >>> 5) + unsignedInt4))) & 4294967295L;
                unsignedInt2 = (unsignedInt2 + ((((unsignedInt << 4) + unsignedInt5) ^ (unsignedInt + j)) ^ ((unsignedInt >>> 5) + unsignedInt6))) & 4294967295L;
                i = i2;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private byte[] decipher(byte[] bArr, int i) {
        int i2 = 16;
        try {
            long unsignedInt = getUnsignedInt(bArr, i, 4);
            long unsignedInt2 = getUnsignedInt(bArr, i + 4, 4);
            long unsignedInt3 = getUnsignedInt(this.key, 0, 4);
            long unsignedInt4 = getUnsignedInt(this.key, 4, 4);
            long unsignedInt5 = getUnsignedInt(this.key, 8, 4);
            long unsignedInt6 = getUnsignedInt(this.key, 12, 4);
            long j = -478700656 & 4294967295L;
            long j2 = -1640531527 & 4294967295L;
            while (true) {
                int i3 = i2 - 1;
                if (i2 <= 0) {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
                    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    dataOutputStream.writeInt((int) unsignedInt);
                    dataOutputStream.writeInt((int) unsignedInt2);
                    dataOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                unsignedInt2 = (unsignedInt2 - ((((unsignedInt << 4) + unsignedInt5) ^ (unsignedInt + j)) ^ ((unsignedInt >>> 5) + unsignedInt6))) & 4294967295L;
                unsignedInt = (unsignedInt - ((((unsignedInt2 << 4) + unsignedInt3) ^ (unsignedInt2 + j)) ^ ((unsignedInt2 >>> 5) + unsignedInt4))) & 4294967295L;
                j = (j - j2) & 4294967295L;
                i2 = i3;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private byte[] decipher(byte[] bArr) {
        return decipher(bArr, 0);
    }

    private void encrypt8Bytes() {
        this.pos = 0;
        while (this.pos < 8) {
            byte[] bArr;
            int i;
            if (this.header) {
                bArr = this.plain;
                i = this.pos;
                bArr[i] = (byte) (bArr[i] ^ this.prePlain[this.pos]);
            } else {
                bArr = this.plain;
                i = this.pos;
                bArr[i] = (byte) (bArr[i] ^ this.out[this.preCrypt + this.pos]);
            }
            this.pos++;
        }
        System.arraycopy(encipher(this.plain), 0, this.out, this.crypt, 8);
        this.pos = 0;
        while (this.pos < 8) {
            bArr = this.out;
            i = this.crypt + this.pos;
            bArr[i] = (byte) (bArr[i] ^ this.prePlain[this.pos]);
            this.pos++;
        }
        System.arraycopy(this.plain, 0, this.prePlain, 0, 8);
        this.preCrypt = this.crypt;
        this.crypt += 8;
        this.pos = 0;
        this.header = false;
    }

    private boolean decrypt8Bytes(byte[] bArr, int i, int i2) {
        this.pos = 0;
        while (this.pos < 8) {
            if (this.contextStart + this.pos >= i2) {
                return true;
            }
            byte[] bArr2 = this.prePlain;
            int i3 = this.pos;
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr[(this.crypt + i) + this.pos]);
            this.pos++;
        }
        this.prePlain = decipher(this.prePlain);
        if (this.prePlain == null) {
            return false;
        }
        this.contextStart += 8;
        this.crypt += 8;
        this.pos = 0;
        return true;
    }

    private int rand() {
        return this.random.nextInt();
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2, int i) {
        byte[] decrypt = decrypt(bArr, 0, bArr.length, bArr2);
        return decrypt == null ? getRandomByte(i) : decrypt;
    }

    private byte[] getRandomByte(int i) {
        byte[] bArr = new byte[i];
        this.random.nextBytes(bArr);
        return bArr;
    }
}
