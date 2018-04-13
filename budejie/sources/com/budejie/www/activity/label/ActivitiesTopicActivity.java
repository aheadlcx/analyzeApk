package com.budejie.www.activity.label;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.widget.PullToRefreshView;
import com.budejie.www.widget.PullToRefreshView.a;
import com.budejie.www.widget.PullToRefreshView.b;
import java.util.List;

public class ActivitiesTopicActivity extends BaseActvityWithLoadDailog {
    private PullToRefreshView a;
    private GridView b;
    private a c;
    private int d;
    private Toast e;
    private boolean f;
    private b h = new b(this) {
        final /* synthetic */ ActivitiesTopicActivity a;

        {
            this.a = r1;
        }

        public void a(PullToRefreshView pullToRefreshView) {
            this.a.a();
        }
    };
    private a i = new a(this) {
        final /* synthetic */ ActivitiesTopicActivity a;

        {
            this.a = r1;
        }

        public void a(PullToRefreshView pullToRefreshView) {
            this.a.d = this.a.d + 1;
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.a(this.a.d), this.a.j);
        }
    };
    private net.tsz.afinal.a.a<String> j = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ ActivitiesTopicActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            this.a.e();
            this.a.a.b();
            this.a.a.c();
            b b = f.b(str);
            if (b == null) {
                this.a.e = an.a(this.a, this.a.getString(R.string.load_failed), -1);
                this.a.e.show();
                return;
            }
            List list = b.a;
            if (list == null) {
                this.a.e = an.a(this.a, this.a.getString(R.string.no_more_data), -1);
                this.a.e.show();
            } else if (this.a.f) {
                this.a.f = false;
                this.a.c.a(list);
            } else {
                this.a.c.b(list);
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.e();
            this.a.a.b();
            this.a.a.c();
            this.a.e = an.a(this.a, this.a.getString(R.string.load_failed), -1);
            this.a.e.show();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_activities_topic);
        d(R.id.navigation_bar);
        a(null);
        setTitle((int) R.string.activities_homepage);
        c(null);
        this.f = false;
        this.a = (PullToRefreshView) findViewById(R.id.activitiesTopic_refresh_view);
        this.a.setOnHeaderRefreshListener(this.h);
        this.a.setOnFooterRefreshListener(this.i);
        this.b = (GridView) findViewById(R.id.activitiesTopicGridView);
        this.c = new a(this);
        this.b.setAdapter(this.c);
        f();
        a();
    }

    private void c(final OnClickListener onClickListener) {
        ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.navigation_refresh_view, null);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ActivitiesTopicActivity b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                    return;
                }
                this.b.a.a();
                this.b.b.smoothScrollToPosition(0);
            }
        });
        if (this.g != null) {
            this.g.setRightView(imageView);
        }
    }

    private void a() {
        this.f = true;
        this.d = 1;
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", a(this.d), this.j);
    }

    private net.tsz.afinal.a.b a(int i) {
        return new j().k(this, i + "");
    }
}
