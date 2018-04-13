package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.widget.rich.LineSpaceExtraCompatTextView;

public class SelfTextHolder_ViewBinding implements Unbinder {
    private SelfTextHolder b;

    @UiThread
    public SelfTextHolder_ViewBinding(SelfTextHolder selfTextHolder, View view) {
        this.b = selfTextHolder;
        selfTextHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        selfTextHolder.content = (LineSpaceExtraCompatTextView) b.b(view, R.id.content, "field 'content'", LineSpaceExtraCompatTextView.class);
        selfTextHolder.resend = b.a(view, R.id.resend, "field 'resend'");
        selfTextHolder.progres = b.a(view, R.id.progres, "field 'progres'");
    }

    @CallSuper
    public void a() {
        SelfTextHolder selfTextHolder = this.b;
        if (selfTextHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        selfTextHolder.avatar = null;
        selfTextHolder.content = null;
        selfTextHolder.resend = null;
        selfTextHolder.progres = null;
    }
}
