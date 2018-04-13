package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.adapter.a.h;
import com.budejie.www.bean.PayHistoryItem;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import java.util.List;
import net.tsz.afinal.a.a;

public class PayHistoryActivity extends BaseTitleActivity {
    private ListView a;
    private h b;
    private Dialog c;
    private Activity d;
    private String e;
    private SharedPreferences i;
    private List<PayHistoryItem> j = null;
    private a<String> k = new a<String>(this) {
        final /* synthetic */ PayHistoryActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            try {
                this.a.c.show();
            } catch (Exception e) {
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            if (this.a.c.isShowing()) {
                this.a.c.dismiss();
            }
        }

        public void a(String str) {
            super.onSuccess(str);
            if (this.a.c.isShowing()) {
                this.a.c.dismiss();
            }
            new AsyncTask<String, String, List<PayHistoryItem>>(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((List) obj);
                }

                protected void a(List<PayHistoryItem> list) {
                    this.a.a.l.sendEmptyMessage(0);
                }

                protected List<PayHistoryItem> a(String... strArr) {
                    try {
                        this.a.a.j = com.budejie.www.j.a.h(this.a.a.d, strArr[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return this.a.a.j;
                }
            }.execute(new String[]{str});
        }
    };
    private Handler l = new Handler(this) {
        final /* synthetic */ PayHistoryActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.b.a(this.a.j);
            this.a.a.setAdapter(this.a.b);
            this.a.b.notifyDataSetChanged();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.pay_history_layout);
        this.d = this;
        a((int) R.string.vip_pay_history_title);
        b();
        c();
        a();
    }

    private void a() {
        BudejieApplication.a.a(RequstMethod.GET, "http://d.api.budejie.com/order/list/", j.f(this, "0", GiftConfigUtil.SUPER_GIRL_GIFT_TAG, this.e), this.k);
    }

    private void b() {
        this.c = new Dialog(this, R.style.dialogTheme);
        this.c.setContentView(R.layout.loaddialog);
        this.c.setCanceledOnTouchOutside(true);
        this.i = getSharedPreferences("weiboprefer", 0);
        this.e = this.i.getString("id", "");
    }

    private void c() {
        this.a = (ListView) findViewById(R.id.listview);
        this.b = new h(this);
    }

    public void onLeftClick(View view) {
        super.onLeftClick(view);
    }

    protected void onResume() {
        super.onResume();
        if (this.b != null) {
            this.b.notifyDataSetChanged();
        }
    }
}
