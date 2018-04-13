package com.budejie.www.activity.labelsubscription;

import android.app.Activity;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
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
import com.budejie.www.c.b;
import com.budejie.www.h.c;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class f extends BaseAdapter {
    private final int a = 0;
    private final int b = 1;
    private final int c = 2;
    private final int d = 3;
    private final int e = 4;
    private Activity f;
    private ArrayList<RecommendSubscription> g;
    private Toast h;
    private b i;
    private Handler j;
    private j k;
    private OnClickListener l = new f$1(this);
    private OnClickListener m = new f$2(this);

    public f(Activity activity, Handler handler) {
        this.f = activity;
        this.i = new b(activity);
        this.g = new ArrayList();
        this.j = handler;
        this.k = new j();
    }

    public List<RecommendSubscription> a(List<RecommendSubscription> list) {
        int i = 0;
        if (this.g != null) {
            RecommendSubscription recommendSubscription;
            this.g.clear();
            int i2 = 0;
            while (i2 < list.size()) {
                if ("recomm_tv".equals(((RecommendSubscription) list.get(i2)).getType()) || "sub_tv".equals(((RecommendSubscription) list.get(i2)).getType()) || "add".equals(((RecommendSubscription) list.get(i2)).getType())) {
                    list.remove(i2);
                }
                i2++;
            }
            int i3 = 0;
            RecommendSubscription recommendSubscription2 = null;
            while (i3 < list.size()) {
                if (!"r".equals(((RecommendSubscription) list.get(i3)).getType())) {
                    if (recommendSubscription2 == null) {
                        recommendSubscription = new RecommendSubscription();
                        recommendSubscription.setType("sub_tv");
                        this.g.add(recommendSubscription);
                        this.g.add(list.get(i3));
                        i3++;
                        recommendSubscription2 = recommendSubscription;
                    } else {
                        this.g.add(list.get(i3));
                    }
                }
                recommendSubscription = recommendSubscription2;
                i3++;
                recommendSubscription2 = recommendSubscription;
            }
            recommendSubscription2 = null;
            while (i < list.size()) {
                if ("r".equals(((RecommendSubscription) list.get(i)).getType())) {
                    if (recommendSubscription2 == null) {
                        recommendSubscription = new RecommendSubscription();
                        recommendSubscription.setType("recomm_tv");
                        this.g.add(recommendSubscription);
                        this.g.add(list.get(i));
                        i++;
                        recommendSubscription2 = recommendSubscription;
                    } else {
                        this.g.add(list.get(i));
                    }
                }
                recommendSubscription = recommendSubscription2;
                i++;
                recommendSubscription2 = recommendSubscription;
            }
        }
        notifyDataSetChanged();
        return this.g;
    }

    public int getItemViewType(int i) {
        RecommendSubscription recommendSubscription = (RecommendSubscription) getItem(i);
        if ("sub_tv".equals(recommendSubscription.getType()) || "recomm_tv".equals(recommendSubscription.getType())) {
            if ("recomm_tv".equals(recommendSubscription.getType())) {
                return 0;
            }
            return 1;
        } else if ("add".equals(recommendSubscription.getType())) {
            return 3;
        } else {
            return 2;
        }
    }

    public int getViewTypeCount() {
        return 4;
    }

    public int getCount() {
        return this.g.isEmpty() ? 0 : this.g.size();
    }

    public Object getItem(int i) {
        return this.g.isEmpty() ? Integer.valueOf(0) : (Serializable) this.g.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        f$a f_a;
        f$c f_c = null;
        int itemViewType = getItemViewType(i);
        if (view != null) {
            switch (itemViewType) {
                case 0:
                    f_a = (f$a) view.getTag();
                    break;
                case 1:
                    f$b f_b = (f$b) view.getTag();
                    f_a = null;
                    break;
                case 2:
                case 3:
                    f_a = null;
                    f_c = (f$c) view.getTag();
                    break;
                default:
                    f_a = null;
                    break;
            }
        }
        f$c f_c2;
        switch (itemViewType) {
            case 0:
                view = LayoutInflater.from(this.f).inflate(R.layout.recommend_label_title, null);
                f$a f_a2 = new f$a(this);
                f_a2.a = view.findViewById(R.id.padding_top);
                view.setTag(f_a2);
                Object obj = f_a2;
                f_c2 = null;
                break;
            case 1:
                view = LayoutInflater.from(this.f).inflate(R.layout.subscription_label_title, null);
                view.setTag(new f$b(this));
                f_c2 = null;
                break;
            case 2:
            case 3:
                view = LayoutInflater.from(this.f).inflate(R.layout.subscription_item_layout, null);
                f$c f_c3 = new f$c(this);
                f_c3.b = (TextView) view.findViewById(R.id.label_name);
                f_c3.a = (AsyncImageView) view.findViewById(R.id.label_icon);
                f_c3.d = (TextView) view.findViewById(R.id.label_subscribe_count);
                f_c3.c = (TextView) view.findViewById(R.id.enter_btn);
                f_c3.e = (TextView) view.findViewById(R.id.add_btn);
                f_c3.f = (TextView) view.findViewById(R.id.default_btn);
                view.setTag(f_c3);
                f_c2 = f_c3;
                break;
            default:
                f_c2 = null;
                break;
        }
        f_a = f_c;
        f_c = f_c2;
        RecommendSubscription recommendSubscription = (RecommendSubscription) this.g.get(i);
        if (recommendSubscription != null) {
            switch (itemViewType) {
                case 0:
                    if (i != 0) {
                        f_a.a.setVisibility(0);
                        break;
                    }
                    f_a.a.setVisibility(8);
                    break;
                case 2:
                    f_c.c.setOnClickListener(this.l);
                    f_c.c.setTag(recommendSubscription);
                    f_c.e.setOnClickListener(this.m);
                    f_c.e.setTag(recommendSubscription);
                    f_c.f.setOnClickListener(this.m);
                    f_c.f.setTag(recommendSubscription);
                    f_c.b.setText(recommendSubscription.getTheme_name());
                    StringBuilder stringBuilder = new StringBuilder();
                    if (Integer.parseInt(recommendSubscription.getSub_number()) < 10000) {
                        stringBuilder.append(recommendSubscription.getSub_number()).append("人订阅");
                    } else if (Integer.parseInt(recommendSubscription.getSub_number()) % 10000 >= 1000) {
                        stringBuilder.append(Integer.parseInt(recommendSubscription.getSub_number()) / 10000).append(".").append((Integer.parseInt(recommendSubscription.getSub_number()) % 10000) / 1000).append("万订阅");
                    } else {
                        stringBuilder.append(Integer.parseInt(recommendSubscription.getSub_number()) / 10000).append("万订阅");
                    }
                    if (TextUtils.isEmpty(recommendSubscription.getPost_num())) {
                        f_c.d.setText(stringBuilder.toString());
                    } else {
                        int i2;
                        stringBuilder.append(" | 总帖数 ");
                        itemViewType = stringBuilder.length();
                        stringBuilder.append(recommendSubscription.getPost_num());
                        int length = stringBuilder.length();
                        CharSequence spannableString = new SpannableString(stringBuilder.toString());
                        if (R.style.ThemeLight == c.a().b()) {
                            i2 = R.color.main_red;
                        } else {
                            i2 = R.color.main_red_black;
                        }
                        spannableString.setSpan(new ForegroundColorSpan(this.f.getResources().getColor(i2)), itemViewType, length, 33);
                        f_c.d.setText(spannableString);
                    }
                    f_c.a.setAsyncCacheImage(recommendSubscription.getImage_list(), R.drawable.label_default_icon);
                    String is_sub = recommendSubscription.getIs_sub();
                    if (!"1".equals(recommendSubscription.getIs_default())) {
                        if (!"1".equals(is_sub)) {
                            if ("0".equals(is_sub)) {
                                f_c.f.setVisibility(8);
                                f_c.c.setVisibility(8);
                                f_c.e.setVisibility(0);
                                break;
                            }
                        }
                        f_c.c.setVisibility(8);
                        f_c.e.setVisibility(8);
                        f_c.f.setVisibility(8);
                        break;
                    }
                    f_c.f.setVisibility(8);
                    f_c.c.setVisibility(8);
                    f_c.e.setVisibility(8);
                    break;
                    break;
                case 3:
                    int i3;
                    f_c.b.setText("添加标签");
                    f_c.d.setText("添加你喜欢的标签");
                    if (R.style.ThemeLight == c.a().b()) {
                        i3 = R.drawable.label_add;
                    } else {
                        i3 = R.drawable.label_add_night;
                    }
                    f_c.a.setImageResource(i3);
                    f_c.c.setVisibility(8);
                    f_c.e.setVisibility(8);
                    break;
            }
        }
        return view;
    }

    private void a(RecommendSubscription recommendSubscription, View view) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.k.a(this.f, recommendSubscription.getTheme_id(), false), new f$3(this, recommendSubscription, view));
    }

    private void a(RecommendSubscription recommendSubscription) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.k.a(this.f, recommendSubscription.getTheme_id(), true), new f$4(this, recommendSubscription));
    }

    public boolean isEmpty() {
        return this.g == null ? true : this.g.isEmpty();
    }
}
