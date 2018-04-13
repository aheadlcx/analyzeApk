package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.g.e;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class t extends c<s> {
    private final ExecutorService a;

    public t() {
        this(Executors.newFixedThreadPool(3));
    }

    t(ExecutorService executorService) {
        this.a = executorService;
    }

    public s b(j<e> jVar, aj ajVar) {
        return new s(jVar, ajVar);
    }

    public void a(final s sVar, final af$a af_a) {
        final Future submit = this.a.submit(new Runnable(this) {
            final /* synthetic */ t c;

            public void run() {
                this.c.b(sVar, af_a);
            }
        });
        sVar.b().a(new e(this) {
            final /* synthetic */ t c;

            public void a() {
                if (submit.cancel(false)) {
                    af_a.a();
                }
            }
        });
    }

    void b(s sVar, af$a af_a) {
        Throwable e;
        InputStream inputStream = null;
        HttpURLConnection a;
        try {
            a = a(sVar.e(), 5);
            if (a != null) {
                try {
                    inputStream = a.getInputStream();
                    af_a.a(inputStream, -1);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        af_a.a(e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                    } catch (Throwable th) {
                        e = th;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                        throw e;
                    }
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                }
            }
            if (a != null) {
                a.disconnect();
            }
        } catch (IOException e6) {
            e = e6;
            a = null;
            af_a.a(e);
            if (inputStream != null) {
                inputStream.close();
            }
            if (a != null) {
                a.disconnect();
            }
        } catch (Throwable th2) {
            e = th2;
            a = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (a != null) {
                a.disconnect();
            }
            throw e;
        }
    }

    private HttpURLConnection a(Uri uri, int i) throws IOException {
        HttpURLConnection a = a(uri);
        int responseCode = a.getResponseCode();
        if (a(responseCode)) {
            return a;
        }
        if (b(responseCode)) {
            String headerField = a.getHeaderField("Location");
            a.disconnect();
            Uri parse = headerField == null ? null : Uri.parse(headerField);
            headerField = uri.getScheme();
            if (i > 0 && parse != null && !parse.getScheme().equals(headerField)) {
                return a(parse, i - 1);
            }
            String a2;
            if (i == 0) {
                a2 = a("URL %s follows too many redirects", uri.toString());
            } else {
                a2 = a("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode));
            }
            throw new IOException(a2);
        }
        a.disconnect();
        throw new IOException(String.format("Image URL %s returned HTTP code %d", new Object[]{uri.toString(), Integer.valueOf(responseCode)}));
    }

    static HttpURLConnection a(Uri uri) throws IOException {
        return (HttpURLConnection) new URL(uri.toString()).openConnection();
    }

    private static boolean a(int i) {
        return i >= 200 && i < 300;
    }

    private static boolean b(int i) {
        switch (i) {
            case 300:
            case 301:
            case 302:
            case 303:
            case TinkerReport.KEY_LOADED_MISSING_DEX_OPT /*307*/:
            case TinkerReport.KEY_LOADED_MISSING_RES /*308*/:
                return true;
            default:
                return false;
        }
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }
}
