package com.qiniu.android.dns.local;

import com.facebook.imageutils.JfifUtil;
import com.qiniu.android.dns.DnsException;
import com.qiniu.android.dns.Record;
import com.qiniu.android.dns.util.BitSet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.IDN;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;

public final class DnsMessage {
    public static byte[] buildQuery(String str, int i) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        BitSet bitSet = new BitSet();
        bitSet.set(8);
        try {
            dataOutputStream.writeShort((short) i);
            dataOutputStream.writeShort((short) bitSet.value());
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.flush();
            b(byteArrayOutputStream, str);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private static void a(OutputStream outputStream, String str) throws IOException {
        for (String toASCII : str.split("[.。．｡]")) {
            byte[] bytes = IDN.toASCII(toASCII).getBytes();
            outputStream.write(bytes.length);
            outputStream.write(bytes, 0, bytes.length);
        }
        outputStream.write(0);
    }

    private static void b(OutputStream outputStream, String str) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        a(outputStream, str);
        dataOutputStream.writeShort(1);
        dataOutputStream.writeShort(1);
    }

    public static Record[] parseResponse(byte[] bArr, int i, String str) throws IOException {
        Object obj = 1;
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        if (readUnsignedShort != i) {
            throw new DnsException(str, "the answer id " + readUnsignedShort + " is not match " + i);
        }
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        Object obj2;
        if (((readUnsignedShort2 >> 8) & 1) == 1) {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        if (((readUnsignedShort2 >> 7) & 1) != 1) {
            obj = null;
        }
        if (obj == null || r2 == null) {
            throw new DnsException(str, "the dns server cant support recursion ");
        }
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        int readUnsignedShort4 = dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        a(dataInputStream, bArr, readUnsignedShort3);
        return b(dataInputStream, bArr, readUnsignedShort4);
    }

    private static String a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if ((readUnsignedByte & JfifUtil.MARKER_SOFn) == JfifUtil.MARKER_SOFn) {
            readUnsignedByte = ((readUnsignedByte & 63) << 8) + dataInputStream.readUnsignedByte();
            HashSet hashSet = new HashSet();
            hashSet.add(Integer.valueOf(readUnsignedByte));
            return a(bArr, readUnsignedByte, hashSet);
        } else if (readUnsignedByte == 0) {
            return "";
        } else {
            byte[] bArr2 = new byte[readUnsignedByte];
            dataInputStream.readFully(bArr2);
            String toUnicode = IDN.toUnicode(new String(bArr2));
            String a = a(dataInputStream, bArr);
            if (a.length() > 0) {
                return toUnicode + "." + a;
            }
            return toUnicode;
        }
    }

    private static String a(byte[] bArr, int i, HashSet<Integer> hashSet) throws IOException {
        int i2 = bArr[i] & 255;
        if ((i2 & JfifUtil.MARKER_SOFn) == JfifUtil.MARKER_SOFn) {
            int i3 = ((i2 & 63) << 8) + (bArr[i + 1] & 255);
            if (hashSet.contains(Integer.valueOf(i3))) {
                throw new DnsException("", "Cyclic offsets detected.");
            }
            hashSet.add(Integer.valueOf(i3));
            return a(bArr, i3, (HashSet) hashSet);
        } else if (i2 == 0) {
            return "";
        } else {
            String str = new String(bArr, i + 1, i2);
            String a = a(bArr, i2 + (i + 1), (HashSet) hashSet);
            if (a.length() > 0) {
                return str + "." + a;
            }
            return str;
        }
    }

    private static void a(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                a(dataInputStream, bArr);
                dataInputStream.readUnsignedShort();
                dataInputStream.readUnsignedShort();
                i = i2;
            } else {
                return;
            }
        }
    }

    private static Record[] b(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        int i2 = 0;
        Record[] recordArr = new Record[i];
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                return recordArr;
            }
            int i4 = i2 + 1;
            recordArr[i2] = b(dataInputStream, bArr);
            i2 = i4;
            i = i3;
        }
    }

    private static Record b(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        String hostAddress;
        a(dataInputStream, bArr);
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        long readUnsignedShort2 = ((long) dataInputStream.readUnsignedShort()) + (((long) dataInputStream.readUnsignedShort()) << 16);
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        switch (readUnsignedShort) {
            case 1:
                byte[] bArr2 = new byte[4];
                dataInputStream.readFully(bArr2);
                hostAddress = InetAddress.getByAddress(bArr2).getHostAddress();
                break;
            case 5:
                hostAddress = a(dataInputStream, bArr);
                break;
            default:
                hostAddress = null;
                for (int i = 0; i < readUnsignedShort3; i++) {
                    dataInputStream.readByte();
                }
                break;
        }
        if (hostAddress != null) {
            return new Record(hostAddress, readUnsignedShort, (int) readUnsignedShort2, System.currentTimeMillis() / 1000);
        }
        throw new UnknownHostException("no record");
    }
}
