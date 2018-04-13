package com.budejie.www.adapter.g.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.androidex.widget.asyncimage.AsyncImageView.ImageListener;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.ShowBigPicture;
import com.budejie.www.activity.video.FullScreenVideoActivity;
import com.budejie.www.activity.view.CircleProgressBar;
import com.budejie.www.activity.view.MediaPlayView;
import com.budejie.www.activity.view.MediaPlayView$a;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.g.c;
import com.budejie.www.util.ac;
import com.budejie.www.util.an;
import com.budejie.www.widget.parsetagview.ParseTagTextView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import pl.droidsonroids.gif.GifDrawable;

public class n extends a<ListItemObject> implements ImageListener {
    private View e;
    private TextView f;
    private AsyncImageView g;
    private AsyncImageView h;
    private ImageView i;
    private CircleProgressBar j;
    private MediaPlayView k;
    private ImageView l;
    private LinearLayout m;
    private RelativeLayout n;

    public n(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.post_repost, viewGroup);
        this.m = (LinearLayout) inflate.findViewById(R.id.ll_repost);
        this.n = (RelativeLayout) inflate.findViewById(R.id.rl_delete);
        this.g = (AsyncImageView) inflate.findViewById(R.id.contentImage);
        this.f = (TextView) inflate.findViewById(R.id.repost_content);
        this.e = inflate.findViewById(R.id.repost_rich_layout);
        this.h = (AsyncImageView) inflate.findViewById(R.id.main_img);
        this.i = (ImageView) inflate.findViewById(R.id.error_img_layout);
        this.j = (CircleProgressBar) inflate.findViewById(R.id.repost_gif_progress);
        this.l = (ImageView) inflate.findViewById(R.id.repost_video_play_btn);
        this.k = (MediaPlayView) inflate.findViewById(R.id.repost_mMPview);
        return inflate;
    }

    public void c() {
        final ListItemObject original_topic = ((ListItemObject) this.c).getOriginal_topic();
        if (original_topic == null) {
            this.m.setVisibility(8);
            this.n.setVisibility(0);
            ParseTagTextView.a(this.a, this.f, "");
        } else {
            this.n.setVisibility(8);
            this.m.setVisibility(0);
            this.e.setVisibility(8);
            this.h.setVisibility(0);
            this.i.setVisibility(8);
            this.l.setVisibility(8);
            this.k.setVisibility(8);
            this.j.setVisibility(8);
            this.m.setOnClickListener(this);
            this.l.setOnClickListener(this);
            this.f.setOnClickListener(this);
            this.h.setImageListenerSpare(this);
            String content = original_topic.getContent();
            int type = RowType.REPOST_ROW.getType();
            try {
                type = Integer.parseInt(original_topic.getType());
            } catch (NumberFormatException e) {
            }
            String imgUrl;
            switch (RowType.valueOf(type)) {
                case IMAGE_ROW:
                    a(this.h, true);
                    imgUrl = original_topic.getImgUrl();
                    if ("1".equals(original_topic.getIs_gif())) {
                        imgUrl = original_topic.getGifFistFrame();
                    }
                    this.h.setPostImage(imgUrl);
                    break;
                case SOUND_ROW:
                    a(this.h, false);
                    this.k.setVisibility(0);
                    this.h.setPostImage(original_topic.getImgUrl());
                    this.k.setOnMediaPlayerStateListener(new c(this.k));
                    this.k.setPlayPath(original_topic.getVoiceUri());
                    this.k.setServerTime(Integer.parseInt(original_topic.getVoicetime()) * 1000);
                    this.k.setDataId(original_topic.getWid());
                    this.k.setPlayingListener(new MediaPlayView$a(this) {
                        final /* synthetic */ n b;

                        public void a() {
                            ((BudejieApplication) this.b.a.getApplicationContext()).a(original_topic);
                        }

                        public void b() {
                            ((BudejieApplication) this.b.a.getApplicationContext()).a(Status.end);
                        }
                    });
                    a(this.k, original_topic.getVoiceUri());
                    break;
                case VIDEO_ROW:
                    a(this.h, false);
                    this.l.setVisibility(0);
                    this.h.setPostImage(original_topic.getImgUrl());
                    break;
                case RICH_ROW:
                    this.e.setVisibility(0);
                    this.g.setRichPostLinkImage(original_topic.getRichObject().getImgUrl());
                    imgUrl = ((ListItemObject) this.c).getOriginal_topic().getRichObject().getTitle();
                    this.h.setVisibility(8);
                    content = imgUrl;
                    break;
                default:
                    this.h.setVisibility(8);
                    break;
            }
            ParseTagTextView.a(this.a, this.f, "@" + ((ListItemObject) this.c).getOriginal_topic().getName() + ":" + content + " ");
        }
        this.h.setClickable(false);
        this.h.setOnClickListener(this);
    }

    public void a(AsyncImageView asyncImageView, boolean z) {
        if (z) {
            asyncImageView.setLayoutParams(new LayoutParams(an.a(this.a, 100), an.a(this.a, 100)));
        } else {
            asyncImageView.setLayoutParams(new LayoutParams(an.a(this.a, 200), an.a(this.a, 200)));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_repost:
            case R.id.repost_content:
                this.b.c.e(view, ((ListItemObject) this.c).getOriginal_topic());
                return;
            case R.id.main_img:
                a();
                return;
            case R.id.repost_video_play_btn:
                a(((ListItemObject) this.c).getOriginal_topic());
                return;
            default:
                return;
        }
    }

    private void a() {
        ListItemObject original_topic = ((ListItemObject) this.c).getOriginal_topic();
        if (RowType.VIDEO_ROW == RowType.valueOf(Integer.parseInt(original_topic.getType()))) {
            a(original_topic);
        } else {
            b();
        }
    }

    private void b() {
        Intent intent = new Intent(this.a, ShowBigPicture.class);
        intent.putExtra("imgPath", ((ListItemObject) this.c).getOriginal_topic().getImgUrl());
        intent.putExtra("download_uri", ((ListItemObject) this.c).getDownloadImageUris());
        intent.putExtra("listItemObject", ((ListItemObject) this.c).getOriginal_topic());
        intent.putExtra("isgif", ((ListItemObject) this.c).getOriginal_topic().getIs_gif());
        intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, ((ListItemObject) this.c).getOriginal_topic().getWidth());
        intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, ((ListItemObject) this.c).getOriginal_topic().getHeight());
        this.a.startActivity(intent);
    }

    private void a(ListItemObject listItemObject) {
        Intent intent = new Intent(this.a, FullScreenVideoActivity.class);
        intent.putExtra(FullScreenVideoActivity.a, listItemObject);
        this.a.startActivity(intent);
    }

    public void onLoadingStarted(String str, View view) {
        this.j.setVisibility(0);
        this.i.setVisibility(8);
        this.j.setProgress(0.0f);
    }

    public void onProgressUpdate(String str, View view, int i) {
        this.j.setProgress((float) i);
    }

    public void onLoadingFailed(final String str, View view, FailReason failReason) {
        this.j.setVisibility(8);
        this.i.setVisibility(0);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ n b;

            public void onClick(View view) {
                if (an.a(this.b.a)) {
                    this.b.h.setPostImage(str, null);
                } else {
                    an.a((Activity) this.b.a, this.b.a.getString(R.string.nonet), -1).show();
                }
            }
        });
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
        if (bitmap == null && gifDrawable == null) {
            onLoadingFailed(str, view, null);
            return;
        }
        this.h.setClickable(true);
        this.j.setVisibility(8);
    }

    public void a(MediaPlayView mediaPlayView, String str) {
        ac a = ac.a(this.a);
        Object m = a.m();
        if (a.c() || a.a()) {
            if (TextUtils.isEmpty(m) || !m.equals(str)) {
                mediaPlayView.e();
            } else {
                mediaPlayView.a();
            }
        } else if (TextUtils.isEmpty(m) || !m.equals(str) || a.b()) {
            mediaPlayView.e();
        } else {
            mediaPlayView.b();
        }
    }
}
