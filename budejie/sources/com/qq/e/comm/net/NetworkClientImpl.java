package com.qq.e.comm.net;

import com.facebook.common.util.UriUtil;
import com.qq.e.comm.net.NetworkClient.Priority;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.util.GDTLogger;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class NetworkClientImpl implements NetworkClient {
    private static final HttpClient a;
    private static final NetworkClient b = new NetworkClientImpl();
    private final ExecutorService c = new ThreadPoolExecutor(5, 10, 180, TimeUnit.SECONDS, this.d);
    private PriorityBlockingQueue<Runnable> d = new PriorityBlockingQueue(15);

    class NetFutureTask<T> extends FutureTask<T> implements Comparable<NetFutureTask<T>> {
        private final Priority a;

        public NetFutureTask(NetworkClientImpl networkClientImpl, Callable<T> callable, Priority priority) {
            super(callable);
            this.a = priority;
        }

        public int compareTo(NetFutureTask<T> netFutureTask) {
            return netFutureTask == null ? 1 : this.a.value() - netFutureTask.a.value();
        }
    }

    static class TaskCallable implements Callable<Response> {
        private Request a;
        private NetworkCallBack b;

        public TaskCallable(Request request) {
            this(request, null);
        }

        public TaskCallable(Request request, NetworkCallBack networkCallBack) {
            this.a = request;
            this.b = networkCallBack;
        }

        private void a(HttpRequestBase httpRequestBase) {
            for (Entry entry : this.a.getHeaders().entrySet()) {
                httpRequestBase.setHeader((String) entry.getKey(), (String) entry.getValue());
            }
            httpRequestBase.setHeader("User-Agent", "GDTADNetClient-[" + System.getProperty("http.agent") + "]");
            httpRequestBase.addHeader("Accept-Encoding", "gzip");
            HttpParams params = httpRequestBase.getParams();
            if (params == null) {
                params = new BasicHttpParams();
            }
            if (this.a.getConnectionTimeOut() > 0) {
                HttpConnectionParams.setConnectionTimeout(params, this.a.getConnectionTimeOut());
            }
            if (this.a.getSocketTimeOut() > 0) {
                HttpConnectionParams.setSoTimeout(params, this.a.getSocketTimeOut());
            }
            httpRequestBase.setParams(params);
        }

        public Response call() throws Exception {
            Throwable th;
            Response response = null;
            try {
                HttpUriRequest httpPost;
                HttpClient a = NetworkClientImpl.a;
                switch (this.a.getMethod()) {
                    case POST:
                        httpPost = new HttpPost(this.a.getUrlWithParas());
                        a(httpPost);
                        byte[] postData = this.a.getPostData();
                        if (postData != null && postData.length > 0) {
                            httpPost.setEntity(new ByteArrayEntity(postData));
                            break;
                        }
                    case GET:
                        httpPost = new HttpGet(this.a.getUrlWithParas());
                        a(httpPost);
                        break;
                    default:
                        httpPost = null;
                        break;
                }
                response = this.a.initResponse(httpPost, a.execute(httpPost));
                th = null;
            } catch (Exception e) {
                th = e;
            }
            if (th == null) {
                if (this.b != null) {
                    this.b.onResponse(this.a, response);
                }
                if (this.a.isAutoClose()) {
                    response.close();
                }
            } else if (this.b != null) {
                GDTLogger.w("NetworkClientException", th);
                this.b.onException(th);
                if (response != null) {
                    response.close();
                }
            } else {
                throw th;
            }
            return response;
        }
    }

    static {
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(3));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
        HttpProtocolParams.setUserAgent(basicHttpParams, "GDTADNetClient-[" + System.getProperty("http.agent") + "]");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
        a = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }

    private NetworkClientImpl() {
    }

    public static NetworkClient getInstance() {
        return b;
    }

    public Future<Response> submit(Request request) {
        return submit(request, Priority.Mid);
    }

    public Future<Response> submit(Request request, Priority priority) {
        Object netFutureTask = new NetFutureTask(this, new TaskCallable(request), priority);
        this.c.execute(netFutureTask);
        GDTLogger.d("QueueSize:" + this.d.size());
        return netFutureTask;
    }

    public void submit(Request request, NetworkCallBack networkCallBack) {
        submit(request, Priority.Mid, networkCallBack);
    }

    public void submit(Request request, Priority priority, NetworkCallBack networkCallBack) {
        this.c.execute(new NetFutureTask(this, new TaskCallable(request, networkCallBack), priority));
        GDTLogger.d("QueueSize:" + this.d.size());
    }
}
