package cn.xiaochuankeji.tieba.ui.post;

import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.ui.widget.image.a;

public class e {
    public static String a(Post post) {
        a b = post._imgList.size() > 0 ? post._imgList.size() == 1 ? b.b(((ServerImage) post._imgList.get(0)).postImageId, true) : b.b(((ServerImage) post._imgList.get(0)).postImageId, false) : null;
        if (b == null || !b.d()) {
            return null;
        }
        return b.e();
    }
}
