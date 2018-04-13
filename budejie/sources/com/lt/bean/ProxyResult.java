package com.lt.bean;

import java.util.List;

public class ProxyResult {
    private DataBean data;
    private String msg;
    private int state;

    public static class DataBean {
        private List<ProxyBean> Proxy;

        public static class ProxyBean {
            private String AppDomainName;
            private String DomainName;
            private String NetIP;
            private String WapIP;

            public String getAppDomainName() {
                return this.AppDomainName;
            }

            public void setAppDomainName(String str) {
                this.AppDomainName = str;
            }

            public String getDomainName() {
                return this.DomainName;
            }

            public void setDomainName(String str) {
                this.DomainName = str;
            }

            public String getNetIP() {
                return this.NetIP;
            }

            public void setNetIP(String str) {
                this.NetIP = str;
            }

            public String getWapIP() {
                return this.WapIP;
            }

            public void setWapIP(String str) {
                this.WapIP = str;
            }
        }

        public List<ProxyBean> getProxy() {
            return this.Proxy;
        }

        public void setProxy(List<ProxyBean> list) {
            this.Proxy = list;
        }
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }
}
