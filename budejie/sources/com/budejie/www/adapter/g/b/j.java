package com.budejie.www.adapter.g.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.util.ImageUtil;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.androidex.widget.asyncimage.AsyncImageView.ImageListener;
import com.budejie.www.R;
import com.budejie.www.activity.ShowBigPicture;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.CircleProgressBar;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.an;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import java.io.Serializable;
import pl.droidsonroids.gif.GifDrawable;

public class j extends a<ListItemObject> implements OnLongClickListener, ImageListener {
    protected AsyncImageView e;
    public ImageView f;
    public SharedPreferences g;
    private ViewGroup h;
    private CircleProgressBar i;
    private TextView j;
    private FrameLayout k;
    private Button l;
    private Handler m;
    private boolean n;
    private boolean o;

    public j(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
        this.g = context.getSharedPreferences("weiboprefer", 0);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, e(), viewGroup);
        this.e = (AsyncImageView) inflate.findViewById(R.id.main_img);
        this.h = (ViewGroup) inflate.findViewById(R.id.progress_layout);
        this.i = (CircleProgressBar) inflate.findViewById(R.id.circleProgressBar);
        this.j = (TextView) inflate.findViewById(R.id.circleProgressText);
        this.f = (ImageView) inflate.findViewById(R.id.error_img_layout);
        this.l = (Button) inflate.findViewById(R.id.play);
        this.k = (FrameLayout) inflate.findViewById(R.id.checkFullPic_layout);
        return inflate;
    }

    protected int e() {
        return R.layout.post_image;
    }

    public void c() {
        int width = ((ListItemObject) this.c).getWidth();
        int height = ((ListItemObject) this.c).getHeight();
        if (com.budejie.www.adapter.b.a.b(width, height)) {
            this.n = true;
            com.budejie.www.adapter.b.a.a(this.e, com.budejie.www.adapter.b.a.g);
            a(6);
        } else {
            this.n = false;
            if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(((ListItemObject) this.c).getType())) {
                com.budejie.www.adapter.b.a.a(this.e, width, height);
                if (width > height || width == 0 || height == 0) {
                    a(15);
                } else {
                    a(6);
                }
            } else {
                com.budejie.www.adapter.b.a.b(this.e, width, height);
                a(15);
            }
        }
        this.e.setImageListenerSpare(this);
        String str = "";
        this.o = false;
        boolean equals = "1".equals(((ListItemObject) this.c).getIs_gif());
        if (equals && !this.n && an.c(this.a)) {
            File imageFile = ImageUtil.getImageFile(((ListItemObject) this.c).getImgUrl());
            if (imageFile == null || !imageFile.exists()) {
                this.o = true;
                this.e.setPostImage(((ListItemObject) this.c).getGifFistFrame());
            }
        }
        if (!this.o) {
            String imgUrl = ((ListItemObject) this.c).getImgUrl();
            String cnd_img = ((ListItemObject) this.c).getCnd_img();
            if (this.n) {
                if (equals) {
                    str = ((ListItemObject) this.c).getGifFistFrame();
                } else {
                    str = imgUrl;
                }
                LayoutParams layoutParams = this.e.getLayoutParams();
                this.e.setFoldPostImage(str, cnd_img, layoutParams.width, layoutParams.height);
            } else {
                this.e.setPostImage(imgUrl, cnd_img);
            }
        }
        this.e.setOnClickListener(this);
        this.e.setOnLongClickListener(this);
    }

    private void a(int i) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.h.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
        if (15 == i) {
            layoutParams.topMargin = 0;
            layoutParams.addRule(15);
            layoutParams2.topMargin = 0;
            layoutParams2.addRule(15);
            return;
        }
        layoutParams.topMargin = com.budejie.www.adapter.b.a.d;
        layoutParams.addRule(15, 0);
        layoutParams2.topMargin = com.budejie.www.adapter.b.a.e;
        layoutParams2.addRule(15, 0);
    }

    public void onLoadingStarted(String str, View view) {
        this.e.setClickable(false);
        this.e.setVisibility(4);
        this.h.setVisibility(0);
        this.f.setVisibility(8);
        this.k.setVisibility(8);
        this.i.setProgress(0.0f);
        this.j.setText("0%");
    }

    public void onProgressUpdate(String str, View view, int i) {
        if (!d.a().i()) {
            this.i.setProgress((float) i);
            this.j.setText(i + "%");
        }
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
        this.h.setVisibility(8);
        this.f.setVisibility(0);
        this.f.setTag(str);
        this.f.setOnClickListener(this);
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
        if (bitmap == null && gifDrawable == null) {
            onLoadingFailed(str, view, null);
            return;
        }
        if (str.equals(((ListItemObject) this.c).getCnd_img())) {
            ((ListItemObject) this.c).setImgUrl(((ListItemObject) this.c).getCnd_img());
        }
        this.e.setVisibility(0);
        this.e.setClickable(true);
        this.h.setVisibility(8);
        f();
    }

    protected void f() {
        if (this.n) {
            MobclickAgent.onEvent(this.a, "主屏按钮", "点击查看完成图出现次数");
            this.k.setVisibility(0);
        } else {
            this.k.setVisibility(8);
        }
        if (this.o) {
            this.l.setVisibility(0);
            this.l.setOnClickListener(this);
            return;
        }
        this.l.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_img:
                if (this.m == null) {
                    this.m = new j$1(this);
                }
                if (this.m.hasMessages(1)) {
                    this.m.removeMessages(1);
                    this.m.sendEmptyMessage(2);
                    return;
                }
                this.m.sendEmptyMessageDelayed(1, 0);
                return;
            case R.id.play:
                this.l.setVisibility(8);
                this.o = false;
                this.e.setPostImage(((ListItemObject) this.c).getImgUrl(), ((ListItemObject) this.c).getCnd_img());
                return;
            case R.id.error_img_layout:
                if (an.a(this.a)) {
                    String str = (String) view.getTag();
                    if (this.n) {
                        LayoutParams layoutParams = this.e.getLayoutParams();
                        this.e.setFoldPostImage(str, null, layoutParams.width, layoutParams.height);
                        return;
                    }
                    this.e.setPostImage(str);
                    return;
                }
                an.a((Activity) this.a, this.a.getString(R.string.nonet), -1).show();
                return;
            case R.id.checkFullPic_layout:
                g();
                MobclickAgent.onEvent(this.a, "主屏按钮", "点击查看完成图点击次数");
                return;
            default:
                return;
        }
    }

    protected void g() {
        if (((ListItemObject) this.c).isIs_ad()) {
            if (this.b.c != null) {
                this.b.c.e(null, (ListItemObject) this.c);
            }
        } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(((ListItemObject) this.c).getType()) || "31".equals(((ListItemObject) this.c).getType()) || (this.b.c instanceof com.budejie.www.adapter.g.a.a)) {
            Intent intent = new Intent(this.a, ShowBigPicture.class);
            intent.putExtra("imgPath", ((ListItemObject) this.c).getImgUrl());
            intent.putExtra("download_uri", ((ListItemObject) this.c).getDownloadImageUris());
            intent.putExtra("listItemObject", (Serializable) this.c);
            intent.putExtra("isgif", ((ListItemObject) this.c).getIs_gif());
            intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, ((ListItemObject) this.c).getWidth());
            intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, ((ListItemObject) this.c).getHeight());
            this.a.startActivity(intent);
        } else {
            this.b.c.e(this.e, (ListItemObject) this.c);
        }
    }

    public boolean onLongClick(View view) {
        if (k.a(this.a).f != null && k.a(this.a).f.isShown()) {
            return false;
        }
        if (this.b.f.b != null) {
            this.b.f.b.performClick();
        }
        return true;
    }
}
