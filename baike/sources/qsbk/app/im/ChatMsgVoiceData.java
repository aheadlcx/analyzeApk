package qsbk.app.im;

import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.json.JSONAble;
import qsbk.app.utils.json.JsonPrivate;

public class ChatMsgVoiceData extends JSONAble {
    public static final int MAX_DURATION = 60;
    public static final int MIN_DURATION = 1;
    public int duration;
    @JsonPrivate
    public String path;
    public boolean played;
    public int progress;
    public int status;
    public String url;

    public ChatMsgVoiceData(String str) {
        try {
            parseFromJSONObject(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ChatMsgVoiceData(String str, int i) {
        this.path = str;
        this.duration = i;
    }

    public static String formatDuration(int i) {
        int min = Math.min(60, Math.max(1, i));
        return String.format(Locale.US, "%02d″", new Object[]{Integer.valueOf(min)});
    }
}
