package qsbk.app.thirdparty.net;

import android.text.TextUtils;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpDelete;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.params.ConnRoutePNames;
import cz.msebera.android.httpclient.conn.scheme.PlainSocketFactory;
import cz.msebera.android.httpclient.conn.scheme.Scheme;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.conn.scheme.SocketFactory;
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.impl.conn.tsccm.ThreadSafeClientConnManager;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.ThirdPartyParameters;
import qsbk.app.thirdparty.Utility.Utility;
import qsbk.app.utils.LogUtil;

public class HttpManager {
    public static final String HTTPMETHOD_GET = "GET";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String a = a();
    private static final String b = ("--" + a);
    private static final String c = ("--" + a + "--");

    private static class a extends SSLSocketFactory {
        SSLContext a = SSLContext.getInstance("TLS");

        public a(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            c cVar = new c(this);
            this.a.init(null, new TrustManager[]{cVar}, null);
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }
    }

    public static String openUrl(String str, String str2, ThirdPartyParameters thirdPartyParameters) throws ThirdPartyException {
        String str3 = "";
        try {
            HttpClient b = b();
            HttpUriRequest httpUriRequest = null;
            b.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, NetStateManager.getAPN());
            if (str2.equals("GET")) {
                httpUriRequest = new HttpGet(str + "?" + Utility.encodeUrl(thirdPartyParameters));
            } else if (str2.equals("POST")) {
                LogUtil.e("url =" + str);
                httpUriRequest = new HttpPost(str);
                String value = thirdPartyParameters.getValue("content-type");
                LogUtil.e("_contentType + " + value);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (value != null) {
                    thirdPartyParameters.remove("content-type");
                    httpUriRequest.setHeader("Content-Type", value);
                } else {
                    httpUriRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
                }
                value = Utility.encodeParameters(thirdPartyParameters);
                LogUtil.e("postParam:" + value);
                byteArrayOutputStream.write(value.getBytes("UTF-8"));
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                httpUriRequest.setEntity(new ByteArrayEntity(toByteArray));
            } else if (str2.equals("DELETE")) {
                httpUriRequest = new HttpDelete(str);
            }
            HttpResponse execute = b.execute(httpUriRequest);
            LogUtil.e("response : " + execute.toString());
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return a(execute);
            }
            throw new ThirdPartyException(a(execute), statusCode);
        } catch (Exception e) {
            throw new ThirdPartyException(e);
        }
    }

    public static String openUrl(String str, String str2, ThirdPartyParameters thirdPartyParameters, byte[] bArr) throws ThirdPartyException {
        String str3 = "";
        try {
            HttpClient b = b();
            HttpUriRequest httpUriRequest = null;
            b.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, NetStateManager.getAPN());
            if (str2.equals("GET")) {
                httpUriRequest = new HttpGet(str + "?" + Utility.encodeUrl(thirdPartyParameters));
            } else if (str2.equals("POST")) {
                LogUtil.e("url =" + str);
                httpUriRequest = new HttpPost(str);
                String value = thirdPartyParameters.getValue("content-type");
                LogUtil.e("_contentType + " + value);
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (bArr.length != 0) {
                    a(byteArrayOutputStream, thirdPartyParameters);
                    httpUriRequest.setHeader("Content-Type", "multipart/form-data; boundary=" + a);
                    a(byteArrayOutputStream, bArr);
                } else {
                    if (value != null) {
                        thirdPartyParameters.remove("content-type");
                        httpUriRequest.setHeader("Content-Type", value);
                    } else {
                        httpUriRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    }
                    byteArrayOutputStream.write(Utility.encodeParameters(thirdPartyParameters).getBytes("UTF-8"));
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                httpUriRequest.setEntity(new ByteArrayEntity(toByteArray));
            } else if (str2.equals("DELETE")) {
                httpUriRequest = new HttpDelete(str);
            }
            HttpResponse execute = b.execute(httpUriRequest);
            LogUtil.e("response : " + execute.toString());
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return a(execute);
            }
            throw new ThirdPartyException(a(execute), statusCode);
        } catch (Exception e) {
            throw new ThirdPartyException(e);
        }
    }

    private static void a(OutputStream outputStream, byte[] bArr) throws ThirdPartyException {
        if (bArr.length != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b).append("\r\n");
            stringBuilder.append("Content-Disposition: form-data; name=\"pic\"; filename=\"").append("news_image").append("\"\r\n");
            stringBuilder.append("Content-Type: ").append("image/png").append("\r\n\r\n");
            try {
                outputStream.write(stringBuilder.toString().getBytes());
                outputStream.write(bArr, 0, bArr.length);
                outputStream.write("\r\n".getBytes());
                outputStream.write(("\r\n" + c).getBytes());
            } catch (Exception e) {
                throw new ThirdPartyException(e);
            }
        }
    }

    public static String openUrl(String str, String str2, ThirdPartyParameters thirdPartyParameters, String str3) throws ThirdPartyException {
        String str4 = "";
        try {
            HttpClient b = b();
            HttpUriRequest httpUriRequest = null;
            b.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, NetStateManager.getAPN());
            if (str2.equals("GET")) {
                httpUriRequest = new HttpGet(str + "?" + Utility.encodeUrl(thirdPartyParameters));
            } else if (str2.equals("POST")) {
                httpUriRequest = new HttpPost(str);
                String value = thirdPartyParameters.getValue("content-type");
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (TextUtils.isEmpty(str3)) {
                    if (value != null) {
                        thirdPartyParameters.remove("content-type");
                        httpUriRequest.setHeader("Content-Type", value);
                    } else {
                        httpUriRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    }
                    byteArrayOutputStream.write(Utility.encodeParameters(thirdPartyParameters).getBytes("UTF-8"));
                } else {
                    a(byteArrayOutputStream, thirdPartyParameters);
                    httpUriRequest.setHeader("Content-Type", "multipart/form-data; boundary=" + a);
                    a(byteArrayOutputStream, str3);
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                httpUriRequest.setEntity(new ByteArrayEntity(toByteArray));
            } else if (str2.equals("DELETE")) {
                httpUriRequest = new HttpDelete(str);
            }
            HttpResponse execute = b.execute(httpUriRequest);
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return a(execute);
            }
            throw new ThirdPartyException(a(execute), statusCode);
        } catch (Exception e) {
            throw new ThirdPartyException(e);
        }
    }

    private static HttpClient b() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory aVar = new a(instance);
            aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", aVar, 443));
            ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 5000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
            return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    private static void a(OutputStream outputStream, ThirdPartyParameters thirdPartyParameters) throws ThirdPartyException {
        String str = "";
        int i = 0;
        while (i < thirdPartyParameters.size()) {
            String key = thirdPartyParameters.getKey(i);
            StringBuilder stringBuilder = new StringBuilder(10);
            stringBuilder.setLength(0);
            stringBuilder.append(b).append("\r\n");
            stringBuilder.append("content-disposition: form-data; name=\"").append(key).append("\"\r\n\r\n");
            stringBuilder.append(thirdPartyParameters.getValue(key)).append("\r\n");
            try {
                outputStream.write(stringBuilder.toString().getBytes());
                i++;
            } catch (Exception e) {
                throw new ThirdPartyException(e);
            }
        }
    }

    private static void a(OutputStream outputStream, String str) throws ThirdPartyException {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        if (str != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b).append("\r\n");
            stringBuilder.append("Content-Disposition: form-data; name=\"pic\"; filename=\"").append("news_image").append("\"\r\n");
            stringBuilder.append("Content-Type: ").append("image/png").append("\r\n\r\n");
            try {
                outputStream.write(stringBuilder.toString().getBytes());
                fileInputStream = new FileInputStream(str);
                try {
                    byte[] bArr = new byte[51200];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        outputStream.write(bArr, 0, read);
                    }
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(("\r\n" + c).getBytes());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e2) {
                            throw new ThirdPartyException(e2);
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                }
            } catch (IOException e4) {
                e2 = e4;
                fileInputStream = null;
                try {
                    throw new ThirdPartyException(e2);
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e22) {
                            throw new ThirdPartyException(e22);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
    }

    private static String a(HttpResponse httpResponse) {
        try {
            InputStream inputStream;
            InputStream content = httpResponse.getEntity().getContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
            if (firstHeader == null || firstHeader.getValue().toLowerCase().indexOf("gzip") <= -1) {
                inputStream = content;
            } else {
                inputStream = new GZIPInputStream(content);
            }
            byte[] bArr = new byte[512];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return new String(byteArrayOutputStream.toByteArray());
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IllegalStateException e) {
            return "";
        } catch (IOException e2) {
            return "";
        }
    }

    static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < 12; i++) {
            long currentTimeMillis = System.currentTimeMillis() + ((long) i);
            if (currentTimeMillis % 3 == 0) {
                stringBuffer.append(((char) ((int) currentTimeMillis)) % 9);
            } else if (currentTimeMillis % 3 == 1) {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 65)));
            } else {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 97)));
            }
        }
        return stringBuffer.toString();
    }
}
