package com.budejie.www.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.budejie.www.activity.htmlpage.NoViewActivity;
import com.budejie.www.util.aa;
import com.microquation.linkedme.android.util.LinkProperties;

public class LinkedMeMiddleActivity extends AppCompatActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            LinkProperties linkProperties = (LinkProperties) getIntent().getParcelableExtra("lmLinkProperties");
            if (linkProperties != null) {
                aa.a("LinkedME-Demo", "Channel " + linkProperties.c());
                aa.a("LinkedME-Demo", "control params " + linkProperties.a());
                aa.a("LinkedME-Demo", "link(深度链接) " + linkProperties.d());
                aa.a("LinkedME-Demo", "是否为新安装 " + linkProperties.e());
                String str = (String) linkProperties.a().get("mod");
                if (!TextUtils.isEmpty(str)) {
                    startActivity(new Intent(this, NoViewActivity.class).setData(Uri.parse(str)).addFlags(268435456));
                }
            }
        }
        finish();
    }
}
