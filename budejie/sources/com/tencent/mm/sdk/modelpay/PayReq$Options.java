package com.tencent.mm.sdk.modelpay;

import android.os.Bundle;

public class PayReq$Options {
    public static final int INVALID_FLAGS = -1;
    public String callbackClassName;
    public int callbackFlags = -1;

    public void fromBundle(Bundle bundle) {
        this.callbackClassName = bundle.getString("_wxapi_payoptions_callback_classname");
        this.callbackFlags = bundle.getInt("_wxapi_payoptions_callback_flags", -1);
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("_wxapi_payoptions_callback_classname", this.callbackClassName);
        bundle.putInt("_wxapi_payoptions_callback_flags", this.callbackFlags);
    }
}
