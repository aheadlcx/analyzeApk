package qsbk.app.model;

import java.io.Serializable;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.json.JSONAble;
import qsbk.app.utils.json.JsonKeyName;

public class BaseUser extends JSONAble implements Serializable {
    @JsonKeyName("uid")
    private String a;
    @JsonKeyName("login")
    private String b;
    @JsonKeyName("icon")
    private String c;

    private BaseUser() {
    }

    public BaseUser(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public static BaseUser optFromJsonObject(JSONObject jSONObject) {
        if (jSONObject != null) {
            return new BaseUser(jSONObject.optString("uid"), jSONObject.optString(QsbkDatabase.LOGIN), jSONObject.optString("icon"));
        }
        throw new IllegalArgumentException(" Paramter can not be null. ");
    }

    public String getUid() {
        return this.a;
    }

    public String getLogin() {
        return this.b;
    }

    public void setLogin(String str) {
        this.b = str;
    }

    public String getIcon() {
        return this.c;
    }

    public void setIcon(String str) {
        this.c = str;
    }

    public String toString() {
        return "ThinUser [uid=" + this.a + ", login=" + this.b + ", icon=" + this.c + "]";
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof BaseUser) && hashCode() == obj.hashCode()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
