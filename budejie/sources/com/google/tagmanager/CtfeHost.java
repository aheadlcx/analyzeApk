package com.google.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;

class CtfeHost {
    private static final String CTFE_SERVER_ADDRESS = "https://www.googletagmanager.com";
    @VisibleForTesting
    static final String CTFE_URL_PATH_PREFIX = "/d?";
    static final String DEBUG_EVENT_NUMBER_QUERY = "&event_number=";
    private String mCtfeServerAddress = CTFE_SERVER_ADDRESS;

    public void setCtfeServerAddress(String str) {
        this.mCtfeServerAddress = str;
        Log.i("The Ctfe server endpoint was changed to: " + str);
    }

    public String getCtfeServerAddress() {
        return this.mCtfeServerAddress;
    }

    String constructCtfeDebugUrl(int i) {
        return this.mCtfeServerAddress + CTFE_URL_PATH_PREFIX + PreviewManager.getInstance().getCTFEUrlDebugQuery() + DEBUG_EVENT_NUMBER_QUERY + i;
    }
}
