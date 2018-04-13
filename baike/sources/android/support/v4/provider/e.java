package android.support.v4.provider;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.provider.FontsContractCompat.FontFamilyResult;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import android.support.v4.provider.FontsContractCompat.FontRequestCallback;

final class e implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ FontRequest b;
    final /* synthetic */ Handler c;
    final /* synthetic */ FontRequestCallback d;

    e(Context context, FontRequest fontRequest, Handler handler, FontRequestCallback fontRequestCallback) {
        this.a = context;
        this.b = fontRequest;
        this.c = handler;
        this.d = fontRequestCallback;
    }

    public void run() {
        try {
            FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(this.a, null, this.b);
            if (fetchFonts.getStatusCode() != 0) {
                switch (fetchFonts.getStatusCode()) {
                    case 1:
                        this.c.post(new g(this));
                        return;
                    case 2:
                        this.c.post(new h(this));
                        return;
                    default:
                        this.c.post(new i(this));
                        return;
                }
            }
            FontInfo[] fonts = fetchFonts.getFonts();
            if (fonts == null || fonts.length == 0) {
                this.c.post(new j(this));
                return;
            }
            int resultCode;
            for (FontInfo fontInfo : fonts) {
                if (fontInfo.getResultCode() != 0) {
                    resultCode = fontInfo.getResultCode();
                    if (resultCode < 0) {
                        this.c.post(new k(this));
                        return;
                    } else {
                        this.c.post(new l(this, resultCode));
                        return;
                    }
                }
            }
            Typeface buildTypeface = FontsContractCompat.buildTypeface(this.a, null, fonts);
            if (buildTypeface == null) {
                this.c.post(new m(this));
            } else {
                this.c.post(new n(this, buildTypeface));
            }
        } catch (NameNotFoundException e) {
            this.c.post(new f(this));
        }
    }
}
