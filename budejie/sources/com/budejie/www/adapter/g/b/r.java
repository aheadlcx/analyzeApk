package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.mycomment.MyCommentInfo;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.e;
import com.budejie.www.http.c;
import com.budejie.www.util.an;
import com.budejie.www.util.d;
import com.budejie.www.util.j;
import com.umeng.analytics.MobclickAgent;
import java.io.IOException;
import pl.droidsonroids.gif.GifDrawable;

public class r extends a<ListItemObject> {
    protected c e;
    private TextView f;
    private AsyncImageView g;
    private TextView h;
    private AsyncImageView i;
    private TextView j;
    private ImageButton k;
    private LinearLayout l;
    private TextView m;
    private FrameLayout n;
    private ImageView o;
    private TextView p;
    private FrameLayout q;
    private ImageView r;
    private TextView s;
    private Context t;
    private e u;

    public r(Context context, b bVar) {
        super(context, bVar);
        this.t = context;
        this.e = new c((PersonalProfileActivity) context, null);
        this.u = new e(context);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.list_item_user_info_comment, viewGroup);
        this.f = (TextView) inflate.findViewById(R.id.user_v);
        this.g = (AsyncImageView) inflate.findViewById(R.id.writerProfile);
        this.l = (LinearLayout) inflate.findViewById(R.id.name_time_layout);
        this.h = (TextView) inflate.findViewById(R.id.writerName);
        this.i = (AsyncImageView) inflate.findViewById(R.id.iv_members_mark);
        this.j = (TextView) inflate.findViewById(R.id.addtime);
        this.m = (TextView) inflate.findViewById(R.id.nearby);
        this.k = (ImageButton) inflate.findViewById(R.id.itemMore);
        this.n = (FrameLayout) inflate.findViewById(R.id.commentDingLayout);
        this.o = (ImageView) inflate.findViewById(R.id.commentDingIv);
        this.p = (TextView) inflate.findViewById(R.id.commentLikeCount);
        this.q = (FrameLayout) inflate.findViewById(R.id.commentCaiLayout);
        this.r = (ImageView) inflate.findViewById(R.id.commentCaiIv);
        this.s = (TextView) inflate.findViewById(R.id.commentCaiCount);
        return inflate;
    }

    public void c() {
        boolean parseInt;
        int parseInt2;
        boolean z;
        NumberFormatException numberFormatException;
        String str;
        CharSequence charSequence;
        Object obj;
        this.f.setVisibility(8);
        this.n.setOnClickListener(this);
        this.q.setOnClickListener(this);
        MyCommentInfo hotcmt = ((ListItemObject) this.c).getHotcmt();
        if (hotcmt != null) {
            this.g.setPostAvatarImage(hotcmt.user.profile_image);
            this.h.setText(hotcmt.user.username);
            String str2 = hotcmt.like_count;
            String str3 = hotcmt.hate_count;
            try {
                parseInt = Integer.parseInt(str2);
                try {
                    parseInt2 = Integer.parseInt(str3);
                } catch (NumberFormatException e) {
                    NumberFormatException numberFormatException2 = e;
                    z = parseInt;
                    numberFormatException = numberFormatException2;
                    Log.i("Commend-dingCount1", numberFormatException.toString());
                    parseInt = z;
                    parseInt2 = 0;
                    this.p.setText(r2 <= 0 ? "" : r2 + "");
                    this.s.setText(parseInt2 <= 0 ? "" : parseInt2 + "");
                    str2 = hotcmt.getDingOrCai();
                    if (!"like".equals(str2)) {
                        this.o.setSelected(true);
                        this.p.setSelected(true);
                        this.r.setSelected(false);
                        this.s.setSelected(false);
                    } else if ("hate".equals(str2)) {
                        this.o.setSelected(false);
                        this.p.setSelected(false);
                        this.r.setSelected(true);
                        this.s.setSelected(true);
                    } else {
                        this.o.setSelected(false);
                        this.p.setSelected(false);
                        this.r.setSelected(false);
                        this.s.setSelected(false);
                    }
                    if (!TextUtils.isEmpty(hotcmt.user.is_vip)) {
                    }
                    this.h.setTextColor(this.a.getResources().getColor(j.F));
                    this.i.setVisibility(8);
                    str = "";
                    if (((ListItemObject) this.c).getHotcmt() != null) {
                        charSequence = ((ListItemObject) this.c).getHotcmt().ctime;
                    } else {
                        obj = str;
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        this.j.setText(charSequence);
                    }
                    this.g.setOnClickListener(this);
                    this.l.setOnClickListener(this);
                }
            } catch (NumberFormatException e2) {
                numberFormatException = e2;
                z = false;
                Log.i("Commend-dingCount1", numberFormatException.toString());
                parseInt = z;
                parseInt2 = 0;
                if (r2 <= 0) {
                }
                this.p.setText(r2 <= 0 ? "" : r2 + "");
                if (parseInt2 <= 0) {
                }
                this.s.setText(parseInt2 <= 0 ? "" : parseInt2 + "");
                str2 = hotcmt.getDingOrCai();
                if (!"like".equals(str2)) {
                    this.o.setSelected(true);
                    this.p.setSelected(true);
                    this.r.setSelected(false);
                    this.s.setSelected(false);
                } else if ("hate".equals(str2)) {
                    this.o.setSelected(false);
                    this.p.setSelected(false);
                    this.r.setSelected(false);
                    this.s.setSelected(false);
                } else {
                    this.o.setSelected(false);
                    this.p.setSelected(false);
                    this.r.setSelected(true);
                    this.s.setSelected(true);
                }
                if (TextUtils.isEmpty(hotcmt.user.is_vip)) {
                }
                this.h.setTextColor(this.a.getResources().getColor(j.F));
                this.i.setVisibility(8);
                str = "";
                if (((ListItemObject) this.c).getHotcmt() != null) {
                    obj = str;
                } else {
                    charSequence = ((ListItemObject) this.c).getHotcmt().ctime;
                }
                if (TextUtils.isEmpty(charSequence)) {
                    this.j.setText(charSequence);
                }
                this.g.setOnClickListener(this);
                this.l.setOnClickListener(this);
            }
            if (r2 <= 0) {
            }
            this.p.setText(r2 <= 0 ? "" : r2 + "");
            if (parseInt2 <= 0) {
            }
            this.s.setText(parseInt2 <= 0 ? "" : parseInt2 + "");
            str2 = hotcmt.getDingOrCai();
            if (!"like".equals(str2)) {
                this.o.setSelected(true);
                this.p.setSelected(true);
                this.r.setSelected(false);
                this.s.setSelected(false);
            } else if ("hate".equals(str2)) {
                this.o.setSelected(false);
                this.p.setSelected(false);
                this.r.setSelected(true);
                this.s.setSelected(true);
            } else {
                this.o.setSelected(false);
                this.p.setSelected(false);
                this.r.setSelected(false);
                this.s.setSelected(false);
            }
            if (TextUtils.isEmpty(hotcmt.user.is_vip) || !"True".equalsIgnoreCase(hotcmt.user.is_vip)) {
                this.h.setTextColor(this.a.getResources().getColor(j.F));
                this.i.setVisibility(8);
            } else {
                this.h.setTextColor(this.a.getResources().getColor(j.H));
                try {
                    this.i.setVisibility(0);
                    Drawable gifDrawable = new GifDrawable(this.a.getResources(), j.I);
                    this.i.setImageDrawable(gifDrawable);
                    gifDrawable.start();
                } catch (NotFoundException e3) {
                    e3.printStackTrace();
                } catch (IOException e4) {
                    e4.printStackTrace();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
        str = "";
        if (((ListItemObject) this.c).getHotcmt() != null) {
            charSequence = ((ListItemObject) this.c).getHotcmt().ctime;
        } else {
            obj = str;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.j.setText(charSequence);
        }
        this.g.setOnClickListener(this);
        this.l.setOnClickListener(this);
    }

    private void a(View view) {
        if (an.a(this.t)) {
            MyCommentInfo hotcmt = ((ListItemObject) this.c).getHotcmt();
            if (hotcmt != null && !hotcmt.isAlreadyDingCai()) {
                String str = hotcmt.id;
                d.a(this.t, (View) this.o.getParent(), "1");
                this.o.setSelected(true);
                this.p.setSelected(true);
                int i = 0;
                try {
                    i = Integer.parseInt(hotcmt.like_count);
                } catch (Exception e) {
                    Log.i("Commend-Ding", e.toString());
                }
                i++;
                this.p.setText(String.valueOf(i));
                hotcmt.setDingOrCai("like");
                hotcmt.like_count = i + "";
                this.e.a(str, "like");
                this.u.a(str, "like");
            }
        }
    }

    private void b(View view) {
        if (an.a(this.t)) {
            MyCommentInfo hotcmt = ((ListItemObject) this.c).getHotcmt();
            if (hotcmt != null && !hotcmt.isAlreadyDingCai()) {
                String str = hotcmt.id;
                d.a(this.t, (View) this.r.getParent(), "1");
                this.r.setSelected(true);
                this.s.setSelected(true);
                int i = 0;
                try {
                    i = Integer.parseInt(hotcmt.hate_count);
                } catch (Exception e) {
                    Log.i("Commend-Ding", e.toString());
                }
                i++;
                this.s.setText(String.valueOf(i));
                hotcmt.setDingOrCai("hate");
                hotcmt.hate_count = i + "";
                this.e.a(str, "hate");
                this.u.a(str, "hate");
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commentDingLayout:
                a(view);
                return;
            case R.id.commentCaiLayout:
                b(view);
                return;
            case R.id.itemMore:
                MobclickAgent.onEvent(this.a, "E01_A10", "帖子右上角三个点点击");
                this.b.c.c(view, (ListItemObject) this.c);
                return;
            default:
                return;
        }
    }
}
