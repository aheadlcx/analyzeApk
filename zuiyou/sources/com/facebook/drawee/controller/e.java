package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class e<INFO> implements c<INFO> {
    private final List<c<? super INFO>> a = new ArrayList(2);

    public synchronized void a(c<? super INFO> cVar) {
        this.a.add(cVar);
    }

    public synchronized void a() {
        this.a.clear();
    }

    private synchronized void c(String str, Throwable th) {
        Log.e("FdingControllerListener", str, th);
    }

    public synchronized void a(String str, Object obj) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((c) this.a.get(i)).a(str, obj);
            } catch (Throwable e) {
                c("InternalListener exception in onSubmit", e);
            }
        }
    }

    public synchronized void a(String str, @Nullable INFO info, @Nullable Animatable animatable) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((c) this.a.get(i)).a(str, info, animatable);
            } catch (Throwable e) {
                c("InternalListener exception in onFinalImageSet", e);
            }
        }
    }

    public void b(String str, @Nullable INFO info) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((c) this.a.get(i)).b(str, (Object) info);
            } catch (Throwable e) {
                c("InternalListener exception in onIntermediateImageSet", e);
            }
        }
    }

    public void b(String str, Throwable th) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((c) this.a.get(i)).b(str, th);
            } catch (Throwable e) {
                c("InternalListener exception in onIntermediateImageFailed", e);
            }
        }
    }

    public synchronized void a(String str, Throwable th) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((c) this.a.get(i)).a(str, th);
            } catch (Throwable e) {
                c("InternalListener exception in onFailure", e);
            }
        }
    }

    public synchronized void a(String str) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((c) this.a.get(i)).a(str);
            } catch (Throwable e) {
                c("InternalListener exception in onRelease", e);
            }
        }
    }
}
