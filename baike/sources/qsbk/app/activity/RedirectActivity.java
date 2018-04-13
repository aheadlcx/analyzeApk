package qsbk.app.activity;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.model.QiushiTopic;

public class RedirectActivity extends AppCompatActivity {
    public static final String ARTICLE = "mqsbk://article";
    public static final String ARTICLE_TOPIC = "mqsbk://articletopic";
    public static final String AUDIT = "mqsbk://articleReview";
    public static final String CIRCLE = "mqsbk://circle";
    public static final String CIRCLE_TOPIC = "mqsbk://circletopic";
    public static final String MAIN = "mqsbk://main";
    public static final String MY_AUDIT = "mqsbk://myArticleReview";
    public static final String QSJX = "mqsbk://qsjx";
    public static final String SCHEMA = "mqsbk:/";
    public static final String TAG = RedirectActivity.class.getSimpleName();
    public static final String WEB = "mqsbk://web";

    public static void navigateToActivity(@NonNull Context context, @NonNull String str) {
        String lastSegment = getLastSegment(str);
        Intent intent = new Intent();
        if (str.startsWith(MAIN)) {
            if (!(context instanceof MainActivity)) {
                intent.setClass(context, MainActivity.class);
                intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
                context.startActivity(intent);
            }
        } else if (str.startsWith(CIRCLE_TOPIC) && !TextUtils.isEmpty(lastSegment)) {
            CircleTopicActivity.launch(context, lastSegment);
        } else if (str.startsWith(ARTICLE_TOPIC) && !TextUtils.isEmpty(lastSegment)) {
            try {
                r0 = Integer.parseInt(lastSegment);
            } catch (Exception e) {
                e.printStackTrace();
                r0 = -1;
            }
            if (r0 != -1) {
                QiushiTopicActivity.Launch(context, new QiushiTopic(r0));
            }
        } else if (str.startsWith(CIRCLE) && !TextUtils.isEmpty(lastSegment)) {
            CircleArticleActivity.launch(context, lastSegment, false);
        } else if (str.startsWith(WEB)) {
            r0 = (str.indexOf(WEB) + WEB.length()) + 1;
            if (r0 < str.length()) {
                SimpleWebActivity.launch(context, str.substring(r0));
            }
        } else if (str.startsWith(QSJX) && !TextUtils.isEmpty(lastSegment)) {
            HighLightQiushiActivity.luanchActivity(context, lastSegment);
        } else if (str.startsWith(AUDIT)) {
            AuditNativeActivity2.launch(context);
        } else if (str.startsWith(MY_AUDIT)) {
            MyAuditListActivity.launch(context, lastSegment);
        } else if (str.startsWith(ARTICLE) && !TextUtils.isEmpty(lastSegment)) {
            SingleArticle.launch(context, lastSegment);
        }
    }

    public static void navigateToActivity(@NonNull String str) {
        navigateToActivity(QsbkApp.getInstance(), str);
    }

    public static String getLastSegment(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        int indexOf = str.indexOf(63);
        int length = str.length();
        if (indexOf <= lastIndexOf) {
            indexOf = length;
        }
        return str.substring(lastIndexOf + 1, indexOf);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        a(getIntent());
        finish();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
        finish();
    }

    private void a(Intent intent) {
        if (intent != null) {
            Uri data = intent.getData();
            if (data != null) {
                String uri = data.toString();
                Object obj = null;
                for (RunningTaskInfo runningTaskInfo : ((ActivityManager) getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE)) {
                    Object obj2;
                    if (MainActivity.class.getName().equals(runningTaskInfo.baseActivity.getClassName())) {
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    obj = obj2;
                }
                if (obj != null) {
                    Log.i(TAG, "mainActivity exist");
                    navigateToActivity(QsbkApp.getInstance(), uri);
                    return;
                }
                Intent intent2 = new Intent(QsbkApp.getInstance(), MainActivity.class);
                intent2.setData(data);
                startActivity(intent2);
                Log.i(TAG, "MainActivity not exist");
                return;
            }
            finish();
        }
    }
}
