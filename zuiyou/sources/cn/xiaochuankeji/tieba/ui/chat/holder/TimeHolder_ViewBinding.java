package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class TimeHolder_ViewBinding implements Unbinder {
    private TimeHolder b;

    @UiThread
    public TimeHolder_ViewBinding(TimeHolder timeHolder, View view) {
        this.b = timeHolder;
        timeHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
    }

    @CallSuper
    public void a() {
        TimeHolder timeHolder = this.b;
        if (timeHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        timeHolder.content = null;
    }
}
