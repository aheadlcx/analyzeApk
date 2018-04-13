package com.ixintui.plugin;

import android.content.Context;
import java.util.List;

public interface IPushSdkApi {
    void addTags(Context context, List list);

    void bindAlias(Context context, String str);

    void configure(Context context, String str);

    void deleteTags(Context context, List list);

    void enableStat(Context context, boolean z);

    void isSuspended(Context context);

    boolean isWrapperCompatible(Context context, int i);

    void listTags(Context context);

    void register(Context context, int i, String str, String str2);

    void resumePush(Context context);

    void suspendPush(Context context);

    void unbindAlias(Context context, String str);
}
