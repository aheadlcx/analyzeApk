package com.budejie.www.adapter.a;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.RoundAsyncImageView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.RecommendUser;
import com.budejie.www.bean.Topics;
import com.budejie.www.util.SquareLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class i extends BaseAdapter {
    public static Map<String, Fans> a = new HashMap();
    private static HashMap<Integer, Boolean> c;
    private List<RecommendUser> b;
    private Activity d;

    public i(List<RecommendUser> list, Activity activity) {
        this.d = activity;
        this.b = list;
        c = new HashMap();
        d();
    }

    private void d() {
        for (int i = 0; i < this.b.size(); i++) {
            a().put(Integer.valueOf(i), Boolean.valueOf(true));
            a.put(((RecommendUser) this.b.get(i)).getUserid(), new Fans((RecommendUser) this.b.get(i)));
        }
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
        i$a i_a;
        if (view == null) {
            i_a = new i$a();
            view = LayoutInflater.from(this.d).inflate(R.layout.recommend_user_item, null);
            i_a.c = (RoundAsyncImageView) view.findViewById(R.id.user_icon);
            i_a.a = (TextView) view.findViewById(R.id.user_name);
            i_a.d = (TextView) view.findViewById(R.id.user_describe);
            i_a.b = (CheckBox) view.findViewById(R.id.item_cb);
            i_a.e = (LinearLayout) view.findViewById(R.id.user_image_list);
            i_a.f = (RelativeLayout) view.findViewById(R.id.person_info);
            i_a.f.setTag(i_a);
            i_a.g = (SquareLayout) view.findViewById(R.id.user_info1);
            i_a.k = (AsyncImageView) view.findViewById(R.id.user_info_image1);
            i_a.o = (Button) view.findViewById(R.id.user_info_text1);
            i_a.h = (SquareLayout) view.findViewById(R.id.user_info2);
            i_a.l = (AsyncImageView) view.findViewById(R.id.user_info_image2);
            i_a.p = (Button) view.findViewById(R.id.user_info_text2);
            i_a.i = (SquareLayout) view.findViewById(R.id.user_info3);
            i_a.m = (AsyncImageView) view.findViewById(R.id.user_info_image3);
            i_a.q = (Button) view.findViewById(R.id.user_info_text3);
            i_a.j = (SquareLayout) view.findViewById(R.id.user_info4);
            i_a.n = (AsyncImageView) view.findViewById(R.id.user_info_image4);
            i_a.r = (Button) view.findViewById(R.id.user_info_text4);
            i_a.s = new Button[]{i_a.o, i_a.p, i_a.q, i_a.r};
            i_a.t = new AsyncImageView[]{i_a.k, i_a.l, i_a.m, i_a.n};
            i_a.u = new SquareLayout[]{i_a.g, i_a.h, i_a.i, i_a.j};
            view.setTag(i_a);
        } else {
            i_a = (i$a) view.getTag();
        }
        i_a.f.setOnClickListener(new i$1(this, i));
        i_a.c.setPostAvatarImage(((RecommendUser) this.b.get(i)).getProfile_image());
        i_a.a.setText(((RecommendUser) this.b.get(i)).getUsername());
        if (TextUtils.isEmpty(((RecommendUser) this.b.get(i)).getIntroduction())) {
            i_a.d.setVisibility(8);
        } else {
            i_a.d.setVisibility(0);
            i_a.d.setText(((RecommendUser) this.b.get(i)).getIntroduction());
        }
        if (((RecommendUser) this.b.get(i)).getTopics() != null && ((RecommendUser) this.b.get(i)).getTopics().size() > 0) {
            i_a.e.setVisibility(0);
            for (int i2 = 0; i2 < i_a.u.length; i2++) {
                i_a.s[i2].setVisibility(8);
                i_a.t[i2].setVisibility(8);
                i_a.u[i2].setVisibility(4);
            }
            int i3 = 0;
            while (true) {
                if (i3 >= (((RecommendUser) this.b.get(i)).getTopics().size() >= 4 ? 4 : ((RecommendUser) this.b.get(i)).getTopics().size())) {
                    break;
                }
                i_a.u[i3].setVisibility(0);
                if ("29".equals(((Topics) ((RecommendUser) this.b.get(i)).getTopics().get(i3)).getType())) {
                    i_a.s[i3].setVisibility(0);
                    i_a.s[i3].setText(((Topics) ((RecommendUser) this.b.get(i)).getTopics().get(i3)).getBody());
                } else {
                    i_a.t[i3].setVisibility(0);
                    i_a.t[i3].setAsyncCacheImage(((Topics) ((RecommendUser) this.b.get(i)).getTopics().get(i3)).getImage());
                }
                i3++;
            }
        } else {
            i_a.e.setVisibility(8);
        }
        i_a.b.setChecked(((Boolean) a().get(Integer.valueOf(i))).booleanValue());
        return view;
    }

    public static HashMap<Integer, Boolean> a() {
        return c;
    }

    public static Collection<Fans> b() {
        if (a != null) {
            return a.values();
        }
        return null;
    }

    public static String c() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Fans id : b()) {
            stringBuilder.append(id.getId()).append(",");
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
        }
        return stringBuilder.toString();
    }
}
