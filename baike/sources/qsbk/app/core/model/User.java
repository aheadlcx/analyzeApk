package qsbk.app.core.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;

public class User implements Serializable {
    public static final int ADMIN = 1;
    public static final String FEMALE = "f";
    public static final String MAN = "m";
    public static final int NOT_ADMIN = 0;
    public static final String UNDEFINED = "u";
    public static final int VERIFY_CODE_FAIL_MANUAL = 6;
    public static final int VERIFY_CODE_FAIL_ONCE = 4;
    public static final int VERIFY_CODE_FAIL_TIME_LIMIT = 5;
    public static final int VERIFY_CODE_MANUAL_AUDIT = 3;
    public static final int VERIFY_CODE_NOT_VERIFIED = 1;
    public static final int VERIFY_CODE_SUCCESS = 2;
    public int age;
    public String astrology;
    public String avatar;
    public String avatar_dec_url;
    public String badge;
    public int code = 1;
    public long contribute;
    public long coupon_receive;
    public List<User$CouponRecord> coupon_record;
    public long coupon_send;
    public int fame;
    public FamilyInfo family_info;
    @SerializedName(alternate = {"fl"}, value = "family_level")
    public int family_level;
    public int follow_count;
    public int followed_count;
    public String gender;
    public String headurl;
    public String hometown;
    public long id;
    public String intro;
    public int is_admin;
    public boolean is_block;
    public boolean is_follow;
    public boolean is_followed;
    public boolean isliving;
    public String job;
    public int level;
    public int like_count;
    public String list_dec_url;
    public String location;
    public int manual;
    public String name;
    public long nick_id;
    public long origin;
    public long origin_id;
    public int pic_count;
    public long platform_id;
    public String t;
    public int tag_count;
    public int voted_count;

    public boolean isMan() {
        return "m".equalsIgnoreCase(this.gender);
    }

    public boolean isFemale() {
        return FEMALE.equalsIgnoreCase(this.gender);
    }

    public boolean isUndefined() {
        return UNDEFINED.equalsIgnoreCase(this.gender);
    }

    public boolean isMe() {
        return (getOriginId() == AppUtils.getInstance().getUserInfoProvider().getUserId() && getOrigin() == AppUtils.getInstance().getUserInfoProvider().getUserOrigin()) || getPlatformId() == AppUtils.getInstance().getUserInfoProvider().getUserPlatformId();
    }

    public boolean isFollow() {
        return this.is_follow || isMe();
    }

    public FamilyInfo getFamilyInfo() {
        return this.family_info;
    }

    public boolean isAdmin() {
        return this.is_admin == 1;
    }

    public void reverseAdmin() {
        int i = 1;
        if (this.is_admin == 1) {
            i = 0;
        }
        this.is_admin = i;
    }

    public long getPlatformId() {
        return this.platform_id > 0 ? this.platform_id : this.id;
    }

    public long getOriginId() {
        return this.origin_id > 0 ? this.origin_id : this.id;
    }

    public long getOrigin() {
        return this.origin > 0 ? this.origin : (long) Constants.SOURCE;
    }

    public String getLocation() {
        return this.location;
    }

    public String getAstrology() {
        return this.astrology;
    }

    public String getSexInChinese() {
        return isFemale() ? "她" : "他";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.id != ((User) obj).id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Long.valueOf(this.id).hashCode();
    }

    public boolean isVerfied() {
        return this.code == 2;
    }

    public void setVerifyCode(int i, int i2) {
        this.code = i;
        this.manual = i2;
    }
}
