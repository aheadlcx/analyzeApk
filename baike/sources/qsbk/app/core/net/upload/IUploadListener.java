package qsbk.app.core.net.upload;

import org.json.JSONObject;

public interface IUploadListener {
    void uploadProgress(String str, double d);

    void uploadStat(String str, boolean z, String str2, JSONObject jSONObject);
}
