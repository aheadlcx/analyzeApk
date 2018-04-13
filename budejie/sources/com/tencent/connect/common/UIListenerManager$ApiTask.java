package com.tencent.connect.common;

import com.tencent.tauth.IUiListener;

public class UIListenerManager$ApiTask {
    public IUiListener mListener;
    public int mRequestCode;
    final /* synthetic */ UIListenerManager this$0;

    public UIListenerManager$ApiTask(UIListenerManager uIListenerManager, int i, IUiListener iUiListener) {
        this.this$0 = uIListenerManager;
        this.mRequestCode = i;
        this.mListener = iUiListener;
    }
}
