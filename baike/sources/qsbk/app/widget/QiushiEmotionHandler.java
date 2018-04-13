package qsbk.app.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.TypedValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.im.ChatMsgEmotionData;

public class QiushiEmotionHandler {
    private static final String a = QiushiEmotionHandler.class.getSimpleName();
    private static final Map<String, QiushiEmotionHandler$EmotionData> b = new LinkedHashMap();
    private static final Pattern c = Pattern.compile("(\u0001)\\[([一-龥A-Za-z~]+?)\\]");
    private static QiushiEmotionHandler d = null;
    private static byte[] e = new byte[0];
    private boolean f = false;
    private boolean g = false;

    private QiushiEmotionHandler() {
    }

    public static final QiushiEmotionHandler getInstance() {
        QiushiEmotionHandler qiushiEmotionHandler;
        synchronized (e) {
            if (d == null) {
                d = new QiushiEmotionHandler();
            }
            qiushiEmotionHandler = d;
        }
        return qiushiEmotionHandler;
    }

    public static void addEmotions(Context context, Spannable spannable, int i, int i2, int i3) {
        if (spannable != null && spannable.length() != 0) {
            Matcher matcher = c.matcher(spannable);
            dm[] dmVarArr = (dm[]) spannable.getSpans(0, spannable.length(), dm.class);
            for (Object removeSpan : dmVarArr) {
                spannable.removeSpan(removeSpan);
            }
            int applyDimension = (int) TypedValue.applyDimension(1, 1.0f, context.getResources().getDisplayMetrics());
            while (matcher.find()) {
                String group = matcher.group(0);
                int start = matcher.start();
                int end = matcher.end();
                if (end - start < 10) {
                    QiushiEmotionHandler$EmotionData emotion = getInstance().getEmotion(group);
                    if (emotion != null) {
                        eu euVar = new eu(context, QiushiEmotionHandler$EmotionData.a(emotion), i, i2, i3);
                        euVar.setPadding(applyDimension, applyDimension);
                        spannable.setSpan(euVar, start, end, 33);
                    }
                }
            }
        }
    }

    public static Map<String, List<ChatMsgEmotionData>> convert2ChatMsgEmotionData() {
        Collection<QiushiEmotionHandler$EmotionData> values = getInstance().getAll().values();
        Map<String, List<ChatMsgEmotionData>> linkedHashMap = new LinkedHashMap(1);
        List arrayList = new ArrayList();
        int i = 0;
        for (QiushiEmotionHandler$EmotionData qiushiEmotionHandler$EmotionData : values) {
            int i2 = i + 1;
            ChatMsgEmotionData chatMsgEmotionData = new ChatMsgEmotionData();
            if (i2 % 28 == 0) {
                chatMsgEmotionData.name = QiushiEmotionHandler$EmotionData.DELETE.getName();
                chatMsgEmotionData.key = QiushiEmotionHandler$EmotionData.DELETE.getName();
                chatMsgEmotionData.localResource = QiushiEmotionHandler$EmotionData.DELETE.getResId();
            } else {
                chatMsgEmotionData.name = qiushiEmotionHandler$EmotionData.getName();
                chatMsgEmotionData.key = qiushiEmotionHandler$EmotionData.getName();
                chatMsgEmotionData.localResource = qiushiEmotionHandler$EmotionData.getResId();
            }
            arrayList.add(chatMsgEmotionData);
            i = i2;
        }
        ChatMsgEmotionData chatMsgEmotionData2 = new ChatMsgEmotionData();
        chatMsgEmotionData2.name = QiushiEmotionHandler$EmotionData.DELETE.getName();
        chatMsgEmotionData2.key = QiushiEmotionHandler$EmotionData.DELETE.getName();
        chatMsgEmotionData2.localResource = QiushiEmotionHandler$EmotionData.DELETE.getResId();
        arrayList.add(chatMsgEmotionData2);
        linkedHashMap.put("emotion_small", arrayList);
        return linkedHashMap;
    }

    private void a(String str, Context context) {
        if (!TextUtils.isEmpty(str)) {
            Resources resources = context.getResources();
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    QiushiEmotionHandler$EmotionData qiushiEmotionHandler$EmotionData = new QiushiEmotionHandler$EmotionData(jSONArray.getJSONObject(i));
                    QiushiEmotionHandler$EmotionData.a(qiushiEmotionHandler$EmotionData, resources.getIdentifier(QiushiEmotionHandler$EmotionData.b(qiushiEmotionHandler$EmotionData), "drawable", context.getPackageName()));
                    QiushiEmotionHandler$EmotionData.a(qiushiEmotionHandler$EmotionData, "\u0001" + QiushiEmotionHandler$EmotionData.c(qiushiEmotionHandler$EmotionData));
                    b.put(QiushiEmotionHandler$EmotionData.c(qiushiEmotionHandler$EmotionData), qiushiEmotionHandler$EmotionData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, QiushiEmotionHandler$EmotionData> getAll() {
        return new LinkedHashMap(b);
    }

    public QiushiEmotionHandler$EmotionData getEmotion(String str) {
        return (QiushiEmotionHandler$EmotionData) b.get(str);
    }

    public void init(Context context) {
        if (!this.f && !this.g) {
            this.g = true;
            new dl(this, context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
