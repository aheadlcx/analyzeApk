package cn.xiaochuankeji.tieba.ui.utils;

import android.content.Context;
import cn.htjyb.b.a;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.a.g;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class c {
    public static void a(Context context, Post post, Comment comment, long j, long j2, ArrayList<ServerImage> arrayList, HashMap<Long, ServerVideo> hashMap, int i, EntranceType entranceType) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (arrayList != null && arrayList.size() != 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a a;
                ServerImage serverImage = (ServerImage) it.next();
                arrayList3.add(cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPic228, serverImage.postImageId));
                if (serverImage.isVideo()) {
                    a = cn.xiaochuankeji.tieba.background.a.f().a(serverImage.videoUrl, Type.kVideo, serverImage.postImageId);
                } else if (serverImage.isMP4()) {
                    a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kMP4, (long) serverImage.mp4Id);
                } else if (serverImage.isGif()) {
                    a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kGif, serverImage.postImageId);
                } else {
                    a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kCommentOriginImg, serverImage.postImageId);
                }
                a.setRotate(serverImage.rotate);
                arrayList2.add(a);
            }
            g.a().a = false;
            MediaBrowseActivity.a(context, i, post, comment, arrayList3, arrayList2, arrayList, hashMap, entranceType, j, j2);
        }
    }
}
