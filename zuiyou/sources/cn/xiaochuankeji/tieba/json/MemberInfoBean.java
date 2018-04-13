package cn.xiaochuankeji.tieba.json;

import cn.xiaochuankeji.tieba.background.beans.Medal;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class MemberInfoBean implements Serializable {
    public static final int MemberType_Admin = 2;
    public static final int MemberType_Common = 0;
    public static final int MemberType_Escort = 3;
    public static final int MemberType_Owner = 4;
    public static final int MemberType_Talent = 1;
    @JSONField(name = "assessor_role")
    private int assessorRole;
    @JSONField(name = "avatar")
    private long avatarId;
    @JSONField(name = "birth")
    private long birthTimestamp;
    @JSONField(name = "fans")
    private int fansCount;
    @JSONField(name = "atts")
    private int followCount;
    @JSONField(name = "atted")
    private int followStatus;
    @JSONField(name = "gender")
    private int gender;
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "isadm")
    private boolean isAdmin;
    @JSONField(name = "is_assessor")
    private boolean isAssessor;
    @JSONField(name = "newfans")
    private boolean isNewFan;
    @JSONField(name = "isreg")
    private boolean isRegister;
    @JSONField(name = "vip")
    private boolean isVip;
    @JSONField(name = "up")
    private int likeCount;
    @JSONField(name = "liken")
    private int liken;
    @JSONField(name = "medal")
    private Medal medal;
    @JSONField(name = "name")
    private String nickName;
    @JSONField(name = "official")
    private int official;
    @JSONField(name = "phone")
    private String phone;
    @JSONField(name = "cover")
    private long profileCoverId;
    @JSONField(name = "trank")
    private int rank;
    @JSONField(name = "rec_post_count")
    private int recPostCount;
    @JSONField(name = "topic_role")
    private int topicRole;
    @JSONField(name = "sign")
    private String userSign;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public boolean isRegister() {
        return this.isRegister;
    }

    public void setRegister(boolean z) {
        this.isRegister = z;
    }

    public boolean isVip() {
        return this.isVip;
    }

    public void setVip(boolean z) {
        this.isVip = z;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public long getAvatarId() {
        if (this.avatarId == 1 || this.avatarId == 2 || this.avatarId == 3) {
            return 0;
        }
        return this.avatarId;
    }

    public void setAvatarId(long j) {
        this.avatarId = j;
    }

    public String getUserSign() {
        return this.userSign;
    }

    public void setUserSign(String str) {
        this.userSign = str;
    }

    public int getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(int i) {
        this.likeCount = i;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public boolean isFollowed() {
        return this.followStatus >= 1;
    }

    public void setAdmin(boolean z) {
        this.isAdmin = z;
    }

    public boolean isAssessor() {
        return this.isAssessor;
    }

    public void setAssessor(boolean z) {
        this.isAssessor = z;
    }

    public int getLiken() {
        return this.liken;
    }

    public void setLiken(int i) {
        this.liken = i;
    }

    public int getFollowStatus() {
        return this.followStatus;
    }

    public void setFollowStatus(int i) {
        this.followStatus = i;
    }

    public boolean isNewFan() {
        return this.isNewFan;
    }

    public void setNewFan(boolean z) {
        this.isNewFan = z;
    }

    public int getFollowCount() {
        return this.followCount;
    }

    public void setFollowCount(int i) {
        this.followCount = i;
    }

    public int getFansCount() {
        return this.fansCount;
    }

    public void setFansCount(int i) {
        this.fansCount = i;
    }

    public int getTopicRole() {
        return this.topicRole;
    }

    public void setTopicRole(int i) {
        this.topicRole = i;
    }

    public int getRecPostCount() {
        return this.recPostCount;
    }

    public void setRecPostCount(int i) {
        this.recPostCount = i;
    }

    public int getAssessorRole() {
        return this.assessorRole;
    }

    public void setAssessorRole(int i) {
        this.assessorRole = i;
    }

    public long getProfileCoverId() {
        return this.profileCoverId;
    }

    public void setProfileCoverId(long j) {
        this.profileCoverId = j;
    }

    public long getBirthTimestamp() {
        return this.birthTimestamp;
    }

    public void setBirthTimestamp(long j) {
        this.birthTimestamp = j;
    }

    public Medal getMedal() {
        return this.medal;
    }

    public void setMedal(Medal medal) {
        this.medal = medal;
    }

    public boolean isOfficial() {
        return this.official == 1;
    }

    public String toString() {
        return "MemberInfoBean{id=" + this.id + ", isRegister=" + this.isRegister + ", isVip=" + this.isVip + ", phone='" + this.phone + '\'' + ", nickName='" + this.nickName + '\'' + ", gender=" + this.gender + ", avatarId=" + this.avatarId + ", userSign='" + this.userSign + '\'' + ", likeCount=" + this.likeCount + ", rank=" + this.rank + ", isAdmin=" + this.isAdmin + ", isAssessor=" + this.isAssessor + ", liken=" + this.liken + ", followStatus=" + this.followStatus + ", isNewFan=" + this.isNewFan + ", followCount=" + this.followCount + ", fansCount=" + this.fansCount + ", topicRole=" + this.topicRole + ", recPostCount=" + this.recPostCount + ", assessorRole=" + this.assessorRole + ", profileCoverId=" + this.profileCoverId + ", birthTimestamp=" + this.birthTimestamp + ", official=" + this.official + ", medal=" + this.medal + '}';
    }
}
