package com.tencent.bugly.proguard;

import com.meizu.cloud.pushsdk.pushtracer.storage.EventStoreHelper;
import java.util.ArrayList;

public final class x extends m implements Cloneable {
    static ArrayList<w> b;
    static final /* synthetic */ boolean c = (!x.class.desiredAssertionStatus());
    public ArrayList<w> a = null;

    public x(ArrayList<w> arrayList) {
        this.a = arrayList;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return n.a(this.a, ((x) obj).a);
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!c) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void a(l lVar) {
        lVar.a(this.a, 0);
    }

    public void a(k kVar) {
        if (b == null) {
            b = new ArrayList();
            b.add(new w());
        }
        this.a = (ArrayList) kVar.a(b, 0, true);
    }

    public void a(StringBuilder stringBuilder, int i) {
        new i(stringBuilder, i).a(this.a, EventStoreHelper.TABLE_EVENTS);
    }
}
