package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.g.e;
import java.util.Map;
import javax.annotation.Nullable;

public interface af<FETCH_STATE extends s> {
    @Nullable
    Map<String, String> a(FETCH_STATE fetch_state, int i);

    void a(FETCH_STATE fetch_state, af$a af_a);

    boolean a(FETCH_STATE fetch_state);

    FETCH_STATE b(j<e> jVar, aj ajVar);

    void b(FETCH_STATE fetch_state, int i);
}
