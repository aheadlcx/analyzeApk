package com.budejie.www.activity.label;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import java.util.List;
import net.tsz.afinal.a.a;

public class LabelsSelectActivity extends BaseActvityWithLoadDailog {
    private XListView a;
    private i b;
    private Toast c;
    private Handler d = new Handler();
    private OnItemClickListener e = new OnItemClickListener(this) {
        final /* synthetic */ LabelsSelectActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            LabelBean labelBean = (LabelBean) this.a.a.getItemAtPosition(i);
            if (labelBean != null) {
                Intent intent = new Intent();
                intent.putExtra("theme_id_key", labelBean.getTheme_id());
                intent.putExtra("theme_name_key", labelBean.getTheme_name());
                this.a.setResult(20, intent);
                this.a.b();
            }
        }
    };
    private a<String> f = new a<String>(this) {
        final /* synthetic */ LabelsSelectActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void a(String str) {
            this.a.a.b();
            List c = f.c(str);
            if (c == null) {
                this.a.c = an.a(this.a, this.a.getString(R.string.no_label), -1);
                this.a.c.show();
                return;
            }
            this.a.b.a(c);
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.a.b();
            this.a.c = an.a(this.a, this.a.getString(R.string.load_failed), -1);
            this.a.c.show();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_labelsselect_layout);
        d(R.id.navigation_bar);
        setTitle((int) R.string.add_label);
        c(null, "取消");
        this.b = new i(this);
        this.a = (XListView) findViewById(R.id.listview);
        this.a.setAdapter(this.b);
        this.a.setPullRefreshEnable(true);
        this.a.setPullLoadEnable(false);
        this.a.setOnItemClickListener(this.e);
        this.a.setXListViewListener(new XListView.a(this) {
            final /* synthetic */ LabelsSelectActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a();
            }

            public void b() {
            }
        });
        this.d.postDelayed(new Runnable(this) {
            final /* synthetic */ LabelsSelectActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a.d();
            }
        }, 200);
    }

    private void c(final OnClickListener onClickListener, String str) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_title, null);
        textView.setText(str);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LabelsSelectActivity b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.b();
                }
            }
        });
        if (this.g != null) {
            this.g.setLeftView(textView);
        }
    }

    private void a() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().l(this, "0"), this.f);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        b();
        return true;
    }

    private void b() {
        finish();
    }
}
