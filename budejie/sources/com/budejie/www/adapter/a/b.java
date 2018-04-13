package com.budejie.www.adapter.a;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.RoundAsyncImageView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.HeadPortraitItem;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pl.droidsonroids.gif.GifDrawable;

public class b extends BaseAdapter implements OnClickListener {
    protected Toast a;
    private Activity b;
    private List<HeadPortraitItem> c;
    private g d;
    private OnClickListener e = new b$1(this);

    public b(Activity activity) {
        this.b = activity;
        this.c = new ArrayList();
        this.d = new g(activity);
    }

    public void a(List<HeadPortraitItem> list) {
        if (this.c != null) {
            this.c.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void a() {
        if (this.c != null) {
            this.c.clear();
        }
    }

    public int getCount() {
        return this.c.isEmpty() ? 0 : this.c.size();
    }

    public Object getItem(int i) {
        return this.c.isEmpty() ? Integer.valueOf(0) : (Serializable) this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b$a b_a;
        if (view == null) {
            view = this.b.getLayoutInflater().inflate(R.layout.ding_topic_item_layout, null);
            b_a = new b$a(this);
            b_a.b = (TextView) view.findViewById(R.id.nickname);
            b_a.c = (AsyncImageView) view.findViewById(R.id.iv_members_mark);
            b_a.a = (RoundAsyncImageView) view.findViewById(R.id.writerProfile);
            b_a.d = (TextView) view.findViewById(R.id.fans_count);
            b_a.e = (TextView) view.findViewById(R.id.tiezi_count);
            b_a.g = (TextView) view.findViewById(R.id.cancel_btn);
            b_a.f = (TextView) view.findViewById(R.id.add_btn);
            view.setTag(b_a);
        } else {
            b_a = (b$a) view.getTag();
        }
        HeadPortraitItem headPortraitItem = (HeadPortraitItem) this.c.get(i);
        b(headPortraitItem, b_a);
        a(headPortraitItem, b_a);
        return view;
    }

    private void a(HeadPortraitItem headPortraitItem, b$a b_a) {
        a(b_a.d, R.string.fans_count, headPortraitItem.getFans_count());
        a(b_a.e, R.string.posts_count, headPortraitItem.getTiezi_count());
    }

    private void a(TextView textView, int i, String str) {
        textView.setText(this.b.getString(i, new Object[]{str}));
    }

    private void b(HeadPortraitItem headPortraitItem, b$a b_a) {
        if (headPortraitItem != null) {
            b_a.g.setOnClickListener(this);
            b_a.g.setTag(headPortraitItem);
            b_a.f.setOnClickListener(this);
            b_a.f.setTag(headPortraitItem);
            b_a.c.setOnClickListener(this.e);
            String is_follow = headPortraitItem.getIs_follow();
            if ("1".equals(is_follow)) {
                b_a.g.setVisibility(0);
                b_a.f.setVisibility(8);
            } else if ("0".equals(is_follow)) {
                b_a.g.setVisibility(8);
                b_a.f.setVisibility(0);
            } else {
                b_a.g.setVisibility(8);
                b_a.f.setVisibility(8);
            }
            b_a.b.setText(headPortraitItem.getUsername());
            if (headPortraitItem.is_vip) {
                b_a.b.setTextColor(this.b.getResources().getColor(j.H));
                b_a.b.setTypeface(Typeface.defaultFromStyle(1));
                try {
                    b_a.c.setVisibility(0);
                    Drawable gifDrawable = new GifDrawable(this.b.getResources(), j.I);
                    b_a.c.setImageDrawable(gifDrawable);
                    gifDrawable.start();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } else {
                b_a.b.setTextColor(this.b.getResources().getColor(j.F));
                b_a.b.setTypeface(Typeface.defaultFromStyle(0));
                b_a.c.setVisibility(8);
            }
            b_a.a.setAsyncCacheImage(headPortraitItem.getProfile_image(), "f".equals(headPortraitItem.getSex()) ? R.color.head_portrait_female_round : R.color.head_portrait_male_round);
        }
    }

    public boolean isEmpty() {
        return this.c == null ? true : this.c.isEmpty();
    }

    public void onClick(View view) {
        if (!an.a(this.b)) {
            an.a(this.b, this.b.getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(ai.b(this.b))) {
            an.a(this.b, 0, null, null, 0);
        } else {
            HeadPortraitItem headPortraitItem = (HeadPortraitItem) view.getTag();
            String is_follow = headPortraitItem.getIs_follow();
            if ("1".equals(is_follow)) {
                a(headPortraitItem);
            } else if ("0".equals(is_follow)) {
                b(headPortraitItem);
            }
        }
    }

    private void a(HeadPortraitItem headPortraitItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().e(this.b, headPortraitItem.getUserid()), new b$2(this, headPortraitItem));
    }

    private void b(HeadPortraitItem headPortraitItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().d(this.b, headPortraitItem.getUserid()), new b$3(this, headPortraitItem));
    }
}
