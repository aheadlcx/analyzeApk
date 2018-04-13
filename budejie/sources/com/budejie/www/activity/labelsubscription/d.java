package com.budejie.www.activity.labelsubscription;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.g.b;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.aa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class d extends BaseAdapter {
    private Activity a;
    private ArrayList<RecommendSubscription> b;
    private b c;
    private Toast d;
    private com.budejie.www.c.b e;
    private j f;
    private String g;
    private OnClickListener h = new d$1(this);
    private OnClickListener i = new d$2(this);

    public d(Activity activity) {
        this.a = activity;
        this.e = new com.budejie.www.c.b(activity);
        this.b = new ArrayList();
        this.c = new b(activity);
        this.f = new j();
    }

    public d(Activity activity, String str) {
        this.a = activity;
        this.e = new com.budejie.www.c.b(activity);
        this.b = new ArrayList();
        this.c = new b(activity);
        this.g = str;
    }

    public void a(List<RecommendSubscription> list) {
        if (this.b != null) {
            this.b.clear();
            this.b.addAll(list);
        }
        notifyDataSetChanged();
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
        d$a d_a;
        aa.b("SubscribedListAdapter", "getView , index = " + i + " , view = " + view);
        if (view == null) {
            view = this.a.getLayoutInflater().inflate(R.layout.subscription_item_layout, null);
            d$a d_a2 = new d$a(this);
            d_a2.b = (TextView) view.findViewById(R.id.label_name);
            d_a2.a = (AsyncImageView) view.findViewById(R.id.label_icon);
            d_a2.d = (TextView) view.findViewById(R.id.label_subscribe_count);
            d_a2.c = (TextView) view.findViewById(R.id.enter_btn);
            d_a2.e = (TextView) view.findViewById(R.id.add_btn);
            d_a2.f = (TextView) view.findViewById(R.id.default_btn);
            d_a2.g = view.findViewById(R.id.bt_layout);
            view.setTag(d_a2);
            d_a = d_a2;
        } else {
            d_a = (d$a) view.getTag();
        }
        a(i, d_a);
        return view;
    }

    private void a(int i, d$a d_a) {
        RecommendSubscription recommendSubscription = (RecommendSubscription) this.b.get(i);
        if (recommendSubscription != null) {
            d_a.c.setOnClickListener(this.i);
            d_a.c.setTag(recommendSubscription);
            d_a.e.setOnClickListener(this.h);
            d_a.e.setTag(recommendSubscription);
            d_a.b.setText(recommendSubscription.getTheme_name());
            if (Integer.parseInt(recommendSubscription.getSub_number()) < 10000) {
                d_a.d.setText(recommendSubscription.getSub_number() + "人订阅");
            } else if (Integer.parseInt(recommendSubscription.getSub_number()) % 10000 >= 1000) {
                d_a.d.setText((Integer.parseInt(recommendSubscription.getSub_number()) / 10000) + "." + ((Integer.parseInt(recommendSubscription.getSub_number()) % 10000) / 1000) + "万人订阅");
            } else {
                d_a.d.setText((Integer.parseInt(recommendSubscription.getSub_number()) / 10000) + "万人订阅");
            }
            d_a.a.setAsyncCacheImage(recommendSubscription.getImage_list(), R.drawable.label_default_icon);
            if ("#".equals(this.g)) {
                d_a.g.setVisibility(8);
                return;
            }
            String is_sub = recommendSubscription.getIs_sub();
            if ("1".equals(recommendSubscription.getIs_default())) {
                d_a.f.setVisibility(0);
                d_a.c.setVisibility(8);
                d_a.e.setVisibility(8);
            } else if ("1".equals(is_sub)) {
                d_a.c.setVisibility(8);
                d_a.e.setVisibility(8);
                d_a.f.setVisibility(8);
            } else if ("0".equals(is_sub)) {
                d_a.f.setVisibility(8);
                d_a.c.setVisibility(8);
                d_a.e.setVisibility(0);
            }
            d_a.g.setVisibility(0);
        }
    }

    private void a(RecommendSubscription recommendSubscription) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.f.a(this.a, recommendSubscription.getTheme_id(), false), new d$3(this, recommendSubscription));
    }

    private void b(RecommendSubscription recommendSubscription) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.f.a(this.a, recommendSubscription.getTheme_id(), true), new d$4(this, recommendSubscription));
    }

    public boolean isEmpty() {
        return this.b == null ? true : this.b.isEmpty();
    }
}
