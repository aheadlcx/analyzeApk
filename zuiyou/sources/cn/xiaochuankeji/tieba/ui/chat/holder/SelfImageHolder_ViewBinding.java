package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class SelfImageHolder_ViewBinding implements Unbinder {
    private SelfImageHolder b;

    @UiThread
    public SelfImageHolder_ViewBinding(SelfImageHolder selfImageHolder, View view) {
        this.b = selfImageHolder;
        selfImageHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        selfImageHolder.image = (WebImageView) b.b(view, R.id.image, "field 'image'", WebImageView.class);
        selfImageHolder.resend = b.a(view, R.id.resend, "field 'resend'");
    }

    @CallSuper
    public void a() {
        SelfImageHolder selfImageHolder = this.b;
        if (selfImageHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        selfImageHolder.avatar = null;
        selfImageHolder.image = null;
        selfImageHolder.resend = null;
    }
}
