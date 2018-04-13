package com.budejie.www.activity.a;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.mycomment.d;
import com.budejie.www.activity.textcomment.MakeTextCommentsActivity;
import com.budejie.www.adapter.e.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.NewCommentItem;
import com.budejie.www.c.e;
import com.budejie.www.c.h;
import com.budejie.www.g.b;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.c;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.widget.CmtPopupWindow;
import com.budejie.www.widget.ExpertXListView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class f extends d {
    public List<d> a = new ArrayList();
    private PersonalProfileActivity b;
    private e c;
    private ExpertXListView d;
    private ListItemObject e;
    private String f;
    private CmtPopupWindow g;
    private c h;
    private e i;
    private b j;
    private com.budejie.www.c.d k;
    private h l;
    private b m;
    private String n = "0";
    private a o;
    private a p = new f$1(this);
    private net.tsz.afinal.a.a<String> q = new f$4(this);
    private net.tsz.afinal.a.a<String> r = new f$5(this);
    private net.tsz.afinal.a.a<String> s = new f$6(this);

    public void b(String str) {
        this.f = str;
    }

    public f(Activity activity) {
        if (activity instanceof PersonalProfileActivity) {
            this.b = (PersonalProfileActivity) activity;
            this.o = this.b.e();
            this.g = new CmtPopupWindow(this.b);
            this.h = new c(this.b, null);
            this.i = new e(this.b);
            this.g.a(this.h);
            this.d = this.b.d();
            this.f = this.b.c();
            this.m = new b();
        }
        this.j = new b(this.b);
        this.l = new h(this.b);
        this.k = new com.budejie.www.c.d(this.b);
    }

    public void a(e eVar) {
        this.c = eVar;
    }

    public void a() {
        boolean z = true;
        this.m.a(1);
        NetWorkUtil netWorkUtil = BudejieApplication.a;
        RequstMethod requstMethod = RequstMethod.GET;
        String str = this.f;
        String str2 = "0";
        if (!(this.b.f() || this.c.a())) {
            z = false;
        }
        netWorkUtil.a(requstMethod, j.a(str, str2, z), new j(this.b), this.q);
    }

    public void a(Object... objArr) {
        this.m.b(3);
        NetWorkUtil netWorkUtil = BudejieApplication.a;
        RequstMethod requstMethod = RequstMethod.GET;
        String str = this.f;
        String str2 = this.n;
        boolean z = this.b.f() || this.c.a();
        netWorkUtil.a(requstMethod, j.a(str, str2, z), new j(this.b), this.r);
    }

    public b b() {
        return this.m;
    }

    public a c() {
        return this.p;
    }

    private NewCommentItem a(ListItemObject listItemObject) {
        if (listItemObject == null || listItemObject.getHotcmt() == null) {
            return null;
        }
        NewCommentItem newCommentItem = new NewCommentItem();
        newCommentItem.id = listItemObject.getHotcmt().id;
        newCommentItem.data_id = listItemObject.getHotcmt().data_id;
        newCommentItem.status = listItemObject.getHotcmt().status;
        newCommentItem.content = listItemObject.getHotcmt().content;
        newCommentItem.ctime = listItemObject.getHotcmt().ctime;
        newCommentItem.precid = listItemObject.getHotcmt().precid;
        newCommentItem.preuid = listItemObject.getHotcmt().preuid;
        newCommentItem.like_count = listItemObject.getHotcmt().like_count;
        newCommentItem.voiceuri = listItemObject.getHotcmt().voiceuri;
        newCommentItem.voicetime = listItemObject.getHotcmt().voicetime;
        newCommentItem.user = listItemObject.getHotcmt().user;
        return newCommentItem;
    }

    private void b(ListItemObject listItemObject) {
        Serializable a = a(listItemObject);
        Intent intent = new Intent(this.b, MakeTextCommentsActivity.class);
        intent.putExtra("posts_id", listItemObject.getWid());
        intent.putExtra("reply_comment", a);
        this.b.startActivity(intent);
        this.b.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void c(ListItemObject listItemObject) {
        String str = listItemObject.getHotcmt().id;
        this.h.a(this.b, listItemObject.getWid(), str, this.f);
    }

    private void c(String str) {
        Dialog dialog = new Dialog(this.b, R.style.dialogTheme);
        View inflate = this.b.getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn);
        Button button2 = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        button2.setTag(str);
        button.setOnClickListener(new f$2(this, dialog));
        button2.setOnClickListener(new f$3(this, str, dialog));
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = an.a(this.b, 300);
        window.setAttributes(attributes);
        dialog.show();
    }

    private void d(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", a(this.f, str), this.s);
    }

    private void a(List<d> list) {
        List a = this.i.a();
        if (a.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                d dVar = (d) list.get(i);
                if (dVar != null && dVar.a != null) {
                    for (int i2 = 0; i2 < a.size(); i2++) {
                        String str = (String) a.get(i2);
                        if (!TextUtils.isEmpty(str)) {
                            String[] split = str.split("#");
                            String str2 = split[0];
                            str = split[1];
                            if (str2 != null && str2.equals(dVar.a.id)) {
                                dVar.a.setDingOrCai(str);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private net.tsz.afinal.a.b a(String str, String str2) {
        return new j().h(this.b, str, str2);
    }

    public void a(String str) {
    }
}
