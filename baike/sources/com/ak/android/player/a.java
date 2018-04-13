package com.ak.android.player;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;

public final class a implements DynamicObject {
    protected VideoAdPlayerListener a;

    public a(VideoAdPlayerListener videoAdPlayerListener) {
        this.a = videoAdPlayerListener;
    }

    public final Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.an /*58051*/:
                    this.a.onPrepared();
                    break;
            }
        }
        return null;
    }
}
