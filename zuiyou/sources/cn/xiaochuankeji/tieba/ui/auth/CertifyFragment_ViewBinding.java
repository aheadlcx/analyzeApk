package cn.xiaochuankeji.tieba.ui.auth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class CertifyFragment_ViewBinding implements Unbinder {
    private CertifyFragment b;
    private View c;
    private View d;

    @UiThread
    public CertifyFragment_ViewBinding(final CertifyFragment certifyFragment, View view) {
        this.b = certifyFragment;
        View a = b.a(view, R.id.close, "method 'close'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ CertifyFragment_ViewBinding c;

            public void a(View view) {
                certifyFragment.close();
            }
        });
        a = b.a(view, R.id.certify, "method 'certify'");
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ CertifyFragment_ViewBinding c;

            public void a(View view) {
                certifyFragment.certify();
            }
        });
    }

    @CallSuper
    public void a() {
        if (this.b == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
