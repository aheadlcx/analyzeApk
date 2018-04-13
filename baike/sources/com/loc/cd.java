package com.loc;

import java.util.ArrayList;
import java.util.HashMap;

public final class cd {
    private HashMap<Long, ce> a = new HashMap();
    private long b = 0;

    private static long a(int i, int i2) {
        return ((((long) i) & 65535) << 32) | (((long) i2) & 65535);
    }

    public final long a(ce ceVar) {
        if (ceVar == null || !ceVar.o) {
            return 0;
        }
        long a;
        HashMap hashMap = this.a;
        switch (ceVar.k) {
            case 1:
            case 3:
            case 4:
                a = a(ceVar.a(), ceVar.b());
                break;
            case 2:
                a = a(ceVar.c(), ceVar.d());
                break;
            default:
                a = 0;
                break;
        }
        ce ceVar2 = (ce) hashMap.get(Long.valueOf(a));
        if (ceVar2 == null) {
            ceVar.m = de.b();
            hashMap.put(Long.valueOf(a), ceVar);
            return 0;
        } else if (ceVar2.e() != ceVar.e()) {
            ceVar.m = de.b();
            hashMap.put(Long.valueOf(a), ceVar);
            return 0;
        } else {
            ceVar.m = ceVar2.m;
            hashMap.put(Long.valueOf(a), ceVar);
            return (de.b() - ceVar2.m) / 1000;
        }
    }

    public final void a() {
        this.a.clear();
        this.b = 0;
    }

    public final void a(ArrayList<? extends ce> arrayList) {
        long j = 0;
        if (arrayList != null) {
            long b = de.b();
            if (this.b <= 0 || b - this.b >= 60000) {
                int i;
                ce ceVar;
                HashMap hashMap = this.a;
                int size = arrayList.size();
                for (i = 0; i < size; i++) {
                    ceVar = (ce) arrayList.get(i);
                    if (ceVar.o) {
                        switch (ceVar.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(ceVar.c, ceVar.d);
                                break;
                            case 2:
                                j = a(ceVar.h, ceVar.i);
                                break;
                        }
                        ce ceVar2 = (ce) hashMap.get(Long.valueOf(j));
                        if (ceVar2 != null) {
                            if (ceVar2.e() == ceVar.e()) {
                                ceVar.m = ceVar2.m;
                            } else {
                                ceVar.m = b;
                            }
                        }
                    }
                }
                hashMap.clear();
                i = arrayList.size();
                for (int i2 = 0; i2 < i; i2++) {
                    ceVar = (ce) arrayList.get(i2);
                    if (ceVar.o) {
                        switch (ceVar.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(ceVar.a(), ceVar.b());
                                break;
                            case 2:
                                j = a(ceVar.c(), ceVar.d());
                                break;
                        }
                        hashMap.put(Long.valueOf(j), ceVar);
                    }
                }
                this.b = b;
            }
        }
    }
}
