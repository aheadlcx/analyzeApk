package com.budejie.www.adapter.a;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.view.BadgeView;
import com.budejie.www.bean.SubscibeBean;
import com.budejie.www.util.i;
import java.util.List;

public class q extends BaseAdapter {
    Activity a;
    LayoutInflater b;
    private List<SubscibeBean> c;
    private int d;
    private BadgeView e;

    public q(Activity activity, List<SubscibeBean> list) {
        this.a = activity;
        this.b = LayoutInflater.from(activity);
        this.c = list;
    }

    public void a(int i) {
        this.d = i;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        q$a q_a = new q$a(this);
        View inflate = this.b.inflate(R.layout.navigation_textview_item, null);
        q_a.a = (TextView) inflate.findViewById(R.id.nav_textview);
        q_a.b = (ImageView) inflate.findViewById(R.id.nav_selectedFlag);
        SubscibeBean subscibeBean = (SubscibeBean) this.c.get(i);
        q_a.a.setText(subscibeBean.name);
        if (subscibeBean.isChecked) {
            q_a.b.setVisibility(0);
            q_a.a.setSelected(true);
            inflate.setBackgroundColor(-1);
        } else {
            q_a.b.setVisibility(4);
            q_a.a.setSelected(false);
            inflate.setBackgroundColor(this.a.getResources().getColor(R.color.recommend_nav_listview_item_normal_bg));
        }
        if ("好友".equals(subscibeBean.name)) {
            this.e = new BadgeView(this.a, q_a.a);
            this.e.setBadgePosition(2);
            this.e.setBackgroundResource(R.drawable.notice_bg);
            this.e.b(10, 30);
            if (HomeGroup.l > 0) {
                int b = (int) (10.0f * i.a().b(this.a));
                this.e.a(b, b);
            } else {
                this.e.b();
            }
        }
        return inflate;
    }
}
