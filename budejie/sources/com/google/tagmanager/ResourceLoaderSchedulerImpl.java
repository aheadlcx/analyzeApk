package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.proto.Serving.SupplementedResource;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class ResourceLoaderSchedulerImpl implements ResourceLoaderScheduler {
    private static final boolean MAY_INTERRUPT_IF_RUNNING = true;
    private LoadCallback<SupplementedResource> mCallback;
    private boolean mClosed;
    private final String mContainerId;
    private final Context mContext;
    private CtfeHost mCtfeHost;
    private String mCtfeUrlPathAndQuery;
    private final ScheduledExecutorService mExecutor;
    private ScheduledFuture<?> mFuture;
    private final ResourceLoaderFactory mResourceLoaderFactory;

    interface ScheduledExecutorServiceFactory {
        ScheduledExecutorService createExecutorService();
    }

    interface ResourceLoaderFactory {
        ResourceLoader createResourceLoader(CtfeHost ctfeHost);
    }

    public ResourceLoaderSchedulerImpl(Context context, String str, CtfeHost ctfeHost) {
        this(context, str, ctfeHost, null, null);
    }

    @VisibleForTesting
    ResourceLoaderSchedulerImpl(Context context, String str, CtfeHost ctfeHost, ScheduledExecutorServiceFactory scheduledExecutorServiceFactory, ResourceLoaderFactory resourceLoaderFactory) {
        this.mCtfeHost = ctfeHost;
        this.mContext = context;
        this.mContainerId = str;
        if (scheduledExecutorServiceFactory == null) {
            scheduledExecutorServiceFactory = new ScheduledExecutorServiceFactory() {
                public ScheduledExecutorService createExecutorService() {
                    return Executors.newSingleThreadScheduledExecutor();
                }
            };
        }
        this.mExecutor = scheduledExecutorServiceFactory.createExecutorService();
        if (resourceLoaderFactory == null) {
            this.mResourceLoaderFactory = new ResourceLoaderFactory() {
                public ResourceLoader createResourceLoader(CtfeHost ctfeHost) {
                    return new ResourceLoader(ResourceLoaderSchedulerImpl.this.mContext, ResourceLoaderSchedulerImpl.this.mContainerId, ctfeHost);
                }
            };
        } else {
            this.mResourceLoaderFactory = resourceLoaderFactory;
        }
    }

    public synchronized void close() {
        ensureNotClosed();
        if (this.mFuture != null) {
            this.mFuture.cancel(false);
        }
        this.mExecutor.shutdown();
        this.mClosed = true;
    }

    public synchronized void setLoadCallback(LoadCallback<SupplementedResource> loadCallback) {
        ensureNotClosed();
        this.mCallback = loadCallback;
    }

    public synchronized void loadAfterDelay(long j, String str) {
        Log.v("loadAfterDelay: containerId=" + this.mContainerId + " delay=" + j);
        ensureNotClosed();
        if (this.mCallback == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.mFuture != null) {
            this.mFuture.cancel(false);
        }
        this.mFuture = this.mExecutor.schedule(createResourceLoader(str), j, TimeUnit.MILLISECONDS);
    }

    public synchronized void setCtfeURLPathAndQuery(String str) {
        ensureNotClosed();
        this.mCtfeUrlPathAndQuery = str;
    }

    private synchronized void ensureNotClosed() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    private ResourceLoader createResourceLoader(String str) {
        ResourceLoader createResourceLoader = this.mResourceLoaderFactory.createResourceLoader(this.mCtfeHost);
        createResourceLoader.setLoadCallback(this.mCallback);
        createResourceLoader.setCtfeURLPathAndQuery(this.mCtfeUrlPathAndQuery);
        createResourceLoader.setPreviousVersion(str);
        return createResourceLoader;
    }
}
