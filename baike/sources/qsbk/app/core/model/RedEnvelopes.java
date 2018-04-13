package qsbk.app.core.model;

import com.google.gson.annotations.SerializedName;

public class RedEnvelopes {
    @SerializedName(alternate = {"c"}, value = "coin")
    public long coin;
    public long id;
    @SerializedName(alternate = {"s"}, value = "size")
    public long size;
}
