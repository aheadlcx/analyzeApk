package qsbk.app.model;

import java.io.Serializable;
import qsbk.app.utils.json.JSONAble;

public class Banner extends JSONAble implements Serializable {
    public static final String TYPE_LIVE = "live";
    public static final String TYPE_USER = "user";
    public static final String TYPE_WEB = "web";
    public long platform_id;
    public double ratio;
    public long redirect_id;
    public long redirect_source;
    public String redirect_type;
    public String redirect_url;
    public String url;
}
