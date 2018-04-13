package com.budejie.www.activity.plate;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.plate.bean.PlateBean;
import java.util.ArrayList;

public class a extends BaseAdapter {
    private Activity a;
    private ArrayList<PlateBean> b = new ArrayList();

    public a(Activity activity) {
        this.a = activity;
    }

    public void a(ArrayList<PlateBean> arrayList) {
        this.b = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a$a a_a;
        if (view == null) {
            a_a = new a$a(this);
            view = this.a.getLayoutInflater().inflate(R.layout.item_plate_list, null);
            a_a.a = (AsyncImageView) view.findViewById(R.id.plate_header);
            a_a.b = (TextView) view.findViewById(R.id.plate_name);
            a_a.c = (TextView) view.findViewById(R.id.plate_update_text_view);
            a_a.d = (TextView) view.findViewById(R.id.online_person_text_view);
            a_a.e = (TextView) view.findViewById(R.id.plate_introduction_text_view);
            a_a.f = (TextView) view.findViewById(R.id.update_prompt_text_view);
            a_a.g = view.findViewById(R.id.line_view);
            view.setTag(a_a);
        } else {
            a_a = (a$a) view.getTag();
        }
        a(a_a, (PlateBean) this.b.get(i));
        return view;
    }

    private void a(a$a a_a, PlateBean plateBean) {
        if (plateBean != null) {
            a_a.a.setAsyncCacheImage(plateBean.image_list, R.drawable.label_default_icon);
            a_a.b.setText(plateBean.theme_name);
            if (plateBean.today_topic_num > 0) {
                a_a.c.setVisibility(0);
                a_a.c.setText(String.valueOf(plateBean.today_topic_num));
                a_a.g.setVisibility(0);
                a_a.f.setVisibility(0);
            } else {
                a_a.c.setVisibility(8);
                a_a.g.setVisibility(8);
                a_a.f.setVisibility(8);
            }
            a_a.e.setText(plateBean.info);
            a_a.d.setText(String.format(this.a.getResources().getString(R.string.plate_online_person_text), new Object[]{Integer.valueOf(plateBean.visit)}));
        }
    }
}
