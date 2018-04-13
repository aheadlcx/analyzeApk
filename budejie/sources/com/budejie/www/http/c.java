package com.budejie.www.http;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.c.e;
import com.budejie.www.f.a;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.an;
import com.tencent.smtt.utils.TbsLog;
import java.util.List;

public class c implements a {
    Activity a;
    a b;
    private j c = new j();

    public c(Activity activity, a aVar) {
        this.a = activity;
        this.b = aVar;
    }

    public void a(String str, String str2, String str3, String str4, int i, String str5) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "get", this.c.b(this.a, str, str3, str2, str5), this.b, null, i);
    }

    public void a(Context context, String str, String str2, String str3, net.tsz.afinal.a.a aVar) {
        BudejieApplication.a.a(RequstMethod.GET, j.c(str2, str, str3), new j(), aVar);
    }

    public void b(Context context, String str, String str2, String str3, net.tsz.afinal.a.a aVar) {
        BudejieApplication.a.a(RequstMethod.GET, j.d(str2, str, str3), new j(), aVar);
    }

    public void a(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, j.a(str, str2), new j(this.a), null);
    }

    public void a(Context context, String str, String str2, String str3) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "get", this.c.c(this.a, str2, str3, str), this, null, TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE);
    }

    public void a(int i, String str) {
        if (i == TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE) {
            Toast a;
            if ("0".equals(str)) {
                a = an.a(this.a, "举报成功", -1);
            } else {
                a = an.a(this.a, "举报失败或已经被您举报了", -1);
            }
            if (a != null) {
                a.show();
            }
        }
    }

    public void a(int i) {
    }

    public void a(String str, int i, int i2, net.tsz.afinal.a.a aVar) {
        BudejieApplication.a.a(RequstMethod.GET, j.i(str), new j(this.a), aVar);
    }

    public void b(String str, int i, int i2, net.tsz.afinal.a.a aVar) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.c.a(this.a, str, i, i2), aVar);
    }

    public void a(String str, String str2, net.tsz.afinal.a.a aVar) {
        if (TextUtils.isEmpty(str2)) {
            BudejieApplication.a.a(RequstMethod.POST, j.m(str), new j(this.a), aVar);
        } else {
            BudejieApplication.a.a(RequstMethod.POST, str2, new j(this.a), aVar);
        }
    }

    public void a(String str, String str2, String str3, net.tsz.afinal.a.a aVar) {
        BudejieApplication.a.a(RequstMethod.GET, j.e(str2, str, str3), new j(this.a), aVar);
    }

    public void a(e eVar, CommentItem commentItem) {
        List a = eVar.a();
        for (int i = 0; i < a.size(); i++) {
            String str = (String) a.get(i);
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split("#");
                Object obj = split[0];
                str = split[1];
                if (!TextUtils.isEmpty(obj) && obj.equals(commentItem.getId())) {
                    commentItem.setDingOrCai(str);
                    return;
                }
            }
        }
    }
}
