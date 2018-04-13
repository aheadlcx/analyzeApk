package cn.xiaochuankeji.tieba.background.data.post;

import cn.xiaochuankeji.tieba.api.subject.SubjectJson;
import cn.xiaochuankeji.tieba.api.subject.a;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import rx.e;

public class Subject extends AbstractPost implements Serializable {
    private static final String COVER = "cover_id";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String URL = "url";
    @JSONField(name = "cover_id")
    public long cover_id;
    @JSONField(name = "id")
    public long id;
    public int pos = -1;
    @JSONField(name = "title")
    public String title;
    @JSONField(name = "url")
    public String url;

    public Subject(JSONObject jSONObject) {
        parseBaseInfo(jSONObject);
    }

    public int classType() {
        return 2;
    }

    public long getMemberId() {
        return 0;
    }

    public void setFollowStatus(int i) {
    }

    public void parseBaseInfo(JSONObject jSONObject) {
        if (jSONObject != null) {
            super.parseBaseInfo(jSONObject);
            this.id = jSONObject.optLong("id");
            this.title = jSONObject.optString("title");
            this.cover_id = jSONObject.optLong(COVER);
            this.url = jSONObject.optString("url");
        }
    }

    public JSONObject serializeTo() throws JSONException {
        JSONObject serializeTo = super.serializeTo();
        serializeTo.put("id", this.id);
        serializeTo.put("title", this.title);
        serializeTo.put(COVER, this.cover_id);
        serializeTo.put("url", this.url);
        return serializeTo;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Subject subject = (Subject) obj;
        if (this.pos != subject.pos || this.id != subject.id || this.cover_id != subject.cover_id) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(subject.title)) {
                return false;
            }
        } else if (subject.title != null) {
            return false;
        }
        if (this.url != null) {
            z = this.url.equals(subject.url);
        } else if (subject.url != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int i2 = ((this.pos * 31) + ((int) (this.id ^ (this.id >>> 32)))) * 31;
        if (this.title != null) {
            hashCode = this.title.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (((hashCode + i2) * 31) + ((int) (this.cover_id ^ (this.cover_id >>> 32)))) * 31;
        if (this.url != null) {
            i = this.url.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Subject{pos=" + this.pos + ", id=" + this.id + ", title='" + this.title + '\'' + ", cover_id=" + this.cover_id + ", url='" + this.url + '\'' + '}';
    }

    public static void fetchSubject(long j, e<SubjectJson> eVar) {
        new a().a(j).a(rx.a.b.a.a()).a((e) eVar);
    }
}
