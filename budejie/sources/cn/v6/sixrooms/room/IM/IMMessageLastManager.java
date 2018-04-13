package cn.v6.sixrooms.room.IM;

import cn.v6.sixrooms.room.bean.ImMessageChatBean;
import cn.v6.sixrooms.room.bean.ImMessageUnreadBean;
import cn.v6.sixrooms.room.bean.ResponseLoginBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SharedPreferencesUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IMMessageLastManager extends IMBaseManager {
    public static final int MSG_TYPE_PLATFORM_FROM_APAD = 2;
    public static final int MSG_TYPE_PLATFORM_FROM_APHONE = 3;
    public static final int MSG_TYPE_PLATFORM_FROM_IPAD = 0;
    public static final int MSG_TYPE_PLATFORM_FROM_IPHONE = 1;
    public static final int MSG_TYPE_PLATFORM_FROM_PC = 9;
    public static final long SYSTEM_INFOMATION_ID = 900000000;
    private static IMMessageLastManager a = new IMMessageLastManager();
    private HashMap<Long, ImMessageUnreadBean> b;
    private ResponseLoginBean c;
    private ImMessageChatBean d;
    private ImMessageChatBean e;
    private List<ImMessageChatBean> f;
    private List<ImMessageChatBean> g;
    private long h = 0;
    private ImMessageUnreadBean i;
    private int j;
    private List<Long> k;
    private boolean l = false;
    private IMMessageLastManager$OnSetTopListener m;

    public boolean destory() {
        a = null;
        return true;
    }

    private IMMessageLastManager() {
        LogUtils.e("IMMessageLastManager", "IMMessageLastManager");
        if (LoginUtils.getLoginUserBean() == null) {
            this.k = new ArrayList();
        } else {
            this.k = SharedPreferencesUtils.getHideList(LoginUtils.getLoginUserBean().getId());
        }
        this.b = new HashMap();
        this.f = Collections.synchronizedList(new ArrayList());
        this.g = Collections.synchronizedList(new ArrayList());
    }

    public static synchronized IMMessageLastManager getInstance() {
        IMMessageLastManager iMMessageLastManager;
        synchronized (IMMessageLastManager.class) {
            if (a == null) {
                a = new IMMessageLastManager();
            }
            iMMessageLastManager = a;
        }
        return iMMessageLastManager;
    }

    public void setMessageOnLogin(String str) {
        int i = 0;
        this.j = 0;
        LogUtils.d("IMMessageLastManager", "liuyue_onLoginInfo: " + str);
        this.c = (ResponseLoginBean) JsonParseUtils.json2Obj(str, ResponseLoginBean.class);
        List msglist = this.c.getMsglist();
        while (i < msglist.size()) {
            ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) msglist.get(i);
            if (!this.k.contains(Long.valueOf(imMessageUnreadBean.getUid()))) {
                this.j += imMessageUnreadBean.getNum();
                this.b.put(Long.valueOf(imMessageUnreadBean.getUid()), imMessageUnreadBean);
            }
            i++;
        }
    }

    public int getFriendApplyNum() {
        return this.c.getRnum();
    }

    public ImMessageChatBean getTempSentSingleMsgBean() {
        return this.d;
    }

    public void onSendMsg(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onSendMsg: " + str);
        this.d = (ImMessageChatBean) JsonParseUtils.json2Obj(str, ImMessageChatBean.class);
    }

    public ImMessageChatBean getTempSentGroupMsgBean() {
        return this.e;
    }

    public void onSendGroupMsg(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onSendGroupMsg: " + str);
        this.e = (ImMessageChatBean) JsonParseUtils.json2Obj(str, ImMessageChatBean.class);
    }

    public void onLastUsers(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onLastUsers: " + str);
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) JsonParseUtils.json2Obj(jSONArray.getString(i), ImMessageUnreadBean.class);
                long uid = imMessageUnreadBean.getUid();
                if (!this.k.contains(Long.valueOf(uid)) && ((ImMessageUnreadBean) this.b.get(Long.valueOf(uid))) == null) {
                    imMessageUnreadBean.setLasttm(imMessageUnreadBean.getTm() + "000000");
                    this.b.put(Long.valueOf(imMessageUnreadBean.getUid()), imMessageUnreadBean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ImMessageChatBean> getTempHistoryList() {
        return this.f;
    }

    public void onReadHistory(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onReadHistory: " + str);
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("list");
            this.f.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                this.f.add(0, (ImMessageChatBean) JsonParseUtils.json2Obj(jSONArray.getString(i), ImMessageChatBean.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ImMessageChatBean> getTempReadOnceList() {
        return this.g;
    }

    public long getOutterTm() {
        return this.h;
    }

    public void onReadOnce(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onReadOnce: " + str);
        try {
            this.g.clear();
            JSONArray jSONArray = new JSONObject(str).getJSONArray("list");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                this.g.add((ImMessageChatBean) JsonParseUtils.json2Obj(jSONArray.getString(i), ImMessageChatBean.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ImMessageUnreadBean getTempUnreadMsg() {
        return this.i;
    }

    public void onNewUnreadMessage(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onNewUnreadMessage: " + str);
        try {
            this.i = (ImMessageUnreadBean) JsonParseUtils.json2Obj(str, ImMessageUnreadBean.class);
            long uid = this.i.getUid();
            this.k.remove(Long.valueOf(uid));
            long uid2;
            ImMessageUnreadBean imMessageUnreadBean;
            if (isGroup(uid)) {
                int i = 0;
                if (LoginUtils.getLoginUserBean() != null) {
                    i = Integer.parseInt(LoginUtils.getLoginUserBean().getId());
                }
                if (this.i.getSenduid() != ((long) i)) {
                    this.j++;
                    uid2 = this.i.getUid();
                    imMessageUnreadBean = (ImMessageUnreadBean) this.b.get(Long.valueOf(uid2));
                    if (imMessageUnreadBean == null) {
                        this.i.setLasttm(this.i.getTm());
                        this.b.put(Long.valueOf(uid2), this.i);
                        return;
                    }
                    imMessageUnreadBean.setNum(imMessageUnreadBean.getNum() + 1);
                    imMessageUnreadBean.setLasttm(this.i.getTm());
                    return;
                }
                return;
            }
            this.j++;
            uid2 = this.i.getUid();
            imMessageUnreadBean = (ImMessageUnreadBean) this.b.get(Long.valueOf(uid2));
            if (imMessageUnreadBean == null) {
                this.i.setLasttm(this.i.getTm());
                this.b.put(Long.valueOf(uid2), this.i);
                return;
            }
            int num = imMessageUnreadBean.getNum();
            if (num < 99) {
                imMessageUnreadBean.setNum(num + 1);
            }
            imMessageUnreadBean.setLasttm(this.i.getTm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onHasReadMsg(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onHasReadMsg: " + str);
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.get(Long.valueOf(jSONArray.getLong(i)));
                if (imMessageUnreadBean != null) {
                    this.j -= imMessageUnreadBean.getNum();
                    imMessageUnreadBean.setNum(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFriendLogin(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onFriendLogin: " + str);
        try {
            ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.get(Long.valueOf(((ImMessageUnreadBean) JsonParseUtils.json2Obj(str, ImMessageUnreadBean.class)).getUid()));
            if (imMessageUnreadBean != null) {
                imMessageUnreadBean.setLogin(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDeleteFriend(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onDeleteFriend: " + str);
        ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.remove(Long.valueOf(Long.parseLong(str)));
        if (imMessageUnreadBean != null) {
            this.j -= imMessageUnreadBean.getNum();
        }
    }

    public void onFriendLogout(String str) {
        LogUtils.d("IMMessageLastManager", "liuyue_onFriendLogout: " + str);
        long j = 0;
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.get(Long.valueOf(j));
        if (imMessageUnreadBean != null) {
            imMessageUnreadBean.setLogin(0);
        }
    }

    public List<ImMessageUnreadBean> getMessageContactList() {
        return new ArrayList(this.b.values());
    }

    public boolean hasContact() {
        return this.b.size() > 0;
    }

    public static boolean isGroup(long j) {
        return j >= 920000000 && j != SYSTEM_INFOMATION_ID;
    }

    public int getNewMsgCount() {
        return this.j;
    }

    public boolean isMessageDataChange() {
        return this.l;
    }

    public void onQuitGroup(String str) {
        ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.remove(Long.valueOf(Long.parseLong(str)));
        this.l = imMessageUnreadBean != null;
        if (imMessageUnreadBean != null) {
            this.j -= imMessageUnreadBean.getNum();
        }
    }

    public void onFansLogout(String str) {
        ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.get(Long.valueOf(Long.parseLong(str)));
        if (imMessageUnreadBean != null) {
            imMessageUnreadBean.setLogin(0);
        }
    }

    public void onFansRemove(String str) {
        ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.remove(Long.valueOf(Long.parseLong(str)));
        if (imMessageUnreadBean != null) {
            this.j -= imMessageUnreadBean.getNum();
        }
    }

    public void onFansLogin(String str) {
        try {
            ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.get(Long.valueOf(Long.parseLong(new JSONObject(str).getString(HistoryOpenHelper.COLUMN_UID))));
            if (imMessageUnreadBean != null) {
                imMessageUnreadBean.setLogin(1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        a = null;
    }

    public void setContactHide(long j) {
        this.k.add(Long.valueOf(j));
        ImMessageUnreadBean imMessageUnreadBean = (ImMessageUnreadBean) this.b.remove(Long.valueOf(j));
        if (imMessageUnreadBean != null) {
            this.j -= imMessageUnreadBean.getNum();
        }
    }

    public void saveHideList() {
        SharedPreferencesUtils.saveHideList(LoginUtils.getLoginUserBean().getId(), this.k);
    }

    public void setOnSetTopListener(IMMessageLastManager$OnSetTopListener iMMessageLastManager$OnSetTopListener) {
        this.m = iMMessageLastManager$OnSetTopListener;
    }

    public void setTop(ImMessageUnreadBean imMessageUnreadBean) {
        long uid = imMessageUnreadBean.getUid();
        ImMessageUnreadBean imMessageUnreadBean2 = (ImMessageUnreadBean) this.b.get(Long.valueOf(uid));
        if (imMessageUnreadBean2 == null) {
            this.b.put(Long.valueOf(uid), imMessageUnreadBean);
        } else {
            imMessageUnreadBean2.setLasttm(imMessageUnreadBean.getTm());
        }
        if (this.m != null) {
            this.m.onSetTop();
        }
    }
}
