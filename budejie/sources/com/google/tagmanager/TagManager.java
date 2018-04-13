package com.google.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.Container.Callback;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager sInstance;
    private final ContainerProvider mContainerProvider;
    private final ConcurrentMap<String, Container> mContainers;
    private final Context mContext;
    private volatile String mCtfeServerAddr;
    private final DataLayer mDataLayer;
    private volatile RefreshMode mRefreshMode;

    @VisibleForTesting
    interface ContainerProvider {
        Container newContainer(Context context, String str, TagManager tagManager);
    }

    static class ContainerOpenException extends RuntimeException {
        private final String mContainerId;

        private ContainerOpenException(String str) {
            super("Container already open: " + str);
            this.mContainerId = str;
        }

        public String getContainerId() {
            return this.mContainerId;
        }
    }

    public enum RefreshMode {
        STANDARD,
        DEFAULT_CONTAINER
    }

    @VisibleForTesting
    TagManager(Context context, ContainerProvider containerProvider, DataLayer dataLayer) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.mContainerProvider = containerProvider;
        this.mRefreshMode = RefreshMode.STANDARD;
        this.mContainers = new ConcurrentHashMap();
        this.mDataLayer = dataLayer;
        this.mDataLayer.registerListener(new Listener() {
            public void changed(Map<Object, Object> map) {
                Object obj = map.get("event");
                if (obj != null) {
                    TagManager.this.refreshTagsInAllContainers(obj.toString());
                }
            }
        });
        this.mDataLayer.registerListener(new AdwordsClickReferrerListener(this.mContext));
    }

    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (sInstance == null) {
                if (context == null) {
                    Log.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                sInstance = new TagManager(context, new ContainerProvider() {
                    public Container newContainer(Context context, String str, TagManager tagManager) {
                        return new Container(context, str, tagManager);
                    }
                }, new DataLayer(new DataLayerPersistentStoreImpl(context)));
            }
            tagManager = sInstance;
        }
        return tagManager;
    }

    @VisibleForTesting
    static void clearInstance() {
        synchronized (TagManager.class) {
            sInstance = null;
        }
    }

    public DataLayer getDataLayer() {
        return this.mDataLayer;
    }

    public Container openContainer(String str, Callback callback) {
        Container newContainer = this.mContainerProvider.newContainer(this.mContext, str, this);
        if (this.mContainers.putIfAbsent(str, newContainer) != null) {
            throw new IllegalArgumentException("Container id:" + str + " has already been opened.");
        }
        if (this.mCtfeServerAddr != null) {
            newContainer.setCtfeServerAddress(this.mCtfeServerAddr);
        }
        newContainer.load(callback);
        return newContainer;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setLogger(Logger logger) {
        Log.setLogger(logger);
    }

    public Logger getLogger() {
        return Log.getLogger();
    }

    public void setRefreshMode(RefreshMode refreshMode) {
        this.mRefreshMode = refreshMode;
    }

    public RefreshMode getRefreshMode() {
        return this.mRefreshMode;
    }

    public Container getContainer(String str) {
        return (Container) this.mContainers.get(str);
    }

    synchronized boolean setPreviewData(Uri uri) {
        boolean z;
        PreviewManager instance = PreviewManager.getInstance();
        if (instance.setPreviewData(uri)) {
            String containerId = instance.getContainerId();
            switch (instance.getPreviewMode()) {
                case NONE:
                    Container container = (Container) this.mContainers.get(containerId);
                    if (container != null) {
                        container.setCtfeUrlPathAndQuery(null);
                        container.refresh();
                        break;
                    }
                    break;
                case CONTAINER:
                case CONTAINER_DEBUG:
                    for (Entry entry : this.mContainers.entrySet()) {
                        Container container2 = (Container) entry.getValue();
                        if (((String) entry.getKey()).equals(containerId)) {
                            container2.setCtfeUrlPathAndQuery(instance.getCTFEUrlPath());
                            container2.refresh();
                        } else if (container2.getCtfeUrlPathAndQuery() != null) {
                            container2.setCtfeUrlPathAndQuery(null);
                            container2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    @VisibleForTesting
    void setCtfeServerAddress(String str) {
        this.mCtfeServerAddr = str;
    }

    boolean removeContainer(String str) {
        return this.mContainers.remove(str) != null;
    }

    private void refreshTagsInAllContainers(String str) {
        for (Container evaluateTags : this.mContainers.values()) {
            evaluateTags.evaluateTags(str);
        }
    }
}
