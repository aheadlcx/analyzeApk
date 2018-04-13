package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.VoteBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class VoteBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        MessageBean voteBean = new VoteBean();
        voteBean.setTypeId(i);
        voteBean.setTm(jSONObject.getLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        voteBean.setVotenum(JsonParseUtils.getInt(jSONObject, "votenum"));
        voteBean.setVotetop(JsonParseUtils.getInt(jSONObject2, "votetop"));
        voteBean.setGiftnum(JsonParseUtils.getInt(jSONObject2, "giftnum"));
        voteBean.setGifttop(JsonParseUtils.getInt(jSONObject2, "gifttop"));
        return voteBean;
    }
}
