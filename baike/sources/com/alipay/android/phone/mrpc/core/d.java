package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;
import qsbk.app.widget.RefreshTipView;

class d implements ConnectionKeepAliveStrategy {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return RefreshTipView.SECOND_REFRESH_INTERVAL;
    }
}
