package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.widget.image.SquaredImageView;
import com.facebook.drawee.a.a.c;
import com.facebook.drawee.a.a.e;
import com.facebook.drawee.d.a;
import com.facebook.imagepipeline.g.f;

public class d extends j {
    private SquaredImageView a;
    private RelativeLayout b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private a f;

    protected d(Context context) {
        super(context);
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_picture_in_multiple_gridview, null);
    }

    protected void a(View view) {
        view.setLongClickable(true);
        this.a = (SquaredImageView) view.findViewById(R.id.picture_view);
        this.b = (RelativeLayout) view.findViewById(R.id.flVBottomLine);
        this.e = (TextView) view.findViewById(R.id.tvLongImgFlag);
        this.c = (ImageView) view.findViewById(R.id.ivVFlag);
        this.d = (TextView) view.findViewById(R.id.tvVDur);
    }

    public void a(final ServerImage serverImage) {
        boolean z;
        final cn.xiaochuankeji.tieba.ui.widget.image.a b = b.b(serverImage.postImageId, false);
        this.f = ((e) ((e) c.a().a(new com.facebook.drawee.controller.b<f>(this) {
            final /* synthetic */ d c;

            public void a(String str, f fVar, Animatable animatable) {
            }

            public void a(String str, Throwable th) {
                if (!(b == null || th == null)) {
                    cn.xiaochuankeji.tieba.background.utils.a.d.a().a(th.getMessage(), b.b());
                }
                if (!(this.c.a == null || b == null || TextUtils.isEmpty(b.b()) || !b.b().contains("/img/webp/id/"))) {
                    b.a(b.b().replaceAll("/img/webp/id/", "/img/view/id/"));
                    this.c.f = ((e) ((e) c.a().a(this)).a(b.b()).a(true)).k();
                    this.c.a.setController(this.c.f);
                }
                if (this.c.f != null && (this.c.f instanceof com.facebook.drawee.a.a.d)) {
                    com.izuiyou.a.a.b.e("post " + serverImage.postImageId + " image fail and retry");
                    ((com.facebook.drawee.a.a.d) this.c.f).onClick();
                }
            }
        })).a(b.b()).a(true)).k();
        this.a.setController(this.f);
        boolean isGif = serverImage.isGif();
        boolean isVideo = serverImage.isVideo();
        if (isGif || isVideo || serverImage.width <= 0 || ((double) ((float) (serverImage.height / serverImage.width))) <= 2.5d) {
            z = false;
        } else {
            z = true;
        }
        if (isVideo) {
            this.c.setVisibility(0);
            this.c.setImageDrawable(c.a.d.a.a.a().b(R.drawable.ic_video2_flag));
            this.e.setVisibility(8);
            this.b.setVisibility(0);
            long j = serverImage.videoDuration;
            if (0 != j) {
                this.d.setVisibility(0);
                this.d.setText(cn.xiaochuankeji.tieba.ui.utils.d.a(j * 1000));
            } else {
                this.d.setVisibility(8);
            }
            int i = serverImage.videoPlayCount;
        } else if (isGif) {
            this.c.setVisibility(0);
            this.c.setImageDrawable(c.a.d.a.a.a().b(R.drawable.ic_gif2_flag));
            this.b.setVisibility(8);
            this.e.setVisibility(8);
        } else if (z) {
            this.c.setVisibility(8);
            this.b.setVisibility(8);
            this.e.setVisibility(0);
        } else {
            this.c.setVisibility(8);
            this.e.setVisibility(8);
            this.b.setVisibility(8);
        }
    }

    public void a(@ColorInt int i) {
        a controller = this.a.getController();
        if (controller != null) {
            com.facebook.drawee.generic.b a = com.facebook.drawee.generic.b.a(e_().getResources());
            a.a(new ColorDrawable(i));
            controller.a(a.t());
            this.a.setController(controller);
        }
    }

    public void b(@ColorInt int i) {
        if (this.a != null) {
            this.a.setColorFilter(i);
        }
    }
}
