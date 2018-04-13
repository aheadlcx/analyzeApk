package com.budejie.www.adapter.a;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.LabelUser;
import com.budejie.www.bean.UserRanking;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.b;

public class a extends BaseAdapter implements OnClickListener {
    protected Toast a;
    List<LabelUser> b;
    final int c = 0;
    final int d = 1;
    final int e = 2;
    final int f = 3;
    final int g = 4;
    private Activity h;
    private List<LabelUser> i;
    private List<LabelUser> j;
    private g k;

    public a(Activity activity, UserRanking userRanking) {
        int i = 0;
        this.h = activity;
        this.i = new ArrayList();
        this.j = userRanking.getRecomList();
        this.b = userRanking.getTopList();
        this.k = new g(activity);
        if (this.i != null) {
            LabelUser labelUser;
            this.i.clear();
            if (userRanking.getRecomList() != null && userRanking.getRecomList().size() > 0) {
                labelUser = null;
                for (int i2 = 0; i2 < userRanking.getRecomList().size(); i2++) {
                    if (labelUser == null) {
                        labelUser = new LabelUser();
                        labelUser.setType("recomm_tv");
                        this.i.add(labelUser);
                        this.i.add(userRanking.getRecomList().get(i2));
                    } else {
                        this.i.add(userRanking.getRecomList().get(i2));
                    }
                }
            }
            if (userRanking.getTopList() != null && userRanking.getTopList().size() > 0) {
                labelUser = null;
                while (i < userRanking.getTopList().size()) {
                    if (labelUser == null) {
                        labelUser = new LabelUser();
                        labelUser.setType("top_tv");
                        this.i.add(labelUser);
                        this.i.add(userRanking.getTopList().get(i));
                    } else {
                        this.i.add(userRanking.getTopList().get(i));
                    }
                    i++;
                }
            }
        }
    }

    public int getCount() {
        return this.i.size();
    }

    public int getItemViewType(int i) {
        LabelUser labelUser = (LabelUser) getItem(i);
        if ("top_tv".equals(labelUser.getType()) || "recomm_tv".equals(labelUser.getType())) {
            if ("recomm_tv".equals(labelUser.getType())) {
                return 0;
            }
            return 1;
        } else if ("r".equals(labelUser.getType())) {
            return 2;
        } else {
            return 3;
        }
    }

    public int getViewTypeCount() {
        return 4;
    }

