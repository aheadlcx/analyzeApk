package cn.xiaochuankeji.tieba.background.utils.monitor.crash;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.a;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.File;
import okhttp3.u;
import okhttp3.w;

public class CrashUploaderService extends IntentService {
    private static final u a = u.a(OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
    private static w b;

    public CrashUploaderService() {
        super("CrashUploaderService");
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("key_crash_dir");
            String stringExtra2 = intent.getStringExtra("key_upload_url");
            String stringExtra3 = intent.getStringExtra("key_upload_header");
            if (!TextUtils.isEmpty(stringExtra)) {
                File file = new File(stringExtra);
                if (file.exists()) {
                    File[] listFiles = new File(stringExtra).listFiles();
                    if (listFiles != null) {
                        long currentTimeMillis = System.currentTimeMillis();
                        for (File file2 : listFiles) {
                            if (currentTimeMillis - file2.lastModified() > 259200000) {
                                file2.delete();
                            } else {
                                a aVar = new a(file2);
                                if (!aVar.b()) {
                                    a(aVar, stringExtra2, stringExtra3);
                                    aVar.c();
                                }
                            }
                        }
                        return;
                    }
                    return;
                }
                file.mkdirs();
            }
        }
    }

    private void a(final a aVar, final String str, final String str2) {
        a.p().a().execute(new Runnable(this) {
            final /* synthetic */ CrashUploaderService d;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x008a in list [B:7:0x0087]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
                /*
                r6 = this;
                r1 = 0;
                r0 = cn.xiaochuankeji.tieba.background.utils.monitor.crash.CrashUploaderService.b;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                if (r0 != 0) goto L_0x0034;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
            L_0x0007:
                r0 = new okhttp3.w$a;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0.<init>();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = new cn.xiaochuankeji.tieba.network.custom.a;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2.<init>();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.a(r2);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = 10;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r4 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.a(r2, r4);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = 15;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r4 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.b(r2, r4);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = 15;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r4 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.c(r2, r4);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.a();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                cn.xiaochuankeji.tieba.background.utils.monitor.crash.CrashUploaderService.b = r0;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
            L_0x0034:
                r0 = r3;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.a();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r1 = cn.htjyb.c.a.b.c(r0);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = cn.xiaochuankeji.tieba.background.utils.monitor.crash.CrashUploaderService.a;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = okhttp3.z.create(r2, r1);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r3 = new okhttp3.v$a;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r3.<init>();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r4 = okhttp3.v.e;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r3 = r3.a(r4);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r4 = "json";	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r5 = r5;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r3.a(r4, r5);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r4 = "file";	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.getName();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r3.a(r4, r0, r2);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = new okhttp3.y$a;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0.<init>();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = r4;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.a(r2);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = r3.a();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.a(r2);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r0.d();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r2 = cn.xiaochuankeji.tieba.background.utils.monitor.crash.CrashUploaderService.b;	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0 = r2.a(r0);	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                r0.a();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                if (r1 == 0) goto L_0x008a;
            L_0x0087:
                r1.delete();
            L_0x008a:
                return;
            L_0x008b:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ IOException -> 0x008b, all -> 0x0095 }
                if (r1 == 0) goto L_0x008a;
            L_0x0091:
                r1.delete();
                goto L_0x008a;
            L_0x0095:
                r0 = move-exception;
                if (r1 == 0) goto L_0x009b;
            L_0x0098:
                r1.delete();
            L_0x009b:
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.background.utils.monitor.crash.CrashUploaderService.1.run():void");
            }
        });
    }
}
