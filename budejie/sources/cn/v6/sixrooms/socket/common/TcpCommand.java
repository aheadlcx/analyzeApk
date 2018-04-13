package cn.v6.sixrooms.socket.common;

import java.io.Serializable;
import java.util.Vector;

public class TcpCommand implements Serializable {
    private static final long serialVersionUID = 1426848902697991656L;
    private Vector<CommItem> a = new Vector();

    public TcpCommand(String str) {
        setCommand(str);
    }

    public Vector<CommItem> getItems() {
        return this.a;
    }

    public void setItems(Vector<CommItem> vector) {
        this.a = vector;
    }

    public CommItem findItem(String str) {
        for (int i = 0; i < this.a.size(); i++) {
            CommItem commItem = (CommItem) this.a.get(i);
            if (commItem.getItemKey().compareTo(str) == 0) {
                return commItem;
            }
        }
        return null;
    }

    public String getItemValue(String str) {
        CommItem findItem = findItem(str);
        if (findItem == null) {
            return null;
        }
        return findItem.getItemValue();
    }

    public CommItem getEncItem() {
        return findItem("enc");
    }

    public String getEncValue() {
        return getItemValue("enc");
    }

    public boolean getEncBoolValue() {
        String encValue = getEncValue();
        if (encValue == null || "".equals(encValue) || !"yes".equals(encValue)) {
            return false;
        }
        return true;
    }

    public CommItem getCommandItem() {
        return findItem("command");
    }

    public String getCommandValue() {
        return getItemValue("command");
    }

    public CommItem getContentItem() {
        return findItem("content");
    }

    public String getContentValue() {
        return getItemValue("content");
    }

    public void setCommand(String str) {
        this.a.clear();
        int indexOf = str.indexOf(SocketUtil.CRLF);
        if (indexOf != -1) {
            String substring = str.substring(indexOf + 2);
            while (true) {
                int indexOf2 = substring.indexOf(SocketUtil.CRLF);
                if (indexOf2 >= 0) {
                    this.a.add(new CommItem(substring.substring(0, indexOf2)));
                    if (substring.length() > indexOf2 + 2) {
                        substring = substring.substring(indexOf2 + 2);
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
