package com.tencent.stat;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;
import qsbk.app.widget.RefreshTipView;

class e extends DefaultConnectionKeepAliveStrategy {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        long keepAliveDuration = super.getKeepAliveDuration(httpResponse, httpContext);
        return keepAliveDuration == -1 ? RefreshTipView.FIRST_REFRESH_INTERVAL : keepAliveDuration;
    }
}
