package com.budejie.www.activity.detail;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ali.auth.third.core.model.Constants;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.CommentShowBigPicture;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.label.enumeration.PlatePostEnum;
import com.budejie.www.activity.label.enumeration.UserBanOperator;
import com.budejie.www.activity.video.CommentItemVideoActivity;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.VoteData;
import com.budejie.www.util.ac;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.budejie.www.widget.VoteView;
import com.budejie.www.widget.parsetagview.NewParseTagEditText;
import com.budejie.www.widget.parsetagview.ParseTagTextView;
import com.sprite.ads.nati.reporter.Reporter;
import de.greenrobot.event.EventBus;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import pl.droidsonroids.gif.GifDrawable;

public class a extends BaseAdapter {
    DecimalFormat a = new DecimalFormat("##.0");
    DecimalFormat b = new DecimalFormat("##.00");
    Map<String, View> c;
    CommentItem d;
    Activity e;
    com.budejie.www.g.b f;
    com.budejie.www.http.c g;
    com.budejie.www.c.e h;
    ArrayList<String> i;
    ListItemObject j;
    SharedPreferences k;
    a l;
    public boolean m = false;
    Reporter n;
    private ArrayList<CommentItem> o = new ArrayList();
    private boolean p;
    private LinearLayout q;
    private boolean r = true;
    private boolean s = false;

    public interface a {
        void a();

        void a(int i, String str);

        void a(CommentItem commentItem);

        void a(String str);

        void b(CommentItem commentItem);

        void b(String str);

        void c(String str);

        void d(String str);

        void e(String str);
    }

    class b implements OnClickListener {
        CommentItem a;
        View b;
        final /* synthetic */ a c;

        public b(a aVar, CommentItem commentItem, View view) {
            this.c = aVar;
            this.a = commentItem;
            this.b = view;
        }

        public void onClick(View view) {
            if (!an.b()) {
                this.c.b(this.a, this.b);
            }
        }
    }

    class c implements OnClickListener {
        CommentItem a;
        View b;
        final /* synthetic */ a c;

        public c(a aVar, CommentItem commentItem, View view) {
            this.c = aVar;
            this.a = commentItem;
            this.b = view;
        }

        public void onClick(View view) {
            if (!an.b()) {
                this.c.a(this.a, this.b);
            }
        }
    }

    class d implements OnClickListener {
        CommentItem a;
        final /* synthetic */ a b;

