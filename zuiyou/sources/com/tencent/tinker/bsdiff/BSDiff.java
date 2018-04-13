package com.tencent.tinker.bsdiff;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class BSDiff {
    private static final byte[] MAGIC_BYTES = new byte[]{(byte) 77, (byte) 105, (byte) 99, (byte) 114, (byte) 111, (byte) 77, (byte) 115, (byte) 103};

    private static class a {
        private int a;

        private a() {
        }
    }

    private static void split(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4 = 0;
        int i5;
        int i6;
        int i7;
        int i8;
        if (i2 < 16) {
            for (i5 = i; i5 < i + i2; i5 += i6) {
                i7 = iArr2[iArr[i5] + i3];
                i6 = 1;
                for (i8 = 1; i5 + i8 < i + i2; i8++) {
                    if (iArr2[iArr[i5 + i8] + i3] < i7) {
                        i7 = iArr2[iArr[i5 + i8] + i3];
                        i6 = 0;
                    }
                    if (iArr2[iArr[i5 + i8] + i3] == i7) {
                        int i9 = iArr[i5 + i6];
                        iArr[i5 + i6] = iArr[i5 + i8];
                        iArr[i5 + i8] = i9;
                        i6++;
                    }
                }
                for (i7 = 0; i7 < i6; i7++) {
                    iArr2[iArr[i5 + i7]] = (i5 + i6) - 1;
                }
                if (i6 == 1) {
                    iArr[i5] = -1;
                }
            }
            return;
        }
        int i10 = iArr2[iArr[(i2 / 2) + i] + i3];
        i6 = 0;
        i7 = 0;
        for (i8 = i; i8 < i + i2; i8++) {
            if (iArr2[iArr[i8] + i3] < i10) {
                i7++;
            }
            if (iArr2[iArr[i8] + i3] == i10) {
                i6++;
            }
        }
        i5 = i7 + i;
        i9 = i6 + i5;
        i8 = i;
        i7 = 0;
        i6 = 0;
        while (i8 < i5) {
            if (iArr2[iArr[i8] + i3] < i10) {
                i8++;
            } else if (iArr2[iArr[i8] + i3] == i10) {
                r7 = iArr[i8];
                iArr[i8] = iArr[i5 + i7];
                iArr[i5 + i7] = r7;
                i7++;
            } else {
                r7 = iArr[i8];
                iArr[i8] = iArr[i9 + i6];
                iArr[i9 + i6] = r7;
                i6++;
            }
        }
        while (i5 + i7 < i9) {
            if (iArr2[iArr[i5 + i7] + i3] == i10) {
                i7++;
            } else {
                i8 = iArr[i5 + i7];
                iArr[i5 + i7] = iArr[i9 + i6];
                iArr[i9 + i6] = i8;
                i6++;
            }
        }
        if (i5 > i) {
            split(iArr, iArr2, i, i5 - i, i3);
        }
        while (i4 < i9 - i5) {
            iArr2[iArr[i5 + i4]] = i9 - 1;
            i4++;
        }
        if (i5 == i9 - 1) {
            iArr[i5] = -1;
        }
        if (i + i2 > i9) {
            split(iArr, iArr2, i9, (i + i2) - i9, i3);
        }
    }

    private static void qsufsort(int[] iArr, int[] iArr2, byte[] bArr, int i) {
        int i2;
        int i3 = 1;
        int[] iArr3 = new int[256];
        for (i2 = 0; i2 < i; i2++) {
            int i4 = bArr[i2] & 255;
            iArr3[i4] = iArr3[i4] + 1;
        }
        for (i2 = 1; i2 < 256; i2++) {
            iArr3[i2] = iArr3[i2] + iArr3[i2 - 1];
        }
        for (i2 = 255; i2 > 0; i2--) {
            iArr3[i2] = iArr3[i2 - 1];
        }
        iArr3[0] = 0;
        for (i2 = 0; i2 < i; i2++) {
            i4 = bArr[i2] & 255;
            int i5 = iArr3[i4] + 1;
            iArr3[i4] = i5;
            iArr[i5] = i2;
        }
        iArr[0] = i;
        for (i2 = 0; i2 < i; i2++) {
            iArr2[i2] = iArr3[bArr[i2] & 255];
        }
        iArr2[i] = 0;
        for (i2 = 1; i2 < 256; i2++) {
            if (iArr3[i2] == iArr3[i2 - 1] + 1) {
                iArr[iArr3[i2]] = -1;
            }
        }
        iArr[0] = -1;
        while (iArr[0] != (-(i + 1))) {
            i2 = 0;
            int i6 = 0;
            while (i2 < i + 1) {
                if (iArr[i2] < 0) {
                    i6 -= iArr[i2];
                    i2 -= iArr[i2];
                } else {
                    if (i6 != 0) {
                        iArr[i2 - i6] = -i6;
                    }
                    i6 = (iArr2[iArr[i2]] + 1) - i2;
                    split(iArr, iArr2, i2, i6, i3);
                    i2 += i6;
                    i6 = 0;
                }
            }
            if (i6 != 0) {
                iArr[i2 - i6] = -i6;
            }
            i3 += i3;
        }
        for (i3 = 0; i3 < i + 1; i3++) {
            iArr[iArr2[i3]] = i3;
        }
    }

    private static int search(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2, int i3, int i4, int i5, a aVar) {
        int matchlen;
        if (i5 - i4 < 2) {
            matchlen = matchlen(bArr, i, iArr[i4], bArr2, i2, i3);
            int matchlen2 = matchlen(bArr, i, iArr[i5], bArr2, i2, i3);
            if (matchlen > matchlen2) {
                aVar.a = iArr[i4];
                return matchlen;
            }
            aVar.a = iArr[i5];
            return matchlen2;
        }
        matchlen = i4 + ((i5 - i4) / 2);
        if (memcmp(bArr, i, iArr[matchlen], bArr2, i2, i3) < 0) {
            return search(iArr, bArr, i, bArr2, i2, i3, matchlen, i5, aVar);
        }
        return search(iArr, bArr, i, bArr2, i2, i3, i4, matchlen, aVar);
    }

    private static int matchlen(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int min = Math.min(i - i2, i3 - i4);
        for (int i5 = 0; i5 < min; i5++) {
            if (bArr[i2 + i5] != bArr2[i4 + i5]) {
                return i5;
            }
        }
        return min;
    }

    private static int memcmp(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = i - i2;
        if (i5 > i3 - i4) {
            i5 = i3 - i4;
        }
        int i6 = 0;
        while (i6 < i5) {
            if (bArr[i6 + i2] != bArr2[i6 + i4]) {
                return bArr[i6 + i2] < bArr2[i6 + i4] ? -1 : 1;
            } else {
                i6++;
            }
        }
        return 0;
    }

    public static void bsdiff(File file, File file2, File file3) throws IOException {
        InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        InputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file2));
        OutputStream fileOutputStream = new FileOutputStream(file3);
        try {
            fileOutputStream.write(bsdiff(bufferedInputStream, (int) file.length(), bufferedInputStream2, (int) file2.length()));
        } finally {
            fileOutputStream.close();
        }
    }

    public static byte[] bsdiff(InputStream inputStream, int i, InputStream inputStream2, int i2) throws IOException {
        byte[] bArr = new byte[i];
        BSUtil.readFromStream(inputStream, bArr, 0, i);
        inputStream.close();
        byte[] bArr2 = new byte[i2];
        BSUtil.readFromStream(inputStream2, bArr2, 0, i2);
        inputStream2.close();
        return bsdiff(bArr, i, bArr2, i2);
    }

    public static byte[] bsdiff(byte[] bArr, int i, byte[] bArr2, int i2) throws IOException {
        int[] iArr = new int[(i + 1)];
        qsufsort(iArr, new int[(i + 1)], bArr, i);
        byte[] bArr3 = new byte[i2];
        byte[] bArr4 = new byte[i2];
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.write(MAGIC_BYTES);
        dataOutputStream.writeLong(-1);
        dataOutputStream.writeLong(-1);
        dataOutputStream.writeLong((long) i2);
        dataOutputStream.flush();
        dataOutputStream = new GZIPOutputStream(dataOutputStream);
        DataOutputStream dataOutputStream2 = new DataOutputStream(dataOutputStream);
        int i3 = 0;
        a aVar = new a();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i3 < i2) {
            int i10 = 0;
            int i11 = i3 + i9;
            i3 = i11;
            while (i3 < i2) {
                int search = search(iArr, bArr, i, bArr2, i2, i3, 0, i, aVar);
                int i12 = i11;
                i9 = i10;
                while (i12 < i3 + search) {
                    if (i12 + i4 < i && bArr[i12 + i4] == bArr2[i12]) {
                        i9++;
                    }
                    i12++;
                }
                if (search == i9 && search != 0) {
                    i11 = search;
                    break;
                } else if (search > i9 + 8) {
                    i11 = search;
                    break;
                } else {
                    if (i3 + i4 < i && bArr[i3 + i4] == bArr2[i3]) {
                        i9--;
                    }
                    i3++;
                    i11 = i12;
                    i10 = i9;
                    i9 = search;
                }
            }
            i11 = i9;
            i9 = i10;
            if (i11 != i9 || i3 == i2) {
                int i13;
                int i14 = 0;
                i9 = 0;
                i12 = 0;
                search = 0;
                while (i6 + search < i3 && i5 + search < i) {
                    if (bArr[i5 + search] == bArr2[i6 + search]) {
                        i9++;
                    }
                    search++;
                    if ((i9 * 2) - search > (i12 * 2) - i14) {
                        i14 = search;
                        i12 = i9;
                    }
                }
                i12 = 0;
                if (i3 < i2) {
                    search = 0;
                    i9 = 0;
                    i13 = 1;
                    while (i3 >= i6 + i13 && aVar.a >= i13) {
                        if (bArr[aVar.a - i13] == bArr2[i3 - i13]) {
                            search++;
                        }
                        if ((search * 2) - i13 > (i9 * 2) - i12) {
                            i9 = search;
                            i12 = i13;
                        }
                        i13++;
                    }
                }
                int i15 = i12;
                if (i6 + i14 > i3 - i15) {
                    i4 = (i6 + i14) - (i3 - i15);
                    i13 = 0;
                    search = 0;
                    i10 = 0;
                    i9 = 0;
                    while (i10 < i4) {
                        if (bArr2[((i6 + i14) - i4) + i10] == bArr[((i5 + i14) - i4) + i10]) {
                            i9++;
                        }
                        if (bArr2[(i3 - i15) + i10] == bArr[(aVar.a - i15) + i10]) {
                            i12 = i9 - 1;
                        } else {
                            i12 = i9;
                        }
                        if (i12 > i13) {
                            i9 = i10 + 1;
                            search = i12;
                        } else {
                            i9 = search;
                            search = i13;
                        }
                        i10++;
                        i13 = search;
                        search = i9;
                        i9 = i12;
                    }
                    i9 = i15 - search;
                    search = i14 + (search - i4);
                } else {
                    i9 = i15;
                    search = i14;
                }
                for (i12 = 0; i12 < search; i12++) {
                    bArr3[i8 + i12] = (byte) (bArr2[i6 + i12] - bArr[i5 + i12]);
                }
                for (i12 = 0; i12 < (i3 - i9) - (i6 + search); i12++) {
                    bArr4[i7 + i12] = bArr2[(i6 + search) + i12];
                }
                i15 = i8 + search;
                i13 = i7 + ((i3 - i9) - (i6 + search));
                dataOutputStream2.writeInt(search);
                dataOutputStream2.writeInt((i3 - i9) - (i6 + search));
                dataOutputStream2.writeInt((aVar.a - i9) - (search + i5));
                i4 = aVar.a - i3;
                i5 = aVar.a - i9;
                i6 = i3 - i9;
                i7 = i13;
                i8 = i15;
                i9 = i11;
            } else {
                i9 = i11;
            }
        }
        dataOutputStream2.flush();
        dataOutputStream.finish();
        int size = dataOutputStream.size() - 32;
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(dataOutputStream);
        gZIPOutputStream.write(bArr3, 0, i8);
        gZIPOutputStream.finish();
        gZIPOutputStream.flush();
        i9 = (dataOutputStream.size() - size) - 32;
        GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(dataOutputStream);
        gZIPOutputStream2.write(bArr4, 0, i7);
        gZIPOutputStream2.finish();
        gZIPOutputStream2.flush();
        dataOutputStream.close();
        OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(32);
        DataOutputStream dataOutputStream3 = new DataOutputStream(byteArrayOutputStream2);
        dataOutputStream3.write(MAGIC_BYTES);
        dataOutputStream3.writeLong((long) size);
        dataOutputStream3.writeLong((long) i9);
        dataOutputStream3.writeLong((long) i2);
        dataOutputStream3.close();
        Object toByteArray = byteArrayOutputStream.toByteArray();
        Object toByteArray2 = byteArrayOutputStream2.toByteArray();
        System.arraycopy(toByteArray2, 0, toByteArray, 0, toByteArray2.length);
        return toByteArray;
    }
}
