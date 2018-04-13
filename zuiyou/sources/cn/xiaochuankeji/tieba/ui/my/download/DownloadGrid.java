package cn.xiaochuankeji.tieba.ui.my.download;

import android.content.Context;
import android.util.AttributeSet;
import com.zhihu.matisse.internal.ui.widget.MediaGrid;

public class DownloadGrid extends MediaGrid {
    public DownloadGrid(Context context) {
        super(context);
    }

    public DownloadGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void init(Context context) {
        super.init(context);
        this.mCheckView.setVisibility(8);
    }
}
