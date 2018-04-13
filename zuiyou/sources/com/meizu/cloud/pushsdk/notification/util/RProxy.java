package com.meizu.cloud.pushsdk.notification.util;

import android.content.Context;

public class RProxy {
    public static int push_expandable_big_image_notification(Context context) {
        return ResourceReader.getInstance(context).getResId("push_expandable_big_image_notification", "layout");
    }

    public static int push_expandable_big_text_notification(Context context) {
        return ResourceReader.getInstance(context).getResId("push_expandable_big_text_notification", "layout");
    }

    public static int push_pure_pic_notification(Context context) {
        return ResourceReader.getInstance(context).getResId("push_pure_pic_notification", "layout");
    }

    public static int push_big_notification_title(Context context) {
        return ResourceReader.getInstance(context).getResId("push_big_notification_title", "id");
    }

    public static int push_big_notification_content(Context context) {
        return ResourceReader.getInstance(context).getResId("push_big_notification_content", "id");
    }

    public static int push_big_notification_date(Context context) {
        return ResourceReader.getInstance(context).getResId("push_big_notification_date", "id");
    }

    public static int push_big_bigview_defaultView(Context context) {
        return ResourceReader.getInstance(context).getResId("push_big_bigview_defaultView", "id");
    }

    public static int push_big_bigtext_defaultView(Context context) {
        return ResourceReader.getInstance(context).getResId("push_big_bigtext_defaultView", "id");
    }

    public static int push_pure_bigview_banner(Context context) {
        return ResourceReader.getInstance(context).getResId("push_pure_bigview_banner", "id");
    }

    public static int push_pure_bigview_expanded(Context context) {
        return ResourceReader.getInstance(context).getResId("push_pure_bigview_expanded", "id");
    }

    public static int push_big_notification_icon(Context context) {
        return ResourceReader.getInstance(context).getResId("push_big_notification_icon", "id");
    }

    public static int stat_sys_third_app_notify(Context context) {
        return ResourceReader.getInstance(context).getResId("stat_sys_third_app_notify", "drawable");
    }
}
