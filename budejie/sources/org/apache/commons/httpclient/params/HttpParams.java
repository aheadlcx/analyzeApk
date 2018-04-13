package org.apache.commons.httpclient.params;

public interface HttpParams {
    boolean getBooleanParameter(String str, boolean z);

    HttpParams getDefaults();

    double getDoubleParameter(String str, double d);

    int getIntParameter(String str, int i);

    long getLongParameter(String str, long j);

    Object getParameter(String str);

    boolean isParameterFalse(String str);

    boolean isParameterSet(String str);

    boolean isParameterSetLocally(String str);

    boolean isParameterTrue(String str);

    void setBooleanParameter(String str, boolean z);

    void setDefaults(HttpParams httpParams);

    void setDoubleParameter(String str, double d);

    void setIntParameter(String str, int i);

    void setLongParameter(String str, long j);

    void setParameter(String str, Object obj);
}
