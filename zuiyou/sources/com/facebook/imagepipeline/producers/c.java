package com.facebook.imagepipeline.producers;

import java.util.Map;
import javax.annotation.Nullable;

public abstract class c<FETCH_STATE extends s> implements af<FETCH_STATE> {
    public boolean a(FETCH_STATE fetch_state) {
        return true;
    }

    public void b(FETCH_STATE fetch_state, int i) {
    }

    @Nullable
    public Map<String, String> a(FETCH_STATE fetch_state, int i) {
        return null;
    }
}
