package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoShadowListView extends ListView {
    public NoShadowListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (Integer.parseInt(VERSION.SDK) >= 9) {
            setOverScrollMode(2);
        }
    }

    public NoShadowListView(Context context) {
        super(context);
    }
}
