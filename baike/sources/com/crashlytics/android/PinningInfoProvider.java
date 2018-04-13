package com.crashlytics.android;

import java.io.InputStream;

public interface PinningInfoProvider {
    String getKeyStorePassword();

    InputStream getKeyStoreStream();

    String[] getPins();
}
