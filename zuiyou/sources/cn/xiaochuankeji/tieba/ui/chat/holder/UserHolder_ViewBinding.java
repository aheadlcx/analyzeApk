package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class UserHolder_ViewBinding implements Unbinder {
    private UserHolder b;

    @UiThread
    public UserHolder_ViewBinding(UserHolder userHolder, View view) {
        this.b = userHolder;
        userHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        userHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
        userHolder.thumb = (WebImageView) b.b(view, R.id.thumb, "field 'thumb'", WebImageView.class);
        userHolder.title = (TextView) b.b(view, R.id.title, "field 'title'", TextView.class);
        userHolder.click_area = b.a(view, R.id.click_area, "field 'click_area'");
    }

    @CallSuper
    public void a() {
        UserHolder userHolder = this.b;
        if (userHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        userHolder.avatar = null;
        userHolder.content = null;
        userHolder.thumb = null;
        userHolder.title = null;
        userHolder.click_area = null;
    }
}
