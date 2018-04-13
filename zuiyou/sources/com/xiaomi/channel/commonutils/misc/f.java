package com.xiaomi.channel.commonutils.misc;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.a;
import com.xiaomi.channel.commonutils.logger.b;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

public class f {
    private static final String a = (Environment.getExternalStorageDirectory().getPath() + "/mipush/");
    private static final String b = (a + "lcfp");
    private static final String c = (a + "lcfp.lock");

    public static boolean a(Context context, String str, long j) {
        RandomAccessFile randomAccessFile;
        IOException e;
        Throwable th;
        FileLock fileLock = null;
        try {
            File file = new File(c);
            a.a(file);
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                fileLock = randomAccessFile.getChannel().lock();
                boolean b = b(context, str, j);
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e2) {
                    }
                }
                a.a(randomAccessFile);
                return b;
            } catch (IOException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e4) {
                        }
                    }
                    a.a(randomAccessFile);
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        fileLock.release();
                    } catch (IOException e5) {
                    }
                    a.a(randomAccessFile);
                    throw th;
                }
            }
        } catch (IOException e6) {
            e = e6;
            randomAccessFile = null;
            e.printStackTrace();
            fileLock.release();
            a.a(randomAccessFile);
            return true;
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            if (fileLock != null && fileLock.isValid()) {
                fileLock.release();
            }
            a.a(randomAccessFile);
            throw th;
        }
    }

    private static boolean b(Context context, String str, long j) {
        Throwable th;
        Writer bufferedWriter;
        IOException e;
        Throwable th2;
        if (VERSION.SDK_INT >= 23 && !com.xiaomi.channel.commonutils.android.a.g(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return true;
        }
        String readLine;
        File file = new File(b);
        List<String> arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = str + ":" + context.getPackageName() + "," + currentTimeMillis;
        if (file.exists()) {
            Reader reader = null;
            Reader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = readLine.split(":");
                        if (split.length == 2) {
                            if (TextUtils.equals(split[0], String.valueOf(str))) {
                                String[] split2 = split[1].split(",");
                                if (split2.length == 2) {
                                    long parseLong = Long.parseLong(split2[1]);
                                    if (!TextUtils.equals(split2[0], context.getPackageName()) && ((float) Math.abs(currentTimeMillis - parseLong)) < ((float) (1000 * j)) * 0.9f) {
                                        a.a(bufferedReader);
                                        return false;
                                    }
                                }
                                continue;
                            } else {
                                arrayList.add(readLine);
                            }
                        }
                    } catch (Exception e2) {
                        reader = bufferedReader;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                a.a(bufferedReader);
            } catch (Exception e3) {
                try {
                    arrayList.clear();
                    a.a(reader);
                    arrayList.add(str2);
                    bufferedWriter = new BufferedWriter(new FileWriter(file));
                    try {
                        for (String readLine2 : arrayList) {
                            bufferedWriter.write(readLine2);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        a.a(bufferedWriter);
                    } catch (IOException e4) {
                        e = e4;
                        try {
                            b.d(e.toString());
                            a.a(bufferedWriter);
                            return true;
                        } catch (Throwable th4) {
                            th = th4;
                            a.a(bufferedWriter);
                            throw th;
                        }
                    }
                    return true;
                } catch (Throwable th5) {
                    th2 = th5;
                    bufferedReader = reader;
                    th = th2;
                    a.a(bufferedReader);
                    throw th;
                }
            } catch (Throwable th52) {
                th2 = th52;
                bufferedReader = null;
                th = th2;
                a.a(bufferedReader);
                throw th;
            }
        } else if (!a.a(file)) {
            return true;
        }
        arrayList.add(str2);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            while (r2.hasNext()) {
                bufferedWriter.write(readLine2);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            a.a(bufferedWriter);
        } catch (IOException e5) {
            e = e5;
            bufferedWriter = null;
            b.d(e.toString());
            a.a(bufferedWriter);
            return true;
        } catch (Throwable th6) {
            th = th6;
            bufferedWriter = null;
            a.a(bufferedWriter);
            throw th;
        }
        return true;
    }
}
