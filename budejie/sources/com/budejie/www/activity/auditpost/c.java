package com.budejie.www.activity.auditpost;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ali.auth.third.core.model.Constants;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.CommentShowBigPicture;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.video.CommentItemVideoActivity;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.VoteData;
import com.budejie.www.util.ac;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.budejie.www.widget.VoteView;
import com.budejie.www.widget.parsetagview.NewParseTagEditText;
import com.budejie.www.widget.parsetagview.ParseTagTextView;
import com.umeng.analytics.MobclickAgent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import pl.droidsonroids.gif.GifDrawable;

public class c extends BaseAdapter {
    CommentItem a;
    DecimalFormat b = new DecimalFormat("##.0");
    DecimalFormat c = new DecimalFormat("##.00");
    LayoutInflater d;
    private Activity e;
    private ArrayList<CommentItem> f;
    private com.budejie.www.http.c g;
    private com.budejie.www.g.b h;
    private com.budejie.www.c.e i;
    private OnClickListener j;

    class a implements OnClickListener {
        CommentItem a;
        View b;
        final /* synthetic */ c c;

        public a(c cVar, CommentItem commentItem, View view) {
            this.c = cVar;
            this.a = commentItem;
            this.b = view;
        }

        public void onClick(View view) {
            if (!an.b()) {
                this.c.b(this.a, this.b);
            }
        }
    }

    class b implements OnClickListener {
        CommentItem a;
        View b;
        final /* synthetic */ c c;

        public b(c cVar, CommentItem commentItem, View view) {
            this.c = cVar;
            this.a = commentItem;
            this.b = view;
        }

        public void onClick(View view) {
            if (!an.b()) {
                this.c.a(this.a, this.b);
            }
        }
    }

    class c implements OnClickListener {
        CommentItem a;
        final /* synthetic */ c b;

