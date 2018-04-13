package com.ak.android.base.landingpage;

import android.view.View;
import java.util.HashMap;

public interface CallBack {
    void onCatchException(Exception exception);

    void onExtendMethod(HashMap<String, Object> hashMap);

    void onOptionClicked(int i, View view);
}
