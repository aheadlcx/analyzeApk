package cn.v6.sixrooms.surfaceanim.view;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.budejie.www.R$styleable;

public class AnimWindowManager {
    private WindowManager a;
    private View b;
    private LayoutParams c;

    public AnimWindowManager(Context context) {
        this.a = (WindowManager) context.getApplicationContext().getSystemService("window");
    }

    public void createWindow(View view) {
        this.b = view;
        this.c = new LayoutParams();
        this.c.type = 2005;
        this.c.format = 1;
        this.c.flags = R$styleable.Theme_Custom_ic_follows_sinafriend;
        this.c.width = -1;
        this.c.height = -1;
        this.c.x = 0;
        this.c.y = 0;
        this.b.setLayoutParams(this.c);
        this.a.addView(this.b, this.c);
    }

    public View getRootView() {
        return this.b;
    }
}
