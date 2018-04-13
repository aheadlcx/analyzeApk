package com.budejie.www.adapter.a;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.RoundAsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.MyMsgItem;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import java.util.ArrayList;
import java.util.List;

public class d extends BaseAdapter implements OnClickListener {
    protected Toast a;
    private Activity b;
    private List<MyMsgItem> c = new ArrayList();
    private g d;

    public d(Activity activity) {
        this.b = activity;
        this.d = new g(activity);
    }

    public void a(List<MyMsgItem> list) {
        try {
            if (!(this.c == null || list == null)) {
                this.c.addAll(list);
            }
            notifyDataSetChanged();
        } catch (Exception e) {
        }
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
        return this.c.isEmpty() ? Integer.valueOf(0) : this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        d$a d_a;
        if (view == null) {
            view = this.b.getLayoutInflater().inflate(R.layout.ding_topic_item_layout, null);
            d_a = new d$a(this);
            d_a.b = (TextView) view.findViewById(R.id.nickname);
            d_a.a = (RoundAsyncImageView) view.findViewById(R.id.writerProfile);
            d_a.c = (TextView) view.findViewById(R.id.fans_count);
            d_a.d = (TextView) view.findViewById(R.id.tiezi_count);
            d_a.f = (TextView) view.findViewById(R.id.cancel_btn);
            d_a.e = (TextView) view.findViewById(R.id.add_btn);
            view.setTag(d_a);
        } else {
            d_a = (d$a) view.getTag();
        }
        MyMsgItem myMsgItem = (MyMsgItem) this.c.get(i);
        a(myMsgItem, d_a);
        b(myMsgItem, d_a);
        return view;
    }

    private void a(MyMsgItem myMsgItem, d$a d_a) {
        if (myMsgItem != null) {
            d_a.f.setOnClickListener(this);
            d_a.f.setTag(myMsgItem);
            d_a.e.setOnClickListener(this);
            d_a.e.setTag(myMsgItem);
            String is_follow = myMsgItem.getIs_follow();
            if ("1".equals(is_follow)) {
                d_a.f.setVisibility(0);
                d_a.e.setVisibility(8);
            } else if ("0".equals(is_follow)) {
                d_a.f.setVisibility(8);
                d_a.e.setVisibility(0);
            }
            d_a.b.setText(myMsgItem.getUsername());
            d_a.a.setAsyncCacheImage(myMsgItem.getProfile_image(), "f".equals(myMsgItem.getSex()) ? R.color.head_portrait_female_round : R.color.head_portrait_male_round);
        }
    }

    private void b(MyMsgItem myMsgItem, d$a d_a) {
        a(d_a.c, R.string.fans_count, myMsgItem.getFans_count());
        a(d_a.d, R.string.posts_count, myMsgItem.getTiezi_count());
    }

    private void a(TextView textView, int i, String str) {
        textView.setText(this.b.getString(i, new Object[]{str}));
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
            MyMsgItem myMsgItem = (MyMsgItem) view.getTag();
            String is_follow = myMsgItem.getIs_follow();
            if ("1".equals(is_follow)) {
                a(myMsgItem);
            } else if ("0".equals(is_follow)) {
                b(myMsgItem);
            }
        }
    }

    private void a(MyMsgItem myMsgItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().e(this.b, myMsgItem.getUserid() + ""), new d$1(this, myMsgItem));
    }

    private void b(MyMsgItem myMsgItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().d(this.b, myMsgItem.getUserid() + ""), new d$2(this, myMsgItem));
    }
}
