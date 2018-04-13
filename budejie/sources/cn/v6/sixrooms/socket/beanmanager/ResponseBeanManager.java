package cn.v6.sixrooms.socket.beanmanager;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.ErrorBean;
import cn.v6.sixrooms.bean.FireworkSuccessBean;
import cn.v6.sixrooms.bean.GiftListBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.MiniGameIdBean;
import cn.v6.sixrooms.bean.SongLiveListBean;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alipay.sdk.util.j;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.google.analytics.tracking.android.HitTypes;
import com.tencent.stat.DeviceInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean authKeyBean = new AuthKeyBean();
        authKeyBean.setTypeId(i);
        long j = jSONObject.getLong(IXAdRequestInfo.MAX_TITLE_LENGTH);
        authKeyBean.setTm(j);
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        String string = jSONObject2.getString("flag");
        String string2 = jSONObject2.getString("t");
        String str2;
        if ("001".equals(string)) {
            MessageBean errorBean;
            if (SocketUtil.T_PROP_PROP.equals(string2)) {
                CharSequence string3 = jSONObject2.getString("content");
                if (!TextUtils.isEmpty(string3) && GiftIdStrs.SUPER_FIREWORKS.equals(string3)) {
                    errorBean = new ErrorBean();
                    errorBean.setContent("您赠送的超级烟花将在5分钟后引爆，敬请期待！");
                    return errorBean;
                }
            }
            if (SocketUtil.SONG_CALLEDOPE_RATE.equals(string2)) {
                Object string4 = jSONObject2.getString("content");
                if (!TextUtils.isEmpty(string4)) {
                    errorBean = new ErrorBean();
                    errorBean.setContent(string4);
                    return errorBean;
                }
            }
            if (SocketUtil.T_MSG_PRIVATE.equals(string2) || SocketUtil.T_MSG_ROOM.equals(string2) || SocketUtil.T_MSG_SHAREMSG.equals(string2)) {
                authKeyBean.setAuthKey(jSONObject2.getJSONObject("content").getString("authKey"));
                return authKeyBean;
            } else if (SocketUtil.T_PRIV_STOPMSG.equals(string2) || SocketUtil.T_PRIV_RECOVER.equals(string2) || SocketUtil.T_PRIV_KILL.equals(string2) || SocketUtil.T_PRIV_ADD_MANAGER.equals(string2) || SocketUtil.T_PRIV_REVOKE_MANAGER.equals(string2) || SocketUtil.T_PRIV_ADD_ADMIN.equals(string2) || SocketUtil.T_PRIV_REVOKE_ADMIN.equals(string2)) {
                authKeyBean = new ErrorBean();
                str2 = "";
                if (SocketUtil.T_PRIV_STOPMSG.equals(string2)) {
                    str2 = "禁言操作已成功";
                } else if (SocketUtil.T_PRIV_RECOVER.equals(string2)) {
                    str2 = "解除禁言操作已成功";
                } else if (SocketUtil.T_PRIV_KILL.equals(string2)) {
                    str2 = "踢出房间操作已成功";
                } else if (SocketUtil.T_PRIV_ADD_MANAGER.equals(string2)) {
                    str2 = "升总管操作已成功";
                } else if (SocketUtil.T_PRIV_REVOKE_MANAGER.equals(string2)) {
                    str2 = "撤总管操作已成功";
                } else if (SocketUtil.T_PRIV_ADD_ADMIN.equals(string2)) {
                    str2 = "升管理操作已成功";
                } else if (SocketUtil.T_PRIV_REVOKE_ADMIN.equals(string2)) {
                    str2 = "撤管理操作已成功";
                }
                authKeyBean.setContent(str2);
                authKeyBean.setType(string2);
                return authKeyBean;
            } else if (SocketUtil.T_SONG_SHOW_LIVE_LIST.equals(string2) || SocketUtil.T_SONG_SHOW_CALLED_LIST.equals(string2)) {
                authKeyBean = new SongLiveListBean();
                authKeyBean.setTypeId(i);
                authKeyBean.setType(string2);
                ArrayList arrayList = new ArrayList();
                authKeyBean.setLiveList(arrayList);
                JSONObject jSONObject3 = jSONObject2.getJSONObject("content");
                JSONArray jSONArray = jSONObject3.getJSONArray("songlist");
                if (SocketUtil.T_SONG_SHOW_CALLED_LIST.equals(string2) && jSONArray.length() == 0) {
                    SubLiveListBean subLiveListBean = new SubLiveListBean();
                    subLiveListBean.setItemnum(jSONObject3.getInt("itemnum"));
                    subLiveListBean.setMemo(jSONObject3.getString(j.b));
                    arrayList.add(subLiveListBean);
                }
                if (jSONArray != null) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject4 = jSONArray.getJSONObject(i2);
                        SubLiveListBean subLiveListBean2 = new SubLiveListBean();
                        subLiveListBean2.setUid(jSONObject4.getString(HistoryOpenHelper.COLUMN_UID));
                        subLiveListBean2.setMscName(jSONObject4.getString("msc_name"));
                        subLiveListBean2.setMscFirst(jSONObject4.getString("msc_first"));
                        subLiveListBean2.setStatus(jSONObject4.getInt("status"));
                        if (SocketUtil.T_SONG_SHOW_CALLED_LIST.equals(string2)) {
                            subLiveListBean2.setSalias(jSONObject4.getString("salias"));
                            subLiveListBean2.setSongTime(Long.valueOf(jSONObject4.getString("add_tm")).longValue());
                            subLiveListBean2.setItemnum(jSONObject3.getInt("itemnum"));
                            subLiveListBean2.setMemo(jSONObject3.getString(j.b));
                            subLiveListBean2.setMid(jSONObject4.getString(HitTypes.ITEM));
                            subLiveListBean2.setId(jSONObject4.getString("id"));
                        }
                        if (SocketUtil.T_SONG_SHOW_LIVE_LIST.equals(string2)) {
                            subLiveListBean2.setMid(jSONObject4.getString(DeviceInfo.TAG_MID));
                        }
                        arrayList.add(subLiveListBean2);
                    }
                }
                return authKeyBean;
            } else if (SocketUtil.T_SONG_ADD_CALLED.equals(string2)) {
                errorBean = new ErrorBean();
                errorBean.setContent("成功加入点歌队列");
                return errorBean;
            } else if (SocketUtil.T_ADD_LIVE_SONG.equals(string2) || SocketUtil.T_DELETE_LIVE_SONG.equals(string2) || SocketUtil.T_UPDATE_LIVE_SONG.equals(string2)) {
                errorBean = new ErrorBean();
                errorBean.setType(string2);
                return errorBean;
            } else if (SocketUtil.T_MSG_CHANGZHAN_VOTE.equals(string2) || SocketUtil.T_MSG_CHANGZHANFINAL_VOTE.equals(string2)) {
                ErrorBean errorBean2 = (ErrorBean) JsonParseUtils.json2Obj(jSONObject2.toString(), ErrorBean.class);
                errorBean2.setTm(j);
                errorBean2.setTypeId(i);
                return errorBean2;
            } else if (SocketUtil.T_PROP_FREEVOTE.equals(string2)) {
                return (ErrorBean) JsonParseUtils.json2Obj(jSONObject2.toString(), ErrorBean.class);
            } else {
                if (SocketUtil.T_MSG_GIFT_LIST_GET.equals(string2)) {
                    return (GiftListBean) JsonParseUtils.json2Obj(jSONObject2.toString(), GiftListBean.class);
                }
                if (SocketUtil.T_PROP_FIREWORK.equals(string2)) {
                    JsonParseUtils.json2Obj(jSONObject2.getJSONObject("content").toString(), FireworkSuccessBean.class);
                    return null;
                } else if (!SocketUtil.T_ROOM_MINIGAME_OPEN.equals(string2) && !SocketUtil.T_ROOM_MINIGAME_CLOSE.equals(string2)) {
                    return null;
                } else {
                    String string5 = jSONObject2.getString("content");
                    errorBean = new MiniGameIdBean();
                    errorBean.setGameId(string5);
                    errorBean.setType(string2);
                    return errorBean;
                }
            }
        }
        authKeyBean = new ErrorBean();
        authKeyBean.setFlag(string);
        authKeyBean.setT(string2);
        if (SocketUtil.FLAG_ON_FAST.equals(string)) {
            str2 = "发言过快，请稍后再试！";
        } else if ("105".equals(string)) {
            str2 = "您当前拥有的六币数量不足，请充值";
        } else if ("406".equals(string)) {
            str2 = "您还没有绑定手机号，绑定手机号后可以发言哦。";
        } else if (SocketUtil.T_PROP_FIREWORK.equals(string2)) {
            return (FireworkSuccessBean) JsonParseUtils.json2Obj(jSONObject2.getJSONObject("content").toString(), FireworkSuccessBean.class);
        } else {
            str2 = jSONObject2.getString("content");
        }
        authKeyBean.setContent(str2);
        return authKeyBean;
    }
}
