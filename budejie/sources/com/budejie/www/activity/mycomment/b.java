package com.budejie.www.activity.mycomment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.androidex.util.ImageUtil;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.androidex.widget.asyncimage.AsyncImageView.ImageListener;
import com.budejie.www.R;
import com.budejie.www.activity.ShowBigPicture;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.adapter.RowType;
import com.budejie.www.http.f;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.widget.CmtPopupWindow;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import pl.droidsonroids.gif.GifDrawable;

public class b extends g {
    f a;
    private String i;
    private String j;
    private boolean k;
    private boolean l;
    private boolean m;

    private class a implements ImageListener {
        final /* synthetic */ b a;
        private c b;

        private a(b bVar, c cVar) {
            this.a = bVar;
            this.b = cVar;
        }

        public void onLoadingStarted(String str, View view) {
            aa.a("MyCommentImageRow", ":::::::onImageRemotePre：" + str);
            this.b.a.setVisibility(8);
            this.b.b.setVisibility(0);
            this.b.f.setVisibility(8);
            this.b.c.setProgress(0);
        }

        public void onProgressUpdate(String str, View view, int i) {
            aa.a("MyCommentImageRow", ":::::::onImageRemoteProgressUpdate：" + str + "::" + i);
            this.b.c.setProgress(i);
        }

