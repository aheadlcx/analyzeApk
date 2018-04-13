package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.HttpServerConnection;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.MethodNotSupportedException;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.UnsupportedHttpVersionException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import cz.msebera.android.httpclient.impl.DefaultHttpResponseFactory;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EncodingUtils;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.IOException;

@Immutable
public class HttpService {
    private volatile HttpParams a;
    private volatile HttpProcessor b;
    private volatile HttpRequestHandlerMapper c;
    private volatile ConnectionReuseStrategy d;
    private volatile HttpResponseFactory e;
    private volatile HttpExpectationVerifier f;

    @Deprecated
    private static class a implements HttpRequestHandlerMapper {
        private final HttpRequestHandlerResolver a;

        public a(HttpRequestHandlerResolver httpRequestHandlerResolver) {
            this.a = httpRequestHandlerResolver;
        }

        public HttpRequestHandler lookup(HttpRequest httpRequest) {
            return this.a.lookup(httpRequest.getRequestLine().getUri());
        }
    }

    @Deprecated
    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory, HttpRequestHandlerResolver httpRequestHandlerResolver, HttpExpectationVerifier httpExpectationVerifier, HttpParams httpParams) {
        this(httpProcessor, connectionReuseStrategy, httpResponseFactory, new a(httpRequestHandlerResolver), httpExpectationVerifier);
        this.a = httpParams;
    }

    @Deprecated
    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory, HttpRequestHandlerResolver httpRequestHandlerResolver, HttpParams httpParams) {
        this(httpProcessor, connectionReuseStrategy, httpResponseFactory, new a(httpRequestHandlerResolver), null);
        this.a = httpParams;
    }

    @Deprecated
    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        setHttpProcessor(httpProcessor);
        setConnReuseStrategy(connectionReuseStrategy);
        setResponseFactory(httpResponseFactory);
    }

    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory, HttpRequestHandlerMapper httpRequestHandlerMapper, HttpExpectationVerifier httpExpectationVerifier) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.b = (HttpProcessor) Args.notNull(httpProcessor, "HTTP processor");
        if (connectionReuseStrategy == null) {
            connectionReuseStrategy = DefaultConnectionReuseStrategy.INSTANCE;
        }
        this.d = connectionReuseStrategy;
        if (httpResponseFactory == null) {
            httpResponseFactory = DefaultHttpResponseFactory.INSTANCE;
        }
        this.e = httpResponseFactory;
        this.c = httpRequestHandlerMapper;
        this.f = httpExpectationVerifier;
    }

    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory, HttpRequestHandlerMapper httpRequestHandlerMapper) {
        this(httpProcessor, connectionReuseStrategy, httpResponseFactory, httpRequestHandlerMapper, null);
    }

    public HttpService(HttpProcessor httpProcessor, HttpRequestHandlerMapper httpRequestHandlerMapper) {
        this(httpProcessor, null, null, httpRequestHandlerMapper, null);
    }

    @Deprecated
    public void setHttpProcessor(HttpProcessor httpProcessor) {
        Args.notNull(httpProcessor, "HTTP processor");
        this.b = httpProcessor;
    }

    @Deprecated
    public void setConnReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        this.d = connectionReuseStrategy;
    }

    @Deprecated
    public void setResponseFactory(HttpResponseFactory httpResponseFactory) {
        Args.notNull(httpResponseFactory, "Response factory");
        this.e = httpResponseFactory;
    }

    @Deprecated
    public void setParams(HttpParams httpParams) {
        this.a = httpParams;
    }

    @Deprecated
    public void setHandlerResolver(HttpRequestHandlerResolver httpRequestHandlerResolver) {
        this.c = new a(httpRequestHandlerResolver);
    }

    @Deprecated
    public void setExpectationVerifier(HttpExpectationVerifier httpExpectationVerifier) {
        this.f = httpExpectationVerifier;
    }

    @Deprecated
    public HttpParams getParams() {
        return this.a;
    }

    public void handleRequest(HttpServerConnection httpServerConnection, HttpContext httpContext) throws IOException, HttpException {
        HttpRequest receiveRequestHeader;
        HttpResponse newHttpResponse;
        HttpException httpException;
        HttpResponse httpResponse = null;
        httpContext.setAttribute("http.connection", httpServerConnection);
        try {
            receiveRequestHeader = httpServerConnection.receiveRequestHeader();
            try {
                if (receiveRequestHeader instanceof HttpEntityEnclosingRequest) {
                    if (((HttpEntityEnclosingRequest) receiveRequestHeader).expectContinue()) {
                        newHttpResponse = this.e.newHttpResponse(HttpVersion.HTTP_1_1, 100, httpContext);
                        if (this.f != null) {
                            try {
                                this.f.verify(receiveRequestHeader, newHttpResponse, httpContext);
                            } catch (HttpException e) {
                                HttpResponse newHttpResponse2 = this.e.newHttpResponse(HttpVersion.HTTP_1_0, 500, httpContext);
                                a(e, newHttpResponse2);
                                newHttpResponse = newHttpResponse2;
                            }
                        }
                        if (newHttpResponse.getStatusLine().getStatusCode() < 200) {
                            httpServerConnection.sendResponseHeader(newHttpResponse);
                            httpServerConnection.flush();
                            httpServerConnection.receiveRequestEntity((HttpEntityEnclosingRequest) receiveRequestHeader);
                        } else {
                            httpResponse = newHttpResponse;
                        }
                    } else {
                        httpServerConnection.receiveRequestEntity((HttpEntityEnclosingRequest) receiveRequestHeader);
                    }
                }
                httpContext.setAttribute("http.request", receiveRequestHeader);
                if (httpResponse == null) {
                    httpResponse = this.e.newHttpResponse(HttpVersion.HTTP_1_1, 200, httpContext);
                    this.b.process(receiveRequestHeader, httpContext);
                    a(receiveRequestHeader, httpResponse, httpContext);
                }
                if (receiveRequestHeader instanceof HttpEntityEnclosingRequest) {
                    EntityUtils.consume(((HttpEntityEnclosingRequest) receiveRequestHeader).getEntity());
                }
                newHttpResponse = httpResponse;
            } catch (HttpException e2) {
                httpException = e2;
                newHttpResponse = this.e.newHttpResponse(HttpVersion.HTTP_1_0, 500, httpContext);
                a(httpException, newHttpResponse);
                httpContext.setAttribute("http.response", newHttpResponse);
                this.b.process(newHttpResponse, httpContext);
                httpServerConnection.sendResponseHeader(newHttpResponse);
                if (a(receiveRequestHeader, newHttpResponse)) {
                    httpServerConnection.sendResponseEntity(newHttpResponse);
                }
                httpServerConnection.flush();
                if (this.d.keepAlive(newHttpResponse, httpContext)) {
                    httpServerConnection.close();
                }
            }
        } catch (HttpException e22) {
            receiveRequestHeader = null;
            httpException = e22;
            newHttpResponse = this.e.newHttpResponse(HttpVersion.HTTP_1_0, 500, httpContext);
            a(httpException, newHttpResponse);
            httpContext.setAttribute("http.response", newHttpResponse);
            this.b.process(newHttpResponse, httpContext);
            httpServerConnection.sendResponseHeader(newHttpResponse);
            if (a(receiveRequestHeader, newHttpResponse)) {
                httpServerConnection.sendResponseEntity(newHttpResponse);
            }
            httpServerConnection.flush();
            if (this.d.keepAlive(newHttpResponse, httpContext)) {
                httpServerConnection.close();
            }
        }
        httpContext.setAttribute("http.response", newHttpResponse);
        this.b.process(newHttpResponse, httpContext);
        httpServerConnection.sendResponseHeader(newHttpResponse);
        if (a(receiveRequestHeader, newHttpResponse)) {
            httpServerConnection.sendResponseEntity(newHttpResponse);
        }
        httpServerConnection.flush();
        if (this.d.keepAlive(newHttpResponse, httpContext)) {
            httpServerConnection.close();
        }
    }

    private boolean a(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpRequest != null && "HEAD".equalsIgnoreCase(httpRequest.getRequestLine().getMethod())) {
            return false;
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode < 200 || statusCode == HttpStatus.SC_NO_CONTENT || statusCode == 304 || statusCode == HttpStatus.SC_RESET_CONTENT) {
            return false;
        }
        return true;
    }

    protected void a(HttpException httpException, HttpResponse httpResponse) {
        if (httpException instanceof MethodNotSupportedException) {
            httpResponse.setStatusCode(501);
        } else if (httpException instanceof UnsupportedHttpVersionException) {
            httpResponse.setStatusCode(505);
        } else if (httpException instanceof ProtocolException) {
            httpResponse.setStatusCode(400);
        } else {
            httpResponse.setStatusCode(500);
        }
        String message = httpException.getMessage();
        if (message == null) {
            message = httpException.toString();
        }
        HttpEntity byteArrayEntity = new ByteArrayEntity(EncodingUtils.getAsciiBytes(message));
        byteArrayEntity.setContentType("text/plain; charset=US-ASCII");
        httpResponse.setEntity(byteArrayEntity);
    }

    protected void a(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        HttpRequestHandler httpRequestHandler = null;
        if (this.c != null) {
            httpRequestHandler = this.c.lookup(httpRequest);
        }
        if (httpRequestHandler != null) {
            httpRequestHandler.handle(httpRequest, httpResponse, httpContext);
        } else {
            httpResponse.setStatusCode(501);
        }
    }
}
