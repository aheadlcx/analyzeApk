package okhttp3.internal.http;

import cz.msebera.android.httpclient.HttpHeaders;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request$Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http2.ConnectionShutdownException;

public final class RetryAndFollowUpInterceptor implements Interceptor {
    private final OkHttpClient a;
    private final boolean b;
    private StreamAllocation c;
    private Object d;
    private volatile boolean e;

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient, boolean z) {
        this.a = okHttpClient;
        this.b = z;
    }

    public void cancel() {
        this.e = true;
        StreamAllocation streamAllocation = this.c;
        if (streamAllocation != null) {
            streamAllocation.cancel();
        }
    }

    public boolean isCanceled() {
        return this.e;
    }

    public void setCallStackTrace(Object obj) {
        this.d = obj;
    }

    public StreamAllocation streamAllocation() {
        return this.c;
    }

    public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
        boolean z;
        Request request = interceptor$Chain.request();
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) interceptor$Chain;
        Call call = realInterceptorChain.call();
        EventListener eventListener = realInterceptorChain.eventListener();
        this.c = new StreamAllocation(this.a.connectionPool(), a(request.url()), call, eventListener, this.d);
        Response response = null;
        int i = 0;
        Request request2 = request;
        while (!this.e) {
            try {
                Response proceed = realInterceptorChain.proceed(request2, this.c, null, null);
                if (response != null) {
                    proceed = proceed.newBuilder().priorResponse(response.newBuilder().body(null).build()).build();
                }
                Request a = a(proceed);
                if (a == null) {
                    if (!this.b) {
                        this.c.release();
                    }
                    return proceed;
                }
                Util.closeQuietly(proceed.body());
                int i2 = i + 1;
                if (i2 > 20) {
                    this.c.release();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                } else if (a.body() instanceof UnrepeatableRequestBody) {
                    this.c.release();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", proceed.code());
                } else {
                    if (!a(proceed, a.url())) {
                        this.c.release();
                        this.c = new StreamAllocation(this.a.connectionPool(), a(a.url()), call, eventListener, this.d);
                    } else if (this.c.codec() != null) {
                        throw new IllegalStateException("Closing the body of " + proceed + " didn't close its backing stream. Bad interceptor?");
                    }
                    response = proceed;
                    i = i2;
                    request2 = a;
                }
            } catch (RouteException e) {
                if (!a(e.getLastConnectException(), false, request2)) {
                    throw e.getLastConnectException();
                }
            } catch (IOException e2) {
                if (e2 instanceof ConnectionShutdownException) {
                    z = false;
                } else {
                    z = true;
                }
                if (!a(e2, z, request2)) {
                    throw e2;
                }
            } catch (Throwable th) {
                this.c.streamFailed(null);
                this.c.release();
            }
        }
        this.c.release();
        throw new IOException("Canceled");
    }

    private Address a(HttpUrl httpUrl) {
        SSLSocketFactory sslSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner = null;
        if (httpUrl.isHttps()) {
            sslSocketFactory = this.a.sslSocketFactory();
            hostnameVerifier = this.a.hostnameVerifier();
            certificatePinner = this.a.certificatePinner();
        } else {
            hostnameVerifier = null;
            sslSocketFactory = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.a.dns(), this.a.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.a.proxyAuthenticator(), this.a.proxy(), this.a.protocols(), this.a.connectionSpecs(), this.a.proxySelector());
    }

    private boolean a(IOException iOException, boolean z, Request request) {
        this.c.streamFailed(iOException);
        if (!this.a.retryOnConnectionFailure()) {
            return false;
        }
        if ((!z || !(request.body() instanceof UnrepeatableRequestBody)) && a(iOException, z) && this.c.hasMoreRoutes()) {
            return true;
        }
        return false;
    }

    private boolean a(IOException iOException, boolean z) {
        boolean z2 = true;
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z) {
                z2 = false;
            }
            return z2;
        } else if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        } else {
            return true;
        }
    }

    private Request a(Response response) throws IOException {
        RequestBody requestBody = null;
        if (response == null) {
            throw new IllegalStateException();
        }
        Route route;
        Connection connection = this.c.connection();
        if (connection != null) {
            route = connection.route();
        } else {
            route = null;
        }
        int code = response.code();
        String method = response.request().method();
        switch (code) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case 307:
            case 308:
                if (!(method.equals("GET") || method.equals("HEAD"))) {
                    return null;
                }
            case 401:
                return this.a.authenticator().authenticate(route, response);
            case 407:
                Proxy proxy;
                if (route != null) {
                    proxy = route.proxy();
                } else {
                    proxy = this.a.proxy();
                }
                if (proxy.type() == Type.HTTP) {
                    return this.a.proxyAuthenticator().authenticate(route, response);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case 408:
                if (!this.a.retryOnConnectionFailure() || (response.request().body() instanceof UnrepeatableRequestBody)) {
                    return null;
                }
                if (response.priorResponse() == null || response.priorResponse().code() != 408) {
                    return response.request();
                }
                return null;
            default:
                return null;
        }
        if (!this.a.followRedirects()) {
            return null;
        }
        String header = response.header(HttpHeaders.LOCATION);
        if (header == null) {
            return null;
        }
        HttpUrl resolve = response.request().url().resolve(header);
        if (resolve == null) {
            return null;
        }
        if (!resolve.scheme().equals(response.request().url().scheme()) && !this.a.followSslRedirects()) {
            return null;
        }
        Request$Builder newBuilder = response.request().newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            boolean redirectsWithBody = HttpMethod.redirectsWithBody(method);
            if (HttpMethod.redirectsToGet(method)) {
                newBuilder.method("GET", null);
            } else {
                if (redirectsWithBody) {
                    requestBody = response.request().body();
                }
                newBuilder.method(method, requestBody);
            }
            if (!redirectsWithBody) {
                newBuilder.removeHeader("Transfer-Encoding");
                newBuilder.removeHeader("Content-Length");
                newBuilder.removeHeader("Content-Type");
            }
        }
        if (!a(response, resolve)) {
            newBuilder.removeHeader("Authorization");
        }
        return newBuilder.url(resolve).build();
    }

    private boolean a(Response response, HttpUrl httpUrl) {
        HttpUrl url = response.request().url();
        return url.host().equals(httpUrl.host()) && url.port() == httpUrl.port() && url.scheme().equals(httpUrl.scheme());
    }
}
