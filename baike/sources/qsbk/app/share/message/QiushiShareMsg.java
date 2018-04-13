package qsbk.app.share.message;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.json.JSONAble;

public class QiushiShareMsg extends JSONAble {
    public String artId;
    public String cImgUrl;
    public CircleTopic circleTopic;
    public String plainText;
    public String type;

    public static String toJsonString(QiushiShareMsg qiushiShareMsg) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", qiushiShareMsg.type);
            jSONObject.put("artId", qiushiShareMsg.artId);
            jSONObject.put("cImgUrl", qiushiShareMsg.cImgUrl);
            jSONObject.put("plainText", qiushiShareMsg.plainText);
            if (qiushiShareMsg.circleTopic != null) {
                jSONObject.put("circleTopic", CircleTopic.toJson(qiushiShareMsg.circleTopic));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public CircleTopic getCircleTopic() {
        return this.circleTopic;
    }

    public void setCircleTopic(CircleTopic circleTopic) {
        this.circleTopic = circleTopic;
    }

    public void setPlainText(String str) {
        this.plainText = str;
    }

    public String getArticleId() {
        return this.artId;
    }

    public void setArticleId(String str) {
        this.artId = str;
    }

    public String getCoverImageUrl() {
        return this.cImgUrl;
    }

    public void setCoverImageUrl(String str) {
        this.cImgUrl = str;
    }

    public String getMsgContent() {
        return this.plainText;
    }

    public String getShareMsgType() {
        return this.type;
    }

    public void setShareMsgType(String str) {
        this.type = str;
    }
}
