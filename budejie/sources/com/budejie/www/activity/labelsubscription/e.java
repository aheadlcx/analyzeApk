package com.budejie.www.activity.labelsubscription;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
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

public class e extends BaseAdapter {
    private Activity a;
    private ArrayList<RecommendSubscription> b;
    private b c;
    private Toast d;
    private com.budejie.www.c.b e;
    private String f;
    private j g;
    private OnClickListener h = new e$1(this);
    private OnClickListener i = new e$2(this);

    public e(Activity activity) {
        this.a = activity;
        this.e = new com.budejie.www.c.b(activity);
        this.b = new ArrayList();
        this.c = new b(activity);
        this.g = new j();
    }

    public void a(List<RecommendSubscription> list, String str) {
        if (this.b != null) {
            this.b.clear();
            this.b.addAll(list);
        }
        this.f = str;
        notifyDataSetChanged();
    }

    public int getCount() {
        if (TextUtils.isEmpty(this.f)) {
            return this.b.isEmpty() ? 0 : this.b.size();
        } else {
            return this.b.size() + 1;
        }
    }

    public Object getItem(int i) {
        if (TextUtils.isEmpty(this.f)) {
            return this.b.isEmpty() ? Integer.valueOf(0) : (Serializable) this.b.get(i);
        } else {
            if (i == 0) {
                return new Integer(1);
            }
            return this.b.isEmpty() ? Integer.valueOf(0) : (Serializable) this.b.get(i - 1);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        e$a e_a;
        aa.b("SubscribedListAdapter", "getView , index = " + i + " , view = " + view);
        if (view == null) {
            LayoutInflater layoutInflater = this.a.getLayoutInflater();
            e$a e_a2 = new e$a(this);
            view = layoutInflater.inflate(R.layout.search_label_result_layout, null);
            e_a2 = new e$a(this);
            e_a2.a = (TextView) view.findViewById(R.id.LabelNameTextView);
            e_a2.b = (TextView) view.findViewById(R.id.LabelPostCountTextView);
            view.setTag(e_a2);
            e_a = e_a2;
        } else {
            e_a = (e$a) view.getTag();
        }
        a(i, e_a);
        return view;
    }

    private void a(int i, e$a e_a) {
        RecommendSubscription recommendSubscription = (RecommendSubscription) this.b.get(i);
        if (recommendSubscription != null) {
            e_a.a.setText(recommendSubscription.getTheme_name());
            e_a.b.setText(recommendSubscription.getPost_number() + "个帖子");
        }
    }

    private void a(RecommendSubscription recommendSubscription) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.g.a(this.a, recommendSubscription.getTheme_id(), false), new e$3(this, recommendSubscription));
    }

    private void b(RecommendSubscription recommendSubscription) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.g.a(this.a, recommendSubscription.getTheme_id(), true), new e$4(this, recommendSubscription));
    }

    public boolean isEmpty() {
        return this.b == null ? true : this.b.isEmpty();
    }
}
