package com.budejie.www.activity.label;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.util.i;
import java.util.List;

public class a extends BaseAdapter {
    private Context a;
    private List<LabelBean> b;
    private LayoutInflater c;
    private int d = R$styleable.Theme_Custom_forward_icon;
    private LayoutParams e;
    private LayoutParams f;

    public a(Context context) {
        this.a = context;
        this.c = (LayoutInflater) context.getSystemService("layout_inflater");
        this.e = a();
        this.f = b();
    }

    private LayoutParams a() {
        int a = (i.a().a(this.a) - (((int) this.a.getResources().getDimension(R.dimen.horizontal_spacing)) * 3)) / 2;
        return new LayoutParams(a, a);
    }

    private LayoutParams b() {
        LayoutParams layoutParams = new LayoutParams((i.a().a(this.a) - (((int) this.a.getResources().getDimension(R.dimen.horizontal_spacing)) * 3)) / 2, -2);
        layoutParams.gravity = 80;
        return layoutParams;
    }

    public void a(List<LabelBean> list) {
        if (this.b == null) {
            this.b = list;
        } else {
            this.b.clear();
            this.b.addAll(list);
        }
        notifyDataSetInvalidated();
    }

    public void b(List<LabelBean> list) {
        if (this.b == null) {
            this.b = list;
        } else {
            this.b.addAll(list);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.b != null) {
            return this.b.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a$a a_a;
        if (view == null) {
            a_a = new a$a(this);
            view = this.c.inflate(R.layout.activities_topic_list_item, null);
            a_a.a = (AsyncImageView) view.findViewById(R.id.item_at_img);
            a_a.c = (TextView) view.findViewById(R.id.item_at_name);
            a_a.b = (TextView) view.findViewById(R.id.item_at_participants_count);
            a_a.d = view.findViewById(R.id.imageready);
            a_a.e = view.findViewById(R.id.imageready2);
            view.setTag(a_a);
        } else {
            a_a = (a$a) view.getTag();
        }
        LabelBean labelBean = (LabelBean) this.b.get(i);
        a_a.c.setText(labelBean.getTheme_name());
        int status = labelBean.getStatus();
        String str = "";
        a_a.b.setLayoutParams(this.f);
        a_a.e.setVisibility(8);
        if (status == 0) {
            a_a.b.setSelected(false);
            a_a.b.setText("已结束" + labelBean.getTotal_users() + "人参与");
            a_a.e.setVisibility(0);
            a_a.e.setLayoutParams(this.e);
        } else if (status == 1) {
            a_a.b.setSelected(true);
            a_a.b.setText("进行中" + labelBean.getTotal_users() + "人参与");
        } else if (status == 2) {
            a_a.b.setSelected(false);
            a_a.b.setText("未开始");
        }
        Object image_list = labelBean.getImage_list();
        a_a.a.setLayoutParams(this.e);
        a_a.d.setLayoutParams(this.e);
        if (!TextUtils.isEmpty(image_list)) {
            a_a.a.setAsyncCacheImage(image_list);
        }
        view.setOnClickListener(new a$1(this, labelBean));
        return view;
    }
}
