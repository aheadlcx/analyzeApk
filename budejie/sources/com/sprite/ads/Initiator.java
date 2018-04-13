package com.sprite.ads;

import android.content.Context;

public interface Initiator {
    @Deprecated
    void applicationInit(Context context);

    void applicationInit(Context context, String str);
}
