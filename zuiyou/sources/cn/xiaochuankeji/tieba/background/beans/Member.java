package cn.xiaochuankeji.tieba.background.beans;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import cn.htjyb.b.a;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.ui.utils.d;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class Member implements Serializable {
    private static final int ASSESSOR_ROLE_DEFAULT = -100;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    private static final String kBirth = "birth";
    private static final String kCommunityRole = "jury";
    private static final String kKeyAtted = "atted";
    private static final String kKeyAtts = "atts";
    private static final String kKeyAvatar = "avatar";
    private static final String kKeyCover = "cover";
    private static final String kKeyFans = "fans";
    private static final String kKeyIsAssessor = "is_assessor";
    private static final String kKeyIsBind = "isbind";
    private static final String kKeyIsEverBind = "iseverbind";
    private static final String kKeyIsRegistered = "isreg";
    private static final String kKeyMemberGender = "gender";
    private static final String kKeyMemberID = "id";
    private static final String kKeyMemberName = "name";
    private static final String kKeyMemberOfficial = "official";
    private static final String kKeyMemberPhone = "phone";
    private static final String kKeyNewfans = "newfans";
    private static final String kKeySign = "sign";
    private static final long serialVersionUID = 8600008271541715087L;
    @JSONField(name = "assessor_role")
    private int assessorRole;
    private int atted;
    private int atts;
    @JSONField(name = "avatar")
    private long avatarID;
    private long birth;
    private int communityRole;
    private long coverId;
    private int fans;
    private int gender;
    private long id;
    private int isBind;
    private int isEverBind;
    @JSONField(name = "isreg")
    private int isRegistered;
    private int isadm;
    private int liken;
    private Medal medal;
    private String name;
    private int newfans;
    private int official;
    private int openType;
    private String phone;
    private String sign;
    @JSONField(name = "topic_role")
    private int topicRole;
    private int trank;
    private int upCount;
    private int vip;

    public Member(long j) {
        this();
        this.id = j;
    }

    public Member() {
        this.isadm = 0;
        this.official = 0;
        this.liken = 0;
        this.atted = 0;
        this.newfans = 0;
        this.atts = 0;
        this.fans = 0;
        this.coverId = 0;
        this.isBind = 0;
        this.isEverBind = 0;
        this.openType = 0;
        this.assessorRole = ASSESSOR_ROLE_DEFAULT;
        this.communityRole = 0;
        this.id = 0;
        this.name = "";
        this.sign = "";
        this.phone = "";
        this.gender = 0;
        this.trank = 0;
        this.upCount = 0;
        this.coverId = 0;
        this.official = 0;
    }

    public Member(JSONObject jSONObject) {
        this();
        unserializeFrom(jSONObject);
    }

    public boolean isFemale() {
        return 2 == this.gender;
    }

    public boolean isOfficial() {
        return this.official == 1;
    }

    public int getOfficial() {
        return this.official;
    }

    public void setOfficial(int i) {
        this.official = i;
    }

    public int getLiken() {
        return this.liken;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getCoverId() {
        return this.coverId;
    }

    @NonNull
    public String getName() {
        return d.b(this.name);
    }

    public String getRawName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getAvatarID() {
        if (this.avatarID == 1 || this.avatarID == 2 || this.avatarID == 3) {
            return 0;
        }
        return this.avatarID;
    }

    public void setAvatarID(long j) {
        this.avatarID = j;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getPhone() {
        return this.phone;
    }

    @NonNull
    public String getDisplayPhone() {
        if (TextUtils.isEmpty(this.phone)) {
            return "";
        }
        try {
            return this.phone.substring(0, 3) + "****" + this.phone.substring(this.phone.length() - 4, this.phone.length());
        } catch (Exception e) {
            return this.phone;
        }
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public boolean isRegistered() {
        return this.isRegistered == 1;
    }

    public void setIsRegistered(int i) {
        this.isRegistered = i;
    }

    public int getTrank() {
        return this.trank;
    }

    public int getUpCount() {
        return this.upCount;
    }

    public int isAdmin() {
        return this.isadm;
    }

    public int atted() {
        return this.atted;
    }

    public int newfans() {
        return this.newfans;
    }

    public int getAtts() {
        return this.atts;
    }

    public int getFans() {
        return this.fans;
    }

    public void setFans(int i) {
        this.fans = i;
    }

    public void setAtted(int i) {
        this.atted = i;
    }

    public int getTopicRole() {
        return this.topicRole;
    }

    public void setTopicRole(int i) {
        this.topicRole = i;
    }

    public int getAssessorRole() {
        return this.assessorRole;
    }

    public void setAssessorRole(int i) {
        this.assessorRole = i;
    }

    public int getOpenType() {
        return this.openType;
    }

    public void setIsBind(int i) {
        this.isBind = i;
    }

    public long getBirth() {
        return this.birth;
    }

    public Medal getMedal() {
        return this.medal;
    }

    public void setMedal(Medal medal) {
        this.medal = medal;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!Member.class.isInstance(obj)) {
            return false;
        }
        if (this.id != ((Member) obj).getId()) {
            return false;
        }
        return true;
    }

    public a getAvatarPicture() {
        return cn.xiaochuankeji.tieba.background.a.f().a(isFemale() ? Type.kAvatarFemale : Type.kAvatarMale, getAvatarID());
    }

    private void unserializeFrom(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.id = jSONObject.optLong("id");
            this.phone = jSONObject.optString(kKeyMemberPhone);
            this.name = jSONObject.optString("name");
            if (TextUtils.isEmpty(this.name)) {
                this.name = this.name.replace("\n", "");
            }
            this.gender = jSONObject.optInt(kKeyMemberGender);
            this.official = jSONObject.optInt(kKeyMemberOfficial);
            this.birth = jSONObject.optLong(kBirth);
            this.avatarID = jSONObject.optLong(kKeyAvatar);
            this.sign = jSONObject.optString(kKeySign);
            this.isRegistered = jSONObject.optInt(kKeyIsRegistered);
            this.trank = jSONObject.optInt("trank");
            this.upCount = jSONObject.optInt("up");
            this.isadm = jSONObject.optInt("isadm");
            this.liken = jSONObject.optInt("liken");
            this.atted = jSONObject.optInt(kKeyAtted);
            this.newfans = jSONObject.optInt(kKeyNewfans);
            this.atts = jSONObject.optInt(kKeyAtts);
            this.fans = jSONObject.optInt(kKeyFans);
            this.coverId = jSONObject.optLong(kKeyCover);
            this.topicRole = jSONObject.optInt("topic_role");
            this.assessorRole = jSONObject.optInt("assessor_role", ASSESSOR_ROLE_DEFAULT);
            this.communityRole = jSONObject.optInt(kCommunityRole, 0);
            this.isBind = jSONObject.optInt(kKeyIsBind, 0);
            this.isEverBind = jSONObject.optInt(kKeyIsEverBind, 0);
            int optInt = jSONObject.optInt("opentype", -1);
            if (optInt >= 0) {
                this.openType = optInt;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("epaulet");
            if (optJSONObject != null) {
                this.medal = new Medal(optJSONObject);
            }
        }
    }

    public JSONObject serializeTo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put(kKeyMemberPhone, this.phone);
        jSONObject.put("name", this.name);
        jSONObject.put(kKeyMemberGender, this.gender);
        jSONObject.put(kKeyMemberOfficial, this.official);
        jSONObject.put(kKeyAvatar, this.avatarID);
        jSONObject.put(kKeySign, this.sign);
        jSONObject.put(kKeyIsRegistered, this.isRegistered);
        jSONObject.put("trank", this.trank);
        jSONObject.put("up", this.upCount);
        jSONObject.put("isadm", this.isadm);
        jSONObject.put("liken", this.liken);
        jSONObject.put(kKeyAtted, this.atted);
        jSONObject.put(kKeyNewfans, this.newfans);
        jSONObject.put(kKeyAtts, this.atts);
        jSONObject.put(kKeyFans, this.fans);
        jSONObject.put(kKeyCover, this.coverId);
        jSONObject.put("topic_role", this.topicRole);
        jSONObject.put("assessor_role", this.assessorRole);
        jSONObject.put(kCommunityRole, this.communityRole);
        jSONObject.put(kKeyIsBind, this.isBind);
        jSONObject.put(kKeyIsEverBind, this.isEverBind);
        jSONObject.put("opentype", this.openType);
        if (this.medal != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("name", this.medal.name);
            jSONObject2.put("type", this.medal.original);
            jSONObject2.put("click_url", this.medal.click_url);
            jSONObject.put("epaulet", jSONObject2);
        }
        return jSONObject;
    }

    public void clear() {
        this.avatarID = 0;
        this.gender = 1;
        this.sign = "";
        this.name = "";
        this.isBind = 0;
        this.isEverBind = 0;
        this.assessorRole = ASSESSOR_ROLE_DEFAULT;
        this.communityRole = 0;
    }

    public boolean isEverBind() {
        return this.isEverBind == 1;
    }

    public boolean isBind() {
        return this.isBind == 1;
    }

    public int getIsBind() {
        return this.isBind;
    }

    public int getCommunityRole() {
        return this.communityRole;
    }
}
