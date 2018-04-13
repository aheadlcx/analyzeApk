package mtopsdk.mtop.domain;

import java.io.Serializable;
import java.util.Map;
import mtopsdk.common.util.l;

public class MtopRequest implements Serializable, b {
    private static final long serialVersionUID = -439476282014493612L;
    private String apiName;
    private String data = "{}";
    public Map dataParams;
    private boolean needEcode;
    private boolean needSession;
    private String version;

    public String getApiName() {
        return this.apiName;
    }

    public String getData() {
        return this.data;
    }

    public String getKey() {
        return (l.b(this.apiName) || l.b(this.version)) ? null : l.a(this.apiName, this.version);
    }

    public String getVersion() {
        return this.version;
    }

    public boolean isLegalRequest() {
        return l.a(this.apiName) && l.a(this.version) && l.a(this.data);
    }

    public boolean isNeedEcode() {
        return this.needEcode;
    }

    public boolean isNeedSession() {
        return this.needSession;
    }

    public void setApiName(String str) {
        this.apiName = str;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setNeedEcode(boolean z) {
        this.needEcode = z;
    }

    public void setNeedSession(boolean z) {
        this.needSession = z;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MtopRequest [");
        stringBuilder.append(" apiName=").append(this.apiName);
        stringBuilder.append(", version=").append(this.version);
        stringBuilder.append(", data=").append(this.data);
        stringBuilder.append(", needEcode=").append(this.needEcode);
        stringBuilder.append(", needSession=").append(this.needSession);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