        public c(c cVar, CommentItem commentItem) {
            this.b = cVar;
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

    class d implements OnClickListener {
        CommentItem a;
        final /* synthetic */ c b;

        public d(c cVar, CommentItem commentItem) {
            this.b = cVar;
            this.a = commentItem;
        }

        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.a.getVideoPlayUrl())) {
                CommentItemVideoActivity.a(this.b.e, this.a);
            }
        }
    }

    class e {
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
        final /* synthetic */ c K;
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

        e(c cVar) {
            this.K = cVar;
        }
    }

    public c(Activity activity, com.budejie.www.c.e eVar, ArrayList<CommentItem> arrayList, OnClickListener onClickListener) {
        this.e = activity;
        this.d = LayoutInflater.from(this.e);
        this.f = arrayList;
        this.g = new com.budejie.www.http.c(this.e, null);
        this.i = eVar;
        this.j = onClickListener;
        this.h = new com.budejie.www.g.b(this.e);
    }

    public void a(ArrayList<CommentItem> arrayList) {
        this.f.clear();
        this.f.addAll(arrayList);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.f.size();
    }

    public Object getItem(int i) {
        return this.f.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        CommentItem commentItem = (CommentItem) getItem(i);
        if (commentItem.isTagIsShow() && commentItem.isIsnew()) {
            return 1;
        }
        return 0;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        String str;
        int i2;
        CharSequence charSequence;
        NumberFormatException e;
        if (view == null) {
            e eVar2 = new e(this);
            view = this.e.getLayoutInflater().inflate(R.layout.comment_item, null);
            eVar2.a = (AsyncImageView) view.findViewById(R.id.thume_img);
            eVar2.b = (TextView) view.findViewById(R.id.user_name);
            eVar2.c = (AsyncImageView) view.findViewById(R.id.iv_members_mark);
            eVar2.e = (ImageView) view.findViewById(R.id.user_sex);
            eVar2.d = (NewParseTagEditText) view.findViewById(R.id.cmtContent);
            eVar2.q = (LinearLayout) view.findViewById(R.id.comment_title_layout);
            eVar2.f = (TextView) view.findViewById(R.id.cmtTagTv);
            eVar2.g = (TextView) view.findViewById(R.id.commentLikeCount);
            eVar2.h = (FrameLayout) view.findViewById(R.id.commentDingLayout);
            eVar2.i = (ImageView) view.findViewById(R.id.commentDingIv);
            eVar2.j = (TextView) view.findViewById(R.id.commentCaiCount);
            eVar2.k = (FrameLayout) view.findViewById(R.id.commentCaiLayout);
            eVar2.l = (ImageView) view.findViewById(R.id.commentCaiIv);
            eVar2.m = (ImageView) view.findViewById(R.id.commend_listview_divider);
            eVar2.n = (AudioLayout) view.findViewById(R.id.cmtVoice);
            eVar2.o = (NewParseTagEditText) view.findViewById(R.id.replyUser);
            eVar2.p = (ProgressBar) view.findViewById(R.id.pb_cmtSending);
            eVar2.r = (TextView) view.findViewById(R.id.cmtLikeCount);
            eVar2.s = (LinearLayout) view.findViewById(R.id.ListCommentLayout);
            eVar2.t = (RelativeLayout) view.findViewById(R.id.HotMoreLayout);
            eVar2.u = (ImageView) view.findViewById(R.id.HotMoreArrow);
            eVar2.v = (ProgressBar) view.findViewById(R.id.HotMoreProgressBar);
            eVar2.w = (RelativeLayout) view.findViewById(R.id.ReplyImageLayout);
            eVar2.x = (AsyncImageView) view.findViewById(R.id.ItemImageView);
            eVar2.y = (ImageView) view.findViewById(R.id.PlayVideoImageView);
            eVar2.z = (ImageView) view.findViewById(R.id.GifIconImageView);
            eVar2.A = (VoteView) view.findViewById(R.id.comment_vote_view);
            eVar2.B = (LinearLayout) view.findViewById(R.id.pub_view);
            eVar2.C = (TextView) view.findViewById(R.id.pub_content_view);
            eVar2.D = view.findViewById(R.id.author_replay_mark);
            eVar2.E = (TextView) view.findViewById(R.id.author_replay_mark_content);
            eVar2.F = (LinearLayout) view.findViewById(R.id.hot_author_replay);
            eVar2.G = (LinearLayout) view.findViewById(R.id.comment_user_name_layout);
            eVar2.H = (ImageView) view.findViewById(R.id.hot_author_replay_divider);
            eVar2.I = (RelativeLayout) view.findViewById(R.id.commend_content_layout);
            eVar2.J = (LinearLayout) view.findViewById(R.id.ll_middle_content_layout);
            view.setTag(R.layout.comment_item, eVar2);
            eVar = eVar2;
        } else {
            eVar = (e) view.getTag(R.layout.comment_item);
        }
        eVar.q.setFocusable(false);
        eVar.m.setVisibility(0);
        eVar.w.setVisibility(8);
        eVar.y.setVisibility(8);
        eVar.z.setVisibility(8);
        eVar.n.setVisibility(8);
        eVar.B.setVisibility(8);
        this.a = (CommentItem) this.f.get(i);
        String type = this.a.getType();
        if (type == null || (type.equals("") | type.equals("null")) != 0) {
            type = "unknown";
        }
        if (this.a.isPub()) {
            str = "pub";
        } else {
            str = type;
        }
        if (str.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
            eVar.w.setVisibility(0);
            eVar.w.setOnClickListener(new c(this, this.a));
            eVar.x.setAsyncCacheImage(this.a.getImageThumbUrl(), R.drawable.likelist_defaut_bg);
            a(eVar.w, this.a.getImageWidth(), this.a.getImageHeight());
        } else if (str.equals("gif")) {
            eVar.z.setVisibility(0);
            eVar.w.setVisibility(0);
            eVar.w.setOnClickListener(new c(this, this.a));
            eVar.x.setAsyncCacheImage(this.a.getGifThumbUrl(), R.drawable.likelist_defaut_bg);
            a(eVar.w, this.a.getGifWidth(), this.a.getGifHeight());
        } else if (str.equals("audio")) {
            eVar.n.setVisibility(0);
            eVar.n.c();
            eVar.n.setPlayPath(this.a.getAudioUrl());
            eVar.n.setAudioTime("" + this.a.getAudioDuration());
            if (ac.a(this.e).c()) {
                type = ac.a(this.e).m();
                if (type == null || !type.equals(this.a.getVoiceuri())) {
                    eVar.n.c();
                } else {
                    eVar.n.d();
                }
            } else {
                eVar.n.c();
            }
        } else if (str.equals("video")) {
            eVar.w.setVisibility(0);
            eVar.w.setOnClickListener(new c(this, this.a));
            eVar.y.setVisibility(0);
            eVar.x.setAsyncCacheImage(this.a.getVideoThumbnail(), R.drawable.likelist_defaut_bg);
            a(eVar.w, this.a.getImageWidth(), this.a.getImageHeight());
        } else if (str.equals("pub")) {
            eVar.B.setVisibility(0);
            eVar.C.setText(this.a.getmVideoTime() + "\" 神配音");
            eVar.B.setOnClickListener(new d(this, this.a));
        }
        VoteData voteData = this.a.getVoteData();
        if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
            eVar.A.setVisibility(8);
        } else {
            eVar.A.a();
            eVar.A.setVisibility(0);
            eVar.A.setVoteData(voteData);
        }
        if (this.a.getHotNp() == null || this.a.getHotNp().equals("null")) {
            eVar.t.setVisibility(8);
        } else {
            eVar.t.setVisibility(0);
            eVar.u.setVisibility(0);
            eVar.v.setVisibility(8);
            eVar.t.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    if (this.b.j != null) {
                        this.b.j.onClick(view);
                        eVar.u.setVisibility(8);
                        eVar.v.setVisibility(0);
                    }
                }
            });
        }
        eVar.s.setVisibility(8);
        eVar.D.setVisibility(8);
        eVar.E.setVisibility(8);
        LayoutParams layoutParams = (LayoutParams) eVar.I.getLayoutParams();
        LayoutParams layoutParams2 = (LayoutParams) eVar.J.getLayoutParams();
        if (this.a.isHotAuthorReplay()) {
            eVar.F.setVisibility(0);
            eVar.G.setVisibility(8);
            eVar.a.setVisibility(8);
            eVar.r.setVisibility(8);
            eVar.m.setVisibility(8);
            if (this.a.isHotAuthorReplayFirst()) {
                eVar.H.setVisibility(0);
                layoutParams.setMargins(layoutParams.leftMargin, an.a(this.e, 10), layoutParams.rightMargin, layoutParams.bottomMargin);
                eVar.I.setLayoutParams(layoutParams);
            } else {
                eVar.H.setVisibility(8);
                layoutParams.setMargins(layoutParams.leftMargin, -an.a(this.e, 5), layoutParams.rightMargin, layoutParams.bottomMargin);
                eVar.I.setLayoutParams(layoutParams);
            }
            layoutParams2.setMargins(an.a(this.e, 21), layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
            eVar.J.setLayoutParams(layoutParams2);
        } else {
            layoutParams.setMargins(layoutParams.leftMargin, an.a(this.e, 10), layoutParams.rightMargin, layoutParams.bottomMargin);
            eVar.I.setLayoutParams(layoutParams);
            layoutParams2.setMargins(an.a(this.e, 12), layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
            eVar.J.setLayoutParams(layoutParams2);
            eVar.F.setVisibility(8);
            eVar.G.setVisibility(0);
            eVar.a.setVisibility(0);
            eVar.H.setVisibility(8);
            eVar.r.setVisibility(0);
            eVar.m.setVisibility(0);
            eVar.b.setText(this.a.getUname());
            if (TextUtils.isEmpty(this.a.getIs_vip()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(this.a.getIs_vip())) {
                eVar.b.setTextColor(this.e.getResources().getColor(j.F));
                eVar.c.setVisibility(8);
            } else {
                eVar.b.setTextColor(this.e.getResources().getColor(j.H));
                try {
                    eVar.c.setVisibility(0);
                    Drawable gifDrawable = new GifDrawable(this.e.getResources(), j.I);
                    eVar.c.setImageDrawable(gifDrawable);
                    gifDrawable.start();
                } catch (NotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            if ("m".equals(this.a.getSex())) {
                eVar.e.setBackgroundResource(j.bs);
            } else {
                eVar.e.setBackgroundResource(j.br);
            }
            type = this.a.getProfile();
            eVar.a.setVisibility(0);
            eVar.a.setPostAvatarImage(type);
            Bundle bundle = new Bundle();
            bundle.putString(PersonalProfileActivity.c, this.a.getUid());
            bundle.putBoolean(PersonalProfileActivity.f, true);
            eVar.a.setOnClickListener(this.h.a(7, bundle));
            eVar.b.setOnClickListener(this.h.a(7, bundle));
            view.setTag(R.layout.cmt_pop_item, this.a);
            i2 = 0;
            charSequence = "0";
            try {
                i2 = Integer.parseInt(this.a.getCmtLikeCount());
            } catch (NumberFormatException e4) {
            }
            if (i2 < 1000) {
                charSequence = "" + i2;
                eVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like1_bg));
            } else if (i2 < 10000) {
                charSequence = "" + this.c.format(((double) i2) / 1000.0d) + "k";
                eVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like2_bg));
            } else if (i2 < 100000) {
                charSequence = "" + this.b.format(((double) i2) / 1000.0d) + "k";
                eVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like3_bg));
            } else if (i2 < 1000000) {
                charSequence = "" + (i2 / 1000) + "k";
                eVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
            } else if (1000000 < i2 && i2 < 10000000) {
                charSequence = "" + this.c.format(((double) i2) / 1000000.0d) + "m";
                eVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
            } else if (i2 > 10000000 && i2 < 100000000) {
                charSequence = "" + this.b.format(((double) i2) / 1000000.0d) + "m";
                eVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
            } else if (100000000 < i2) {
                charSequence = "" + (i2 / 1000000) + "m";
                eVar.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
            }
            eVar.r.setText(charSequence);
        }
        if (this.a.isTagIsShow()) {
            eVar.q.setVisibility(0);
            eVar.f.setBackgroundResource(j.r);
            eVar.m.setVisibility(8);
            eVar.H.setVisibility(8);
            if (this.a.isIshot()) {
                eVar.f.setText(R.string.hot_comment);
            } else {
                eVar.f.setText(R.string.new_comment);
            }
            eVar.q.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            });
            if (ai.a(this.e) == 1) {
                eVar.q.setPadding(0, 0, 0, 0);
            } else {
                eVar.q.setPadding(an.a(this.e, 1), 0, an.a(this.e, 1), 0);
            }
        } else {
            eVar.q.setVisibility(8);
        }
        String dingCount = this.a.getDingCount();
        String caiCount = this.a.getCaiCount();
        int i3 = 0;
        try {
            i2 = Integer.parseInt(dingCount);
            try {
                i3 = Integer.parseInt(caiCount);
            } catch (NumberFormatException e5) {
                e = e5;
                e.printStackTrace();
                eVar.g.setText(i2 <= 0 ? i2 + "" : "");
                eVar.j.setText(i3 <= 0 ? i3 + "" : "");
                type = this.a.getDingOrCai();
                if (!"like".equals(type)) {
                    eVar.i.setSelected(true);
                    eVar.g.setSelected(true);
                    eVar.l.setSelected(false);
                    eVar.j.setSelected(false);
                } else if ("hate".equals(type)) {
                    eVar.i.setSelected(false);
                    eVar.g.setSelected(false);
                    eVar.l.setSelected(false);
                    eVar.j.setSelected(false);
                } else {
                    eVar.i.setSelected(false);
                    eVar.g.setSelected(false);
                    eVar.l.setSelected(true);
                    eVar.j.setSelected(true);
                }
                eVar.h.setTag(Integer.valueOf(i));
                eVar.h.setOnClickListener(new b(this, this.a, view));
                eVar.k.setTag(Integer.valueOf(i));
                eVar.k.setOnClickListener(new a(this, this.a, view));
                charSequence = "";
                if (this.a.getMpreName().length() > 0) {
                    eVar.o.setVisibility(8);
                } else {
                    charSequence = "//@" + this.a.getMpreName() + ":";
                    if (!TextUtils.isEmpty(this.a.getMpreContent())) {
                        charSequence = charSequence + this.a.getMpreContent();
                    }
                    eVar.o.setVisibility(0);
                    ParseTagTextView.a(this.e.getApplicationContext(), eVar.o, charSequence);
                }
                if (!TextUtils.isEmpty(this.a.getVoiceuri())) {
                    if (TextUtils.isEmpty(this.a.getContent())) {
                        eVar.o.setVisibility(0);
                        ParseTagTextView.a(this.e.getApplicationContext(), eVar.o, charSequence + " " + this.a.getContent());
                    } else if (TextUtils.isEmpty(charSequence)) {
                        eVar.o.setVisibility(8);
                    }
                }
                if (this.a.getState() != -1) {
                    eVar.p.setVisibility(0);
                    eVar.d.setVisibility(8);
                } else {
                    eVar.p.setVisibility(8);
                    charSequence = this.a.getContent();
                    if (charSequence != null) {
                    }
                    eVar.d.setVisibility(8);
                }
                return view;
            }
        } catch (NumberFormatException e6) {
            NumberFormatException numberFormatException = e6;
            i2 = 0;
            e = numberFormatException;
            e.printStackTrace();
            if (i2 <= 0) {
            }
            eVar.g.setText(i2 <= 0 ? i2 + "" : "");
            if (i3 <= 0) {
            }
            eVar.j.setText(i3 <= 0 ? i3 + "" : "");
            type = this.a.getDingOrCai();
            if (!"like".equals(type)) {
                eVar.i.setSelected(true);
                eVar.g.setSelected(true);
                eVar.l.setSelected(false);
                eVar.j.setSelected(false);
            } else if ("hate".equals(type)) {
                eVar.i.setSelected(false);
                eVar.g.setSelected(false);
                eVar.l.setSelected(true);
                eVar.j.setSelected(true);
            } else {
                eVar.i.setSelected(false);
                eVar.g.setSelected(false);
                eVar.l.setSelected(false);
                eVar.j.setSelected(false);
            }
            eVar.h.setTag(Integer.valueOf(i));
            eVar.h.setOnClickListener(new b(this, this.a, view));
            eVar.k.setTag(Integer.valueOf(i));
            eVar.k.setOnClickListener(new a(this, this.a, view));
            charSequence = "";
            if (this.a.getMpreName().length() > 0) {
                charSequence = "//@" + this.a.getMpreName() + ":";
                if (TextUtils.isEmpty(this.a.getMpreContent())) {
                    charSequence = charSequence + this.a.getMpreContent();
                }
                eVar.o.setVisibility(0);
                ParseTagTextView.a(this.e.getApplicationContext(), eVar.o, charSequence);
            } else {
                eVar.o.setVisibility(8);
            }
            if (TextUtils.isEmpty(this.a.getVoiceuri())) {
                if (TextUtils.isEmpty(this.a.getContent())) {
                    eVar.o.setVisibility(0);
                    ParseTagTextView.a(this.e.getApplicationContext(), eVar.o, charSequence + " " + this.a.getContent());
                } else if (TextUtils.isEmpty(charSequence)) {
                    eVar.o.setVisibility(8);
                }
            }
            if (this.a.getState() != -1) {
                eVar.p.setVisibility(8);
                charSequence = this.a.getContent();
                if (charSequence != null) {
                }
                eVar.d.setVisibility(8);
            } else {
                eVar.p.setVisibility(0);
                eVar.d.setVisibility(8);
            }
            return view;
        }
        if (i2 <= 0) {
        }
        eVar.g.setText(i2 <= 0 ? i2 + "" : "");
        if (i3 <= 0) {
        }
        eVar.j.setText(i3 <= 0 ? i3 + "" : "");
        type = this.a.getDingOrCai();
        if (!"like".equals(type)) {
            eVar.i.setSelected(true);
            eVar.g.setSelected(true);
            eVar.l.setSelected(false);
            eVar.j.setSelected(false);
        } else if ("hate".equals(type)) {
            eVar.i.setSelected(false);
            eVar.g.setSelected(false);
            eVar.l.setSelected(true);
            eVar.j.setSelected(true);
        } else {
            eVar.i.setSelected(false);
            eVar.g.setSelected(false);
            eVar.l.setSelected(false);
            eVar.j.setSelected(false);
        }
        eVar.h.setTag(Integer.valueOf(i));
        eVar.h.setOnClickListener(new b(this, this.a, view));
        eVar.k.setTag(Integer.valueOf(i));
        eVar.k.setOnClickListener(new a(this, this.a, view));
        charSequence = "";
        if (this.a.getMpreName().length() > 0) {
            charSequence = "//@" + this.a.getMpreName() + ":";
            if (TextUtils.isEmpty(this.a.getMpreContent())) {
                charSequence = charSequence + this.a.getMpreContent();
            }
            eVar.o.setVisibility(0);
            ParseTagTextView.a(this.e.getApplicationContext(), eVar.o, charSequence);
        } else {
            eVar.o.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.a.getVoiceuri())) {
            if (TextUtils.isEmpty(this.a.getContent())) {
                eVar.o.setVisibility(0);
                ParseTagTextView.a(this.e.getApplicationContext(), eVar.o, charSequence + " " + this.a.getContent());
            } else if (TextUtils.isEmpty(charSequence)) {
                eVar.o.setVisibility(8);
            }
        }
        if (this.a.getState() != -1) {
            eVar.p.setVisibility(8);
            charSequence = this.a.getContent();
            if (charSequence != null || "".equals(charSequence) || str.equals("audio")) {
                eVar.d.setVisibility(8);
            } else {
                eVar.d.setVisibility(0);
                eVar.d.setText(charSequence);
            }
        } else {
            eVar.p.setVisibility(0);
            eVar.d.setVisibility(8);
        }
        return view;
    }

    private void a(RelativeLayout relativeLayout, int i, int i2) {
        try {
            int a = an.a(this.e, 119);
            int i3 = (i * a) / i2;
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            layoutParams.width = i3;
            layoutParams.height = a;
            relativeLayout.setLayoutParams(layoutParams);
        } catch (Exception e) {
            MobclickAgent.onEvent(this.e, "cacheException", "FullScreenVideoActivity onResume run:" + e.getLocalizedMessage());
            e.printStackTrace();
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
            this.i.a(id, "like");
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
            this.i.a(id, "hate");
        }
    }
}
