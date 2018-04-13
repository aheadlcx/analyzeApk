package cn.v6.sixrooms.utils;

import android.content.Context;
import android.content.Intent;
import cn.v6.sixrooms.constants.CustomBroadcast;

public class SendBroadcastUtils {
    public static void sendUserInfoBroadcast(Context context) {
        Intent intent = new Intent();
        intent.setAction(CustomBroadcast.USER_INFO);
        intent.putExtra("flag", 101);
        context.sendBroadcast(intent);
    }

    public static void sendUserPicBroadcast(Context context) {
        Intent intent = new Intent();
        intent.setAction(CustomBroadcast.USER_INFO);
        intent.putExtra("flag", 102);
        context.sendBroadcast(intent);
    }

    public static void sendUserNameBroadcast(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction(CustomBroadcast.USER_INFO);
        intent.putExtra("flag", 104);
        intent.putExtra("newName", str);
        context.sendBroadcast(intent);
    }

    public static void sendUserLogout(Context context) {
        Intent intent = new Intent();
        intent.setAction(CustomBroadcast.USER_INFO);
        intent.putExtra("flag", 103);
        context.sendBroadcast(intent);
    }
}
