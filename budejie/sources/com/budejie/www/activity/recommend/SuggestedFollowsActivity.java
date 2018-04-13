package com.budejie.www.activity.recommend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.util.h;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.BudejieApplication.b;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.search.SearchMainActivity;
import com.budejie.www.adapter.a.q;
import com.budejie.www.bean.InviteFriendsResults;
import com.budejie.www.bean.SubscibeBean;
import com.budejie.www.bean.SubscibeResult;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.h.c;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.google.gson.Gson;
import com.tencent.weibo.sdk.android.api.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SuggestedFollowsActivity extends BaseActvityWithLoadDailog implements OnClickListener, b {
    public SharedPreferences a;
    public com.budejie.www.g.b b;
    private ListView c;
    private TextView d;
    private q e;
    private d f;
    private Toast h;
    private List<SubscibeBean> i;
    private n j;
    private a k;
    private IntentFilter l = new IntentFilter();
    private a m;
    private SubscibeBean n;
    private SubscibeBean o;
    private ImageView p;
    private AnimationDrawable q = null;
    private BudejieApplication r;
    private Handler s = new Handler(this) {
        final /* synthetic */ SuggestedFollowsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.a.d.setVisibility(0);
            }
        }
    };
    private net.tsz.afinal.a.a<String> t = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ SuggestedFollowsActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            Log.i("follows", str);
            if (TextUtils.isEmpty(str)) {
                this.a.h = an.a(this.a, this.a.getString(R.string.no_subscibe), -1);
                this.a.h.show();
                return;
            }
            try {
                List list = ((SubscibeResult) new Gson().fromJson(str, SubscibeResult.class)).list;
                if (list != null && !list.isEmpty()) {
                    this.a.i.addAll(list);
                    this.a.e.notifyDataSetChanged();
                    this.a.f.a(list);
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.a.h = an.a(this.a, this.a.getString(R.string.no_subscibe), -1);
                this.a.h.show();
            }
        }
    };
    private OnItemClickListener u = new OnItemClickListener(this) {
        final /* synthetic */ SuggestedFollowsActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.a.n != null) {
                this.a.n.isChecked = false;
            }
            SubscibeBean subscibeBean = (SubscibeBean) this.a.c.getItemAtPosition(i);
            if (subscibeBean != null) {
                Log.i("follows", subscibeBean.name);
                this.a.f.a(subscibeBean.name);
                subscibeBean.isChecked = true;
                this.a.e.notifyDataSetChanged();
                this.a.n = subscibeBean;
            }
        }
    };
    private OnClickListener v = new OnClickListener(this) {
        final /* synthetic */ SuggestedFollowsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.k = (a) this.a.getSupportFragmentManager().findFragmentByTag(this.a.getResources().getString(R.string.sf_friends));
            List a = this.a.k.a();
            if (!a.isEmpty()) {
                if (this.a.j.d(this.a)) {
                    this.a.a(a);
                } else {
                    BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.b(a), this.a.w);
                }
            }
        }
    };
    private net.tsz.afinal.a.a<String> w = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ SuggestedFollowsActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            this.a.f();
        }

        public void a(String str) {
            this.a.e();
            if (TextUtils.isEmpty(str)) {
                this.a.h = an.a(this.a, this.a.getString(R.string.invite_friends_failure), -1);
                this.a.h.show();
                return;
            }
            try {
                InviteFriendsResults inviteFriendsResults = (InviteFriendsResults) new Gson().fromJson(str, InviteFriendsResults.class);
                if (inviteFriendsResults.result.equals("0")) {
                    if (this.a.k != null) {
                        this.a.k.c();
                    }
                    this.a.h = an.a(this.a, this.a.getString(R.string.invite_friends_success), -1);
                    this.a.h.show();
                    return;
                }
                this.a.h = an.a(this.a, inviteFriendsResults.result_msg, -1);
                this.a.h.show();
            } catch (Exception e) {
                e.printStackTrace();
                this.a.h = an.a(this.a, this.a.getString(R.string.invite_friends_failure), -1);
                this.a.h.show();
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.e();
            this.a.h = an.a(this.a, this.a.getString(R.string.invite_friends_failure), -1);
            this.a.h.show();
        }
    };

    class a extends BroadcastReceiver {
        final /* synthetic */ SuggestedFollowsActivity a;

        a(SuggestedFollowsActivity suggestedFollowsActivity) {
            this.a = suggestedFollowsActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.budejie.SUB_SINA_RECOMMEND_UPDATE")) {
                if (HomeGroup.l < 0) {
                    HomeGroup.l = 0;
                }
                this.a.e.notifyDataSetChanged();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.suggested_follows_layout);
        d(R.id.navigation_bar);
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        a(null);
        setTitle((int) R.string.suggested_follows);
        View imageView = new ImageView(this);
        imageView.setImageResource(c.a().b(R.attr.recommend_title_search_icon));
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SuggestedFollowsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(new Intent(this.a, SearchMainActivity.class));
            }
        });
        this.g.setRightView(imageView);
        this.r = (BudejieApplication) getApplication();
        b();
        this.f = new d(this, R.id.followsDetailsContent);
        this.f.a(getResources().getString(R.string.suggested), c.class, new Bundle());
        this.f.a(getResources().getString(R.string.sf_friends), a.class, new Bundle());
        this.i = new ArrayList();
        SubscibeBean subscibeBean = new SubscibeBean();
        subscibeBean.name = getResources().getString(R.string.suggested);
        subscibeBean.id = "tag_type_recommend";
        this.i.add(subscibeBean);
        if (!TextUtils.isEmpty(ai.b(this))) {
            this.o = new SubscibeBean();
            this.o.name = getResources().getString(R.string.sf_friends);
            this.o.id = "tag_type_friend";
            this.i.add(this.o);
        }
        this.c = (ListView) findViewById(R.id.navListView);
        this.e = new q(this, this.i);
        this.c.setAdapter(this.e);
        this.c.setOnItemClickListener(this.u);
        this.c.setChoiceMode(1);
        g();
        this.p = (ImageView) findViewById(R.id.melodyview);
        this.q = (AnimationDrawable) this.p.getBackground();
        this.p.setOnClickListener(this);
        if ("bindPhone".equals(getIntent().getStringExtra("source"))) {
            this.f.a(this.o.name);
            this.e.a(1);
            this.o.isChecked = true;
            this.n = this.o;
        } else {
            this.f.a(subscibeBean.name);
            this.e.a(0);
            subscibeBean.isChecked = true;
            this.n = subscibeBean;
        }
        this.l.addAction("android.budejie.SUB_SINA_RECOMMEND_UPDATE");
        this.m = new a(this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (an.a(this.a) && this.i != null && this.i.size() > 1 && !"tag_type_friend".equals(((SubscibeBean) this.i.get(1)).id)) {
            SubscibeBean subscibeBean = new SubscibeBean();
            subscibeBean.name = getResources().getString(R.string.sf_friends);
            subscibeBean.id = "tag_type_friend";
            this.i.add(1, subscibeBean);
            this.e.notifyDataSetChanged();
        }
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(this.m, this.l);
        this.r.a((b) this);
        Status a = this.r.a();
        if (a != null) {
            a(a);
        }
        this.b = new com.budejie.www.g.b(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.m);
        } catch (Exception e) {
        }
    }

    private void b() {
        this.a = getSharedPreferences("weiboprefer", 0);
        this.j = new n(this);
        this.b = new com.budejie.www.g.b(this);
    }

    public SharedPreferences a() {
        if (this.a == null) {
            this.a = getSharedPreferences("weiboprefer", 0);
        }
        return this.a;
    }

    private void g() {
        BudejieApplication.a.a(RequstMethod.GET, j.i(), new j(this), this.t);
    }

    public void a(String str) {
        if ("no_invite_data".equals(str)) {
            h();
        } else {
            c(this.v, str);
        }
    }

    private void c(final OnClickListener onClickListener, String str) {
        if (this.d == null) {
            this.d = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_right_textview, null);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SuggestedFollowsActivity b;

                public void onClick(View view) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    } else {
                        this.b.finish();
                    }
                }
            });
            if (this.g != null) {
                this.g.setRightView(this.d);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            this.d.setText(str);
            this.s.sendEmptyMessage(1);
        }
    }

    private void h() {
        if (this.d != null) {
            this.d.setVisibility(8);
        }
    }

    private void a(List<SuggestedFollowsListItem> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SuggestedFollowsListItem suggestedFollowsListItem : list) {
                stringBuilder.append(suggestedFollowsListItem.phone + h.b);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + stringBuilder.toString()));
            intent.putExtra("sms_body", "最近发现了一个特别好玩的app，百思不得姐，里面的内容很给力，推荐你试试.http://www.budejie.com/budejie/?f=sms&d=android");
            startActivity(intent);
        }
    }

    private net.tsz.afinal.a.b b(List<SuggestedFollowsListItem> list) {
        String str;
        String str2;
        String str3 = null;
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (SuggestedFollowsListItem suggestedFollowsListItem : list) {
            int i2 = suggestedFollowsListItem.plat_flag;
            stringBuffer.append(suggestedFollowsListItem.social_name + ",");
            i = i2;
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        String stringBuffer2 = stringBuffer.toString();
        HashMap a = this.j.a(ai.b(this));
        if (i == 0) {
            str = "sina";
            str3 = (String) a.get("weibo_token");
            str2 = null;
        } else if (i == 1) {
            str2 = (String) a.get("qq_token");
            str = "qq";
            str3 = str2;
            str2 = Util.getSharePersistent(this, "OPEN_ID");
        } else {
            str2 = null;
            str = null;
        }
        return a(str, str3, str2, stringBuffer2);
    }

    private net.tsz.afinal.a.b a(String str, String str2, String str3, String str4) {
        return new j().a((Context) this, str, str2, str3, str4);
    }

    public void a(Status status) {
        int i = 0;
        switch (status) {
            case start:
                this.q.stop();
                this.q.start();
                this.p.setVisibility(0);
                return;
            case stop:
                this.q.stop();
                ImageView imageView = this.p;
                if (!an.b) {
                    i = 8;
                }
                imageView.setVisibility(i);
                return;
            case end:
                this.q.stop();
                this.p.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.melodyview:
                view.setTag(this.r.d());
                this.b.a(3, null).onClick(view);
                return;
            default:
                return;
        }
    }
}
