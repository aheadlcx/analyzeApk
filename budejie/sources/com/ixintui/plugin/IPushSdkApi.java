package com.ixintui.plugin;

import android.content.Context;
import com.ixintui.pushsdk.PushModel;
import java.util.List;

public interface IPushSdkApi {
    void addTags(Context context, List list);

    void bindAlias(Context context, String str);

    void clearBadge(Context context);

    void configure(Context context, String str);

    void deleteTags(Context context, List list);

    void enableStat(Context context, boolean z);

    PushModel getBadgeModel(Context context);

    void incrBadge(Context context, int i);

    void isSuspended(Context context);

    boolean isWrapperCompatible(Context context, int i);

    void listTags(Context context);

    boolean onAppEaseEvent(Context context, String str, int i, int i2);

    void onPause(Context context);

    void onResume(Context context);

    void openMIBadge(Context context, boolean z);

    void register(Context context, int i, String str, String str2);

    void register(Context context, int i, String str, String str2, String str3, String str4);

    void resumePush(Context context);

    void setBadgeModel(Context context, PushModel pushModel);

    void setBadgeNum(Context context, int i);

    void suspendPush(Context context);

    void unbindAlias(Context context, String str);
}
