package cn.v6.sixrooms.socket.IM;

import java.util.EventListener;
import org.json.JSONObject;

public interface IMListener extends EventListener {
    void onActionReceive(int i, long j, String str);

    void onContentReceive(int i, long j, String str, JSONObject jSONObject);
}
