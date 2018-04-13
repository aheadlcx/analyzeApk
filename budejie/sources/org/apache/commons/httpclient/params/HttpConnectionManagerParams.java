package org.apache.commons.httpclient.params;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HostConfiguration;

public class HttpConnectionManagerParams extends HttpConnectionParams {
    public static final String MAX_HOST_CONNECTIONS = "http.connection-manager.max-per-host";
    public static final String MAX_TOTAL_CONNECTIONS = "http.connection-manager.max-total";

    public void setDefaultMaxConnectionsPerHost(int i) {
        setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, i);
    }

    public void setMaxConnectionsPerHost(HostConfiguration hostConfiguration, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxHostConnections must be greater than 0");
        }
        Map map = (Map) getParameter(MAX_HOST_CONNECTIONS);
        if (map == null) {
            map = new HashMap();
        } else {
            Object hashMap = new HashMap(map);
        }
        map.put(hostConfiguration, new Integer(i));
        setParameter(MAX_HOST_CONNECTIONS, map);
    }

    public int getDefaultMaxConnectionsPerHost() {
        return getMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION);
    }

    public int getMaxConnectionsPerHost(HostConfiguration hostConfiguration) {
        Map map = (Map) getParameter(MAX_HOST_CONNECTIONS);
        if (map == null) {
            return 2;
        }
        Integer num = (Integer) map.get(hostConfiguration);
        if (num == null && hostConfiguration != HostConfiguration.ANY_HOST_CONFIGURATION) {
            return getMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION);
        }
        return num == null ? 2 : num.intValue();
    }

    public void setMaxTotalConnections(int i) {
        setIntParameter(MAX_TOTAL_CONNECTIONS, i);
    }

    public int getMaxTotalConnections() {
        return getIntParameter(MAX_TOTAL_CONNECTIONS, 20);
    }
}
