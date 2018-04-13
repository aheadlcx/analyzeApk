package mtopsdk.mtop.antiattack;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.io.Serializable;
import java.util.Map;
import mtopsdk.common.util.l;
import mtopsdk.mtop.domain.b;

public class CheckCodeDO implements Serializable, b {
    public static final String CHECKCODE_CHECK_URL_KEY = "check";
    public static final String CHECKCODE_IMAGE_URL_KEY = "image";
    public static final String CHECKCODE_USER_INPUT_KEY = "code";
    private static final long serialVersionUID = 1095959954944984636L;
    public Map checkParams;
    public String checkPath;
    public String imageUrl;

    public enum CheckCodeFieldEnum {
        APPLY("app"),
        BACK("back"),
        HOW("how"),
        IP("ip"),
        RAND("rand"),
        SESSION("session"),
        V(IXAdRequestInfo.V),
        W(IXAdRequestInfo.WIDTH),
        NATIVE("native");
        
        private String field;

        private CheckCodeFieldEnum(String str) {
            this.field = str;
        }

        public final String getField() {
            return this.field;
        }
    }

    public boolean isValid() {
        return (l.b(this.imageUrl) || l.b(this.checkPath) || this.checkParams == null) ? false : true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append("CheckCodeDO [imageUrl=").append(this.imageUrl);
        stringBuilder.append(", checkPath=").append(this.checkPath);
        stringBuilder.append(", checkParams=").append(this.checkParams);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
