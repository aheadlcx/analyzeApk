package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.sina.weibo.sdk.a.e;
import com.sina.weibo.sdk.a.j;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.c;
import com.sina.weibo.sdk.web.view.WbSdkProgressBar;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class WbShareTransActivity extends Activity {
    boolean a = false;
    private String b;
    private a c;
    private FrameLayout d;
    private View e;
    private int f = -1;
    private int g = -1;
    private Handler h = new Handler(this) {
        final /* synthetic */ WbShareTransActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.a.a(1);
        }
    };

    private class a extends AsyncTask<WeiboMultiMessage, Object, b> {
        final /* synthetic */ WbShareTransActivity a;

        private a(WbShareTransActivity wbShareTransActivity) {
            this.a = wbShareTransActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((WeiboMultiMessage[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((b) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected b a(WeiboMultiMessage... weiboMultiMessageArr) {
            WeiboMultiMessage weiboMultiMessage = weiboMultiMessageArr[0];
            b bVar = new b();
            try {
                if (com.sina.weibo.sdk.b.a(this.a)) {
                    if (c.b(this.a).c() >= 10772) {
                        if (!(weiboMultiMessage.imageObject == null || weiboMultiMessage.multiImageObject == null)) {
                            weiboMultiMessage.imageObject = null;
                        }
                        if (!(weiboMultiMessage.videoSourceObject == null || (weiboMultiMessage.multiImageObject == null && weiboMultiMessage.imageObject == null))) {
                            weiboMultiMessage.multiImageObject = null;
                            weiboMultiMessage.imageObject = null;
                        }
                    }
                    if (weiboMultiMessage.multiImageObject != null) {
                        ArrayList arrayList = new ArrayList();
                        Iterator it = weiboMultiMessage.multiImageObject.c().iterator();
                        while (it.hasNext()) {
                            Object a = com.sina.weibo.sdk.a.c.a(this.a, (Uri) it.next(), 1);
                            if (!TextUtils.isEmpty(a)) {
                                arrayList.add(Uri.fromFile(new File(a)));
                            }
                        }
                        weiboMultiMessage.multiImageObject.a(arrayList);
                    }
                    if (weiboMultiMessage.videoSourceObject != null) {
                        String a2 = com.sina.weibo.sdk.a.c.a(this.a, weiboMultiMessage.videoSourceObject.h, 0);
                        weiboMultiMessage.videoSourceObject.h = Uri.fromFile(new File(a2));
                        weiboMultiMessage.videoSourceObject.i = com.sina.weibo.sdk.a.c.a(a2);
                    }
                }
                bVar.b = weiboMultiMessage;
                bVar.a = true;
            } catch (Exception e) {
                bVar.a = false;
            }
            return bVar;
        }

        protected void a(b bVar) {
            super.onPostExecute(bVar);
            this.a.d.setVisibility(4);
            if (bVar.a) {
                this.a.a(bVar.b);
            } else {
                this.a.a(2);
            }
        }
    }

    private class b {
        boolean a;
        WeiboMultiMessage b;
        final /* synthetic */ WbShareTransActivity c;

        private b(WbShareTransActivity wbShareTransActivity) {
            this.c = wbShareTransActivity;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        this.b = getIntent().getStringExtra("startActivity");
        if (bundle != null) {
            this.b = bundle.getString("startActivity");
            this.a = bundle.getBoolean("resultDataFlag", false);
            return;
        }
        a();
    }

    private void a() {
        Bundle extras = getIntent().getExtras();
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        weiboMultiMessage.toObject(extras);
        b(weiboMultiMessage);
    }

    private void b() {
        this.f = getIntent().getIntExtra("progressColor", -1);
        this.g = getIntent().getIntExtra("progressId", -1);
        this.d = new FrameLayout(this);
        if (this.g != -1) {
            try {
                this.e = ((LayoutInflater) getSystemService("layout_inflater")).inflate(this.g, null);
            } catch (Exception e) {
                this.e = new WbSdkProgressBar(this);
            }
        } else {
            this.e = new WbSdkProgressBar(this);
            if (this.f != -1) {
                ((WbSdkProgressBar) this.e).setProgressColor(this.f);
            }
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.d.addView(this.e, layoutParams);
        this.d.setBackgroundColor(855638016);
    }

    private void a(WeiboMultiMessage weiboMultiMessage) {
        Intent intent = getIntent();
        this.a = true;
        intent.putExtra("startFlag", -1);
        Intent intent2 = new Intent("com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY");
        intent2.setPackage(intent.getStringExtra("startPackage"));
        intent2.setAction(intent.getStringExtra("startAction"));
        Bundle extras = intent.getExtras();
        weiboMultiMessage.toBundle(extras);
        intent2.putExtras(extras);
        String packageName = getPackageName();
        intent2.putExtra("_weibo_sdkVersion", "0041005000");
        intent2.putExtra("_weibo_appPackage", packageName);
        intent2.putExtra("_weibo_appKey", com.sina.weibo.sdk.b.b().getAppKey());
        intent2.putExtra("_weibo_flag", 538116905);
        intent2.putExtra("_weibo_sign", e.a(j.a(this, packageName)));
        try {
            if (!TextUtils.isEmpty(intent.getStringExtra("gotoActivity"))) {
                intent2.setClassName(this, intent.getStringExtra("gotoActivity"));
                startActivity(intent2);
            } else if (com.sina.weibo.sdk.b.a(this)) {
                startActivityForResult(intent2, 765);
            } else {
                a(2);
            }
        } catch (Exception e) {
            try {
                intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("_weibo_resp_errcode", 2);
                intent.putExtras(bundle);
                intent.setFlags(131072);
                intent.setClassName(this, this.b);
                startActivity(intent);
                finish();
            } catch (Exception e2) {
                finish();
            }
        }
    }

    private void b(WeiboMultiMessage weiboMultiMessage) {
        setContentView(this.d);
        if (weiboMultiMessage.multiImageObject == null && weiboMultiMessage.videoSourceObject == null) {
            a(weiboMultiMessage);
            return;
        }
        setContentView(this.d);
        if (this.c != null) {
            this.c.cancel(true);
        }
        this.c = new a();
        this.c.execute(new WeiboMultiMessage[]{weiboMultiMessage});
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.h != null) {
            this.h.sendEmptyMessageDelayed(0, 100);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getIntExtra("startFlag", -1) != 0) {
            this.h.removeMessages(0);
            this.h = null;
            try {
                Bundle extras = intent.getExtras();
                Intent intent2 = new Intent();
                intent2.putExtras(extras);
                intent2.setFlags(131072);
                intent2.setClassName(this, this.b);
                startActivity(intent2);
            } catch (Exception e) {
            }
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.remove("startFlag");
        bundle.putBoolean("resultDataFlag", true);
        bundle.putString("startActivity", this.b);
    }

    private void a(int i) {
        if (this.d != null) {
            this.d.setVisibility(8);
        }
        try {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("_weibo_resp_errcode", i);
            intent.putExtras(bundle);
            intent.setFlags(131072);
            intent.setClassName(this, this.b);
            startActivity(intent);
        } catch (Exception e) {
        }
        finish();
    }
}
