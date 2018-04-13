package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCanceledListener;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import com.baidu.mobstat.Config;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class al extends LoaderManager {
    static boolean a = false;
    final SparseArrayCompat<a> b = new SparseArrayCompat();
    final SparseArrayCompat<a> c = new SparseArrayCompat();
    final String d;
    boolean e;
    boolean f;
    boolean g;
    FragmentHostCallback h;

    final class a implements OnLoadCanceledListener<Object>, OnLoadCompleteListener<Object> {
        final int a;
        final Bundle b;
        LoaderCallbacks<Object> c;
        Loader<Object> d;
        boolean e;
        boolean f;
        Object g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        boolean l;
        boolean m;
        a n;
        final /* synthetic */ al o;

        public a(al alVar, int i, Bundle bundle, LoaderCallbacks<Object> loaderCallbacks) {
            this.o = alVar;
            this.a = i;
            this.b = bundle;
            this.c = loaderCallbacks;
        }

        void a() {
            if (this.i && this.j) {
                this.h = true;
            } else if (!this.h) {
                this.h = true;
                if (al.a) {
                    Log.v("LoaderManager", "  Starting: " + this);
                }
                if (this.d == null && this.c != null) {
                    this.d = this.c.onCreateLoader(this.a, this.b);
                }
                if (this.d == null) {
                    return;
                }
                if (!this.d.getClass().isMemberClass() || Modifier.isStatic(this.d.getClass().getModifiers())) {
                    if (!this.m) {
                        this.d.registerListener(this.a, this);
                        this.d.registerOnLoadCanceledListener(this);
                        this.m = true;
                    }
                    this.d.startLoading();
                    return;
                }
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.d);
            }
        }

        void b() {
            if (al.a) {
                Log.v("LoaderManager", "  Retaining: " + this);
            }
            this.i = true;
            this.j = this.h;
            this.h = false;
            this.c = null;
        }

        void c() {
            if (this.i) {
                if (al.a) {
                    Log.v("LoaderManager", "  Finished Retaining: " + this);
                }
                this.i = false;
                if (!(this.h == this.j || this.h)) {
                    e();
                }
            }
            if (this.h && this.e && !this.k) {
                a(this.d, this.g);
            }
        }

        void d() {
            if (this.h && this.k) {
                this.k = false;
                if (this.e && !this.i) {
                    a(this.d, this.g);
                }
            }
        }

        void e() {
            if (al.a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.h = false;
            if (!this.i && this.d != null && this.m) {
                this.m = false;
                this.d.unregisterListener(this);
                this.d.unregisterOnLoadCanceledListener(this);
                this.d.stopLoading();
            }
        }

        boolean f() {
            if (al.a) {
                Log.v("LoaderManager", "  Canceling: " + this);
            }
            if (!this.h || this.d == null || !this.m) {
                return false;
            }
            boolean cancelLoad = this.d.cancelLoad();
            if (cancelLoad) {
                return cancelLoad;
            }
            onLoadCanceled(this.d);
            return cancelLoad;
        }

        void g() {
            String str;
            LoaderCallbacks loaderCallbacks = null;
            if (al.a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.l = true;
            boolean z = this.f;
            this.f = false;
            if (this.c != null && this.d != null && this.e && z) {
                if (al.a) {
                    Log.v("LoaderManager", "  Resetting: " + this);
                }
                if (this.o.h != null) {
                    String str2 = this.o.h.d.u;
                    this.o.h.d.u = "onLoaderReset";
                    str = str2;
                } else {
                    str = null;
                }
                try {
                    this.c.onLoaderReset(this.d);
                } finally {
                    loaderCallbacks = this.o.h;
                    if (loaderCallbacks != null) {
                        loaderCallbacks = this.o.h.d;
                        loaderCallbacks.u = str;
                    }
                }
            }
            this.c = loaderCallbacks;
            this.g = loaderCallbacks;
            this.e = false;
            if (this.d != null) {
                if (this.m) {
                    this.m = false;
                    this.d.unregisterListener(this);
                    this.d.unregisterOnLoadCanceledListener(this);
                }
                this.d.reset();
            }
            if (this.n != null) {
                this.n.g();
            }
        }

        public void onLoadCanceled(Loader<Object> loader) {
            if (al.a) {
                Log.v("LoaderManager", "onLoadCanceled: " + this);
            }
            if (this.l) {
                if (al.a) {
                    Log.v("LoaderManager", "  Ignoring load canceled -- destroyed");
                }
            } else if (this.o.b.get(this.a) == this) {
                a aVar = this.n;
                if (aVar != null) {
                    if (al.a) {
                        Log.v("LoaderManager", "  Switching to pending loader: " + aVar);
                    }
                    this.n = null;
                    this.o.b.put(this.a, null);
                    g();
                    this.o.a(aVar);
                }
            } else if (al.a) {
                Log.v("LoaderManager", "  Ignoring load canceled -- not active");
            }
        }

        public void onLoadComplete(Loader<Object> loader, Object obj) {
            if (al.a) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (this.l) {
                if (al.a) {
                    Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
                }
            } else if (this.o.b.get(this.a) == this) {
                a aVar = this.n;
                if (aVar != null) {
                    if (al.a) {
                        Log.v("LoaderManager", "  Switching to pending loader: " + aVar);
                    }
                    this.n = null;
                    this.o.b.put(this.a, null);
                    g();
                    this.o.a(aVar);
                    return;
                }
                if (!(this.g == obj && this.e)) {
                    this.g = obj;
                    this.e = true;
                    if (this.h) {
                        a(loader, obj);
                    }
                }
                aVar = (a) this.o.c.get(this.a);
                if (!(aVar == null || aVar == this)) {
                    aVar.f = false;
                    aVar.g();
                    this.o.c.remove(this.a);
                }
                if (this.o.h != null && !this.o.hasRunningLoaders()) {
                    this.o.h.d.c();
                }
            } else if (al.a) {
                Log.v("LoaderManager", "  Ignoring load complete -- not active");
            }
        }

        void a(Loader<Object> loader, Object obj) {
            String str;
            if (this.c != null) {
                if (this.o.h != null) {
                    String str2 = this.o.h.d.u;
                    this.o.h.d.u = "onLoadFinished";
                    str = str2;
                } else {
                    str = null;
                }
                try {
                    if (al.a) {
                        Log.v("LoaderManager", "  onLoadFinished in " + loader + ": " + loader.dataToString(obj));
                    }
                    this.c.onLoadFinished(loader, obj);
                    this.f = true;
                } finally {
                    if (this.o.h != null) {
                        this.o.h.d.u = str;
                    }
                }
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append("LoaderInfo{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" #");
            stringBuilder.append(this.a);
            stringBuilder.append(" : ");
            DebugUtils.buildShortClassTag(this.d, stringBuilder);
            stringBuilder.append("}}");
            return stringBuilder.toString();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.a);
            printWriter.print(" mArgs=");
            printWriter.println(this.b);
            printWriter.print(str);
            printWriter.print("mCallbacks=");
            printWriter.println(this.c);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.d);
            if (this.d != null) {
                this.d.dump(str + "  ", fileDescriptor, printWriter, strArr);
            }
            if (this.e || this.f) {
                printWriter.print(str);
                printWriter.print("mHaveData=");
                printWriter.print(this.e);
                printWriter.print("  mDeliveredData=");
                printWriter.println(this.f);
                printWriter.print(str);
                printWriter.print("mData=");
                printWriter.println(this.g);
            }
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.h);
            printWriter.print(" mReportNextStart=");
            printWriter.print(this.k);
            printWriter.print(" mDestroyed=");
            printWriter.println(this.l);
            printWriter.print(str);
            printWriter.print("mRetaining=");
            printWriter.print(this.i);
            printWriter.print(" mRetainingStarted=");
            printWriter.print(this.j);
            printWriter.print(" mListenerRegistered=");
            printWriter.println(this.m);
            if (this.n != null) {
                printWriter.print(str);
                printWriter.println("Pending Loader ");
                printWriter.print(this.n);
                printWriter.println(Config.TRACE_TODAY_VISIT_SPLIT);
                this.n.dump(str + "  ", fileDescriptor, printWriter, strArr);
            }
        }
    }

    al(String str, FragmentHostCallback fragmentHostCallback, boolean z) {
        this.d = str;
        this.h = fragmentHostCallback;
        this.e = z;
    }

    void a(FragmentHostCallback fragmentHostCallback) {
        this.h = fragmentHostCallback;
    }

    private a a(int i, Bundle bundle, LoaderCallbacks<Object> loaderCallbacks) {
        a aVar = new a(this, i, bundle, loaderCallbacks);
        aVar.d = loaderCallbacks.onCreateLoader(i, bundle);
        return aVar;
    }

    private a b(int i, Bundle bundle, LoaderCallbacks<Object> loaderCallbacks) {
        try {
            this.g = true;
            a a = a(i, bundle, loaderCallbacks);
            a(a);
            return a;
        } finally {
            this.g = false;
        }
    }

    void a(a aVar) {
        this.b.put(aVar.a, aVar);
        if (this.e) {
            aVar.a();
        }
    }

    public <D> Loader<D> initLoader(int i, Bundle bundle, LoaderCallbacks<D> loaderCallbacks) {
        if (this.g) {
            throw new IllegalStateException("Called while creating a loader");
        }
        a aVar = (a) this.b.get(i);
        if (a) {
            Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
        }
        if (aVar == null) {
            aVar = b(i, bundle, loaderCallbacks);
            if (a) {
                Log.v("LoaderManager", "  Created new loader " + aVar);
            }
        } else {
            if (a) {
                Log.v("LoaderManager", "  Re-using existing loader " + aVar);
            }
            aVar.c = loaderCallbacks;
        }
        if (aVar.e && this.e) {
            aVar.a(aVar.d, aVar.g);
        }
        return aVar.d;
    }

    public <D> Loader<D> restartLoader(int i, Bundle bundle, LoaderCallbacks<D> loaderCallbacks) {
        if (this.g) {
            throw new IllegalStateException("Called while creating a loader");
        }
        a aVar = (a) this.b.get(i);
        if (a) {
            Log.v("LoaderManager", "restartLoader in " + this + ": args=" + bundle);
        }
        if (aVar != null) {
            a aVar2 = (a) this.c.get(i);
            if (aVar2 == null) {
                if (a) {
                    Log.v("LoaderManager", "  Making last loader inactive: " + aVar);
                }
                aVar.d.abandon();
                this.c.put(i, aVar);
            } else if (aVar.e) {
                if (a) {
                    Log.v("LoaderManager", "  Removing last inactive loader: " + aVar);
                }
                aVar2.f = false;
                aVar2.g();
                aVar.d.abandon();
                this.c.put(i, aVar);
            } else if (aVar.f()) {
                if (a) {
                    Log.v("LoaderManager", "  Current loader is running; configuring pending loader");
                }
                if (aVar.n != null) {
                    if (a) {
                        Log.v("LoaderManager", "  Removing pending loader: " + aVar.n);
                    }
                    aVar.n.g();
                    aVar.n = null;
                }
                if (a) {
                    Log.v("LoaderManager", "  Enqueuing as new pending loader");
                }
                aVar.n = a(i, bundle, loaderCallbacks);
                return aVar.n.d;
            } else {
                if (a) {
                    Log.v("LoaderManager", "  Current loader is stopped; replacing");
                }
                this.b.put(i, null);
                aVar.g();
            }
        }
        return b(i, bundle, loaderCallbacks).d;
    }

    public void destroyLoader(int i) {
        if (this.g) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (a) {
            Log.v("LoaderManager", "destroyLoader in " + this + " of " + i);
        }
        int indexOfKey = this.b.indexOfKey(i);
        if (indexOfKey >= 0) {
            a aVar = (a) this.b.valueAt(indexOfKey);
            this.b.removeAt(indexOfKey);
            aVar.g();
        }
        indexOfKey = this.c.indexOfKey(i);
        if (indexOfKey >= 0) {
            aVar = (a) this.c.valueAt(indexOfKey);
            this.c.removeAt(indexOfKey);
            aVar.g();
        }
        if (this.h != null && !hasRunningLoaders()) {
            this.h.d.c();
        }
    }

    public <D> Loader<D> getLoader(int i) {
        if (this.g) {
            throw new IllegalStateException("Called while creating a loader");
        }
        a aVar = (a) this.b.get(i);
        if (aVar == null) {
            return null;
        }
        if (aVar.n != null) {
            return aVar.n.d;
        }
        return aVar.d;
    }

    void a() {
        if (a) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.e) {
            Throwable runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
            return;
        }
        this.e = true;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            ((a) this.b.valueAt(size)).a();
        }
    }

    void b() {
        if (a) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (this.e) {
            for (int size = this.b.size() - 1; size >= 0; size--) {
                ((a) this.b.valueAt(size)).e();
            }
            this.e = false;
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
    }

    void c() {
        if (a) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (this.e) {
            this.f = true;
            this.e = false;
            for (int size = this.b.size() - 1; size >= 0; size--) {
                ((a) this.b.valueAt(size)).b();
            }
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
    }

    void d() {
        if (this.f) {
            if (a) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.f = false;
            for (int size = this.b.size() - 1; size >= 0; size--) {
                ((a) this.b.valueAt(size)).c();
            }
        }
    }

    void e() {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            ((a) this.b.valueAt(size)).k = true;
        }
    }

    void f() {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            ((a) this.b.valueAt(size)).d();
        }
    }

    void g() {
        int size;
        if (!this.f) {
            if (a) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (size = this.b.size() - 1; size >= 0; size--) {
                ((a) this.b.valueAt(size)).g();
            }
            this.b.clear();
        }
        if (a) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (size = this.c.size() - 1; size >= 0; size--) {
            ((a) this.c.valueAt(size)).g();
        }
        this.c.clear();
        this.h = null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("LoaderManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        DebugUtils.buildShortClassTag(this.h, stringBuilder);
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        if (this.b.size() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            String str2 = str + "    ";
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                a aVar = (a) this.b.valueAt(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.b.keyAt(i2));
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.dump(str2, fileDescriptor, printWriter, strArr);
            }
        }
        if (this.c.size() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            String str3 = str + "    ";
            while (i < this.c.size()) {
                aVar = (a) this.c.valueAt(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.c.keyAt(i));
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.dump(str3, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    public boolean hasRunningLoaders() {
        int size = this.b.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            int i2;
            a aVar = (a) this.b.valueAt(i);
            if (!aVar.h || aVar.f) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            z |= i2;
        }
        return z;
    }
}
