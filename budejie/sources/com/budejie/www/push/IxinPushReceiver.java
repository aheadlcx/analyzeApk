package com.budejie.www.push;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.LaucherActivity;
import com.budejie.www.activity.MessageCenterActivity;
import com.budejie.www.activity.htmlpage.NoViewActivity;
import com.budejie.www.util.a;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.ixintui.pushsdk.SdkConstants;

public class IxinPushReceiver extends BroadcastReceiver {
    static final String a = IxinPushReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        int i = 0;
        aa.e("PushReceiverOfIxin", "onReceive");
        try {
            String action = intent.getAction();
            String stringExtra;
            if (action.equals(SdkConstants.MESSAGE_ACTION)) {
                stringExtra = intent.getStringExtra(SdkConstants.MESSAGE);
                aa.e("PushReceiverOfIxin", "message received, msg is: " + stringExtra + "extra: " + intent.getStringExtra(SdkConstants.ADDITION));
            } else if (action.equals(SdkConstants.RESULT_ACTION)) {
                action = intent.getStringExtra(SdkConstants.COMMAND);
                int intExtra = intent.getIntExtra(SdkConstants.CODE, 0);
                if (intExtra != 0) {
                    aa.e("PushReceiverOfIxin", "command is: " + action + " result error: " + intent.getStringExtra(SdkConstants.ERROR));
                } else {
                    aa.e("PushReceiverOfIxin", "command is: " + action + "result OK");
                }
                String stringExtra2 = intent.getStringExtra(SdkConstants.ADDITION);
                if (stringExtra2 != null && intExtra == 0) {
                    aa.e("PushReceiverOfIxin", "result extra: " + stringExtra2);
                    if (SdkConstants.IS_SUSPENDED.equals(action)) {
                        if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(stringExtra2)) {
                            i = 1;
                        }
                        ai.d(context, i);
                    } else if (SdkConstants.REGISTER.equals(action)) {
                        ai.e(context, stringExtra2);
                    }
                }
            } else if (action.equals(SdkConstants.NOTIFICATION_CLICK_ACTION)) {
                stringExtra = intent.getStringExtra(SdkConstants.MESSAGE);
                aa.e("PushReceiverOfIxin", "notification click received, msg is: " + stringExtra);
                ai.f(context, stringExtra);
                Intent intent2 = new Intent();
                intent2.setClass(context, LaucherActivity.class);
                intent2.addFlags(268435456);
                context.startActivity(intent2);
            }
        } catch (Exception e) {
            aa.e("PushReceiverOfIxin", e.toString());
        }
    }

    public static void a(Context context, d dVar) {
        String a = dVar.a();
        aa.e("launchPageV2:adk", a);
        context.startActivity(new Intent(context, NoViewActivity.class).setData(Uri.parse(a)).addFlags(268435456));
    }

    public static void a(Context context, b bVar) {
        aa.a(a, "launchPageV1-->msg" + bVar.toString());
        Intent intent = new Intent();
        intent.setFlags(268435456);
        if (bVar.b == 102) {
            a(context);
            intent.setClass(context, MessageCenterActivity.class);
            context.startActivity(intent);
        } else if (bVar.b == 101) {
            if (TextUtils.isEmpty(bVar.a)) {
                a(context);
                return;
            }
            try {
                b(context, bVar);
            } catch (Exception e) {
                aa.a(a, "e.toString()" + e.toString());
            }
        } else if (bVar.b == 103) {
            intent.setAction("com.budejie.www.activity.MyInfoActivity");
            intent.putExtra("tag_all", "myinfo_type");
            intent.setClass(context, HomeGroup.class);
            context.startActivity(intent);
        } else {
            a(context);
        }
    }

    public static void b(Context context, b bVar) {
        aa.a(a, "toCommendDetail");
        a.a((Activity) context, null, bVar.a, false);
    }

    public static void a(Context context) {
        aa.a(a, "toHomePage");
        Intent intent = new Intent();
        intent.setClass(context, HomeGroup.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }
}
