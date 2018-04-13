package com.umeng.fb;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.fb.model.UserInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import u.fb.g;
import u.fb.k;
import u.fb.m;
import u.fb.n;
import u.fb.o;

public class ContactActivity extends Activity {
    private static final String a = "plain";
    private ImageView b;
    private ImageView c;
    private EditText d;
    private FeedbackAgent e;
    private TextView f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(n.a(this));
        this.e = new FeedbackAgent(this);
        this.b = (ImageView) findViewById(m.d(this));
        this.c = (ImageView) findViewById(m.g(this));
        this.d = (EditText) findViewById(m.h(this));
        this.f = (TextView) findViewById(m.i(this));
        try {
            String str = (String) this.e.getUserInfo().getContact().get(a);
            this.d.setText(str);
            long userInfoLastUpdateAt = this.e.getUserInfoLastUpdateAt();
            if (userInfoLastUpdateAt > 0) {
                Date date = new Date(userInfoLastUpdateAt);
                this.f.setText(new StringBuilder(String.valueOf(getResources().getString(o.a(this)))).append(SimpleDateFormat.getDateTimeInstance().format(date)).toString());
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
            if (g.b(str)) {
                this.d.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.toggleSoftInput(2, 0);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ContactActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ContactActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    UserInfo userInfo;
                    UserInfo userInfo2 = this.a.e.getUserInfo();
                    if (userInfo2 == null) {
                        userInfo = new UserInfo();
                    } else {
                        userInfo = userInfo2;
                    }
                    Map contact = userInfo.getContact();
                    if (contact == null) {
                        contact = new HashMap();
                    }
                    contact.put(ContactActivity.a, this.a.d.getEditableText().toString());
                    userInfo.setContact(contact);
                    this.a.e.setUserInfo(userInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.a.a();
            }
        });
    }

    void a() {
        finish();
        if (VERSION.SDK_INT > 4) {
            new Object(this) {
                final /* synthetic */ ContactActivity a;

                {
                    this.a = r1;
                }

                public void a(Activity activity) {
                    activity.overridePendingTransition(k.a(this.a), k.d(this.a));
                }
            }.a(this);
        }
    }
}
