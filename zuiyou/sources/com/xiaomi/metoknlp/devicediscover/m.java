package com.xiaomi.metoknlp.devicediscover;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class m extends Thread {
    private WeakReference a;
    private WeakReference b;
    private final int c;

    public m(Handler handler, Context context, int i) {
        this.a = new WeakReference(handler);
        this.b = new WeakReference(context);
        this.c = i;
        start();
    }

    private Handler a() {
        return this.a == null ? null : (Handler) this.a.get();
    }

    public static void a(Context context, Handler handler, int i) {
        m mVar = new m(handler, context, i);
    }

    private void a(String str, int i, int i2, HashMap hashMap) {
        Object e;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
            try {
                int i3;
                bufferedReader.readLine();
                String[] strArr = new String[((i2 - i) + 1)];
                for (i3 = 0; i3 < (i2 - i) + 1; i3++) {
                    strArr[i3] = str + "." + i3;
                }
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    String[] split = readLine.split("[ ]+");
                    if (split.length >= 6) {
                        Object obj = split[0];
                        String str2 = split[3];
                        for (String equals : strArr) {
                            if (equals.equals(obj) && str2.matches("..:..:..:..:..:..") && !"00:00:00:00:00:00".equals(str2)) {
                                hashMap.put(obj, str2);
                            }
                        }
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    Log.w("Scanner", "get address file not found:" + e);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                Log.w("Scanner", "get address read file error:" + e);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e7) {
                    }
                }
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            bufferedReader = null;
            Log.w("Scanner", "get address file not found:" + e);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e9) {
            e = e9;
            bufferedReader = null;
            Log.w("Scanner", "get address read file error:" + e);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    private void a(HashMap hashMap) {
        Handler a = a();
        Message obtainMessage = a.obtainMessage(this.c);
        obtainMessage.obj = hashMap;
        a.sendMessage(obtainMessage);
    }

    private Context b() {
        return this.a == null ? null : (Context) this.b.get();
    }

    private HashMap c() {
        HashMap hashMap = new HashMap();
        String b = j.b(b());
        if (b != null) {
            String substring = b.substring(0, b.lastIndexOf("."));
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(40);
            long eVar;
            try {
                int i;
                Runnable[] runnableArr = new Runnable[255];
                for (i = 1; i < 255; i++) {
                    eVar = new e(this, substring + "." + i);
                    runnableArr[i] = eVar;
                }
                for (i = 1; i < 255; i++) {
                    eVar = runnableArr[i];
                    newFixedThreadPool.execute(eVar);
                }
                try {
                    newFixedThreadPool.awaitTermination(eVar, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    Log.w("Scanner", "find device error:" + e);
                }
            } catch (Exception e2) {
                eVar = new StringBuilder().append("multi-threading error: ");
                Log.w("Scanner", eVar.append(e2).toString());
                try {
                    newFixedThreadPool.awaitTermination(eVar, TimeUnit.MILLISECONDS);
                } catch (Exception e22) {
                    Log.w("Scanner", "find device error:" + e22);
                }
                a(substring, 1, 255, hashMap);
                return hashMap;
            } finally {
                newFixedThreadPool.shutdown();
                int i2 = 10000;
                try {
                    newFixedThreadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
                } catch (Exception e3) {
                    Log.w("Scanner", "find device error:" + e3);
                }
            }
            a(substring, 1, 255, hashMap);
        }
        return hashMap;
    }

    public void run() {
        a(c());
    }
}
