package qsbk.app.thirdparty.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qsbk.app.R;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyAuthListener;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.ThirdPartyDialogError;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.Utility.Utility;

public class ThirdPartyDialog extends Dialog {
    static LayoutParams a = new LayoutParams(-1, -1);
    private static int e = 16973840;
    private static int f = 0;
    private static int g = 0;
    private static int h = 0;
    private static int i = 0;
    protected ThirdPartyAuthListener b;
    protected ProgressDialog c;
    protected WebView d;
    private String j;
    private RelativeLayout k;
    private RelativeLayout l;

    private class a implements OnCancelListener {
        final /* synthetic */ ThirdPartyDialog a;

        private a(ThirdPartyDialog thirdPartyDialog) {
            this.a = thirdPartyDialog;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.b.onCancel();
        }
    }

    private class b extends WebViewClient {
        final /* synthetic */ ThirdPartyDialog a;

        private b(ThirdPartyDialog thirdPartyDialog) {
            this.a = thirdPartyDialog;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!str.startsWith("sms:")) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("address", str.replace("sms:", ""));
            intent.setType("vnd.android-dir/mms-sms");
            this.a.getContext().startActivity(intent);
            return true;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.a.b.onError(new ThirdPartyDialogError(str, i, str2));
            this.a.dismiss();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (str.startsWith(ThirdParty.redirecturl)) {
                this.a.b(webView, str);
                webView.stopLoading();
                this.a.dismiss();
                return;
            }
            super.onPageStarted(webView, str, bitmap);
            this.a.c.show();
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.a.c.isShowing()) {
                this.a.c.dismiss();
            }
            this.a.d.setVisibility(0);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }
    }

    private class c extends WebViewClient {
        final /* synthetic */ ThirdPartyDialog a;

        private c(ThirdPartyDialog thirdPartyDialog) {
            this.a = thirdPartyDialog;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.d("Weibo-WebView", "Redirect URL: " + str);
            if (!str.startsWith("sms:")) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("address", str.replace("sms:", ""));
            intent.setType("vnd.android-dir/mms-sms");
            this.a.getContext().startActivity(intent);
            return true;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.a.b.onError(new ThirdPartyDialogError(str, i, str2));
            this.a.dismiss();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (str.startsWith(ThirdParty.redirecturl)) {
                this.a.a(webView, str);
                webView.stopLoading();
                this.a.dismiss();
                return;
            }
            super.onPageStarted(webView, str, bitmap);
            this.a.c.show();
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.a.c.isShowing()) {
                this.a.c.dismiss();
            }
            this.a.d.setVisibility(0);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }
    }

    public ThirdPartyDialog(Context context, String str, ThirdPartyAuthListener thirdPartyAuthListener) {
        super(context, e);
        this.j = str;
        this.b = thirdPartyAuthListener;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = new ProgressDialog(getContext());
        this.c.requestWindowFeature(1);
        this.c.setMessage("正在加载...");
        this.c.setOnKeyListener(new a(this));
        requestWindowFeature(1);
        getWindow().setFeatureDrawableAlpha(0, 0);
        this.l = new RelativeLayout(getContext());
        b();
        addContentView(this.l, new ViewGroup.LayoutParams(-1, -1));
        if (d()) {
            this.l.setOnTouchListener(new b(this));
        }
        if (this.b != null) {
            setOnCancelListener(new a());
        }
    }

    private void a() {
        try {
            this.c.dismiss();
            if (this.d != null) {
                this.d.stopLoading();
                this.d.destroy();
            }
        } catch (Exception e) {
        }
        cancel();
    }

    private void b() {
        InputStream inputStream = null;
        this.k = new RelativeLayout(getContext());
        this.d = new WebView(getContext());
        this.d.setVerticalScrollBarEnabled(false);
        this.d.setHorizontalScrollBarEnabled(false);
        this.d.getSettings().setJavaScriptEnabled(true);
        this.d.setWebViewClient(getWebViewClient());
        this.d.loadUrl(this.j);
        this.d.setLayoutParams(a);
        this.d.setVisibility(4);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        this.l.setBackgroundColor(0);
        try {
            inputStream = getContext().getAssets().open("weibosdk_dialog_bg.9.png");
            float f = getContext().getResources().getDisplayMetrics().density;
            layoutParams2.leftMargin = (int) (10.0f * f);
            layoutParams2.topMargin = (int) (10.0f * f);
            layoutParams2.rightMargin = (int) (10.0f * f);
            layoutParams2.bottomMargin = (int) (f * 10.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            try {
                this.k.setBackgroundResource(R.drawable.weibosdk_dialog_bg);
            } catch (Exception e2) {
                e2.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        } else {
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            this.k.setBackgroundDrawable(new NinePatchDrawable(decodeStream, decodeStream.getNinePatchChunk(), new Rect(0, 0, 0, 0), null));
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e32) {
                e32.printStackTrace();
            }
        }
        this.k.addView(this.d, layoutParams2);
        this.k.setGravity(17);
        if (c()) {
            layoutParams.leftMargin = f;
            layoutParams.topMargin = g;
            layoutParams.rightMargin = h;
            layoutParams.bottomMargin = i;
        } else {
            Resources resources = getContext().getResources();
            layoutParams.leftMargin = resources.getDimensionPixelSize(R.dimen.thirdparty_dialog_left_margin);
            layoutParams.rightMargin = resources.getDimensionPixelSize(R.dimen.thirdparty_dialog_right_margin);
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.thirdparty_dialog_top_margin);
            layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.thirdparty_dialog_bottom_margin);
        }
        this.l.addView(this.k, layoutParams);
    }

    public WebViewClient getWebViewClient() {
        return ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equals(ThirdParty.thirdparty_type) ? new b() : new c();
    }

    private void a(WebView webView, String str) {
        Bundle parseUrl = Utility.parseUrl(str);
        String string = parseUrl.getString(com.umeng.analytics.pro.b.J);
        String string2 = parseUrl.getString("error_code");
        if (string == null && string2 == null) {
            this.b.onComplete(parseUrl);
        } else if (string.equals("access_denied")) {
            this.b.onCancel();
        } else if (string2 == null) {
            this.b.onThirdPartyException(new ThirdPartyException(string, 0));
        } else {
            this.b.onThirdPartyException(new ThirdPartyException(string, Integer.parseInt(string2)));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c() {
        /*
        r7 = this;
        r3 = 0;
        r1 = 1;
        r2 = 0;
        r0 = r7.getContext();
        r0 = r0.getAssets();
        r4 = r7.getContext();
        r4 = r4.getResources();
        r4 = r4.getDisplayMetrics();
        r4 = r4.density;
        r5 = "values/dimens.xml";
        r3 = r0.open(r5);	 Catch:{ IOException -> 0x00d6, all -> 0x00a9 }
        r5 = android.util.Xml.newPullParser();	 Catch:{ IOException -> 0x00db, all -> 0x00a9 }
        r0 = "utf-8";
        r5.setInput(r3, r0);	 Catch:{ XmlPullParserException -> 0x00e0 }
        r0 = r5.getEventType();	 Catch:{ XmlPullParserException -> 0x00e0 }
    L_0x002c:
        if (r0 == r1) goto L_0x00c7;
    L_0x002e:
        switch(r0) {
            case 2: goto L_0x0036;
            default: goto L_0x0031;
        };
    L_0x0031:
        r0 = r5.next();	 Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x002c;
    L_0x0036:
        r0 = r5.getName();	 Catch:{ XmlPullParserException -> 0x005f }
        r2 = "dimen";
        r0 = r0.equals(r2);	 Catch:{ XmlPullParserException -> 0x005f }
        if (r0 == 0) goto L_0x0031;
    L_0x0042:
        r0 = 0;
        r2 = "name";
        r0 = r5.getAttributeValue(r0, r2);	 Catch:{ XmlPullParserException -> 0x005f }
        r2 = "weibosdk_dialog_left_margin";
        r2 = r2.equals(r0);	 Catch:{ XmlPullParserException -> 0x005f }
        if (r2 == 0) goto L_0x006a;
    L_0x0051:
        r0 = r5.nextText();	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = (float) r0;	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = r0 * r4;
        r0 = (int) r0;	 Catch:{ XmlPullParserException -> 0x005f }
        f = r0;	 Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x0031;
    L_0x005f:
        r0 = move-exception;
    L_0x0060:
        r0.printStackTrace();	 Catch:{ IOException -> 0x0080, all -> 0x00a9 }
        r0 = r1;
    L_0x0064:
        if (r3 == 0) goto L_0x0069;
    L_0x0066:
        r3.close();	 Catch:{ IOException -> 0x00c9 }
    L_0x0069:
        return r0;
    L_0x006a:
        r2 = "weibosdk_dialog_top_margin";
        r2 = r2.equals(r0);	 Catch:{ XmlPullParserException -> 0x005f }
        if (r2 == 0) goto L_0x0093;
    L_0x0072:
        r0 = r5.nextText();	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = (float) r0;	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = r0 * r4;
        r0 = (int) r0;	 Catch:{ XmlPullParserException -> 0x005f }
        g = r0;	 Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x0031;
    L_0x0080:
        r0 = move-exception;
        r2 = r3;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0085:
        r1.printStackTrace();	 Catch:{ all -> 0x00d3 }
        if (r2 == 0) goto L_0x0069;
    L_0x008a:
        r2.close();	 Catch:{ IOException -> 0x008e }
        goto L_0x0069;
    L_0x008e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0069;
    L_0x0093:
        r2 = "weibosdk_dialog_right_margin";
        r2 = r2.equals(r0);	 Catch:{ XmlPullParserException -> 0x005f }
        if (r2 == 0) goto L_0x00b0;
    L_0x009b:
        r0 = r5.nextText();	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = (float) r0;	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = r0 * r4;
        r0 = (int) r0;	 Catch:{ XmlPullParserException -> 0x005f }
        h = r0;	 Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x0031;
    L_0x00a9:
        r0 = move-exception;
    L_0x00aa:
        if (r3 == 0) goto L_0x00af;
    L_0x00ac:
        r3.close();	 Catch:{ IOException -> 0x00ce }
    L_0x00af:
        throw r0;
    L_0x00b0:
        r2 = "weibosdk_dialog_bottom_margin";
        r0 = r2.equals(r0);	 Catch:{ XmlPullParserException -> 0x005f }
        if (r0 == 0) goto L_0x0031;
    L_0x00b8:
        r0 = r5.nextText();	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = (float) r0;	 Catch:{ XmlPullParserException -> 0x005f }
        r0 = r0 * r4;
        r0 = (int) r0;	 Catch:{ XmlPullParserException -> 0x005f }
        i = r0;	 Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x0031;
    L_0x00c7:
        r0 = r1;
        goto L_0x0064;
    L_0x00c9:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0069;
    L_0x00ce:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00af;
    L_0x00d3:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00aa;
    L_0x00d6:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r2 = r3;
        goto L_0x0085;
    L_0x00db:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r2 = r3;
        goto L_0x0085;
    L_0x00e0:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.thirdparty.ui.ThirdPartyDialog.c():boolean");
    }

    private void b(WebView webView, String str) {
        Bundle bundle = new Bundle();
        Matcher matcher = Pattern.compile("access_token=(.+?)(?:&|$)").matcher(str);
        if (matcher.find()) {
            bundle.putString("access_token", matcher.group(1));
        }
        matcher = Pattern.compile("expires_in=(.+?)(?:&|$)").matcher(str);
        if (matcher.find()) {
            bundle.putString("expires_in", matcher.group(1));
        }
        this.b.onComplete(bundle);
    }

    private boolean d() {
        try {
            return ((Boolean) Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e) {
            if (Build.DEVICE.equals("mx2")) {
                return true;
            }
            if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
                return false;
            }
            return false;
        }
    }
}
