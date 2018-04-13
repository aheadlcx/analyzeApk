package cn.v6.sixrooms.socket.common;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.AddressBean;
import cn.v6.sixrooms.utils.RandomUtils;
import java.util.List;

public class SocketAddress {
    private static SocketAddress a = null;
    private List<String> b;
    private List<String> c;
    private int d = 0;
    private int e = 0;

    public static SocketAddress getInstance() {
        if (a == null) {
            a = new SocketAddress();
        }
        return a;
    }

    public AddressBean getAddress(List<String> list, int i) {
        if (i > list.size() - 1) {
            i = 0;
        }
        String str = (String) list.get(i);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        AddressBean addressBean = new AddressBean();
        int indexOf = str.indexOf(":");
        if (indexOf != -1) {
            addressBean.setAddress(str.substring(0, indexOf));
            addressBean.setPort(Integer.parseInt(str.substring(indexOf + 1)));
        }
        return addressBean;
    }

    public AddressBean getCurrentChatAddress() {
        return getAddress(this.c, this.e);
    }

    public AddressBean getCurrentImAddress() {
        return getAddress(this.b, this.d);
    }

    public AddressBean getNextImAddress() {
        int size = this.b.size();
        this.d++;
        if (this.d > size - 1) {
            this.d = 0;
        }
        return getCurrentImAddress();
    }

    public AddressBean getNextChatAddress() {
        int size = this.c.size();
        this.e++;
        if (this.e > size - 1) {
            this.e = 0;
        }
        return getCurrentChatAddress();
    }

    public List<String> getImAddressList() {
        return this.b;
    }

    public void setImAddressList(List<String> list) {
        this.d = 0;
        this.b = list;
        if (list.size() > 1) {
            this.d = RandomUtils.getLimitRandom(0, list.size() - 1);
        }
    }

    public List<String> getChatAddressList() {
        return this.c;
    }

    public void setChatAddressList(List<String> list) {
        this.e = 0;
        this.c = list;
        if (list.size() > 1) {
            this.e = RandomUtils.getLimitRandom(0, list.size() - 1);
        }
    }
}
