package cn.xiaochuankeji.tieba.background.upload;

import android.text.TextUtils;
import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import com.zhihu.matisse.MimeType;
import java.util.Iterator;

public class i {
    public static String a(LocalMedia localMedia) {
        Object b = b.b(localMedia.path);
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        if (TextUtils.isEmpty(localMedia.mimeType)) {
            return null;
        }
        for (MimeType mimeType : MimeType.ofAll()) {
            if (localMedia.mimeType.equals(mimeType.mMimeTypeName) && mimeType.mExtensions != null) {
                Iterator it = mimeType.mExtensions.iterator();
                if (it.hasNext()) {
                    return (String) it.next();
                }
            }
        }
        return null;
    }
}
