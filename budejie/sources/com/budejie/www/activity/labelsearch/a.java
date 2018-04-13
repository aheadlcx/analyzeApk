package com.budejie.www.activity.labelsearch;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.plate.bean.PlateBean;
import java.util.List;

public class a extends BaseAdapter {
    private List<PlateBean> a;
    private LayoutInflater b;

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context, List<PlateBean> list) {
        this.a = list;
        this.b = LayoutInflater.from(context);
    }

    public int getCount() {
        return com.budejie.www.goddubbing.c.a.a(this.a) ? 0 : this.a.size();
    }

    public PlateBean a(int i) {
        return (PlateBean) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a$a a_a;
        if (view == null) {
            a$a a_a2 = new a$a(this, null);
            view = this.b.inflate(R.layout.item_label_search, null);
            a_a2.a = (AsyncImageView) view.findViewById(R.id.logo_imageView);
            a_a2.b = (TextView) view.findViewById(R.id.theme_name_textView);
            a_a2.c = (TextView) view.findViewById(R.id.count_textView);
            view.setTag(a_a2);
            a_a = a_a2;
        } else {
            a_a = (a$a) view.getTag();
        }
        a(a_a, i);
        return view;
    }

    private void a(a$a a_a, int i) {
        PlateBean plateBean = (PlateBean) this.a.get(i);
        if (plateBean != null) {
            if (TextUtils.isEmpty(plateBean.image_list)) {
                a_a.a.setImageResource(R.drawable.label_default_icon);
            } else {
                a_a.a.setAsyncCacheImage(plateBean.image_list);
            }
            a_a.b.setText(plateBean.theme_name);
            String str = plateBean.tail;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            a_a.c.setText(String.valueOf(plateBean.sub_number) + str);
        }
    }
}
