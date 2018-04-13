package com.budejie.www.adapter.d;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.b;
import com.budejie.www.adapter.f.g;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.an;
import com.budejie.www.util.at;
import com.budejie.www.util.z;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.List;

public class m extends com.budejie.www.adapter.a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;
    protected final int f;
    protected final com.budejie.www.c.m g;
    protected final com.budejie.www.http.a h;
    private g i;
    private String j;
    private String k;
    private com.budejie.www.f.a l = new com.budejie.www.f.a(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public void a(int i, String str) {
            if (!"0".equals(z.x(str).getResult())) {
                if (i == 959) {
                    an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_failed_sex), -1).show();
                } else if (i == 1959) {
                    an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_failed_education), -1).show();
                } else if (i == 2959) {
                    an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_failed_age), -1).show();
                }
                if (this.a.i != null && this.a.i.c != null) {
                    this.a.a(this.a.i.c);
                }
            } else if (i == 959) {
                an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_successed), -1).show();
                this.a.g.a("sex", this.a.j, this.a.k);
            } else if (i == 1959) {
                an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_successed), -1).show();
                this.a.g.a("degree", this.a.j, this.a.k);
            } else if (i == 2959) {
                an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_successed), -1).show();
                this.a.g.a("age_group", this.a.j, this.a.k);
            }
        }

        public void a(int i) {
            if (i == 959) {
                an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_failed_sex), -1).show();
            } else if (i == 1959) {
                an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_failed_education), -1).show();
            } else if (i == 2959) {
                an.a(this.a.b, this.a.b.getResources().getString(R.string.user_info_collect_failed_age), -1).show();
            }
            if (this.a.i != null && this.a.i.c != null) {
                this.a.a(this.a.i.c);
            }
        }
    };

    class a implements OnClickListener {
        List<Button> a;
        final /* synthetic */ m b;

        public a(m mVar, List<Button> list) {
            this.b = mVar;
            this.a = list;
        }

        public void onClick(View view) {
            MobclickAgent.onEvent(this.b.b, "E01-A04", "用户资料收集完善点击次数");
            for (Button button : this.a) {
                if (button.getId() == view.getId()) {
                    view.setSelected(true);
                } else {
                    button.setSelected(false);
                }
            }
            this.b.j = ((Button) view).getTag().toString();
            if (this.b.f == 6) {
                this.b.h.a(this.b.k, this.b.j, "", "", "", "", "", 959);
            } else if (this.b.f == 5) {
                this.b.h.a(this.b.k, "", "", this.b.j, "", "", "", 2959);
            } else if (this.b.f == 7) {
                this.b.h.a(this.b.k, "", "", "", this.b.j, "", "", 1959);
            }
        }
    }

    public /* synthetic */ Object d() {
        return a();
    }

    public m(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i, int i2) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.e = i;
        this.f = i2;
        this.c = LayoutInflater.from(activity);
        this.h = new com.budejie.www.http.a(activity, this.l);
        this.g = new com.budejie.www.c.m(activity);
        if (activity instanceof PostsActivity) {
            this.k = ((PostsActivity) activity).h;
        }
    }

    @SuppressLint({"InflateParams"})
    public View b() {
        this.i = new g();
        this.i.c = new ArrayList();
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.new_new_list_item_user_info_collect, null);
        this.i.a = (LinearLayout) viewGroup.findViewById(R.id.item_layout);
        this.i.b = (TextView) viewGroup.findViewById(R.id.user_info_collect_title);
        Button button;
        Button button2;
        if (6 == this.f) {
            OnClickListener aVar = new a(this, this.i.c);
            this.i.b.setText(at.a(this.b, this.b.getResources().getString(R.string.user_info_collect_title_gender), this.b.getResources().getString(R.string.user_info_collect_title_gender_highlight)));
            View inflate = this.c.inflate(R.layout.new_new_list_item_user_info_collect_gender, null);
            button = (Button) inflate.findViewById(R.id.beauty);
            button2 = (Button) inflate.findViewById(R.id.handsome);
            button.setOnClickListener(aVar);
            button2.setOnClickListener(aVar);
            this.i.c.add(button);
            this.i.c.add(button2);
            this.i.a.addView(inflate);
        } else if (5 == this.f) {
            OnClickListener aVar2 = new a(this, this.i.c);
            this.i.b.setText(at.a(this.b, this.b.getResources().getString(R.string.user_info_collect_title), this.b.getResources().getString(R.string.user_info_collect_title_highlight1), this.b.getResources().getString(R.string.user_info_collect_title_highlight2)));
            View inflate2 = this.c.inflate(R.layout.new_new_list_item_user_info_collect_age, null);
            button = (Button) inflate2.findViewById(R.id.after_70s);
            button2 = (Button) inflate2.findViewById(R.id.after_80s);
            r3 = (Button) inflate2.findViewById(R.id.after_85s);
            r4 = (Button) inflate2.findViewById(R.id.after_90s);
            Button button3 = (Button) inflate2.findViewById(R.id.after_95s);
            Button button4 = (Button) inflate2.findViewById(R.id.after_00s);
            button.setOnClickListener(aVar2);
            button2.setOnClickListener(aVar2);
            r3.setOnClickListener(aVar2);
            r4.setOnClickListener(aVar2);
            button3.setOnClickListener(aVar2);
            button4.setOnClickListener(aVar2);
            this.i.c.add(button);
            this.i.c.add(button2);
            this.i.c.add(r3);
            this.i.c.add(r4);
            this.i.c.add(button3);
            this.i.c.add(button4);
            this.i.a.addView(inflate2);
        } else if (7 == this.f) {
            OnClickListener aVar3 = new a(this, this.i.c);
            this.i.b.setText(at.a(this.b, this.b.getResources().getString(R.string.user_info_collect_title), this.b.getResources().getString(R.string.user_info_collect_title_highlight1), this.b.getResources().getString(R.string.user_info_collect_title_highlight2)));
            View inflate3 = this.c.inflate(R.layout.new_new_list_item_user_info_collect_education, null);
            button = (Button) inflate3.findViewById(R.id.under_high_school);
            button2 = (Button) inflate3.findViewById(R.id.high_school);
            r3 = (Button) inflate3.findViewById(R.id.college);
            r4 = (Button) inflate3.findViewById(R.id.college_above);
            button.setOnClickListener(aVar3);
            button2.setOnClickListener(aVar3);
            r3.setOnClickListener(aVar3);
            r4.setOnClickListener(aVar3);
            this.i.c.add(button);
            this.i.c.add(button2);
            this.i.c.add(r3);
            this.i.c.add(r4);
            this.i.a.addView(inflate3);
        }
        viewGroup.setTag(this.i);
        return viewGroup;
    }

    public void a(b bVar) {
        g gVar = (g) bVar;
    }

    public ListItemObject a() {
        return this.a;
    }

    public int c() {
        if (this.f == 5) {
            return RowType.USER_INFO_COLLECT_ROW_AGE_GROUP.ordinal();
        }
        if (this.f == 7) {
            return RowType.USER_INFO_COLLECT_ROW_EDUCATION.ordinal();
        }
        if (this.f == 6) {
            return RowType.USER_INFO_COLLECT_ROW_GENDER.ordinal();
        }
        return RowType.USER_INFO_COLLECT_ROW_GENDER.ordinal();
    }

    private void a(List<Button> list) {
        for (Button selected : list) {
            selected.setSelected(false);
        }
    }
}
