package com.umeng.commonsdk.internal.utils;

import android.os.Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class g {
    private static final String a = "\n";
    private static final byte[] b = "\nexit\n".getBytes();
    private static byte[] c = new byte[32];

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String... r10) {
        /*
        r0 = 0;
        r1 = new java.lang.ProcessBuilder;	 Catch:{ IOException -> 0x00e1, Exception -> 0x0071, all -> 0x0082 }
        r2 = 0;
        r2 = new java.lang.String[r2];	 Catch:{ IOException -> 0x00e1, Exception -> 0x0071, all -> 0x0082 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x00e1, Exception -> 0x0071, all -> 0x0082 }
        r1 = r1.command(r10);	 Catch:{ IOException -> 0x00e1, Exception -> 0x0071, all -> 0x0082 }
        r7 = r1.start();	 Catch:{ IOException -> 0x00e1, Exception -> 0x0071, all -> 0x0082 }
        r1 = r7.getOutputStream();	 Catch:{ IOException -> 0x00eb, Exception -> 0x00be, all -> 0x0099 }
        r3 = r7.getInputStream();	 Catch:{ IOException -> 0x00f4, Exception -> 0x00c6, all -> 0x00a2 }
        r2 = r7.getErrorStream();	 Catch:{ IOException -> 0x00fc, Exception -> 0x00cd, all -> 0x00aa }
        r4 = b;	 Catch:{ IOException -> 0x0103, Exception -> 0x00d3, all -> 0x00b1 }
        r1.write(r4);	 Catch:{ IOException -> 0x0103, Exception -> 0x00d3, all -> 0x00b1 }
        r1.flush();	 Catch:{ IOException -> 0x0103, Exception -> 0x00d3, all -> 0x00b1 }
        r7.waitFor();	 Catch:{ IOException -> 0x0103, Exception -> 0x00d3, all -> 0x00b1 }
        r4 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0103, Exception -> 0x00d3, all -> 0x00b1 }
        r4.<init>(r3);	 Catch:{ IOException -> 0x0103, Exception -> 0x00d3, all -> 0x00b1 }
        r5 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0109, Exception -> 0x00d8, all -> 0x00b7 }
        r5.<init>(r4);	 Catch:{ IOException -> 0x0109, Exception -> 0x00d8, all -> 0x00b7 }
        r8 = r5.readLine();	 Catch:{ IOException -> 0x010e, Exception -> 0x00dc, all -> 0x00bc }
        if (r8 == 0) goto L_0x0112;
    L_0x0038:
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x010e, Exception -> 0x00dc, all -> 0x00bc }
        r6.<init>();	 Catch:{ IOException -> 0x010e, Exception -> 0x00dc, all -> 0x00bc }
        r6.append(r8);	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
        r8 = a;	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
        r6.append(r8);	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
    L_0x0045:
        r8 = r5.readLine();	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
        if (r8 == 0) goto L_0x0060;
    L_0x004b:
        r6.append(r8);	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
        r8 = a;	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
        r6.append(r8);	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
        goto L_0x0045;
    L_0x0054:
        r8 = move-exception;
    L_0x0055:
        a(r1, r2, r3, r4, r5);
        if (r7 == 0) goto L_0x005d;
    L_0x005a:
        c(r7);
    L_0x005d:
        if (r6 != 0) goto L_0x0094;
    L_0x005f:
        return r0;
    L_0x0060:
        r8 = c;	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
        r8 = r2.read(r8);	 Catch:{ IOException -> 0x0054, Exception -> 0x00df, all -> 0x00bc }
        if (r8 > 0) goto L_0x0060;
    L_0x0068:
        a(r1, r2, r3, r4, r5);
        if (r7 == 0) goto L_0x005d;
    L_0x006d:
        c(r7);
        goto L_0x005d;
    L_0x0071:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r7 = r0;
    L_0x0079:
        a(r1, r2, r3, r4, r5);
        if (r7 == 0) goto L_0x005d;
    L_0x007e:
        c(r7);
        goto L_0x005d;
    L_0x0082:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r7 = r0;
        r9 = r0;
        r0 = r1;
        r1 = r9;
    L_0x008b:
        a(r1, r2, r3, r4, r5);
        if (r7 == 0) goto L_0x0093;
    L_0x0090:
        c(r7);
    L_0x0093:
        throw r0;
    L_0x0094:
        r0 = r6.toString();
        goto L_0x005f;
    L_0x0099:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x008b;
    L_0x00a2:
        r2 = move-exception;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r9 = r0;
        r0 = r2;
        r2 = r9;
        goto L_0x008b;
    L_0x00aa:
        r2 = move-exception;
        r4 = r0;
        r5 = r0;
        r9 = r2;
        r2 = r0;
        r0 = r9;
        goto L_0x008b;
    L_0x00b1:
        r4 = move-exception;
        r5 = r0;
        r9 = r0;
        r0 = r4;
        r4 = r9;
        goto L_0x008b;
    L_0x00b7:
        r5 = move-exception;
        r9 = r5;
        r5 = r0;
        r0 = r9;
        goto L_0x008b;
    L_0x00bc:
        r0 = move-exception;
        goto L_0x008b;
    L_0x00be:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0079;
    L_0x00c6:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0079;
    L_0x00cd:
        r2 = move-exception;
        r2 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0079;
    L_0x00d3:
        r4 = move-exception;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0079;
    L_0x00d8:
        r5 = move-exception;
        r5 = r0;
        r6 = r0;
        goto L_0x0079;
    L_0x00dc:
        r6 = move-exception;
        r6 = r0;
        goto L_0x0079;
    L_0x00df:
        r8 = move-exception;
        goto L_0x0079;
    L_0x00e1:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r7 = r0;
        goto L_0x0055;
    L_0x00eb:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0055;
    L_0x00f4:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0055;
    L_0x00fc:
        r2 = move-exception;
        r2 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0055;
    L_0x0103:
        r4 = move-exception;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0055;
    L_0x0109:
        r5 = move-exception;
        r5 = r0;
        r6 = r0;
        goto L_0x0055;
    L_0x010e:
        r6 = move-exception;
        r6 = r0;
        goto L_0x0055;
    L_0x0112:
        r6 = r0;
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.g.a(java.lang.String[]):java.lang.String");
    }

    private static void a(OutputStream outputStream, InputStream inputStream, InputStream inputStream2, InputStreamReader inputStreamReader, BufferedReader bufferedReader) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException e3) {
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e4) {
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e5) {
            }
        }
    }

    private static void a(Process process) {
        int b = b(process);
        if (b != 0) {
            try {
                Process.killProcess(b);
            } catch (Exception e) {
                try {
                    process.destroy();
                } catch (Exception e2) {
                }
            }
        }
    }

    private static int b(Process process) {
        String obj = process.toString();
        try {
            return Integer.parseInt(obj.substring(obj.indexOf("=") + 1, obj.indexOf("]")));
        } catch (Exception e) {
            return 0;
        }
    }

    private static void c(Process process) {
        if (process != null) {
            try {
                if (process.exitValue() != 0) {
                    a(process);
                }
            } catch (IllegalThreadStateException e) {
                a(process);
            }
        }
    }
}