    public Object getItem(int i) {
        return this.i.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a$d a_d;
        a$a a_a;
        a$b a_b;
        a$c a_c = null;
        int itemViewType = getItemViewType(i);
        if (view != null) {
            switch (itemViewType) {
                case 0:
                    a_d = null;
                    a_a = (a$a) view.getTag();
                    a_b = null;
                    break;
                case 1:
                    a_b = (a$b) view.getTag();
                    a_d = null;
                    a_a = null;
                    break;
                case 2:
                    a_d = null;
                    a_a = null;
                    a_c = (a$c) view.getTag();
                    Object obj = null;
                    break;
                case 3:
                    a_d = (a$d) view.getTag();
                    a_a = null;
                    a_b = null;
                    break;
                default:
                    a_d = null;
                    a_b = null;
                    a_a = null;
                    break;
            }
        }
        a$c a_c2;
        a$b a_b2;
        switch (itemViewType) {
            case 0:
                view = LayoutInflater.from(this.h).inflate(R.layout.contribute_list_text_item, null);
                a$a a_a2 = new a$a(this);
                a_a2.a = (TextView) view.findViewById(R.id.left_title_text);
                view.setTag(a_a2);
                a_c2 = null;
                a_a = a_a2;
                a_b2 = null;
                break;
            case 1:
                view = LayoutInflater.from(this.h).inflate(R.layout.contribute_list_text_item, null);
                a_b2 = new a$b(this);
                a_b2.a = (TextView) view.findViewById(R.id.left_title_text);
                a_b2.b = (TextView) view.findViewById(R.id.right_title_text);
                view.setTag(a_b2);
                a_c2 = null;
                a_a = null;
                break;
            case 2:
                view = LayoutInflater.from(this.h).inflate(R.layout.contribute_list_item, null);
                a$c a_c3 = new a$c(this);
                a_c3.a = (AsyncImageView) view.findViewById(R.id.usericon);
                a_c3.b = (TextView) view.findViewById(R.id.username);
                a_c3.c = (TextView) view.findViewById(R.id.fans_count);
                a_c3.d = (TextView) view.findViewById(R.id.cancel_btn);
                a_c3.e = (LinearLayout) view.findViewById(R.id.recommend_follow);
                a_c3.f = (TextView) view.findViewById(R.id.user_v);
                a_c3.g = (TextView) view.findViewById(R.id.user_ranking);
                view.setTag(a_c3);
                a_c2 = null;
                a_a = null;
                a_c = a_c3;
                Object obj2 = null;
                break;
            case 3:
                view = LayoutInflater.from(this.h).inflate(R.layout.contribute_list_item, null);
                a_d = new a$d(this);
                a_d.a = (AsyncImageView) view.findViewById(R.id.usericon);
                a_d.b = (TextView) view.findViewById(R.id.username);
                a_d.c = (TextView) view.findViewById(R.id.fans_count);
                a_d.d = (TextView) view.findViewById(R.id.cancel_btn);
                a_d.e = (LinearLayout) view.findViewById(R.id.recommend_follow);
                a_d.f = (TextView) view.findViewById(R.id.user_v);
                a_d.g = (TextView) view.findViewById(R.id.user_ranking);
                view.setTag(a_d);
                obj = a_d;
                a_a = null;
                a_b2 = null;
                break;
            default:
                a_c2 = null;
                a_b2 = null;
                a_a = null;
                break;
        }
        a$c a_c4 = a_c2;
        a_b = a_b2;
        a_d = a_c4;
        LabelUser labelUser;
        switch (itemViewType) {
            case 0:
                a_a.a.setText("小编推荐");
                break;
            case 1:
                a_b.a.setText("贡献排行");
                a_b.b.setText("如何上榜？");
                a_b.b.setOnClickListener(new a$1(this));
                break;
            case 2:
                labelUser = (LabelUser) this.i.get(i);
                a_c.a.setAsyncCacheImage(labelUser.getHeader(), R.drawable.head_portrait);
                a_c.b.setText(labelUser.getName());
                a_c.c.setText(labelUser.getFans_count());
                if ("1".equals(labelUser.getJie_v()) || "1".equals(labelUser.getSina_v())) {
                    a_c.f.setVisibility(0);
                } else {
                    a_c.f.setVisibility(8);
                }
                if ("1".equals(labelUser.getIs_follow())) {
                    a_c.d.setVisibility(0);
                    a_c.e.setVisibility(8);
                } else {
                    a_c.d.setVisibility(8);
                    a_c.e.setVisibility(0);
                }
                a_c.d.setOnClickListener(this);
                a_c.d.setTag(labelUser);
                a_c.e.setOnClickListener(this);
                a_c.e.setTag(labelUser);
                break;
            case 3:
                labelUser = (LabelUser) this.i.get(i);
                a_d.a.setAsyncCacheImage(labelUser.getHeader(), R.drawable.head_portrait);
                a_d.b.setText(labelUser.getName());
                a_d.c.setText(labelUser.getFans_count());
                if ("1".equals(labelUser.getJie_v()) || "1".equals(labelUser.getSina_v())) {
                    a_d.f.setVisibility(0);
                } else {
                    a_d.f.setVisibility(8);
                }
                if (i == (this.j.size() > 0 ? this.j.size() + 2 : this.j.size() + 1)) {
                    a_d.g.setVisibility(0);
                    a_d.g.setBackgroundResource(R.drawable.user_ranking_one);
                } else {
                    if (i == (this.j.size() > 0 ? this.j.size() + 3 : this.j.size() + 2)) {
                        a_d.g.setVisibility(0);
                        a_d.g.setBackgroundResource(R.drawable.user_ranking_two);
                    } else {
                        if (i == (this.j.size() > 0 ? this.j.size() + 4 : this.j.size() + 3)) {
                            a_d.g.setVisibility(0);
                            a_d.g.setBackgroundResource(R.drawable.user_ranking_three);
                        } else {
                            a_d.g.setVisibility(8);
                        }
                    }
                }
                if ("1".equals(labelUser.getIs_follow())) {
                    a_d.d.setVisibility(0);
                    a_d.e.setVisibility(8);
                } else {
                    a_d.d.setVisibility(8);
                    a_d.e.setVisibility(0);
                }
                a_d.d.setOnClickListener(this);
                a_d.d.setTag(labelUser);
                a_d.e.setOnClickListener(this);
                a_d.e.setTag(labelUser);
                break;
        }
        return view;
    }

    public void onClick(View view) {
        if (!an.a(this.h)) {
            an.a(this.h, this.h.getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(ai.b(this.h))) {
            an.a(this.h, 0, null, null, 0);
        } else {
            LabelUser labelUser = (LabelUser) view.getTag();
            String is_follow = labelUser.getIs_follow();
            if ("1".equals(is_follow)) {
                a(labelUser);
            } else if ("0".equals(is_follow)) {
                b(labelUser);
            }
        }
    }

    private void a(LabelUser labelUser) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().e(this.h, labelUser.getUid()), new a$2(this, labelUser));
    }

    private void b(LabelUser labelUser) {
        b c = new j().c(this.h, labelUser.getUid(), "1");
        c.d("c", "user");
        c.d("a", "follow");
        c.d(UserTrackerConstants.FROM, "1");
        c.d("userid", labelUser.getUid());
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", c, new a$3(this, labelUser));
    }
}
