package qsbk.app.live.model;

import com.google.gson.annotations.SerializedName;

public class LiveRedEnvelopesRecordInfo {
    @SerializedName(alternate = {"c"}, value = "coin")
    public long coin;
    @SerializedName(alternate = {"n"}, value = "count")
    public int count;
    @SerializedName(alternate = {"ct"}, value = "createAt")
    public long createAt;
    @SerializedName(alternate = {"dc"}, value = "foldCount")
    public int foldCount;
    public long id;
    @SerializedName(alternate = {"sc"}, value = "shareCoin")
    public long shareCoin;
    @SerializedName(alternate = {"s"}, value = "source")
    public long source;
    @SerializedName(alternate = {"u"}, value = "userId")
    public long userId;
}
