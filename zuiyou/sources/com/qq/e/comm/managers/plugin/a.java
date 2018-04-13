package com.qq.e.comm.managers.plugin;

import android.content.Context;
import com.qq.e.comm.constants.Constants.PLUGIN;
import com.qq.e.comm.net.NetworkCallBack;
import com.qq.e.comm.net.NetworkClient.Priority;
import com.qq.e.comm.net.NetworkClientImpl;
import com.qq.e.comm.net.rr.PlainRequest;
import com.qq.e.comm.net.rr.Request.Method;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.util.FileUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class a {
    private static final Pattern a = Pattern.compile(".*plugin\\.dex-(\\d+)\\.jar.*");
    private final Context b;
    private com.qq.e.comm.managers.plugin.PM.a.a c;

    class a implements NetworkCallBack {
        private final String a;
        private final int b;
        private /* synthetic */ a c;

        public a(a aVar, String str, int i) {
            this.c = aVar;
            this.a = str;
            this.b = i;
        }

        private static String a(Response response, File file) {
            InputStream streamContent;
            OutputStream fileOutputStream;
            Throwable e;
            Object obj;
            Throwable th;
            String str = null;
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                streamContent = response.getStreamContent();
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e2) {
                    e = e2;
                    obj = str;
                    try {
                        GDTLogger.e("IOException While Update Plugin", e);
                        FileUtil.tryClose(streamContent);
                        FileUtil.tryClose(fileOutputStream);
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        FileUtil.tryClose(streamContent);
                        FileUtil.tryClose(fileOutputStream);
                        throw th;
                    }
                } catch (NoSuchAlgorithmException e3) {
                    e = e3;
                    obj = str;
                    GDTLogger.e("MD5SUMException While Update Plugin", e);
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    return str;
                } catch (Throwable e4) {
                    obj = str;
                    th = e4;
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = streamContent.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                        fileOutputStream.write(bArr, 0, read);
                    }
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    str = Md5Util.byteArrayToHexString(instance.digest());
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                } catch (IOException e5) {
                    e4 = e5;
                    GDTLogger.e("IOException While Update Plugin", e4);
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    return str;
                } catch (NoSuchAlgorithmException e6) {
                    e4 = e6;
                    GDTLogger.e("MD5SUMException While Update Plugin", e4);
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    return str;
                }
            } catch (IOException e7) {
                e4 = e7;
                obj = str;
                Object obj2 = str;
                GDTLogger.e("IOException While Update Plugin", e4);
                FileUtil.tryClose(streamContent);
                FileUtil.tryClose(fileOutputStream);
                return str;
            } catch (NoSuchAlgorithmException e8) {
                e4 = e8;
                fileOutputStream = str;
                streamContent = str;
                GDTLogger.e("MD5SUMException While Update Plugin", e4);
                FileUtil.tryClose(streamContent);
                FileUtil.tryClose(fileOutputStream);
                return str;
            } catch (Throwable e42) {
                fileOutputStream = str;
                streamContent = str;
                th = e42;
                FileUtil.tryClose(streamContent);
                FileUtil.tryClose(fileOutputStream);
                throw th;
            }
            return str;
        }

        private void a() {
            if (this.c.c != null) {
                this.c.c.b();
            }
        }

        private boolean a(File file) {
            try {
                StringUtil.writeTo(this.b + "#####" + this.a, file);
                return true;
            } catch (Throwable e) {
                GDTLogger.e("IOException While Update Plugin", e);
                return false;
            }
        }

        public final void onException(Exception exception) {
            GDTLogger.w("Exception While Update Plugin", exception);
            a();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onResponse(com.qq.e.comm.net.rr.Request r8, com.qq.e.comm.net.rr.Response r9) {
            /*
            r7 = this;
            r0 = 1;
            r1 = 0;
            r2 = r9.getStatusCode();
            r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r2 != r3) goto L_0x012d;
        L_0x000a:
            r2 = r7.c;	 Catch:{ Throwable -> 0x00d8 }
            r2 = r2.b;	 Catch:{ Throwable -> 0x00d8 }
            r3 = new java.io.File;	 Catch:{ Throwable -> 0x00d8 }
            r4 = "e_qq_com_plugin";
            r5 = 0;
            r2 = r2.getDir(r4, r5);	 Catch:{ Throwable -> 0x00d8 }
            r4 = "gdt_plugin.tmp";
            r3.<init>(r2, r4);	 Catch:{ Throwable -> 0x00d8 }
            r2 = r7.c;	 Catch:{ Throwable -> 0x00d8 }
            r2 = r2.b;	 Catch:{ Throwable -> 0x00d8 }
            r4 = new java.io.File;	 Catch:{ Throwable -> 0x00d8 }
            r5 = "e_qq_com_plugin";
            r6 = 0;
            r2 = r2.getDir(r5, r6);	 Catch:{ Throwable -> 0x00d8 }
            r5 = "gdt_plugin.tmp.sig";
            r4.<init>(r2, r5);	 Catch:{ Throwable -> 0x00d8 }
            r2 = a(r9, r3);	 Catch:{ Throwable -> 0x00d8 }
            r5 = com.qq.e.comm.util.a.a();	 Catch:{ Throwable -> 0x00d8 }
            r6 = r7.a;	 Catch:{ Throwable -> 0x00d8 }
            r5 = r5.b(r6, r2);	 Catch:{ Throwable -> 0x00d8 }
            if (r5 == 0) goto L_0x00bc;
        L_0x0046:
            r2 = r7.a(r4);	 Catch:{ Throwable -> 0x00d8 }
            if (r2 == 0) goto L_0x00ba;
        L_0x004c:
            r2 = r7.c;	 Catch:{ Throwable -> 0x00d8 }
            r2 = r2.b;	 Catch:{ Throwable -> 0x00d8 }
            r2 = com.qq.e.comm.managers.plugin.PM.AnonymousClass1.b(r2);	 Catch:{ Throwable -> 0x00d8 }
            r2 = com.qq.e.comm.util.FileUtil.renameTo(r3, r2);	 Catch:{ Throwable -> 0x00d8 }
            if (r2 == 0) goto L_0x00b8;
        L_0x005c:
            r2 = r7.c;	 Catch:{ Throwable -> 0x00d8 }
            r2 = r2.b;	 Catch:{ Throwable -> 0x00d8 }
            r2 = com.qq.e.comm.managers.plugin.PM.AnonymousClass1.d(r2);	 Catch:{ Throwable -> 0x00d8 }
            r2 = com.qq.e.comm.util.FileUtil.renameTo(r4, r2);	 Catch:{ Throwable -> 0x00d8 }
            if (r2 == 0) goto L_0x00b8;
        L_0x006c:
            r2 = r0;
        L_0x006d:
            if (r2 == 0) goto L_0x00ba;
        L_0x006f:
            r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d8 }
            r2 = "PluginUpdateSucc:";
            r1.<init>(r2);	 Catch:{ Throwable -> 0x00d8 }
            r0 = r1.append(r0);	 Catch:{ Throwable -> 0x00d8 }
            r0 = r0.toString();	 Catch:{ Throwable -> 0x00d8 }
            com.qq.e.comm.util.GDTLogger.d(r0);	 Catch:{ Throwable -> 0x00d8 }
            r0 = r7.c;	 Catch:{ Throwable -> 0x00d8 }
            r0 = r0.c;	 Catch:{ Throwable -> 0x00d8 }
            if (r0 == 0) goto L_0x0093;
        L_0x008a:
            r0 = r7.c;	 Catch:{ Throwable -> 0x00d8 }
            r0 = r0.c;	 Catch:{ Throwable -> 0x00d8 }
            r0.a();	 Catch:{ Throwable -> 0x00d8 }
        L_0x0093:
            r0 = new java.lang.StringBuilder;
            r1 = "TIMESTAMP_AFTER_DOWNPLUGIN:";
            r0.<init>(r1);
            r2 = java.lang.System.nanoTime();
            r0 = r0.append(r2);
            r1 = ";sig=";
            r0 = r0.append(r1);
            r1 = r7.a;
            r0 = r0.append(r1);
            r0 = r0.toString();
            com.qq.e.comm.util.GDTLogger.d(r0);
        L_0x00b7:
            return;
        L_0x00b8:
            r2 = r1;
            goto L_0x006d;
        L_0x00ba:
            r0 = r1;
            goto L_0x006f;
        L_0x00bc:
            r3.delete();	 Catch:{ Throwable -> 0x00d8 }
            r0 = "Fail to update plugin while verifying,sig=%s,md5=%s";
            r1 = 2;
            r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00d8 }
            r3 = 0;
            r4 = r7.a;	 Catch:{ Throwable -> 0x00d8 }
            r1[r3] = r4;	 Catch:{ Throwable -> 0x00d8 }
            r3 = 1;
            r1[r3] = r2;	 Catch:{ Throwable -> 0x00d8 }
            r0 = java.lang.String.format(r0, r1);	 Catch:{ Throwable -> 0x00d8 }
            com.qq.e.comm.util.GDTLogger.report(r0);	 Catch:{ Throwable -> 0x00d8 }
            r7.a();	 Catch:{ Throwable -> 0x00d8 }
            goto L_0x0093;
        L_0x00d8:
            r0 = move-exception;
            r1 = "UnknownException While Update Plugin";
            com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x0107 }
            r7.a();	 Catch:{ all -> 0x0107 }
            r0 = new java.lang.StringBuilder;
            r1 = "TIMESTAMP_AFTER_DOWNPLUGIN:";
            r0.<init>(r1);
            r2 = java.lang.System.nanoTime();
            r0 = r0.append(r2);
            r1 = ";sig=";
            r0 = r0.append(r1);
            r1 = r7.a;
            r0 = r0.append(r1);
            r0 = r0.toString();
            com.qq.e.comm.util.GDTLogger.d(r0);
            goto L_0x00b7;
        L_0x0107:
            r0 = move-exception;
            r1 = new java.lang.StringBuilder;
            r2 = "TIMESTAMP_AFTER_DOWNPLUGIN:";
            r1.<init>(r2);
            r2 = java.lang.System.nanoTime();
            r1 = r1.append(r2);
            r2 = ";sig=";
            r1 = r1.append(r2);
            r2 = r7.a;
            r1 = r1.append(r2);
            r1 = r1.toString();
            com.qq.e.comm.util.GDTLogger.d(r1);
            throw r0;
        L_0x012d:
            r0 = new java.lang.StringBuilder;
            r1 = "DownLoad Plugin Jar Status error,response status code=";
            r0.<init>(r1);
            r1 = r9.getStatusCode();
            r0 = r0.append(r1);
            r0 = r0.toString();
            com.qq.e.comm.util.GDTLogger.report(r0);
            r7.a();
            goto L_0x00b7;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.plugin.a.a.onResponse(com.qq.e.comm.net.rr.Request, com.qq.e.comm.net.rr.Response):void");
        }
    }

    public a(Context context) {
        this.b = context.getApplicationContext();
    }

    public final void a(com.qq.e.comm.managers.plugin.PM.a.a aVar) {
        this.c = aVar;
    }

    public final void a(String str, String str2) {
        if (!StringUtil.isEmpty(str) && !StringUtil.isEmpty(str2)) {
            int i;
            String str3 = "0";
            Matcher matcher = a.matcher(str2);
            if (matcher.matches()) {
                str3 = matcher.group(1);
            }
            int parseInteger = StringUtil.parseInteger(str3, 0);
            if (parseInteger < PLUGIN.ASSET_PLUGIN_VERSION) {
                GDTLogger.i("online plugin version is smaller than asset plugin version" + parseInteger + ",559" + ".download give up");
                i = 0;
            } else {
                i = 1;
            }
            if (i != 0) {
                GDTLogger.d("TIMESTAP_BEFORE_OWN_PLUGIN:" + System.nanoTime());
                NetworkClientImpl.getInstance().submit(new PlainRequest(str2, Method.GET, null), Priority.High, new a(this, str, parseInteger));
            }
        }
    }
}
