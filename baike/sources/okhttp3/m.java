package okhttp3;

import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocket;
import okhttp3.OkHttpClient.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;

final class m extends Internal {
    m() {
    }

    public void addLenient(Headers$Builder headers$Builder, String str) {
        headers$Builder.a(str);
    }

    public void addLenient(Headers$Builder headers$Builder, String str, String str2) {
        headers$Builder.a(str, str2);
    }

    public void setCache(Builder builder, InternalCache internalCache) {
        builder.a(internalCache);
    }

    public boolean connectionBecameIdle(ConnectionPool connectionPool, RealConnection realConnection) {
        return connectionPool.b(realConnection);
    }

    public RealConnection get(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation, Route route) {
        return connectionPool.a(address, streamAllocation, route);
    }

    public boolean equalsNonHost(Address address, Address address2) {
        return address.a(address2);
    }

    public Socket deduplicate(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation) {
        return connectionPool.a(address, streamAllocation);
    }

    public void put(ConnectionPool connectionPool, RealConnection realConnection) {
        connectionPool.a(realConnection);
    }

    public RouteDatabase routeDatabase(ConnectionPool connectionPool) {
        return connectionPool.a;
    }

    public int code(Response.Builder builder) {
        return builder.c;
    }

    public void apply(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z) {
        connectionSpec.a(sSLSocket, z);
    }

    public HttpUrl getHttpUrlChecked(String str) throws MalformedURLException, UnknownHostException {
        return HttpUrl.b(str);
    }

    public StreamAllocation streamAllocation(Call call) {
        return ((n) call).a();
    }

    public Call newWebSocketCall(OkHttpClient okHttpClient, Request request) {
        return n.a(okHttpClient, request, true);
    }
}
