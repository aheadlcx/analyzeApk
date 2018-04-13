package com.b;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import com.budejie.www.bean.UserItem;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.budejie.www.util.p.a;
import com.spriteapp.booklibrary.listener.ChannelListener;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

class a$1 implements ChannelListener {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void toLoginPage(Context context) {
        UserItem g = an.g(context);
        if (g != null) {
            a.a(g);
        } else {
            an.a((Activity) context, 0, null, null, 0);
        }
    }

    public void showShareDialog(final Context context, BookDetailResponse bookDetailResponse, boolean z) {
        if (this.a.a == null) {
            this.a.a = Tencent.createInstance("100336987", context);
        }
        if (a.a(this.a) == null) {
            a.a(this.a, WXAPIFactory.createWXAPI(context, "wx592fdc48acfbe290"));
            a.a(this.a).registerApp("wx592fdc48acfbe290");
        }
        boolean isWXAppInstalled = a.a(this.a).isWXAppInstalled();
        boolean z2 = a.a(this.a).getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT;
        a.a(this.a, new b());
        a.b(this.a).a(bookDetailResponse);
        p.a(context, isWXAppInstalled, z2, a.d(), z, new a(this) {
            final /* synthetic */ a$1 b;

            public void a(int i, Dialog dialog) {
                switch (i) {
                    case 1:
                        a.b(this.b.a).a(context);
                        break;
                    case 3:
                        a.b(this.b.a).a(context, a.a(this.b.a), true);
                        break;
                    case 4:
                        a.b(this.b.a).a(context, a.a(this.b.a), false);
                        break;
                    case 6:
                        a.b(this.b.a).b(context, this.b.a.a);
                        break;
                    case 7:
                        a.b(this.b.a).b(context);
                        break;
                    case 8:
                        a.b(this.b.a).a(context, this.b.a.a);
                        break;
                }
                dialog.cancel();
            }
        });
    }
}
