package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.utils.a.g;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.post.postitem.MultipleImgFrameLayoutContainer.a;
import java.util.ArrayList;
import java.util.Iterator;

public class f extends e {
    private MultipleImgFrameLayoutContainer o;
    private ArrayList<ServerImage> p;

    public f(Context context, int i) {
        super(context, i);
    }

    protected void c(int i) {
        this.b.setVisibility(0);
        this.o = new MultipleImgFrameLayoutContainer(this.d);
        this.o.setLayoutParams(new LayoutParams(-1, -2));
        this.b.addView(this.o);
    }

    protected void g() {
        if (this.g != null && this.g._imgList != null && this.g._imgList.size() != 0) {
            this.p = this.g._imgList;
            this.o.setData(this.p);
            this.o.setOnContainerClickListener(new a(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    this.a.e(i);
                }
            });
        }
    }

    private void e(final int i) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        r.a = "post";
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            cn.htjyb.b.a a;
            ServerImage serverImage = (ServerImage) it.next();
            arrayList2.add(cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPic228, serverImage.postImageId));
            if (serverImage.isVideo()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(this.g.getImgVideoBy(serverImage.postImageId).getUrl(), Type.kVideo, serverImage.postImageId);
            } else if (serverImage.isMP4()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kMP4, (long) serverImage.mp4Id);
            } else if (serverImage.isGif()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kGif, serverImage.postImageId);
            } else {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPicLarge, serverImage.postImageId);
            }
            a.setRotate(serverImage.rotate);
            arrayList.add(a);
        }
        this.j.a(this.g);
        g.a().a = false;
        PermissionItem permissionItem = new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE");
        permissionItem.deniedMessage = "拒绝该权限后无法正常浏览大图和视频";
        permissionItem.deniedButton = "仍然拒绝";
        permissionItem.needGotoSetting = true;
        permissionItem.rationalButton = "确认";
        permissionItem.rationalMessage = "打开存储权限后才可以正常浏览大图和视频";
        permissionItem.runIgnorePermission = false;
        permissionItem.settingText = "前往设置";
        permissionItem.runIgnorePermission = false;
        cn.xiaochuankeji.aop.permission.a.a(this.d).a(permissionItem, new e(this) {
            final /* synthetic */ f d;

            public void permissionGranted() {
                MediaBrowseActivity.a(this.d.d, i, this.d.g, arrayList2, arrayList, this.d.p, this.d.g.imgVideos, this.d.l);
            }

            public void permissionDenied() {
                cn.xiaochuankeji.tieba.background.utils.g.a("没有存储权限，无法正常浏览大图和视频");
            }
        });
    }

    public void a(EntranceType entranceType) {
        this.l = entranceType;
        this.l = entranceType;
        if (this.k == null) {
            return;
        }
        if (entranceType == EntranceType.Post_RecommendImgTxt) {
            this.k.a(EntranceType.Review_RecommendImgTxt);
        } else if (entranceType == EntranceType.Post_RecommendIndex) {
            this.k.a(EntranceType.Review_RecommendIndex);
        } else if (entranceType == EntranceType.Post_RecommendVideo) {
            this.k.a(EntranceType.Review_RecommendVideo);
        } else {
            this.k.a(entranceType);
        }
    }
}
