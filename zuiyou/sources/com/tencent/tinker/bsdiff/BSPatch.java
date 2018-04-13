package com.tencent.tinker.bsdiff;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;

public class BSPatch {
    public static final int RETURN_DIFF_FILE_ERR = 2;
    public static final int RETURN_NEW_FILE_ERR = 4;
    public static final int RETURN_OLD_FILE_ERR = 3;
    public static final int RETURN_SUCCESS = 1;

    public static int patchLessMemory(RandomAccessFile randomAccessFile, File file, File file2, int i) throws IOException {
        if (randomAccessFile == null || randomAccessFile.length() <= 0) {
            return 3;
        }
        if (file == null) {
            return 4;
        }
        if (file2 == null || file2.length() <= 0) {
            return 2;
        }
        byte[] bArr = new byte[((int) file2.length())];
        InputStream fileInputStream = new FileInputStream(file2);
        try {
            BSUtil.readFromStream(fileInputStream, bArr, 0, bArr.length);
            return patchLessMemory(randomAccessFile, (int) randomAccessFile.length(), bArr, bArr.length, file, i);
        } finally {
            fileInputStream.close();
        }
    }

    public static int patchLessMemory(RandomAccessFile randomAccessFile, int i, byte[] bArr, int i2, File file, int i3) throws IOException {
        if (randomAccessFile == null || i <= 0) {
            return 3;
        }
        if (file == null) {
            return 4;
        }
        if (bArr == null || i2 <= 0) {
            return 2;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr, 0, i2));
        dataInputStream.skip(8);
        long readLong = dataInputStream.readLong();
        long readLong2 = dataInputStream.readLong();
        int readLong3 = (int) dataInputStream.readLong();
        dataInputStream.close();
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr, 0, i2);
        byteArrayInputStream.skip(32);
        DataInputStream dataInputStream2 = new DataInputStream(new GZIPInputStream(byteArrayInputStream));
        byteArrayInputStream = new ByteArrayInputStream(bArr, 0, i2);
        byteArrayInputStream.skip(32 + readLong);
        InputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byteArrayInputStream = new ByteArrayInputStream(bArr, 0, i2);
        byteArrayInputStream.skip((readLong + readLong2) + 32);
        InputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
        OutputStream fileOutputStream = new FileOutputStream(file);
        int[] iArr = new int[3];
        int i4 = 0;
        int i5 = 0;
        while (i5 < readLong3) {
            int i6;
            for (i6 = 0; i6 <= 2; i6++) {
                iArr[i6] = dataInputStream2.readInt();
            }
            if (iArr[0] + i5 > readLong3) {
                fileOutputStream.close();
                randomAccessFile.close();
                fileOutputStream.close();
                return 2;
            }
            byte[] bArr2 = new byte[iArr[0]];
            if (BSUtil.readFromStream(gZIPInputStream, bArr2, 0, iArr[0])) {
                byte[] bArr3 = new byte[iArr[0]];
                if (randomAccessFile.read(bArr3, 0, iArr[0]) < iArr[0]) {
                    fileOutputStream.close();
                    randomAccessFile.close();
                    fileOutputStream.close();
                    return 2;
                }
                i6 = 0;
                while (i6 < iArr[0]) {
                    try {
                        if (i4 + i6 >= 0 && i4 + i6 < i) {
                            bArr2[i6] = (byte) (bArr2[i6] + bArr3[i6]);
                        }
                        i6++;
                    } finally {
                        randomAccessFile.close();
                        fileOutputStream.close();
                    }
                }
                fileOutputStream.write(bArr2);
                i6 = iArr[0] + i5;
                i5 = iArr[0] + i4;
                if (iArr[1] + i6 > readLong3) {
                    fileOutputStream.close();
                    randomAccessFile.close();
                    fileOutputStream.close();
                    return 2;
                }
                byte[] bArr4 = new byte[iArr[1]];
                if (BSUtil.readFromStream(gZIPInputStream2, bArr4, 0, iArr[1])) {
                    fileOutputStream.write(bArr4);
                    fileOutputStream.flush();
                    i6 += iArr[1];
                    i5 += iArr[2];
                    randomAccessFile.seek((long) i5);
                    i4 = i5;
                    i5 = i6;
                } else {
                    fileOutputStream.close();
                    return 2;
                }
            }
            fileOutputStream.close();
            randomAccessFile.close();
            fileOutputStream.close();
            return 2;
        }
        dataInputStream2.close();
        gZIPInputStream.close();
        gZIPInputStream2.close();
        randomAccessFile.close();
        fileOutputStream.close();
        return 1;
    }

    public static int patchFast(File file, File file2, File file3, int i) throws IOException {
        if (file == null || file.length() <= 0) {
            return 3;
        }
        if (file2 == null) {
            return 4;
        }
        if (file3 == null || file3.length() <= 0) {
            return 2;
        }
        InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] bArr = new byte[((int) file3.length())];
        InputStream fileInputStream = new FileInputStream(file3);
        try {
            BSUtil.readFromStream(fileInputStream, bArr, 0, bArr.length);
            byte[] patchFast = patchFast(bufferedInputStream, (int) file.length(), bArr, i);
            OutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                fileOutputStream.write(patchFast);
                return 1;
            } finally {
                fileOutputStream.close();
            }
        } finally {
            fileInputStream.close();
        }
    }

    public static int patchFast(InputStream inputStream, InputStream inputStream2, File file) throws IOException {
        if (inputStream == null) {
            return 3;
        }
        if (file == null) {
            return 4;
        }
        if (inputStream2 == null) {
            return 2;
        }
        byte[] inputStreamToByte = BSUtil.inputStreamToByte(inputStream);
        byte[] inputStreamToByte2 = BSUtil.inputStreamToByte(inputStream2);
        inputStreamToByte = patchFast(inputStreamToByte, inputStreamToByte.length, inputStreamToByte2, inputStreamToByte2.length, 0);
        OutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(inputStreamToByte);
            return 1;
        } finally {
            fileOutputStream.close();
        }
    }

    public static byte[] patchFast(InputStream inputStream, InputStream inputStream2) throws IOException {
        if (inputStream == null || inputStream2 == null) {
            return null;
        }
        byte[] inputStreamToByte = BSUtil.inputStreamToByte(inputStream);
        byte[] inputStreamToByte2 = BSUtil.inputStreamToByte(inputStream2);
        return patchFast(inputStreamToByte, inputStreamToByte.length, inputStreamToByte2, inputStreamToByte2.length, 0);
    }

    public static byte[] patchFast(InputStream inputStream, int i, byte[] bArr, int i2) throws IOException {
        byte[] bArr2 = new byte[i];
        BSUtil.readFromStream(inputStream, bArr2, 0, i);
        inputStream.close();
        return patchFast(bArr2, i, bArr, bArr.length, i2);
    }

    public static byte[] patchFast(byte[] bArr, int i, byte[] bArr2, int i2, int i3) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr2, 0, i2));
        dataInputStream.skip(8);
        long readLong = dataInputStream.readLong();
        long readLong2 = dataInputStream.readLong();
        int readLong3 = (int) dataInputStream.readLong();
        dataInputStream.close();
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr2, 0, i2);
        byteArrayInputStream.skip(32);
        DataInputStream dataInputStream2 = new DataInputStream(new GZIPInputStream(byteArrayInputStream));
        byteArrayInputStream = new ByteArrayInputStream(bArr2, 0, i2);
        byteArrayInputStream.skip(32 + readLong);
        InputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byteArrayInputStream = new ByteArrayInputStream(bArr2, 0, i2);
        byteArrayInputStream.skip((readLong + readLong2) + 32);
        InputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr3 = new byte[readLong3];
        int[] iArr = new int[3];
        int i4 = 0;
        int i5 = 0;
        while (i5 < readLong3) {
            int i6;
            for (i6 = 0; i6 <= 2; i6++) {
                iArr[i6] = dataInputStream2.readInt();
            }
            if (iArr[0] + i5 > readLong3) {
                throw new IOException("Corrupt by wrong patch file.");
            } else if (BSUtil.readFromStream(gZIPInputStream, bArr3, i5, iArr[0])) {
                i6 = 0;
                while (i6 < iArr[0]) {
                    if (i4 + i6 >= 0 && i4 + i6 < i) {
                        int i7 = i5 + i6;
                        bArr3[i7] = (byte) (bArr3[i7] + bArr[i4 + i6]);
                    }
                    i6++;
                }
                i6 = iArr[0] + i5;
                i5 = iArr[0] + i4;
                if (iArr[1] + i6 > readLong3) {
                    throw new IOException("Corrupt by wrong patch file.");
                } else if (BSUtil.readFromStream(gZIPInputStream2, bArr3, i6, iArr[1])) {
                    i4 = i5 + iArr[2];
                    i5 = i6 + iArr[1];
                } else {
                    throw new IOException("Corrupt by wrong patch file.");
                }
            } else {
                throw new IOException("Corrupt by wrong patch file.");
            }
        }
        dataInputStream2.close();
        gZIPInputStream.close();
        gZIPInputStream2.close();
        return bArr3;
    }
}
