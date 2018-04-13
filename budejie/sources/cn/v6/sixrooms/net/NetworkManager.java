package cn.v6.sixrooms.net;

import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import com.facebook.common.util.UriUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class NetworkManager {
    public static final int CONNECT_TIMEOUT = 15000;
    public static final int MAX_ROUTE_CONNECTIONS = 100;
    public static final int MAX_TOTAL_CONNECTIONS = 100;
    public static final int READ_TIMEOUT = 15000;
    public static final int WAIT_TIMEOUT = 15000;
    private static volatile NetworkManager c;
    private HttpClient a;
    private HttpResponse b;

    private NetworkManager() {
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
        ConnManagerParams.setTimeout(basicHttpParams, 15000);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(100));
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 15000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 15000);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        this.a = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        this.a.getParams().setParameter(org.apache.commons.httpclient.params.HttpConnectionParams.CONNECTION_TIMEOUT, Integer.valueOf(15000));
        this.a.getParams().setParameter("http.socket.timeout", Integer.valueOf(15000));
    }

    public static synchronized NetworkManager getInstance() {
        NetworkManager networkManager;
        synchronized (NetworkManager.class) {
            if (c == null) {
                synchronized (NetworkServiceSingleton.class) {
                    if (c == null) {
                        c = new NetworkManager();
                    }
                }
            }
            networkManager = c;
        }
        return networkManager;
    }

    public InputStream sendPostRequest(String str, List<NameValuePair> list) {
        HttpUriRequest httpPost = new HttpPost(str);
        LogUtils.i("httpPost", "url:" + str);
        LogUtils.i("httpPost", "list:" + list);
        httpPost.setHeader("User-Agent", SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            this.b = this.a.execute(httpPost);
            if (this.b.getStatusLine().getStatusCode() == 200) {
                return this.b.getEntity().getContent();
            }
            LogUtils.e("NetworkManager", "url----" + str);
            LogUtils.e("NetworkManager", "code----" + this.b.getStatusLine().getStatusCode());
            LogUtils.e("NetworkManager", "respone----" + this.b.getEntity().getContent());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public InputStream sendPostRequestUploadFile(String str, MultipartEntity multipartEntity) {
        HttpUriRequest httpPost = new HttpPost(str);
        LogUtils.i("httpPost", "url:" + str);
        LogUtils.i("httpPost", "me:" + multipartEntity);
        httpPost.setHeader("User-Agent", SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        try {
            httpPost.setEntity(multipartEntity);
            this.b = this.a.execute(httpPost);
            if (this.b.getStatusLine().getStatusCode() == 200) {
                return this.b.getEntity().getContent();
            }
            LogUtils.e(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, this.b.getStatusLine().getStatusCode());
            LogUtils.e("responseContent", this.b.getEntity().getContent());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public InputStream sendPostRequest(String str, String str2) {
        InputStream inputStream = null;
        HttpUriRequest httpPost = new HttpPost(str);
        httpPost.setHeader("User-Agent", SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        try {
            httpPost.setEntity(new StringEntity(str2, "UTF-8"));
            this.b = this.a.execute(httpPost);
            if (this.b.getStatusLine().getStatusCode() == 200) {
                inputStream = this.b.getEntity().getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public InputStream sendGetRequest(String str) {
        InputStream inputStream = null;
        HttpUriRequest httpGet = new HttpGet(str);
        LogUtils.i("httpGet", "url:" + str);
        httpGet.setHeader("User-Agent", SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        LogUtils.i("NetworkManager", "\tSocketUtil.encryptContent(AppInfoUtils.getAppInfo()):" + SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        LogUtils.i("NetworkManager", AppInfoUtils.getAppInfo());
        try {
            this.b = this.a.execute(httpGet);
            if (this.b.getStatusLine().getStatusCode() == 200) {
                inputStream = this.b.getEntity().getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public List<Object> sendPostRequestNeedHeaders(String str, List<NameValuePair> list) {
        int i = 0;
        HttpUriRequest httpPost = new HttpPost(str);
        httpPost.getParams().setParameter("http.protocol.handle-redirects", Boolean.valueOf(false));
        LogUtils.i("httpPost", "url:" + str);
        LogUtils.i("httpPost", "list:" + list);
        httpPost.setHeader("User-Agent", SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            this.b = this.a.execute(httpPost);
            if (this.b.getStatusLine().getStatusCode() == 302) {
                InputStream content = this.b.getEntity().getContent();
                Map hashMap = new HashMap();
                Header[] allHeaders = this.b.getAllHeaders();
                LogUtils.d("NetworkManager", "allHeaders长度: " + allHeaders.length);
                int length = allHeaders.length;
                while (i < length) {
                    Header header = allHeaders[i];
                    String name = header.getName();
                    String value = header.getValue();
                    LogUtils.d("NetworkManager", name + ":" + value);
                    hashMap.put(name, value);
                    i++;
                }
                List<Object> arrayList = new ArrayList();
                arrayList.add(content);
                arrayList.add(hashMap);
                return arrayList;
            }
            LogUtils.e(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, this.b.getStatusLine().getStatusCode());
            LogUtils.e("responseContent", this.b.getEntity().getContent());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
