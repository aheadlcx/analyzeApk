package org.mozilla.javascript.commonjs.module.provider;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.Serializable;
import java.util.StringTokenizer;

public final class ParsedContentType implements Serializable {
    private static final long serialVersionUID = 1;
    private final String contentType;
    private final String encoding;

    public ParsedContentType(String str) {
        String trim;
        String str2 = null;
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, VoiceWakeuperAidl.PARAMS_SEPARATE);
            if (stringTokenizer.hasMoreTokens()) {
                trim = stringTokenizer.nextToken().trim();
                while (stringTokenizer.hasMoreTokens()) {
                    String trim2 = stringTokenizer.nextToken().trim();
                    if (trim2.startsWith("charset=")) {
                        str2 = trim2.substring(8).trim();
                        int length = str2.length();
                        if (length > 0) {
                            if (str2.charAt(0) == '\"') {
                                str2 = str2.substring(1);
                            }
                            if (str2.charAt(length - 1) == '\"') {
                                str2 = str2.substring(0, length - 1);
                            }
                        }
                        this.contentType = trim;
                        this.encoding = str2;
                    }
                }
                this.contentType = trim;
                this.encoding = str2;
            }
        }
        trim = null;
        this.contentType = trim;
        this.encoding = str2;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getEncoding() {
        return this.encoding;
    }
}
