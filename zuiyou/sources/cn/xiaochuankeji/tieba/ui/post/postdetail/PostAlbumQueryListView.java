package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.content.Context;
import android.util.AttributeSet;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;

public class PostAlbumQueryListView extends QueryListView {
    private Context d;
    private int e = 0;

    public PostAlbumQueryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context;
    }
}
