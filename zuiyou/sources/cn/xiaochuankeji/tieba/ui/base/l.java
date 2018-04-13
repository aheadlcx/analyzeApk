package cn.xiaochuankeji.tieba.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class l extends f {
    private boolean a;
    private boolean b;
    private boolean c = true;
    private boolean d = false;

    protected abstract View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected abstract void e();

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.size() > 0) {
            a(arguments);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = true;
        View a = a(layoutInflater, viewGroup, bundle);
        this.b = true;
        d();
        return a;
    }

    public void setUserVisibleHint(boolean z) {
        try {
            super.setUserVisibleHint(z);
            if (getUserVisibleHint()) {
                b();
            } else {
                c();
            }
        } catch (RuntimeException e) {
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            c();
        } else {
            b();
        }
    }

    protected void b() {
        this.a = true;
        d();
    }

    protected void c() {
        this.a = false;
    }

    protected void d() {
        if (!f() || !h()) {
            return;
        }
        if (this.d || g()) {
            this.d = false;
            this.c = false;
            e();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.b = false;
    }

    public void a(Bundle bundle) {
    }

    public boolean f() {
        return this.b;
    }

    public boolean g() {
        return this.c;
    }

    public boolean h() {
        return this.a;
    }
}
