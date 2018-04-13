package com.budejie.www.activity;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.bean.Fans;
import com.budejie.www.c.g;
import com.budejie.www.g.b;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.aa;
import com.budejie.www.util.j;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import pl.droidsonroids.gif.GifDrawable;

public class a extends BaseAdapter {
    private Activity a;
    private ArrayList<Fans> b;
    private b c;
    private Toast d;
    private g e;
    private boolean f;
    private OnClickListener g = new a$1(this);
    private OnClickListener h = new a$2(this);

    public a(Activity activity) {
        this.a = activity;
        this.b = new ArrayList();
        this.c = new b(activity);
        this.e = new g(activity);
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(ArrayList<Fans> arrayList) {
        if (this.b != null) {
            this.b.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    public void a() {
        if (this.b != null) {
            this.b.clear();
        }
    }

    public int getCount() {
        return this.b.isEmpty() ? 0 : this.b.size();
    }

    public Object getItem(int i) {
        return this.b.isEmpty() ? Integer.valueOf(0) : (Serializable) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a$a a_a;
        aa.b("FansListAdapter", "getView , index = " + i + " , view = " + view);
        if (view == null) {
            view = this.a.getLayoutInflater().inflate(R.layout.fans_item_layout, null);
            a$a a_a2 = new a$a(this);
            a_a2.b = (TextView) view.findViewById(R.id.nickname);
            a_a2.c = (AsyncImageView) view.findViewById(R.id.iv_members_mark);
            a_a2.a = (AsyncImageView) view.findViewById(R.id.writerProfile);
            a_a2.f = (TextView) view.findViewById(R.id.pro);
            a_a2.d = (TextView) view.findViewById(R.id.add_btn);
            a_a2.e = (TextView) view.findViewById(R.id.cancel_btn);
            a_a2.g = (RelativeLayout) view.findViewById(R.id.bt_layout);
            a_a2.h = (TextView) view.findViewById(R.id.unread_num);
            a_a2.i = (TextView) view.findViewById(R.id.time);
            if (this.f) {
                a_a2.h.setVisibility(0);
                a_a2.i.setVisibility(0);
                a_a2.c.setVisibility(8);
                a_a2.g.setVisibility(8);
                a_a2.d.setVisibility(8);
                a_a2.e.setVisibility(8);
            } else {
                a_a2.h.setVisibility(8);
                a_a2.i.setVisibility(8);
                a_a2.c.setVisibility(0);
                a_a2.g.setVisibility(0);
                a_a2.d.setVisibility(0);
                a_a2.e.setVisibility(0);
            }
            view.setTag(a_a2);
            a_a = a_a2;
        } else {
            a_a = (a$a) view.getTag();
        }
        a(i, a_a);
        return view;
    }

    private void a(int i, a$a a_a) {
        Fans fans = (Fans) this.b.get(i);
        if (fans != null) {
            a_a.b.setText(fans.getUsername());
            a_a.a.setAsyncCacheImage(fans.getUserPic(), R.drawable.head_portrait);
            if (this.f) {
                if (fans.unread > 0) {
                    a_a.h.setVisibility(0);
                    a_a.h.setText(fans.unread > 99 ? "N" : fans.unread + "");
                } else {
                    a_a.h.setVisibility(8);
                }
                a_a.i.setText(fans.topic.passtime);
                if (TextUtils.isEmpty(fans.topic.text)) {
                    a_a.f.setVisibility(8);
                    return;
                }
                a_a.f.setText(fans.topic.text);
                a_a.f.setVisibility(0);
                return;
            }
            a_a.c.setOnClickListener(this.g);
            if (fans.is_vip) {
                try {
                    a_a.b.setTextColor(this.a.getResources().getColor(j.H));
                    a_a.b.setTypeface(Typeface.defaultFromStyle(1));
                    a_a.c.setVisibility(0);
                    Drawable gifDrawable = new GifDrawable(this.a.getResources(), j.I);
                    a_a.c.setImageDrawable(gifDrawable);
                    gifDrawable.start();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } else {
                try {
                    a_a.b.setTextColor(this.a.getResources().getColor(j.G));
                    a_a.b.setTypeface(Typeface.defaultFromStyle(0));
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                a_a.c.setVisibility(8);
            }
            a_a.e.setOnClickListener(this.h);
            a_a.e.setTag(fans);
            a_a.d.setOnClickListener(this.h);
            a_a.d.setTag(fans);
            CharSequence introduction = fans.getIntroduction();
            if (TextUtils.isEmpty(introduction)) {
                a_a.f.setVisibility(8);
            } else {
                a_a.f.setText(introduction);
                a_a.f.setVisibility(0);
            }
            String relationship = fans.getRelationship();
            if (relationship.equals("2") || relationship.equals("4")) {
                a_a.e.setVisibility(0);
                a_a.d.setVisibility(8);
            } else if (relationship.equals("0") || relationship.equals("3")) {
                a_a.e.setVisibility(8);
                a_a.d.setVisibility(0);
            } else {
                a_a.e.setVisibility(8);
                a_a.d.setVisibility(8);
            }
        }
    }

    private void a(Fans fans) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().d(this.a, fans.getId()), new a$3(this, fans));
    }

    private void b(Fans fans) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().e(this.a, fans.getId()), new a$4(this, fans));
    }

    public boolean isEmpty() {
        return this.b == null ? true : this.b.isEmpty();
    }
}
