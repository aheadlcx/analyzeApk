package com.baidu.mobstat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum y {
    AP_LIST(0),
    APP_LIST(1),
    APP_TRACE(2),
    APP_CHANGE(3),
    APP_APK(4);
    
    private int f;

    public abstract x a();

    private y(int i) {
        this.f = i;
    }

    public String toString() {
        return String.valueOf(this.f);
    }

    public synchronized ArrayList<w> a(int i, int i2) {
        ArrayList<w> arrayList;
        arrayList = new ArrayList();
        x xVar = null;
        try {
            xVar = a();
            if (xVar.a()) {
                arrayList = xVar.a(i, i2);
                if (xVar != null) {
                    xVar.close();
                }
            } else if (xVar != null) {
                xVar.close();
            }
        } catch (Throwable e) {
            bd.b(e);
            if (xVar != null) {
                xVar.close();
            }
        } catch (Throwable th) {
            if (xVar != null) {
                xVar.close();
            }
        }
        return arrayList;
    }

    public synchronized long a(long j, String str) {
        long j2;
        j2 = -1;
        x xVar = null;
        try {
            xVar = a();
            if (xVar.a()) {
                j2 = xVar.a(String.valueOf(j), str);
                if (xVar != null) {
                    xVar.close();
                }
            } else if (xVar != null) {
                xVar.close();
            }
        } catch (Throwable e) {
            bd.b(e);
            if (xVar != null) {
                xVar.close();
            }
        } catch (Throwable th) {
            if (xVar != null) {
                xVar.close();
            }
        }
        return j2;
    }

    public synchronized int a(ArrayList<Long> arrayList) {
        int i = 0;
        synchronized (this) {
            if (arrayList != null) {
                if (arrayList.size() != 0) {
                    x xVar = null;
                    int i2;
                    try {
                        xVar = a();
                        if (xVar.a()) {
                            int size = arrayList.size();
                            int i3 = 0;
                            while (i3 < size) {
                                if (xVar.b(((Long) arrayList.get(i3)).longValue())) {
                                    i3++;
                                    i++;
                                } else if (xVar != null) {
                                    xVar.close();
                                }
                            }
                            if (xVar != null) {
                                xVar.close();
                                i2 = i;
                            } else {
                                i2 = i;
                            }
                            i = i2;
                        } else if (xVar != null) {
                            xVar.close();
                        }
                    } catch (Throwable e) {
                        Throwable th = e;
                        i2 = 0;
                        bd.b(th);
                        if (xVar != null) {
                            xVar.close();
                        }
                    } catch (Throwable th2) {
                        if (xVar != null) {
                            xVar.close();
                        }
                    }
                }
            }
        }
        return i;
    }

    public synchronized List<String> a(int i) {
        List<String> arrayList;
        arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        a(arrayList, arrayList2, arrayList3, i, 500);
        if (arrayList3.size() != 0 && arrayList.size() == 0 && arrayList2.size() == 0) {
            w wVar = (w) arrayList3.get(0);
            long a = wVar.a();
            String b = wVar.b();
            arrayList2.add(Long.valueOf(a));
            arrayList.add(b);
        }
        int a2 = a(arrayList2);
        if (a2 != arrayList.size()) {
            arrayList = arrayList.subList(0, a2);
        }
        return arrayList;
    }

    private int a(List<String> list, ArrayList<Long> arrayList, ArrayList<w> arrayList2, int i, int i2) {
        int i3 = 0;
        int c = c();
        int i4 = 0;
        int i5 = i2;
        while (c > 0) {
            int i6;
            if (c < i5) {
                i6 = c;
            } else {
                i6 = i5;
            }
            ArrayList a = a(i6, i4);
            if (i4 == 0 && a.size() != 0) {
                arrayList2.add((w) a.get(0));
            }
            Iterator it = a.iterator();
            while (it.hasNext()) {
                w wVar = (w) it.next();
                long a2 = wVar.a();
                String b = wVar.b();
                int length = b.length();
                if (i3 + length > i) {
                    break;
                }
                arrayList.add(Long.valueOf(a2));
                list.add(b);
                i3 += length;
            }
            c -= i6;
            i4 += i6;
            i5 = i6;
        }
        return i3;
    }

    public synchronized boolean b() {
        return c() == 0;
    }

    public synchronized boolean b(int i) {
        return c() >= i;
    }

    private int c() {
        x xVar = null;
        try {
            xVar = a();
            if (xVar.a()) {
                int b = xVar.b();
                if (xVar == null) {
                    return b;
                }
                xVar.close();
                return b;
            }
            if (xVar != null) {
                xVar.close();
            }
            return 0;
        } catch (Throwable e) {
            bd.b(e);
            if (xVar != null) {
                xVar.close();
            }
        } catch (Throwable th) {
            if (xVar != null) {
                xVar.close();
            }
        }
    }
}
