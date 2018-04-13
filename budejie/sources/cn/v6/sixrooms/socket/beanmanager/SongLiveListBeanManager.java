package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.SongLiveListBean;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.google.analytics.tracking.android.HitTypes;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SongLiveListBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        LogUtils.e("test", "点歌同意消息下发:  " + jSONObject2.toString());
        if (!"001".equals(jSONObject2.getString("flag"))) {
            return new SongLiveListBean();
        }
        MessageBean songLiveListBean = new SongLiveListBean();
        songLiveListBean.setTypeId(i);
        songLiveListBean.setType(SocketUtil.T_SONG_ADD_CALLED);
        String string = jSONObject2.getString("status");
        jSONObject2 = jSONObject2.getJSONObject("songs");
        List arrayList = new ArrayList();
        SubLiveListBean subLiveListBean = new SubLiveListBean();
        subLiveListBean.setItemnum(1);
        subLiveListBean.setSong_status(string);
        subLiveListBean.setUid(jSONObject2.getString(HistoryOpenHelper.COLUMN_UID));
        subLiveListBean.setMscName(jSONObject2.getString("msc_name"));
        subLiveListBean.setMscFirst(jSONObject2.getString("msc_first"));
        subLiveListBean.setStatus(jSONObject2.getInt("status"));
        subLiveListBean.setSalias(jSONObject2.getString("salias"));
        subLiveListBean.setSongTime(Long.valueOf(jSONObject2.getString("add_tm")).longValue());
        subLiveListBean.setMid(jSONObject2.getString(HitTypes.ITEM));
        subLiveListBean.setId(jSONObject2.getString("id"));
        arrayList.add(subLiveListBean);
        songLiveListBean.setLiveList(arrayList);
        return songLiveListBean;
    }
}
