package com.budejie.www.activity.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.budejie.www.R;
import com.budejie.www.activity.video.f.b;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.e;
import com.budejie.www.http.c;
import com.budejie.www.util.an;
import com.budejie.www.util.q;
import java.io.IOException;
import java.io.Serializable;

public class CommentItemVideoActivity extends Activity implements b {
    OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ CommentItemVideoActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.detail_title_back:
                    this.a.b.finish();
                    return;
                case R.id.detail_title_like:
                    this.a.a(false);
                    return;
                case R.id.detail_title_hate:
                    this.a.f();
                    return;
                case R.id.detail_title_download:
                    if (this.a.l != null) {
                        String str;
                        ListItemObject listItemObject = new ListItemObject();
                        if (TextUtils.isEmpty(this.a.l.getContent())) {
                            str = "缓存视频";
                        } else {
                            str = this.a.l.getContent();
                        }
                        listItemObject.setContent(str);
                        listItemObject.setDownloadVideoUris(this.a.l.getVideoDownLoadUrl());
                        listItemObject.setVideouri(this.a.l.getVideoPlayUrl());
                        listItemObject.setImgUrl(this.a.l.getVideoThumbnail());
                        an.a(this.a.b, listItemObject);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private CommentItemVideoActivity b;
    private c c;
    private e d;
    private f e;
    private RelativeLayout f;
    private VideoView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private CommentItem l;
    private String m;
    private int n;
    private int o;

    private class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ CommentItemVideoActivity a;

        private a(CommentItemVideoActivity commentItemVideoActivity) {
            this.a = commentItemVideoActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void a(Void... voidArr) {
            this.a.c();
            return null;
        }

        protected void a(Void voidR) {
            LayoutParams layoutParams = (LayoutParams) this.a.g.getLayoutParams();
            layoutParams.addRule(13, -1);
            this.a.g.setLayoutParams(layoutParams);
        }
    }

    public static void a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Intent intent = new Intent(context, CommentItemVideoActivity.class);
            Serializable commentItem = new CommentItem();
            commentItem.setId(str);
            commentItem.setVideoPlayUrl(str2);
            intent.putExtra("comment_item_key", commentItem);
            context.startActivity(intent);
        }
    }

    public static void a(Activity activity, CommentItem commentItem) {
        a((Context) activity, commentItem);
    }

    public static void a(Context context, CommentItem commentItem) {
        if (commentItem != null && !TextUtils.isEmpty(commentItem.getVideoPlayUrl())) {
            Intent intent = new Intent(context, CommentItemVideoActivity.class);
            intent.putExtra("comment_item_key", commentItem);
            context.startActivity(intent);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.comment_item_video_layout);
        overridePendingTransition(R.anim.show_picture_enter, R.anim.show_picture_excessive);
        this.b = this;
        this.c = new c(this, null);
        this.d = new e(this);
        e();
        a();
    }

    private void e() {
        this.h = (ImageView) findViewById(R.id.detail_title_back);
        this.i = (ImageView) findViewById(R.id.detail_title_like);
        this.j = (ImageView) findViewById(R.id.detail_title_hate);
        this.k = (ImageView) findViewById(R.id.detail_title_download);
        this.h.setOnClickListener(this.a);
        this.i.setOnClickListener(this.a);
        this.j.setOnClickListener(this.a);
        this.k.setOnClickListener(this.a);
        this.g = (VideoView) findViewById(R.id.video_view);
        this.f = (RelativeLayout) findViewById(R.id.video_layout);
    }

    private void a(boolean z) {
        if (this.l != null && !this.l.isAlreadyDingCai()) {
            this.i.setSelected(true);
            this.l.setDingOrCai("like");
            this.c.a(this.l.getId(), "like");
            this.d.a(this.l.getId(), "like");
            if (z) {
                q.a(this.e.b);
            }
        }
    }

    private void f() {
        if (this.l != null && !this.l.isAlreadyDingCai()) {
            this.j.setSelected(true);
            this.l.setDingOrCai("hate");
            this.c.a(this.l.getId(), "hate");
            this.d.a(this.l.getId(), "hate");
        }
    }

    public void a() {
        if (getIntent() != null) {
            this.l = (CommentItem) getIntent().getSerializableExtra("comment_item_key");
            if (this.l != null) {
                this.c.a(this.d, this.l);
                this.m = this.l.getVideoPlayUrl();
                this.i.setSelected("like".equals(this.l.getDingOrCai()));
                this.j.setSelected("hate".equals(this.l.getDingOrCai()));
                b();
            }
        }
    }

    public void b() {
        ListItemObject g = g();
        if (g != null) {
            this.e = new f(this, g, 5);
            this.e.setDoubleClickCallback(this);
            this.g.setMircroMediaController(this.e);
            this.g.setVideoPath(this.m);
            new a().execute(new Void[0]);
        }
    }

    private ListItemObject g() {
        if (this.l == null) {
            return null;
        }
        ListItemObject listItemObject = new ListItemObject();
        listItemObject.setVideouri(this.m);
        listItemObject.setDownloadVideoUris(this.m);
        listItemObject.setWidth(this.l.getImageWidth());
        listItemObject.setHeight(this.l.getImageHeight());
        listItemObject.setImgPath(this.l.getVideoThumbnail());
        listItemObject.setType("41");
        return listItemObject;
    }

    protected void onPause() {
        super.onPause();
        if (this.g != null) {
            this.g.b();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            this.g.g();
        }
    }

    public void c() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this.m);
            mediaPlayer.prepare();
            this.n = mediaPlayer.getVideoWidth();
            this.o = mediaPlayer.getVideoHeight();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public void b_() {
        a(true);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.show_picture_excessive, R.anim.show_picture_exit);
    }
}
