package cn.v6.sixrooms.listener;

import android.net.Uri;
import android.webkit.ValueCallback;

public interface OpenFileChooserCallBack {
    void openFileChooserCallBack(ValueCallback<Uri> valueCallback, String str);

    void openFileChooserCallBackOfAndroid5(ValueCallback<Uri[]> valueCallback, String str);
}
