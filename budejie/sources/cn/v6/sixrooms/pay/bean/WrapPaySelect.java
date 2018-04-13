package cn.v6.sixrooms.pay.bean;

import java.util.List;

public class WrapPaySelect {
    private List<PaySelectBean> alipayless;
    private List<PaySelectBean> bankline;
    private CommodityInfo wxpayapp;
    private List<PaySelectBean> yeepayszx;
    private List<PaySelectBean> yeepayunicom;

    public static class CommodityInfo {
        private String msg;
        private List<CommodityItem> payrate;

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public List<CommodityItem> getPayrate() {
            return this.payrate;
        }

        public void setPayrate(List<CommodityItem> list) {
            this.payrate = list;
        }
    }

    public static class CommodityItem implements PayInfoInterface {
        private int c;
        private int r;

        public int getR() {
            return this.r;
        }

        public void setR(int i) {
            this.r = i;
        }

        public int getC() {
            return this.c;
        }

        public void setC(int i) {
            this.c = i;
        }

        public String getContent() {
            return "充值" + this.r + "元兑换" + this.c + "六币";
        }

        public String getMoney() {
            return this.r;
        }

        public String getCoin6() {
            return this.c;
        }
    }

    public CommodityInfo getWxpayapp() {
        return this.wxpayapp;
    }

    public void setWxpayapp(CommodityInfo commodityInfo) {
        this.wxpayapp = commodityInfo;
    }

    public List<PaySelectBean> getAlipayless() {
        return this.alipayless;
    }

    public void setAlipayless(List<PaySelectBean> list) {
        this.alipayless = list;
    }

    public List<PaySelectBean> getBankline() {
        return this.bankline;
    }

    public void setBankline(List<PaySelectBean> list) {
        this.bankline = list;
    }

    public List<PaySelectBean> getYeepayszx() {
        return this.yeepayszx;
    }

    public void setYeepayszx(List<PaySelectBean> list) {
        this.yeepayszx = list;
    }

    public List<PaySelectBean> getYeepayunicom() {
        return this.yeepayunicom;
    }

    public void setYeepayunicom(List<PaySelectBean> list) {
        this.yeepayunicom = list;
    }
}
