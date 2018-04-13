package cn.xiaochuankeji.tieba.background.utils.share;

import cn.htjyb.c.a.b;
import cn.xiaochuan.base.BaseApplication;
import java.io.File;
import java.io.IOException;

public class a {
    public static String a() {
        String str = cn.xiaochuankeji.tieba.background.a.e().l() + "share_logo_v32500.jpg";
        File file = new File(str);
        if (!file.exists()) {
            try {
                b.a(BaseApplication.getAppContext().getAssets().open("share_logo_v32500.jpg"), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
