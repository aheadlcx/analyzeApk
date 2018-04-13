package com.budejie.www.activity.noticesetting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.MyInfoActivity;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.adapter.a.g;
import com.budejie.www.bean.NotificationSettingItem;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.type.UpdateUserInfo;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class NotificationSettingsActivity extends BaseTitleActivity implements a {
    private Activity a;
    private SharedPreferences b;
    private SharedPreferences c;
    private Editor d;
    private Toast e;
    private List<NotificationSettingItem> i;
    private g j;
    private XListView k;
    private net.tsz.afinal.a.a<String> l = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ NotificationSettingsActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.e = an.a(this.a.a, this.a.getString(R.string.load_failed), -1);
            this.a.e.show();
        }

        public void a(String str) {
            try {
                this.a.i = ((UpdateUserInfo) new Gson().fromJson(str, UpdateUserInfo.class)).getSwitchsList();
                if (this.a.i == null || this.a.i.size() <= 0) {
                    this.a.j();
                }
                MyInfoActivity.d = this.a.i;
                this.a.i();
                this.a.j = new g(this.a.a, this.a.i);
                this.a.k.setAdapter(this.a.j);
            } catch (Exception e) {
                e.printStackTrace();
                this.a.e = an.a(this.a.a, this.a.getString(R.string.load_failed), -1);
                this.a.e.show();
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.notification_settings);
        this.a = this;
        this.c = getSharedPreferences("weiboprefer", 0);
        this.b = getSharedPreferences("config", 0);
        c();
        g();
    }

    private void c() {
        this.k = (XListView) findViewById(R.id.listview);
        this.k.setPullLoadEnable(false);
        this.k.setPullRefreshEnable(false);
        a((int) R.string.noti_setting);
    }

    private void g() {
        this.i = MyInfoActivity.d;
        if (this.i == null || this.i.size() == 0) {
            h();
            return;
        }
        i();
        this.j = new g(this.a, this.i);
        this.k.setAdapter(this.j);
    }

    protected void onDestroy() {
        super.onDestroy();
        h();
    }

    private void h() {
        Object string = this.c.getString("id", "");
        if (!TextUtils.isEmpty(string)) {
            j jVar = new j();
            BudejieApplication.a.a(RequstMethod.GET, j.e(), j.s(this.a, string), this.l);
        }
    }

    private void i() {
        if (this.i != null && this.i.size() > 0) {
            this.d = this.b.edit();
            for (NotificationSettingItem notificationSettingItem : this.i) {
                this.d.putInt(notificationSettingItem.getDb_key(), notificationSettingItem.getUser_setting());
            }
            this.d.commit();
        }
    }

    private void j() {
        this.i = new ArrayList();
        this.i.add(new NotificationSettingItem("push_comment", "帖子被评论", 1, this.b.getInt("push_comment", 0)));
        this.i.add(new NotificationSettingItem("push_reply", "评论被回复", 1, this.b.getInt("push_comment", 0)));
        this.i.add(new NotificationSettingItem("push_commentup", "评论被顶", 1, this.b.getInt("push_comment", 0)));
        this.i.add(new NotificationSettingItem("push_addfans", "新增粉丝", 1, this.b.getInt("push_comment", 0)));
        this.i.add(new NotificationSettingItem("push_praise", "主页被赞", 1, this.b.getInt("push_comment", 0)));
        this.i.add(new NotificationSettingItem("push_tiezi", "帖子被赞", 1, this.b.getInt("push_comment", 0)));
    }

    public void a() {
    }

    public void b() {
    }
}
