package com.budejie.www.adapter.d;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.androidex.util.ImageUtil;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.RichPostDetail;
import com.budejie.www.activity.ShowBigPicture;
import com.budejie.www.activity.video.f.b;
import com.budejie.www.activity.video.k;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.e.e;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.nostra13.universalimageloader.core.d.c;
import com.tencent.connect.common.Constants;
import java.io.File;

public class d extends a implements OnClickListener, b {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;
    protected d$a f;
    protected boolean g;
    private Handler h = new d$1(this);
    private c i = new d$2(this);

    public /* synthetic */ Object d() {
        return a();
    }

    public d(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i, boolean z) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.c = LayoutInflater.from(activity);
        this.e = i;
        this.g = z;
    }

    public View b() {
        d$a d_a = new d$a(this);
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.post_detail_head_layout, null);
        this.a.mcollapsibleState = 0;
        d$a.a(d_a, (ImageView) viewGroup.findViewById(R.id.post_detail_video_play));
        d$a.a(d_a, (AsyncImageView) viewGroup.findViewById(R.id.post_detail_head_img));
        d$a.a(d_a, (SubsamplingScaleImageView) viewGroup.findViewById(R.id.post_detail_subsampling));
        d$a.a(d_a, viewGroup.findViewById(R.id.post_detail_subsampling_placeholder_view));
        d$a.a(d_a).setPanEnabled(false);
        d$a.a(d_a).setZoomEnabled(false);
        d$a.a(d_a).setMaxScale(20.0f);
        d$a.a(d_a).setMinimumScaleType(2);
        d$a.a(d_a, (RelativeLayout) viewGroup.findViewById(R.id.post_detail_head_video_container_layout));
        d$a.a(d_a, (LinearLayout) viewGroup.findViewById(R.id.post_detail_rich_mark));
        viewGroup.setTag(d_a);
        return viewGroup;
    }

    public int c() {
        return RowType.POST_DETAIL_HEAD_ROW.ordinal();
    }

    public void a(com.budejie.www.adapter.b bVar) {
        this.f = (d$a) bVar;
        d$a.b(this.f).setVisibility(8);
        if (this.a != null) {
            if (!Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(this.a.getType()) || this.a.getWidth() <= 0 || this.a.getHeight() / this.a.getWidth() <= 3 || "1".equals(this.a.getIs_gif())) {
                d$a.a(this.f).setVisibility(8);
                d$a.c(this.f).setVisibility(8);
                d$a.d(this.f).setVisibility(0);
                if (!TextUtils.isEmpty(this.a.getImgUrl()) || !TextUtils.isEmpty(this.a.getCnd_img())) {
                    com.budejie.www.adapter.b.a.d(d$a.d(this.f), this.a.getWidth(), this.a.getHeight());
                    d$a.d(this.f).setPostImage(this.a.getImgUrl(), this.a.getCnd_img());
                } else if (this.a.getRichObject() == null || TextUtils.isEmpty(this.a.getRichObject().getImgUrl())) {
                    d$a.d(this.f).setVisibility(8);
                } else {
                    com.budejie.www.adapter.b.a.d(d$a.d(this.f), 594, 318);
                    d$a.d(this.f).setPostImage(this.a.getRichObject().getImgUrl());
                    d$a.b(this.f).setVisibility(0);
                }
            } else {
                d$a.c(this.f).setVisibility(0);
                d$a.a(this.f).setVisibility(0);
                d$a.d(this.f).setVisibility(8);
                String a = com.lt.a.a(this.b).a(this.a.getImgUrl());
                File imageFile = ImageUtil.getImageFile(a);
                if (imageFile == null || !imageFile.exists()) {
                    aa.b("PostDedailHeadRow", "没有缓存 imageUri=" + a);
                    com.nostra13.universalimageloader.core.d.a().a(a, new com.nostra13.universalimageloader.core.assist.c(this.a.getWidth(), this.a.getHeight()), e.j(), this.i);
                } else {
                    aa.b("PostDedailHeadRow", "缓存 imageUri=" + a);
                    d$a.a(this.f).setImage(com.davemorrissey.labs.subscaleview.a.b(imageFile.getPath()));
                }
            }
            if (TextUtils.isEmpty(this.a.getVideouri()) && TextUtils.isEmpty(this.a.getVideouriBackup())) {
                d$a.e(this.f).setVisibility(8);
                d$a.d(this.f).setOnClickListener(this);
                d$a.a(this.f).setOnClickListener(this);
                return;
            }
            d$a.e(this.f).setVisibility(0);
            d$a.f(this.f).setTag(this.f);
            d$a.f(this.f).setOnClickListener(this);
            com.budejie.www.adapter.b.a.f(d$a.d(this.f), this.a.getWidth(), this.a.getHeight());
            com.budejie.www.adapter.b.a.b(d$a.f(this.f), this.a.getWidth(), this.a.getHeight());
            k.a d = k.a(this.b).d();
            if (k.a(this.b).c && d != null && d.c == d$a.f(this.f) && !d.b.equals(this.a.getVideouri())) {
                k.a(this.b).h();
            }
            d$a d_a = this.f;
            k a2 = k.a(this.b);
            a2.getClass();
            d$a.a(d_a, new k.a(a2, this.e, d$a.f(this.f)));
            if (this.g) {
                this.h.sendEmptyMessageDelayed(0, 2000);
            }
        }
    }

    public ListItemObject a() {
        return this.a;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_detail_head_video_container_layout:
                try {
                    d$a d_a = (d$a) view.getTag();
                    an.E(this.b);
                    k.a(this.b).a(this.a, d$a.g(d_a), this, 0);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.post_detail_head_img:
            case R.id.post_detail_subsampling:
                Intent intent;
                if (this.a.getRichObject() != null) {
                    intent = new Intent(this.b, RichPostDetail.class);
                    intent.putExtra("listitem_object", this.a);
                    this.b.startActivity(intent);
                    return;
                }
                intent = new Intent(this.b, ShowBigPicture.class);
                intent.putExtra("imgPath", this.a.getImgUrl());
                intent.putExtra("download_uri", this.a.getDownloadImageUris());
                intent.putExtra("listItemObject", this.a);
                intent.putExtra("isgif", this.a.getIs_gif());
                intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, this.a.getWidth());
                intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, this.a.getHeight());
                this.b.startActivity(intent);
                return;
            default:
                return;
        }
    }

    public void b_() {
        aa.b("PostDetailHeadRow", "onDoubleClick");
        if (this.d != null) {
            View view = new View(this.b);
            view.setTag(R.id.DOUBLE_CLICK_KEY, Boolean.valueOf(true));
            this.d.a(view, this.a);
        }
    }

    public void e() {
        this.h.sendEmptyMessageDelayed(0, 400);
    }

    public void f() {
        if (this.f != null && d$a.a(this.f) != null) {
            d$a.a(this.f).a();
            d$a.a(this.f, null);
        }
    }
}
