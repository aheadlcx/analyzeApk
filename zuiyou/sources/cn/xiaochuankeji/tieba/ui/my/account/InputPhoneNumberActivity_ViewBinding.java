package cn.xiaochuankeji.tieba.ui.my.account;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class InputPhoneNumberActivity_ViewBinding implements Unbinder {
    private InputPhoneNumberActivity b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public InputPhoneNumberActivity_ViewBinding(final InputPhoneNumberActivity inputPhoneNumberActivity, View view) {
        this.b = inputPhoneNumberActivity;
        inputPhoneNumberActivity.tvIntro = (TextView) b.b(view, R.id.tvIntro, "field 'tvIntro'", TextView.class);
        View a = b.a(view, R.id.bnNext, "field 'bnNext' and method 'commit'");
        inputPhoneNumberActivity.bnNext = (Button) b.c(a, R.id.bnNext, "field 'bnNext'", Button.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ InputPhoneNumberActivity_ViewBinding c;

            public void a(View view) {
                inputPhoneNumberActivity.commit();
            }
        });
        inputPhoneNumberActivity.title = (TextView) b.b(view, R.id.title, "field 'title'", TextView.class);
        inputPhoneNumberActivity.tips = (AppCompatTextView) b.b(view, R.id.tips, "field 'tips'", AppCompatTextView.class);
        a = b.a(view, R.id.cc, "field 'cc' and method 'openRegion'");
        inputPhoneNumberActivity.cc = (AppCompatTextView) b.c(a, R.id.cc, "field 'cc'", AppCompatTextView.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ InputPhoneNumberActivity_ViewBinding c;

            public void a(View view) {
                inputPhoneNumberActivity.openRegion();
            }
        });
        inputPhoneNumberActivity.phone_layout = b.a(view, R.id.phone_layout, "field 'phone_layout'");
        inputPhoneNumberActivity.clearPhoneView = (AppCompatImageView) b.b(view, R.id.iv_clear_phone, "field 'clearPhoneView'", AppCompatImageView.class);
        inputPhoneNumberActivity.phoneEdit = (EditText) b.b(view, R.id.phone, "field 'phoneEdit'", EditText.class);
        inputPhoneNumberActivity.bind_tips = (AppCompatTextView) b.b(view, R.id.bind_tips, "field 'bind_tips'", AppCompatTextView.class);
        inputPhoneNumberActivity.codeEdit = (EditText) b.b(view, R.id.etPhoneCode, "field 'codeEdit'", EditText.class);
        inputPhoneNumberActivity.errorTipText = (TextView) b.b(view, R.id.tv_error_tip, "field 'errorTipText'", TextView.class);
        inputPhoneNumberActivity.codeActionText = (TextView) b.b(view, R.id.code_action, "field 'codeActionText'", TextView.class);
        View a2 = b.a(view, R.id.back, "method 'back'");
        this.e = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ InputPhoneNumberActivity_ViewBinding c;

            public void a(View view) {
                inputPhoneNumberActivity.back();
            }
        });
    }

    @CallSuper
    public void a() {
        InputPhoneNumberActivity inputPhoneNumberActivity = this.b;
        if (inputPhoneNumberActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        inputPhoneNumberActivity.tvIntro = null;
        inputPhoneNumberActivity.bnNext = null;
        inputPhoneNumberActivity.title = null;
        inputPhoneNumberActivity.tips = null;
        inputPhoneNumberActivity.cc = null;
        inputPhoneNumberActivity.phone_layout = null;
        inputPhoneNumberActivity.clearPhoneView = null;
        inputPhoneNumberActivity.phoneEdit = null;
        inputPhoneNumberActivity.bind_tips = null;
        inputPhoneNumberActivity.codeEdit = null;
        inputPhoneNumberActivity.errorTipText = null;
        inputPhoneNumberActivity.codeActionText = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
