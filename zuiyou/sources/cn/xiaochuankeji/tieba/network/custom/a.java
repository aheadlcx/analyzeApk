package cn.xiaochuankeji.tieba.network.custom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class a implements HostnameVerifier {
    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
