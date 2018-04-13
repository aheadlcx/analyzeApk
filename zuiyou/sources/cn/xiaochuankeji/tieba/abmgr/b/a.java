package cn.xiaochuankeji.tieba.abmgr.b;

import android.widget.ImageView;
import cn.xiaochuankeji.tieba.abmgr.a.b;
import cn.xiaochuankeji.tieba.abmgr.data.Requirement;

public class a {
    public static void a(ImageView imageView) {
        switch (b.a().a(Requirement.HOME_REFRESH)) {
            case HOME_REFRESH_VISIBLE:
                imageView.setVisibility(0);
                return;
            case HOME_REFRESH_GONE:
                imageView.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public static void a(ImageView imageView, boolean z) {
        if (z) {
            imageView.setVisibility(8);
        } else {
            a(imageView);
        }
    }
}
