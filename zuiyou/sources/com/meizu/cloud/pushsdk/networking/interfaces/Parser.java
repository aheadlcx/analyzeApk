package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.http.RequestBody;
import com.meizu.cloud.pushsdk.networking.http.ResponseBody;
import java.io.IOException;
import java.lang.reflect.Type;

public interface Parser<F, T> {

    public static abstract class Factory {
        public Parser<ResponseBody, ?> responseBodyParser(Type type) {
            return null;
        }

        public Parser<?, RequestBody> requestBodyParser(Type type) {
            return null;
        }
    }

    T convert(F f) throws IOException;
}
