package cn.v6.sixrooms.bean;

import cn.v6.sixrooms.hall.BaseBean;
import cn.v6.sixrooms.room.game.MiniGameBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrapRoomInfo extends BaseBean {
    private RoomEventFloatBean eventFloat;
    private RoomEventFloatTwoBean eventFloatTwo;
    private String giftType;
    private List<UserInfoBean> giftUserConf;
    private String isAnchor;
    private String isBirth;
    private String isFav;
    private boolean isRoomManager;
    private String isTalent;
    private String isUserSafe = "";
    private LiveinfoBean liveinfoBean;
    private LiveinfoBean[] liveinfoBeans;
    private String miniGameIntro;
    private List<MiniGameBean> miniGameList;
    private ArrayList<RoommsgBean> privateRoommsgBeans;
    private ArrayList<RoommsgBean> publicRoommsgBeans;
    private ArrayList<RoomNotice> roomNotices;
    private RoomParamInfoBean roomParamInfoBean;
    private RoomPropConfBean roomPropConf;
    private RoominfoBean roominfoBean;
    private SuperGMemBean superGMem;
    private String talentFinal;
    private TalentFloatBean talentFloat;
    private String tplType;
    private WrapUserInfo wrapUserInfo;

    public String getMiniGameIntro() {
        return this.miniGameIntro;
    }

    public void setMiniGameIntro(String str) {
        this.miniGameIntro = str;
    }

    public List<MiniGameBean> getMiniGameList() {
        return this.miniGameList;
    }

    public void setMiniGameList(List<MiniGameBean> list) {
        this.miniGameList = list;
    }

    public RoomEventFloatTwoBean getEventFloatTwo() {
        return this.eventFloatTwo;
    }

    public void setEventFloatTwo(RoomEventFloatTwoBean roomEventFloatTwoBean) {
        this.eventFloatTwo = roomEventFloatTwoBean;
    }

    public RoomEventFloatBean getEventFloat() {
        return this.eventFloat;
    }

    public void setEventFloat(RoomEventFloatBean roomEventFloatBean) {
        this.eventFloat = roomEventFloatBean;
    }

    public List<UserInfoBean> getGiftUserConf() {
        return this.giftUserConf;
    }

    public void setGiftUserConf(List<UserInfoBean> list) {
        this.giftUserConf = list;
    }

    public LiveinfoBean[] getLiveinfoBeans() {
        return this.liveinfoBeans;
    }

    public void setLiveinfoBeans(LiveinfoBean[] liveinfoBeanArr) {
        this.liveinfoBeans = liveinfoBeanArr;
    }

    public String getTplType() {
        return this.tplType;
    }

    public void setTplType(String str) {
        this.tplType = str;
    }

    public boolean isRoomManager() {
        return this.isRoomManager;
    }

    public void setRoomManager(boolean z) {
        this.isRoomManager = z;
    }

    public WrapUserInfo getWrapUserInfo() {
        return this.wrapUserInfo;
    }

    public void setWrapUserInfo(WrapUserInfo wrapUserInfo) {
        this.wrapUserInfo = wrapUserInfo;
    }

    public RoominfoBean getRoominfoBean() {
        return this.roominfoBean;
    }

    public void setRoominfoBean(RoominfoBean roominfoBean) {
        this.roominfoBean = roominfoBean;
    }

    public LiveinfoBean getLiveinfoBean() {
        return this.liveinfoBean;
    }

    public void setLiveinfoBean(LiveinfoBean liveinfoBean) {
        this.liveinfoBean = liveinfoBean;
    }

    public RoomParamInfoBean getRoomParamInfoBean() {
        return this.roomParamInfoBean;
    }

    public void setRoomParamInfoBean(RoomParamInfoBean roomParamInfoBean) {
        this.roomParamInfoBean = roomParamInfoBean;
    }

    public ArrayList<RoommsgBean> getPublicRoommsgBeans() {
        return this.publicRoommsgBeans;
    }

    public void setPublicRoommsgBeans(ArrayList<RoommsgBean> arrayList) {
        this.publicRoommsgBeans = arrayList;
    }

    public String getIsFav() {
        return this.isFav;
    }

    public void setIsFav(String str) {
        this.isFav = str;
    }

    public ArrayList<RoomNotice> getRoomNotices() {
        return this.roomNotices;
    }

    public void setRoomNotices(ArrayList<RoomNotice> arrayList) {
        this.roomNotices = arrayList;
    }

    public String getIsUserSafe() {
        return this.isUserSafe;
    }

    public void setIsUserSafe(String str) {
        this.isUserSafe = str;
    }

    public String getIsBirth() {
        return this.isBirth;
    }

    public void setIsBirth(String str) {
        this.isBirth = str;
    }

    public String getIsTalent() {
        return this.isTalent;
    }

    public void setIsTalent(String str) {
        this.isTalent = str;
    }

    public String getGiftType() {
        return this.giftType;
    }

    public void setGiftType(String str) {
        this.giftType = str;
    }

    public TalentFloatBean getTalentFloat() {
        return this.talentFloat;
    }

    public void setTalentFloat(TalentFloatBean talentFloatBean) {
        this.talentFloat = talentFloatBean;
    }

    public String getTalentFinal() {
        return this.talentFinal;
    }

    public void setTalentFinal(String str) {
        this.talentFinal = str;
    }

    public String getIsAnchor() {
        return this.isAnchor;
    }

    public void setIsAnchor(String str) {
        this.isAnchor = str;
    }

    public ArrayList<RoommsgBean> getPrivateRoommsgBeans() {
        return this.privateRoommsgBeans;
    }

    public void setPrivateRoommsgBeans(ArrayList<RoommsgBean> arrayList) {
        this.privateRoommsgBeans = arrayList;
    }

    public SuperGMemBean getSuperGMem() {
        return this.superGMem;
    }

    public void setSuperGMem(SuperGMemBean superGMemBean) {
        this.superGMem = superGMemBean;
    }

    public RoomPropConfBean getRoomPropConf() {
        return this.roomPropConf;
    }

    public void setRoomPropConf(RoomPropConfBean roomPropConfBean) {
        this.roomPropConf = roomPropConfBean;
    }

    public String toString() {
        return "WrapRoomInfo{roominfoBean=" + this.roominfoBean + ", liveinfoBean=" + this.liveinfoBean + ", roomParamInfoBean=" + this.roomParamInfoBean + ", publicRoommsgBeans=" + this.publicRoommsgBeans + ", privateRoommsgBeans=" + this.privateRoommsgBeans + ", isFav='" + this.isFav + '\'' + ", isBirth='" + this.isBirth + '\'' + ", roomNotices=" + this.roomNotices + ", wrapUserInfo=" + this.wrapUserInfo + ", isUserSafe='" + this.isUserSafe + '\'' + ", isRoomManager=" + this.isRoomManager + ", tplType='" + this.tplType + '\'' + ", isTalent='" + this.isTalent + '\'' + ", giftType='" + this.giftType + '\'' + ", liveinfoBeans=" + Arrays.toString(this.liveinfoBeans) + ", giftUserConf=" + this.giftUserConf + ", talentFloat=" + this.talentFloat + ", talentFinal='" + this.talentFinal + '\'' + ", eventFloat=" + this.eventFloat + ", isAnchor='" + this.isAnchor + '\'' + ", superGMem=" + this.superGMem + ", roomPropConf=" + this.roomPropConf + '}';
    }
}
