package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import com.baidu.mobstat.Config;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback<E> extends FragmentContainer {
    private final Activity a;
    final Context b;
    final int c;
    final k d;
    private final Handler e;
    private SimpleArrayMap<String, LoaderManager> f;
    private boolean g;
    private al h;
    private boolean i;
    private boolean j;

    @Nullable
    public abstract E onGetHost();

    public FragmentHostCallback(Context context, Handler handler, int i) {
        this(context instanceof Activity ? (Activity) context : null, context, handler, i);
    }

    FragmentHostCallback(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, fragmentActivity.mHandler, 0);
    }

    FragmentHostCallback(Activity activity, Context context, Handler handler, int i) {
        this.d = new k();
        this.a = activity;
        this.b = context;
        this.e = handler;
        this.c = i;
    }

    public void onDump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public boolean onShouldSaveFragmentState(Fragment fragment) {
        return true;
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater() {
        return LayoutInflater.from(this.b);
    }

    public void onSupportInvalidateOptionsMenu() {
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i) {
        onStartActivityFromFragment(fragment, intent, i, null);
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, @Nullable Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.b.startActivity(intent);
    }

    public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, Bundle bundle) throws SendIntentException {
        if (i != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.startIntentSenderForResult(this.a, intentSender, i, intent, i2, i3, i4, bundle);
    }

    public void onRequestPermissionsFromFragment(@NonNull Fragment fragment, @NonNull String[] strArr, int i) {
    }

    public boolean onShouldShowRequestPermissionRationale(@NonNull String str) {
        return false;
    }

    public boolean onHasWindowAnimations() {
        return true;
    }

    public int onGetWindowAnimations() {
        return this.c;
    }

    @Nullable
    public View onFindViewById(int i) {
        return null;
    }

    public boolean onHasView() {
        return true;
    }

    Activity a() {
        return this.a;
    }

    Context b() {
        return this.b;
    }

    Handler c() {
        return this.e;
    }

    k d() {
        return this.d;
    }

    al e() {
        if (this.h != null) {
            return this.h;
        }
        this.i = true;
        this.h = a("(root)", this.j, true);
        return this.h;
    }

    void a(String str) {
        if (this.f != null) {
            al alVar = (al) this.f.get(str);
            if (alVar != null && !alVar.f) {
                alVar.g();
                this.f.remove(str);
            }
        }
    }

    void onAttachFragment(Fragment fragment) {
    }

    boolean f() {
        return this.g;
    }

    void g() {
        if (!this.j) {
            this.j = true;
            if (this.h != null) {
                this.h.a();
            } else if (!this.i) {
                this.h = a("(root)", this.j, false);
                if (!(this.h == null || this.h.e)) {
                    this.h.a();
                }
            }
            this.i = true;
        }
    }

    void a(boolean z) {
        this.g = z;
        if (this.h != null && this.j) {
            this.j = false;
            if (z) {
                this.h.c();
            } else {
                this.h.b();
            }
        }
    }

    void h() {
        if (this.h != null) {
            this.h.c();
        }
    }

    void i() {
        if (this.h != null) {
            this.h.g();
        }
    }

    void j() {
        if (this.f != null) {
            int size = this.f.size();
            al[] alVarArr = new al[size];
            for (int i = size - 1; i >= 0; i--) {
                alVarArr[i] = (al) this.f.valueAt(i);
            }
            for (int i2 = 0; i2 < size; i2++) {
                al alVar = alVarArr[i2];
                alVar.d();
                alVar.f();
            }
        }
    }

    al a(String str, boolean z, boolean z2) {
        if (this.f == null) {
            this.f = new SimpleArrayMap();
        }
        al alVar = (al) this.f.get(str);
        if (alVar == null && z2) {
            alVar = new al(str, this, z);
            this.f.put(str, alVar);
            return alVar;
        } else if (!z || alVar == null || alVar.e) {
            return alVar;
        } else {
            alVar.a();
            return alVar;
        }
    }

    SimpleArrayMap<String, LoaderManager> k() {
        int i;
        int i2 = 0;
        if (this.f != null) {
            int size = this.f.size();
            al[] alVarArr = new al[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                alVarArr[i3] = (al) this.f.valueAt(i3);
            }
            boolean f = f();
            i = 0;
            while (i2 < size) {
                al alVar = alVarArr[i2];
                if (!alVar.f && f) {
                    if (!alVar.e) {
                        alVar.a();
                    }
                    alVar.c();
                }
                if (alVar.f) {
                    i = 1;
                } else {
                    alVar.g();
                    this.f.remove(alVar.d);
                }
                i2++;
            }
        } else {
            i = 0;
        }
        if (i != 0) {
            return this.f;
        }
        return null;
    }

    void a(SimpleArrayMap<String, LoaderManager> simpleArrayMap) {
        if (simpleArrayMap != null) {
            int size = simpleArrayMap.size();
            for (int i = 0; i < size; i++) {
                ((al) simpleArrayMap.valueAt(i)).a(this);
            }
        }
        this.f = simpleArrayMap;
    }

    void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.j);
        if (this.h != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.h)));
            printWriter.println(Config.TRACE_TODAY_VISIT_SPLIT);
            this.h.dump(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }
}