        public d(a aVar, CommentItem commentItem) {
            this.b = aVar;
            this.a = commentItem;
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
                    CommentItemVideoActivity.a(this.b.e, this.a);
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

    class e implements OnClickListener {
        CommentItem a;
        final /* synthetic */ a b;

        public e(a aVar, CommentItem commentItem) {
            this.b = aVar;
            this.a = commentItem;
        }

        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.a.getVideoPlayUrl())) {
                CommentItemVideoActivity.a(this.b.e, this.a);
            }
        }
    }

    class f implements OnClickListener {
        int a;
        CommentItem b;
        View c;
        final /* synthetic */ a d;

        public f(a aVar, int i, CommentItem commentItem, View view) {
            this.d = aVar;
            this.a = i;
            this.b = commentItem;
            this.c = view;
        }

        public void onClick(View view) {
            if (this.b.getStatus() != null && !this.b.getStatus().equals("1") && !this.b.getStatus().equals("4")) {
                this.d.a(this.a, this.b, this.c);
            }
        }
    }

    private class g {
        TextView a;
        TextView b;
        TextView c;
        LinearLayout d;
        RelativeLayout e;
        ImageView f;
        ProgressBar g;
        FrameLayout h;
        TextView i;
        ImageView j;
        FrameLayout k;
        TextView l;
        ImageView m;
        RelativeLayout n;
        ImageView o;
        AsyncImageView p;
        ImageView q;
        AudioLayout r;
        View s;
        ImageView t;
        TextView u;
        LinearLayout v;
        TextView w;
        final /* synthetic */ a x;

        private g(a aVar) {
            this.x = aVar;
        }
    }

    private class h {
        VoteView A;
        LinearLayout B;
        TextView C;
        View D;
        TextView E;
        LinearLayout F;
        LinearLayout G;
        ImageView H;
        RelativeLayout I;
        LinearLayout J;
        final /* synthetic */ a K;
        AsyncImageView a;
        TextView b;
        AsyncImageView c;
        NewParseTagEditText d;
        ImageView e;
        TextView f;
        TextView g;
        FrameLayout h;
        ImageView i;
        TextView j;
        FrameLayout k;
        ImageView l;
        ImageView m;
        AudioLayout n;
        NewParseTagEditText o;
        ProgressBar p;
        LinearLayout q;
        TextView r;
        LinearLayout s;
        RelativeLayout t;
        ImageView u;
        ProgressBar v;
        RelativeLayout w;
        AsyncImageView x;
        ImageView y;
        ImageView z;

        private h(a aVar) {
            this.K = aVar;
        }
    }

    class i implements OnClickListener {
        VoteData a;
        final /* synthetic */ a b;

        public i(a aVar, VoteData voteData) {
            this.b = aVar;
            this.a = voteData;
        }

        public void onClick(View view) {
            new com.budejie.www.widget.g(this.b.e, R.style.dialog, this.a).show();
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Activity activity, com.budejie.www.g.b bVar, com.budejie.www.http.c cVar, com.budejie.www.c.e eVar) {
        this.e = activity;
        this.f = bVar;
        this.g = cVar;
        this.h = eVar;
        this.c = new HashMap();
        this.i = new ArrayList();
        this.k = activity.getSharedPreferences("weiboprefer", 0);
    }

    public void a(ListItemObject listItemObject) {
        this.j = listItemObject;
    }

    public void a(boolean z) {
        this.p = z;
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void a(ArrayList<CommentItem> arrayList) {
        this.o = arrayList;
        notifyDataSetChanged();
    }

    public void a(LinearLayout linearLayout) {
        this.q = linearLayout;
    }

    public void b(boolean z) {
        this.r = z;
    }

    public void a(Reporter reporter) {
        this.n = reporter;
    }

    public int getCount() {
        return this.o.size();
    }

    public CommentItem a(int i) {
        return (CommentItem) this.o.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        CommentItem a = a(i);
        if (a.isTagIsShow() && a.isIsnew()) {
            return 1;
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        h hVar;
        String str;
        int i2;
        CharSequence charSequence;
        NumberFormatException e;
        if (view == null) {
            h hVar2 = new h();
            view = this.e.getLayoutInflater().inflate(R.layout.comment_item, null);
            hVar2.a = (AsyncImageView) view.findViewById(R.id.thume_img);
            hVar2.b = (TextView) view.findViewById(R.id.user_name);
            hVar2.c = (AsyncImageView) view.findViewById(R.id.iv_members_mark);
            hVar2.e = (ImageView) view.findViewById(R.id.user_sex);
            hVar2.d = (NewParseTagEditText) view.findViewById(R.id.cmtContent);
            hVar2.q = (LinearLayout) view.findViewById(R.id.comment_title_layout);
            hVar2.f = (TextView) view.findViewById(R.id.cmtTagTv);
            hVar2.g = (TextView) view.findViewById(R.id.commentLikeCount);
            hVar2.h = (FrameLayout) view.findViewById(R.id.commentDingLayout);
            hVar2.i = (ImageView) view.findViewById(R.id.commentDingIv);
            hVar2.j = (TextView) view.findViewById(R.id.commentCaiCount);
            hVar2.k = (FrameLayout) view.findViewById(R.id.commentCaiLayout);
            hVar2.l = (ImageView) view.findViewById(R.id.commentCaiIv);
            hVar2.m = (ImageView) view.findViewById(R.id.commend_listview_divider);
            hVar2.n = (AudioLayout) view.findViewById(R.id.cmtVoice);
            hVar2.o = (NewParseTagEditText) view.findViewById(R.id.replyUser);
            hVar2.p = (ProgressBar) view.findViewById(R.id.pb_cmtSending);
            hVar2.r = (TextView) view.findViewById(R.id.cmtLikeCount);
            hVar2.s = (LinearLayout) view.findViewById(R.id.ListCommentLayout);
            hVar2.t = (RelativeLayout) view.findViewById(R.id.HotMoreLayout);
            hVar2.u = (ImageView) view.findViewById(R.id.HotMoreArrow);
            hVar2.v = (ProgressBar) view.findViewById(R.id.HotMoreProgressBar);
            hVar2.w = (RelativeLayout) view.findViewById(R.id.ReplyImageLayout);
            hVar2.x = (AsyncImageView) view.findViewById(R.id.ItemImageView);
            hVar2.y = (ImageView) view.findViewById(R.id.PlayVideoImageView);
            hVar2.z = (ImageView) view.findViewById(R.id.GifIconImageView);
            hVar2.A = (VoteView) view.findViewById(R.id.comment_vote_view);
            hVar2.B = (LinearLayout) view.findViewById(R.id.pub_view);
            hVar2.C = (TextView) view.findViewById(R.id.pub_content_view);
            hVar2.D = view.findViewById(R.id.author_replay_mark);
            hVar2.E = (TextView) view.findViewById(R.id.author_replay_mark_content);
            hVar2.F = (LinearLayout) view.findViewById(R.id.hot_author_replay);
            hVar2.G = (LinearLayout) view.findViewById(R.id.comment_user_name_layout);
            hVar2.H = (ImageView) view.findViewById(R.id.hot_author_replay_divider);
            hVar2.I = (RelativeLayout) view.findViewById(R.id.commend_content_layout);
            hVar2.J = (LinearLayout) view.findViewById(R.id.ll_middle_content_layout);
            view.setTag(R.layout.comment_item, hVar2);
            if (getItemViewType(i) == 1 && this.r && this.q != null) {
                try {
                    ((LinearLayout) view.findViewById(R.id.ad_layout)).addView(this.q, new LayoutParams(-1, -2));
                } catch (Exception e2) {
                }
                if (!(this.n == null || this.m)) {
                    this.m = true;
                    this.n.onExposured(this.q);
                }
                hVar = hVar2;
            } else {
                hVar = hVar2;
            }
        } else {
            hVar = (h) view.getTag(R.layout.comment_item);
        }
        hVar.q.setFocusable(false);
        hVar.m.setVisibility(0);
        this.d = (CommentItem) this.o.get(i);
        hVar.w.setVisibility(8);
        hVar.y.setVisibility(8);
        hVar.z.setVisibility(8);
        hVar.n.setVisibility(8);
        hVar.B.setVisibility(8);
        String type = this.d.getType();
        if (type == null || (type.equals("") | type.equals("null")) != 0) {
            type = "unknown";
        }
        if (this.d.isPub()) {
            str = "pub";
        } else {
            str = type;
        }
        if (!str.equals("text")) {
            if (str.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                hVar.w.setVisibility(0);
                hVar.w.setOnClickListener(new d(this, this.d));
                com.budejie.www.adapter.b.a.c(hVar.x, this.d.getImageWidth(), this.d.getImageHeight());
                hVar.x.setAsyncCacheImage(this.d.getImageThumbUrl(), R.drawable.likelist_defaut_bg);
            } else if (str.equals("gif")) {
                hVar.z.setVisibility(0);
                hVar.w.setVisibility(0);
                hVar.w.setOnClickListener(new d(this, this.d));
                com.budejie.www.adapter.b.a.c(hVar.x, this.d.getGifWidth(), this.d.getGifHeight());
                hVar.x.setAsyncCacheImage(this.d.getGifThumbUrl(), R.drawable.likelist_defaut_bg);
            } else if (str.equals("audio")) {
                hVar.n.setVisibility(0);
                hVar.n.c();
                hVar.n.setPlayPath(this.d.getAudioUrl());
                hVar.n.setAudioTime("" + this.d.getAudioDuration());
                ac a = ac.a(this.e);
                if (a.c()) {
                    type = a.m();
                    if (type == null || !type.equals(this.d.getAudioUrl())) {
                        hVar.n.c();
                    } else {
                        hVar.n.d();
                    }
                } else {
                    hVar.n.c();
                }
            } else if (str.equals("video")) {
                hVar.w.setVisibility(0);
                hVar.w.setOnClickListener(new d(this, this.d));
                hVar.y.setVisibility(0);
                com.budejie.www.adapter.b.a.c(hVar.x, 0, 0);
                hVar.x.setAsyncCacheImage(this.d.getVideoThumbnail(), R.drawable.likelist_defaut_bg);
            } else if (str.equals("pub")) {
                hVar.B.setVisibility(0);
                hVar.C.setText(this.d.getmVideoTime() + "\" 神配音");
                hVar.B.setOnClickListener(new e(this, this.d));
            }
        }
        VoteData voteData = this.d.getVoteData();
        if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
            hVar.A.setVisibility(8);
        } else {
            hVar.A.a();
            hVar.A.setVisibility(0);
            hVar.A.setVoteData(voteData);
        }
        if (this.d.getHotNp() == null || this.d.getHotNp().equals("null")) {
            hVar.t.setVisibility(8);
        } else {
            hVar.t.setVisibility(0);
            hVar.u.setVisibility(0);
            hVar.v.setVisibility(8);
            hVar.t.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    hVar.u.setVisibility(8);
                    hVar.v.setVisibility(0);
                    if (this.b.l != null) {
                        this.b.l.a();
                    }
                }
            });
        }
        if (this.d.getReplyList() == null || this.d.getReplyList().size() <= 0) {
            hVar.s.setVisibility(8);
            hVar.D.setVisibility(8);
            hVar.E.setVisibility(8);
        } else {
            a(hVar, this.d.getReplyList(), i);
            if (this.d.getUid().equals(this.d.getAuthorUid())) {
                hVar.D.setVisibility(0);
                hVar.E.setVisibility(0);
            } else {
                hVar.D.setVisibility(8);
                hVar.E.setVisibility(8);
            }
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) hVar.I.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) hVar.J.getLayoutParams();
        if (this.d.isHotAuthorReplay()) {
            hVar.F.setVisibility(0);
            hVar.G.setVisibility(8);
            hVar.a.setVisibility(8);
            hVar.r.setVisibility(8);
            hVar.m.setVisibility(8);
            if (this.d.isHotAuthorReplayFirst()) {
                hVar.H.setVisibility(0);
                layoutParams.setMargins(layoutParams.leftMargin, an.a(this.e, 10), layoutParams.rightMargin, layoutParams.bottomMargin);
                hVar.I.setLayoutParams(layoutParams);
            } else {
                hVar.H.setVisibility(8);
                layoutParams.setMargins(layoutParams.leftMargin, -an.a(this.e, 5), layoutParams.rightMargin, layoutParams.bottomMargin);
                hVar.I.setLayoutParams(layoutParams);
            }
            layoutParams2.setMargins(an.a(this.e, 21), layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
            hVar.J.setLayoutParams(layoutParams2);
        } else {
            layoutParams.setMargins(layoutParams.leftMargin, an.a(this.e, 10), layoutParams.rightMargin, layoutParams.bottomMargin);
            hVar.I.setLayoutParams(layoutParams);
            layoutParams2.setMargins(an.a(this.e, 12), layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
            hVar.J.setLayoutParams(layoutParams2);
            hVar.F.setVisibility(8);
            hVar.G.setVisibility(0);
            hVar.a.setVisibility(0);
            hVar.H.setVisibility(8);
            hVar.r.setVisibility(0);
            hVar.m.setVisibility(0);
            hVar.b.setText(this.d.getUname());
            if (TextUtils.isEmpty(this.d.getIs_vip()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(this.d.getIs_vip())) {
                hVar.b.setTextColor(this.e.getResources().getColor(j.F));
                hVar.c.setVisibility(8);
            } else {
                hVar.b.setTextColor(this.e.getResources().getColor(j.H));
                try {
                    hVar.c.setVisibility(0);
                    Drawable gifDrawable = new GifDrawable(this.e.getResources(), j.I);
                    hVar.c.setImageDrawable(gifDrawable);
                    gifDrawable.start();
                } catch (NotFoundException e3) {
                    e3.printStackTrace();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            if ("m".equals(this.d.getSex())) {
                hVar.e.setBackgroundResource(j.bs);
            } else {
                hVar.e.setBackgroundResource(j.br);
            }
            type = this.d.getProfile();
            hVar.a.setVisibility(0);
            hVar.a.setPostAvatarImage(type);
            Bundle bundle = new Bundle();
            bundle.putString(PersonalProfileActivity.c, this.d.getUid());
            bundle.putBoolean(PersonalProfileActivity.f, true);
            hVar.a.setOnClickListener(this.f.a(7, bundle));
            hVar.b.setOnClickListener(this.f.a(7, bundle));
            view.setTag(R.layout.cmt_pop_item, this.d);
            i2 = 0;
            charSequence = "0";
            try {
                i2 = Integer.parseInt(this.d.getCmtLikeCount());
            } catch (NumberFormatException e5) {
            }
            if (i2 < 1000) {
                charSequence = "" + i2;
                hVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like1_bg));
            } else if (i2 < 10000) {
                charSequence = "" + this.b.format(((double) i2) / 1000.0d) + "k";
                hVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like2_bg));
            } else if (i2 < 100000) {
                charSequence = "" + this.a.format(((double) i2) / 1000.0d) + "k";
                hVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like3_bg));
            } else if (i2 < 1000000) {
                charSequence = "" + (i2 / 1000) + "k";
                hVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
            } else if (1000000 < i2 && i2 < 10000000) {
                charSequence = "" + this.b.format(((double) i2) / 1000000.0d) + "m";
                hVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
            } else if (i2 > 10000000 && i2 < 100000000) {
                charSequence = "" + this.a.format(((double) i2) / 1000000.0d) + "m";
                hVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
            } else if (100000000 < i2) {
                charSequence = "" + (i2 / 1000000) + "m";
                hVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
            }
            hVar.r.setText(charSequence);
        }
        if (this.d.isTagIsShow()) {
            hVar.q.setVisibility(0);
            hVar.f.setBackgroundResource(j.r);
            hVar.m.setVisibility(8);
            hVar.H.setVisibility(8);
            if (this.d.isIshot()) {
                hVar.f.setText(R.string.hot_comment);
            } else {
                hVar.f.setText(R.string.new_comment);
            }
            hVar.q.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            });
            if (ai.a(this.e) == 1) {
                hVar.q.setPadding(0, 0, 0, 0);
            } else {
                hVar.q.setPadding(an.a(this.e, 1), 0, an.a(this.e, 1), 0);
            }
        } else {
            hVar.q.setVisibility(8);
        }
        String dingCount = this.d.getDingCount();
        String caiCount = this.d.getCaiCount();
        int i3 = 0;
        try {
            i2 = Integer.parseInt(dingCount);
            try {
                i3 = Integer.parseInt(caiCount);
            } catch (NumberFormatException e6) {
                e = e6;
                Log.i("Commend-dingCount1", e.toString());
                hVar.g.setText(i2 <= 0 ? i2 + "" : "");
                hVar.j.setText(i3 <= 0 ? i3 + "" : "");
                type = this.d.getDingOrCai();
                if (!"like".equals(type)) {
                    hVar.i.setSelected(true);
                    hVar.g.setSelected(true);
                    hVar.l.setSelected(false);
                    hVar.j.setSelected(false);
                } else if ("hate".equals(type)) {
                    hVar.i.setSelected(false);
                    hVar.g.setSelected(false);
                    hVar.l.setSelected(false);
                    hVar.j.setSelected(false);
                } else {
                    hVar.i.setSelected(false);
                    hVar.g.setSelected(false);
                    hVar.l.setSelected(true);
                    hVar.j.setSelected(true);
                }
                hVar.h.setTag(Integer.valueOf(i));
                hVar.h.setOnClickListener(new c(this, this.d, view));
                hVar.k.setTag(Integer.valueOf(i));
                hVar.k.setOnClickListener(new b(this, this.d, view));
                charSequence = "";
                if (this.d.getMpreName().length() > 0) {
                    hVar.o.setVisibility(8);
                } else {
                    charSequence = "//@" + this.d.getMpreName() + ":";
                    if (!TextUtils.isEmpty(this.d.getMpreContent())) {
                        charSequence = charSequence + this.d.getMpreContent();
                    }
                    hVar.o.setVisibility(0);
                    ParseTagTextView.a(this.e.getApplicationContext(), hVar.o, charSequence);
                }
                if (!TextUtils.isEmpty(this.d.getVoiceuri())) {
                    if (TextUtils.isEmpty(this.d.getContent())) {
                        hVar.o.setVisibility(0);
                        ParseTagTextView.a(this.e.getApplicationContext(), hVar.o, charSequence + " " + this.d.getContent());
                    } else if (TextUtils.isEmpty(charSequence)) {
                        hVar.o.setVisibility(8);
                    }
                }
                if (this.d.getState() != -1) {
                    hVar.p.setVisibility(0);
                    hVar.d.setVisibility(8);
                } else {
                    hVar.p.setVisibility(8);
                    charSequence = this.d.getContent();
                    if (charSequence != null) {
                    }
                    hVar.d.setVisibility(8);
                    hVar.d.setOnClickListener(new f(this, i, (CommentItem) this.o.get(i), view));
                    hVar.I.setOnClickListener(new f(this, i, (CommentItem) this.o.get(i), view));
                }
                return view;
            }
        } catch (NumberFormatException e7) {
            NumberFormatException numberFormatException = e7;
            i2 = 0;
            e = numberFormatException;
            Log.i("Commend-dingCount1", e.toString());
            if (i2 <= 0) {
            }
            hVar.g.setText(i2 <= 0 ? i2 + "" : "");
            if (i3 <= 0) {
            }
            hVar.j.setText(i3 <= 0 ? i3 + "" : "");
            type = this.d.getDingOrCai();
            if (!"like".equals(type)) {
                hVar.i.setSelected(true);
                hVar.g.setSelected(true);
                hVar.l.setSelected(false);
                hVar.j.setSelected(false);
            } else if ("hate".equals(type)) {
                hVar.i.setSelected(false);
                hVar.g.setSelected(false);
                hVar.l.setSelected(true);
                hVar.j.setSelected(true);
            } else {
                hVar.i.setSelected(false);
                hVar.g.setSelected(false);
                hVar.l.setSelected(false);
                hVar.j.setSelected(false);
            }
            hVar.h.setTag(Integer.valueOf(i));
            hVar.h.setOnClickListener(new c(this, this.d, view));
            hVar.k.setTag(Integer.valueOf(i));
            hVar.k.setOnClickListener(new b(this, this.d, view));
            charSequence = "";
            if (this.d.getMpreName().length() > 0) {
                charSequence = "//@" + this.d.getMpreName() + ":";
                if (TextUtils.isEmpty(this.d.getMpreContent())) {
                    charSequence = charSequence + this.d.getMpreContent();
                }
                hVar.o.setVisibility(0);
                ParseTagTextView.a(this.e.getApplicationContext(), hVar.o, charSequence);
            } else {
                hVar.o.setVisibility(8);
            }
            if (TextUtils.isEmpty(this.d.getVoiceuri())) {
                if (TextUtils.isEmpty(this.d.getContent())) {
                    hVar.o.setVisibility(0);
                    ParseTagTextView.a(this.e.getApplicationContext(), hVar.o, charSequence + " " + this.d.getContent());
                } else if (TextUtils.isEmpty(charSequence)) {
                    hVar.o.setVisibility(8);
                }
            }
            if (this.d.getState() != -1) {
                hVar.p.setVisibility(8);
                charSequence = this.d.getContent();
                if (charSequence != null) {
                }
                hVar.d.setVisibility(8);
                hVar.d.setOnClickListener(new f(this, i, (CommentItem) this.o.get(i), view));
                hVar.I.setOnClickListener(new f(this, i, (CommentItem) this.o.get(i), view));
            } else {
                hVar.p.setVisibility(0);
                hVar.d.setVisibility(8);
            }
            return view;
        }
        if (i2 <= 0) {
        }
        hVar.g.setText(i2 <= 0 ? i2 + "" : "");
        if (i3 <= 0) {
        }
        hVar.j.setText(i3 <= 0 ? i3 + "" : "");
        type = this.d.getDingOrCai();
        if (!"like".equals(type)) {
            hVar.i.setSelected(true);
            hVar.g.setSelected(true);
            hVar.l.setSelected(false);
            hVar.j.setSelected(false);
        } else if ("hate".equals(type)) {
            hVar.i.setSelected(false);
            hVar.g.setSelected(false);
            hVar.l.setSelected(true);
            hVar.j.setSelected(true);
        } else {
            hVar.i.setSelected(false);
            hVar.g.setSelected(false);
            hVar.l.setSelected(false);
            hVar.j.setSelected(false);
        }
        hVar.h.setTag(Integer.valueOf(i));
        hVar.h.setOnClickListener(new c(this, this.d, view));
        hVar.k.setTag(Integer.valueOf(i));
        hVar.k.setOnClickListener(new b(this, this.d, view));
        charSequence = "";
        if (this.d.getMpreName().length() > 0) {
            charSequence = "//@" + this.d.getMpreName() + ":";
            if (TextUtils.isEmpty(this.d.getMpreContent())) {
                charSequence = charSequence + this.d.getMpreContent();
            }
            hVar.o.setVisibility(0);
            ParseTagTextView.a(this.e.getApplicationContext(), hVar.o, charSequence);
        } else {
            hVar.o.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.d.getVoiceuri())) {
            if (TextUtils.isEmpty(this.d.getContent())) {
                hVar.o.setVisibility(0);
                ParseTagTextView.a(this.e.getApplicationContext(), hVar.o, charSequence + " " + this.d.getContent());
            } else if (TextUtils.isEmpty(charSequence)) {
                hVar.o.setVisibility(8);
            }
        }
        if (this.d.getState() != -1) {
            hVar.p.setVisibility(8);
            charSequence = this.d.getContent();
            if (charSequence != null || "".equals(charSequence) || str.equals("audio")) {
                hVar.d.setVisibility(8);
            } else {
                hVar.d.setVisibility(0);
                hVar.d.setText(charSequence);
            }
            hVar.d.setOnClickListener(new f(this, i, (CommentItem) this.o.get(i), view));
            hVar.I.setOnClickListener(new f(this, i, (CommentItem) this.o.get(i), view));
        } else {
            hVar.p.setVisibility(0);
            hVar.d.setVisibility(8);
        }
        return view;
    }

    private void a(h hVar, ArrayList<CommentItem> arrayList, int i) {
        NumberFormatException e;
        View view = null;
        hVar.s.setVisibility(0);
        hVar.s.removeAllViews();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            View view2;
            Object obj;
            final CommentItem commentItem = (CommentItem) arrayList.get(i2);
            g gVar = null;
            if (this.c.containsKey(commentItem.getId() + this.d.getId())) {
                view2 = (View) this.c.get(commentItem.getId() + this.d.getId());
                gVar = (g) view2.getTag(R.layout.comment_item_reply);
                try {
                    hVar.s.removeView(view2);
                    hVar.s.addView(view2);
                    obj = null;
                } catch (Exception e2) {
                    obj = 1;
                }
            } else {
                int i3 = 1;
                view2 = view;
            }
            if (obj != null) {
                g gVar2 = new g();
                View inflate = this.e.getLayoutInflater().inflate(R.layout.comment_item_reply, null);
                gVar2.d = (LinearLayout) inflate.findViewById(R.id.ReplayLayout);
                gVar2.e = (RelativeLayout) inflate.findViewById(R.id.HideLayout);
                gVar2.f = (ImageView) inflate.findViewById(R.id.LappedArrow);
                gVar2.g = (ProgressBar) inflate.findViewById(R.id.LappedProgressBar);
                gVar2.a = (TextView) inflate.findViewById(R.id.UserNameTextView);
                gVar2.t = (ImageView) inflate.findViewById(R.id.iv_members_mark_reply);
                gVar2.b = (TextView) inflate.findViewById(R.id.ContentTextView);
                gVar2.c = (TextView) inflate.findViewById(R.id.NumberTextView);
                gVar2.h = (FrameLayout) inflate.findViewById(R.id.commentDingLayout);
                gVar2.i = (TextView) inflate.findViewById(R.id.commentLikeCount);
                gVar2.j = (ImageView) inflate.findViewById(R.id.commentDingIv);
                gVar2.k = (FrameLayout) inflate.findViewById(R.id.commentCaiLayout);
                gVar2.l = (TextView) inflate.findViewById(R.id.commentCaiCount);
                gVar2.m = (ImageView) inflate.findViewById(R.id.commentCaiIv);
                gVar2.n = (RelativeLayout) inflate.findViewById(R.id.ReplyImageLayout);
                gVar2.o = (ImageView) inflate.findViewById(R.id.PlayVideoImageView);
                gVar2.p = (AsyncImageView) inflate.findViewById(R.id.ItemImageView);
                gVar2.q = (ImageView) inflate.findViewById(R.id.GifIconImageView);
                gVar2.r = (AudioLayout) inflate.findViewById(R.id.cmtVoice);
                gVar2.s = inflate.findViewById(R.id.line_view);
                gVar2.u = (TextView) inflate.findViewById(R.id.comment_reply_vote);
                gVar2.v = (LinearLayout) inflate.findViewById(R.id.pub_view);
                gVar2.w = (TextView) inflate.findViewById(R.id.pub_content_view);
                try {
                    hVar.s.addView(inflate);
                } catch (Exception e3) {
                }
                inflate.setTag(R.layout.comment_item_reply, gVar2);
                this.c.put(commentItem.getId() + this.d.getId(), inflate);
                gVar = gVar2;
                view = inflate;
            } else {
                view = view2;
            }
            if (commentItem.getLapped() == null || !commentItem.getLapped().equals(Constants.SERVICE_SCOPE_FLAG_VALUE)) {
                gVar.c.setVisibility(0);
                gVar.d.setVisibility(0);
                gVar.e.setVisibility(8);
                gVar.n.setVisibility(8);
                gVar.o.setVisibility(8);
                gVar.q.setVisibility(8);
                gVar.r.setVisibility(8);
                gVar.v.setVisibility(8);
                gVar.b.setVisibility(0);
                gVar.s.setVisibility(0);
                if (i2 == arrayList.size() - 1) {
                    gVar.s.setVisibility(8);
                }
                String type = commentItem.getType();
                if (type == null || (type.equals("") | type.equals("null")) != 0) {
                    type = "unknown";
                }
                if (commentItem.isPub()) {
                    type = "pub";
                }
                if (!type.equals("text")) {
                    if (type.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                        if (commentItem.getContent().equals("")) {
                            gVar.b.setVisibility(8);
                        }
                        gVar.n.setVisibility(0);
                        gVar.n.setOnClickListener(new d(this, commentItem));
                        com.budejie.www.adapter.b.a.c(gVar.p, commentItem.getImageWidth(), commentItem.getImageHeight());
                        gVar.p.setAsyncCacheImage(commentItem.getImageThumbUrl(), R.drawable.likelist_defaut_bg);
                    } else if (type.equals("audio")) {
                        gVar.r.setVisibility(0);
                        gVar.b.setVisibility(8);
                        gVar.r.c();
                        gVar.r.setPlayPath(commentItem.getAudioUrl());
                        gVar.r.setAudioTime("" + commentItem.getAudioDuration());
                        ac a = ac.a(this.e);
                        if (a.c()) {
                            type = a.m();
                            if (type == null || !type.equals(commentItem.getAudioUrl())) {
                                gVar.r.c();
                            } else {
                                gVar.r.d();
                            }
                        } else {
                            gVar.r.c();
                        }
                    } else if (type.equals("video")) {
                        if (commentItem.getContent().equals("")) {
                            gVar.b.setVisibility(8);
                        }
                        gVar.n.setVisibility(0);
                        gVar.o.setVisibility(0);
                        gVar.n.setOnClickListener(new d(this, commentItem));
                        com.budejie.www.adapter.b.a.c(gVar.p, 0, 0);
                        gVar.p.setAsyncCacheImage(commentItem.getVideoThumbnail(), R.drawable.likelist_defaut_bg);
                    } else if (type.equals("gif")) {
                        if (commentItem.getContent().equals("")) {
                            gVar.b.setVisibility(8);
                        }
                        gVar.q.setVisibility(0);
                        gVar.n.setVisibility(0);
                        gVar.n.setOnClickListener(new d(this, commentItem));
                        com.budejie.www.adapter.b.a.c(gVar.p, commentItem.getGifWidth(), commentItem.getGifHeight());
                        gVar.p.setAsyncCacheImage(commentItem.getGifThumbUrl(), R.drawable.likelist_defaut_bg);
                    } else if (type.equals("pub")) {
                        gVar.v.setVisibility(0);
                        gVar.w.setText(commentItem.getmVideoTime() + "\" 神配音");
                        gVar.v.setOnClickListener(new e(this, commentItem));
                    } else {
                        gVar.b.setText("当前版本暂不支持查看此评论，请升级至最新版本。");
                    }
                }
                VoteData voteData = commentItem.getVoteData();
                if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
                    gVar.u.setVisibility(8);
                } else {
                    gVar.u.setVisibility(0);
                    gVar.u.setOnClickListener(new i(this, voteData));
                }
                gVar.a.setText(commentItem.getUname().replace("\n", ""));
                if (TextUtils.isEmpty(commentItem.getIs_vip()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(commentItem.getIs_vip())) {
                    gVar.a.setTextColor(this.e.getResources().getColor(R.color.comment_item_reply_name_color));
                    gVar.t.setVisibility(8);
                } else {
                    gVar.a.setTextColor(this.e.getResources().getColor(j.H));
                    try {
                        gVar.t.setVisibility(0);
                        Drawable gifDrawable = new GifDrawable(this.e.getResources(), j.I);
                        gVar.t.setImageDrawable(gifDrawable);
                        gifDrawable.start();
                    } catch (NotFoundException e4) {
                        e4.printStackTrace();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putString(PersonalProfileActivity.c, commentItem.getUid());
                bundle.putBoolean(PersonalProfileActivity.f, true);
                gVar.a.setOnClickListener(this.f.a(7, bundle));
                gVar.b.setText(commentItem.getContent());
                gVar.c.setText(commentItem.getFloorNum());
                if (commentItem.getStatus() == null || !(commentItem.getStatus().equals("1") || commentItem.getStatus().equals("4"))) {
                    gVar.b.setTextColor(com.budejie.www.h.c.a().c(R.attr.comment_reply_item_content_color));
                    String dingCount = commentItem.getDingCount();
                    String caiCount = commentItem.getCaiCount();
                    int i4 = 0;
                    try {
                        i3 = Integer.parseInt(dingCount);
                        try {
                            i4 = Integer.parseInt(caiCount);
                        } catch (NumberFormatException e6) {
                            e = e6;
                            Log.i("Commend-dingCount1", e.toString());
                            gVar.i.setText(i3 <= 0 ? "" : i3 + "");
                            gVar.l.setText(i4 <= 0 ? "" : i4 + "");
                            type = commentItem.getDingOrCai();
                            if (!"like".equals(type)) {
                                gVar.j.setSelected(true);
                                gVar.i.setSelected(true);
                                gVar.m.setSelected(false);
                                gVar.l.setSelected(false);
                            } else if ("hate".equals(type)) {
                                gVar.j.setSelected(false);
                                gVar.i.setSelected(false);
                                gVar.m.setSelected(true);
                                gVar.l.setSelected(true);
                            } else {
                                gVar.j.setSelected(false);
                                gVar.i.setSelected(false);
                                gVar.m.setSelected(false);
                                gVar.l.setSelected(false);
                            }
                            gVar.h.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ a c;

                                public void onClick(View view) {
                                    if (!an.b()) {
                                        this.c.a(commentItem, view);
                                    }
                                }
                            });
                            gVar.k.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ a c;

                                public void onClick(View view) {
                                    if (!an.b()) {
                                        this.c.b(commentItem, view);
                                    }
                                }
                            });
                            gVar.d.setTag(commentItem.getId());
                            gVar.d.setOnClickListener(new f(this, i, commentItem, view));
                        }
                    } catch (NumberFormatException e7) {
                        NumberFormatException numberFormatException = e7;
                        i3 = 0;
                        e = numberFormatException;
                        Log.i("Commend-dingCount1", e.toString());
                        if (i3 <= 0) {
                        }
                        gVar.i.setText(i3 <= 0 ? "" : i3 + "");
                        if (i4 <= 0) {
                        }
                        gVar.l.setText(i4 <= 0 ? "" : i4 + "");
                        type = commentItem.getDingOrCai();
                        if (!"like".equals(type)) {
                            gVar.j.setSelected(true);
                            gVar.i.setSelected(true);
                            gVar.m.setSelected(false);
                            gVar.l.setSelected(false);
                        } else if ("hate".equals(type)) {
                            gVar.j.setSelected(false);
                            gVar.i.setSelected(false);
                            gVar.m.setSelected(false);
                            gVar.l.setSelected(false);
                        } else {
                            gVar.j.setSelected(false);
                            gVar.i.setSelected(false);
                            gVar.m.setSelected(true);
                            gVar.l.setSelected(true);
                        }
                        gVar.h.setOnClickListener(/* anonymous class already generated */);
                        gVar.k.setOnClickListener(/* anonymous class already generated */);
                        gVar.d.setTag(commentItem.getId());
                        gVar.d.setOnClickListener(new f(this, i, commentItem, view));
                    }
                    if (i3 <= 0) {
                    }
                    gVar.i.setText(i3 <= 0 ? "" : i3 + "");
                    if (i4 <= 0) {
                    }
                    gVar.l.setText(i4 <= 0 ? "" : i4 + "");
                    type = commentItem.getDingOrCai();
                    if (!"like".equals(type)) {
                        gVar.j.setSelected(true);
                        gVar.i.setSelected(true);
                        gVar.m.setSelected(false);
                        gVar.l.setSelected(false);
                    } else if ("hate".equals(type)) {
                        gVar.j.setSelected(false);
                        gVar.i.setSelected(false);
                        gVar.m.setSelected(true);
                        gVar.l.setSelected(true);
                    } else {
                        gVar.j.setSelected(false);
                        gVar.i.setSelected(false);
                        gVar.m.setSelected(false);
                        gVar.l.setSelected(false);
                    }
                    gVar.h.setOnClickListener(/* anonymous class already generated */);
                    gVar.k.setOnClickListener(/* anonymous class already generated */);
                } else {
                    gVar.h.setVisibility(8);
                    gVar.k.setVisibility(8);
                    gVar.b.setTextColor(com.budejie.www.h.c.a().c(R.attr.comment_reply_item_delete_text_color));
                }
                gVar.d.setTag(commentItem.getId());
                gVar.d.setOnClickListener(new f(this, i, commentItem, view));
            } else {
                gVar.c.setVisibility(8);
                gVar.d.setVisibility(8);
                gVar.e.setVisibility(0);
                gVar.f.setVisibility(0);
                gVar.g.setVisibility(8);
                final ImageView imageView = gVar.f;
                final ProgressBar progressBar = gVar.g;
                final int i5 = i;
                gVar.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a e;

                    public void onClick(View view) {
                        imageView.setVisibility(8);
                        progressBar.setVisibility(0);
                        if (this.e.l != null) {
                            this.e.l.a(i5, commentItem.getId());
                        }
                    }
                });
            }
        }
    }

    private void a(CommentItem commentItem, View view) {
        if (!commentItem.isAlreadyDingCai()) {
            ImageView imageView = (ImageView) view.findViewById(R.id.commentDingIv);
            TextView textView = (TextView) view.findViewById(R.id.commentLikeCount);
            String id = commentItem.getId();
            com.budejie.www.util.d.a(this.e, (View) imageView.getParent(), "1");
            imageView.setSelected(true);
            textView.setSelected(true);
            int i = 0;
            try {
                i = Integer.parseInt(commentItem.getDingCount());
            } catch (Exception e) {
                Log.i("Commend-Ding", e.toString());
            }
            i++;
            textView.setText(String.valueOf(i));
            commentItem.setDingOrCai("like");
            commentItem.setDingCount(i + "");
            this.g.a(id, "like");
            this.h.a(id, "like");
        }
    }

    private void b(CommentItem commentItem, View view) {
        if (!commentItem.isAlreadyDingCai()) {
            ImageView imageView = (ImageView) view.findViewById(R.id.commentCaiIv);
            TextView textView = (TextView) view.findViewById(R.id.commentCaiCount);
            String id = commentItem.getId();
            com.budejie.www.util.d.a(this.e, (View) imageView.getParent(), "1");
            imageView.setSelected(true);
            textView.setSelected(true);
            int i = 0;
            try {
                i = Integer.parseInt(commentItem.getCaiCount());
            } catch (Exception e) {
                Log.i("Commend-Ding", e.toString());
            }
            i++;
            textView.setText(String.valueOf(i));
            commentItem.setDingOrCai("hate");
            commentItem.setCaiCount(i + "");
            this.g.a(id, "hate");
            this.h.a(id, "hate");
        }
    }

    private void a(int i, final CommentItem commentItem, View view) {
        if (this.l != null && commentItem != null) {
            int parseInt;
            final Dialog dialog = new Dialog(this.e, R.style.DialogTheme_CreateUgc);
            String string = this.k.getString("id", "");
            LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.e.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
            linearLayout.setMinimumWidth(10000);
            LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
            linearLayout2.setBackgroundResource(j.aC);
            Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
            button.setBackgroundResource(j.aC);
            button.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.e.getResources().getDimensionPixelOffset(R.dimen.list_item_writer_profile));
            this.i.clear();
            if (commentItem.getFloorNum() != null) {
                try {
                    parseInt = Integer.parseInt(commentItem.getFloorNum());
                } catch (Exception e) {
                    parseInt = 0;
                }
            } else if (commentItem.getReplyList() == null || commentItem.getReplyList().size() <= 0) {
                parseInt = 0;
            } else {
                try {
                    parseInt = Integer.parseInt(((CommentItem) commentItem.getReplyList().get(commentItem.getReplyList().size() - 1)).getFloorNum()) + 1;
                } catch (Exception e2) {
                    parseInt = 0;
                }
            }
            if (parseInt < 50 && !commentItem.getUid().equals(string)) {
                this.i.add("回复");
                if (!this.s) {
                    this.i.add("语音回复");
                }
            }
            if (!commentItem.getUid().equals(string)) {
                if (!(this.j == null || this.j.getUid() == null || !this.j.getUid().equals(string) || this.s)) {
                    this.i.add("删除");
                }
                this.i.add("举报");
            } else if (!this.s) {
                this.i.add("删除");
            }
            if (!this.s) {
                if (an.v(this.e)) {
                    this.i.add("删除");
                    this.i.add("删除并拉黑");
                }
                if (commentItem.getType().equals("audio")) {
                    this.i.add("转文字");
                }
            }
            String uid = commentItem.getUid();
            Object obj = (!this.p || !CommonLabelActivity.h || uid.equals(string) || CommonLabelActivity.a(uid)) ? null : 1;
            if (obj != null) {
                this.i.add(CommonLabelActivity.b(uid) ? "取消禁言" : "禁言");
            }
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                final String str = (String) this.i.get(i2);
                View textView = new TextView(this.e);
                textView.setGravity(17);
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
                textView.setText(str);
                textView.setTextSize(2, 17.0f);
                textView.setBackgroundResource(j.aC);
                textView.setTag(Integer.valueOf(i2));
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a d;

                    public void onClick(View view) {
                        dialog.dismiss();
                        if (this.d.l != null) {
                            String id = commentItem.getId();
                            if (str.equals("删除")) {
                                this.d.l.a(id);
                            } else if (str.equals("删除并拉黑")) {
                                this.d.l.c(id);
                            } else if (str.equals("拉黑")) {
                                this.d.l.b(id);
                            } else if (str.equals("转文字")) {
                                this.d.l.d(id);
                            } else if (str.equals("回复")) {
                                this.d.l.a(commentItem);
                            } else if (str.equals("语音回复")) {
                                this.d.l.b(commentItem);
                            } else if (str.equals("举报")) {
                                this.d.l.e(id);
                            } else if (str.equals("禁言") || str.equals("取消禁言")) {
                                this.d.a(str, commentItem);
                            }
                        }
                    }
                });
                textView.setLayoutParams(layoutParams);
                if (i2 == 0) {
                    linearLayout2.addView(textView);
                } else {
                    View imageView = new ImageView(this.e);
                    imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, this.e.getResources().getDimensionPixelOffset(R.dimen.divide_line_height)));
                    linearLayout2.addView(imageView);
                    linearLayout2.addView(textView);
                }
            }
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.x = 0;
            attributes.y = -1000;
            attributes.gravity = 80;
            dialog.onWindowAttributesChanged(attributes);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(linearLayout);
            dialog.show();
        }
    }

    private void a(String str, CommentItem commentItem) {
        if (!TextUtils.isEmpty(str)) {
            String value;
            UserBanOperator userBanOperator = new UserBanOperator();
            userBanOperator.setCommentItem(commentItem);
            if (str.equals("禁言")) {
                value = PlatePostEnum.USER_BAN.getValue();
            } else {
                value = PlatePostEnum.CANCEL_USER_BAN.getValue();
            }
            userBanOperator.setOperationType(value);
            EventBus.getDefault().post(userBanOperator);
        }
    }
}
