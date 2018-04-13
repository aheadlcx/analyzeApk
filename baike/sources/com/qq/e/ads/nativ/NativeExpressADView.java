package com.qq.e.ads.nativ;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.NEADI;
import com.qq.e.comm.pi.NEADVI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import java.util.HashMap;
import org.json.JSONObject;

public class NativeExpressADView extends FrameLayout {
    private NEADVI a;
    private boolean b = false;
    private volatile int c = 0;

    public NativeExpressADView(NEADI neadi, Context context, ADSize aDSize, String str, String str2, JSONObject jSONObject, HashMap<String, JSONObject> hashMap) {
        super(context);
        final Context context2 = context;
        final String str3 = str;
        final NEADI neadi2 = neadi;
        final ADSize aDSize2 = aDSize;
        final String str4 = str2;
        final JSONObject jSONObject2 = jSONObject;
        final HashMap<String, JSONObject> hashMap2 = hashMap;
        GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
            final /* synthetic */ NativeExpressADView h;

            public void run() {
                if (GDTADManager.getInstance().initWith(context2, str3)) {
                    try {
                        final POFactory pOFactory = GDTADManager.getInstance().getPM().getPOFactory();
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            private /* synthetic */ AnonymousClass1 b;

                            /* JADX WARNING: inconsistent code. */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void run() {
                                /*
                                r11 = this;
                                r10 = 1;
                                r0 = r0;	 Catch:{ Throwable -> 0x0053 }
                                if (r0 == 0) goto L_0x004b;
                            L_0x0005:
                                r0 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r9 = r0.h;	 Catch:{ Throwable -> 0x0053 }
                                r0 = r0;	 Catch:{ Throwable -> 0x0053 }
                                r1 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r1 = r4;	 Catch:{ Throwable -> 0x0053 }
                                r2 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r2 = r2;	 Catch:{ Throwable -> 0x0053 }
                                r3 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r3 = r3.h;	 Catch:{ Throwable -> 0x0053 }
                                r4 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r4 = r5;	 Catch:{ Throwable -> 0x0053 }
                                r5 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r5 = r3;	 Catch:{ Throwable -> 0x0053 }
                                r6 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r6 = r6;	 Catch:{ Throwable -> 0x0053 }
                                r7 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r7 = r7;	 Catch:{ Throwable -> 0x0053 }
                                r8 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r8 = r8;	 Catch:{ Throwable -> 0x0053 }
                                r0 = r0.getNativeExpressADView(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Throwable -> 0x0053 }
                                r9.a = r0;	 Catch:{ Throwable -> 0x0053 }
                                r0 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r0 = r0.h;	 Catch:{ Throwable -> 0x0053 }
                                r1 = 1;
                                r0.b = true;	 Catch:{ Throwable -> 0x0053 }
                                r0 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r0 = r0.h;	 Catch:{ Throwable -> 0x0053 }
                                r0 = r0.c;	 Catch:{ Throwable -> 0x0053 }
                                if (r0 <= 0) goto L_0x004b;
                            L_0x0044:
                                r0 = r11.b;	 Catch:{ Throwable -> 0x0053 }
                                r0 = r0.h;	 Catch:{ Throwable -> 0x0053 }
                                r0.render();	 Catch:{ Throwable -> 0x0053 }
                            L_0x004b:
                                r0 = r11.b;
                                r0 = r0.h;
                                r0.b = true;
                            L_0x0052:
                                return;
                            L_0x0053:
                                r0 = move-exception;
                                r1 = "Exception while init Native Express AD View Core";
                                com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x0061 }
                                r0 = r11.b;
                                r0 = r0.h;
                                r0.b = true;
                                goto L_0x0052;
                            L_0x0061:
                                r0 = move-exception;
                                r1 = r11.b;
                                r1 = r1.h;
                                r1.b = true;
                                throw r0;
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.nativ.NativeExpressADView.1.1.run():void");
                            }
                        });
                        return;
                    } catch (Throwable th) {
                        GDTLogger.e("Exception while init Native Express AD View plugin", th);
                        return;
                    }
                }
                GDTLogger.e("Fail to init ADManager");
            }
        });
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public void render() {
        if (!this.b) {
            this.c++;
        } else if (this.a != null) {
            this.a.render();
        } else {
            GDTLogger.e("Native Express AD View Init Error");
        }
    }

    public void setAdSize(ADSize aDSize) {
        if (this.a != null) {
            this.a.setAdSize(aDSize);
        }
    }
}
