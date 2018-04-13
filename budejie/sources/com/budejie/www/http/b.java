package com.budejie.www.http;

import android.app.Activity;
import android.content.Context;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.f.a;

public class b {
    private Context a;
    private a b;
    private j c = new j();

    private b(Context context, a aVar) {
        this.a = context;
        this.b = aVar;
    }

    public static b a(Activity activity, a aVar) {
        return new b(activity.getApplicationContext(), aVar);
    }

    public void a(String str, String str2, String str3, int i) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "get", this.c.b(this.a, str, str2, str3), this.b, null, i);
    }
}
