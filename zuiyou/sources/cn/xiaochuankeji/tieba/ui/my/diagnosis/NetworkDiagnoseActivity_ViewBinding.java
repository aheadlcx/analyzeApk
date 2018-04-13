package cn.xiaochuankeji.tieba.ui.my.diagnosis;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class NetworkDiagnoseActivity_ViewBinding implements Unbinder {
    private NetworkDiagnoseActivity b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public NetworkDiagnoseActivity_ViewBinding(final NetworkDiagnoseActivity networkDiagnoseActivity, View view) {
        this.b = networkDiagnoseActivity;
        View a = b.a(view, R.id.back, "method 'back'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ NetworkDiagnoseActivity_ViewBinding c;

            public void a(View view) {
                networkDiagnoseActivity.back();
            }
        });
        a = b.a(view, R.id.btn_start, "method 'btnStart'");
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ NetworkDiagnoseActivity_ViewBinding c;

            public void a(View view) {
                networkDiagnoseActivity.btnStart(view);
            }
        });
        a = b.a(view, R.id.btn_copy, "method 'copy'");
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ NetworkDiagnoseActivity_ViewBinding c;

            public void a(View view) {
                networkDiagnoseActivity.copy();
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
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
