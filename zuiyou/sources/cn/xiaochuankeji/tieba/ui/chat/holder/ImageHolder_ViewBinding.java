package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class ImageHolder_ViewBinding implements Unbinder {
    private ImageHolder b;

    @UiThread
    public ImageHolder_ViewBinding(ImageHolder imageHolder, View view) {
        this.b = imageHolder;
        imageHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        imageHolder.image = (WebImageView) b.b(view, R.id.image, "field 'image'", WebImageView.class);
    }

    @CallSuper
    public void a() {
        ImageHolder imageHolder = this.b;
        if (imageHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        imageHolder.avatar = null;
        imageHolder.image = null;
    }
}
