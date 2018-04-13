package com.budejie.www.activity.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.adapter.e.a;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.i;
import com.budejie.www.util.j;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import pl.droidsonroids.gif.GifDrawable;

public class RecommendView extends RelativeLayout implements OnClickListener {
    public static List<SuggestedFollowsListItem> a = new ArrayList();
    a b;
    private SuggestedFollowsListItem c = null;
    private int d = 100;
    private int e = 5;
    private Context f;
    private View g;
    private AsyncImageView h;
    private LinearLayout i;
    private LinearLayout j;
    private TextView k;
    private AsyncImageView l;
    private TextView m;
    private TextView n;
    private ProgressBar o;
    private Handler p = new RecommendView$1(this);

    public RecommendView(Context context) {
        super(context);
        this.f = context;
        b();
    }

    public RecommendView(Context context, List<SuggestedFollowsListItem> list) {
        super(context);
        this.f = context;
        a((List) list);
    }

    private void a(List<SuggestedFollowsListItem> list) {
        b();
        if (list != null && !list.isEmpty()) {
            setFollowsItemList(list);
        }
    }

    public RecommendView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context;
        b();
    }

    public RecommendView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = context;
        b();
    }

    @SuppressLint({"InflateParams"})
    private void b() {
        this.g = LayoutInflater.from(this.f).inflate(R.layout.recommend_layout, null);
        this.g.setOnClickListener(this);
        this.h = (AsyncImageView) this.g.findViewById(R.id.recommend_user_avatar);
        this.i = (LinearLayout) this.g.findViewById(R.id.recommend_follow);
        this.j = (LinearLayout) this.g.findViewById(R.id.recommend_followed);
        this.o = (ProgressBar) this.g.findViewById(R.id.recommend_follow_loading);
        this.k = (TextView) this.g.findViewById(R.id.recommend_nickname);
        this.l = (AsyncImageView) this.g.findViewById(R.id.iv_members_mark);
        this.m = (TextView) this.g.findViewById(R.id.recommend_fans_count);
        this.n = (TextView) this.g.findViewById(R.id.recommend_fans_extra);
        this.i.setOnClickListener(this);
        this.l.setOnClickListener(this);
        addView(this.g);
        as.b().a().add(new WeakReference(this.p));
    }

    public void setPre(int i) {
        this.d = i;
    }

    public void setFollowUpperLimit(int i) {
        this.e = i;
    }

    public void setRowClickHandler(a aVar) {
        this.b = aVar;
    }

    public void setFollowsItem(SuggestedFollowsListItem suggestedFollowsListItem) {
        this.c = suggestedFollowsListItem;
        this.h.setAsyncCacheImage(suggestedFollowsListItem.header, R.drawable.head_portrait);
        this.k.setText(suggestedFollowsListItem.screen_name);
        if (suggestedFollowsListItem.is_vip) {
            this.k.setTextColor(this.f.getResources().getColor(j.H));
            try {
                this.l.setVisibility(0);
                Drawable gifDrawable = new GifDrawable(this.f.getResources(), j.I);
                this.l.setImageDrawable(gifDrawable);
                gifDrawable.start();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
            this.k.setTextColor(this.f.getResources().getColor(j.F));
            this.l.setVisibility(8);
        }
        TextView textView = this.m;
        Context context = this.f;
        Object[] objArr = new Object[1];
        objArr[0] = !TextUtils.isEmpty(suggestedFollowsListItem.tiezi_count) ? suggestedFollowsListItem.tiezi_count : Integer.valueOf(0);
        textView.setText(context.getString(R.string.posts_count, objArr));
        this.n.setText(this.f.getString(R.string.follows_count, new Object[]{suggestedFollowsListItem.fans_count}));
        if (suggestedFollowsListItem.is_follow == 0) {
            this.i.setVisibility(0);
            this.j.setVisibility(8);
            return;
        }
        this.i.setVisibility(8);
        this.j.setVisibility(0);
    }

    public void setFollowsItemList(List<SuggestedFollowsListItem> list) {
        a = list;
        setFollowsItem((SuggestedFollowsListItem) a.remove(0));
    }

    public SuggestedFollowsListItem getCurFollowsItem() {
        return this.c;
    }

    public void a(int i) {
        if (i == 0) {
            this.i.setVisibility(0);
            this.j.setVisibility(8);
            return;
        }
        this.i.setVisibility(8);
        this.j.setVisibility(0);
    }

    private void c() {
        if (a.size() != 0) {
            setFollowsItem((SuggestedFollowsListItem) a.remove(0));
        }
    }

    public void a(View view) {
        Animation translateAnimation = new TranslateAnimation((float) i.a().a(this.f), 0.0f, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        translateAnimation.setDuration(350);
        translateAnimation.setStartOffset(150);
        translateAnimation.setAnimationListener(new RecommendView$2(this, view));
        view.startAnimation(translateAnimation);
    }

    public void b(View view) {
        Animation translateAnimation = new TranslateAnimation(0.0f, (float) (-i.a().a(this.f)), 0.0f, 0.0f);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        translateAnimation.setDuration(350);
        translateAnimation.setAnimationListener(new RecommendView$3(this, view));
        view.startAnimation(translateAnimation);
    }

    public void a() {
        int i = this.e - 1;
        this.e = i;
        if (i > 0) {
            b(this.g);
        }
    }

    public void onClick(View view) {
        if (view == this.g) {
            if (this.c == null) {
                an.a((Activity) this.f, this.f.getString(R.string.data_error), -1).show();
                return;
            }
            Intent intent = new Intent(this.f, PersonalProfileActivity.class);
            intent.putExtra(PersonalProfileActivity.c, this.c.uid);
            this.f.startActivity(intent);
        } else if (view != this.i) {
        } else {
            if (!an.a(this.f)) {
                an.a((Activity) this.f, this.f.getString(R.string.nonet), -1).show();
            } else if (this.c != null) {
                aa.b("NewsFeedActivity", "onResume" + an.a(this.f));
                d();
                this.b.a(this.c);
            }
        }
    }

    private void d() {
        this.i.setVisibility(8);
        this.o.setVisibility(0);
    }

    private void b(int i) {
        this.o.setVisibility(8);
        if (i == 0) {
            this.i.setVisibility(0);
        } else {
            this.j.setVisibility(0);
        }
    }
}
