package com.budejie.www.activity.mycomment;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.e;
import com.budejie.www.http.c;
import com.budejie.www.widget.CmtPopupWindow;
import com.budejie.www.widget.parsetagview.ParseTagTextView;

public class g extends a {
    private h a;
    protected final d b;
    protected final ListItemObject c;
    protected final Activity d;
    protected final LayoutInflater e;
    protected final e.a f;
    protected final int g;
    protected c h;
    private e i;

    public /* synthetic */ Object d() {
        return a();
    }

    public g(Activity activity, e.a aVar, d dVar, int i, CmtPopupWindow cmtPopupWindow) {
        this.b = dVar;
        this.c = dVar.c;
        this.d = activity;
        this.f = aVar;
        this.e = LayoutInflater.from(activity);
        this.g = i;
        this.h = new c(activity, null);
        this.i = new e(activity);
    }

    public View b() {
        h hVar = new h();
        ViewGroup viewGroup = (ViewGroup) this.e.inflate(R.layout.new_mycomment_list_item_text, null);
        hVar.j = (LinearLayout) viewGroup.findViewById(R.id.item_layout);
        hVar.k = (AsyncImageView) viewGroup.findViewById(R.id.writerProfile);
        hVar.l = (TextView) viewGroup.findViewById(R.id.writerName);
        hVar.m = (TextView) viewGroup.findViewById(R.id.commentHint);
        hVar.n = viewGroup.findViewById(R.id.writerProfile_ready);
        hVar.o = (LinearLayout) viewGroup.findViewById(R.id.name_time_layout);
        hVar.q = (AudioLayout) viewGroup.findViewById(R.id.mycomment_content_voice);
        hVar.p = (TextView) viewGroup.findViewById(R.id.mycomment_content_textview);
        hVar.r = (LinearLayout) viewGroup.findViewById(R.id.mycomment_content_layout);
        hVar.s = (LinearLayout) viewGroup.findViewById(R.id.mycomment_reply_content_layout);
        hVar.u = (TextView) viewGroup.findViewById(R.id.mycomment_reply_content_textview);
        hVar.v = (AudioLayout) viewGroup.findViewById(R.id.mycomment_reply_content_voice);
        hVar.w = (TextView) viewGroup.findViewById(R.id.mycomment_username_textview);
        hVar.x = (TextView) viewGroup.findViewById(R.id.mycomment_reply_username_textview);
        hVar.t = (TextView) viewGroup.findViewById(R.id.content);
        viewGroup.setTag(hVar);
        return viewGroup;
    }

    public int c() {
        return RowType.COMMENT_TXT_ROW.ordinal();
    }

    public void a(b bVar) {
        this.a = (h) bVar;
        MyCommentInfo myCommentInfo = this.b.a;
        MyCommentInfo myCommentInfo2 = this.b.b;
        ListItemObject listItemObject = this.b.c;
        this.a.l.setText(listItemObject.getName());
        this.a.m.setText(listItemObject.getPasstime().substring(0, 16));
        this.a.k.setAsyncCacheImage(listItemObject.getProfile(), R.drawable.head_portrait);
        this.a.w.setVisibility(0);
        this.a.w.setText(myCommentInfo.user.username + "：");
        if (TextUtils.isEmpty(myCommentInfo.voiceuri)) {
            this.a.q.setVisibility(8);
            this.a.p.setVisibility(0);
            this.a.p.setText(myCommentInfo.content);
            if (myCommentInfo2 == null) {
                this.a.s.setVisibility(8);
                this.a.w.setVisibility(8);
                this.a.p.setText(myCommentInfo.user.username + "：" + myCommentInfo.content);
            } else if (!TextUtils.isEmpty(myCommentInfo2.content)) {
                this.a.w.setVisibility(8);
                this.a.s.setVisibility(8);
                this.a.p.setText(myCommentInfo.user.username + "：" + myCommentInfo.content + "//@" + myCommentInfo2.user.username + "：" + myCommentInfo2.content);
            } else if (TextUtils.isEmpty(myCommentInfo2.voiceuri)) {
                this.a.s.setVisibility(8);
            } else {
                this.a.s.setVisibility(0);
                this.a.v.setVisibility(0);
                this.a.u.setVisibility(8);
                this.a.x.setText("//@" + myCommentInfo2.user.username + "：");
                this.a.v.setPlayPath(myCommentInfo2.voiceuri);
                this.a.v.setAudioTime(myCommentInfo2.voicetime);
            }
        } else {
            this.a.q.setVisibility(0);
            this.a.q.setPlayPath(myCommentInfo.voiceuri);
            this.a.q.setAudioTime(myCommentInfo.voicetime);
            this.a.p.setVisibility(8);
            if (myCommentInfo2 == null) {
                this.a.s.setVisibility(8);
            } else if (!TextUtils.isEmpty(myCommentInfo2.content)) {
                this.a.s.setVisibility(0);
                this.a.v.setVisibility(8);
                this.a.u.setVisibility(0);
                this.a.x.setText("//@" + myCommentInfo2.user.username + "：");
                this.a.u.setText(myCommentInfo2.content);
            } else if (TextUtils.isEmpty(myCommentInfo2.voiceuri)) {
                this.a.s.setVisibility(8);
            } else {
                this.a.s.setVisibility(0);
                this.a.v.setVisibility(0);
                this.a.u.setVisibility(8);
                this.a.x.setText("//@" + myCommentInfo2.user.username + "：");
                this.a.v.setPlayPath(myCommentInfo2.voiceuri);
                this.a.v.setAudioTime(myCommentInfo2.voicetime);
            }
        }
        this.a.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f.a(view, this.a.b);
            }
        });
        this.a.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f.c(view, this.a.b);
            }
        });
        ParseTagTextView.a(this.d, this.a.t, this.c.getContent());
        this.a.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f.a(view, this.a.b);
            }
        });
        this.a.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f.b(view, this.a.b);
            }
        });
        this.a.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f.b(view, this.a.b);
            }
        });
    }

    public d a() {
        return this.b;
    }
}
