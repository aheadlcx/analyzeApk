package com.nineoldandroids.a;

import java.util.ArrayList;

public abstract class a implements Cloneable {
    ArrayList<a> a = null;

    public interface a {
        void a(a aVar);

        void b(a aVar);

        void c(a aVar);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    public void a() {
    }

    public void a(a aVar) {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(aVar);
    }

    public a b() {
        try {
            a aVar = (a) super.clone();
            if (this.a != null) {
                ArrayList arrayList = this.a;
                aVar.a = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    aVar.a.add(arrayList.get(i));
                }
            }
            return aVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
