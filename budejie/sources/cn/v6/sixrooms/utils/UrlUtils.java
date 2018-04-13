package cn.v6.sixrooms.utils;

import com.ali.auth.third.login.LoginConstants;
import java.util.List;
import org.apache.http.NameValuePair;

public class UrlUtils {
    public static String getUrl(String str, List<NameValuePair> list) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (!(list == null || list.size() == 0)) {
            stringBuffer.append("?");
            for (NameValuePair nameValuePair : list) {
                String name = nameValuePair.getName();
                stringBuffer.append(name).append(LoginConstants.EQUAL).append(nameValuePair.getValue()).append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static String getPadapiUrl(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append("?padapi=").append(str2);
        return stringBuffer.toString();
    }
}
