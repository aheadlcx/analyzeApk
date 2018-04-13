package com.facebook.imagepipeline.producers;

import java.util.Map;
import javax.annotation.Nullable;

public abstract class BaseNetworkFetcher<FETCH_STATE extends FetchState> implements NetworkFetcher<FETCH_STATE> {
    public boolean shouldPropagate(FETCH_STATE fetch_state) {
        return true;
    }

    public void onFetchCompletion(FETCH_STATE fetch_state, int i) {
    }

    @Nullable
    public Map<String, String> getExtraMap(FETCH_STATE fetch_state, int i) {
        return null;
    }
}
