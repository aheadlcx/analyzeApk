package com.alibaba.wireless.security.open.umid;

import com.alibaba.wireless.security.open.IComponent;
import com.alibaba.wireless.security.open.SecException;

public interface IUMIDComponent extends IComponent {
    public static final int ENVIRONMENT_DAILY = 2;
    public static final int ENVIRONMENT_ONLINE = 0;
    public static final int ENVIRONMENT_PRE = 1;

    @Deprecated
    String getSecurityToken() throws SecException;

    String getSecurityToken(int i) throws SecException;

    void initUMID() throws SecException;

    void initUMID(int i, IUMIDInitListenerEx iUMIDInitListenerEx) throws SecException;

    @Deprecated
    void initUMID(String str, int i, String str2, IUMIDInitListenerEx iUMIDInitListenerEx) throws SecException;

    int initUMIDSync(int i) throws SecException;

    @Deprecated
    void registerInitListener(IUMIDInitListener iUMIDInitListener) throws SecException;

    void setEnvironment(int i) throws SecException;

    void setOnlineHost(String str) throws SecException;
}
