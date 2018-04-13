package com.flurry.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.cafe.plugin.QbSignPlugin;
import qsbk.app.core.model.User;

public class CatalogActivity extends Activity implements OnClickListener {
    private static volatile String a = "<html><body><table height='100%' width='100%' border='0'><tr><td style='vertical-align:middle;text-align:center'>No recommendations available<p><button type='input' onClick='activity.finish()'>Back</button></td></tr></table></body></html>";
    private WebView b;
    private w c;
    private List d = new ArrayList();
    private u e;
    private p f;

    protected void onCreate(Bundle bundle) {
        setTheme(16973839);
        super.onCreate(bundle);
        this.e = FlurryAgent.b();
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Long valueOf = Long.valueOf(intent.getExtras().getLong(Config.OS));
            if (valueOf != null) {
                this.f = this.e.b(valueOf.longValue());
            }
        }
        View abVar = new ab(this, this);
        abVar.setId(1);
        abVar.setBackgroundColor(-16777216);
        this.b = new WebView(this);
        this.b.setId(2);
        this.b.setScrollBarStyle(0);
        this.b.setBackgroundColor(-1);
        if (this.f != null) {
            this.b.setWebViewClient(new q(this));
        }
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.addJavascriptInterface(this, "activity");
        this.c = new w(this, this);
        this.c.setId(3);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10, abVar.getId());
        relativeLayout.addView(abVar, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, abVar.getId());
        layoutParams.addRule(2, this.c.getId());
        relativeLayout.addView(this.b, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12, abVar.getId());
        relativeLayout.addView(this.c, layoutParams);
        Bundle extras = getIntent().getExtras();
        String string = extras == null ? null : extras.getString(User.UNDEFINED);
        if (string == null) {
            this.b.loadDataWithBaseURL(null, a, "text/html", "utf-8", null);
        } else {
            this.b.loadUrl(string);
        }
        setContentView(relativeLayout);
    }

    public void finish() {
        super.finish();
    }

    protected void onDestroy() {
        this.e.h();
        super.onDestroy();
    }

    public void onClick(View view) {
        if (view instanceof y) {
            x xVar = new x();
            xVar.a = this.f;
            xVar.b = this.b.getUrl();
            xVar.c = new ArrayList(this.c.b());
            this.d.add(xVar);
            if (this.d.size() > 5) {
                this.d.remove(0);
            }
            x xVar2 = new x();
            y yVar = (y) view;
            String b = yVar.b(this.e.j());
            this.f = yVar.a();
            xVar2.a = yVar.a();
            xVar2.a.a(new f((byte) 4, this.e.k()));
            xVar2.b = b;
            xVar2.c = this.c.a(view.getContext());
            a(xVar2);
        } else if (view.getId() == 10000) {
            finish();
        } else if (view.getId() == QbSignPlugin.REQUEST_BIND_PHONE) {
            this.c.a();
        } else if (this.d.isEmpty()) {
            finish();
        } else {
            a((x) this.d.remove(this.d.size() - 1));
        }
    }

    private void a(x xVar) {
        try {
            this.b.loadUrl(xVar.b);
            this.c.a(xVar.c);
        } catch (Exception e) {
            ah.a("FlurryAgent", "Error loading url: " + xVar.b);
        }
    }
}
