package cn.xiaochuankeji.tieba.json.recommend;

import cn.xiaochuankeji.tieba.background.data.ServerImage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerImageBean implements Serializable {
    @JSONField(deserialize = false, serialize = false)
    private static final String kFormatGif = "gif";
    @JSONField(deserialize = false, serialize = false)
    private static final String kFormatMP4 = "mp4";
    @JSONField(name = "dancnt")
    public int danmuCount;
    @JSONField(name = "fmt")
    public String fmt;
    @JSONField(name = "h")
    public int height;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "mp4")
    public int mp4Id;
    @JSONField(deserialize = false, serialize = false)
    public ServerVideoBean videoBean;
    @JSONField(name = "video")
    public int videoStatus;
    @JSONField(name = "w")
    public int width;

    public static boolean asVideo(ServerImageBean serverImageBean) {
        return serverImageBean.videoStatus == 1;
    }

    public static boolean asGif(ServerImageBean serverImageBean) {
        return kFormatGif.equalsIgnoreCase(serverImageBean.fmt);
    }

    public static boolean asMp4(ServerImageBean serverImageBean) {
        return serverImageBean.mp4Id > 0;
    }

    public static ServerImage getServerImageFromServerImageBean(ServerImageBean serverImageBean) {
        try {
            return new ServerImage(new JSONObject(JSON.toJSONString(serverImageBean)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
