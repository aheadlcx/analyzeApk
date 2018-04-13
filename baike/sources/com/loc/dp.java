package com.loc;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import com.loc.be.a;
import java.io.File;
import java.io.OutputStream;

final class dp implements Runnable {
    final /* synthetic */ bp a;
    final /* synthetic */ Context b;

    dp(bp bpVar, Context context) {
        this.a = bpVar;
        this.b = context;
    }

    public final void run() {
        be a;
        Throwable th;
        OutputStream outputStream = null;
        synchronized (bq.class) {
            try {
                byte[] a2 = this.a.a();
                a = be.a(new File(x.a(this.b, x.h)), (long) PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE);
                try {
                    a.a(bq.a);
                    a b = a.b(Long.toString(System.currentTimeMillis()));
                    outputStream = b.a();
                    outputStream.write(a2);
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
                        w.a(th22, "OfflineLocManager", "applyOfflineLocEntity");
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
