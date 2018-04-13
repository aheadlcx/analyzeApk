package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.widget.rich.RichTextBoard;

public class TaleBodyHolder_ViewBinding implements Unbinder {
    private TaleBodyHolder b;

    @UiThread
    public TaleBodyHolder_ViewBinding(TaleBodyHolder taleBodyHolder, View view) {
        this.b = taleBodyHolder;
        taleBodyHolder.textBoard = (RichTextBoard) b.b(view, R.id.body, "field 'textBoard'", RichTextBoard.class);
    }

    @CallSuper
    public void a() {
        TaleBodyHolder taleBodyHolder = this.b;
        if (taleBodyHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleBodyHolder.textBoard = null;
    }
}
