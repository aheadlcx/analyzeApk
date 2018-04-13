package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import com.budejie.www.R;
import com.budejie.www.h.c;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;

public class SwitchThemeMiddleActivity extends Activity {
    public static Bitmap a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        aa.b("MoreActivity", "SwitchThemeMiddleActivity onCreate");
        View imageView = new ImageView(this);
        imageView.setImageBitmap(a);
        an.f((Activity) this);
        setContentView(imageView);
        c.a().a((Context) this);
        imageView.postDelayed(new Runnable(this) {
            final /* synthetic */ SwitchThemeMiddleActivity a;

            {
                this.a = r1;
            }

            public void run() {
                Intent intent = this.a.getIntent();
                intent.putExtra("noSplashAd", true);
                intent.setClass(this.a, HomeGroup.class);
                this.a.startActivity(intent);
                this.a.overridePendingTransition(R.anim.switch_style_enter, R.anim.switch_style_exit);
                SwitchThemeMiddleActivity.a = null;
                this.a.finish();
            }
        }, 100);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
