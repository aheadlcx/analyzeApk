package com.loc;

import android.content.Context;
import com.loc.be.a;
import com.loc.be.b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

public class bt {
    public static String a() {
        return t.a(new Date().getTime());
    }

    public static String a(Context context, s sVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(n.e(context)).append("\",\"sdkversion\":\"").append(sVar.c()).append("\",\"product\":\"").append(sVar.a()).append("\",\"ed\":\"").append(sVar.d()).append("\",\"nt\":\"").append(n.c(context)).append("\",\"np\":\"").append(n.a(context)).append("\",\"mnc\":\"").append(n.b(context)).append("\",\"ant\":\"").append(n.d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String a(String str, String str2, int i, String str3, String str4) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append(",\"timestamp\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    public static void a(Context context, long j, String str) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileNotFoundException e;
        IOException e2;
        File file = new File(x.a(context, str));
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(t.a(String.valueOf(j)));
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th3) {
                            th = th3;
                            th.printStackTrace();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e2 = e4;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th6) {
                        th = th6;
                        th.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e6) {
            e2 = e6;
            fileOutputStream = null;
            e2.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th7) {
            th = th7;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static void a(Context context, String str, byte[] bArr) {
        Throwable th;
        OutputStream outputStream = null;
        if (bArr != null && bArr.length != 0) {
            synchronized (bt.class) {
                Random random = new Random();
                be a;
                try {
                    a = be.a(new File(x.a(context, str)), 307200);
                    try {
                        a b = a.b(Integer.toString(random.nextInt(100)) + Long.toString(System.nanoTime()));
                        outputStream = b.a();
                        outputStream.write(bArr);
                        b.b();
                        a.d();
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Throwable th2) {
                                th2.printStackTrace();
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable th22) {
                                th22.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th22 = th3;
                        try {
                            w.a(th22, "Statistics.Utils", "writeToDiskLruCache");
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th222) {
                                    th222.printStackTrace();
                                }
                            }
                            if (a != null) {
                                try {
                                    a.close();
                                } catch (Throwable th2222) {
                                    th2222.printStackTrace();
                                }
                            }
                        } catch (Throwable th4) {
                            th2222 = th4;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th5) {
                                    th5.printStackTrace();
                                }
                            }
                            if (a != null) {
                                try {
                                    a.close();
                                } catch (Throwable th52) {
                                    th52.printStackTrace();
                                }
                            }
                            throw th2222;
                        }
                    }
                } catch (Throwable th6) {
                    th2222 = th6;
                    a = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (a != null) {
                        a.close();
                    }
                    throw th2222;
                }
            }
        }
    }

    public static byte[] a(Context context, String str) {
        Throwable th;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        String a = x.a(context, str);
        be beVar = null;
        try {
            beVar = be.a(new File(a), 307200);
            File file = new File(a);
            if (file.exists()) {
                String[] list = file.list();
                int length = list.length;
                while (i < length) {
                    String str2 = list[i];
                    if (str2.contains(".0")) {
                        byteArrayOutputStream.write(a(beVar, str2.split("\\.")[0], true));
                    }
                    i++;
                }
            }
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (beVar != null) {
                try {
                    beVar.close();
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    return bArr;
                }
            }
        } catch (Throwable th3) {
            w.a(th3, "Statistics.Utils", "getContent");
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (beVar != null) {
                beVar.close();
            }
        } catch (Throwable th4) {
            th3 = th4;
            th3.printStackTrace();
        }
        return bArr;
    }

    static byte[] a(be beVar, String str, boolean z) {
        Throwable th;
        Throwable th2;
        InputStream inputStream = null;
        InputStream inputStream2 = null;
        byte[] bArr = new byte[0];
        b a;
        try {
            a = beVar.a(str);
            if (a == null) {
                if (inputStream != null) {
                    try {
                        inputStream2.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th4) {
                        th3 = th4;
                        th3.printStackTrace();
                        return bArr;
                    }
                }
                return bArr;
            }
            try {
                inputStream = a.a();
                if (inputStream == null) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th5) {
                            th32 = th5;
                            th32.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                }
                bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                if (z) {
                    beVar.c(str);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th322) {
                        th322.printStackTrace();
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th6) {
                        th322 = th6;
                        th322.printStackTrace();
                        return bArr;
                    }
                }
                return bArr;
            } catch (Throwable th7) {
                th322 = th7;
                try {
                    w.a(th322, "Utils", "readSingleLog");
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th3222) {
                            th3222.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th8) {
                            th3222 = th8;
                            th3222.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                } catch (Throwable th9) {
                    th2 = th9;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th32222) {
                            th32222.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th322222) {
                            th322222.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th10) {
            th2 = th10;
            a = inputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th2;
        }
    }

    public static int b(Context context, String str) {
        int i = 0;
        try {
            File file = new File(x.a(context, str));
            if (file.exists()) {
                i = file.list().length;
            }
        } catch (Throwable th) {
            w.a(th, "Statistics.Utils", "getFileNum");
        }
        return i;
    }

    public static long c(android.content.Context r7, java.lang.String r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.loc.bt.c(android.content.Context, java.lang.String):long. bs: [B:17:0x0036, B:39:0x005f]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = 0;
        r2 = com.loc.x.a(r7, r8);
        r5 = new java.io.File;
        r5.<init>(r2);
        r2 = r5.exists();
        if (r2 != 0) goto L_0x0012;
    L_0x0011:
        return r0;
    L_0x0012:
        r4 = 0;
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0034, IOException -> 0x0045, Throwable -> 0x0056, all -> 0x0081 }
        r3.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0034, IOException -> 0x0045, Throwable -> 0x0056, all -> 0x0081 }
        r2 = r3.available();	 Catch:{ FileNotFoundException -> 0x0088, IOException -> 0x0086, Throwable -> 0x0084 }
        r2 = new byte[r2];	 Catch:{ FileNotFoundException -> 0x0088, IOException -> 0x0086, Throwable -> 0x0084 }
        r3.read(r2);	 Catch:{ FileNotFoundException -> 0x0088, IOException -> 0x0086, Throwable -> 0x0084 }
        r2 = com.loc.t.a(r2);	 Catch:{ FileNotFoundException -> 0x0088, IOException -> 0x0086, Throwable -> 0x0084 }
        r0 = java.lang.Long.parseLong(r2);	 Catch:{ FileNotFoundException -> 0x0088, IOException -> 0x0086, Throwable -> 0x0084 }
        if (r3 == 0) goto L_0x0011;
    L_0x002b:
        r3.close();	 Catch:{ Throwable -> 0x002f }
        goto L_0x0011;
    L_0x002f:
        r2 = move-exception;
    L_0x0030:
        r2.printStackTrace();
        goto L_0x0011;
    L_0x0034:
        r2 = move-exception;
        r3 = r4;
    L_0x0036:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0075 }
        r5 = "getUpdateTime";	 Catch:{ all -> 0x0075 }
        com.loc.w.a(r2, r4, r5);	 Catch:{ all -> 0x0075 }
        if (r3 == 0) goto L_0x0011;
    L_0x003f:
        r3.close();	 Catch:{ Throwable -> 0x0043 }
        goto L_0x0011;
    L_0x0043:
        r2 = move-exception;
        goto L_0x0030;
    L_0x0045:
        r2 = move-exception;
        r3 = r4;
    L_0x0047:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0075 }
        r5 = "getUpdateTime";	 Catch:{ all -> 0x0075 }
        com.loc.w.a(r2, r4, r5);	 Catch:{ all -> 0x0075 }
        if (r3 == 0) goto L_0x0011;
    L_0x0050:
        r3.close();	 Catch:{ Throwable -> 0x0054 }
        goto L_0x0011;
    L_0x0054:
        r2 = move-exception;
        goto L_0x0030;
    L_0x0056:
        r2 = move-exception;
        r3 = r4;
    L_0x0058:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0075 }
        r6 = "getUpdateTime";	 Catch:{ all -> 0x0075 }
        com.loc.w.a(r2, r4, r6);	 Catch:{ all -> 0x0075 }
        r2 = r5.exists();	 Catch:{ Throwable -> 0x0070 }
        if (r2 == 0) goto L_0x0068;	 Catch:{ Throwable -> 0x0070 }
    L_0x0065:
        r5.delete();	 Catch:{ Throwable -> 0x0070 }
    L_0x0068:
        if (r3 == 0) goto L_0x0011;
    L_0x006a:
        r3.close();	 Catch:{ Throwable -> 0x006e }
        goto L_0x0011;
    L_0x006e:
        r2 = move-exception;
        goto L_0x0030;
    L_0x0070:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0075 }
        goto L_0x0068;
    L_0x0075:
        r0 = move-exception;
    L_0x0076:
        if (r3 == 0) goto L_0x007b;
    L_0x0078:
        r3.close();	 Catch:{ Throwable -> 0x007c }
    L_0x007b:
        throw r0;
    L_0x007c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007b;
    L_0x0081:
        r0 = move-exception;
        r3 = r4;
        goto L_0x0076;
    L_0x0084:
        r2 = move-exception;
        goto L_0x0058;
    L_0x0086:
        r2 = move-exception;
        goto L_0x0047;
    L_0x0088:
        r2 = move-exception;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bt.c(android.content.Context, java.lang.String):long");
    }
}
