package com.baidu.mobads.production.f;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

public interface a {
    void a(int i, int i2);

    void a(Activity activity);

    void a(Activity activity, RelativeLayout relativeLayout);

    boolean a(int i, KeyEvent keyEvent);

    void addEventListener(String str, IOAdEventListener iOAdEventListener);

    void p();

    void q();

    void request();

    boolean v();
}
