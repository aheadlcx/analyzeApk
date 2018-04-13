package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.proto.Serving.Resource;
import com.google.analytics.containertag.proto.Serving.Supplemental;
import com.google.analytics.containertag.proto.Serving.SupplementedResource;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.CustomFunctionCall.CustomEvaluator;
import com.google.tagmanager.LoadCallback.Failure;
import com.google.tagmanager.ResourceUtil.ExpandedResource;
import com.google.tagmanager.ResourceUtil.InvalidResourceException;
import com.google.tagmanager.TagManager.RefreshMode;
import com.google.tagmanager.proto.Resource.ResourceWithMetadata;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    static final boolean ENABLE_CONTAINER_DEBUG_MODE = false;
    @VisibleForTesting
    static final int MAX_NUMBER_OF_TOKENS = 30;
    static final int MINIMUM_REFRESH_PERIOD_BURST_MODE_MS = 5000;
    static final long MINIMUM_REFRESH_PERIOD_MS = 900000;
    static final long REFRESH_PERIOD_ON_FAILURE_MS = 3600000;
    static final long REFRESH_PERIOD_ON_SUCCESS_MS = 43200000;
    private Clock mClock;
    private final String mContainerId;
    private final Context mContext;
    private CtfeHost mCtfeHost;
    private volatile String mCtfeServerAddress;
    private volatile String mCtfeUrlPathAndQuery;
    @VisibleForTesting
    LoadCallback<ResourceWithMetadata> mDiskLoadCallback;
    private Map<String, FunctionCallMacroHandler> mFunctionCallMacroHandlers;
    private Map<String, FunctionCallTagHandler> mFunctionCallTagHandlers;
    private SupplementedResource mLastLoadedSupplementedResource;
    private volatile long mLastRefreshMethodCalledTime;
    private volatile long mLastRefreshTime;
    @VisibleForTesting
    LoadCallback<SupplementedResource> mNetworkLoadCallback;
    private ResourceLoaderScheduler mNetworkLoadScheduler;
    private volatile int mNumTokens;
    private volatile int mResourceFormatVersion;
    private ResourceStorage mResourceStorage;
    private volatile String mResourceVersion;
    private Runtime mRuntime;
    private final TagManager mTagManager;
    private Callback mUserCallback;

    public interface Callback {
        void containerRefreshBegin(Container container, RefreshType refreshType);

        void containerRefreshFailure(Container container, RefreshType refreshType, RefreshFailure refreshFailure);

        void containerRefreshSuccess(Container container, RefreshType refreshType);
    }

    public interface FunctionCallMacroHandler {
        Object getValue(String str, Map<String, Object> map);
    }

    private class FunctionCallMacroHandlerAdapter implements CustomEvaluator {
        private FunctionCallMacroHandlerAdapter() {
        }

        public Object evaluate(String str, Map<String, Object> map) {
            FunctionCallMacroHandler functionCallMacroHandler = Container.this.getFunctionCallMacroHandler(str);
            return functionCallMacroHandler == null ? null : functionCallMacroHandler.getValue(str, map);
        }
    }

    public interface FunctionCallTagHandler {
        void execute(String str, Map<String, Object> map);
    }

    private class FunctionCallTagHandlerAdapter implements CustomEvaluator {
        private FunctionCallTagHandlerAdapter() {
        }

        public Object evaluate(String str, Map<String, Object> map) {
            Container.this.getFunctionCallTagHandler(str).execute(str, map);
            return Types.getDefaultString();
        }
    }

    public enum RefreshFailure {
        NO_SAVED_CONTAINER,
        IO_ERROR,
        NO_NETWORK,
        NETWORK_ERROR,
        SERVER_ERROR,
        UNKNOWN_ERROR
    }

    public enum RefreshType {
        SAVED,
        NETWORK
    }

    interface ResourceLoaderScheduler {
        void close();

        void loadAfterDelay(long j, String str);

        void setCtfeURLPathAndQuery(String str);

        void setLoadCallback(LoadCallback<SupplementedResource> loadCallback);
    }

    interface ResourceStorage {
        void close();

        ExpandedResource loadExpandedResourceFromJsonAsset(String str);

        Resource loadResourceFromContainerAsset(String str);

        void loadResourceFromDiskInBackground();

        void saveResourceToDiskInBackground(ResourceWithMetadata resourceWithMetadata);

        void setLoadCallback(LoadCallback<ResourceWithMetadata> loadCallback);
    }

    Container(Context context, String str, TagManager tagManager) {
        this(context, str, tagManager, new ResourceStorageImpl(context, str));
    }

    @VisibleForTesting
    Container(Context context, String str, TagManager tagManager, ResourceStorage resourceStorage) {
        this.mResourceVersion = "";
        this.mResourceFormatVersion = 0;
        this.mCtfeHost = new CtfeHost();
        this.mContext = context;
        this.mContainerId = str;
        this.mTagManager = tagManager;
        this.mLastLoadedSupplementedResource = new SupplementedResource();
        this.mResourceStorage = resourceStorage;
        this.mNumTokens = 30;
        this.mFunctionCallMacroHandlers = new HashMap();
        this.mFunctionCallTagHandlers = new HashMap();
        createInitialContainer();
    }

    public String getContainerId() {
        return this.mContainerId;
    }

    public boolean getBoolean(String str) {
        Runtime runtime = getRuntime();
        if (runtime == null) {
            Log.e("getBoolean called for closed container.");
            return Types.getDefaultBoolean().booleanValue();
        }
        try {
            return Types.valueToBoolean((Value) runtime.evaluateMacroReference(str).getObject()).booleanValue();
        } catch (Exception e) {
            Log.e("Calling getBoolean() threw an exception: " + e.getMessage() + " Returning default value.");
            return Types.getDefaultBoolean().booleanValue();
        }
    }

    public double getDouble(String str) {
        Runtime runtime = getRuntime();
        if (runtime == null) {
            Log.e("getDouble called for closed container.");
            return Types.getDefaultDouble().doubleValue();
        }
        try {
            return Types.valueToDouble((Value) runtime.evaluateMacroReference(str).getObject()).doubleValue();
        } catch (Exception e) {
            Log.e("Calling getDouble() threw an exception: " + e.getMessage() + " Returning default value.");
            return Types.getDefaultDouble().doubleValue();
        }
    }

    public long getLong(String str) {
        Runtime runtime = getRuntime();
        if (runtime == null) {
            Log.e("getLong called for closed container.");
            return Types.getDefaultInt64().longValue();
        }
        try {
            return Types.valueToInt64((Value) runtime.evaluateMacroReference(str).getObject()).longValue();
        } catch (Exception e) {
            Log.e("Calling getLong() threw an exception: " + e.getMessage() + " Returning default value.");
            return Types.getDefaultInt64().longValue();
        }
    }

    public String getString(String str) {
        Runtime runtime = getRuntime();
        if (runtime == null) {
            Log.e("getString called for closed container.");
            return Types.getDefaultString();
        }
        try {
            return Types.valueToString((Value) runtime.evaluateMacroReference(str).getObject());
        } catch (Exception e) {
            Log.e("Calling getString() threw an exception: " + e.getMessage() + " Returning default value.");
            return Types.getDefaultString();
        }
    }

    public long getLastRefreshTime() {
        return this.mLastRefreshTime;
    }

    public synchronized void refresh() {
        if (getRuntime() == null) {
            Log.w("refresh called for closed container");
        } else {
            try {
                if (isDefaultContainerRefreshMode()) {
                    Log.w("Container is in DEFAULT_CONTAINER mode. Refresh request is ignored.");
                } else {
                    long currentTimeMillis = this.mClock.currentTimeMillis();
                    if (useAvailableToken(currentTimeMillis)) {
                        Log.v("Container refresh requested");
                        loadAfterDelay(0);
                        this.mLastRefreshMethodCalledTime = currentTimeMillis;
                    } else {
                        Log.v("Container refresh was called too often. Ignored.");
                    }
                }
            } catch (Exception e) {
                Log.e("Calling refresh() throws an exception: " + e.getMessage());
            }
        }
    }

    public synchronized void close() {
        try {
            if (this.mNetworkLoadScheduler != null) {
                this.mNetworkLoadScheduler.close();
            }
            this.mNetworkLoadScheduler = null;
            if (this.mResourceStorage != null) {
                this.mResourceStorage.close();
            }
            this.mResourceStorage = null;
            this.mUserCallback = null;
            this.mTagManager.removeContainer(this.mContainerId);
        } catch (Exception e) {
            Log.e("Calling close() threw an exception: " + e.getMessage());
        }
        this.mRuntime = null;
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    void load(Callback callback) {
        load(callback, new ResourceLoaderSchedulerImpl(this.mContext, this.mContainerId, this.mCtfeHost), new Clock() {
            public long currentTimeMillis() {
                return System.currentTimeMillis();
            }
        });
    }

    public synchronized void registerFunctionCallMacroHandler(String str, FunctionCallMacroHandler functionCallMacroHandler) {
        this.mFunctionCallMacroHandlers.put(str, functionCallMacroHandler);
    }

    public synchronized FunctionCallMacroHandler getFunctionCallMacroHandler(String str) {
        return (FunctionCallMacroHandler) this.mFunctionCallMacroHandlers.get(str);
    }

    public synchronized void registerFunctionCallTagHandler(String str, FunctionCallTagHandler functionCallTagHandler) {
        this.mFunctionCallTagHandlers.put(str, functionCallTagHandler);
    }

    public synchronized FunctionCallTagHandler getFunctionCallTagHandler(String str) {
        return (FunctionCallTagHandler) this.mFunctionCallTagHandlers.get(str);
    }

    private synchronized void callRefreshSuccess(RefreshType refreshType) {
        Log.v("calling containerRefreshSuccess(" + refreshType + "): mUserCallback = " + this.mUserCallback);
        if (this.mUserCallback != null) {
            this.mUserCallback.containerRefreshSuccess(this, refreshType);
        }
    }

    private synchronized void callRefreshFailure(RefreshType refreshType, RefreshFailure refreshFailure) {
        if (this.mUserCallback != null) {
            this.mUserCallback.containerRefreshFailure(this, refreshType, refreshFailure);
        }
    }

    private synchronized void callRefreshBegin(RefreshType refreshType) {
        if (this.mUserCallback != null) {
            this.mUserCallback.containerRefreshBegin(this, refreshType);
        }
    }

    @VisibleForTesting
    void evaluateTags(String str) {
        getRuntime().evaluateTags(str);
    }

    @VisibleForTesting
    synchronized void load(Callback callback, ResourceLoaderScheduler resourceLoaderScheduler, final Clock clock) {
        if (this.mDiskLoadCallback != null) {
            throw new RuntimeException("Container already loaded: container ID: " + this.mContainerId);
        }
        this.mClock = clock;
        this.mUserCallback = callback;
        this.mDiskLoadCallback = new LoadCallback<ResourceWithMetadata>() {
            public void startLoad() {
                Container.this.callRefreshBegin(RefreshType.SAVED);
            }

            public void onSuccess(ResourceWithMetadata resourceWithMetadata) {
                if (Container.this.isDefault()) {
                    Resource resource;
                    if (resourceWithMetadata.supplementedResource != null) {
                        resource = resourceWithMetadata.supplementedResource.resource;
                        Container.this.mLastLoadedSupplementedResource = resourceWithMetadata.supplementedResource;
                    } else {
                        resource = resourceWithMetadata.resource;
                        Container.this.mLastLoadedSupplementedResource.resource = resource;
                        Container.this.mLastLoadedSupplementedResource.supplemental = null;
                        Container.this.mLastLoadedSupplementedResource.fingerprint = resource.version;
                    }
                    Container.this.initEvaluators(resource);
                    if (Container.this.mLastLoadedSupplementedResource.supplemental != null) {
                        Container.this.setSupplementals(Container.this.mLastLoadedSupplementedResource.supplemental);
                    }
                    Log.v("setting refresh time to saved time: " + resourceWithMetadata.timeStamp);
                    Container.this.mLastRefreshTime = resourceWithMetadata.timeStamp;
                    Container.this.loadAfterDelay(Math.max(0, Math.min(Container.REFRESH_PERIOD_ON_SUCCESS_MS, (Container.this.mLastRefreshTime + Container.REFRESH_PERIOD_ON_SUCCESS_MS) - clock.currentTimeMillis())));
                }
                Container.this.callRefreshSuccess(RefreshType.SAVED);
            }

            public void onFailure(Failure failure) {
                Container.this.callRefreshFailure(RefreshType.SAVED, failureToRefreshFailure(failure));
                if (Container.this.isDefault()) {
                    Container.this.loadAfterDelay(0);
                }
            }

            private RefreshFailure failureToRefreshFailure(Failure failure) {
                switch (failure) {
                    case NOT_AVAILABLE:
                        return RefreshFailure.NO_SAVED_CONTAINER;
                    case IO_ERROR:
                        return RefreshFailure.IO_ERROR;
                    case SERVER_ERROR:
                        return RefreshFailure.SERVER_ERROR;
                    default:
                        return RefreshFailure.UNKNOWN_ERROR;
                }
            }
        };
        if (isDefaultContainerRefreshMode()) {
            Log.i("Container is in DEFAULT_CONTAINER mode. Use default container.");
        } else {
            this.mResourceStorage.setLoadCallback(this.mDiskLoadCallback);
            this.mNetworkLoadCallback = new LoadCallback<SupplementedResource>() {
                public void startLoad() {
                    Container.this.callRefreshBegin(RefreshType.NETWORK);
                }

                public void onSuccess(SupplementedResource supplementedResource) {
                    synchronized (Container.this) {
                        Resource resource = supplementedResource.resource;
                        if (resource != null) {
                            Container.this.initEvaluators(resource);
                            Container.this.mLastLoadedSupplementedResource.resource = resource;
                        } else if (Container.this.mLastLoadedSupplementedResource.resource == null) {
                            onFailure(Failure.SERVER_ERROR);
                            return;
                        }
                        if (supplementedResource.supplemental.length > 0) {
                            Container.this.setSupplementals(supplementedResource.supplemental);
                            Container.this.mLastLoadedSupplementedResource.supplemental = supplementedResource.supplemental;
                        }
                        Container.this.mLastRefreshTime = clock.currentTimeMillis();
                        Container.this.mLastLoadedSupplementedResource.fingerprint = supplementedResource.fingerprint;
                        if (Container.this.mLastLoadedSupplementedResource.fingerprint.length() == 0) {
                            Container.this.mLastLoadedSupplementedResource.fingerprint = Container.this.mLastLoadedSupplementedResource.resource.version;
                        }
                        Log.v("setting refresh time to current time: " + Container.this.mLastRefreshTime);
                        if (!Container.this.isContainerPreview()) {
                            Container.this.saveResourceToDisk(Container.this.mLastLoadedSupplementedResource);
                        }
                        Container.this.loadAfterDelay(Container.REFRESH_PERIOD_ON_SUCCESS_MS);
                        Container.this.callRefreshSuccess(RefreshType.NETWORK);
                    }
                }

                public void onFailure(Failure failure) {
                    Container.this.loadAfterDelay(3600000);
                    Container.this.callRefreshFailure(RefreshType.NETWORK, failureToRefreshFailure(failure));
                }

                private RefreshFailure failureToRefreshFailure(Failure failure) {
                    switch (failure) {
                        case NOT_AVAILABLE:
                            return RefreshFailure.NO_NETWORK;
                        case IO_ERROR:
                            return RefreshFailure.NETWORK_ERROR;
                        case SERVER_ERROR:
                            return RefreshFailure.SERVER_ERROR;
                        default:
                            return RefreshFailure.UNKNOWN_ERROR;
                    }
                }
            };
            resourceLoaderScheduler.setLoadCallback(this.mNetworkLoadCallback);
            if (isContainerPreview()) {
                this.mCtfeUrlPathAndQuery = PreviewManager.getInstance().getCTFEUrlPath();
                resourceLoaderScheduler.setCtfeURLPathAndQuery(this.mCtfeUrlPathAndQuery);
            }
            if (this.mCtfeServerAddress != null) {
                this.mCtfeHost.setCtfeServerAddress(this.mCtfeServerAddress);
            }
            this.mNetworkLoadScheduler = resourceLoaderScheduler;
            this.mResourceStorage.loadResourceFromDiskInBackground();
        }
    }

    @VisibleForTesting
    String getResourceVersion() {
        return this.mResourceVersion;
    }

    @VisibleForTesting
    synchronized void loadAfterDelay(long j) {
        if (!(this.mNetworkLoadScheduler == null || isDefaultContainerRefreshMode())) {
            this.mNetworkLoadScheduler.loadAfterDelay(j, this.mLastLoadedSupplementedResource.fingerprint);
        }
    }

    private synchronized void saveResourceToDisk(SupplementedResource supplementedResource) {
        if (this.mResourceStorage != null) {
            ResourceWithMetadata resourceWithMetadata = new ResourceWithMetadata();
            resourceWithMetadata.timeStamp = getLastRefreshTime();
            resourceWithMetadata.resource = new Resource();
            resourceWithMetadata.supplementedResource = supplementedResource;
            this.mResourceStorage.saveResourceToDiskInBackground(resourceWithMetadata);
        }
    }

    private void initEvaluators(Resource resource) {
        try {
            initEvaluatorsWithExpandedResource(ResourceUtil.getExpandedResource(resource));
        } catch (InvalidResourceException e) {
            Log.e("Not loading resource: " + resource + " because it is invalid: " + e.toString());
        }
    }

    private void setSupplementals(Supplemental[] supplementalArr) {
        List arrayList = new ArrayList();
        for (Object add : supplementalArr) {
            arrayList.add(add);
        }
        getRuntime().setSupplementals(arrayList);
    }

    private void initEvaluatorsWithExpandedResource(ExpandedResource expandedResource) {
        this.mResourceVersion = expandedResource.getVersion();
        this.mResourceFormatVersion = expandedResource.getResourceFormatVersion();
        ExpandedResource expandedResource2 = expandedResource;
        setRuntime(new Runtime(this.mContext, expandedResource2, this.mTagManager.getDataLayer(), new FunctionCallMacroHandlerAdapter(), new FunctionCallTagHandlerAdapter(), createEventInfoDistributor(this.mResourceVersion)));
    }

    @VisibleForTesting
    EventInfoDistributor createEventInfoDistributor(String str) {
        if (PreviewManager.getInstance().getPreviewMode().equals(PreviewMode.CONTAINER_DEBUG)) {
        }
        return new NoopEventInfoDistributor();
    }

    private synchronized void setRuntime(Runtime runtime) {
        this.mRuntime = runtime;
    }

    private synchronized Runtime getRuntime() {
        return this.mRuntime;
    }

    @VisibleForTesting
    synchronized void setCtfeServerAddress(String str) {
        this.mCtfeServerAddress = str;
        if (str != null) {
            this.mCtfeHost.setCtfeServerAddress(str);
        }
    }

    @VisibleForTesting
    synchronized void setCtfeUrlPathAndQuery(String str) {
        this.mCtfeUrlPathAndQuery = str;
        if (this.mNetworkLoadScheduler != null) {
            this.mNetworkLoadScheduler.setCtfeURLPathAndQuery(str);
        }
    }

    String getCtfeUrlPathAndQuery() {
        return this.mCtfeUrlPathAndQuery;
    }

    private boolean isContainerPreview() {
        PreviewManager instance = PreviewManager.getInstance();
        return (instance.getPreviewMode() == PreviewMode.CONTAINER || instance.getPreviewMode() == PreviewMode.CONTAINER_DEBUG) && this.mContainerId.equals(instance.getContainerId());
    }

    private void createInitialContainer() {
        String str = "tagmanager/" + this.mContainerId;
        Resource loadResourceFromContainerAsset = this.mResourceStorage.loadResourceFromContainerAsset(str);
        if (loadResourceFromContainerAsset != null) {
            initEvaluators(loadResourceFromContainerAsset);
            return;
        }
        ExpandedResource loadExpandedResourceFromJsonAsset = this.mResourceStorage.loadExpandedResourceFromJsonAsset(str + ".json");
        if (loadExpandedResourceFromJsonAsset == null) {
            Log.w("No default container found; creating an empty container.");
            loadExpandedResourceFromJsonAsset = ExpandedResource.newBuilder().build();
        }
        initEvaluatorsWithExpandedResource(loadExpandedResourceFromJsonAsset);
    }

    private boolean isDefaultContainerRefreshMode() {
        return this.mTagManager.getRefreshMode() == RefreshMode.DEFAULT_CONTAINER;
    }

    private boolean useAvailableToken(long j) {
        if (this.mLastRefreshMethodCalledTime == 0) {
            this.mNumTokens--;
            return true;
        }
        long j2 = j - this.mLastRefreshMethodCalledTime;
        if (j2 < 5000) {
            return false;
        }
        if (this.mNumTokens < 30) {
            this.mNumTokens = Math.min(30, ((int) Math.floor((double) (j2 / MINIMUM_REFRESH_PERIOD_MS))) + this.mNumTokens);
        }
        if (this.mNumTokens <= 0) {
            return false;
        }
        this.mNumTokens--;
        return true;
    }
}
