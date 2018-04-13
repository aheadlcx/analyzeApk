package com.google.analytics.tracking.android;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

public class ExceptionReporter implements UncaughtExceptionHandler {
    static final String DEFAULT_DESCRIPTION = "UncaughtException";
    private ExceptionParser mExceptionParser;
    private final UncaughtExceptionHandler mOriginalHandler;
    private final ServiceManager mServiceManager;
    private final Tracker mTracker;

    public ExceptionReporter(Tracker tracker, ServiceManager serviceManager, UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (serviceManager == null) {
            throw new NullPointerException("serviceManager cannot be null");
        } else {
            this.mOriginalHandler = uncaughtExceptionHandler;
            this.mTracker = tracker;
            this.mServiceManager = serviceManager;
            this.mExceptionParser = new StandardExceptionParser(context, new ArrayList());
            Log.v("ExceptionReporter created, original handler is " + (uncaughtExceptionHandler == null ? "null" : uncaughtExceptionHandler.getClass().getName()));
        }
    }

    public ExceptionParser getExceptionParser() {
        return this.mExceptionParser;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.mExceptionParser = exceptionParser;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String str = DEFAULT_DESCRIPTION;
        if (this.mExceptionParser != null) {
            str = this.mExceptionParser.getDescription(thread != null ? thread.getName() : null, th);
        }
        Log.v("Tracking Exception: " + str);
        this.mTracker.send(MapBuilder.createException(str, Boolean.valueOf(true)).build());
        this.mServiceManager.dispatchLocalHits();
        if (this.mOriginalHandler != null) {
            Log.v("Passing exception to original handler.");
            this.mOriginalHandler.uncaughtException(thread, th);
        }
    }
}
