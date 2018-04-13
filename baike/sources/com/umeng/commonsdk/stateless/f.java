package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.ar;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.statistics.common.e;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.zip.Deflater;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.model.CustomButton;

public class f {
    public static int a;
    private static final byte[] b = new byte[]{(byte) 10, (byte) 1, (byte) 11, (byte) 5, (byte) 4, ar.m, (byte) 7, (byte) 9, (byte) 23, (byte) 3, (byte) 1, (byte) 6, (byte) 8, (byte) 12, (byte) 13, (byte) 91};
    private static Object c = new Object();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r11, java.lang.String r12, java.lang.String r13, byte[] r14) {
        /*
        r3 = 0;
        r0 = 1;
        r2 = 0;
        if (r11 == 0) goto L_0x01d0;
    L_0x0005:
        r5 = c;	 Catch:{ IOException -> 0x01be, Throwable -> 0x012f, all -> 0x0180 }
        monitor-enter(r5);	 Catch:{ IOException -> 0x01be, Throwable -> 0x012f, all -> 0x0180 }
        r1 = "walle";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x00da }
        r6 = 0;
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r7.<init>();	 Catch:{ all -> 0x00da }
        r8 = "[stateless] begin write envelope, thread is ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x00da }
        r8 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x00da }
        r7 = r7.append(r8);	 Catch:{ all -> 0x00da }
        r7 = r7.toString();	 Catch:{ all -> 0x00da }
        r4[r6] = r7;	 Catch:{ all -> 0x00da }
        com.umeng.commonsdk.statistics.common.e.a(r1, r4);	 Catch:{ all -> 0x00da }
        r1 = new java.io.File;	 Catch:{ all -> 0x00da }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r4.<init>();	 Catch:{ all -> 0x00da }
        r6 = r11.getFilesDir();	 Catch:{ all -> 0x00da }
        r4 = r4.append(r6);	 Catch:{ all -> 0x00da }
        r6 = "/";
        r4 = r4.append(r6);	 Catch:{ all -> 0x00da }
        r6 = "stateless";
        r4 = r4.append(r6);	 Catch:{ all -> 0x00da }
        r4 = r4.toString();	 Catch:{ all -> 0x00da }
        r1.<init>(r4);	 Catch:{ all -> 0x00da }
        r4 = r1.isDirectory();	 Catch:{ all -> 0x00da }
        if (r4 != 0) goto L_0x0055;
    L_0x0052:
        r1.mkdir();	 Catch:{ all -> 0x00da }
    L_0x0055:
        r4 = new java.io.File;	 Catch:{ all -> 0x00da }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r6.<init>();	 Catch:{ all -> 0x00da }
        r1 = r1.getPath();	 Catch:{ all -> 0x00da }
        r1 = r6.append(r1);	 Catch:{ all -> 0x00da }
        r6 = "/";
        r1 = r1.append(r6);	 Catch:{ all -> 0x00da }
        r1 = r1.append(r12);	 Catch:{ all -> 0x00da }
        r1 = r1.toString();	 Catch:{ all -> 0x00da }
        r4.<init>(r1);	 Catch:{ all -> 0x00da }
        r1 = r4.isDirectory();	 Catch:{ all -> 0x00da }
        if (r1 != 0) goto L_0x007e;
    L_0x007b:
        r4.mkdir();	 Catch:{ all -> 0x00da }
    L_0x007e:
        r1 = new java.io.File;	 Catch:{ all -> 0x00da }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r6.<init>();	 Catch:{ all -> 0x00da }
        r4 = r4.getPath();	 Catch:{ all -> 0x00da }
        r4 = r6.append(r4);	 Catch:{ all -> 0x00da }
        r6 = "/";
        r4 = r4.append(r6);	 Catch:{ all -> 0x00da }
        r4 = r4.append(r13);	 Catch:{ all -> 0x00da }
        r4 = r4.toString();	 Catch:{ all -> 0x00da }
        r1.<init>(r4);	 Catch:{ all -> 0x00da }
        r4 = r1.exists();	 Catch:{ all -> 0x00da }
        if (r4 != 0) goto L_0x00a7;
    L_0x00a4:
        r1.createNewFile();	 Catch:{ all -> 0x00da }
    L_0x00a7:
        r4 = new java.io.FileOutputStream;	 Catch:{ all -> 0x00da }
        r4.<init>(r1);	 Catch:{ all -> 0x00da }
        r4.write(r14);	 Catch:{ all -> 0x01c4 }
        r4.close();	 Catch:{ all -> 0x01c4 }
        r1 = 0;
        monitor-exit(r5);	 Catch:{ all -> 0x01c9 }
        if (r3 == 0) goto L_0x00b9;
    L_0x00b6:
        r1.close();	 Catch:{ IOException -> 0x01a8 }
    L_0x00b9:
        r1 = "walle";
        r3 = new java.lang.Object[r0];
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "[stateless] end write envelope, thread id ";
        r4 = r4.append(r5);
        r5 = java.lang.Thread.currentThread();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3[r2] = r4;
        com.umeng.commonsdk.statistics.common.e.a(r1, r3);
    L_0x00d9:
        return r0;
    L_0x00da:
        r1 = move-exception;
        r4 = r2;
    L_0x00dc:
        monitor-exit(r5);	 Catch:{ all -> 0x01cd }
        throw r1;	 Catch:{ IOException -> 0x00de, Throwable -> 0x01b7, all -> 0x01b2 }
    L_0x00de:
        r1 = move-exception;
        r10 = r1;
        r1 = r4;
        r4 = r3;
        r3 = r10;
    L_0x00e3:
        r5 = "walle";
        r6 = 1;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x01b5 }
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01b5 }
        r8.<init>();	 Catch:{ all -> 0x01b5 }
        r9 = "[stateless] write envelope, e is ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x01b5 }
        r9 = r3.getMessage();	 Catch:{ all -> 0x01b5 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x01b5 }
        r8 = r8.toString();	 Catch:{ all -> 0x01b5 }
        r6[r7] = r8;	 Catch:{ all -> 0x01b5 }
        com.umeng.commonsdk.statistics.common.e.a(r5, r6);	 Catch:{ all -> 0x01b5 }
        com.umeng.commonsdk.proguard.b.a(r11, r3);	 Catch:{ all -> 0x01b5 }
        if (r4 == 0) goto L_0x010d;
    L_0x010a:
        r4.close();	 Catch:{ IOException -> 0x01ab }
    L_0x010d:
        r3 = "walle";
        r0 = new java.lang.Object[r0];
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "[stateless] end write envelope, thread id ";
        r4 = r4.append(r5);
        r5 = java.lang.Thread.currentThread();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r0[r2] = r4;
        com.umeng.commonsdk.statistics.common.e.a(r3, r0);
        r0 = r1;
        goto L_0x00d9;
    L_0x012f:
        r1 = move-exception;
        r4 = r3;
        r3 = r1;
        r1 = r2;
    L_0x0133:
        r5 = "walle";
        r6 = 1;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x01b5 }
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01b5 }
        r8.<init>();	 Catch:{ all -> 0x01b5 }
        r9 = "[stateless] write envelope, e is ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x01b5 }
        r9 = r3.getMessage();	 Catch:{ all -> 0x01b5 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x01b5 }
        r8 = r8.toString();	 Catch:{ all -> 0x01b5 }
        r6[r7] = r8;	 Catch:{ all -> 0x01b5 }
        com.umeng.commonsdk.statistics.common.e.a(r5, r6);	 Catch:{ all -> 0x01b5 }
        com.umeng.commonsdk.proguard.b.a(r11, r3);	 Catch:{ all -> 0x01b5 }
        if (r4 == 0) goto L_0x015d;
    L_0x015a:
        r4.close();	 Catch:{ IOException -> 0x01ae }
    L_0x015d:
        r3 = "walle";
        r0 = new java.lang.Object[r0];
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "[stateless] end write envelope, thread id ";
        r4 = r4.append(r5);
        r5 = java.lang.Thread.currentThread();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r0[r2] = r4;
        com.umeng.commonsdk.statistics.common.e.a(r3, r0);
        r0 = r1;
        goto L_0x00d9;
    L_0x0180:
        r1 = move-exception;
        r4 = r3;
    L_0x0182:
        if (r4 == 0) goto L_0x0187;
    L_0x0184:
        r4.close();	 Catch:{ IOException -> 0x01b0 }
    L_0x0187:
        r3 = "walle";
        r0 = new java.lang.Object[r0];
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "[stateless] end write envelope, thread id ";
        r4 = r4.append(r5);
        r5 = java.lang.Thread.currentThread();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r0[r2] = r4;
        com.umeng.commonsdk.statistics.common.e.a(r3, r0);
        throw r1;
    L_0x01a8:
        r1 = move-exception;
        goto L_0x00b9;
    L_0x01ab:
        r3 = move-exception;
        goto L_0x010d;
    L_0x01ae:
        r3 = move-exception;
        goto L_0x015d;
    L_0x01b0:
        r3 = move-exception;
        goto L_0x0187;
    L_0x01b2:
        r1 = move-exception;
        r4 = r3;
        goto L_0x0182;
    L_0x01b5:
        r1 = move-exception;
        goto L_0x0182;
    L_0x01b7:
        r1 = move-exception;
        r10 = r1;
        r1 = r4;
        r4 = r3;
        r3 = r10;
        goto L_0x0133;
    L_0x01be:
        r1 = move-exception;
        r4 = r3;
        r3 = r1;
        r1 = r2;
        goto L_0x00e3;
    L_0x01c4:
        r1 = move-exception;
        r3 = r4;
        r4 = r2;
        goto L_0x00dc;
    L_0x01c9:
        r1 = move-exception;
        r4 = r0;
        goto L_0x00dc;
    L_0x01cd:
        r1 = move-exception;
        goto L_0x00dc;
    L_0x01d0:
        r0 = r2;
        goto L_0x00d9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.f.a(android.content.Context, java.lang.String, java.lang.String, byte[]):boolean");
    }

    public static byte[] a(String str) throws IOException {
        byte[] bArr;
        IOException iOException;
        Throwable th;
        FileChannel fileChannel = null;
        synchronized (c) {
            e.a("walle", "[stateless] begin read envelope, thread is " + Thread.currentThread());
            try {
                FileChannel channel = new RandomAccessFile(str, CustomButton.POSITION_RIGHT).getChannel();
                try {
                    MappedByteBuffer load = channel.map(MapMode.READ_ONLY, 0, channel.size()).load();
                    System.out.println(load.isLoaded());
                    bArr = new byte[((int) channel.size())];
                    if (load.remaining() > 0) {
                        load.get(bArr, 0, load.remaining());
                    }
                    e.a("walle", "[stateless] end read envelope, thread id " + Thread.currentThread());
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                    fileChannel = channel;
                    iOException = iOException2;
                    try {
                        e.a("walle", "[stateless] write envelope, e is " + iOException.getMessage());
                        throw iOException;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileChannel != null) {
                            try {
                                fileChannel.close();
                            } catch (IOException e3) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    fileChannel = channel;
                    th = th4;
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                iOException = e4;
                e.a("walle", "[stateless] write envelope, e is " + iOException.getMessage());
                throw iOException;
            }
        }
        return bArr;
    }

    public static File a(Context context) {
        File file;
        Throwable th;
        Throwable th2;
        Throwable th3;
        File file2 = null;
        try {
            synchronized (c) {
                try {
                    e.a("walle", "get last envelope begin, thread is " + Thread.currentThread());
                    if (!(context == null || context.getApplicationContext() == null)) {
                        Object obj = context.getApplicationContext().getFilesDir() + MqttTopic.TOPIC_LEVEL_SEPARATOR + a.e;
                        if (!TextUtils.isEmpty(obj)) {
                            File file3 = new File(obj);
                            if (file3 != null && file3.isDirectory()) {
                                File[] listFiles = file3.listFiles();
                                if (listFiles != null && listFiles.length > 0) {
                                    int i = 0;
                                    file = null;
                                    while (i < listFiles.length) {
                                        try {
                                            file2 = listFiles[i];
                                            if (file2 != null && file2.isDirectory()) {
                                                File[] listFiles2 = file2.listFiles();
                                                if (listFiles2 != null && listFiles2.length > 0) {
                                                    Arrays.sort(listFiles2, new k());
                                                    file2 = listFiles2[0];
                                                    if (file2 != null && (file == null || file.lastModified() > file2.lastModified())) {
                                                        file = file2;
                                                    }
                                                }
                                            }
                                            i++;
                                        } catch (Throwable th22) {
                                            th = th22;
                                            file2 = file;
                                            th3 = th;
                                        }
                                    }
                                    e.a("walle", "get last envelope end, thread is " + Thread.currentThread());
                                }
                            }
                        }
                    }
                    file = null;
                    e.a("walle", "get last envelope end, thread is " + Thread.currentThread());
                } catch (Throwable th4) {
                    th3 = th4;
                    try {
                        throw th3;
                    } catch (Throwable th32) {
                        th = th32;
                        file = file2;
                        th22 = th;
                    }
                }
            }
        } catch (Throwable th322) {
            th = th322;
            file = null;
            th22 = th;
            b.a(context, th22);
            return file;
        }
    }

    public static void a(Context context, String str, int i) {
        int i2 = 0;
        if (str == null) {
            try {
                e.a("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            } catch (Throwable th) {
                b.a(context, th);
                return;
            }
        }
        File file = new File(str);
        if (file.isDirectory()) {
            synchronized (c) {
                File[] listFiles = file.listFiles();
                e.a("AmapLBS", "[lbs-build] delete file begin " + listFiles.length + ", thread is " + Thread.currentThread());
                if (listFiles == null || listFiles.length < i) {
                    e.a("AmapLBS", "[lbs-build] file size < max");
                } else {
                    int i3;
                    e.a("AmapLBS", "[lbs-build] file size >= max");
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : listFiles) {
                        if (obj != null) {
                            arrayList.add(obj);
                        }
                    }
                    if (arrayList != null && arrayList.size() >= i) {
                        Collections.sort(arrayList, new l());
                        if (e.a) {
                            for (i3 = 0; i3 < arrayList.size(); i3++) {
                                e.a("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i3)).getPath());
                            }
                        }
                        while (i2 <= arrayList.size() - i) {
                            if (!(arrayList == null || arrayList.get(i2) == null)) {
                                e.a("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i2)).getPath());
                                try {
                                    ((File) arrayList.get(i2)).delete();
                                    arrayList.remove(i2);
                                } catch (Exception e) {
                                }
                            }
                            i2++;
                        }
                    }
                }
                e.a("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
            }
            return;
        }
        e.a("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
    }

    public static boolean a(long j, long j2) {
        if (j > j2) {
            return true;
        }
        return false;
    }

    public static byte[] a(byte[] bArr) throws IOException {
        Throwable th;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        a = 0;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int deflate = deflater.deflate(bArr2);
                    a += deflate;
                    byteArrayOutputStream.write(bArr2, 0, deflate);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            deflater.end();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            byteArrayOutputStream = null;
            th = th4;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(b));
        return instance.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }
}
