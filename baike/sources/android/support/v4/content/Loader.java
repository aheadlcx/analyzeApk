package android.support.v4.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.DebugUtils;
import com.alipay.sdk.util.h;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader<D> {
    int n;
    OnLoadCompleteListener<D> o;
    OnLoadCanceledListener<D> p;
    Context q;
    boolean r = false;
    boolean s = false;
    boolean t = true;
    boolean u = false;
    boolean v = false;

    public interface OnLoadCanceledListener<D> {
        void onLoadCanceled(@NonNull Loader<D> loader);
    }

    public interface OnLoadCompleteListener<D> {
        void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d);
    }

    public final class ForceLoadContentObserver extends ContentObserver {
        final /* synthetic */ Loader a;

        public ForceLoadContentObserver(Loader loader) {
            this.a = loader;
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            this.a.onContentChanged();
        }
    }

    public Loader(@NonNull Context context) {
        this.q = context.getApplicationContext();
    }

    public void deliverResult(@Nullable D d) {
        if (this.o != null) {
            this.o.onLoadComplete(this, d);
        }
    }

    public void deliverCancellation() {
        if (this.p != null) {
            this.p.onLoadCanceled(this);
        }
    }

    @NonNull
    public Context getContext() {
        return this.q;
    }

    public int getId() {
        return this.n;
    }

    public void registerListener(int i, @NonNull OnLoadCompleteListener<D> onLoadCompleteListener) {
        if (this.o != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.o = onLoadCompleteListener;
        this.n = i;
    }

    public void unregisterListener(@NonNull OnLoadCompleteListener<D> onLoadCompleteListener) {
        if (this.o == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.o != onLoadCompleteListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.o = null;
        }
    }

    public void registerOnLoadCanceledListener(@NonNull OnLoadCanceledListener<D> onLoadCanceledListener) {
        if (this.p != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.p = onLoadCanceledListener;
    }

    public void unregisterOnLoadCanceledListener(@NonNull OnLoadCanceledListener<D> onLoadCanceledListener) {
        if (this.p == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.p != onLoadCanceledListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.p = null;
        }
    }

    public boolean isStarted() {
        return this.r;
    }

    public boolean isAbandoned() {
        return this.s;
    }

    public boolean isReset() {
        return this.t;
    }

    public final void startLoading() {
        this.r = true;
        this.t = false;
        this.s = false;
        e();
    }

    protected void e() {
    }

    public boolean cancelLoad() {
        return b();
    }

    protected boolean b() {
        return false;
    }

    public void forceLoad() {
        a();
    }

    protected void a() {
    }

    public void stopLoading() {
        this.r = false;
        f();
    }

    protected void f() {
    }

    public void abandon() {
        this.s = true;
        h();
    }

    protected void h() {
    }

    public void reset() {
        g();
        this.t = true;
        this.r = false;
        this.s = false;
        this.u = false;
        this.v = false;
    }

    protected void g() {
    }

    public boolean takeContentChanged() {
        boolean z = this.u;
        this.u = false;
        this.v |= z;
        return z;
    }

    public void commitContentChanged() {
        this.v = false;
    }

    public void rollbackContentChanged() {
        if (this.v) {
            onContentChanged();
        }
    }

    public void onContentChanged() {
        if (this.r) {
            forceLoad();
        } else {
            this.u = true;
        }
    }

    @NonNull
    public String dataToString(@Nullable D d) {
        StringBuilder stringBuilder = new StringBuilder(64);
        DebugUtils.buildShortClassTag(d, stringBuilder);
        stringBuilder.append(h.d);
        return stringBuilder.toString();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        DebugUtils.buildShortClassTag(this, stringBuilder);
        stringBuilder.append(" id=");
        stringBuilder.append(this.n);
        stringBuilder.append(h.d);
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.n);
        printWriter.print(" mListener=");
        printWriter.println(this.o);
        if (this.r || this.u || this.v) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.r);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.u);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.v);
        }
        if (this.s || this.t) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.s);
            printWriter.print(" mReset=");
            printWriter.println(this.t);
        }
    }
}
