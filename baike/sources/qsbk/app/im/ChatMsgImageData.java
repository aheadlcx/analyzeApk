package qsbk.app.im;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.json.JSONAble;

public class ChatMsgImageData extends JSONAble {
    public boolean hasUploaded = false;
    public int height;
    public String oldUrl;
    public int progress;
    public int status;
    public String url;
    public int width;

    public interface Status {
        public static final int DONE = 1;
        public static final int DOWNLOADING = 3;
        public static final int FAILED = 2;
        public static final int UPLOADING = 4;
    }

    public ChatMsgImageData(String str) {
        try {
            parseFromJSONObject(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ChatMsgImageData(String str, int i, int i2) {
        this.url = str;
        this.width = i;
        this.height = i2;
        this.oldUrl = str;
    }

    public ChatMsgImageData(String str, int i, int i2, boolean z) {
        this.url = str;
        this.width = i;
        this.height = i2;
        this.hasUploaded = z;
        this.status = 1;
    }

    public String toString() {
        return "ChatMsgImageData{url='" + this.url + '\'' + ", height=" + this.height + ", width=" + this.width + ", status=" + this.status + ", progress=" + this.progress + ", hasUploaded=" + this.hasUploaded + ", oldUrl='" + this.oldUrl + '\'' + '}';
    }
}
