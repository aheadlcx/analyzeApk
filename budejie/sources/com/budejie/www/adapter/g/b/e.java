package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.CommentShowBigPicture;
import com.budejie.www.activity.mycomment.MyCommentInfo;
import com.budejie.www.activity.video.CommentItemVideoActivity;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.ac;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class e extends com.budejie.www.adapter.g.a<ListItemObject> {
    private Context e;
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private TextView i;
    private TextView j;
    private AudioLayout k;
    private TextView l;
    private AudioLayout m;
    private TextView n;
    private LinearLayout o;
    private TextView p;
    private RelativeLayout q;
    private ImageView r;
    private AsyncImageView s;
    private ImageView t;

    class a implements OnClickListener {
        MyCommentInfo a;
        final /* synthetic */ e b;

        public a(e eVar, MyCommentInfo myCommentInfo) {
            this.b = eVar;
            this.a = myCommentInfo;
        }

        public void onClick(View view) {
            if (this.a != null) {
                String type = this.a.getType();
                Intent intent;
                if (type.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                    intent = new Intent(this.b.e, CommentShowBigPicture.class);
                    intent.putExtra("imgPath", this.a.getImageShowUrl());
                    intent.putExtra("isgif", "0");
                    intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, this.a.getImageWidth());
                    intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, this.a.getImageHeight());
                    intent.putExtra("download_uri", this.a.getImageDownloadUrls());
                    this.b.e.startActivity(intent);
                } else if (type.equals("video")) {
                    CommentItemVideoActivity.a(this.b.e, this.b.a(this.a));
                } else if (type.equals("gif")) {
                    intent = new Intent(this.b.e, CommentShowBigPicture.class);
                    intent.putExtra("isgif", "1");
                    intent.putExtra("GifShowUrls", this.a.getGifShowUrl());
                    intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, this.a.getGifWidth());
                    intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, this.a.getGifHeight());
                    intent.putExtra("download_uri", this.a.getGifDownLoadUrls());
                    this.b.e.startActivity(intent);
                }
            }
        }
    }

    public e(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
        this.e = context;
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.e, a(), viewGroup);
        this.f = (LinearLayout) inflate.findViewById(R.id.comment_content_all_layout);
        this.g = (LinearLayout) inflate.findViewById(R.id.hot_cmt_content_layout);
        this.k = (AudioLayout) inflate.findViewById(R.id.hot_cmt_content_voice);
        this.j = (TextView) inflate.findViewById(R.id.hot_cmt_content_textview);
        this.h = (LinearLayout) inflate.findViewById(R.id.hot_cmt_reply_content_layout);
        this.l = (TextView) inflate.findViewById(R.id.hot_cmt_reply_content_textview);
        this.m = (AudioLayout) inflate.findViewById(R.id.hot_cmt_reply_content_voice);
        this.i = (TextView) inflate.findViewById(R.id.hot_cmt_username_textview);
        this.n = (TextView) inflate.findViewById(R.id.hot_cmt_reply_username_textview);
        this.o = (LinearLayout) inflate.findViewById(R.id.bottom_comment_layout);
        this.p = (TextView) inflate.findViewById(R.id.ContentTextView);
        this.q = (RelativeLayout) inflate.findViewById(R.id.ReplyImageLayout);
        this.r = (ImageView) inflate.findViewById(R.id.PlayVideoImageView);
        this.s = (AsyncImageView) inflate.findViewById(R.id.ItemImageView);
        this.t = (ImageView) inflate.findViewById(R.id.GifIconImageView);
        return inflate;
    }

    protected int a() {
        return R.layout.post_comment_item;
    }

    public void c() {
        if (((ListItemObject) this.c).getType() == null || !((ListItemObject) this.c).getType().equals("51")) {
            if (!TextUtils.isEmpty(((ListItemObject) this.c).getContent())) {
                this.p.setText(((ListItemObject) this.c).getContent());
            }
        } else if (!(((ListItemObject) this.c).getRichObject() == null || ((ListItemObject) this.c).getRichObject().getTitle() == null)) {
            this.p.setText(((ListItemObject) this.c).getRichObject().getTitle());
        }
        b();
    }

    private void b() {
        this.o.setOnClickListener(this);
        String str = "";
        if (((ListItemObject) this.c).getHotcmt() == null || ((ListItemObject) this.c).getHotcmt().user == null) {
            Object obj = str;
        } else {
            CharSequence charSequence = ((ListItemObject) this.c).getHotcmt().user.username;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            this.g.setVisibility(8);
            this.f.setOnClickListener(this);
            this.g.setOnClickListener(this);
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            this.t.setVisibility(8);
            this.j.setVisibility(0);
            MyCommentInfo hotcmt = ((ListItemObject) this.c).getHotcmt();
            String type = hotcmt.getType();
            if (type.equals("text")) {
                this.g.setVisibility(0);
                this.j.setText(((ListItemObject) this.c).getHotcmt().content);
            } else if (type.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                if (!((ListItemObject) this.c).getHotcmt().content.equals("")) {
                    this.g.setVisibility(0);
                    this.j.setVisibility(0);
                    this.j.setText(((ListItemObject) this.c).getHotcmt().content);
                }
                this.q.setVisibility(0);
                this.q.setOnClickListener(new a(this, hotcmt));
                com.budejie.www.adapter.b.a.c(this.s, hotcmt.getImageWidth(), hotcmt.getImageHeight());
                this.s.setAsyncCacheImage(hotcmt.getImageThumbUrl(), R.drawable.likelist_defaut_bg);
            } else if (type.equals("audio")) {
                this.g.setVisibility(0);
                this.j.setVisibility(8);
                this.k.setVisibility(0);
                this.k.c();
                this.k.setPlayPath(hotcmt.getAudioUrl());
                this.k.setAudioTime("" + hotcmt.getAudioDuration());
                if (!ac.a(this.e).c()) {
                    this.k.c();
                } else if (ac.a(this.e).m().equals(((ListItemObject) this.c).getHotcmt().voiceuri)) {
                    this.k.d();
                } else {
                    this.k.c();
                }
            } else if (type.equals("video")) {
                if (!((ListItemObject) this.c).getHotcmt().content.equals("")) {
                    this.g.setVisibility(0);
                    this.j.setText(((ListItemObject) this.c).getHotcmt().content);
                }
                this.q.setVisibility(0);
                this.q.setOnClickListener(new a(this, hotcmt));
                this.r.setVisibility(0);
                com.budejie.www.adapter.b.a.c(this.s, 0, 0);
                this.s.setAsyncCacheImage(hotcmt.getVideoThumbnail(), R.drawable.likelist_defaut_bg);
            } else if (type.equals("gif")) {
                if (!((ListItemObject) this.c).getHotcmt().content.equals("")) {
                    this.g.setVisibility(0);
                    this.j.setText(((ListItemObject) this.c).getHotcmt().content);
                }
                this.t.setVisibility(0);
                this.q.setVisibility(0);
                this.q.setOnClickListener(new a(this, hotcmt));
                com.budejie.www.adapter.b.a.c(this.s, hotcmt.getGifWidth(), hotcmt.getGifHeight());
                this.s.setAsyncCacheImage(hotcmt.getGifThumbUrl(), R.drawable.likelist_defaut_bg);
            }
        }
    }

    private CommentItem a(MyCommentInfo myCommentInfo) {
        if (myCommentInfo == null) {
            return null;
        }
        CommentItem commentItem = new CommentItem();
        commentItem.setId(myCommentInfo.id);
        commentItem.setImageWidth(myCommentInfo.getImageWidth());
        commentItem.setImageHeight(myCommentInfo.getImageHeight());
        commentItem.setVideoPlayUrl(myCommentInfo.getVideoPlayUrl());
        commentItem.setVideoThumbnail(myCommentInfo.getVideoThumbnail());
        return commentItem;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hot_cmt_content_layout:
            case R.id.comment_content_all_layout:
            case R.id.bottom_comment_layout:
                this.b.c.d(view, (ListItemObject) this.c);
                return;
            default:
                return;
        }
    }
}
