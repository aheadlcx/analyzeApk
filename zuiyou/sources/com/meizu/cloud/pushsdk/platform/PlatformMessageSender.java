package com.meizu.cloud.pushsdk.platform;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.impl.model.PlatformMessage;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.meizu.cloud.pushsdk.util.UxIPUtils;
import com.tencent.connect.common.Constants;
import java.util.List;

public class PlatformMessageSender {
    public static final String TAG = "PlatformMessageSender";

    private interface OnUpdateIntent {
        BasicPushStatus getBasicStatus();

        String getBasicStatusExtra();

        String getMethod();
    }

    public static void sendPushStatus(Context context, String str, final PushSwitchStatus pushSwitchStatus) {
        sendPlatformStatus(context, str, new OnUpdateIntent() {
            public String getMethod() {
                return PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PUSH_STATUS;
            }

            public BasicPushStatus getBasicStatus() {
                return pushSwitchStatus;
            }

            public String getBasicStatusExtra() {
                return PushConstants.EXTRA_APP_PUSH_SWITCH_STATUS;
            }
        });
    }

    public static void sendRegisterStatus(Context context, String str, final RegisterStatus registerStatus) {
        sendPlatformStatus(context, str, new OnUpdateIntent() {
            public String getMethod() {
                return PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_REGISTER_STATUS;
            }

            public BasicPushStatus getBasicStatus() {
                return registerStatus;
            }

            public String getBasicStatusExtra() {
                return PushConstants.EXTRA_APP_PUSH_REGISTER_STATUS;
            }
        });
    }

    public static void sendUnRegisterStatus(Context context, String str, final UnRegisterStatus unRegisterStatus) {
        sendPlatformStatus(context, str, new OnUpdateIntent() {
            public String getMethod() {
                return PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_UNREGISTER_STATUS;
            }

            public BasicPushStatus getBasicStatus() {
                return unRegisterStatus;
            }

            public String getBasicStatusExtra() {
                return PushConstants.EXTRA_APP_PUSH_UNREGISTER_STATUS;
            }
        });
    }

    public static void sendSubTags(Context context, String str, final SubTagsStatus subTagsStatus) {
        sendPlatformStatus(context, str, new OnUpdateIntent() {
            public String getMethod() {
                return PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_SUBTAGS_STATUS;
            }

            public BasicPushStatus getBasicStatus() {
                return subTagsStatus;
            }

            public String getBasicStatusExtra() {
                return PushConstants.EXTRA_APP_PUSH_SUBTAGS_STATUS;
            }
        });
    }

    public static void sendSubAlias(Context context, String str, final SubAliasStatus subAliasStatus) {
        sendPlatformStatus(context, str, new OnUpdateIntent() {
            public String getMethod() {
                return PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_SUBALIAS_STATUS;
            }

            public BasicPushStatus getBasicStatus() {
                return subAliasStatus;
            }

            public String getBasicStatusExtra() {
                return PushConstants.EXTRA_APP_PUSH_SUBALIAS_STATUS;
            }
        });
    }

    private static void sendPlatformStatus(Context context, String str, OnUpdateIntent onUpdateIntent) {
        Intent intent = new Intent();
        intent.addCategory(str);
        intent.setPackage(str);
        intent.putExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD, onUpdateIntent.getMethod());
        intent.putExtra(onUpdateIntent.getBasicStatusExtra(), onUpdateIntent.getBasicStatus());
        sendMessageFromBroadcast(context, intent, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, str);
        sendMessageFromBroadcast(context, new Intent("com.meizu.cloud.pushservice.action.PUSH_SERVICE_START"), null, str);
    }

    public static void switchPushMessageSetting(Context context, int i, boolean z, String str) {
        Object appVersionName = MzSystemUtils.getAppVersionName(context, "com.meizu.cloud");
        a.a(TAG, context.getPackageName() + " switchPushMessageSetting cloudVersion_name " + appVersionName);
        if (!TextUtils.isEmpty(appVersionName) && appVersionName.startsWith(Constants.VIA_SHARE_TYPE_INFO)) {
            Intent intent = new Intent(PushConstants.MZ_PUSH_ON_MESSAGE_SWITCH_SETTING);
            intent.putExtra(PushConstants.EXTRA_APP_PUSH_SWITCH_SETTING_TYPE, i);
            intent.putExtra(PushConstants.EXTRA_APP_PUSH_SWITCH_SETTING_STATUS, z);
            intent.putExtra(PushConstants.EXTRA_APP_PUSH_SWITCH_SETTING_PACKAGE_NAME, str);
            intent.setClassName("com.meizu.cloud", "com.meizu.cloud.pushsdk.pushservice.MzPushService");
            context.startService(intent);
        }
    }

    public static void launchStartActivity(Context context, String str, String str2, String str3) {
        PlatformMessage buildPlatformMessage = UxIPUtils.buildPlatformMessage(str3);
        Parcelable parse = MessageV3.parse(str, str, buildPlatformMessage.getPushTimesTamp(), buildPlatformMessage.getDeviceId(), buildPlatformMessage.getTaskId(), buildPlatformMessage.getSeqId(), str2);
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, parse);
        intent.putExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD, PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE);
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        if (!TextUtils.isEmpty(str)) {
            intent.setPackage(str);
            intent.setClassName(str, "com.meizu.cloud.pushsdk.NotificationService");
        }
        intent.putExtra("command_type", "reflect_receiver");
        a.a(TAG, "start notification service " + parse);
        try {
            context.startService(intent);
        } catch (Exception e) {
            a.d(TAG, "launchStartActivity error " + e.getMessage());
        }
    }

    public static void sendMessageFromBroadcast(Context context, Intent intent, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            intent.setAction(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.setPackage(str2);
        }
        Object findReceiver = findReceiver(context, str, str2);
        if (!TextUtils.isEmpty(findReceiver)) {
            intent.setClassName(str2, findReceiver);
        }
        context.sendBroadcast(intent);
    }

    public static String findReceiver(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            return null;
        }
        return ((ResolveInfo) queryBroadcastReceivers.get(0)).activityInfo.name;
    }
}
