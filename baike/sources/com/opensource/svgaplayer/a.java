package com.opensource.svgaplayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class a implements Runnable {
    final /* synthetic */ SVGADynamicEntity a;
    final /* synthetic */ String b;
    final /* synthetic */ Handler c;
    final /* synthetic */ String d;

    a(SVGADynamicEntity sVGADynamicEntity, String str, Handler handler, String str2) {
        this.a = sVGADynamicEntity;
        this.b = str;
        this.c = handler;
        this.d = str2;
    }

    public final void run() {
        try {
            URLConnection openConnection = new URL(this.b).openConnection();
            if (!(openConnection instanceof HttpURLConnection)) {
                openConnection = null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            if (httpURLConnection != null) {
                httpURLConnection.setConnectTimeout(20000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                Bitmap decodeStream = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                if (decodeStream != null) {
                    this.c.post(new b(decodeStream, this));
                }
                httpURLConnection.getInputStream().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
