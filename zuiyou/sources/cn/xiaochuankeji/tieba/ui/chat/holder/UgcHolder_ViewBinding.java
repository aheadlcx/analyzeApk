package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class UgcHolder_ViewBinding implements Unbinder {
    private UgcHolder b;

    @UiThread
    public UgcHolder_ViewBinding(UgcHolder ugcHolder, View view) {
        this.b = ugcHolder;
        ugcHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        ugcHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
        ugcHolder.thumb = (WebImageView) b.b(view, R.id.thumb, "field 'thumb'", WebImageView.class);
        ugcHolder.title = (TextView) b.b(view, R.id.title, "field 'title'", TextView.class);
        ugcHolder.click_area = b.a(view, R.id.click_area, "field 'click_area'");
    }

    @CallSuper
    public void a() {
        UgcHolder ugcHolder = this.b;
        if (ugcHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        ugcHolder.avatar = null;
        ugcHolder.content = null;
        ugcHolder.thumb = null;
        ugcHolder.title = null;
        ugcHolder.click_area = null;
    }
}
