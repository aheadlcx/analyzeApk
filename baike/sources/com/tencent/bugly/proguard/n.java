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

public final class n {
    public static final long a = System.currentTimeMillis();
    private static n b = null;
    private Context c;
    private String d = a.b().d;
    private Map<Integer, Map<String, m>> e = new HashMap();
    private SharedPreferences f;

    private n(Context context) {
        this.c = context;
        this.f = context.getSharedPreferences("crashrecord", 0);
    }

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (b == null) {
                b = new n(context);
            }
            nVar = b;
        }
        return nVar;
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            nVar = b;
        }
        return nVar;
    }

    private synchronized boolean b(int i) {
        boolean z;
        try {
            List<m> c = c(i);
            if (c == null) {
                z = false;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                List arrayList = new ArrayList();
                Collection arrayList2 = new ArrayList();
                for (m mVar : c) {
                    if (mVar.b != null && mVar.b.equalsIgnoreCase(this.d) && mVar.d > 0) {
                        arrayList.add(mVar);
                    }
                    if (mVar.c + 86400000 < currentTimeMillis) {
                        arrayList2.add(mVar);
                    }
                }
                Collections.sort(arrayList);
                if (arrayList.size() < 2) {
                    c.removeAll(arrayList2);
                    a(i, (List) c);
                    z = false;
                } else if (arrayList.size() <= 0 || ((m) arrayList.get(arrayList.size() - 1)).c + 86400000 >= currentTimeMillis) {
                    z = true;
                } else {
                    c.clear();
                    a(i, (List) c);
                    z = false;
                }
            }
        } catch (Exception e) {
            x.e("isFrequentCrash failed", new Object[0]);
            z = false;
        }
        return z;
    }

    public final synchronized void a(int i, int i2) {
        w.a().a(new as(this, 1004, i2));
    }

    private synchronized <T extends List<?>> T c(int i) {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2;
        T t;
        Throwable th;
        try {
            File file = new File(this.c.getDir("crashrecord", 0), i);
            if (file.exists()) {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(file));
                } catch (IOException e) {
                    objectInputStream2 = null;
                    try {
                        x.a("open record file error", new Object[0]);
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
                    objectInputStream = null;
                    try {
                        x.a("get object error", new Object[0]);
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
                } catch (Throwable th5) {
                    th = th5;
                    objectInputStream = null;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
                try {
                    List list = (List) objectInputStream.readObject();
                    objectInputStream.close();
                } catch (IOException e3) {
                    objectInputStream2 = objectInputStream;
                    x.a("open record file error", new Object[0]);
                    if (objectInputStream2 != null) {
                        objectInputStream2.close();
                    }
                    t = null;
                    return t;
                } catch (ClassNotFoundException e4) {
                    x.a("get object error", new Object[0]);
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    t = null;
                    return t;
                }
            }
            t = null;
        } catch (Exception e5) {
            x.e("readCrashRecord error", new Object[0]);
        }
        return t;
    }

    private synchronized <T extends List<?>> void a(int i, T t) {
        ObjectOutputStream objectOutputStream;
        IOException e;
        Throwable th;
        if (t != null) {
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(this.c.getDir("crashrecord", 0), i)));
                    try {
                        objectOutputStream.writeObject(t);
                        objectOutputStream.close();
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            x.a("open record file error", new Object[0]);
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
                    x.a("open record file error", new Object[0]);
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
                x.e("writeCrashRecord error", new Object[0]);
            }
        }
    }

    public final synchronized boolean a(int i) {
        boolean z = true;
        synchronized (this) {
            try {
                z = this.f.getBoolean(i + "_" + this.d, true);
                w.a().a(new at(this, i));
            } catch (Exception e) {
                x.e("canInit error", new Object[0]);
            }
        }
        return z;
    }
}
