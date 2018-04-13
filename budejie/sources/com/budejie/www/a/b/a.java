package com.budejie.www.a.b;

import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.util.an;
import java.io.IOException;
import okhttp3.aa;
import okhttp3.t;

public class a implements t {
    public aa intercept(okhttp3.t.a aVar) throws IOException {
        return aVar.a(aVar.a().e().b("ver", "6.9.2").b("client", AlibcConstants.PF_ANDROID).b("market", "xiaomi").b("udid", an.e(BudejieApplication.g)).b("mac", an.h(BudejieApplication.g)).b("os", an.a()).b("appname", "baisibudejie").b("User-Agent", an.c()).b("visiting", an.f(BudejieApplication.g)).b("cookie", NetWorkUtil.b(BudejieApplication.g)).b());
    }
}