        public void onLoadingFailed(final String str, View view, FailReason failReason) {
            aa.a("MyCommentImageRow", "加载图片失败：" + str);
            this.b.b.setVisibility(8);
            this.b.a.setVisibility(4);
            this.b.f.setVisibility(0);
            this.b.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (!an.a(this.b.a.d)) {
                        an.a(this.b.a.d, this.b.a.d.getString(R.string.nonet), -1).show();
                    } else if (this.b.a.l) {
                        LayoutParams layoutParams = this.b.b.a.getLayoutParams();
                        this.b.b.a.setFoldPostImage(str, null, layoutParams.width, layoutParams.height);
                    } else {
                        this.b.b.a.setPostImage(str, null);
                    }
                }
            });
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
            if (bitmap == null && gifDrawable == null) {
                onLoadingFailed(str, view, null);
                return;
            }
            this.b.a.setVisibility(0);
            this.b.a.setClickable(true);
            this.b.b.setVisibility(8);
            this.a.a(this.b);
        }
    }

    public b(Activity activity, com.budejie.www.activity.mycomment.e.a aVar, d dVar, int i, CmtPopupWindow cmtPopupWindow) {
        super(activity, aVar, dVar, i, cmtPopupWindow);
        this.a = new f(activity);
    }

    public View b() {
        c cVar = new c();
        ViewGroup viewGroup = (ViewGroup) this.e.inflate(R.layout.new_mycomment_list_item_image, null);
        cVar.j = (LinearLayout) viewGroup.findViewById(R.id.item_layout);
        cVar.k = (AsyncImageView) viewGroup.findViewById(R.id.writerProfile);
        cVar.l = (TextView) viewGroup.findViewById(R.id.writerName);
        cVar.m = (TextView) viewGroup.findViewById(R.id.commentHint);
        cVar.o = (LinearLayout) viewGroup.findViewById(R.id.name_time_layout);
        cVar.q = (AudioLayout) viewGroup.findViewById(R.id.mycomment_content_voice);
        cVar.p = (TextView) viewGroup.findViewById(R.id.mycomment_content_textview);
        cVar.r = (LinearLayout) viewGroup.findViewById(R.id.mycomment_content_layout);
        cVar.s = (LinearLayout) viewGroup.findViewById(R.id.mycomment_reply_content_layout);
        cVar.u = (TextView) viewGroup.findViewById(R.id.mycomment_reply_content_textview);
        cVar.v = (AudioLayout) viewGroup.findViewById(R.id.mycomment_reply_content_voice);
        cVar.w = (TextView) viewGroup.findViewById(R.id.mycomment_username_textview);
        cVar.x = (TextView) viewGroup.findViewById(R.id.mycomment_reply_username_textview);
        cVar.t = (TextView) viewGroup.findViewById(R.id.content);
        cVar.e = (FrameLayout) viewGroup.findViewById(R.id.image_layout);
        cVar.a = (AsyncImageView) viewGroup.findViewById(R.id.main_img);
        cVar.b = (ViewGroup) viewGroup.findViewById(R.id.progress_layout);
        cVar.c = (ProgressBar) viewGroup.findViewById(R.id.gif_progress);
        cVar.n = viewGroup.findViewById(R.id.writerProfile_ready);
        cVar.d = viewGroup.findViewById(R.id.imageready);
        cVar.f = (LinearLayout) viewGroup.findViewById(R.id.error_img_layout);
        cVar.g = (Button) viewGroup.findViewById(R.id.play);
        cVar.h = (FrameLayout) viewGroup.findViewById(R.id.checkFullPic_layout);
        cVar.i = (ImageView) viewGroup.findViewById(R.id.checkFullPic_back);
        viewGroup.setTag(cVar);
        return viewGroup;
    }

    public int c() {
        return RowType.COMMENT_IMAGE_ROW.ordinal();
    }

    public void a(com.budejie.www.adapter.b bVar) {
        super.a(bVar);
        c cVar = (c) bVar;
        int width = this.c.getWidth();
        int height = this.c.getHeight();
        this.k = "1".equals(this.c.getIs_gif());
        if (com.budejie.www.adapter.b.a.b(width, height)) {
            this.l = true;
            height = PostsActivity.k;
            if (cVar.h != null) {
                this.a.a(cVar.i, com.budejie.www.adapter.b.a.a);
            }
            com.budejie.www.adapter.b.a.a(cVar.a, height);
        } else {
            this.l = false;
            this.a.a(width, height, cVar.a, false, com.budejie.www.adapter.b.a.a, cVar.d);
            if (cVar.h != null) {
                cVar.h.setVisibility(4);
            }
        }
        if (bVar instanceof j) {
            this.a.b(width, height, cVar.a, false, com.budejie.www.adapter.b.a.a, cVar.d);
        }
        cVar.f.setVisibility(8);
        cVar.a.setImageListenerSpare(new a(cVar));
        String str = "";
        this.m = false;
        if (!(!this.k || this.l || "1".equals(an.b(this.d)))) {
            File imageFile = ImageUtil.getImageFile(this.c.getImgUrl());
            if (imageFile == null || !imageFile.exists()) {
                this.m = true;
                str = this.c.getGifFistFrame();
                this.i = this.c.getImgUrl();
                this.j = this.c.getCnd_img();
                cVar.a.setPostImage(str);
            }
        }
        if (!this.m) {
            str = this.c.getImgUrl();
            String cnd_img = this.c.getCnd_img();
            if (this.l) {
                if (this.k) {
                    str = this.c.getGifFistFrame();
                }
                LayoutParams layoutParams = cVar.a.getLayoutParams();
                cVar.a.setFoldPostImage(str, cnd_img, layoutParams.width, layoutParams.height);
            } else {
                cVar.a.setPostImage(str, cnd_img);
            }
        }
        cVar.a.setClickable(false);
        cVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent(this.a.d, ShowBigPicture.class);
                intent.putExtra("imgPath", this.a.c.getImgUrl());
                intent.putExtra("listItemObject", this.a.c);
                intent.putExtra("isgif", this.a.c.getIs_gif());
                intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, this.a.c.getWidth());
                intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, this.a.c.getHeight());
                intent.putExtra("download_uri", this.a.c.getDownloadImageUris());
                this.a.d.startActivity(intent);
            }
        });
    }

    protected void a(final c cVar) {
        if (cVar.h != null) {
            if (this.l) {
                MobclickAgent.onEvent(this.d, "主屏按钮", "点击查看完成图出现次数");
                cVar.h.setVisibility(0);
            } else {
                cVar.h.setVisibility(4);
            }
        }
        if (this.m) {
            cVar.g.setVisibility(0);
            cVar.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    cVar.g.setVisibility(8);
                    this.b.m = false;
                    cVar.a.setPostImage(this.b.i, this.b.j);
                }
            });
            return;
        }
        cVar.g.setVisibility(8);
    }
}
