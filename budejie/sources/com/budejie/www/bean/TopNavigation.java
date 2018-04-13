package com.budejie.www.bean;

import com.budejie.www.util.ab;
import java.io.Serializable;

public class TopNavigation implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    public int display_level;
    public int display_num;
    public String entrytype;
    public String god_topic_type;
    public String name;
    public String recsys_url;
    public String type;
    public String url;

    public String getKey() {
        return ab.a(this.url);
    }

    public boolean equals(Object obj) {
        if (obj instanceof TopNavigation) {
            return this.name.equals(((TopNavigation) obj).name);
        }
        return super.equals(obj);
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
