package qsbk.app.live.model;

import com.google.gson.annotations.SerializedName;

public class LiveRedEnvelopesRecord {
    @SerializedName(alternate = {"c"}, value = "coin")
    public long coin;
    @SerializedName(alternate = {"ct"}, value = "createAt")
    public long createAt;
    public long id;
    @SerializedName(alternate = {"s"}, value = "source")
    public long source;
    @SerializedName(alternate = {"u"}, value = "user")
    public LiveUser user;
    @SerializedName(alternate = {"ui"}, value = "userId")
    public long userId;
}
