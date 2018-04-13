package qsbk.app.utils;

import java.util.Vector;
import qsbk.app.provider.DataUnit;

public class AuditQueue extends Vector<DataUnit> {
    public void push(DataUnit dataUnit) {
        super.addElement(dataUnit);
    }

    public DataUnit pop() {
        if (empty()) {
            return null;
        }
        DataUnit dataUnit = (DataUnit) super.elementAt(0);
        super.removeElementAt(0);
        return dataUnit;
    }

    public boolean empty() {
        return super.isEmpty();
    }

    public void clear() {
        super.removeAllElements();
    }
}
