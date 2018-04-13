package com.facebook.stetho.server.http;

import android.support.annotation.Nullable;
import java.util.ArrayList;

public class HandlerRegistry {
    private final ArrayList<HttpHandler> mHttpHandlers = new ArrayList();
    private final ArrayList<PathMatcher> mPathMatchers = new ArrayList();

    public synchronized void register(PathMatcher pathMatcher, HttpHandler httpHandler) {
        this.mPathMatchers.add(pathMatcher);
        this.mHttpHandlers.add(httpHandler);
    }

    public synchronized boolean unregister(PathMatcher pathMatcher, HttpHandler httpHandler) {
        boolean z;
        int indexOf = this.mPathMatchers.indexOf(pathMatcher);
        if (indexOf < 0 || httpHandler != this.mHttpHandlers.get(indexOf)) {
            z = false;
        } else {
            this.mPathMatchers.remove(indexOf);
            this.mHttpHandlers.remove(indexOf);
            z = true;
        }
        return z;
    }

    @Nullable
    public synchronized HttpHandler lookup(String str) {
        HttpHandler httpHandler;
        int size = this.mPathMatchers.size();
        for (int i = 0; i < size; i++) {
            if (((PathMatcher) this.mPathMatchers.get(i)).match(str)) {
                httpHandler = (HttpHandler) this.mHttpHandlers.get(i);
                break;
            }
        }
        httpHandler = null;
        return httpHandler;
    }
}
