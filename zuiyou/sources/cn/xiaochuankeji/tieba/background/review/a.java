package cn.xiaochuankeji.tieba.background.review;

import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.f.b;

public class a {
    public static String a(Comment comment) {
        cn.xiaochuankeji.tieba.ui.widget.image.a aVar;
        if (comment.getCommentImage() == null || comment.getCommentImage().size() <= 0) {
            aVar = null;
        } else {
            aVar = b.a(((ServerImage) comment.getCommentImage().get(0)).postImageId, false);
        }
        if (aVar == null || !aVar.d()) {
            return null;
        }
        return aVar.e();
    }
}
