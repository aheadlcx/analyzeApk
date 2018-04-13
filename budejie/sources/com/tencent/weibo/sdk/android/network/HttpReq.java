package com.tencent.weibo.sdk.android.network;

import android.os.AsyncTask;
import android.util.Log;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.ali.auth.third.login.LoginConstants;
import com.alipay.sdk.util.j;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.zip.GZIPInputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MIME;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class HttpReq extends AsyncTask<Void, Integer, Object> {
    private final String GET = "GET";
    private final String POST = "POST";
    protected HttpCallback mCallBack = null;
    protected String mHost = null;
    protected String mMethod = null;
    protected ReqParam mParam = new ReqParam();
    protected int mPort = HttpConfig.CRM_SERVER_PORT;
    private int mServiceTag = -1;
    protected String mUrl = null;

    public static class UTF8PostMethod extends PostMethod {
        public UTF8PostMethod(String str) {
            super(str);
        }

        public String getRequestCharSet() {
            return "UTF-8";
        }
    }

    protected abstract Object processResponse(InputStream inputStream) throws Exception;

    protected abstract void setReq(HttpMethod httpMethod) throws Exception;

    public void setServiceTag(int i) {
        this.mServiceTag = i;
    }

    public int getServiceTag() {
        return this.mServiceTag;
    }

    protected HttpCallback getCallBack() {
        return this.mCallBack;
    }

    public void setParam(ReqParam reqParam) {
        this.mParam = reqParam;
    }

    public void addParam(String str, String str2) {
        this.mParam.addParam(str, str2);
    }

    public void addParam(String str, Object obj) {
        this.mParam.addParam(str, obj);
    }

    public Object runReq() throws Exception {
        HttpMethod getMethod;
        HttpClient httpClient = new HttpClient();
        if (this.mMethod.equals("GET")) {
            this.mUrl += "?" + this.mParam.toString().substring(0, this.mParam.toString().length() - 1);
            getMethod = new GetMethod(this.mUrl);
        } else if (!this.mMethod.equals("POST")) {
            throw new Exception("unrecognized http method");
        } else if (this.mParam.getmParams().get("pic") != null) {
            return processResponse(picMethod());
        } else {
            getMethod = new UTF8PostMethod(this.mUrl);
        }
        httpClient.getHostConfiguration().setHost(this.mHost, this.mPort, "https");
        getMethod.setRequestHeader(MIME.CONTENT_TYPE, PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);
        setReq(getMethod);
        int executeMethod = httpClient.executeMethod(getMethod);
        Log.d(j.c, new StringBuilder(String.valueOf(executeMethod)).toString());
        if (executeMethod != 200) {
            return null;
        }
        return processResponse(getMethod.getResponseBodyAsStream());
    }

    private InputStream picMethod() {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        String str = "---------------------------7da2137580612";
        str = SocketUtil.CRLF;
        str = "--";
        str = MultipartPostMethod.MULTIPART_FORM_CONTENT_TYPE;
        HttpUriRequest httpPost = new HttpPost(this.mUrl);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String reqParam = this.mParam.toString();
        httpPost.setHeader(MIME.CONTENT_TYPE, new StringBuilder(String.valueOf(str)).append("; boundary=").append("---------------------------7da2137580612").toString());
        if (reqParam != null) {
            try {
                if (!reqParam.equals("")) {
                    for (String str2 : reqParam.split("&")) {
                        if (!(str2 == null || str2.equals("") || str2.indexOf(LoginConstants.EQUAL) <= -1)) {
                            String[] split = str2.split(LoginConstants.EQUAL);
                            str2 = split.length == 2 ? decode(split[1]) : "";
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("-----------------------------7da2137580612\r\n");
                            stringBuilder.append("Content-Disposition:form-data; name=\"" + split[0] + "\"" + SocketUtil.CRLF);
                            stringBuilder.append(SocketUtil.CRLF);
                            stringBuilder.append(str2);
                            stringBuilder.append(SocketUtil.CRLF);
                            byteArrayOutputStream.write(stringBuilder.toString().getBytes("utf-8"));
                        }
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("-----------------------------7da2137580612\r\n");
                    stringBuilder2.append("Content-Disposition:form-data; name=\"pic\"; filename=\"123456.jpg\"\r\n");
                    stringBuilder2.append("Content-Type:image/jpeg\r\n\r\n");
                    byteArrayOutputStream.write(stringBuilder2.toString().getBytes("utf-8"));
                    char[] toCharArray = ((String) this.mParam.getmParams().get("pic")).toCharArray();
                    byte[] bArr = new byte[toCharArray.length];
                    for (int i = 0; i < toCharArray.length; i++) {
                        bArr[i] = (byte) toCharArray[i];
                    }
                    byteArrayOutputStream.write(bArr);
                    byteArrayOutputStream.write("---------------------------7da2137580612\r\n".getBytes("utf-8"));
                }
            } catch (IOException e) {
                return null;
            }
        }
        byteArrayOutputStream.write("-----------------------------7da2137580612--\r\n".getBytes("utf-8"));
        httpPost.setEntity(new ByteArrayEntity(byteArrayOutputStream.toByteArray()));
        byteArrayOutputStream.close();
        HttpResponse execute = defaultHttpClient.execute(httpPost);
        if (execute.getStatusLine().getStatusCode() == 200) {
            return readHttpResponse(execute);
        }
        readHttpResponse(execute);
        return null;
    }

    public static String decode(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static InputStream readHttpResponse(HttpResponse httpResponse) {
        InputStream inputStream = null;
        try {
            inputStream = httpResponse.getEntity().getContent();
            try {
                Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
                if (firstHeader == null || firstHeader.getValue().toLowerCase().indexOf("gzip") <= -1) {
                    return inputStream;
                }
                return new GZIPInputStream(inputStream);
            } catch (IllegalStateException e) {
                return inputStream;
            } catch (IOException e2) {
                return inputStream;
            }
        } catch (IllegalStateException e3) {
            return inputStream;
        } catch (IOException e4) {
            return inputStream;
        }
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Object doInBackground(Void... voidArr) {
        try {
            return runReq();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(Object obj) {
        if (this.mCallBack != null) {
            this.mCallBack.onResult(obj);
        }
        HttpService.getInstance().onReqFinish(this);
    }

    protected void onCancelled() {
        if (this.mCallBack != null) {
            this.mCallBack.onResult(null);
        }
        HttpService.getInstance().onReqFinish(this);
    }
}
