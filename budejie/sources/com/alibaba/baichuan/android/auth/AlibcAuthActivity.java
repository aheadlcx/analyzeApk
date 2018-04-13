package com.alibaba.baichuan.android.auth;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.alibaba.baichuan.android.trade.utils.i;

public class AlibcAuthActivity extends Activity implements OnClickListener {
    static int a;
    private boolean b = false;

    private class a implements e {
        com.alibaba.baichuan.android.auth.AlibcAuth.a a;
        final /* synthetic */ AlibcAuthActivity b;

        private a(AlibcAuthActivity alibcAuthActivity) {
            this.b = alibcAuthActivity;
            this.a = (com.alibaba.baichuan.android.auth.AlibcAuth.a) AlibcAuth.a.get(Integer.valueOf(AlibcAuthActivity.a));
        }

        public void a() {
            this.a.e.a();
            AlibcAuth.a.remove(Integer.valueOf(AlibcAuthActivity.a));
            this.b.finish();
        }

        public void a(String str, String str2) {
            if (TextUtils.equals("FAIL_SYS_ACCESS_TOKEN_STOP_SERVICE", str)) {
                this.a.e.a(str, str2);
                AlibcAuth.a.remove(Integer.valueOf(AlibcAuthActivity.a));
                return;
            }
            this.b.b = true;
            this.b.a();
            c();
        }

        public void b() {
            this.a.e.b();
            AlibcAuth.a.remove(Integer.valueOf(AlibcAuthActivity.a));
            this.b.finish();
        }

        public void c() {
            this.a.e.c();
        }
    }

    private class b implements e {
        com.alibaba.baichuan.android.auth.AlibcAuth.a a;
        final /* synthetic */ AlibcAuthActivity b;

        private b(AlibcAuthActivity alibcAuthActivity) {
            this.b = alibcAuthActivity;
            this.a = (com.alibaba.baichuan.android.auth.AlibcAuth.a) AlibcAuth.a.get(Integer.valueOf(AlibcAuthActivity.a));
        }

        public void a() {
            this.b.b = false;
            this.b.a();
        }

        public void a(String str, String str2) {
            this.b.b = true;
            this.b.a();
            c();
        }

        public void b() {
            this.a.e.b();
            AlibcAuth.a.remove(Integer.valueOf(AlibcAuthActivity.a));
            this.b.finish();
        }

        public void c() {
            this.a.e.c();
        }
    }

    private void a() {
        if (!isFinishing()) {
            com.alibaba.baichuan.android.auth.AlibcAuth.a aVar = (com.alibaba.baichuan.android.auth.AlibcAuth.a) AlibcAuth.a.get(Integer.valueOf(a));
            String charSequence = getApplicationInfo().loadLabel(getPackageManager()).toString();
            TextView textView = (TextView) findViewById(i.a(this, "id", "open_auth_title"));
            TextView textView2 = (TextView) findViewById(i.a(this, "id", "open_auth_desc"));
            TextView textView3 = (TextView) findViewById(i.a(this, "id", "open_auth_btn_grant"));
            findViewById(i.a(this, "id", "open_auth_rl")).setVisibility(0);
            c();
            if (this.b) {
                textView.setText("淘宝授权失败");
                textView2.setText("请确认网络环境后再试试？");
                textView3.setText("重试");
            } else {
                String str;
                textView.setText(charSequence + "将获取");
                CharSequence stringBuffer = new StringBuffer();
                Object hintInfo;
                if (aVar.a != null) {
                    for (String str2 : AlibcAuthHint.getApiAndHint(aVar.a)) {
                        hintInfo = AlibcAuthHint.getHintInfo(str2);
                        stringBuffer.append(TextUtils.isEmpty(hintInfo) ? "访问您淘宝账号信息的权限(" + str2 + ")" : hintInfo + "\n");
                    }
                } else if (aVar.c != null) {
                    for (String str22 : aVar.c) {
                        hintInfo = AlibcAuthHint.getHintInfo(str22);
                        stringBuffer.append(TextUtils.isEmpty(hintInfo) ? "访问您淘宝账号信息的权限(" + str22 + ")" : hintInfo + "\n");
                    }
                }
                stringBuffer.delete(stringBuffer.lastIndexOf("\n"), stringBuffer.length());
                if (TextUtils.isEmpty(stringBuffer)) {
                    str22 = "访问您淘宝账号信息的权限";
                } else {
                    CharSequence charSequence2 = stringBuffer;
                }
                textView2.setText(str22);
                textView3.setText("确认授权");
            }
            textView3.setOnClickListener(this);
            findViewById(i.a(this, "id", "open_auth_btn_cancel")).setOnClickListener(this);
            findViewById(i.a(this, "id", "open_auth_btn_close")).setOnClickListener(this);
        }
    }

    private void b() {
        findViewById(i.a(this, "id", "com_alibc_auth_progressbar")).setVisibility(0);
    }

