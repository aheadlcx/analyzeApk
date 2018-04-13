package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.bugly.crashreport.common.info.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ac {
    public static final long a = System.currentTimeMillis();
    private static ac b = null;
    private Context c;
    private String d = a.b().e;
    private Map<Integer, Map<String, ab>> e = new HashMap();
    private SharedPreferences f;

    public ac(Context context) {
        this.c = context;
        this.f = context.getSharedPreferences("crashrecord", 0);
    }

    public static synchronized ac a(Context context) {
        ac acVar;
        synchronized (ac.class) {
            if (b == null) {
                b = new ac(context);
            }
            acVar = b;
        }
        return acVar;
    }

    public static synchronized ac a() {
        ac acVar;
        synchronized (ac.class) {
            acVar = b;
        }
        return acVar;
    }

    private synchronized boolean b(int i) {
        boolean z;
        try {
            List<ab> c = c(i);
            if (c == null) {
                z = false;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                List arrayList = new ArrayList();
                Collection arrayList2 = new ArrayList();
                for (ab abVar : c) {
                    if (abVar.b != null && abVar.b.equalsIgnoreCase(this.d) && abVar.d > 0) {
                        arrayList.add(abVar);
                    }
                    if (abVar.c + com.umeng.analytics.a.i < currentTimeMillis) {
                        arrayList2.add(abVar);
                    }
                }
                Collections.sort(arrayList);
                if (arrayList.size() < 2) {
                    c.removeAll(arrayList2);
                    a(i, (List) c);
                    z = false;
                } else if (arrayList.size() <= 0 || ((ab) arrayList.get(arrayList.size() - 1)).c + com.umeng.analytics.a.i >= currentTimeMillis) {
                    z = true;
                } else {
                    c.clear();
                    a(i, (List) c);
                    z = false;
                }
            }
        } catch (Exception e) {
            an.e("isFrequentCrash failed", new Object[0]);
            z = false;
        }
        return z;
    }

    public synchronized void a(int i, int i2) {
        am.a().a(new ac$1(this, i, i2));
    }

    private synchronized <T extends List<?>> T c(int i) {
        T t;
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2;
        Throwable th;
        try {
            File file = new File(this.c.getDir("crashrecord", 0), i + "");
            if (file == null || !file.exists()) {
                t = null;
            } else {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    try {
                        List list = (List) objectInputStream.readObject();
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                    } catch (IOException e) {
                        objectInputStream2 = objectInputStream;
                        try {
                            an.a("open record file error", new Object[0]);
                            if (objectInputStream2 != null) {
                                objectInputStream2.close();
                            }
                            t = null;
                            return t;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            objectInputStream = objectInputStream2;
                            th = th3;
                            if (objectInputStream != null) {
                                objectInputStream.close();
                            }
                            throw th;
                        }
                    } catch (ClassNotFoundException e2) {
                        try {
                            an.a("get object error", new Object[0]);
                            if (objectInputStream != null) {
                                objectInputStream.close();
                            }
                            t = null;
                            return t;
                        } catch (Throwable th4) {
                            th = th4;
                            if (objectInputStream != null) {
                                objectInputStream.close();
                            }
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    objectInputStream2 = null;
                    an.a("open record file error", new Object[0]);
                    if (objectInputStream2 != null) {
                        objectInputStream2.close();
                    }
                    t = null;
                    return t;
                } catch (ClassNotFoundException e4) {
                    objectInputStream = null;
                    an.a("get object error", new Object[0]);
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    t = null;
                    return t;
                } catch (Throwable th5) {
                    th = th5;
                    objectInputStream = null;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            an.e("readCrashRecord error", new Object[0]);
        }
        return t;
    }

    private synchronized <T extends List<?>> void a(int i, T t) {
        IOException e;
        Throwable th;
        if (t != null) {
            try {
                ObjectOutputStream objectOutputStream;
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(this.c.getDir("crashrecord", 0), i + "")));
                    try {
                        objectOutputStream.writeObject(t);
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            an.a("open record file error", new Object[0]);
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    objectOutputStream = null;
                    e.printStackTrace();
                    an.a("open record file error", new Object[0]);
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectOutputStream = null;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                an.e("writeCrashRecord error", new Object[0]);
            }
        }
    }

    public synchronized boolean a(int i) {
        boolean z = true;
        synchronized (this) {
            try {
                z = this.f.getBoolean(i + "_" + this.d, true);
                am.a().a(new ac$2(this, i));
            } catch (Exception e) {
                an.e("canInit error", new Object[0]);
            }
        }
        return z;
    }
}
