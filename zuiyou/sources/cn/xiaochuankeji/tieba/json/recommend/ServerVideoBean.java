package cn.xiaochuankeji.tieba.json.recommend;

import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerVideoBean implements Serializable {
    @JSONField(name = "h5id")
    public String h5Id;
    @JSONField(name = "h5type")
    public String h5Type;
    @JSONField(name = "h5url")
    public String h5Url;
    @JSONField(name = "url")
    public String originUrl;
    @JSONField(name = "playcnt")
    public int playCount;
    @JSONField(name = "priority")
    public int priority;
    @JSONField(name = "thumb")
    public long thumbId;
    @JSONField(name = "urlext")
    public String urlExt;
    @JSONField(name = "urlwm")
    public String urlWithWM;
    @JSONField(name = "urlsrc")
    public String urlsrc;
    @JSONField(name = "dur")
    public int videoDur;

    public static ServerVideo getServerVideoFromBean(ServerVideoBean serverVideoBean) {
        try {
            return new ServerVideo(new JSONObject(JSON.toJSONString(serverVideoBean)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
