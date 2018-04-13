package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.view.CustomListView;
import com.budejie.www.bean.DingMeData;
import com.budejie.www.bean.DingNewsItem;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.c;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.util.f;
import com.budejie.www.util.z;
import java.util.ArrayList;
import java.util.Collection;

public class DingMeActivity extends SensorBaseActivity {
    String a = "DingMeActivity";
    DingMeActivity b;
    TextView c;
    Button d;
    LinearLayout e;
    CustomListView f;
    View g;
    View h;
    Toast i;
    a j;
    boolean k = true;
    boolean l = false;
    DingMeData m = new DingMeData();
    ArrayList<DingNewsItem> n = new ArrayList();
    c o;
    private long p = 0;
    private String q;
    private int r;
    private String s;
    private String t;
    private String u;
    private String v;
    private OnClickListener w = new OnClickListener(this) {
        final /* synthetic */ DingMeActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.b.finish();
        }
    };
    private com.budejie.www.activity.view.CustomListView.b x = new com.budejie.www.activity.view.CustomListView.b(this) {
        final /* synthetic */ DingMeActivity a;

        {
            this.a = r1;
        }

        public void a() {
            if (!an.a(this.a.b)) {
                this.a.i = an.a(this.a.b, this.a.b.getString(R.string.nonet), -1);
                this.a.i.show();
            } else if (this.a.k && this.a.j != null) {
                if (this.a.h != null) {
                    ((TextView) this.a.h.findViewById(R.id.message_list_more_tv)).setText(R.string.more_info);
                    this.a.h.setVisibility(0);
                }
                if (!this.a.l) {
                    this.a.l = true;
                    if (this.a.n == null || !this.a.n.isEmpty()) {
                        this.a.d();
                    }
                }
            }
        }
    };
    private net.tsz.afinal.a.a y = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ DingMeActivity a;

        {
            this.a = r1;
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.l = false;
            this.a.h.setVisibility(8);
            this.a.i = an.a(this.a.b, this.a.b.getString(R.string.time_out), -1);
            this.a.i.show();
        }

        public void onSuccess(Object obj) {
            super.onSuccess(obj);
            this.a.l = false;
            if (obj == null) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            String obj2 = obj.toString();
            if (TextUtils.isEmpty(obj2)) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            DingMeData dingMeData = null;
            if (this.a.r == 0) {
                dingMeData = z.p(obj2);
            } else if (this.a.r == 1 || this.a.r == 2) {
                dingMeData = z.o(obj2);
            }
            if (dingMeData != null) {
                this.a.p = dingMeData.getInfo().np;
                Collection list = dingMeData.getList();
                if (list == null || list.isEmpty()) {
                    this.a.k = false;
                    this.a.i = an.a(this.a.b, this.a.b.getString(R.string.no_more_data), -1);
                    this.a.i.show();
                } else {
                    this.a.n.addAll(list);
                    this.a.j.notifyDataSetChanged();
                }
                if (this.a.p <= 0) {
                    this.a.k = false;
                }
                this.a.h.setVisibility(8);
            }
        }
    };
    private net.tsz.afinal.a.a z = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ DingMeActivity a;

        {
            this.a = r1;
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.g.setVisibility(8);
            this.a.i = an.a(this.a.b, this.a.b.getString(R.string.time_out), -1);
            this.a.i.show();
        }

        public void onSuccess(Object obj) {
            super.onSuccess(obj);
            this.a.g.setVisibility(8);
            if (obj == null) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            String obj2 = obj.toString();
            if (TextUtils.isEmpty(obj2)) {
                Toast.makeText(this.a, "数据为空", 0).show();
                return;
            }
            if (this.a.r == 0) {
                this.a.m = z.p(obj2);
            } else if (this.a.r == 1 || this.a.r == 2) {
                this.a.m = z.o(obj2);
            }
            if (this.a.m != null) {
                this.a.p = this.a.m.getInfo().np;
                if (this.a.p <= 0) {
                    this.a.k = false;
                }
                this.a.n = this.a.m.getList();
                if (this.a.n == null) {
                    this.a.n = new ArrayList();
                }
                if (this.a.j == null) {
                    this.a.j = new a(this.a);
                    this.a.f.setAdapter(this.a.j);
                    return;
                }
                this.a.j.notifyDataSetChanged();
            }
        }
    };

    private class a extends BaseAdapter {
        f a = new f();
        b b = null;
        com.budejie.www.g.b c;
        final /* synthetic */ DingMeActivity d;

        public a(DingMeActivity dingMeActivity) {
            this.d = dingMeActivity;
            this.c = new com.budejie.www.g.b(dingMeActivity.b);
        }

        public int getCount() {
            return this.d.n.size();
        }

        public Object getItem(int i) {
            return this.d.n.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                this.b = new b();
                view = this.d.b.getLayoutInflater().inflate(R.layout.mynews_cmt_system_item, null);
                this.b.a = (AsyncImageView) view.findViewById(R.id.ding_Profile_imageview);
                this.b.b = (TextView) view.findViewById(R.id.news_title);
                this.b.c = (TextView) view.findViewById(R.id.news_time);
                this.b.d = view.findViewById(R.id.list_divider);
                view.setTag(this.b);
            } else {
                this.b = (b) view.getTag();
            }
            DingNewsItem dingNewsItem = (DingNewsItem) this.d.n.get(i);
            this.b.b.setText(dingNewsItem.getName() + dingNewsItem.getTitle());
            this.b.c.setText(dingNewsItem.getTime());
            this.b.a.setPostAvatarImage(dingNewsItem.getHeader());
            if (i == this.d.n.size() - 1) {
                this.b.d.setVisibility(8);
            }
            Bundle bundle = new Bundle();
            bundle.putString(PersonalProfileActivity.c, dingNewsItem.getUid());
            this.b.a.setOnClickListener(this.c.a(7, bundle));
            return view;
        }
    }

    static class b {
        AsyncImageView a;
        TextView b;
        TextView c;
        View d;

        b() {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mynews_layout);
        this.b = this;
        a();
        this.s = getIntent().getStringExtra("comment_id");
        b();
        c();
    }

    private void a() {
        Intent intent = getIntent();
        this.r = intent.getIntExtra("page_type", -1);
        if (this.r == 0) {
            this.q = "投票的人";
            this.u = intent.getStringExtra("post_id");
            this.v = intent.getStringExtra("vote_id");
            this.s = intent.getStringExtra("comment_id");
        } else if (this.r == 1) {
            this.q = "顶";
            this.s = intent.getStringExtra("comment_id");
            this.t = "like";
        } else if (this.r == 2) {
            this.q = "踩";
            this.s = intent.getStringExtra("comment_id");
            this.t = "hate";
        } else {
            finish();
        }
    }

    private void b() {
        this.c = (TextView) findViewById(R.id.title_center_txt);
        this.d = (Button) findViewById(R.id.title_left_btn);
        this.e = (LinearLayout) findViewById(R.id.left_layout);
        this.f = (CustomListView) findViewById(R.id.mynews_listview);
        this.g = findViewById(R.id.loadingDialog);
        this.c.setText(this.q);
        this.e.setVisibility(0);
        this.d.setVisibility(0);
        this.e.setOnClickListener(this.w);
        this.d.setOnClickListener(this.w);
        this.f.setonLoadListener(this.x);
        this.f.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DingMeActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    DingNewsItem dingNewsItem = (DingNewsItem) this.a.n.get(i - 1);
                    Intent intent = new Intent(this.a.b, PersonalProfileActivity.class);
                    intent.putExtra(PersonalProfileActivity.c, dingNewsItem.getUid());
                    this.a.b.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.h = this.f.getFootView();
        if (this.h != null) {
            this.h.setVisibility(8);
        }
        this.o = new c(this, null);
    }

    private void c() {
        if (an.a((Context) this)) {
            this.g.setVisibility(0);
            this.p = 0;
            if (this.r == 0) {
                BudejieApplication.a.a(RequstMethod.GET, j.c(this.u, this.v, this.s, this.p + ""), new j(this), this.z);
                return;
            } else if (this.r == 1 || this.r == 2) {
                this.o.a(this.t, this.s, this.p + "", this.z);
                return;
            } else {
                return;
            }
        }
        this.i = an.a((Activity) this, getString(R.string.nonet), -1);
        this.i.show();
    }

    private void d() {
        if (!an.a((Context) this)) {
            this.i = an.a((Activity) this, getString(R.string.nonet), -1);
            this.i.show();
            this.h.setVisibility(8);
        } else if (this.r == 0) {
            BudejieApplication.a.a(RequstMethod.GET, j.c(this.u, this.v, this.s, this.p + ""), new j(this), this.y);
        } else if (this.r == 1 || this.r == 2) {
            this.o.a(this.t, this.s, this.p + "", this.y);
        }
    }
}
