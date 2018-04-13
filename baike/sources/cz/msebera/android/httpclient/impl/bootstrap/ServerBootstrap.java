package cz.msebera.android.httpclient.impl.bootstrap;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.ExceptionLogger;
import cz.msebera.android.httpclient.HttpConnectionFactory;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.config.ConnectionConfig;
import cz.msebera.android.httpclient.config.SocketConfig;
import cz.msebera.android.httpclient.impl.DefaultBHttpServerConnection;
import cz.msebera.android.httpclient.impl.DefaultBHttpServerConnectionFactory;
import cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import cz.msebera.android.httpclient.impl.DefaultHttpResponseFactory;
import cz.msebera.android.httpclient.protocol.HttpExpectationVerifier;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpProcessorBuilder;
import cz.msebera.android.httpclient.protocol.HttpRequestHandler;
import cz.msebera.android.httpclient.protocol.HttpRequestHandlerMapper;
import cz.msebera.android.httpclient.protocol.HttpService;
import cz.msebera.android.httpclient.protocol.ResponseConnControl;
import cz.msebera.android.httpclient.protocol.ResponseContent;
import cz.msebera.android.httpclient.protocol.ResponseDate;
import cz.msebera.android.httpclient.protocol.ResponseServer;
import cz.msebera.android.httpclient.protocol.UriHttpRequestHandlerMapper;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;

public class ServerBootstrap {
    private int a;
    private InetAddress b;
    private SocketConfig c;
    private ConnectionConfig d;
    private LinkedList<HttpRequestInterceptor> e;
    private LinkedList<HttpRequestInterceptor> f;
    private LinkedList<HttpResponseInterceptor> g;
    private LinkedList<HttpResponseInterceptor> h;
    private String i;
    private HttpProcessor j;
    private ConnectionReuseStrategy k;
    private HttpResponseFactory l;
    private HttpRequestHandlerMapper m;
    private Map<String, HttpRequestHandler> n;
    private HttpExpectationVerifier o;
    private ServerSocketFactory p;
    private SSLContext q;
    private SSLServerSetupHandler r;
    private HttpConnectionFactory<? extends DefaultBHttpServerConnection> s;
    private ExceptionLogger t;

    private ServerBootstrap() {
    }

    public static ServerBootstrap bootstrap() {
        return new ServerBootstrap();
    }

    public final ServerBootstrap setListenerPort(int i) {
        this.a = i;
        return this;
    }

    public final ServerBootstrap setLocalAddress(InetAddress inetAddress) {
        this.b = inetAddress;
        return this;
    }

    public final ServerBootstrap setSocketConfig(SocketConfig socketConfig) {
        this.c = socketConfig;
        return this;
    }

    public final ServerBootstrap setConnectionConfig(ConnectionConfig connectionConfig) {
        this.d = connectionConfig;
        return this;
    }

    public final ServerBootstrap setHttpProcessor(HttpProcessor httpProcessor) {
        this.j = httpProcessor;
        return this;
    }

