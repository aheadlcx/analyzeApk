package cn.v6.sdk.sixrooms.coop;

import android.support.annotation.UiThread;

@UiThread
public interface V6Coop$GetCoopTypeListener {
    void getCoopTypeFailed(String str);

    void getCoopTypeSuccess();
}
