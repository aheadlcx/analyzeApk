package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import c.a.i.u;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.facebook.drawee.a.a.e;
import com.facebook.drawee.controller.c;
import com.facebook.drawee.d.a;
import com.facebook.imagepipeline.g.f;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class h extends e implements u {
    private EntranceType A = EntranceType.PostItem;
    private WebImageView o;
    private WebImageView p;
    private FrameLayout q;
    private ImageView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private ServerImage v;
    private TextView w;
    private TextView x;
    private Runnable y;
    private a z;

    public h(Context context) {
        super(context);
    }

    protected void c(int i) {
        this.b.removeAllViews();
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.postitem_one_pic_fl, this.b);
        this.b.setVisibility(0);
        this.o = (WebImageView) inflate.findViewById(R.id.picture_view);
        this.p = (WebImageView) inflate.findViewById(R.id.portrait_picture_view);
        this.q = (FrameLayout) inflate.findViewById(R.id.flVBottomLine);
        this.u = (TextView) inflate.findViewById(R.id.tvLongImgFlag);
        this.r = (ImageView) inflate.findViewById(R.id.ivVFlag);
        this.s = (TextView) inflate.findViewById(R.id.tvVDur);
        this.t = (TextView) inflate.findViewById(R.id.tvPlayCount);
        this.x = (TextView) inflate.findViewById(R.id.tvDanmuCount);
        this.w = (TextView) inflate.findViewById(R.id.btn_retry);
        this.w.setTag(Boolean.FALSE);
        this.w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (((Boolean) view.getTag()).booleanValue() && this.a.v != null) {
                    this.a.b(true, this.a.v.isImage());
                }
            }
        });
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
    }

    protected void g() {
        if (this.g._imgList != null && this.g._imgList.size() != 0) {
            boolean z;
            this.v = (ServerImage) this.g._imgList.get(0);
            b(true, this.v.isImage());
            boolean isGif = this.v.isGif();
            boolean isVideo = this.v.isVideo();
            if (isGif || isVideo || ((double) ((float) (this.v.height / this.v.width))) <= 2.5d) {
                z = false;
            } else {
                z = true;
            }
            d();
            if (isVideo) {
                this.r.setVisibility(0);
                this.u.setVisibility(8);
                this.q.setVisibility(0);
                ServerVideo imgVideoBy = this.g.getImgVideoBy(this.v.postImageId);
                if (imgVideoBy != null) {
                    long duration = imgVideoBy.getDuration();
                    if (0 != duration) {
                        this.s.setVisibility(0);
                        this.s.setText(d.a(duration * 1000));
                    } else {
                        this.s.setVisibility(8);
                    }
                    int playCount = imgVideoBy.getPlayCount();
                    if (playCount > 0) {
                        this.t.setText(d.b(playCount));
                        this.x.setVisibility(0);
                    } else {
                        this.t.setVisibility(8);
                    }
                    playCount = this.v.danmakuCount;
                    if (playCount > 0) {
                        this.x.setText(d.b(playCount));
                        this.x.setVisibility(0);
                        return;
                    }
                    this.x.setVisibility(8);
                }
            } else if (isGif) {
                this.r.setVisibility(0);
                this.q.setVisibility(8);
                this.u.setVisibility(8);
            } else if (z) {
                this.r.setVisibility(8);
                this.q.setVisibility(8);
                this.u.setVisibility(0);
            } else {
                this.r.setVisibility(8);
                this.u.setVisibility(8);
                this.q.setVisibility(8);
            }
        }
    }

    private boolean a(long j) {
        if (this.g._ID == j && this.g.imageLoadState == 1) {
            return true;
        }
        return false;
    }

    private void p() {
        if (this.y != null) {
            this.o.removeCallbacks(this.y);
            this.y = null;
        }
    }

    private void b(boolean z, final boolean z2) {
        final long j = this.g._ID;
        if (TextUtils.isEmpty(this.g.campaignId) || !this.g.campaignId.equals("NY_divination_18")) {
            cn.xiaochuankeji.tieba.ui.widget.image.a b;
            this.p.setVisibility(8);
            if (cn.xiaochuankeji.tieba.background.utils.c.a.c().C().newPostThumbSize == 1) {
                b = b.b(this.v.postImageId, true);
            } else {
                b = b.b(this.v.postImageId, true);
            }
            final boolean z3 = z;
            final boolean z4 = z2;
            c anonymousClass2 = new com.facebook.drawee.controller.b<f>(this) {
                final /* synthetic */ h e;

                public void a(String str, f fVar, Animatable animatable) {
                    if (this.e.a(j)) {
                        this.e.g.imageLoadState = 2;
                    }
                    this.e.w.setVisibility(8);
                    this.e.p();
                }

                public void a(String str, Throwable th) {
                    if (!this.e.a(j) || z3) {
                        if (!(b == null || th == null)) {
                            cn.xiaochuankeji.tieba.background.utils.a.d.a().a(th.getMessage(), b.b());
                        }
                        if (!(this.e.o == null || b == null || TextUtils.isEmpty(b.b()) || !b.b().contains("/img/webp/id/"))) {
                            b.a(b.b().replaceAll("/img/webp/id/", "/img/view/id/"));
                            this.e.z = ((e) ((e) com.facebook.drawee.a.a.c.a().a(this)).a(b.b()).a(true)).k();
                            this.e.o.setController(this.e.z);
                        }
                        if (this.e.z != null && (this.e.z instanceof com.facebook.drawee.a.a.d)) {
                            com.izuiyou.a.a.b.e("post " + this.e.v.postImageId + " image fail and retry:" + th.getStackTrace());
                            ((com.facebook.drawee.a.a.d) this.e.z).onClick();
                        }
                    } else if (z4) {
                        this.e.w.setVisibility(0);
                        Drawable drawable = this.e.d.getResources().getDrawable(R.drawable.icon_retry);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        this.e.w.setCompoundDrawables(null, null, drawable, null);
                        this.e.w.setText("封面加载失败,点击重试");
                        this.e.w.setTag(Boolean.TRUE);
                    }
                }
            };
            com.facebook.drawee.components.b a = com.facebook.drawee.components.b.a();
            a.a(true);
            a.a(3);
            this.z = ((e) ((e) com.facebook.drawee.a.a.c.a().a(anonymousClass2)).a(b.b()).a(true)).k();
            this.o.setController(this.z);
            this.w.setVisibility(8);
            this.w.setTag(Boolean.FALSE);
            if (a(j) && this.g.imageLoadState == 1) {
                if (z2) {
                    this.w.setVisibility(0);
                    this.w.setText("封面正在获取中");
                    this.w.setCompoundDrawables(null, null, null, null);
                }
                if (z) {
                    p();
                    this.y = new Runnable(this) {
                        final /* synthetic */ h c;

                        public void run() {
                            if (this.c.a(j)) {
                                this.c.b(false, z2);
                            }
                        }
                    };
                    this.o.postDelayed(this.y, 3000);
                    return;
                }
                return;
            }
            return;
        }
        String a2 = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", this.v.postImageId, "/sz/360");
        a k = ((e) com.facebook.drawee.a.a.c.a().b(ImageRequestBuilder.a(Uri.parse(a2)).a(new com.facebook.imagepipeline.j.a(20)).n())).k();
        this.p.setVisibility(0);
        this.p.setImageURI(a2);
        this.o.setController(k);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (this.o == view || view == this.p) {
            cn.htjyb.b.a a;
            final ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            arrayList2.add(cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPic480, this.v.postImageId));
            if (this.v.isVideo()) {
                ServerVideo imgVideoBy = this.g.getImgVideoBy(this.v.postImageId);
                if (imgVideoBy != null) {
                    a = cn.xiaochuankeji.tieba.background.a.f().a(imgVideoBy.getUrl(), Type.kVideo, this.v.postImageId);
                } else {
                    g.a("链接错误");
                    return;
                }
            } else if (this.v.isMP4()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kMP4, (long) this.v.mp4Id);
            } else if (this.v.isGif()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kGif, this.v.postImageId);
            } else {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPicLarge, this.v.postImageId);
            }
            a.setRotate(this.v.rotate);
            arrayList.add(a);
            final ArrayList arrayList3 = new ArrayList();
            arrayList3.add(this.v);
            cn.xiaochuankeji.tieba.background.utils.a.g.a().a = false;
            PermissionItem permissionItem = new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE");
            permissionItem.deniedMessage = "拒绝该权限后无法正常浏览大图和视频";
            permissionItem.deniedButton = "仍然拒绝";
            permissionItem.needGotoSetting = true;
            permissionItem.rationalButton = "确认";
            permissionItem.rationalMessage = "打开存储权限后才可以正常浏览大图和视频";
            permissionItem.runIgnorePermission = false;
            permissionItem.settingText = "前往设置";
            permissionItem.runIgnorePermission = false;
            cn.xiaochuankeji.aop.permission.a.a(this.d).a(permissionItem, new cn.xiaochuankeji.aop.permission.e(this) {
                final /* synthetic */ h d;

                public void permissionGranted() {
                    cn.xiaochuankeji.tieba.background.utils.a.g.a().a = false;
                    MediaBrowseActivity.a(this.d.d, 0, this.d.g, arrayList2, arrayList, arrayList3, this.d.g.imgVideos, this.d.A);
                    this.d.j.a(this.d.g);
                    try {
                        new JSONObject().put("tid", this.d.g._topic._topicID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    r.a = "post";
                }

                public void permissionDenied() {
                    g.a("没有存储权限，无法正常浏览大图和视频");
                }
            });
        }
    }

    public void a(EntranceType entranceType) {
        this.A = entranceType;
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

    public void d() {
        super.d();
        if (this.v != null) {
            boolean isGif = this.v.isGif();
            boolean isVideo = this.v.isVideo();
            Object obj = null;
            if (!isGif && !isVideo && this.v.width > 0 && ((double) ((float) (this.v.height / this.v.width))) > 2.5d) {
                obj = 1;
            }
            Drawable b = c.a.d.a.a.a().b(R.drawable.ic_gif_flag);
            if (isVideo) {
                b = c.a.d.a.a.a().b(R.drawable.ic_video_flag);
            }
            if (this.r == null) {
                return;
            }
            if (isGif || isVideo) {
                this.r.setImageDrawable(b);
                return;
            }
            this.r.setVisibility(8);
            if (obj == null) {
            }
        }
    }
}