    public final ServerBootstrap addInterceptorFirst(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            if (this.g == null) {
                this.g = new LinkedList();
            }
            this.g.addFirst(httpResponseInterceptor);
        }
        return this;
    }

    public final ServerBootstrap addInterceptorLast(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            if (this.h == null) {
                this.h = new LinkedList();
            }
            this.h.addLast(httpResponseInterceptor);
        }
        return this;
    }

    public final ServerBootstrap addInterceptorFirst(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            if (this.e == null) {
                this.e = new LinkedList();
            }
            this.e.addFirst(httpRequestInterceptor);
        }
        return this;
    }

    public final ServerBootstrap addInterceptorLast(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            if (this.f == null) {
                this.f = new LinkedList();
            }
            this.f.addLast(httpRequestInterceptor);
        }
        return this;
    }

    public final ServerBootstrap setServerInfo(String str) {
        this.i = str;
        return this;
    }

    public final ServerBootstrap setConnectionReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        this.k = connectionReuseStrategy;
        return this;
    }

    public final ServerBootstrap setResponseFactory(HttpResponseFactory httpResponseFactory) {
        this.l = httpResponseFactory;
        return this;
    }

    public final ServerBootstrap setHandlerMapper(HttpRequestHandlerMapper httpRequestHandlerMapper) {
        this.m = httpRequestHandlerMapper;
        return this;
    }

    public final ServerBootstrap registerHandler(String str, HttpRequestHandler httpRequestHandler) {
        if (!(str == null || httpRequestHandler == null)) {
            if (this.n == null) {
                this.n = new HashMap();
            }
            this.n.put(str, httpRequestHandler);
        }
        return this;
    }

    public final ServerBootstrap setExpectationVerifier(HttpExpectationVerifier httpExpectationVerifier) {
        this.o = httpExpectationVerifier;
        return this;
    }

    public final ServerBootstrap setConnectionFactory(HttpConnectionFactory<? extends DefaultBHttpServerConnection> httpConnectionFactory) {
        this.s = httpConnectionFactory;
        return this;
    }

    public final ServerBootstrap setSslSetupHandler(SSLServerSetupHandler sSLServerSetupHandler) {
        this.r = sSLServerSetupHandler;
        return this;
    }

    public final ServerBootstrap setServerSocketFactory(ServerSocketFactory serverSocketFactory) {
        this.p = serverSocketFactory;
        return this;
    }

    public final ServerBootstrap setSslContext(SSLContext sSLContext) {
        this.q = sSLContext;
        return this;
    }

    public final ServerBootstrap setExceptionLogger(ExceptionLogger exceptionLogger) {
        this.t = exceptionLogger;
        return this;
    }

    public HttpServer create() {
        int i;
        SocketConfig socketConfig;
        HttpProcessor httpProcessor = this.j;
        if (httpProcessor == null) {
            Iterator it;
            HttpProcessorBuilder create = HttpProcessorBuilder.create();
            if (this.e != null) {
                it = this.e.iterator();
                while (it.hasNext()) {
                    create.addFirst((HttpRequestInterceptor) it.next());
                }
            }
            if (this.g != null) {
                it = this.g.iterator();
                while (it.hasNext()) {
                    create.addFirst((HttpResponseInterceptor) it.next());
                }
            }
            String str = this.i;
            if (str == null) {
                str = "Apache-HttpCore/1.1";
            }
            create.addAll(new ResponseDate(), new ResponseServer(str), new ResponseContent(), new ResponseConnControl());
            if (this.f != null) {
                it = this.f.iterator();
                while (it.hasNext()) {
                    create.addLast((HttpRequestInterceptor) it.next());
                }
            }
            if (this.h != null) {
                it = this.h.iterator();
                while (it.hasNext()) {
                    create.addLast((HttpResponseInterceptor) it.next());
                }
            }
            httpProcessor = create.build();
        }
        HttpRequestHandlerMapper httpRequestHandlerMapper = this.m;
        if (httpRequestHandlerMapper == null) {
            httpRequestHandlerMapper = new UriHttpRequestHandlerMapper();
            if (this.n != null) {
                for (Entry entry : this.n.entrySet()) {
                    httpRequestHandlerMapper.register((String) entry.getKey(), (HttpRequestHandler) entry.getValue());
                }
            }
        }
        ConnectionReuseStrategy connectionReuseStrategy = this.k;
        if (connectionReuseStrategy == null) {
            connectionReuseStrategy = DefaultConnectionReuseStrategy.INSTANCE;
        }
        HttpResponseFactory httpResponseFactory = this.l;
        if (httpResponseFactory == null) {
            httpResponseFactory = DefaultHttpResponseFactory.INSTANCE;
        }
        HttpService httpService = new HttpService(httpProcessor, connectionReuseStrategy, httpResponseFactory, httpRequestHandlerMapper, this.o);
        ServerSocketFactory serverSocketFactory = this.p;
        if (serverSocketFactory == null) {
            if (this.q != null) {
                serverSocketFactory = this.q.getServerSocketFactory();
            } else {
                serverSocketFactory = ServerSocketFactory.getDefault();
            }
        }
        HttpConnectionFactory httpConnectionFactory = this.s;
        if (httpConnectionFactory == null) {
            if (this.d != null) {
                httpConnectionFactory = new DefaultBHttpServerConnectionFactory(this.d);
            } else {
                httpConnectionFactory = DefaultBHttpServerConnectionFactory.INSTANCE;
            }
        }
        ExceptionLogger exceptionLogger = this.t;
        if (exceptionLogger == null) {
            exceptionLogger = ExceptionLogger.NO_OP;
        }
        if (this.a > 0) {
            i = this.a;
        } else {
            i = 0;
        }
        InetAddress inetAddress = this.b;
        if (this.c != null) {
            socketConfig = this.c;
        } else {
            socketConfig = SocketConfig.DEFAULT;
        }
        return new HttpServer(i, inetAddress, socketConfig, serverSocketFactory, httpService, httpConnectionFactory, this.r, exceptionLogger);
    }
}
