package com.budejie.www.adapter.a;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.bean.Topics;
import com.budejie.www.util.SquareLayout;
import java.util.List;

public class c extends BaseAdapter {
    private List<LabelBean> a;
    private Activity b;
    private LayoutInflater c = null;

    public c(List<LabelBean> list, Activity activity) {
        this.b = activity;
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (((LabelBean) this.a.get(i)).getStatus() != 1) {
            return null;
        }
        c$a c_a;
        if (view == null) {
            c_a = new c$a();
            view = LayoutInflater.from(this.b).inflate(R.layout.my_activity_item, null);
            c_a.a = (TextView) view.findViewById(R.id.activity_name);
            c_a.b = (TextView) view.findViewById(R.id.participant_no);
            c_a.c = (LinearLayout) view.findViewById(R.id.activity_image_list);
            c_a.d = (RelativeLayout) view.findViewById(R.id.activity_info_cell);
            c_a.d.setTag(c_a);
            c_a.e = (SquareLayout) view.findViewById(R.id.activity_info1);
            c_a.i = (AsyncImageView) view.findViewById(R.id.activity_info_image1);
            c_a.m = (Button) view.findViewById(R.id.activity_info_text1);
            c_a.f = (SquareLayout) view.findViewById(R.id.activity_info2);
            c_a.j = (AsyncImageView) view.findViewById(R.id.activity_info_image2);
            c_a.n = (Button) view.findViewById(R.id.activity_info_text2);
            c_a.g = (SquareLayout) view.findViewById(R.id.activity_info3);
            c_a.k = (AsyncImageView) view.findViewById(R.id.activity_info_image3);
            c_a.o = (Button) view.findViewById(R.id.activity_info_text3);
            c_a.h = (SquareLayout) view.findViewById(R.id.activity_info4);
            c_a.l = (AsyncImageView) view.findViewById(R.id.activity_info_image4);
            c_a.p = (Button) view.findViewById(R.id.activity_info_text4);
            c_a.q = new Button[]{c_a.m, c_a.n, c_a.o, c_a.p};
            c_a.r = new AsyncImageView[]{c_a.i, c_a.j, c_a.k, c_a.l};
            c_a.s = new SquareLayout[]{c_a.e, c_a.f, c_a.g, c_a.h};
            view.setTag(c_a);
        } else {
            c_a = (c$a) view.getTag();
        }
        c_a.d.setOnClickListener(new c$1(this, i));
        c_a.c.setOnClickListener(new c$2(this, i));
        c_a.a.setText(((LabelBean) this.a.get(i)).getTheme_name());
        c_a.b.setText(((LabelBean) this.a.get(i)).getTotal_users() + "人参与");
        if (((LabelBean) this.a.get(i)).getTopics() == null || ((LabelBean) this.a.get(i)).getTopics().size() <= 0) {
            return view;
        }
        c_a.c.setVisibility(0);
        int i2 = 0;
        while (true) {
            if (i2 >= (((LabelBean) this.a.get(i)).getTopics().size() >= 4 ? 4 : ((LabelBean) this.a.get(i)).getTopics().size())) {
                return view;
            }
            c_a.s[i2].setVisibility(0);
            if ("29".equals(((Topics) ((LabelBean) this.a.get(i)).getTopics().get(i2)).getType())) {
                c_a.q[i2].setVisibility(0);
                c_a.q[i2].setText(((Topics) ((LabelBean) this.a.get(i)).getTopics().get(i2)).getBody());
            } else {
                c_a.r[i2].setVisibility(0);
                c_a.r[i2].setAsyncCacheImage(((Topics) ((LabelBean) this.a.get(i)).getTopics().get(i2)).getImage());
            }
            i2++;
        }
    }
}
