package com.budejie.www.http;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.video.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.d;
import com.budejie.www.c.h;
import com.budejie.www.util.i;

public class f {
    Activity a;
    d b;
    h c;
    j d;

    public f(Activity activity) {
        this.a = activity;
        if (this.b == null) {
            this.b = new d(activity);
        }
        if (this.c == null) {
            this.c = new h(activity);
        }
        this.d = new j();
    }

    public void a(View view, ListItemObject listItemObject, Handler handler) {
        Object obj = 1;
        try {
            boolean isForwardAndCollect_isweixin = listItemObject.isForwardAndCollect_isweixin();
            if (!(TextUtils.isEmpty(listItemObject.getImgUrl()) || a.a())) {
                handler.sendEmptyMessage(10);
                obj = null;
            }
            if (obj != null) {
                new Thread(new f$1(this, listItemObject, isForwardAndCollect_isweixin, handler)).start();
                BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "get", this.d.a(this.a, listItemObject), null, null, -1);
            } else if (!isForwardAndCollect_isweixin) {
                handler.sendEmptyMessage(6);
                handler.sendEmptyMessage(12);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(ListItemObject listItemObject, Handler handler, String str) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "get", this.d.a(this.a, listItemObject, str), null, null, -1);
    }

    public static void a(Activity activity, Handler handler, String str, String str2) {
        Context context = activity;
        BudejieApplication.a.a(context, "https://api.budejie.com/api/api_open.php", "get", new j().l(activity, str, str2), null, handler, 1001);
    }

    public static void b(Activity activity, Handler handler, String str, String str2) {
        Context context = activity;
        BudejieApplication.a.a(context, "https://api.budejie.com/api/api_open.php", "get", new j().m(activity, str, str2), null, handler, 1004);
    }

    public static void a(Activity activity, Handler handler, String str, String str2, boolean z) {
        Context context = activity;
        BudejieApplication.a.a(context, "http://api.budejie.com/api/api_open.php", "get", new j().a(activity, str, str2, z), null, handler, 1003);
    }

    public void a(int i, int i2, View view, boolean z, int i3, View view2) {
        int i4 = i3 - 60;
        float f = (((float) i2) / ((float) i)) * ((float) i4);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i4, (int) f);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i4, (int) f);
        layoutParams.gravity = 17;
        layoutParams.topMargin = 5;
        layoutParams.bottomMargin = 5;
        layoutParams2.gravity = 17;
        layoutParams2.leftMargin = 0;
        layoutParams2.topMargin = 5;
        layoutParams2.rightMargin = 0;
        layoutParams2.bottomMargin = 5;
        view.setLayoutParams(layoutParams);
        if (view2 != null) {
            view2.setLayoutParams(layoutParams2);
        }
    }

    public void b(int i, int i2, View view, boolean z, int i3, View view2) {
        int i4;
        int i5 = i3 - 60;
        if (i == 0 || i2 == 0 || i2 >= i) {
            i4 = i5;
        } else {
            i4 = (i2 * i5) / i;
        }
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i5, i4);
        layoutParams.gravity = 17;
        layoutParams.topMargin = 5;
        layoutParams.bottomMargin = 5;
        view.setLayoutParams(layoutParams);
        if (view2 != null) {
            layoutParams = new FrameLayout.LayoutParams(i5, i4);
            layoutParams.gravity = 17;
            layoutParams.leftMargin = 0;
            layoutParams.topMargin = 5;
            layoutParams.rightMargin = 0;
            layoutParams.bottomMargin = 5;
            view2.setLayoutParams(layoutParams);
        }
    }

    public void a(View view, int i) {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(i - 60, -2);
        layoutParams.gravity = 17;
        layoutParams.leftMargin = 5;
        layoutParams.rightMargin = 5;
        view.setLayoutParams(layoutParams);
    }

    public void a(String str, Handler handler, ListItemObject listItemObject) {
        this.c.a(listItemObject, str);
        if (!"ding".equals(str) && "cai".equals(str)) {
            handler.sendEmptyMessage(2);
        }
    }

    public int a(View view) {
        int b = (int) (((float) 30) * i.a().b(this.a));
        Bitmap decodeResource = BitmapFactory.decodeResource(this.a.getResources(), R.drawable.playbutton_normal_bg);
        b = (decodeResource.getHeight() - b) / 2;
        view.setLayoutParams(new LinearLayout.LayoutParams(-2, b));
        decodeResource.recycle();
        return b;
    }

    public void a(TextView textView, int i) {
        float b = ((float) 30) - (((float) 10) * i.a().b(this.a));
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 5, (int) b, i + 5);
        layoutParams.gravity = 85;
        textView.setLayoutParams(layoutParams);
    }

    public void b(TextView textView, int i) {
        float b = ((float) 30) - (((float) 10) * i.a().b(this.a));
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 5, (int) b, 0);
        layoutParams.gravity = 53;
        textView.setLayoutParams(layoutParams);
    }
}
