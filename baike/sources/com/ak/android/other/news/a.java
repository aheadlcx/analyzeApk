package com.ak.android.other.news;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;
import org.json.JSONObject;

public final class a implements DynamicObject {
    private final ActCallBack a;

    public a(ActCallBack actCallBack) {
        this.a = actCallBack;
    }

    public final Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.ap /*59002*/:
                    this.a.onActionCall(((Integer) objArr[0]).intValue(), (JSONObject) objArr[1]);
                    break;
            }
        }
        return null;
    }
}
