package com.spriteapp.booklibrary.util;

import android.content.Context;
import android.content.Intent;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.ui.activity.PublishCommentActivity;
import com.spriteapp.booklibrary.ui.activity.ReadActivity;
import com.spriteapp.booklibrary.ui.activity.SettingActivity;
import com.spriteapp.booklibrary.ui.activity.WebViewActivity;

public class ActivityUtil {
    public static void toWebViewActivity(Context context, String str) {
        toWebViewActivity(context, str, false);
    }

    public static void toWebViewActivity(Context context, String str, boolean z) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.LOAD_URL_TAG, str);
        intent.putExtra(WebViewActivity.IS_H5_PAY_TAG, z);
        context.startActivity(intent);
    }

    public static void toReadActivity(Context context, BookDetailResponse bookDetailResponse) {
        toReadActivity(context, bookDetailResponse, false);
    }

    public static void toReadActivity(Context context, BookDetailResponse bookDetailResponse, boolean z) {
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra(ReadActivity.BOOK_DETAIL_TAG, bookDetailResponse);
        intent.putExtra(ReadActivity.IS_FROM_NATIVE_STORE, z);
        context.startActivity(intent);
    }

    public static void toSettingActivity(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    public static void toPublishCommentActivity(Context context, int i) {
        if (AppUtil.isLogin()) {
            Intent intent = new Intent(context, PublishCommentActivity.class);
            intent.putExtra(PublishCommentActivity.BOOK_ID_TAG, i);
            context.startActivity(intent);
            return;
        }
        HuaXiSDK.getInstance().toLoginPage(context);
    }

    public static void toCommonActivity(Context context, Class cls) {
        context.startActivity(new Intent(context, cls));
    }
}
