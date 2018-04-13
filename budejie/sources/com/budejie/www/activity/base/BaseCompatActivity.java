package com.budejie.www.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.budejie.www.h.c;
import com.budejie.www.util.ai;

public abstract class BaseCompatActivity extends AppCompatActivity {
    public static int e;

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void onResume() {
        super.onResume();
        e = ai.a(this);
        setTheme(c.a().a(e));
    }
}