    private void c() {
        findViewById(i.a(this, "id", "com_alibc_auth_progressbar")).setVisibility(8);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r5) {
        /*
        r4 = this;
        r0 = r5.getId();
        r1 = "id";
        r2 = "open_auth_btn_cancel";
        r1 = com.alibaba.baichuan.android.trade.utils.i.a(r4, r1, r2);
        if (r0 == r1) goto L_0x0018;
    L_0x000e:
        r1 = "id";
        r2 = "open_auth_btn_close";
        r1 = com.alibaba.baichuan.android.trade.utils.i.a(r4, r1, r2);
        if (r0 != r1) goto L_0x0041;
    L_0x0018:
        monitor-enter(r4);
        r0 = com.alibaba.baichuan.android.auth.AlibcAuth.a;	 Catch:{ all -> 0x003e }
        r1 = a;	 Catch:{ all -> 0x003e }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x003e }
        r0 = r0.get(r1);	 Catch:{ all -> 0x003e }
        r0 = (com.alibaba.baichuan.android.auth.AlibcAuth.a) r0;	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0039;
    L_0x0029:
        r0 = r0.e;	 Catch:{ all -> 0x003e }
        r0.b();	 Catch:{ all -> 0x003e }
        r0 = com.alibaba.baichuan.android.auth.AlibcAuth.a;	 Catch:{ all -> 0x003e }
        r1 = a;	 Catch:{ all -> 0x003e }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x003e }
        r0.remove(r1);	 Catch:{ all -> 0x003e }
    L_0x0039:
        r4.finish();	 Catch:{ all -> 0x003e }
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
    L_0x003d:
        return;
    L_0x003e:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        throw r0;
    L_0x0041:
        r1 = "id";
        r2 = "open_auth_btn_grant";
        r1 = com.alibaba.baichuan.android.trade.utils.i.a(r4, r1, r2);
        if (r0 != r1) goto L_0x003d;
    L_0x004b:
        monitor-enter(r4);
        r0 = com.alibaba.baichuan.android.auth.AlibcAuth.a;	 Catch:{ all -> 0x0061 }
        r1 = a;	 Catch:{ all -> 0x0061 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x0061 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0061 }
        r0 = (com.alibaba.baichuan.android.auth.AlibcAuth.a) r0;	 Catch:{ all -> 0x0061 }
        if (r0 != 0) goto L_0x0064;
    L_0x005c:
        r4.finish();	 Catch:{ all -> 0x0061 }
        monitor-exit(r4);	 Catch:{ all -> 0x0061 }
        goto L_0x003d;
    L_0x0061:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0061 }
        throw r0;
    L_0x0064:
        r4.b();	 Catch:{ all -> 0x0061 }
        r1 = "id";
        r2 = "open_auth_rl";
        r1 = com.alibaba.baichuan.android.trade.utils.i.a(r4, r1, r2);	 Catch:{ all -> 0x0061 }
        r1 = r4.findViewById(r1);	 Catch:{ all -> 0x0061 }
        r2 = 8;
        r1.setVisibility(r2);	 Catch:{ all -> 0x0061 }
        r1 = r0.a;	 Catch:{ all -> 0x0061 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0061 }
        if (r1 != 0) goto L_0x00a8;
    L_0x0080:
        r1 = r0.a;	 Catch:{ all -> 0x0061 }
        r1 = com.alibaba.baichuan.android.auth.AlibcAuthHint.getApiAndHint(r1);	 Catch:{ all -> 0x0061 }
        if (r1 == 0) goto L_0x008e;
    L_0x0088:
        r2 = r1.size();	 Catch:{ all -> 0x0061 }
        if (r2 > 0) goto L_0x009d;
    L_0x008e:
        r0 = r0.a;	 Catch:{ all -> 0x0061 }
        r1 = new com.alibaba.baichuan.android.auth.AlibcAuthActivity$b;	 Catch:{ all -> 0x0061 }
        r2 = 0;
        r1.<init>();	 Catch:{ all -> 0x0061 }
        r2 = 0;
        r3 = 0;
        com.alibaba.baichuan.android.auth.AlibcAuth.a(r0, r1, r2, r3);	 Catch:{ all -> 0x0061 }
    L_0x009b:
        monitor-exit(r4);	 Catch:{ all -> 0x0061 }
        goto L_0x003d;
    L_0x009d:
        r0 = new com.alibaba.baichuan.android.auth.AlibcAuthActivity$a;	 Catch:{ all -> 0x0061 }
        r2 = 0;
        r0.<init>();	 Catch:{ all -> 0x0061 }
        r2 = 0;
        com.alibaba.baichuan.android.auth.AlibcAuth.a(r1, r0, r2);	 Catch:{ all -> 0x0061 }
        goto L_0x009b;
    L_0x00a8:
        r0 = r0.c;	 Catch:{ all -> 0x0061 }
        r1 = new com.alibaba.baichuan.android.auth.AlibcAuthActivity$a;	 Catch:{ all -> 0x0061 }
        r2 = 0;
        r1.<init>();	 Catch:{ all -> 0x0061 }
        r2 = 0;
        com.alibaba.baichuan.android.auth.AlibcAuth.a(r0, r1, r2);	 Catch:{ all -> 0x0061 }
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.baichuan.android.auth.AlibcAuthActivity.onClick(android.view.View):void");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(i.a(this, "layout", "com_alibc_auth_actiivty"));
        a = getIntent().getExtras().getInt("authId");
        com.alibaba.baichuan.android.auth.AlibcAuth.a aVar = (com.alibaba.baichuan.android.auth.AlibcAuth.a) AlibcAuth.a.get(Integer.valueOf(a));
        if (aVar.c == null) {
            AlibcAuth.a(aVar.a, new b(), false, false);
        } else {
            a();
        }
    }
}
